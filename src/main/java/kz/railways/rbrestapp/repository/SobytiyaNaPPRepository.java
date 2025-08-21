package kz.railways.rbrestapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import kz.railways.rbrestapp.entity.SobytiyaNaPPData;

public interface SobytiyaNaPPRepository extends CrudRepository<SobytiyaNaPPData, String>{
	@Query(value="select SUBSTR(date, 1, 4) AS y1, rb_in_defects.id as id, concat(date,\" \", s_time) AS b_dt, concat(e_date,\" \", e_time) AS e_dt, rb_in_defects.dn_id, rb_in_defects.farm_id, rb_dn.title as dntitle,  \r\n"
			+ "                rb_farm.title as farmtitle, rb_farm.sign_ds as farmsignds, rb_in_defects.enterprises_id as enterprises_id, \r\n"
			+ "                rb_enterprises.title as enttitle, rb_enterprises.code as encode, rb_defects.id as codeid, rb_defects.title as deftitle, var_train, investigate, \r\n"
			+ "                description from rb_in_defects \r\n"
			+ "                left join rb_dn on dn_id = rb_dn.id \r\n"
			+ "                left join rb_farm on farm_id = rb_farm.id \r\n"
			+ "                left join rb_defects on defects_id = rb_defects.id \r\n"
			+ "                left join rb_enterprises on enterprises_id = rb_enterprises.id where rb_in_defects.deleted_at is null and date BETWEEN :b_date AND :e_date \r\n"
			+ "					 and rb_defects.classifiers_id = 5 and rb_defects.classifiers_id <> 6 and rb_defects.sign_mr = 4 and rb_in_defects.dn_id in (:nod) and rb_in_defects.farm_id in (1) \r\n"
			+ "                union (select SUBSTR(date, 1, 4) AS y1, rb_in_difficulties.id as id, concat(date,\" \", s_time) AS b_dt, concat(e_date,\" \", e_time) AS e_dt, rb_in_difficulties.dn_id, \r\n"
			+ "					 rb_in_difficulties.farm_id, rb_dn.title as dntitle, rb_farm.title as farmtitle, rb_farm.sign_ds as farmsignds, \r\n"
			+ "					 rb_in_difficulties.enterprises_id as enterprises_id, rb_enterprises.title as enttitle, rb_enterprises.code as encode, rb_difficulties.id as codeid, \r\n"
			+ "					 rb_difficulties.title as deftitle, rb_in_difficulties.var_train, rb_in_difficulties.investigate, rb_in_difficulties.description from rb_in_difficulties \r\n"
			+ "                left join rb_dn on dn_id = rb_dn.id \r\n"
			+ "                left join rb_farm on farm_id = rb_farm.id \r\n"
			+ "                left join rb_difficulties on difficulties_id = rb_difficulties.id \r\n"
			+ "                left join rb_enterprises on enterprises_id = rb_enterprises.id where rb_in_difficulties.deleted_at is null and rb_in_difficulties.date between :b_date AND :e_date \r\n"
			+ "					 and rb_difficulties.classifiers_id = 5  and rb_in_difficulties.dn_id in (:nod) and rb_in_difficulties.farm_id in (1) ) \r\n"
			+ "                union (select SUBSTR(date, 1, 4) AS y1, rb_in_other_difficulties.id as id, concat(date,\" \", s_time) AS b_dt, concat(e_date,\" \", e_time) AS e_dt, rb_in_other_difficulties.dn_id, \r\n"
			+ "					 rb_in_other_difficulties.farm_id, rb_dn.title as dntitle, rb_farm.title as farmtitle, rb_farm.sign_ds as farmsignds, \r\n"
			+ "					 rb_in_other_difficulties.enterprises_id as enterprises_id, rb_enterprises.title as enttitle, rb_enterprises.code as encode, rb_difficulties.id as codeid, rb_difficulties.title as deftitle, var_train, investigate, description from rb_in_other_difficulties \r\n"
			+ "                left join rb_dn on dn_id = rb_dn.id \r\n"
			+ "                left join rb_farm on farm_id = rb_farm.id \r\n"
			+ "                left join rb_difficulties on difficulties_id = rb_difficulties.id \r\n"
			+ "                left join rb_enterprises on enterprises_id = rb_enterprises.id where rb_in_other_difficulties.deleted_at is null and date between :b_date AND :e_date \r\n"
			+ "					 and rb_difficulties.classifiers_id = 5 and rb_in_other_difficulties.dn_id in (:nod) and rb_in_other_difficulties.farm_id in (1)) ;", nativeQuery = true)
	List<SobytiyaNaPPData> findAll(@Param("b_date") String b_date, @Param("e_date") String e_date, @Param("nod") String nod);
}
