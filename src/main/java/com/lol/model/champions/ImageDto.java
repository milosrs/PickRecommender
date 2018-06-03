package com.lol.model.champions;

public class ImageDto {
	private String full;
	private String group;
	private String sprite;
	private int h;	
	private int w;
	private int y;
	private int x;
	
	
	public ImageDto() {
		super();
	}
	public ImageDto(String full, String group, String sprite, int h, int w, int y, int x) {
		super();
		this.full = full;
		this.group = group;
		this.sprite = sprite;
		this.h = h;
		this.w = w;
		this.y = y;
		this.x = x;
	}
	public String getFull() {
		return full;
	}
	public void setFull(String full) {
		this.full = full;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getSprite() {
		return sprite;
	}
	public void setSprite(String sprite) {
		this.sprite = sprite;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
}
