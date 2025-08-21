package kz.railways.rbrestapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kz.railways.rbrestapp.entity.RbClassifier;
import kz.railways.rbrestapp.repository.ClassifierRepository;

@Service
public class RbClassifierService {
	
	@Autowired
	private ClassifierRepository classifierRepository;
	
	public List<RbClassifier> getRbClassifierList(){
		return classifierRepository.findAllList(); 
	}
	
	public List<RbClassifier> getRbAllClassifierList(){
		return classifierRepository.findList(); 
	}
}
