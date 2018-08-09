package com.lol.model.viewModel;

import com.lol.model.champions.ImageDto;

public class ChampionViewModel {
	public int id;
	public ImageDto imageInfo;
	public String name;
	public String title;
	
	public ChampionViewModel() {
		super();
	}
	public ChampionViewModel(int id, ImageDto imageInfo, String name, String title) {
		super();
		this.id = id;
		this.imageInfo = imageInfo;
		this.name = name;
		this.title = title;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ImageDto getImageInfo() {
		return imageInfo;
	}
	public void setImageInfo(ImageDto imageInfo) {
		this.imageInfo = imageInfo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
