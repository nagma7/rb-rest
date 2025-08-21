package kz.railways.rbrestapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import kz.railways.rbrestapp.entity.ZapMery;

public interface ZapMeryRepository extends CrudRepository<ZapMery, String> {
	@Query(value="select if(t1.owner_id is not null,t1.owner_id ,t1.id) as id1, COUNT(*) AS count_, "
			+ "(select rb_act_violations_type.title from rb_act_violations_type where rb_act_violations_type.id=id1) as title "
			+ "    from rb_acts "
			+ "    right join rb_act_violations on rb_acts.id=rb_act_violations.acts_id "
			+ "    left join rb_act_violations_type as t1 on t1.id=rb_act_violations.act_violation_type_id "
			+ "    left join rb_farm f on f.id=rb_act_violations.farm_id "
			+ "     "
			+ "    where rb_acts.date between :b_date and :e_date and rb_acts.dn_id = :nod and rb_act_violations.act_violation_type_id is not null "
			+ "    and rb_act_violations.deleted_at is null  and f.own=1  "
			+ "     and t1.deleted_at is null "
			+ "    group by 1  ", nativeQuery = true)
	List<ZapMery> findZapMeryData(@Param("b_date") String b_date, @Param("e_date") String e_date, @Param("nod") String nod);
	
	@Query(value="select if(t1.owner_id is not null,t1.owner_id ,t1.id) as id1, COUNT(*) AS count_, "
			+ "(select rb_act_violations_type.title from rb_act_violations_type where rb_act_violations_type.id=id1) as title "
			+ "    from rb_acts "
			+ "    right join rb_act_violations on rb_acts.id=rb_act_violations.acts_id "
			+ "    left join rb_act_violations_type as t1 on t1.id=rb_act_violations.act_violation_type_id "
			+ "    left join rb_farm f on f.id=rb_act_violations.farm_id "
			+ "    left join rb_struktura_farm sf on sf.farm_id=f.id "
			+ "    where rb_acts.date between :b_date and :e_date and rb_acts.dn_id = :nod and rb_act_violations.act_violation_type_id is not null "
			+ "    and rb_act_violations.deleted_at is null  and f.own=1 AND sf.struktura_id = :st "
			+ "     and t1.deleted_at is null "
			+ "    group by 1  ", nativeQuery = true)
	List<ZapMery> findZapMeryByStructData(@Param("b_date") String b_date, @Param("e_date") String e_date, @Param("nod") String nod, @Param("st") String st);
}