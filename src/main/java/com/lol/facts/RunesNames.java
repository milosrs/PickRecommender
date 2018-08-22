package com.lol.facts;

public class RunesNames {
	private String primaryName;
	private String secondaryName;
	
	public RunesNames(String primaryName, String secondaryName) {
		super();
		this.primaryName = primaryName;
		this.secondaryName = secondaryName;
	}

	public RunesNames() {
		super();
	}

	public String getPrimaryName() {
		return primaryName;
	}

	public void setPrimaryName(String primaryName) {
		this.primaryName = primaryName;
	}

	public String getSecondaryName() {
		return secondaryName;
	}

	public void setSecondaryName(String secondaryName) {
		this.secondaryName = secondaryName;
	}
}
