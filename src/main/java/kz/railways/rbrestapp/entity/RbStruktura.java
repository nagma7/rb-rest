package kz.railways.rbrestapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name="rb_struktura")
public class RbStruktura {
	@Id
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "kod")
	private String title;
}
