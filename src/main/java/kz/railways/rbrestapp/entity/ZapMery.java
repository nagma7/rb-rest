package kz.railways.rbrestapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ZapMery {
	@Column(name="title")
	private String title;
	@Column(name="count_")
	private int count;
	@Id
	@Column(name="id1")
	private int id;
}
