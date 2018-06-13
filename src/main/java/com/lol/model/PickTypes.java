package com.lol.model;

public enum PickTypes {
	FRIEND("Friendly"),
	OPPONENT("Opponent");
	
	private String type;
	
	PickTypes(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}
}
