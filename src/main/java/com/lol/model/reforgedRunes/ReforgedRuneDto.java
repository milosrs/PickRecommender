package com.lol.model.reforgedRunes;

public class ReforgedRuneDto {
	private String runePathName;
	private int runePathId;
	private String name;	
	private int id;
	private String key;
	private String shortDesc;
	private String longDesc;
	private String icon;
	
	
	public ReforgedRuneDto() {
		super();
	}
	public ReforgedRuneDto(String runePathName, int runePathId, String name, int id, String key, String shortDesc,
			String longDesc, String icon) {
		super();
		this.runePathName = runePathName;
		this.runePathId = runePathId;
		this.name = name;
		this.id = id;
		this.key = key;
		this.shortDesc = shortDesc;
		this.longDesc = longDesc;
		this.icon = icon;
	}
	public String getRunePathName() {
		return runePathName;
	}
	public void setRunePathName(String runePathName) {
		this.runePathName = runePathName;
	}
	public int getRunePathId() {
		return runePathId;
	}
	public void setRunePathId(int runePathId) {
		this.runePathId = runePathId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getShortDesc() {
		return shortDesc;
	}
	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}
	public String getLongDesc() {
		return longDesc;
	}
	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
}
