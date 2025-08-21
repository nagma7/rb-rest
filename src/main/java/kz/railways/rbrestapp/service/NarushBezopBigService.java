package kz.railways.rbrestapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import kz.railways.rbrestapp.entity.NarushBezopBig;
import kz.railways.rbrestapp.repository.NarushBezopBigRepository;
import kz.railways.rbrestapp.utils.DateUtil;

@Service
public class NarushBezopBigService {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private NarushBezopBigRepository narushBezopBigService;
	//private NarushBezopBigRepository2 narushBezopBigService;
	
	public List<NarushBezopBig> getRbNarushBezopBig1(Map<String, String> personJsonObject){
        
        List<NarushBezopBig> allNarushBezopList = new ArrayList<NarushBezopBig>();
        
        entityManager.clear();
        NarushBezopBig bezopBig = new NarushBezopBig();
        bezopBig.setDate(DateUtil.getPreviousBeginYearFromStr(personJsonObject.get("date").substring(0, 4)));
        bezopBig.setNarushList(narushBezopBigService.findAll(DateUtil.getPreviousBeginDateFromStr(personJsonObject.get("date").substring(0, 4)), 
        		DateUtil.getPreviousBeginYearFromStr(personJsonObject.get("date").substring(0, 4)) + personJsonObject.get("date").substring(4), personJsonObject.get("nod")));
        allNarushBezopList.add(bezopBig);
        
        entityManager.clear();
        NarushBezopBig bezopBig1 = new NarushBezopBig();
        bezopBig1.setDate(DateUtil.getCurrentBeginYearFromStr(personJsonObject.get("date").substring(0, 4)));
        bezopBig1.setNarushList(narushBezopBigService.findAll(DateUtil.getBeginDate(personJsonObject.get("date").substring(0, 4)), personJsonObject.get("date"), personJsonObject.get("nod")));
        allNarushBezopList.add(bezopBig1);
        
        return allNarushBezopList;
    }
	
