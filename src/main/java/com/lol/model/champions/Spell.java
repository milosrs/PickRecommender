package com.lol.model.champions;

import java.util.List;

public class Spell {
	private String cooldownBurn;
	private String resource;
	private LevelTipDto leveltip;
	private List<SpellVarsDto>	vars;
	private String costType;
	private ImageDto image;
	private String sanitizedDescription;
	private String sanitizedTooltip;
	private List<Object> effect;
	private String tooltip	;
	private int maxrank;
	private String costBurn;
	private String rangeBurn;
	private Object range; //This field is either a List of Integer or the String "self" for spells that target ones own champion.;
	private List<Double> cooldown;
	private List<Integer> cost;
	private String key;
	private String description;
	private List<String> effectBurn;
	private List<ImageDto> altimages;
	private String name;
	
	
	public Spell() {
		super();
	}
	public Spell(String cooldownBurn, String resource, LevelTipDto leveltip, List<SpellVarsDto> vars, String costType,
			ImageDto image, String sanitizedDescription, String sanitizedTooltip, List<Object> effect, String tooltip,
			int maxrank, String costBurn, String rangeBurn, Object range, List<Double> cooldown, List<Integer> cost,
			String key, String description, List<String> effectBurn, List<ImageDto> altimages, String name) {
		super();
		this.cooldownBurn = cooldownBurn;
		this.resource = resource;
		this.leveltip = leveltip;
		this.vars = vars;
		this.costType = costType;
		this.image = image;
		this.sanitizedDescription = sanitizedDescription;
		this.sanitizedTooltip = sanitizedTooltip;
		this.effect = effect;
		this.tooltip = tooltip;
		this.maxrank = maxrank;
		this.costBurn = costBurn;
		this.rangeBurn = rangeBurn;
		this.range = range;
		this.cooldown = cooldown;
		this.cost = cost;
		this.key = key;
		this.description = description;
		this.effectBurn = effectBurn;
		this.altimages = altimages;
		this.name = name;
	}
	public String getCooldownBurn() {
		return cooldownBurn;
	}
	public void setCooldownBurn(String cooldownBurn) {
		this.cooldownBurn = cooldownBurn;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public LevelTipDto getLeveltip() {
		return leveltip;
	}
	public void setLeveltip(LevelTipDto leveltip) {
		this.leveltip = leveltip;
	}
	public List<SpellVarsDto> getVars() {
		return vars;
	}
	public void setVars(List<SpellVarsDto> vars) {
		this.vars = vars;
	}
	public String getCostType() {
		return costType;
	}
	public void setCostType(String costType) {
		this.costType = costType;
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
	public String getSanitizedTooltip() {
		return sanitizedTooltip;
	}
	public void setSanitizedTooltip(String sanitizedTooltip) {
		this.sanitizedTooltip = sanitizedTooltip;
	}
	public List<Object> getEffect() {
		return effect;
	}
	public void setEffect(List<Object> effect) {
		this.effect = effect;
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
	public String getCostBurn() {
		return costBurn;
	}
	public void setCostBurn(String costBurn) {
		this.costBurn = costBurn;
	}
	public String getRangeBurn() {
		return rangeBurn;
	}
	public void setRangeBurn(String rangeBurn) {
		this.rangeBurn = rangeBurn;
	}
	public Object getRange() {
		return range;
	}
	public void setRange(Object range) {
		this.range = range;
	}
	public List<Double> getCooldown() {
		return cooldown;
	}
	public void setCooldown(List<Double> cooldown) {
		this.cooldown = cooldown;
	}
	public List<Integer> getCost() {
		return cost;
	}
	public void setCost(List<Integer> cost) {
		this.cost = cost;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<String> getEffectBurn() {
		return effectBurn;
	}
	public void setEffectBurn(List<String> effectBurn) {
		this.effectBurn = effectBurn;
	}
	public List<ImageDto> getAltimages() {
		return altimages;
	}
	public void setAltimages(List<ImageDto> altimages) {
		this.altimages = altimages;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
