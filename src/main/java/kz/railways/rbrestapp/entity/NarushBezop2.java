package kz.railways.rbrestapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class NarushBezop2 {
	
	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "curryear")
	private Integer year;
	@Column(name = "lastyear")
	private Integer lastyear;
	@Column(name = "title")
	private String title;	
	@Column(name = "currcount")
	private Integer count;	
	@Column(name = "lastcount")
	private Integer lastCount;
}
