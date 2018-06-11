package com.lol.model.champions;

import java.util.Map;

public class ChampionListDto {
	private Map<String, String>	 keys;
	private Map<String, Champion> data;	
	private String version;
	private String type;
	private String format;
	
	public ChampionListDto() {
		super();
	}
	public ChampionListDto(Map<String, String> keys, Map<String, Champion> data, String version, String type,
			String format) {
		super();
		this.keys = keys;
		this.data = data;
		this.version = version;
		this.type = type;
		this.format = format;
	}
	public Map<String, String> getKeys() {
		return keys;
	}
	public void setKeys(Map<String, String> keys) {
		this.keys = keys;
	}
	public Map<String, Champion> getData() {
		return data;
	}
	public void setData(Map<String, Champion> data) {
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
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
}
