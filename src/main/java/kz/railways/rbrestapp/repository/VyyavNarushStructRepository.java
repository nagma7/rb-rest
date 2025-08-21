package kz.railways.rbrestapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import kz.railways.rbrestapp.entity.VyyavNarushStrukt;

public interface VyyavNarushStructRepository extends CrudRepository<VyyavNarushStrukt, String> {
	@Query(value="select s.kod,count(*) as count_ from rb_acts d "			
			+ "		right join rb_act_violations on rb_act_violations.acts_id=d.id "
			+ "		left join rb_struktura_farm sf on sf.farm_id=rb_act_violations.farm_id "
			+ "		left join rb_farm f on f.id=rb_act_violations.farm_id "
			+ "		left join rb_struktura s on s.id=sf.struktura_id "
			+ "		where d.date between :b_date and :e_date    and f.own=1  and sf.struktura_id is not null and d.dn_id = :nod "
			+ "		group by 1", nativeQuery = true)
	List<VyyavNarushStrukt> findVyyavNarushData(@Param("b_date") String b_date, @Param("e_date") String e_date, @Param("nod") String nod);
	
	@Query(value="select f.code as kod,count(*) as count_ from rb_acts d "			
			+ "		right join rb_act_violations on rb_act_violations.acts_id=d.id "
			+ "		left join rb_struktura_farm sf on sf.farm_id=rb_act_violations.farm_id "
			+ "		left join rb_farm f on f.id=rb_act_violations.farm_id "
			+ "		where d.date between :b_date and :e_date    and f.own=1  and sf.struktura_id is not null and d.dn_id = :nod AND sf.struktura_id = :st "
			+ "		group by 1", nativeQuery = true)
	List<VyyavNarushStrukt> findVyyavNarushDataByStruct(@Param("b_date") String b_date, @Param("e_date") String e_date, @Param("nod") String nod, @Param("st") String st);
}