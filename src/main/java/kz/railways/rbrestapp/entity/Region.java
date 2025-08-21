package kz.railways.rbrestapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="rb_dn")
public class Region {	
	@Id
	@Column(name="id")
	private Integer code;
	
	@Column(name="title")
	private String title;	
	
}
