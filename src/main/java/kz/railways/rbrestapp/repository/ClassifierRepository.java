package kz.railways.rbrestapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import kz.railways.rbrestapp.entity.RbClassifier;

public interface ClassifierRepository extends CrudRepository<RbClassifier, Integer>{
	
	@Query(value= " SELECT id, title FROM rb_classifiers WHERE id IN (1,2,3,4) ORDER BY id ", nativeQuery = true)
	List<RbClassifier> findAllList();
	
	@Query(value= " SELECT id, title FROM rb_classifiers WHERE id IN (1,2,3,4,5) ORDER BY id ", nativeQuery = true)
	List<RbClassifier> findList();
}
