package kz.railways.rbrestapp.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import kz.railways.rbrestapp.entity.MainNBD;
import kz.railways.rbrestapp.entity.NbdAnswer;
import kz.railways.rbrestapp.entity.RbClassifier;
import kz.railways.rbrestapp.entity.RbStruktura;
import kz.railways.rbrestapp.repository.MainNBDRepository;
import kz.railways.rbrestapp.utils.DateUtil;

@Service
public class MainAllService {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	//private MainOtherRepository mainNBDService;
	private MainNBDRepository mainNBDService;	
	

	@Autowired
	private RbStrukturaService rbClassifierService;
	
	public List<NbdAnswer> getMainNbdAllData(String date, String nod){
        List<NbdAnswer> nbdAnswerList = new ArrayList<NbdAnswer>();
        
        List<RbStruktura> rbClassifierList = rbClassifierService.getRbStrukturaList();
        RbStruktura rs = new RbStruktura();
        rs.setId(100);
        rs.setTitle("События на ПП");
        rbClassifierList.add(rs);
        List<MainNBD> ansList = new ArrayList<>(); 
        for(RbStruktura cl: rbClassifierList) {
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
        
        for(MainNBD mnbd: ansList) {
        	NbdAnswer nbdAnswer = new NbdAnswer();
			nbdAnswer.setStrukt(mnbd.getStruktCode());
			nbdAnswer.setCount(mnbd.getCount());
			nbdAnswer.setLastCount(mnbd.getLastCount());
			
			if (mnbd.getCount() == 0 && mnbd.getLastCount() == 0){
	        	nbdAnswer.setTotalCount("0");
	        }else if (mnbd.getCount() == 0 && mnbd.getLastCount() > 0){
	        	nbdAnswer.setTotalCount("-100");
	        }else if (mnbd.getCount() == 1 && mnbd.getLastCount() == 0){
	        	nbdAnswer.setTotalCount("+100");
	        }else if (mnbd.getCount() > 1 && mnbd.getLastCount() == 0){
	        	nbdAnswer.setTotalCount(">100");
	        }else {
	        	double t = (mnbd.getCount() * 100) / mnbd.getLastCount() - 100;
	        	DecimalFormat f = new DecimalFormat("#");
	        	nbdAnswer.setTotalCount(f.format(t));
	        }
	        nbdAnswerList.add(nbdAnswer);
	        
	        if (mnbd.getSId() != 100) {
	        	countNBD += mnbd.getCount();
	        	lastCountNBD += mnbd.getLastCount();
	        }
        }
       /* for (MainNBD last:lastMainNBDList) {
        	for (MainNBD curr:currMainNBDList) {
        		if(last.getSId() == curr.getSId()) {
		        	if(curr.getCount() == null)
						curr.setCount(0);
					/*if(curr.getLastCount() == null)
						curr.setLastCount(0);*/
					/*NbdAnswer nbdAnswer = new NbdAnswer();
					nbdAnswer.setStrukt(curr.getStruktCode());
					nbdAnswer.setCount(curr.getCount());
					nbdAnswer.setLastCount(last.getCount());
					if (curr.getCount() == 0 && last.getCount() == 0){
			        	nbdAnswer.setTotalCount("0");
			        }else if (curr.getCount() == 0 && last.getCount() > 0){
			        	nbdAnswer.setTotalCount("-100");
			        }else if (curr.getCount() == 1 && last.getCount() == 0){
			        	nbdAnswer.setTotalCount("+100");
			        }else if (curr.getCount() > 1 && last.getCount() == 0){
			        	nbdAnswer.setTotalCount(">100");
			        }else {
			        	double t = (curr.getCount() * 100) / last.getCount() - 100;
			        	DecimalFormat f = new DecimalFormat("#");
			        	nbdAnswer.setTotalCount(f.format(t));
			        }
			        nbdAnswerList.add(nbdAnswer);
			        
			        if (curr.getSId() != 100) {
			        	countNBD += curr.getCount();
			        	lastCountNBD += last.getCount();
			        }
        		}
            }
        }*/

        NbdAnswer nbdAll = new NbdAnswer();
		nbdAll.setStrukt("НБД");
		nbdAll.setCount(countNBD);
		nbdAll.setLastCount(lastCountNBD);

        if (countNBD == 0 && lastCountNBD == 0){
        	nbdAll.setTotalCount("0");
        }else if (countNBD == 0 && lastCountNBD > 0){
        	nbdAll.setTotalCount("-100");
        }else if (countNBD == 1 && lastCountNBD == 0){
        	nbdAll.setTotalCount("+100");
        }else if (countNBD > 1 && lastCountNBD == 0){
        	nbdAll.setTotalCount(">100");
        }else {
        	double t = (countNBD * 100) / lastCountNBD - 100;
        	DecimalFormat f = new DecimalFormat("#");
        	nbdAll.setTotalCount(f.format(t));
        }
        nbdAnswerList.add(nbdAll);
        return nbdAnswerList;
    }
}
