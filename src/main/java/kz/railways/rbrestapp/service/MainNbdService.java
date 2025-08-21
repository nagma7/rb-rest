package kz.railways.rbrestapp.service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
import kz.railways.rbrestapp.entity.MainNBD;
import kz.railways.rbrestapp.entity.NbdAnswer;
import kz.railways.rbrestapp.entity.RbStruktura;
import kz.railways.rbrestapp.repository.MainNBDRepository;
import kz.railways.rbrestapp.utils.DateUtil;

@Service
public class MainNbdService {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private MainNBDRepository mainNBDService;
	//private MainNBDRepository2 mainNBDService;
	
	@Autowired
	private RbStrukturaService rbStrukturaService;
	
	public List<NbdAnswer> getMainNbdAllData(String date, String nod){
		List<NbdAnswer> nbdAnswerList = new ArrayList<>();
		
        entityManager.clear();
        List<MainNBD> rbInDifficulties = mainNBDService.findAllNbd(DateUtil.getPreviousBeginDateFromStr(date.substring(0, 4)), 
        		DateUtil.getPreviousBeginYearFromStr(date.substring(0, 4)) + date.substring(4),DateUtil.getBeginDate(date.substring(0, 4)), date, nod);
        
        for (MainNBD curr:rbInDifficulties) {
        	if(curr.getSId() != 100) {
				NbdAnswer nbdAnswer = new NbdAnswer();
				nbdAnswer.setStrukt(curr.getStruktCode());
				if(curr.getCount() == null)
					nbdAnswer.setCount(0);
				else
					nbdAnswer.setCount(curr.getCount());
				if (curr.getLastCount() == null)
					nbdAnswer.setLastCount(0);
				else			
					nbdAnswer.setLastCount(curr.getLastCount());
		        nbdAnswerList.add(nbdAnswer);
        	}
	    }
        return nbdAnswerList;
    }
	
