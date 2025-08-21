package kz.railways.rbrestapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ZapMeryStrukt {
	@Id
	@Column(name="kod")
	private String code;
	@Column(name="count_")
	private int count;
}
