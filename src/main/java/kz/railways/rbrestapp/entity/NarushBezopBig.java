package kz.railways.rbrestapp.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NarushBezopBig {
	
	private String date;
	private List<NarushBezopBigData> narushList;

}
