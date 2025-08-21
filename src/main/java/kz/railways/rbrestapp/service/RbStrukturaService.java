package kz.railways.rbrestapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kz.railways.rbrestapp.entity.RbStruktura;
import kz.railways.rbrestapp.repository.RbStrukturaRepository;

@Service
public class RbStrukturaService {

	@Autowired
	private RbStrukturaRepository rbStrukturaRepository;
	
	public List<RbStruktura> getRbStrukturaList(){
		return rbStrukturaRepository.findAllList(); 
	}
	
	public List<RbStruktura> getCPDOStrukturaList(){
		return rbStrukturaRepository.findCPDOList(); 
	}
}
