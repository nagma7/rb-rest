package kz.railways.rbrestapp.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter	
public class NbdAnswer {
	private String strukt;
	private int lastCount;
	private int count;
	private String totalCount;
	private String link;
}
