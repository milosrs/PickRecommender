package com.lol.model.champions;

import java.util.List;

public class Champion {
	private InfoDto	info;
	private List<String> enemytips;
	private Stats stats;
	private String name;
	private String title;
	private ImageDto image;
	private	List<String> tags;
	private String partype;
	private List<SkinDto> skins;
	private Passive passive;
	private List<RecommendedDto> recommended;
	private List<String> allytips;
	private String key;
	private String lore;
	private int id;
	private String blurb;
	private List<Spell> spells;
	
	
	public Champion() {
		super();
	}
	public Champion(InfoDto info, List<String> enemytips, Stats stats, String name, String title, ImageDto image,
			List<String> tags, String partype, List<SkinDto> skins, Passive passive, List<RecommendedDto> recommended,
			List<String> allytips, String key, String lore, int id, String blurb, List<Spell> spells) {
		super();
		this.info = info;
		this.enemytips = enemytips;
		this.stats = stats;
		this.name = name;
		this.title = title;
		this.image = image;
		this.tags = tags;
		this.partype = partype;
		this.skins = skins;
		this.passive = passive;
		this.recommended = recommended;
		this.allytips = allytips;
		this.key = key;
		this.lore = lore;
		this.id = id;
		this.blurb = blurb;
		this.spells = spells;
	}
	public InfoDto getInfo() {
		return info;
	}
	public void setInfo(InfoDto info) {
		this.info = info;
	}
	public List<String> getEnemytips() {
		return enemytips;
	}
	public void setEnemytips(List<String> enemytips) {
		this.enemytips = enemytips;
	}
	public Stats getStats() {
		return stats;
	}
	public void setStats(Stats stats) {
		this.stats = stats;
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
	public ImageDto getImage() {
		return image;
	}
	public void setImage(ImageDto image) {
		this.image = image;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public String getPartype() {
		return partype;
	}
	public void setPartype(String partype) {
		this.partype = partype;
	}
	public List<SkinDto> getSkins() {
		return skins;
	}
	public void setSkins(List<SkinDto> skins) {
		this.skins = skins;
	}
	public Passive getPassive() {
		return passive;
	}
	public void setPassive(Passive passive) {
		this.passive = passive;
	}
	public List<RecommendedDto> getRecommended() {
		return recommended;
	}
	public void setRecommended(List<RecommendedDto> recommended) {
		this.recommended = recommended;
	}
	public List<String> getAllytips() {
		return allytips;
	}
	public void setAllytips(List<String> allytips) {
		this.allytips = allytips;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getLore() {
		return lore;
	}
	public void setLore(String lore) {
		this.lore = lore;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBlurb() {
		return blurb;
	}
	public void setBlurb(String blurb) {
		this.blurb = blurb;
	}
	public List<Spell> getSpells() {
		return spells;
	}
	public void setSpells(List<Spell> spells) {
		this.spells = spells;
	}
	
	
}
