package kz.railways.rbrestapp.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import kz.railways.rbrestapp.bussiness.MainPageRbBlockManager;
import kz.railways.rbrestapp.entity.MainPageRbBlock;


@RestController
public class ICenterController {

	@Autowired
	MainPageRbBlockManager blockManager;
	
	@PostMapping("ic/getmainpageblocklist")	
	public ResponseEntity<List<MainPageRbBlock>> getMainPageBlocklist(String date, String nod) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return ResponseEntity.ok(blockManager.getMainPageRbBlocks(sdf.parse(date), nod));
	}
}
