package com.lol.model.viewModel;

import java.util.List;

import com.lol.model.reforgedRunes.ReforgedRuneDto;

public class RuneRecommendationViewModel {
	private String primaryRunesPathName;
	private String secondaryRunesPathName;
	private List<ReforgedRuneViewModel> primaryRunes;
	private List<ReforgedRuneViewModel> secondaryRunes;
	
	public RuneRecommendationViewModel(String primaryRunesPathName, String secondaryRunesPathName,
			List<ReforgedRuneViewModel> primaryRunes, List<ReforgedRuneViewModel> secondaryRunes) {
		super();
		this.primaryRunesPathName = primaryRunesPathName;
		this.secondaryRunesPathName = secondaryRunesPathName;
		this.primaryRunes = primaryRunes;
		this.secondaryRunes = secondaryRunes;
	}
	
	public RuneRecommendationViewModel() {
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
	public List<ReforgedRuneViewModel> getPrimaryRunes() {
		return primaryRunes;
	}
	public void setPrimaryRunes(List<ReforgedRuneViewModel> primaryRunes) {
		this.primaryRunes = primaryRunes;
	}
	public List<ReforgedRuneViewModel> getSecondaryRunes() {
		return secondaryRunes;
	}
	public void setSecondaryRunes(List<ReforgedRuneViewModel> secondaryRunes) {
		this.secondaryRunes = secondaryRunes;
	}
}
