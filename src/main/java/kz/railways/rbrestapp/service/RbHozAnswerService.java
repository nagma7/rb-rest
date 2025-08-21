package kz.railways.rbrestapp.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import kz.railways.rbrestapp.entity.ChartsAnswer;
import kz.railways.rbrestapp.entity.RbHoz;
import kz.railways.rbrestapp.entity.RbHozAnswer;
import kz.railways.rbrestapp.repository.RbHozAnswerRepository;
import kz.railways.rbrestapp.utils.DateUtil;

@Service 
public class RbHozAnswerService {

	@Autowired
	RbHozAnswerRepository answerRepository;
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private RbHozService rbHozService;
	
	public Map<String, Object> getRbHozList(String date, String nod, String st){		
		Map<String, Object> ans = new HashMap<String, Object>();
        List<ChartsAnswer> rbHozChartsAnswerList = new ArrayList<ChartsAnswer>();
        List<String> nameList = new ArrayList<>();
        entityManager.clear();
        List<RbHoz> rbHozList = null;
        List<RbHozAnswer> rbHozAnswerList = null;
        entityManager.clear();
        switch (st) {
			case "99" -> {
				rbHozList = rbHozService.getRbHozList();
				rbHozAnswerList = answerRepository.findAll(DateUtil.getPreviousBeginDateFromStr(date.substring(0, 4)), 
	        		DateUtil.getPreviousBeginYearFromStr(date.substring(0, 4)) + date.substring(4), nod);
			}
			case "1","2","3","5" -> {
				rbHozList = rbHozService.getRbHozByStructList(st,1);
				rbHozAnswerList = answerRepository.findAllbyStruct(DateUtil.getPreviousBeginDateFromStr(date.substring(0, 4)), 
	        		DateUtil.getPreviousBeginYearFromStr(date.substring(0, 4)) + date.substring(4), nod, st);
			}
		}
        
        List<RbHozAnswer> allNbdLastList = new ArrayList<>();
        for(RbHoz cl: rbHozList) {
        	RbHozAnswer nb2 = new RbHozAnswer();
        	nb2.setId(cl.getId());
        	nb2.setTitle(cl.getTitle());
        	nb2.setCount(0);
        	allNbdLastList.add(nb2);
        }
        
        for(RbHozAnswer cl: allNbdLastList) {
        	for(RbHozAnswer bl:rbHozAnswerList) {
        		if(cl.getId() == bl.getId()) {
        			if (bl.getCount() == null)
    					bl.setCount(0);
        			cl.setCount(bl.getCount());
        		}
        	}
        }
                
        List<Integer> dLast = new ArrayList<>();
        ChartsAnswer narushBezopAnswerPrev = new ChartsAnswer();
        for(RbHozAnswer nb:allNbdLastList) {
        	if(narushBezopAnswerPrev.getName() == null)
        		narushBezopAnswerPrev.setName(DateUtil.getPreviousBeginYearFromStr(date.substring(0, 4)));        	
        	dLast.add(nb.getCount());
        	nameList.add(nb.getTitle());
        }
        
        narushBezopAnswerPrev.setData(dLast);
        rbHozChartsAnswerList.add(narushBezopAnswerPrev);
        
        entityManager.clear();
        switch (st) {
			case "99" -> {rbHozAnswerList = answerRepository.findAll(DateUtil.getBeginDate(date.substring(0, 4)), date, nod);
			}
			case "1","2","3","5" -> {rbHozAnswerList = answerRepository.findAllbyStruct(DateUtil.getBeginDate(date.substring(0, 4)), date, nod, st);
			}
		}
        allNbdLastList.clear();
        for(RbHoz cl: rbHozList) {
        	RbHozAnswer nb2 = new RbHozAnswer();
        	nb2.setId(cl.getId());
        	nb2.setTitle(cl.getTitle());
        	nb2.setCount(0);
        	allNbdLastList.add(nb2);
        }
        
        for(RbHozAnswer cl: allNbdLastList) {
        	for(RbHozAnswer bl:rbHozAnswerList) {
        		if(cl.getId() == bl.getId()) {
        			if (bl.getCount() == null)
    					bl.setCount(0);
        			cl.setCount(bl.getCount());
        		}
        	}
        }
        
        ChartsAnswer narushBezopAnswerCurr = new ChartsAnswer();        
        List<Integer> dCurr = new ArrayList<>();
        
        for(RbHozAnswer nb:allNbdLastList) {
        	if(narushBezopAnswerCurr.getName() == null)
        		narushBezopAnswerCurr.setName(DateUtil.getCurrentBeginYearFromStr(date.substring(0, 4)));        	
        	dCurr.add(nb.getCount());
        }
        
        narushBezopAnswerCurr.setData(dCurr);
        rbHozChartsAnswerList.add(narushBezopAnswerCurr);
        ans.put("names", nameList);
        ans.put("data", rbHozChartsAnswerList);
        return ans; 
	}
	
