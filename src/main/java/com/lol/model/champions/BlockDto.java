package com.lol.model.champions;

import java.util.List;

public class BlockDto {
	private List<BlockItemDto> items;
	private boolean recMath;
	private String type;
	
	public BlockDto() {
		super();
	}
	public BlockDto(List<BlockItemDto> items, boolean recMath, String type) {
		super();
		this.items = items;
		this.recMath = recMath;
		this.type = type;
	}
	public List<BlockItemDto> getItems() {
		return items;
	}
	public void setItems(List<BlockItemDto> items) {
		this.items = items;
	}
	public boolean isRecMath() {
		return recMath;
	}
	public void setRecMath(boolean recMath) {
		this.recMath = recMath;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
