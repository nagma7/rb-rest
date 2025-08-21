package kz.railways.rbrestapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kz.railways.rbrestapp.entity.RbHoz;
import kz.railways.rbrestapp.repository.RbHozRepository;

@Service
public class RbHozService {

	@Autowired
	private RbHozRepository rbHozRepository;
	
	public List<RbHoz> getRbHozList(){
		return rbHozRepository.findAllList(); 
	}
	
	public List<RbHoz> getRbHozByStructList(String st, int own){
		return rbHozRepository.findAllByStructList(st, own); 
	}
}
