package kz.railways.rbrestapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import kz.railways.rbrestapp.entity.ZapMeryStrukt;

public interface ZapMeryStructRepository extends CrudRepository<ZapMeryStrukt, String> {
	@Query(value="select s.kod, COUNT(*) AS count_ from rb_acts "
			+ "right join rb_act_violations on rb_acts.id=rb_act_violations.acts_id "
			+ "		left join rb_act_violations_type as t1 on t1.id=rb_act_violations.act_violation_type_id "
			+ "		left join rb_farm f on f.id=rb_act_violations.farm_id "
			+ "		left join rb_struktura_farm sf on sf.farm_id=rb_act_violations.farm_id "
			+ "		left join rb_struktura s on s.id=sf.struktura_id "
			+ "		where rb_acts.date between :b_date and :e_date and rb_acts.dn_id = :nod and rb_act_violations.act_violation_type_id is not null "
			+ "		and rb_act_violations.deleted_at is null  and f.own=1  "
			+ "		and t1.deleted_at is null "
			+ "		group by 1 ", nativeQuery = true)
	List<ZapMeryStrukt> findZapMeryStruktData(@Param("b_date") String b_date, @Param("e_date") String e_date, @Param("nod") String nod);
	
	@Query(value="select f.code as kod, COUNT(*) AS count_ from rb_acts "
			+ "right join rb_act_violations on rb_acts.id=rb_act_violations.acts_id "
			+ "		left join rb_act_violations_type as t1 on t1.id=rb_act_violations.act_violation_type_id "
			+ "		left join rb_farm f on f.id=rb_act_violations.farm_id "
			+ "		left join rb_struktura_farm sf on sf.farm_id=rb_act_violations.farm_id "
			+ "		where rb_acts.date between :b_date and :e_date and rb_acts.dn_id = :nod and rb_act_violations.act_violation_type_id is not null "
			+ "		and rb_act_violations.deleted_at is null  and f.own=1  "
			+ "		and t1.deleted_at is null AND sf.struktura_id = :st"
			+ "		group by 1 ", nativeQuery = true)
	List<ZapMeryStrukt> findZapMeryStruktByStructData(@Param("b_date") String b_date, @Param("e_date") String e_date, @Param("nod") String nod, @Param("st") String st);
}