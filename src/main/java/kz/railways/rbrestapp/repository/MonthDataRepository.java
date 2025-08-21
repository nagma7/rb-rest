package kz.railways.rbrestapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import kz.railways.rbrestapp.entity.MonthData;

public interface MonthDataRepository extends CrudRepository<MonthData, String> {
	@Query(value="SELECT ydate, mdate, SUM(c) AS countm FROM (select year(ind.date) AS ydate, month(ind.date) AS mdate, count(*) AS c from rb_in_defects ind "
			+ "	left join rb_farm f on f.id=ind.farm_id "
			+ "	left join rb_defects d on d.id=ind.defects_id "
			+ "	left join rb_classifiers cl on cl.id=d.classifiers_id "
			+ "	where  (ind.date>=:b_date and ind.date <= :e_date) and f.own=1   and ind.deleted_at  is  null and  ind.dn_id=:nod and ind.deleted_at is null "
			+ "	group by 1,2 "
			+ "	union all "
			+ "select year(ind.date),month(ind.date),count(*) from rb_in_difficulties ind "
			+ "	left join rb_farm f on f.id=ind.farm_id "
			+ "	left join rb_difficulties d on d.id=ind.difficulties_id "
			+ "	left join rb_classifiers cl on cl.id=d.classifiers_id "
			+ "	where  (ind.date>=:b_date and ind.date <= :e_date) and f.own=1   and ind.deleted_at  is  null and  ind.dn_id=:nod and ind.deleted_at is null  "
			+ "	group by 1,2 "
			+ "	union all "
			+ "select year(ind.date),month(ind.date),count(*) from rb_in_other_difficulties ind "
			+ "	left join rb_farm f on f.id=ind.farm_id "
			+ "	left join rb_difficulties d on d.id=ind.difficulties_id "
			+ "	left join rb_classifiers cl on cl.id=d.classifiers_id "
			+ "	where  (ind.date>=:b_date and ind.date <= :e_date) and f.own=1  and ind.deleted_at  is  null  and  ind.dn_id=:nod and ind.deleted_at is null "
			+ "	group by 1,2 "
			+ "	order by 2) a GROUP BY mdate", nativeQuery = true)
	List<MonthData> findMonthData(@Param("b_date") String b_date, @Param("e_date") String e_date, @Param("nod") String nod);
	
	@Query(value="SELECT ydate, mdate, SUM(c) AS countm FROM (select year(ind.date) AS ydate, month(ind.date) AS mdate, count(*) AS c from rb_in_defects ind "
			+ "	left join rb_farm f on f.id=ind.farm_id "
			+ " left join rb_struktura_farm sf on sf.farm_id=f.id "
			+ "	left join rb_defects d on d.id=ind.defects_id "
			+ "	left join rb_classifiers cl on cl.id=d.classifiers_id "
			+ "	where  (ind.date>=:b_date and ind.date <= :e_date) and f.own=1   and ind.deleted_at  is  null and  ind.dn_id=:nod and ind.deleted_at is null and "
			+ "	sf.struktura_id = :st"
			+ "	group by 1,2 "
			+ "	union all "
			+ "select year(ind.date),month(ind.date),count(*) from rb_in_difficulties ind "
			+ "	left join rb_farm f on f.id=ind.farm_id "
			+ " left join rb_struktura_farm sf on sf.farm_id=f.id "
			+ "	left join rb_difficulties d on d.id=ind.difficulties_id "
			+ "	left join rb_classifiers cl on cl.id=d.classifiers_id "
			+ "	where  (ind.date>=:b_date and ind.date <= :e_date) and f.own=1   and ind.deleted_at  is  null and  ind.dn_id=:nod and ind.deleted_at is null and "
			+ "	sf.struktura_id = :st"
			+ "	group by 1,2 "
			+ "	union all "
			+ "select year(ind.date),month(ind.date),count(*) from rb_in_other_difficulties ind "
			+ "	left join rb_farm f on f.id=ind.farm_id "
			+ " left join rb_struktura_farm sf on sf.farm_id=f.id "
			+ "	left join rb_difficulties d on d.id=ind.difficulties_id "
			+ "	left join rb_classifiers cl on cl.id=d.classifiers_id "
			+ "	where  (ind.date>=:b_date and ind.date <= :e_date) and f.own=1  and ind.deleted_at  is  null  and  ind.dn_id=:nod and ind.deleted_at is null and "
			+ "	sf.struktura_id = :st"
			+ "	group by 1,2 "
			+ "	order by 2) a GROUP BY mdate", nativeQuery = true)
	List<MonthData> findMonthDataByStruct(@Param("b_date") String b_date, @Param("e_date") String e_date, @Param("nod") String nod, @Param("st") String st);
}