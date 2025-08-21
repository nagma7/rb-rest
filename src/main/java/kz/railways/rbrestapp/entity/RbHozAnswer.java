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
public class RbHozAnswer {
	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "code")
	private String title;
	
	@Column(name = "count")
	private Integer count;
}
