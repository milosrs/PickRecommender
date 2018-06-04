package com.lol.model.summonerSpells;

import java.util.List;

import com.lol.model.champions.ImageDto;
import com.lol.model.champions.LevelTipDto;
import com.lol.model.champions.SpellVarsDto;

public class SummonerSpellDto {
	private	List<SpellVarsDto> vars;
	private ImageDto image;
	private String costBurn;
	private List<Double> cooldown;
	private List<String> effectBurn;
	private int id;
	private String cooldownBurn;
	private String tooltip;
	private int maxrank;
	private String rangeBurn;
	private String description;
	private List<Object> effect; 		//This field is a List of List of Double.
	private String key;
	private LevelTipDto leveltip;
	private List<String> modes;
	private String resource;
	private String name;
	private String costType;
	private String sanitizedDescription;
	private String sanitizedTooltip;	
	private Object range; 				//This field is either a List of Integer or the String 'self' for spells that target one's own champion.
	private List<Integer> cost;
	private int summonerLevel;
	
	
	public SummonerSpellDto() {
		super();
	}
	public SummonerSpellDto(List<SpellVarsDto> vars, ImageDto image, String costBurn, List<Double> cooldown,
			List<String> effectBurn, int id, String cooldownBurn, String tooltip, int maxrank, String rangeBurn,
			String description, List<Object> effect, String key, LevelTipDto leveltip, List<String> modes,
			String resource, String name, String costType, String sanitizedDescription, String sanitizedTooltip,
			Object range, List<Integer> cost, int summonerLevel) {
		super();
		this.vars = vars;
		this.image = image;
		this.costBurn = costBurn;
		this.cooldown = cooldown;
		this.effectBurn = effectBurn;
		this.id = id;
		this.cooldownBurn = cooldownBurn;
		this.tooltip = tooltip;
		this.maxrank = maxrank;
		this.rangeBurn = rangeBurn;
		this.description = description;
		this.effect = effect;
		this.key = key;
		this.leveltip = leveltip;
		this.modes = modes;
		this.resource = resource;
		this.name = name;
		this.costType = costType;
		this.sanitizedDescription = sanitizedDescription;
		this.sanitizedTooltip = sanitizedTooltip;
		this.range = range;
		this.cost = cost;
		this.summonerLevel = summonerLevel;
	}
	public List<SpellVarsDto> getVars() {
		return vars;
	}
	public void setVars(List<SpellVarsDto> vars) {
		this.vars = vars;
	}
	public ImageDto getImage() {
		return image;
	}
	public void setImage(ImageDto image) {
		this.image = image;
	}
	public String getCostBurn() {
		return costBurn;
	}
	public void setCostBurn(String costBurn) {
		this.costBurn = costBurn;
	}
	public List<Double> getCooldown() {
		return cooldown;
	}
	public void setCooldown(List<Double> cooldown) {
		this.cooldown = cooldown;
	}
	public List<String> getEffectBurn() {
		return effectBurn;
	}
	public void setEffectBurn(List<String> effectBurn) {
		this.effectBurn = effectBurn;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCooldownBurn() {
		return cooldownBurn;
	}
	public void setCooldownBurn(String cooldownBurn) {
		this.cooldownBurn = cooldownBurn;
	}
	public String getTooltip() {
		return tooltip;
	}
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}
	public int getMaxrank() {
		return maxrank;
	}
	public void setMaxrank(int maxrank) {
		this.maxrank = maxrank;
	}
	public String getRangeBurn() {
		return rangeBurn;
	}
	public void setRangeBurn(String rangeBurn) {
		this.rangeBurn = rangeBurn;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Object> getEffect() {
		return effect;
	}
	public void setEffect(List<Object> effect) {
		this.effect = effect;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public LevelTipDto getLeveltip() {
		return leveltip;
	}
	public void setLeveltip(LevelTipDto leveltip) {
		this.leveltip = leveltip;
	}
	public List<String> getModes() {
		return modes;
	}
	public void setModes(List<String> modes) {
		this.modes = modes;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCostType() {
		return costType;
	}
	public void setCostType(String costType) {
		this.costType = costType;
	}
	public String getSanitizedDescription() {
		return sanitizedDescription;
	}
	public void setSanitizedDescription(String sanitizedDescription) {
		this.sanitizedDescription = sanitizedDescription;
	}
	public String getSanitizedTooltip() {
		return sanitizedTooltip;
	}
	public void setSanitizedTooltip(String sanitizedTooltip) {
		this.sanitizedTooltip = sanitizedTooltip;
	}
	public Object getRange() {
		return range;
	}
	public void setRange(Object range) {
		this.range = range;
	}
	public List<Integer> getCost() {
		return cost;
	}
	public void setCost(List<Integer> cost) {
		this.cost = cost;
	}
	public int getSummonerLevel() {
		return summonerLevel;
	}
	public void setSummonerLevel(int summonerLevel) {
		this.summonerLevel = summonerLevel;
	}
	
	
}
