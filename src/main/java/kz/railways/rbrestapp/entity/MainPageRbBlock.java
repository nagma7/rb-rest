package kz.railways.rbrestapp.entity;

import java.util.Map;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="rb_main_page_blocks", schema="nsi")
public class MainPageRbBlock {
	@Id
	private int id;
	private String title;
	private String link;
	private int sort;
	@Transient
	private NbdAnswer ndb;
	@Transient
	private String style;
	@Transient
	private Map<String, String> styleNg;
}