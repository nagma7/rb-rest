package kz.railways.rbrestapp.bussiness;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kz.railways.rbrestapp.entity.MainPageRbBlock;
import kz.railways.rbrestapp.entity.NbdAnswer;
import kz.railways.rbrestapp.service.MainAllService;
import lombok.Getter;

@Service
public class MainPageRbBlockManager {	
	@Autowired
	private MainAllService mainAllService;
	@Getter
	private List<NbdAnswer> nbdAllOther;
    
	public List<MainPageRbBlock> getMainPageRbBlocks(Date ydate, String nod){
		//List<MainPageRbBlock> blockList = mainPageRbBlockService.getMainPageRbBlockList();
		List<MainPageRbBlock> blockList = new ArrayList<>();
		MainPageRbBlock rbBlock1 = new MainPageRbBlock();
		rbBlock1.setId(99);
		rbBlock1.setTitle("НБД");
		blockList.add(rbBlock1);
		
		MainPageRbBlock rbBlock2 = new MainPageRbBlock();
		rbBlock2.setId(1);
		rbBlock2.setTitle("ГП");
		blockList.add(rbBlock2);
		
		MainPageRbBlock rbBlock3 = new MainPageRbBlock();
		rbBlock3.setId(2);
		rbBlock3.setTitle("ЦЖС");
		blockList.add(rbBlock3);
		
		MainPageRbBlock rbBlock4 = new MainPageRbBlock();
		rbBlock4.setId(3);
		rbBlock4.setTitle("ЦЛ");
		blockList.add(rbBlock4);
		
		MainPageRbBlock rbBlock5 = new MainPageRbBlock();
		rbBlock5.setId(5);
		rbBlock5.setTitle("ДЗО");
		blockList.add(rbBlock5);
		
		MainPageRbBlock rbBlock6 = new MainPageRbBlock();
		rbBlock6.setId(100);
		rbBlock6.setTitle("События на ПП");
		blockList.add(rbBlock6);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		this.nbdAllOther = mainAllService.getMainNbdAllData(format.format(ydate), nod);
		for(MainPageRbBlock block:blockList) {
			for(NbdAnswer nbd:nbdAllOther) {				
				if (block.getTitle().equals(nbd.getStrukt())) {
					//block.setNdb(null);
					block.setNdb(nbd);					
					if (nbd.getCount() < nbd.getLastCount()) { 
						block.setStyle("background-color: #6ebe71;color: #fdfdfd;");
						Map<String, String> style = new HashMap<>();
						style.put("background", "#6ebe71");
						style.put("color", "#fdfdfd");
						block.setStyleNg(style);
					}
					else if (nbd.getCount() > nbd.getLastCount()) { 
						block.setStyle("background-color: #f46161;color: #fdfdfd;");
						Map<String, String> style = new HashMap<>();
						style.put("background", "#f46161");
						style.put("color", "#fdfdfd");
						block.setStyleNg(style);
					}
					else if (nbd.getCount() == nbd.getLastCount()) { 
						block.setStyle("background-color: #ffd965;color: #fdfdfd;");
						Map<String, String> style = new HashMap<>();
						style.put("background", "#ffd965");
						style.put("color", "#fdfdfd");
						block.setStyleNg(style);
					}
					if (nbd.getCount() == 0 && nbd.getLastCount() == 0)
						if (nbd.getCount() == nbd.getLastCount()) {
							block.setStyle("background-color: #6ebe71;color: #fdfdfd;");
							Map<String, String> style = new HashMap<>();
							style.put("background", "#6ebe71");
							style.put("color", "#fdfdfd");
							block.setStyleNg(style);
						}
				}
			}
		}
		return blockList;
	}
}