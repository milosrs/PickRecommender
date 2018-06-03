package com.lol.model.champions;

public class Passive {
	private ImageDto image;		
	private String sanitizedDescription;
	private String name;
	private String description;
	
	public Passive() {
		super();
	}
	public Passive(ImageDto image, String sanitizedDescription, String name, String description) {
		super();
		this.image = image;
		this.sanitizedDescription = sanitizedDescription;
		this.name = name;
		this.description = description;
	}
	public ImageDto getImage() {
		return image;
	}
	public void setImage(ImageDto image) {
		this.image = image;
	}
	public String getSanitizedDescription() {
		return sanitizedDescription;
	}
	public void setSanitizedDescription(String sanitizedDescription) {
		this.sanitizedDescription = sanitizedDescription;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
