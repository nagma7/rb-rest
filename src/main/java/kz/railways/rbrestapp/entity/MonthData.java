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
public class MonthData {
	
	@Column(name = "ydate")
	private int yDate;
	@Id
	@Column(name = "mdate")
	private String mDate;
	
	@Column(name = "countm")
	private int count;
}
