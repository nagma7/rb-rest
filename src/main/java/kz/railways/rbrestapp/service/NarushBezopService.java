package kz.railways.rbrestapp.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import kz.railways.rbrestapp.entity.ChartsAnswer;
import kz.railways.rbrestapp.entity.NarushBezop;
import kz.railways.rbrestapp.entity.NarushBezop2;
import kz.railways.rbrestapp.entity.NarushBezopAnswer;
import kz.railways.rbrestapp.entity.RbClassifier;
import kz.railways.rbrestapp.entity.Region;
import kz.railways.rbrestapp.repository.NarushBezopRepository;
import kz.railways.rbrestapp.repository.NarushBezopRepository2;
import kz.railways.rbrestapp.repository.RegionRepository;
import kz.railways.rbrestapp.utils.DateUtil;

@Service
public class NarushBezopService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private NarushBezopRepository narushBezopService;
	
	@Autowired
	private NarushBezopRepository2 narushBezopService2;

	@Autowired
	private RbClassifierService classifierService;
	
	@Autowired
	private RegionRepository regionRepository;
	
	public List<List<NarushBezop>> getRbNarushBezop(Map<String, String> personJsonObject){
        List<List<NarushBezop>> allNarushBezopList = new ArrayList<List<NarushBezop>>();
        entityManager.clear();
        allNarushBezopList.add(narushBezopService.findAll(DateUtil.getPreviousBeginDateFromStr(personJsonObject.get("date").substring(0, 4)), 
        		DateUtil.getPreviousBeginYearFromStr(personJsonObject.get("date").substring(0, 4)) + personJsonObject.get("date").substring(4), personJsonObject.get("nod")));
        entityManager.clear();
        allNarushBezopList.add(narushBezopService.findAll(DateUtil.getBeginDate(personJsonObject.get("date").substring(0, 4)), 
        		personJsonObject.get("date"), personJsonObject.get("nod")));
        return allNarushBezopList;
    }
	
	public List<NarushBezopAnswer> getNarushBezopAnswer1(Map<String, String> personJsonObject){
        entityManager.clear();
        List<NarushBezopAnswer> narushBezopAnswerList = new ArrayList<NarushBezopAnswer>();
        //Map<String, List<NarushBezopBig>> allNarushBezopMap = new HashMap<>();
        //allNarushBezopList.add(narushBezopBigService.findAll(DateUtil.getPreviousYear()+"-01-01", DateUtil.getPreviousYear()+"-"+mnd));
        NarushBezopAnswer narushBezopAnswerPrev = new NarushBezopAnswer();
        narushBezopAnswerPrev.setYear(DateUtil.getPreviousBeginYearFromStr(personJsonObject.get("date").substring(0, 4)));
        narushBezopAnswerPrev.setNarushBezopList(narushBezopService.findAll(DateUtil.getPreviousBeginDateFromStr(personJsonObject.get("date").substring(0, 4)), 
        		DateUtil.getPreviousBeginYearFromStr(personJsonObject.get("date").substring(0, 4)) + personJsonObject.get("date").substring(4), personJsonObject.get("nod")));
        narushBezopAnswerList.add(narushBezopAnswerPrev);
        //allNarushBezopList.add(narushBezopBigService.findAll());
        //allNarushBezopMap.put(DateUtil.getPreviousYear(), narushBezopBigService.findAll(DateUtil.getPreviousYear()+"-01-01", DateUtil.getPreviousYear()+"-"+mnd));
        //allNarushBezopMap.put(DateUtil.getPreviousYear(), narushBezopBigService.findAll());
        
        entityManager.clear();
        NarushBezopAnswer narushBezopAnswerCurr = new NarushBezopAnswer();
        narushBezopAnswerCurr.setYear(DateUtil.getCurrentBeginYearFromStr(personJsonObject.get("date").substring(0, 4)));
        narushBezopAnswerCurr.setNarushBezopList(narushBezopService.findAll(DateUtil.getBeginDate(personJsonObject.get("date").substring(0, 4)), 
        		personJsonObject.get("date"), personJsonObject.get("nod")));
        narushBezopAnswerList.add(narushBezopAnswerCurr);
        //allNarushBezopList.add(narushBezopBigService.findAll(DateUtil.getCurrentYear()+"-01-01", dB));
        //allNarushBezopList.add(narushBezopBigService.findAll2());
        //allNarushBezopMap.put(DateUtil.getCurrentYear(), narushBezopBigService.findAll(DateUtil.getCurrentYear()+"-01-01", dB));
        //allNarushBezopMap.put(DateUtil.getCurrentYear(), narushBezopBigService.findAll2());
        return narushBezopAnswerList;
    }
	
	public List<NarushBezopAnswer> getNarushBezopAnswer(String date, String nod){		
        entityManager.clear();
        List<NarushBezopAnswer> narushBezopAnswerList = new ArrayList<NarushBezopAnswer>();
        NarushBezopAnswer narushBezopAnswerPrev = new NarushBezopAnswer();
        narushBezopAnswerPrev.setYear(DateUtil.getPreviousBeginYearFromStr(date.substring(0, 4)));
        narushBezopAnswerPrev.setNarushBezopList(narushBezopService.findAll(DateUtil.getPreviousBeginDateFromStr(date.substring(0, 4)), 
        		DateUtil.getPreviousBeginYearFromStr(date.substring(0, 4)) + date.substring(4), nod));
        narushBezopAnswerList.add(narushBezopAnswerPrev);
        
        entityManager.clear();
        NarushBezopAnswer narushBezopAnswerCurr = new NarushBezopAnswer();
        narushBezopAnswerCurr.setYear(DateUtil.getCurrentBeginYearFromStr(date.substring(0, 4)));
        narushBezopAnswerCurr.setNarushBezopList(narushBezopService.findAll(DateUtil.getBeginDate(date.substring(0, 4)), date, nod));
        narushBezopAnswerList.add(narushBezopAnswerCurr);
        return narushBezopAnswerList;
    }
	
	public List<ChartsAnswer> getNarushBezopAnswer1(String date, String nod){		
        
        List<ChartsAnswer> narushBezopAnswerList = new ArrayList<ChartsAnswer>();
        
        ChartsAnswer narushBezopAnswerPrev = new ChartsAnswer();
        narushBezopAnswerPrev.setName(DateUtil.getPreviousBeginYearFromStr(date.substring(0, 4)));
        entityManager.clear();
        List<NarushBezop> bezopList = narushBezopService.findAll(DateUtil.getPreviousBeginDateFromStr(date.substring(0, 4)), 
        		DateUtil.getPreviousBeginYearFromStr(date.substring(0, 4)) + date.substring(4), nod);
        List<Integer> d = new ArrayList<>();
        for(NarushBezop nb:bezopList) {
        	d.add(nb.getCount());
        }
        narushBezopAnswerPrev.setData(d);
        narushBezopAnswerList.add(narushBezopAnswerPrev);
        
        entityManager.clear();
        ChartsAnswer narushBezopAnswerCurr = new ChartsAnswer();
        narushBezopAnswerCurr.setName(DateUtil.getCurrentBeginYearFromStr(date.substring(0, 4)));        
        List<NarushBezop> bezopList2 = narushBezopService.findAll(DateUtil.getBeginDate(date.substring(0, 4)), date, nod);
        List<Integer> d2 = new ArrayList<>();
        for(NarushBezop nb:bezopList2) {
        	d2.add(nb.getCount());
        }
        narushBezopAnswerCurr.setData(d2);
        narushBezopAnswerList.add(narushBezopAnswerCurr);
        return narushBezopAnswerList;
    }
	
	public Map<String, Object> getNarushBezopAnswer12(String date, String nod, String st){		
		Map<String, Object> ans = new HashMap<String, Object>();
        List<ChartsAnswer> narushBezopAnswerList = new ArrayList<ChartsAnswer>();
        List<String> nameList = new ArrayList<>();
        entityManager.clear();
        List<RbClassifier> classifierList = classifierService.getRbClassifierList();
        List<NarushBezop2> bezopList = null;
        entityManager.clear();
        switch (st) {
			case "99" -> {bezopList = narushBezopService2.findAll2(DateUtil.getPreviousBeginDateFromStr(date.substring(0, 4)), 
	        		DateUtil.getPreviousBeginYearFromStr(date.substring(0, 4)) + date.substring(4), DateUtil.getBeginDate(date.substring(0, 4)), date, nod);
			}
			case "1","2","3","5" -> {bezopList = narushBezopService2.findAllBySt(DateUtil.getPreviousBeginDateFromStr(date.substring(0, 4)), 
	        		DateUtil.getPreviousBeginYearFromStr(date.substring(0, 4)) + date.substring(4), DateUtil.getBeginDate(date.substring(0, 4)), date, nod, st);
			}
		}
        
        ChartsAnswer narushBezopAnswerPrev = new ChartsAnswer();
        ChartsAnswer narushBezopAnswerCurr = new ChartsAnswer();
        List<NarushBezop2> allNb2List = new ArrayList<>();
        for(RbClassifier cl: classifierList) {
        	NarushBezop2 nb2 = new NarushBezop2();
        	nb2.setId(cl.getId());
        	nb2.setTitle(cl.getTitle());
        	allNb2List.add(nb2);
        }
        
        for(NarushBezop2 cl: allNb2List) {
        	for(NarushBezop2 bl:bezopList) {
        		if(cl.getId() == bl.getId()) {
        			cl.setYear(bl.getYear());
        			cl.setCount(bl.getCount());
        			cl.setLastyear(bl.getLastyear());
        			cl.setLastCount(bl.getLastCount());
        		}
        	}
        }
        
        List<Integer> dLast = new ArrayList<>();
        List<Integer> dCurr = new ArrayList<>();
        int lastGeneral = 0;
        int currGeneral = 0;
        for(NarushBezop2 nb:allNb2List) {
        	if(narushBezopAnswerPrev.getName() == null)
        		narushBezopAnswerPrev.setName(DateUtil.getPreviousBeginYearFromStr(date.substring(0, 4)));
        	if(narushBezopAnswerCurr.getName()== null)
        		narushBezopAnswerCurr.setName(DateUtil.getCurrentBeginYearFromStr(date.substring(0, 4)));
        	if(nb.getCount() == null)
        		nb.setCount(0);
        	if(nb.getLastCount() == null)
        		nb.setLastCount(0);        	
        	dLast.add(nb.getLastCount());
        	dCurr.add(nb.getCount());
        	nameList.add(nb.getTitle());
        	lastGeneral += nb.getLastCount();
            currGeneral += nb.getCount();
        }
        dLast.add(lastGeneral);
        dCurr.add(currGeneral);
        narushBezopAnswerPrev.setData(dLast);
        narushBezopAnswerList.add(narushBezopAnswerPrev);
        narushBezopAnswerCurr.setData(dCurr);
        narushBezopAnswerList.add(narushBezopAnswerCurr);
        nameList.add("Общ.НБД");
        ans.put("names", nameList);
        ans.put("data", narushBezopAnswerList);
        return ans;
    }
	
	
	public Map<String, Object> getNarushBezopAnswer123(String date, String nod, String st){		
		Map<String, Object> ans = new HashMap<String, Object>();
        List<ChartsAnswer> narushBezopAnswerList = new ArrayList<ChartsAnswer>();
        List<String> nameList = new ArrayList<>();
        entityManager.clear();
        List<RbClassifier> classifierList = classifierService.getRbClassifierList();
        List<NarushBezop> bezopList = null;
        entityManager.clear();
        switch (st) {
			case "99" -> {bezopList = narushBezopService.findAll(
					DateUtil.getPreviousBeginDateFromStr(date.substring(0, 4)), 
	        		DateUtil.getPreviousBeginYearFromStr(date.substring(0, 4)) + date.substring(4), nod);
			}
			case "1","2","3","5" -> {bezopList = narushBezopService.findAllByStruct(DateUtil.getPreviousBeginDateFromStr(date.substring(0, 4)), 
	        		DateUtil.getPreviousBeginYearFromStr(date.substring(0, 4)) + date.substring(4), nod, st);
			}
		}
        
        List<NarushBezop> allNbdLastList = new ArrayList<>();
        for(RbClassifier cl: classifierList) {
        	NarushBezop nb2 = new NarushBezop();
        	nb2.setTitle(cl.getTitle());
        	allNbdLastList.add(nb2);
        }
        
        for(NarushBezop cl: allNbdLastList) {
        	for(NarushBezop bl:bezopList) {
        		if(cl.getTitle().equals(bl.getTitle())) {
        			cl.setYear(bl.getYear());
        			cl.setCount(bl.getCount());
        		}
        	}
        }
                
        List<Integer> dLast = new ArrayList<>();
        int lastGeneral = 0;
        ChartsAnswer narushBezopAnswerPrev = new ChartsAnswer();
        for(NarushBezop nb:allNbdLastList) {
        	if(narushBezopAnswerPrev.getName() == null)
        		narushBezopAnswerPrev.setName(DateUtil.getPreviousBeginYearFromStr(date.substring(0, 4)));        	
        	dLast.add(nb.getCount());
        	nameList.add(nb.getTitle());
        	lastGeneral += nb.getCount();
        }
        
        dLast.add(lastGeneral);        
        narushBezopAnswerPrev.setData(dLast);
        narushBezopAnswerList.add(narushBezopAnswerPrev);
        
        entityManager.clear();
        switch (st) {
			case "99" -> {bezopList = narushBezopService.findAll(DateUtil.getBeginDate(date.substring(0, 4)), date, nod);
			}
			case "1","2","3","5" -> {bezopList = narushBezopService.findAllByStruct(DateUtil.getBeginDate(date.substring(0, 4)), date, nod, st);
			}
		}
        allNbdLastList.clear();
        for(RbClassifier cl: classifierList) {
        	NarushBezop nb2 = new NarushBezop();
        	nb2.setTitle(cl.getTitle());
        	allNbdLastList.add(nb2);
        }
        
        for(NarushBezop cl: allNbdLastList) {
        	for(NarushBezop bl:bezopList) {
        		if(cl.getTitle().equals(bl.getTitle())) {
        			cl.setYear(bl.getYear());
        			cl.setCount(bl.getCount());
        		}
        	}
        }
        
        ChartsAnswer narushBezopAnswerCurr = new ChartsAnswer();        
        List<Integer> dCurr = new ArrayList<>();
        int currGeneral = 0;
        
        for(NarushBezop nb:allNbdLastList) {
        	if(narushBezopAnswerCurr.getName() == null)
        		narushBezopAnswerCurr.setName(DateUtil.getCurrentBeginYearFromStr(date.substring(0, 4)));        	
        	dCurr.add(nb.getCount());        	
        	currGeneral += nb.getCount();
        }
        
        dCurr.add(currGeneral);
        narushBezopAnswerCurr.setData(dCurr);
        narushBezopAnswerList.add(narushBezopAnswerCurr);
        nameList.add("Общ.НБД");
        ans.put("names", nameList);
        ans.put("data", narushBezopAnswerList);
        return ans;
    }
	
	
	
	
	public Map<String, Object> getNarushBezopWithoutNod(String beginDate, String endDate){		
		Map<String, Object> ans = new HashMap<String, Object>();
        List<ChartsAnswer> narushBezopAnswerList = new ArrayList<ChartsAnswer>();
        List<String> nameList = new ArrayList<>();
        entityManager.clear();
        List<RbClassifier> classifierList = classifierService.getRbClassifierList();
        List<NarushBezop> bezopList = null;
        entityManager.clear();
        bezopList = narushBezopService.findAllWithoutNod(DateUtil.getPreviousBeginYearFromStr(beginDate.substring(0, 4)) + beginDate.substring(4), 
        		DateUtil.getPreviousBeginYearFromStr(endDate.substring(0, 4)) + endDate.substring(4));
        /*switch (st) {
			case "99" -> {bezopList = narushBezopService.findAll(DateUtil.getPreviousBeginDateFromStr(date.substring(0, 4)), 
	        		DateUtil.getPreviousBeginYearFromStr(date.substring(0, 4)) + date.substring(4), nod);
			}
			case "1","2","3","5" -> {bezopList = narushBezopService.findAllByStruct(DateUtil.getPreviousBeginDateFromStr(date.substring(0, 4)), 
	        		DateUtil.getPreviousBeginYearFromStr(date.substring(0, 4)) + date.substring(4), nod, st);
			}
		}*/
        
        List<NarushBezop> allNbdLastList = new ArrayList<>();
        for(RbClassifier cl: classifierList) {
        	NarushBezop nb2 = new NarushBezop();
        	nb2.setTitle(cl.getTitle());
        	allNbdLastList.add(nb2);
        }
        
        for(NarushBezop cl: allNbdLastList) {
        	for(NarushBezop bl:bezopList) {
        		if(cl.getTitle().equals(bl.getTitle())) {
        			cl.setYear(bl.getYear());
        			cl.setCount(bl.getCount());
        		}
        	}
        }
                
        List<Integer> dLast = new ArrayList<>();
        int lastGeneral = 0;
        ChartsAnswer narushBezopAnswerPrev = new ChartsAnswer();
        for(NarushBezop nb:allNbdLastList) {
        	if(narushBezopAnswerPrev.getName() == null)
        		narushBezopAnswerPrev.setName(DateUtil.getPreviousBeginYearFromStr(beginDate.substring(0, 4)));        	
        	dLast.add(nb.getCount());
        	nameList.add(nb.getTitle());
        	lastGeneral += nb.getCount();
        }
        
        
        
        entityManager.clear();
        bezopList = narushBezopService.findAllWithoutNod(beginDate, endDate);
        /*switch (st) {
			case "99" -> {bezopList = narushBezopService.findAll(DateUtil.getBeginDate(date.substring(0, 4)), date, nod);
			}
			case "1","2","3","5" -> {bezopList = narushBezopService.findAllByStruct(DateUtil.getBeginDate(date.substring(0, 4)), date, nod, st);
			}
		}*/
        allNbdLastList.clear();
        for(RbClassifier cl: classifierList) {
        	NarushBezop nb2 = new NarushBezop();
        	nb2.setTitle(cl.getTitle());
        	allNbdLastList.add(nb2);
        }
        
        for(NarushBezop cl: allNbdLastList) {
        	for(NarushBezop bl:bezopList) {
        		if(cl.getTitle().equals(bl.getTitle())) {
        			cl.setYear(bl.getYear());
        			cl.setCount(bl.getCount());
        		}
        	}
        }
        
        ChartsAnswer narushBezopAnswerCurr = new ChartsAnswer();        
        List<Integer> dCurr = new ArrayList<>();
        int currGeneral = 0;
        
        for(NarushBezop nb:allNbdLastList) {
        	if(narushBezopAnswerCurr.getName() == null)
        		narushBezopAnswerCurr.setName(DateUtil.getCurrentBeginYearFromStr(endDate.substring(0, 4)));        	
        	dCurr.add(nb.getCount());        	
        	currGeneral += nb.getCount();
        }
        
        dCurr.add(currGeneral);
        narushBezopAnswerCurr.setData(dCurr);
        narushBezopAnswerList.add(narushBezopAnswerCurr);
        
        dLast.add(lastGeneral);
        narushBezopAnswerPrev.setData(dLast);
        narushBezopAnswerList.add(narushBezopAnswerPrev);
        nameList.add("НБД");
        ans.put("names", nameList);
        ans.put("data", narushBezopAnswerList);
        return ans;
    }
	
	public Map<String, Object> getCompNOther(String beginDate, String endDate, int own){		
		Map<String, Object> ans = new HashMap<String, Object>();
        List<ChartsAnswer> narushBezopAnswerList = new ArrayList<ChartsAnswer>();
        List<String> nameList = new ArrayList<>();
        entityManager.clear();
        List<RbClassifier> classifierList = classifierService.getRbClassifierList();
        List<NarushBezop> bezopList = null;
        entityManager.clear();
        bezopList = narushBezopService.findCompNOther(DateUtil.getPreviousBeginYearFromStr(beginDate.substring(0, 4)) + beginDate.substring(4), 
        		DateUtil.getPreviousBeginYearFromStr(endDate.substring(0, 4)) + endDate.substring(4), own);
        /*switch (st) {
			case "99" -> {bezopList = narushBezopService.findAll(DateUtil.getPreviousBeginDateFromStr(date.substring(0, 4)), 
	        		DateUtil.getPreviousBeginYearFromStr(date.substring(0, 4)) + date.substring(4), nod);
			}
			case "1","2","3","5" -> {bezopList = narushBezopService.findAllByStruct(DateUtil.getPreviousBeginDateFromStr(date.substring(0, 4)), 
	        		DateUtil.getPreviousBeginYearFromStr(date.substring(0, 4)) + date.substring(4), nod, st);
			}
		}*/
        
        List<NarushBezop> allNbdLastList = new ArrayList<>();
        for(RbClassifier cl: classifierList) {
        	NarushBezop nb2 = new NarushBezop();
        	nb2.setTitle(cl.getTitle());
        	allNbdLastList.add(nb2);
        }
        
        for(NarushBezop cl: allNbdLastList) {
        	for(NarushBezop bl:bezopList) {
        		if(cl.getTitle().equals(bl.getTitle())) {
        			cl.setYear(bl.getYear());
        			cl.setCount(bl.getCount());
        		}
        	}
        }
                
        List<Integer> dLast = new ArrayList<>();
        int lastGeneral = 0;
        ChartsAnswer narushBezopAnswerPrev = new ChartsAnswer();
        for(NarushBezop nb:allNbdLastList) {
        	if(narushBezopAnswerPrev.getName() == null)
        		narushBezopAnswerPrev.setName(DateUtil.getPreviousBeginYearFromStr(beginDate.substring(0, 4)));        	
        	dLast.add(nb.getCount());
        	nameList.add(nb.getTitle());
        	lastGeneral += nb.getCount();
        }
        
                
        narushBezopAnswerPrev.setData(dLast);
        narushBezopAnswerList.add(narushBezopAnswerPrev);
        
        entityManager.clear();
        bezopList = narushBezopService.findCompNOther(beginDate, endDate, own);
        /*switch (st) {
			case "99" -> {bezopList = narushBezopService.findAll(DateUtil.getBeginDate(date.substring(0, 4)), date, nod);
			}
			case "1","2","3","5" -> {bezopList = narushBezopService.findAllByStruct(DateUtil.getBeginDate(date.substring(0, 4)), date, nod, st);
			}
		}*/
        allNbdLastList.clear();
        for(RbClassifier cl: classifierList) {
        	NarushBezop nb2 = new NarushBezop();
        	nb2.setTitle(cl.getTitle());
        	allNbdLastList.add(nb2);
        }
        
        for(NarushBezop cl: allNbdLastList) {
        	for(NarushBezop bl:bezopList) {
        		if(cl.getTitle().equals(bl.getTitle())) {
        			cl.setYear(bl.getYear());
        			cl.setCount(bl.getCount());
        		}
        	}
        }
        
        ChartsAnswer narushBezopAnswerCurr = new ChartsAnswer();        
        List<Integer> dCurr = new ArrayList<>();
        int currGeneral = 0;
        
        for(NarushBezop nb:allNbdLastList) {
        	if(narushBezopAnswerCurr.getName() == null)
        		narushBezopAnswerCurr.setName(DateUtil.getCurrentBeginYearFromStr(endDate.substring(0, 4)));        	
        	dCurr.add(nb.getCount());        	
        	currGeneral += nb.getCount();
        }
                
        narushBezopAnswerCurr.setData(dCurr);
        narushBezopAnswerList.add(narushBezopAnswerCurr);        
        ans.put("names", nameList);
        ans.put("lastdata", narushBezopAnswerPrev);
        ans.put("currdata", narushBezopAnswerCurr);
        return ans;
    }
	
	public Map<String, Object> getNjsNodgpData(String bDate, String eDate, String st){		
		Map<String, Object> ans = new HashMap<String, Object>();
        List<ChartsAnswer> narushBezopAnswerList = new ArrayList<ChartsAnswer>();
        List<String> nameList = new ArrayList<>();
        
        List<String> nodNameList = new ArrayList<>();
        List<Integer> incidentList = new ArrayList<>();
    	List<Integer> sobytiyaList = new ArrayList<>();
    	List<Integer> avariyaList = new ArrayList<>();
    	List<Integer> krusheniaList = new ArrayList<>();
    	
        Map<String, Object> njsMap = new HashMap<String, Object>();
        Map<String, Object> nodgpMap = new HashMap<String, Object>();
        entityManager.clear();
        List<RbClassifier> classifierList = classifierService.getRbClassifierList();
        List<NarushBezop> bezopList = null;        
        List<Region> regionList = regionRepository.findNodList();
        for(Region r: regionList) {
        	nodNameList.add(r.getTitle());
        	krusheniaList.add(0);
        	avariyaList.add(0);
			sobytiyaList.add(0);
			incidentList.add(0);
        }
        int i = 0;
        for(Region r: regionList) {
	        entityManager.clear();
	        bezopList = narushBezopService.findNbdAllByStruct(DateUtil.getPreviousBeginYearFromStr(bDate.substring(0, 4)) + bDate.substring(4), 
		        		DateUtil.getPreviousBeginYearFromStr(eDate.substring(0, 4)) + eDate.substring(4), r.getCode().toString(), st);
        	for(NarushBezop b:bezopList) {
        		switch (b.getTitle()) {
					case "Крушения": krusheniaList.set(i, b.getCount());
					case "Авария": avariyaList.set(i, b.getCount());
					case "События": sobytiyaList.set(i, b.getCount());
					case "Инциденты": incidentList.set(i, b.getCount());
        		}        	
        	}	        
	        i++;
        }
        Map<String, Object> classifierMap = new HashMap<String, Object>();
        classifierMap.put("name", DateUtil.getPreviousBeginYearFromStr(bDate.substring(0, 4)));
    	classifierMap.put("Крушения", krusheniaList);
    	classifierMap.put("Авария", avariyaList);
    	classifierMap.put("События", sobytiyaList);
    	classifierMap.put("Инциденты", incidentList);
    	njsMap.put("last", classifierMap);
        
        /*List<NarushBezop> allNbdLastList = new ArrayList<>();
        for(RbClassifier cl: classifierList) {
        	NarushBezop nb2 = new NarushBezop();
        	nb2.setTitle(cl.getTitle());
        	nb2.setCount(0);
        	allNbdLastList.add(nb2);
        }
        
        for(NarushBezop cl: allNbdLastList) {
        	for(NarushBezop bl:bezopList) {
        		if(cl.getTitle().equals(bl.getTitle())) {
        			cl.setYear(bl.getYear());
        			cl.setCount(bl.getCount());
        		}
        	}
        }*/
                /*
        List<Integer> dLast = new ArrayList<>();
        int lastGeneral = 0;
        ChartsAnswer narushBezopAnswerPrev = new ChartsAnswer();
        for(NarushBezop nb:allNbdLastList) {
        	if(narushBezopAnswerPrev.getName() == null)
        		narushBezopAnswerPrev.setName(DateUtil.getPreviousBeginYearFromStr(eDate.substring(0, 4)));        	
        	dLast.add(nb.getCount());
        	nameList.add(nb.getTitle());
        	lastGeneral += nb.getCount();
        }
        
        dLast.add(lastGeneral);        
        narushBezopAnswerPrev.setData(dLast);
        narushBezopAnswerList.add(narushBezopAnswerPrev);*/        
    	krusheniaList = new ArrayList<>();
    	avariyaList = new ArrayList<>();
		sobytiyaList = new ArrayList<>();
		incidentList = new ArrayList<>();
        for(Region r: regionList) {
        	krusheniaList.add(0);
        	avariyaList.add(0);
			sobytiyaList.add(0);
			incidentList.add(0);
        }
        i = 0;
        
        for(Region r: regionList) {
        	entityManager.clear();
            bezopList = narushBezopService.findNbdAllByStruct(bDate, eDate, r.getCode().toString(), st);	        
            for(NarushBezop b:bezopList) {
        		switch (b.getTitle()) {
					case "Крушения": krusheniaList.set(i, b.getCount());
					case "Авария": avariyaList.set(i, b.getCount());
					case "События": sobytiyaList.set(i, b.getCount());
					case "Инциденты": incidentList.set(i, b.getCount());
        		}        	
        	}
	        i++;
        }
        classifierMap = new HashMap<String, Object>();
        classifierMap.put("name", DateUtil.getCurrentBeginYearFromStr(eDate.substring(0, 4)));
        classifierMap.put("Крушения", krusheniaList);
    	classifierMap.put("Авария", avariyaList);
    	classifierMap.put("События", sobytiyaList);
    	classifierMap.put("Инциденты", incidentList);
        njsMap.put("curr", classifierMap);        
        
        
		/*allNbdLastList.clear();
        for(RbClassifier cl: classifierList) {
        	NarushBezop nb2 = new NarushBezop();
        	nb2.setTitle(cl.getTitle());
        	allNbdLastList.add(nb2);
        }
        
        for(NarushBezop cl: allNbdLastList) {
        	for(NarushBezop bl:bezopList) {
        		if(cl.getTitle().equals(bl.getTitle())) {
        			cl.setYear(bl.getYear());
        			cl.setCount(bl.getCount());
        		}
        	}
        }*/
        /*
        ChartsAnswer narushBezopAnswerCurr = new ChartsAnswer();        
        List<Integer> dCurr = new ArrayList<>();
        int currGeneral = 0;
        
        for(NarushBezop nb:allNbdLastList) {
        	if(narushBezopAnswerCurr.getName() == null)
        		narushBezopAnswerCurr.setName(DateUtil.getCurrentBeginYearFromStr(bDate.substring(0, 4)));        	
        	dCurr.add(nb.getCount());        	
        	currGeneral += nb.getCount();
        }
        
        dCurr.add(currGeneral);
        narushBezopAnswerCurr.setData(dCurr);
        narushBezopAnswerList.add(narushBezopAnswerCurr);
        nameList.add("Общ.НБД");*/
        //ans.put("names", nameList);
        
        //НОДГП
        /*i = 0;
        krusheniaList = new ArrayList<>();
    	avariyaList = new ArrayList<>();
		sobytiyaList = new ArrayList<>();
		incidentList = new ArrayList<>();
        for(Region r: regionList) {
	        entityManager.clear();
	        bezopList = narushBezopService.findNbdAllByStruct(DateUtil.getPreviousBeginYearFromStr(bDate.substring(0, 4)) + bDate.substring(4), 
		        		DateUtil.getPreviousBeginYearFromStr(eDate.substring(0, 4)) + eDate.substring(4), r.getCode().toString(), "1");
        	for(NarushBezop b:bezopList) {
        		switch (b.getTitle()) {
					case "Крушения": krusheniaList.set(i, b.getCount());
					case "Авария": avariyaList.set(i, b.getCount());
					case "События": sobytiyaList.set(i, b.getCount());
					case "Инциденты": incidentList.set(i, b.getCount());
        		}        	
        	}	        
	        i++;
        }
        classifierMap = new HashMap<String, Object>();
    	classifierMap.put("Крушения", krusheniaList);
    	classifierMap.put("Авария", avariyaList);
    	classifierMap.put("События", sobytiyaList);
    	classifierMap.put("Инциденты", incidentList);
        nodgpMap.put(DateUtil.getPreviousBeginYearFromStr(eDate.substring(0, 4)), classifierMap);        
        */
        /*List<NarushBezop> allNbdLastList = new ArrayList<>();
        for(RbClassifier cl: classifierList) {
        	NarushBezop nb2 = new NarushBezop();
        	nb2.setTitle(cl.getTitle());
        	nb2.setCount(0);
        	allNbdLastList.add(nb2);
        }
        
        for(NarushBezop cl: allNbdLastList) {
        	for(NarushBezop bl:bezopList) {
        		if(cl.getTitle().equals(bl.getTitle())) {
        			cl.setYear(bl.getYear());
        			cl.setCount(bl.getCount());
        		}
        	}
        }*/
                /*
        List<Integer> dLast = new ArrayList<>();
        int lastGeneral = 0;
        ChartsAnswer narushBezopAnswerPrev = new ChartsAnswer();
        for(NarushBezop nb:allNbdLastList) {
        	if(narushBezopAnswerPrev.getName() == null)
        		narushBezopAnswerPrev.setName(DateUtil.getPreviousBeginYearFromStr(eDate.substring(0, 4)));        	
        	dLast.add(nb.getCount());
        	nameList.add(nb.getTitle());
        	lastGeneral += nb.getCount();
        }
        
        dLast.add(lastGeneral);        
        narushBezopAnswerPrev.setData(dLast);
        narushBezopAnswerList.add(narushBezopAnswerPrev);*/        
       /* krusheniaList = new ArrayList<>();
    	avariyaList = new ArrayList<>();
		sobytiyaList = new ArrayList<>();
		incidentList = new ArrayList<>();
        for(Region r: regionList) {
        	krusheniaList.add(0);
        	avariyaList.add(0);
			sobytiyaList.add(0);
			incidentList.add(0);
        }
        i = 0;
        
        for(Region r: regionList) {
        	entityManager.clear();
            bezopList = narushBezopService.findNbdAllByStruct(bDate, eDate, r.getCode().toString(), "1");	        
            for(NarushBezop b:bezopList) {
        		switch (b.getTitle()) {
					case "Крушения": krusheniaList.set(i, b.getCount());
					case "Авария": avariyaList.set(i, b.getCount());
					case "События": sobytiyaList.set(i, b.getCount());
					case "Инциденты": incidentList.set(i, b.getCount());
        		}        	
        	}
	        i++;
        }
        classifierMap = new HashMap<String, Object>();
        classifierMap.clear();
        classifierMap.put("Крушения", krusheniaList);
    	classifierMap.put("Авария", avariyaList);
    	classifierMap.put("События", sobytiyaList);
    	classifierMap.put("Инциденты", incidentList);
        nodgpMap.put(DateUtil.getCurrentBeginYearFromStr(bDate.substring(0, 4)), classifierMap);
        */
        ans.put("names", nodNameList);
        ans.put("st", njsMap);
        //ans.put("nodgp", nodgpMap);
        //ans.put("data", narushBezopAnswerList);
        return ans;
    }
	
	
	public Map<String, Object> getRazrezNbd(String beginDate, String endDate){		
		Map<String, Object> ans = new HashMap<String, Object>();
        List<String> nameList = new ArrayList<>();
        entityManager.clear();
        List<NarushBezop> bezopList = narushBezopService.findRazrezNbd(beginDate, endDate);
        List<Integer> allNbdList = new ArrayList<>();
        
    	for(NarushBezop bl:bezopList) {
    		nameList.add(bl.getTitle());
    		allNbdList.add(bl.getCount());
		}
    	        
        ans.put("names", nameList);
        ans.put("data", allNbdList);
        return ans;
    }
}
