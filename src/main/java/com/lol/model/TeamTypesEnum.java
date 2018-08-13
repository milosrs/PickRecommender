package com.lol.model;

public enum TeamTypesEnum {
	FRIENDLY("Friendly"),
	OPPONENT("Opponent");
	
	private String type;
	
	TeamTypesEnum(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
