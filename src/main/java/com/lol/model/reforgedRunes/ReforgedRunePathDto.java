package com.lol.model.reforgedRunes;

import java.util.List;

public class ReforgedRunePathDto {
	private List<ReforgedRuneSlotDto> slots;	
	private String icon;
	private int id;	
	private String key;	
	private String name;
	
	
	public ReforgedRunePathDto() {
		super();
	}
	public ReforgedRunePathDto(List<ReforgedRuneSlotDto> slots, String icon, int id, String key, String name) {
		super();
		this.slots = slots;
		this.icon = icon;
		this.id = id;
		this.key = key;
		this.name = name;
	}
	public List<ReforgedRuneSlotDto> getSlots() {
		return slots;
	}
	public void setSlots(List<ReforgedRuneSlotDto> slots) {
		this.slots = slots;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
