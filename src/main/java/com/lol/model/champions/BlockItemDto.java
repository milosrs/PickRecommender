package com.lol.model.champions;

public class BlockItemDto {
	private int count;
	private int id;
	
	public BlockItemDto() {
		super();
	}
	public BlockItemDto(int count, int id) {
		super();
		this.count = count;
		this.id = id;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