	public List<NarushBezopBig> getRbNarushBezopBig(String date, String nod, int cl, int farm, String m, String st, int hoz){
        
        List<NarushBezopBig> allNarushBezopList = new ArrayList<NarushBezopBig>();
        if (cl == 0 && farm == 0 && m.equals("") && hoz == 0) {
	        entityManager.clear();
	        NarushBezopBig bezopBig = new NarushBezopBig();
	        bezopBig.setDate(DateUtil.getPreviousBeginYearFromStr(date.substring(0, 4)));
	        bezopBig.setNarushList(narushBezopBigService.findAll(DateUtil.getPreviousBeginDateFromStr(date.substring(0, 4)), 
	        		DateUtil.getPreviousBeginYearFromStr(date.substring(0, 4)) + date.substring(4), nod));
	        allNarushBezopList.add(bezopBig);
	        
	        entityManager.clear();
	        NarushBezopBig bezopBig1 = new NarushBezopBig();
	        bezopBig1.setDate(DateUtil.getCurrentBeginYearFromStr(date.substring(0, 4)));
	        bezopBig1.setNarushList(narushBezopBigService.findAll(DateUtil.getBeginDate(date.substring(0, 4)), date, nod));
	        allNarushBezopList.add(bezopBig1);
        }else if(cl > 0 && farm == 0 && m.equals("") && hoz == 0){ // classifiers
        	entityManager.clear();
	        NarushBezopBig bezopBig = new NarushBezopBig();
	        bezopBig.setDate(DateUtil.getPreviousBeginYearFromStr(date.substring(0, 4)));
	        switch (st) {
				case "99" -> bezopBig.setNarushList(narushBezopBigService.findByCl(DateUtil.getPreviousBeginDateFromStr(date.substring(0, 4)), 
	        		DateUtil.getPreviousBeginYearFromStr(date.substring(0, 4)) + date.substring(4), nod, cl));
				case "1","2","3","5" -> bezopBig.setNarushList(narushBezopBigService.findByClAndStruct(DateUtil.getPreviousBeginDateFromStr(date.substring(0, 4)), 
		        		DateUtil.getPreviousBeginYearFromStr(date.substring(0, 4)) + date.substring(4), nod, cl, st));
	        }
	        allNarushBezopList.add(bezopBig);
	        
	        entityManager.clear();
	        NarushBezopBig bezopBig1 = new NarushBezopBig();
	        bezopBig1.setDate(DateUtil.getCurrentBeginYearFromStr(date.substring(0, 4)));
	        switch (st) {
				case "99" -> bezopBig1.setNarushList(narushBezopBigService.findByCl(DateUtil.getBeginDate(date.substring(0, 4)), date, nod, cl));
				case "1","2","3","5" -> bezopBig1.setNarushList(narushBezopBigService.findByClAndStruct(DateUtil.getBeginDate(date.substring(0, 4)), date, nod, cl, st));
	        }
	        allNarushBezopList.add(bezopBig1);
        }else if(cl == 0 && farm > 0 && m.equals("") && hoz == 0){ 
        	entityManager.clear();
	        NarushBezopBig bezopBig = new NarushBezopBig();
	        bezopBig.setDate(DateUtil.getPreviousBeginYearFromStr(date.substring(0, 4)));
	        bezopBig.setNarushList(narushBezopBigService.findByFarm(DateUtil.getPreviousBeginDateFromStr(date.substring(0, 4)), 
	        		DateUtil.getPreviousBeginYearFromStr(date.substring(0, 4)) + date.substring(4), nod, farm));
	        allNarushBezopList.add(bezopBig);
	        
	        entityManager.clear();
	        NarushBezopBig bezopBig1 = new NarushBezopBig();
	        bezopBig1.setDate(DateUtil.getCurrentBeginYearFromStr(date.substring(0, 4)));
	        bezopBig1.setNarushList(narushBezopBigService.findByFarm(DateUtil.getBeginDate(date.substring(0, 4)), date, nod, farm));
	        allNarushBezopList.add(bezopBig1);
        }else if (cl == 0 && farm == 0 && !m.equals("") && hoz == 0) {
        	entityManager.clear();
	        NarushBezopBig bezopBig = new NarushBezopBig();
	        bezopBig.setDate(DateUtil.getPreviousBeginYearFromStr(date.substring(0, 4)));
	        switch (st) {
				case "99" -> bezopBig.setNarushList(narushBezopBigService.findByMonth(DateUtil.getPreviousBeginDateFromStr(date.substring(0, 4)), 
	        					DateUtil.getPreviousBeginYearFromStr(date.substring(0, 4)) + date.substring(4), nod, m));
				case "1","2","3","5" -> bezopBig.setNarushList(narushBezopBigService.findByMonthAndStruct(DateUtil.getPreviousBeginDateFromStr(date.substring(0, 4)), 
    					DateUtil.getPreviousBeginYearFromStr(date.substring(0, 4)) + date.substring(4), nod, m, st));
	        }
	        allNarushBezopList.add(bezopBig);
	        
	        entityManager.clear();
	        NarushBezopBig bezopBig1 = new NarushBezopBig();
	        bezopBig1.setDate(DateUtil.getCurrentBeginYearFromStr(date.substring(0, 4)));
	        switch (st) {
				case "99" -> bezopBig1.setNarushList(narushBezopBigService.findByMonth(DateUtil.getBeginDate(date.substring(0, 4)), date, nod, m));
				case "1","2","3","5" -> bezopBig1.setNarushList(narushBezopBigService.findByMonthAndStruct(DateUtil.getBeginDate(date.substring(0, 4)), date, nod, m, st));
	        }
	        bezopBig1.setNarushList(narushBezopBigService.findByMonth(DateUtil.getBeginDate(date.substring(0, 4)), date, nod, m));
	        allNarushBezopList.add(bezopBig1);
        }else if (cl == 0 && farm == 0 && m.equals("") && hoz > 0) {
        	entityManager.clear();
	        NarushBezopBig bezopBig = new NarushBezopBig();
	        bezopBig.setDate(DateUtil.getPreviousBeginYearFromStr(date.substring(0, 4)));
	        bezopBig.setNarushList(narushBezopBigService.findByHoz(DateUtil.getPreviousBeginDateFromStr(date.substring(0, 4)), 
	        					DateUtil.getPreviousBeginYearFromStr(date.substring(0, 4)) + date.substring(4), nod, hoz));
			allNarushBezopList.add(bezopBig);
	        
	        entityManager.clear();
	        NarushBezopBig bezopBig1 = new NarushBezopBig();
	        bezopBig1.setDate(DateUtil.getCurrentBeginYearFromStr(date.substring(0, 4)));
	        bezopBig1.setNarushList(narushBezopBigService.findByHoz(DateUtil.getBeginDate(date.substring(0, 4)), date, nod, hoz));
	        
	        allNarushBezopList.add(bezopBig1);
        }
        
        return allNarushBezopList;
    }
}

