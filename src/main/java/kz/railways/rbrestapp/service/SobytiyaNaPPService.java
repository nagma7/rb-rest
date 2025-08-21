package kz.railways.rbrestapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import kz.railways.rbrestapp.entity.SobytiyaNaPP;
import kz.railways.rbrestapp.repository.SobytiyaNaPPRepository;
import kz.railways.rbrestapp.utils.DateUtil;

@Service
public class SobytiyaNaPPService {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	//private NarushBezopBigRepository narushBezopBigService;
	private SobytiyaNaPPRepository sobytiyaNaPPRepository;
	
	public List<SobytiyaNaPP> getSobytiyaNaPPData(String date, String nod){
        entityManager.clear();
        List<SobytiyaNaPP> allSobytiyaNaPPData = new ArrayList<SobytiyaNaPP>();
        SobytiyaNaPP sobytiyaNaPP = new SobytiyaNaPP();
        sobytiyaNaPP.setDate(DateUtil.getPreviousBeginYearFromStr(date.substring(0, 4)));
        sobytiyaNaPP.setSobytiyaNaPPDataList(sobytiyaNaPPRepository.findAll(DateUtil.getPreviousBeginDateFromStr(date.substring(0, 4)), 
        		DateUtil.getPreviousBeginYearFromStr(date.substring(0, 4)) + date.substring(4), nod));
        allSobytiyaNaPPData.add(sobytiyaNaPP);
        
        entityManager.clear();
        SobytiyaNaPP sobytiyaNaPP1 = new SobytiyaNaPP();
        sobytiyaNaPP1.setDate(DateUtil.getCurrentBeginYearFromStr(date.substring(0, 4)));
        sobytiyaNaPP1.setSobytiyaNaPPDataList(sobytiyaNaPPRepository.findAll(DateUtil.getBeginDate(date.substring(0, 4)), date, nod));
        allSobytiyaNaPPData.add(sobytiyaNaPP1);
        return allSobytiyaNaPPData;
    }
}

