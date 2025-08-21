package kz.railways.rbrestapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import kz.railways.rbrestapp.entity.MainNBD;

public interface MainOtherRepository extends CrudRepository<MainNBD, Long> {
	@Query(value="SELECT s_id, strukt_code, SUM(count) AS count FROM (select sf.struktura_id AS s_id,s.kod AS strukt_code, COUNT(*) as COUNT from  rb_in_defects d"
			+ "			left join rb_struktura_farm sf on sf.farm_id = d.farm_id"
			+ "			left join rb_struktura s on s.id = sf.struktura_id"
			+ "			left join rb_farm f on f.id = d.farm_id"
			+ "			left join rb_defects def on def.id = d.defects_id"
			+ "			left join rb_classifiers cl on cl.id = def.classifiers_id"
			+ "			where d.deleted_at is null"
			+ "			and d.date >= :b_date and d.date <= :e_date"
			+ "			and s.code not in (10) and d.dn_id = :nod and d.deleted_at is null"
			+ "			and f.own = 1"
			+ "			group by 1,2"
			+ "			union all"
			+ "		    select sf.struktura_id,s.kod, count(*) from  rb_in_difficulties d"
			+ "			left join rb_struktura_farm sf on sf.farm_id = d.farm_id"
			+ "			left join rb_struktura s on s.id = sf.struktura_id"
			+ "			left join rb_farm f on f.id = d.farm_id"
			+ "			left join rb_defects def on def.id = d.difficulties_id"
			+ "			left join rb_classifiers cl on cl.id = def.classifiers_id"
			+ "			where d.deleted_at is null"
			+ "			and d.date >= :b_date and d.date <= :e_date"
			+ "			and s.code not in (10) and d.dn_id=:nod and d.deleted_at is null"
			+ "			and f.own = 1"
			+ "			group by 1,2"
			+ "			union all"
			+ "		    select sf.struktura_id,s.kod ,count(*) from  rb_in_other_difficulties d"
			+ "			left join rb_struktura_farm sf on sf.farm_id = d.farm_id"
			+ "			left join rb_struktura s on s.id = sf.struktura_id"
			+ "			left join rb_farm f on f.id = d.farm_id"
			+ "			left join rb_defects def on def.id = d.difficulties_id"
			+ "			left join rb_classifiers cl on cl.id = def.classifiers_id"
			+ "			where d.deleted_at is null"
			+ "			and d.date >= :b_date and d.date <= :e_date"
			+ "			and s.code not in (10) and d.dn_id = :nod and d.deleted_at is null"
			+ "			and f.own = 1"
			+ "			group by 1,2"
			+ "       union ALL     "
			+ "       select'100' as struktura_id,cl.title,count(*) from  rb_in_other_difficulties d"
			+ "       left join rb_difficulties dif on dif.id = d.difficulties_id"
			+ "       left join rb_classifiers cl on cl.id = dif.classifiers_id"
			+ "       where d.deleted_at is null"
			+ "       and d.date >= :b_date and d.date <= :e_date"
			+ "       and dif.classifiers_id = 5 and d.dn_id = :nod   and d.deleted_at is null) a GROUP BY strukt_code", nativeQuery = true)
	List<MainNBD> findAllOther(@Param("b_date") String b_date, @Param("e_date") String e_date, @Param("nod") String nod);
}
