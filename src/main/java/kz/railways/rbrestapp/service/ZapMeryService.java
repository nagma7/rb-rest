package kz.railways.rbrestapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import kz.railways.rbrestapp.entity.ZapMery;
import kz.railways.rbrestapp.entity.ZapMeryData;
import kz.railways.rbrestapp.entity.ZapMeryStrukt;
import kz.railways.rbrestapp.repository.ZapMeryRepository;
import kz.railways.rbrestapp.repository.ZapMeryStructRepository;
import kz.railways.rbrestapp.utils.DateUtil;

@Service
public class ZapMeryService {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private ZapMeryRepository zapMeryRepo;
	@Autowired
	private ZapMeryStructRepository zapMeryStructRepository;
	
	public ZapMeryData getZapMery(String date, String nod, String st) {
		ZapMeryData ans = new ZapMeryData();
		List<ZapMery> zapMeryList = null;
		entityManager.clear();
		if(st.equals("99"))
			zapMeryList = zapMeryRepo.findZapMeryData(DateUtil.getBeginDate(date.substring(0, 4)), date, nod);
		else
			zapMeryList = zapMeryRepo.findZapMeryByStructData(DateUtil.getBeginDate(date.substring(0, 4)), date, nod, st);
		ans.setZapMery(zapMeryList);
		List<ZapMeryStrukt> zapMeryStruktList = null;
		entityManager.clear();
		if(st.equals("99"))
			zapMeryStruktList = zapMeryStructRepository.findZapMeryStruktData(DateUtil.getBeginDate(date.substring(0, 4)), date, nod);
		else
			zapMeryStruktList = zapMeryStructRepository.findZapMeryStruktByStructData(DateUtil.getBeginDate(date.substring(0, 4)), date, nod, st);
		ans.setZapMeryStruktList(zapMeryStruktList);
		return ans;
	}
}
