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
public class NarushBezop {
	
	@Column(name = "y")
	private int year;
	/*@Column(name = "lastyear")
	private int lastyear;*/
	@Id
	@Column(name = "title")
	private String title;
	
	@Column(name = "s")
	private int count;
	
	/*@Column(name = "lastcount")
	private Integer lastCount;*/
}
