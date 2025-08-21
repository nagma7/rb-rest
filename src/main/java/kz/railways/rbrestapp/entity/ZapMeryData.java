package kz.railways.rbrestapp.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ZapMeryData {
	private List<ZapMery> zapMery;
	private List<ZapMeryStrukt> zapMeryStruktList;
}
