package kz.railways.rbrestapp.service;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import kz.railways.rbrestapp.entity.ChartsAnswer;
import kz.railways.rbrestapp.entity.MonthData;
import kz.railways.rbrestapp.entity.MonthDataAnswer;
import kz.railways.rbrestapp.repository.MonthDataRepository;
import kz.railways.rbrestapp.utils.DateUtil;

@Service
public class MonthDataService {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private MonthDataRepository monthDataRepository;
	
	public List<MonthDataAnswer> getMonthAllData(String date, String nod){
		List<MonthDataAnswer> answers = new ArrayList<>();
        entityManager.clear();
        List<MonthData> lastAllMonthList = monthDataRepository.findMonthData(DateUtil.getPreviousBeginDateFromStr(date.substring(0, 4)), 
        		DateUtil.getPreviousBeginYearFromStr(date.substring(0, 4)) + date.substring(4), nod);
        
        entityManager.clear();
        List<MonthData> currAllMonthList = monthDataRepository.findMonthData(DateUtil.getBeginDate(date.substring(0, 4)), date, nod);
        
        MonthDataAnswer lastAnswer = new MonthDataAnswer();
        lastAnswer.setYear(DateUtil.getPreviousBeginYearFromStr(date.substring(0, 4)));
        lastAnswer.setMonthDataList(lastAllMonthList);
        answers.add(lastAnswer);
        
        MonthDataAnswer currAnswer = new MonthDataAnswer();                
        currAnswer.setYear(DateUtil.getCurrentBeginYearFromStr(date.substring(0, 4)));
        currAnswer.setMonthDataList(currAllMonthList);
        answers.add(currAnswer);
        return answers;
	}
	
	String getMonthForInt(int num) {
	    String month = "wrong";
	    DateFormatSymbols dfs = new DateFormatSymbols();
	    String[] months = dfs.getMonths();
	    if (num >= 0 && num <= 11) {
	        month = months[num];
	    }
	    return month;
	}
	
	public Map<String, Object> getMonthAllData1(String date, String nod, String st){
		Map<String, Object> ans = new HashMap<String, Object>();
        List<ChartsAnswer> monthAnswerList = new ArrayList<ChartsAnswer>();
        List<String> nameList = new ArrayList<>();
        ChartsAnswer monthAnswerPrev = new ChartsAnswer();
        ChartsAnswer monthAnswerCurr = new ChartsAnswer();
        List<MonthData> lastEmptyMonthList = new ArrayList<>();
    	for(int i = 1; i<=12; i++) { 
    		MonthData md = new MonthData();
    		md.setCount(0);
    		md.setMDate(String.valueOf(i));
    		md.setYDate(0);
    		lastEmptyMonthList.add(md);    		
    		nameList.add(getMonthForInt(i-1));
    	}
    	List<MonthData> lastAllMonthList = null;
        entityManager.clear();
        switch (st) {
			case "99" -> {lastAllMonthList = monthDataRepository.findMonthData(DateUtil.getPreviousBeginDateFromStr(date.substring(0, 4)), 
	        		DateUtil.getPreviousBeginYearFromStr(date.substring(0, 4)) + date.substring(4), nod);
			}
			case "1","2","3","5" -> {lastAllMonthList = monthDataRepository.findMonthDataByStruct(DateUtil.getPreviousBeginDateFromStr(date.substring(0, 4)), 
	        		DateUtil.getPreviousBeginYearFromStr(date.substring(0, 4)) + date.substring(4), nod, st);
			}
		}        
        
        for(MonthData emd:lastEmptyMonthList) {
        	for(MonthData md:lastAllMonthList) {
        		if(md.getMDate().equals(emd.getMDate())) {
        			emd.setCount(md.getCount());
        			emd.setYDate(md.getYDate());
        		}
        	}
        }
        
        List<Integer> dLast = new ArrayList<>();
        for(MonthData md:lastEmptyMonthList) {
       		monthAnswerPrev.setName(DateUtil.getPreviousBeginYearFromStr(date.substring(0, 4)));
        	dLast.add(md.getCount());
        }
        
        List<MonthData> currEmptyMonthList = new ArrayList<>();
    	for(int i = 1; i<=12; i++) { 
    		MonthData md = new MonthData();
    		md.setCount(0);
    		md.setMDate(String.valueOf(i));
    		md.setYDate(0);
    		currEmptyMonthList.add(md);
    	}
    	
    	List<MonthData> currAllMonthList = null;
        entityManager.clear();
        switch (st) {
			case "99" -> {currAllMonthList = monthDataRepository.findMonthData(DateUtil.getBeginDate(date.substring(0, 4)), date, nod);
			}
			case "1","2","3","5" -> {currAllMonthList = monthDataRepository.findMonthDataByStruct(DateUtil.getBeginDate(date.substring(0, 4)), date, nod, st);
			}
		}
        
        for(MonthData emd1:currEmptyMonthList) {
        	for(MonthData md1:currAllMonthList) {
        		if(md1.getMDate().equals(emd1.getMDate())) {
        			emd1.setCount(md1.getCount());
        			emd1.setYDate(md1.getYDate());
        		}
        	}
        }
        
        List<Integer> dCurr = new ArrayList<>();
        for(MonthData md:currEmptyMonthList) {
        	if(monthAnswerCurr.getName()== null)
        		monthAnswerCurr.setName(DateUtil.getCurrentBeginYearFromStr(date.substring(0, 4)));        	
        	dCurr.add(md.getCount());
        }
        
        /*MonthDataAnswer lastAnswer = new MonthDataAnswer();
        lastAnswer.setYear(DateUtil.getPreviousBeginYearFromStr(date.substring(0, 4)));
        lastAnswer.setMonthDataList(lastAllMonthList);
        answers.add(lastAnswer);
        
        MonthDataAnswer currAnswer = new MonthDataAnswer();                
        currAnswer.setYear(DateUtil.getCurrentBeginYearFromStr(date.substring(0, 4)));
        currAnswer.setMonthDataList(currAllMonthList);
        answers.add(currAnswer);*/
        
        monthAnswerPrev.setData(dLast);
        monthAnswerList.add(monthAnswerPrev);
        monthAnswerCurr.setData(dCurr);
        monthAnswerList.add(monthAnswerCurr);
        
        ans.put("names", nameList);
        ans.put("data", monthAnswerList);
        return ans;
	}
        
}
