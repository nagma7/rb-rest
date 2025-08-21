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
public class SobytiyaNaPPData {

	@Column(name = "y1")
	private String year; 
	
	@Id
	@Column(name = "b_dt")
	private String begindate;	
	
	@Column(name = "e_dt")
	private String enddate;
	
	@Column(name = "dn_id")
	private int dn_id;
	
	@Column(name = "farm_id")
	private int farm_id;
	
	@Column(name = "dntitle")
	private String dntitle;
	
	@Column(name = "farmtitle")
	private String farmtitle;
    
    @Column(name = "farmsignds")
    private int farmsignds;

	@Column(name = "enterprises_id")
	private int enterprises_id;

	@Column(name = "enttitle")
    private String enttitle;

	@Column(name = "encode")
	private String encode;
	
	@Column(name = "codeid")
	private int codeid;

	@Column(name = "deftitle")
	private String deftitle;
	
	@Column(name = "var_train")
	private String var_train;
	
	@Column(name = "investigate")
	private String investigate;
	
	@Column(name = "description")
	private String description; 
}
