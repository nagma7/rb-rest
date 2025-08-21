package kz.railways.rbrestapp.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonthDataAnswer {
	private String year;
	private List<MonthData> monthDataList;
}
