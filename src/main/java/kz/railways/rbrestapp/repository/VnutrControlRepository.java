package kz.railways.rbrestapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import kz.railways.rbrestapp.entity.VnutrControl;

public interface VnutrControlRepository extends CrudRepository<VnutrControl, Integer>{
	@Query(value="select count(*) as count_  from rb_acts  "
			+ "      right join rb_act_violations on rb_act_violations.acts_id = rb_acts.id "
			+ "      left join rb_farm f on f.id=rb_act_violations.farm_id "
			+ "      left join rb_act_violations_type on rb_act_violations_type.id=rb_act_violations.act_violation_type_id "
			+ "        where   "
			+ "        rb_acts.date between :b_date and :e_date and rb_acts.dn_id=:nod   and f.own=1 "
			+ "        and rb_act_violations.act_violation_type_id is not null "
			+ "        and rb_act_violations_type.deleted_at is null "
			+ "        and f .deleted_at is null "
			+ "        and rb_acts.deleted_at is null "
			+ "", nativeQuery = true)
	VnutrControl findCountZapMer(@Param("b_date") String b_date, @Param("e_date") String e_date, @Param("nod") String nod);
	
	@Query(value="select count(*) as count_  from rb_acts  "
			+ "      right join rb_act_violations on rb_act_violations.acts_id = rb_acts.id "
			+ "      left join rb_farm f on f.id=rb_act_violations.farm_id "
			+ "		 LEFT JOIN rb_struktura_farm rs ON rs.farm_id = f.id "
			+ "      left join rb_act_violations_type on rb_act_violations_type.id=rb_act_violations.act_violation_type_id "
			+ "        where   "
			+ "        rb_acts.date between :b_date and :e_date and rb_acts.dn_id=:nod   and f.own=1 "
			+ "        and rb_act_violations.act_violation_type_id is not null "
			+ "        and rb_act_violations_type.deleted_at is null "
			+ "        and f .deleted_at is null "
			+ "        and rb_acts.deleted_at is null "
			+ "          AND rs.struktura_id = :st", nativeQuery = true)
	VnutrControl findCountZapMerByStruct(@Param("b_date") String b_date, @Param("e_date") String e_date, @Param("nod") String nod, @Param("st") String st);

	@Query(value="select count(*) as count_  from rb_acts  "
			+ "      left join rb_act_violations on rb_act_violations.acts_id = rb_acts.id "
			+ "      left join rb_farm f on f.id=rb_act_violations.farm_id "
			+ "      where  rb_acts.date between :b_date and :e_date and rb_acts.dn_id=:nod and f.own=1 "
			+ "      and rb_act_violations.deleted_at is null		    "
			+ "      and f .deleted_at is null "
			+ "      and rb_acts.deleted_at is null "
			+ "", nativeQuery = true)
	VnutrControl findCountNarush(@Param("b_date") String b_date, @Param("e_date") String e_date, @Param("nod") String nod);
	
	@Query(value="select count(*) as count_  from rb_acts  "
			+ " left join rb_act_violations on rb_act_violations.acts_id = rb_acts.id "
			+ " left join rb_farm f on f.id=rb_act_violations.farm_id "
			+ " LEFT JOIN rb_struktura_farm rs ON rs.farm_id = f.id "
			+ " where  rb_acts.date between :b_date and :e_date and rb_acts.dn_id=:nod and f.own=1 "
			+ " and rb_act_violations.deleted_at is null		    "
			+ " and f .deleted_at is null "
			+ " and rb_acts.deleted_at is null "
			+ "	AND rs.struktura_id = :st ", nativeQuery = true)
	VnutrControl findCountNarushByStruct(@Param("b_date") String b_date, @Param("e_date") String e_date, @Param("nod") String nod, @Param("st") String st);
	
	@Query(value="select count(*) as count_ from rb_acts  "
			+ "     RIGHT JOIN rb_act_violations on rb_act_violations.acts_id = rb_acts.id "
			+ "     left join rb_farm f on f.id=rb_act_violations.farm_id "
			+ "       where   "
			+ "      (rb_acts.date between :b_date and :e_date) and rb_acts.dn_id=:nod  and f.own=1  "
			+ "      and  rb_act_violations.farm_id is not null "
			+ "      and rb_act_violations.enterprise_id is not null "
			+ "     and rb_act_violations.deleted_at is null "
			+ "      and f .deleted_at is null "
			+ "       and rb_acts.deleted_at is null "
			+ "     group by rb_acts.id,rb_act_violations.farm_id,rb_act_violations.enterprise_id "
			+ " ", nativeQuery = true)
	List<VnutrControl> findCountProverki(@Param("b_date") String b_date, @Param("e_date") String e_date, @Param("nod") String nod);
	
	@Query(value="select count(*) as count_ from rb_acts "
			+ "     RIGHT JOIN rb_act_violations on rb_act_violations.acts_id = rb_acts.id"
			+ "     left join rb_farm f on f.id=rb_act_violations.farm_id"
			+ "     left join rb_struktura_farm sf on sf.farm_id=rb_act_violations.farm_id"
			+ "   where  "
			+ "      (rb_acts.date between :b_date and :e_date) and "
			+ "		rb_acts.dn_id = :nod and "
			+ "		f.own = 1 and  "
			+ "		rb_act_violations.farm_id is not null and "
			+ "		rb_act_violations.enterprise_id is not NULL and "
			+ "		rb_act_violations.deleted_at is null and "
			+ "		f .deleted_at is null and "
			+ "		rb_acts.deleted_at is NULL AND "
			+ "		sf.struktura_id = :st "
			+ "   group by rb_acts.id,rb_act_violations.farm_id,rb_act_violations.enterprise_id ", nativeQuery = true)
	List<VnutrControl> findCountProverkiByStruct(@Param("b_date") String b_date, @Param("e_date") String e_date, @Param("nod") String nod, @Param("st") String st);
}
