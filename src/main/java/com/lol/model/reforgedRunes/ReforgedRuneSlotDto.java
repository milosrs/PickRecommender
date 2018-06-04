package com.lol.model.reforgedRunes;

import java.util.List;

public class ReforgedRuneSlotDto {
	private List<ReforgedRuneDto> runes;

	
	public ReforgedRuneSlotDto() {
		super();
	}

	public ReforgedRuneSlotDto(List<ReforgedRuneDto> runes) {
		super();
		this.runes = runes;
	}

	public List<ReforgedRuneDto> getRunes() {
		return runes;
	}

	public void setRunes(List<ReforgedRuneDto> runes) {
		this.runes = runes;
	}
	
	
}
