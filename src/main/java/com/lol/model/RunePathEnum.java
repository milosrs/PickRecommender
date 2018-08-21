package com.lol.model;

public enum RunePathEnum {
	PRECISION("Precision"),
	SORCERY("Sorcery"),
	INSPIRATION("Inspiration"),
	RESOLVE("Resolve"),
	DOMINATION("Domination");
	
	private String type;
	
	RunePathEnum(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
