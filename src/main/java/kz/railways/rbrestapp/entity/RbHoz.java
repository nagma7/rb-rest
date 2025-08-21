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
public class RbHoz {
	@Id
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "code")
	private String title;
}
