package kz.railways.rbrestapp.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SobytiyaNaPP {
	private String date;
	private List<SobytiyaNaPPData> sobytiyaNaPPDataList;
}
