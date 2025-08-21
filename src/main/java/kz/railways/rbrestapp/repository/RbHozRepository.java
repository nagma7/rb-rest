package kz.railways.rbrestapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import kz.railways.rbrestapp.entity.RbHoz;

public interface RbHozRepository extends CrudRepository<RbHoz, Integer>{
	
	@Query(value= "SELECT a.id, a.code FROM rb_farm a "
			+ "LEFT JOIN rb_struktura_farm b ON b.farm_id = a.id "
			+ "WHERE a.own = 1 AND b.struktura_id IN (1,2,3,5) ORDER BY b.id ", nativeQuery = true)
	List<RbHoz> findAllList();

	@Query(value= "SELECT a.id, a.code FROM rb_farm a "
			+ "LEFT JOIN rb_struktura_farm b ON b.farm_id = a.id "
			+ "WHERE a.own = :own AND b.struktura_id = :st ORDER BY b.id ", nativeQuery = true)
	List<RbHoz> findAllByStructList(@Param("st") String st, @Param("own") int own);
}