package kz.railways.rbrestapp.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NarushBezopAnswer {
	private String year;
	private List<NarushBezop> narushBezopList;
}
