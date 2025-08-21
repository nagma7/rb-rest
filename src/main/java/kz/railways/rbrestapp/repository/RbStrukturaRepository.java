package kz.railways.rbrestapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import kz.railways.rbrestapp.entity.RbStruktura;

public interface RbStrukturaRepository extends CrudRepository<RbStruktura, Integer>{
	
	@Query(value= " SELECT id, kod FROM rb_struktura WHERE id IN (1,2,3,5) ORDER BY id ", nativeQuery = true)
	List<RbStruktura> findAllList();
	
	@Query(value= "SELECT id, kod FROM rb_struktura WHERE code in (1,0) and deleted_at is null order by id", nativeQuery = true)
	List<RbStruktura> findCPDOList();
}