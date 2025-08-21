package kz.railways.rbrestapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import kz.railways.rbrestapp.entity.VnutrControl;
import kz.railways.rbrestapp.entity.VnutrControlAnswer;
import kz.railways.rbrestapp.repository.VnutrControlRepository;
import kz.railways.rbrestapp.utils.DateUtil;

@Service
public class VnutrControlService {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private VnutrControlRepository vnutrControlService;
	
	public List<VnutrControlAnswer> getVnutrControl(String date, String nod, String st) {
		List<VnutrControlAnswer> ans = new ArrayList<>();
		List<VnutrControl> countProverki = null;
		entityManager.clear();
		if (st.equals("99"))
			countProverki = vnutrControlService.findCountProverki(DateUtil.getBeginDate(date.substring(0, 4)), date, nod);
		else
			countProverki = vnutrControlService.findCountProverkiByStruct(DateUtil.getBeginDate(date.substring(0, 4)), date, nod, st);
		VnutrControlAnswer countProverkiAns = new VnutrControlAnswer();
		countProverkiAns.setTitle("Проверки");
		countProverkiAns.setVnutrControlCount(countProverki.size());
		ans.add(countProverkiAns);
		VnutrControl countNarush = null;
		entityManager.clear();
		if (st.equals("99"))
			countNarush = vnutrControlService.findCountNarush(DateUtil.getBeginDate(date.substring(0, 4)), date, nod);
		else
			countNarush = vnutrControlService.findCountNarushByStruct(DateUtil.getBeginDate(date.substring(0, 4)), date, nod, st);
		VnutrControlAnswer countNarushAns = new VnutrControlAnswer();
		countNarushAns.setTitle("Выявлено нарушении");
		countNarushAns.setVnutrControlCount(countNarush.getCount());
		ans.add(countNarushAns);
		VnutrControl countZapMer = null;
		entityManager.clear();
		if (st.equals("99"))
			countZapMer = vnutrControlService.findCountZapMer(DateUtil.getBeginDate(date.substring(0, 4)), date, nod);
		else
			countZapMer = vnutrControlService.findCountZapMerByStruct(DateUtil.getBeginDate(date.substring(0, 4)), date, nod, st);
		VnutrControlAnswer countZapMerAns = new VnutrControlAnswer();
		countZapMerAns.setTitle("Принято запретных мер");
		countZapMerAns.setVnutrControlCount(countZapMer.getCount());
		ans.add(countZapMerAns);
		 
		return ans;
	}
	
}