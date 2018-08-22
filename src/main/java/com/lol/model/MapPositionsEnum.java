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
	
	public static MapPositionsEnum convertShortToEnum(String name) {
		MapPositionsEnum toRet = null;
		name = name.toUpperCase();
		
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
