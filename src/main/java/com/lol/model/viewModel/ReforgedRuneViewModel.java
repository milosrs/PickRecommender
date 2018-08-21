package com.lol.model.viewModel;

public class ReforgedRuneViewModel {
	private String name;
	private String description;
	
	public ReforgedRuneViewModel(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public ReforgedRuneViewModel() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