	public Map<String, Object> getNBDRbHozList(String bdate, String edate, String st, int own){		
		Map<String, Object> ans = new HashMap<String, Object>();
        List<ChartsAnswer> rbHozChartsAnswerList = new ArrayList<ChartsAnswer>();
        List<String> nameList = new ArrayList<>();
        entityManager.clear();
        List<RbHoz> rbHozList = null;
        List<RbHozAnswer> rbHozAnswerList = null;
        entityManager.clear();
		rbHozList = rbHozService.getRbHozByStructList(st, own);
		rbHozAnswerList = answerRepository.findAllbyStructNBD(DateUtil.getPreviousBeginYearFromStr(bdate.substring(0, 4)) + bdate.substring(4), 
    		DateUtil.getPreviousBeginYearFromStr(edate.substring(0, 4)) + edate.substring(4), st, own);
        
        List<RbHozAnswer> allNbdLastList = new ArrayList<>();
        /*for(RbHoz cl: rbHozList) {
        	RbHozAnswer nb2 = new RbHozAnswer();
        	nb2.setId(cl.getId());
        	nb2.setTitle(cl.getTitle());
        	nb2.setCount(0);
        	allNbdLastList.add(nb2);
        }*/
        
        for(RbHozAnswer bl:rbHozAnswerList) {
        	if(bl.getCount() > 0) {
	        	RbHozAnswer nb2 = new RbHozAnswer();
	        	nb2.setId(bl.getId());
	        	nb2.setTitle(bl.getTitle());
	        	nb2.setCount(bl.getCount());
	        	allNbdLastList.add(nb2);
        	}
        }
        	
        	/*for(RbHozAnswer bl:rbHozAnswerList) {
        		if(cl.getId() == bl.getId()) {
        			if (bl.getCount() == null)
    					bl.setCount(0);
        			cl.setCount(bl.getCount());
        		}
        	}
        }*/
        
        /*for(RbHozAnswer cl: allNbdLastList) {
        	for(RbHozAnswer bl:rbHozAnswerList) {
        		if(cl.getId() == bl.getId()) {
        			if (bl.getCount() == null)
    					bl.setCount(0);
        			cl.setCount(bl.getCount());
        		}
        	}
        }*/
                
        List<Integer> dLast = new ArrayList<>();
        ChartsAnswer narushBezopAnswerPrev = new ChartsAnswer();
        for(RbHozAnswer nb:allNbdLastList) {        	
        	if(narushBezopAnswerPrev.getName() == null)
        		narushBezopAnswerPrev.setName(DateUtil.getPreviousBeginYearFromStr(bdate.substring(0, 4)));
        	dLast.add(nb.getCount());
        	nameList.add(nb.getTitle());
        }
        
        narushBezopAnswerPrev.setData(dLast);
        rbHozChartsAnswerList.add(narushBezopAnswerPrev);
        
        entityManager.clear();
        rbHozAnswerList = answerRepository.findAllbyStructNBD(bdate, edate, st, own);		
		
        allNbdLastList = new ArrayList<>();
        /*for(RbHoz cl: rbHozList) {
        	RbHozAnswer nb2 = new RbHozAnswer();
        	nb2.setId(cl.getId());
        	nb2.setTitle(cl.getTitle());
        	nb2.setCount(0);
        	allNbdLastList.add(nb2);
        }
        
        for(RbHozAnswer cl: allNbdLastList) {
        	for(RbHozAnswer bl:rbHozAnswerList) {
        		if(cl.getId() == bl.getId()) {
        			if (bl.getCount() == null)
    					bl.setCount(0);
        			cl.setCount(bl.getCount());
        		}
        	}
        }*/
        
        for(RbHozAnswer bl:rbHozAnswerList) {
        	if(bl.getCount() > 0) {
	        	RbHozAnswer nb2 = new RbHozAnswer();
	        	nb2.setId(bl.getId());
	        	nb2.setTitle(bl.getTitle());
	        	nb2.setCount(bl.getCount());
	        	allNbdLastList.add(nb2);
        	}
        }
        
        ChartsAnswer narushBezopAnswerCurr = new ChartsAnswer();        
        List<Integer> dCurr = new ArrayList<>();
        
        for(RbHozAnswer nb:allNbdLastList) {
        	if(narushBezopAnswerCurr.getName() == null)
        		narushBezopAnswerCurr.setName(DateUtil.getCurrentBeginYearFromStr(edate.substring(0, 4)));        	
        	dCurr.add(nb.getCount());
        }
        
        narushBezopAnswerCurr.setData(dCurr);
        rbHozChartsAnswerList.add(narushBezopAnswerCurr);
        ans.put("names", nameList);
        ans.put("prevdata", narushBezopAnswerCurr);
        ans.put("currdata", narushBezopAnswerPrev);
        return ans; 
	}
}