	public Map<String, Object> getRazrezStruct(String date, String nod){
		Map<String, Object> ans = new HashMap<String, Object>();
        List<ChartsAnswer> razrezAnswerList = new ArrayList<ChartsAnswer>();
        List<String> nameList = new ArrayList<>();
        
        entityManager.clear();
        List<RbStruktura> rbStrukturaList = rbStrukturaService.getRbStrukturaList(); 
        entityManager.clear();
        List<MainNBD> bezopList = mainNBDService.findAllNbd(DateUtil.getPreviousBeginDateFromStr(date.substring(0, 4)), 
        		DateUtil.getPreviousBeginYearFromStr(date.substring(0, 4)) + date.substring(4),DateUtil.getBeginDate(date.substring(0, 4)), date, nod);
        ChartsAnswer razrezPrev = new ChartsAnswer();
        ChartsAnswer razrezCurr = new ChartsAnswer();
        List<MainNBD> bezopAnsList = new ArrayList<>();
        for(RbStruktura rbstrukt: rbStrukturaList) {
        	MainNBD a = new MainNBD();
        	a.setSId(rbstrukt.getId());
        	a.setStruktCode(rbstrukt.getTitle());
        	bezopAnsList.add(a);
        } 
        
        for(MainNBD mAns :bezopAnsList) {
        	for(MainNBD mnb: bezopList) {
        		if(mAns.getSId() == mnb.getSId()) {
        			mAns.setCount(mnb.getCount());
        			mAns.setLastCount(mnb.getLastCount());
        		}
        	}
        }
        
        List<Integer> dLast = new ArrayList<>();
        List<Integer> dCurr = new ArrayList<>();
        for (MainNBD mn:bezopAnsList) {
        	if(mn.getSId() != 100) {
        		if(razrezPrev.getName() == null)
        			razrezPrev.setName(String.valueOf(DateUtil.getPreviousBeginYearFromStr(date.substring(0, 4))));
            	if(razrezCurr.getName()== null)
            		razrezCurr.setName(String.valueOf(DateUtil.getCurrentBeginYearFromStr(date.substring(0, 4))));				
				if(mn.getCount() == null)
					mn.setCount(0);
				if (mn.getLastCount() == null)
					mn.setLastCount(0);
				dLast.add(mn.getLastCount());
	        	dCurr.add(mn.getCount());
	        	nameList.add(mn.getStruktCode());
        	}
	    }
        razrezPrev.setData(dLast);
        razrezAnswerList.add(razrezPrev);
        razrezCurr.setData(dCurr);        
        razrezAnswerList.add(razrezCurr);        
        ans.put("names", nameList);
        ans.put("data", razrezAnswerList);
        return ans;
	}
	
	
	public Map<String, Object> getRazrezStruct1(String date, String nod){
        List<NbdAnswer> nbdAnswerList = new ArrayList<NbdAnswer>();
        
        List<RbStruktura> rbStrukturaList = rbStrukturaService.getRbStrukturaList();
        List<MainNBD> ansList = new ArrayList<>(); 
        for(RbStruktura cl: rbStrukturaList) {
        	MainNBD m =new MainNBD();
        	m.setSId(cl.getId());
        	m.setStruktCode(cl.getTitle());
        	m.setCount(0);
        	m.setLastCount(0);
        	ansList.add(m);
        }
        entityManager.clear();
        List<MainNBD> lastMainNBDList = mainNBDService.findAll(DateUtil.getPreviousBeginDateFromStr(date.substring(0, 4)), 
        		DateUtil.getPreviousBeginYearFromStr(date.substring(0, 4)) + date.substring(4), nod);
                
        for(MainNBD mnbd: ansList) {
        	for(MainNBD ansmnbd: lastMainNBDList) {
        		if(mnbd.getSId() == ansmnbd.getSId()) {
        			if (ansmnbd.getCount() == null)
        				ansmnbd.setCount(0);
        			mnbd.setLastCount(ansmnbd.getCount());
        		}
        		
        	}
        }
        
        entityManager.clear();
        List<MainNBD> currMainNBDList = mainNBDService.findAll(DateUtil.getBeginDate(date.substring(0, 4)), date, nod);
        
        for(MainNBD mnbd: ansList) {
        	for(MainNBD ansmnbd: currMainNBDList) {
        		if(mnbd.getSId() == ansmnbd.getSId()) {
        			if (ansmnbd.getCount() == null)
        				ansmnbd.setCount(0);
        			mnbd.setCount(ansmnbd.getCount());
        		}
        		
        	}
        }
        
        
        int countNBD = 0;
        int lastCountNBD = 0;
        List<Integer> dLast = new ArrayList<>();
        List<Integer> dCurr = new ArrayList<>();
        ChartsAnswer razrezPrev = new ChartsAnswer();
        ChartsAnswer razrezCurr = new ChartsAnswer();
        List<String> nameList = new ArrayList<>();
        
        for (MainNBD mn:ansList) {
        	if(mn.getSId() != 100) {
        		if(razrezPrev.getName() == null)
        			razrezPrev.setName(String.valueOf(DateUtil.getPreviousBeginYearFromStr(date.substring(0, 4))));
            	if(razrezCurr.getName()== null)
            		razrezCurr.setName(String.valueOf(DateUtil.getCurrentBeginYearFromStr(date.substring(0, 4))));				
				if(mn.getCount() == null)
					mn.setCount(0);
				if (mn.getLastCount() == null)
					mn.setLastCount(0);
				dLast.add(mn.getLastCount());
	        	dCurr.add(mn.getCount());
	        	nameList.add(mn.getStruktCode());
        	}
	    }
        List<ChartsAnswer> razrezAnswerList = new ArrayList<ChartsAnswer>();
        razrezPrev.setData(dLast);
        razrezAnswerList.add(razrezPrev);
        razrezCurr.setData(dCurr);        
        razrezAnswerList.add(razrezCurr);
        Map<String, Object> ans = new HashMap<String, Object>();
        ans.put("names", nameList);
        ans.put("data", razrezAnswerList);
        return ans;
    }
	
	
	public Map<String, Object> getCpDOData(String beginDate, String endDate){
        List<NbdAnswer> nbdAnswerList = new ArrayList<NbdAnswer>();
        
        List<RbStruktura> rbStrukturaList = rbStrukturaService.getRbStrukturaList();
        List<MainNBD> ansList = new ArrayList<>(); 
        for(RbStruktura cl: rbStrukturaList) {
        	MainNBD m =new MainNBD();
        	m.setSId(cl.getId());
        	m.setStruktCode(cl.getTitle());
        	m.setCount(0);
        	m.setLastCount(0);
        	ansList.add(m);
        }
        entityManager.clear();
        List<MainNBD> lastMainNBDList = mainNBDService.findCPDO(DateUtil.getPreviousBeginYearFromStr(beginDate.substring(0, 4)) + beginDate.substring(4), 
        		DateUtil.getPreviousBeginYearFromStr(endDate.substring(0, 4)) + endDate.substring(4));
                
        for(MainNBD mnbd: ansList) {
        	for(MainNBD ansmnbd: lastMainNBDList) {
        		if(mnbd.getSId() == ansmnbd.getSId()) {
        			if (ansmnbd.getCount() == null)
        				ansmnbd.setCount(0);
        			mnbd.setLastCount(ansmnbd.getCount());
        		}
        		
        	}
        }
        
        entityManager.clear();
        List<MainNBD> currMainNBDList = mainNBDService.findCPDO(beginDate, endDate);
        
        for(MainNBD mnbd: ansList) {
        	for(MainNBD ansmnbd: currMainNBDList) {
        		if(mnbd.getSId() == ansmnbd.getSId()) {
        			if (ansmnbd.getCount() == null)
        				ansmnbd.setCount(0);
        			mnbd.setCount(ansmnbd.getCount());
        		}
        		
        	}
        }
        
        
        int countNBD = 0;
        int lastCountNBD = 0;
        List<Integer> dLast = new ArrayList<>();
        List<Integer> dCurr = new ArrayList<>();
        ChartsAnswer razrezPrev = new ChartsAnswer();
        ChartsAnswer razrezCurr = new ChartsAnswer();
        List<String> nameList = new ArrayList<>();        
        for (MainNBD mn:ansList) {
        	if(mn.getSId() != 100) {
        		if(razrezPrev.getName() == null)
        			razrezPrev.setName(DateUtil.getPreviousBeginYearFromStr(beginDate.substring(0, 4)));
            	if(razrezCurr.getName()== null)
            		razrezCurr.setName(DateUtil.getCurrentBeginYearFromStr(endDate.substring(0, 4)));				
				if(mn.getCount() == null)
					mn.setCount(0);
				if (mn.getLastCount() == null)
					mn.setLastCount(0);
				dLast.add(mn.getLastCount());
	        	dCurr.add(mn.getCount());
	        	nameList.add(mn.getStruktCode());
        	}
	    }
        List<ChartsAnswer> razrezAnswerList = new ArrayList<ChartsAnswer>();
        razrezPrev.setData(dLast);
        razrezAnswerList.add(razrezPrev);
        razrezCurr.setData(dCurr);        
        razrezAnswerList.add(razrezCurr);
        Map<String, Object> ans = new HashMap<String, Object>();
        ans.put("names", nameList);
        //ans.put("data", razrezAnswerList);
        ans.put("prevdata", razrezPrev);
        ans.put("currdata", razrezCurr);
        return ans;
    }
	
}
