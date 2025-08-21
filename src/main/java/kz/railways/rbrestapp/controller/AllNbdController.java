package kz.railways.rbrestapp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import kz.railways.rbrestapp.service.MainNbdService;
import kz.railways.rbrestapp.service.NarushBezopService;
import kz.railways.rbrestapp.service.RbHozAnswerService;

@RestController
public class AllNbdController {

	@Autowired
	private NarushBezopService narushBezopService;
	@Autowired
	private MainNbdService mainNbdService;
	@Autowired
	private RbHozAnswerService rbHozAnswerService;
	
	@PostMapping(path= "nbd/narushbezopallnbd")
    public ResponseEntity<Map<String, Object>> getRbNarushBezopAllNbd(String beginDate, String endDate){
		return ResponseEntity.ok(narushBezopService.getNarushBezopWithoutNod(beginDate, endDate));
    }
	
	@PostMapping(path= "nbd/cpido")
	public ResponseEntity<Map<String, Object>> getCpdoData(String beginDate, String endDate){
		//return ResponseEntity.ok(mainNbdService.getRazrezStruct(date, nod));
		return ResponseEntity.ok(mainNbdService.getCpDOData(beginDate, endDate));
    }
	
	@PostMapping(path= "nbd/nbdrazrezhoz")
	public ResponseEntity<Map<String, Object>> getNbdRazrezHoz(String beginDate, String endDate, String st, int own){
		return ResponseEntity.ok(rbHozAnswerService.getNBDRbHozList(beginDate, endDate, st, own));
    }
	
	@PostMapping(path= "nbd/njsnodgpchartdata")
    public ResponseEntity<Map<String, Object>> getNbdWithNodByStruct(String beginDate, String endDate, String st){
		return ResponseEntity.ok(narushBezopService.getNjsNodgpData(beginDate, endDate, st));
    }
	
	@PostMapping(path= "nbd/compnother")
    public ResponseEntity<Map<String, Object>> getCompNProch(String beginDate, String endDate, int own){
		return ResponseEntity.ok(narushBezopService.getCompNOther(beginDate, endDate, own));
    }
	@PostMapping(path= "nbd/nbdrazrez")
    public ResponseEntity<Map<String, Object>> getNbdRazrez(String beginDate, String endDate){
		return ResponseEntity.ok(narushBezopService.getRazrezNbd(beginDate, endDate));
    }
}
