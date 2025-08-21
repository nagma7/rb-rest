package kz.railways.rbrestapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import kz.railways.rbrestapp.entity.VyyavNarush;
import kz.railways.rbrestapp.entity.VyyavNarushData;
import kz.railways.rbrestapp.entity.VyyavNarushStrukt;
import kz.railways.rbrestapp.repository.VyyavNarushRepository;
import kz.railways.rbrestapp.repository.VyyavNarushStructRepository;
import kz.railways.rbrestapp.utils.DateUtil;

@Service
public class VyyavNarushService {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private VyyavNarushRepository vyyavNarushRepo;
	@Autowired
	private VyyavNarushStructRepository vyyavNarushStructRepository;
	public VyyavNarushData getVyyavNarush(String date, String nod, String struct) {
		VyyavNarushData ans = new VyyavNarushData();
		List<VyyavNarush> vyyavNarushList = null;
		entityManager.clear();
		if(struct.equals("99"))
			vyyavNarushList = vyyavNarushRepo.findVyyavNarushData(DateUtil.getBeginDate(date.substring(0, 4)), date, nod);
		else
			vyyavNarushList = vyyavNarushRepo.findVyyavNarushDataByStruct(DateUtil.getBeginDate(date.substring(0, 4)), date, nod, struct);
		ans.setVyyavNarush(vyyavNarushList);
		List<VyyavNarushStrukt> vyyavNarushStruktList =null;
		entityManager.clear();
		if(struct.equals("99"))
			vyyavNarushStruktList = vyyavNarushStructRepository.findVyyavNarushData(DateUtil.getBeginDate(date.substring(0, 4)), date, nod);
		else
			vyyavNarushStruktList = vyyavNarushStructRepository.findVyyavNarushDataByStruct(DateUtil.getBeginDate(date.substring(0, 4)), date, nod, struct);
		ans.setVyyavNarushStruktList(vyyavNarushStruktList);
		return ans;
	}
}
