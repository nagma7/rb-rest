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
public class NarushBezopBigData {
	
	@Column(name = "y1")
	private String year; 
	
	@Id
	@Column(name = "b_dt")
	private String begindate;	
	
	@Column(name = "e_dt")
	private String enddate;
	
	@Column(name = "defects_id")
	private int defectsId;
	
	@Column(name = "clid")
	private int clid;
	
	@Column(name = "def_title")
	private String defTitle;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "investigate")
	private String investigate;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "farm_code")
	private String farmCode;
	
	@Column(name = "nod_title")
	private String nodTitle;
	
}
