package com.lol.model.summonerSpells;

import java.util.HashMap;

public class SummonerSpellListDto {
	private HashMap<String, SummonerSpellDto> data;
	private String version;
	private String type;
	
	
	public SummonerSpellListDto() {
		super();
	}
	public SummonerSpellListDto(HashMap<String, SummonerSpellDto> data, String version, String type) {
		super();
		this.data = data;
		this.version = version;
		this.type = type;
	}
	public HashMap<String, SummonerSpellDto> getData() {
		return data;
	}
	public void setData(HashMap<String, SummonerSpellDto> data) {
		this.data = data;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
