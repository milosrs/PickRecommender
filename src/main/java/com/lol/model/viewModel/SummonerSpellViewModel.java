package com.lol.model.viewModel;

public class SummonerSpellViewModel {
	private String description;
	private String name;
	private String fullImagePath;
	
	public SummonerSpellViewModel(String description, String name, String fullImagePath) {
		super();
		this.description = description;
		this.name = name;
		this.fullImagePath = fullImagePath;
	}
	
	public SummonerSpellViewModel() {
		super();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullImagePath() {
		return fullImagePath;
	}

	public void setFullImagePath(String fullImagePath) {
		this.fullImagePath = fullImagePath;
	}
}
