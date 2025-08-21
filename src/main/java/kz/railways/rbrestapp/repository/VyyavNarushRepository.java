package kz.railways.rbrestapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import kz.railways.rbrestapp.entity.VyyavNarush;

public interface VyyavNarushRepository extends CrudRepository<VyyavNarush, String> {
	@Query(value="select rb_act_violations_wording.title,count(*) as count_ from rb_act_violations_wording  "
			+ "      left join rb_act_violations on rb_act_violations.act_violations_wording_id = rb_act_violations_wording.id "
			+ "      left join rb_acts on rb_acts.id=rb_act_violations.acts_id "
			+ "      left join rb_farm f on f.id=rb_act_violations.farm_id "
			+ "      where rb_acts.date between :b_date and :e_date and rb_acts.dn_id = :nod  and f.own=1 "
			+ "            and rb_act_violations.deleted_at is null		    "
			+ "            and f .deleted_at is null "
			+ "            and rb_acts.deleted_at is null "
			+ "      group by 1 "
			+ "      union all "
			+ "      select 'Другие' as title, count(*) from rb_acts "
			+ "      left join rb_act_violations on rb_acts.id=rb_act_violations.acts_id "
			+ "      left join rb_farm f on f.id=rb_act_violations.farm_id "
			+ "      where rb_acts.date between :b_date and :e_date and rb_acts.dn_id = :nod and rb_act_violations.act_violations_wording_id is null  and f.own=1 "
			+ "            and rb_act_violations.deleted_at is null		    "
			+ "            and f .deleted_at is null "
			+ "            and rb_acts.deleted_at is null "
			+ "      group by 1 ", nativeQuery = true)
	List<VyyavNarush> findVyyavNarushData(@Param("b_date") String b_date, @Param("e_date") String e_date, @Param("nod") String nod);
	
	@Query(value="select rb_act_violations_wording.title,count(*) as count_ from rb_act_violations_wording  "
			+ "      left join rb_act_violations on rb_act_violations.act_violations_wording_id = rb_act_violations_wording.id "
			+ "      left join rb_acts on rb_acts.id=rb_act_violations.acts_id "
			+ "      left join rb_farm f on f.id=rb_act_violations.farm_id "
			+ "		 left join rb_struktura_farm sf on sf.farm_id=f.id "
			+ "      where rb_acts.date between :b_date and :e_date and rb_acts.dn_id = :nod  and f.own=1 "
			+ "            and rb_act_violations.deleted_at is null		    "
			+ "            and f .deleted_at is null "
			+ "            and rb_acts.deleted_at is null "
			+ "				AND sf.struktura_id = :st "
			+ "      group by 1 "
			+ "      union all "
			+ "      select 'Другие' as title, count(*) from rb_acts "
			+ "      left join rb_act_violations on rb_acts.id=rb_act_violations.acts_id "
			+ "      left join rb_farm f on f.id=rb_act_violations.farm_id "
			+ "		 left join rb_struktura_farm sf on sf.farm_id=f.id "
			+ "      where rb_acts.date between :b_date and :e_date and rb_acts.dn_id = :nod and rb_act_violations.act_violations_wording_id is null  and f.own=1 "
			+ "            and rb_act_violations.deleted_at is null		    "
			+ "            and f .deleted_at is null "
			+ "            and rb_acts.deleted_at is null "
			+ "				AND sf.struktura_id = :st "
			+ "      group by 1 ", nativeQuery = true)
	List<VyyavNarush> findVyyavNarushDataByStruct(@Param("b_date") String b_date, @Param("e_date") String e_date, @Param("nod") String nod, @Param("st") String st);
}