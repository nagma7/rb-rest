package kz.railways.rbrestapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import kz.railways.rbrestapp.entity.Region;

public interface RegionRepository extends CrudRepository<Region, Integer>{
	@Query(value= "SELECT id, title FROM rb_dn WHERE id not in (5,12,15) order by id", nativeQuery = true)
	List<Region> findNodList();
}
