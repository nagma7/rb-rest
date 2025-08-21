package kz.railways.rbrestapp.entity;

import lombok.Setter;

import java.util.List;

import lombok.Getter;

@Getter
@Setter
public class VyyavNarushData {
	private List<VyyavNarush> vyyavNarush;
	private List<VyyavNarushStrukt> vyyavNarushStruktList;
}
