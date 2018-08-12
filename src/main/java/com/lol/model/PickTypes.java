package com.lol.model;

public enum PickTypes {
	FRIENDLY("Friendly"),
	OPPONENT("Opponent");
	
	private String type;
	
	PickTypes(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
