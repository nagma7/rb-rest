package kz.railways.rbrestapp.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChartsAnswer {
	private String name;
	private List<Integer> data;
}
