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

public class MainNBD {
	@Id
	@Column(name = "s_id")
	private int sId;
	
	@Column(name = "strukt_code")
	private String struktCode;
	
	@Column(name = "count")
	private Integer count;
	
	@Column(name = "lastcount")
	private Integer lastCount;
	
}
