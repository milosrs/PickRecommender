package com.lol.model;

public enum MapPositionsEnum {
	TOP,
	JUNGLE,
	MID,
	BOTTOM,
	SUPPORT;
	
	public MapPositionsEnum getValue() {
		return this;
	}
	
	public static MapPositionsEnum enumFactory(String name) {
		MapPositionsEnum toRet = null;
		
		if(name.equals("JG"))
			toRet = MapPositionsEnum.JUNGLE;
		if(name.equals("BOT"))
			toRet = MapPositionsEnum.BOTTOM;
		if(name.equals("SUP"))
			toRet = MapPositionsEnum.SUPPORT;
		if(name.equals("TOP"))
			toRet = MapPositionsEnum.TOP;
		if(name.equals("MID"))
			toRet = MapPositionsEnum.MID;
		
		return toRet;
	}
}
