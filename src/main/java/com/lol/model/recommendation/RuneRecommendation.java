package com.lol.model.recommendation;

import java.util.List;

import com.lol.model.reforgedRunes.ReforgedRuneDto;

public class RuneRecommendation {
	private String primaryRunesPathName;
	private String secondaryRunesPathName;
	private List<ReforgedRuneDto> primaryRunes;
	private List<ReforgedRuneDto> secondaryRunes;
	
	public RuneRecommendation(String primaryRunesPathName, String secondaryRunesPathName, List<ReforgedRuneDto> primaryRunes, List<ReforgedRuneDto> secondaryRunes) {
		super();
		this.primaryRunesPathName = primaryRunesPathName;
		this.secondaryRunesPathName = secondaryRunesPathName;
		this.primaryRunes = primaryRunes;
		this.secondaryRunes = secondaryRunes;
	}

	public RuneRecommendation() {
		super();
	}

	public String getPrimaryRunesPathName() {
		return primaryRunesPathName;
	}

	public void setPrimaryRunesPathName(String primaryRunesPathName) {
		this.primaryRunesPathName = primaryRunesPathName;
	}

	public String getSecondaryRunesPathName() {
		return secondaryRunesPathName;
	}

	public void setSecondaryRunesPathName(String secondaryRunesPathName) {
		this.secondaryRunesPathName = secondaryRunesPathName;
	}

	public List<ReforgedRuneDto> getPrimaryRunes() {
		return primaryRunes;
	}

	public void setPrimaryRunes(List<ReforgedRuneDto> primaryRunes) {
		this.primaryRunes = primaryRunes;
	}

	public List<ReforgedRuneDto> getSecondaryRunes() {
		return secondaryRunes;
	}

	public void setSecondaryRunes(List<ReforgedRuneDto> secondaryRunes) {
		this.secondaryRunes = secondaryRunes;
	}
}
