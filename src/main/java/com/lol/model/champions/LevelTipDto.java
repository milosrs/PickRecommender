package com.lol.model.champions;

import java.util.List;

public class LevelTipDto {
	private List<String> effect;
	private List<String> label;
	
	
	public LevelTipDto() {
		super();
	}
	public LevelTipDto(List<String> effect, List<String> label) {
		super();
		this.effect = effect;
		this.label = label;
	}
	public List<String> getEffect() {
		return effect;
	}
	public void setEffect(List<String> effect) {
		this.effect = effect;
	}
	public List<String> getLabel() {
		return label;
	}
	public void setLabel(List<String> label) {
		this.label = label;
	}
}
