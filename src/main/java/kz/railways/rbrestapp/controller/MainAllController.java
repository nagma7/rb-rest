package kz.railways.rbrestapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import kz.railways.rbrestapp.entity.MonthDataAnswer;
import kz.railways.rbrestapp.entity.NarushBezopAnswer;
import kz.railways.rbrestapp.entity.NarushBezopBig;
import kz.railways.rbrestapp.entity.NbdAnswer;
import kz.railways.rbrestapp.entity.SobytiyaNaPP;
import kz.railways.rbrestapp.entity.VnutrControlAnswer;
import kz.railways.rbrestapp.entity.VyyavNarushData;
import kz.railways.rbrestapp.entity.ZapMeryData;
import kz.railways.rbrestapp.service.MainAllService;
import kz.railways.rbrestapp.service.MainNbdService;
import kz.railways.rbrestapp.service.MonthDataService;
import kz.railways.rbrestapp.service.NarushBezopBigService;
import kz.railways.rbrestapp.service.NarushBezopService;
import kz.railways.rbrestapp.service.RbHozAnswerService;
import kz.railways.rbrestapp.service.SobytiyaNaPPService;
import kz.railways.rbrestapp.service.VnutrControlService;
import kz.railways.rbrestapp.service.VyyavNarushService;
import kz.railways.rbrestapp.service.ZapMeryService;
import lombok.AllArgsConstructor;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
public class MainAllController {
	
	@Autowired
	private MainAllService mainAllService;
	@Autowired
	private VnutrControlService vnutrControlService;
	@Autowired
	private SobytiyaNaPPService service;
	@Autowired
	private NarushBezopService narushBezopService;
	@Autowired
	private NarushBezopBigService narushBezopBigService;
	@Autowired
	private MonthDataService dataService;
	@Autowired
	private VyyavNarushService vyyavNarushService;	
	@Autowired
	private ZapMeryService zapMeryService;
	@Autowired
	private MainNbdService mainNbdService;
	@Autowired
	private RbHozAnswerService rbHozAnswerService;
	
	@PostMapping(path= "api/mainall")
	public ResponseEntity<List<NbdAnswer>> getMainNbdAll(String date, String nod){
		return ResponseEntity.ok(mainAllService.getMainNbdAllData(date, nod));
    }
	
	@PostMapping(path= "api/mainnbd")
	public ResponseEntity<List<NbdAnswer>> getMainNbd(String date, String nod){
		return ResponseEntity.ok(mainNbdService.getMainNbdAllData(date, nod));
    }
	
	@PostMapping(path= "api/razrezstruct")
	public ResponseEntity<Map<String, Object>> getRazrezStruct(String date, String nod){
		//return ResponseEntity.ok(mainNbdService.getRazrezStruct(date, nod));
		return ResponseEntity.ok(mainNbdService.getRazrezStruct1(date, nod));
    }
	
	@PostMapping(path= "api/razrezhoz")
	public ResponseEntity<Map<String, Object>> getRazrezHoz(String date, String nod, String st){
		return ResponseEntity.ok(rbHozAnswerService.getRbHozList(date, nod, st));
    }
		
	@PostMapping(path= "api/vnutrcontrol")
	public ResponseEntity<List<VnutrControlAnswer>> getVnutrControl(String date, String nod, String st){
		return ResponseEntity.ok(vnutrControlService.getVnutrControl(date, nod, st));
    }
			
	@PostMapping(path= "api/sobytiyanapp")
    public ResponseEntity<List<SobytiyaNaPP>> getSobytiyaNaPPData(String date, String nod){
		return ResponseEntity.ok(service.getSobytiyaNaPPData(date, nod));
    }
	
	@PostMapping(path= "api/narushbezop")
    public ResponseEntity<List<NarushBezopAnswer>> getRbNarushBezop(String date, String nod){
		return ResponseEntity.ok(narushBezopService.getNarushBezopAnswer(date, nod));
    }
	
	@PostMapping(path= "api/narushbezop1")
    public ResponseEntity<Map<String, Object>> getRbNarushBezop1(String date, String nod, String st){
		return ResponseEntity.ok(narushBezopService.getNarushBezopAnswer12(date, nod, st));
    }
	
	@PostMapping(path= "api/narushbezop2")
    public ResponseEntity<Map<String, Object>> getRbNarushBezop12(String date, String nod, String st){
		return ResponseEntity.ok(narushBezopService.getNarushBezopAnswer123(date, nod, st));
    }
	
	@PostMapping(path= "api/narushbezopbig")
    public ResponseEntity<List<NarushBezopBig>> getRbNarushBezopBigLast1(String date, String nod, int cl, int farm, String m, String st, int hoz){
        return ResponseEntity.ok(narushBezopBigService.getRbNarushBezopBig(date, nod, cl, farm, m, st, hoz));
    }
	
	@PostMapping(path= "api/monthdata")
    public ResponseEntity<List<MonthDataAnswer>> getMonthData(String date, String nod){
		return ResponseEntity.ok(dataService.getMonthAllData(date, nod));
    }
	
	@PostMapping(path= "api/monthdata1")
    public ResponseEntity<Map<String, Object>> getMonthData1(String date, String nod, String st){
		return ResponseEntity.ok(dataService.getMonthAllData1(date, nod, st));
    }
	
	@PostMapping(path= "api/vyyavnarush")
    public ResponseEntity<VyyavNarushData> getVyyavNarushData(String date, String nod, String st){
		return ResponseEntity.ok(vyyavNarushService.getVyyavNarush(date, nod, st));
    }
	
	@PostMapping(path= "api/zapmery")
    public ResponseEntity<ZapMeryData> getZapMeryData(String date, String nod, String st){
		return ResponseEntity.ok(zapMeryService.getZapMery(date, nod, st));
    }
	
	//nbd

	@PostMapping(path= "api/narushbezopallnbd")
    public ResponseEntity<Map<String, Object>> getRbNarushBezopAllNbd(String beginDate, String endDate){
		return ResponseEntity.ok(narushBezopService.getNarushBezopWithoutNod(beginDate, endDate));
    }
	
	@PostMapping(path= "api/cpido")
	public ResponseEntity<Map<String, Object>> getCpdoData(String beginDate, String endDate){
		//return ResponseEntity.ok(mainNbdService.getRazrezStruct(date, nod));
		return ResponseEntity.ok(mainNbdService.getCpDOData(beginDate, endDate));
    }
	
	@PostMapping(path= "api/nbdrazrezhoz")
	public ResponseEntity<Map<String, Object>> getNbdRazrezHoz(String beginDate, String endDate, String st, int own){
		return ResponseEntity.ok(rbHozAnswerService.getNBDRbHozList(beginDate, endDate, st, own));
    }
	
	@PostMapping(path= "api/njsnodgpchartdata")
    public ResponseEntity<Map<String, Object>> getNbdWithNodByStruct(String beginDate, String endDate, String st){
		return ResponseEntity.ok(narushBezopService.getNjsNodgpData(beginDate, endDate, st));
    }
	
	@PostMapping(path= "api/compnother")
    public ResponseEntity<Map<String, Object>> getCompNProch(String beginDate, String endDate, int own){
		return ResponseEntity.ok(narushBezopService.getCompNOther(beginDate, endDate, own));
    }
	@PostMapping(path= "api/nbdrazrez")
    public ResponseEntity<Map<String, Object>> getNbdRazrez(String beginDate, String endDate){
		return ResponseEntity.ok(narushBezopService.getRazrezNbd(beginDate, endDate));
    }
}