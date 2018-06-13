package com.lol.model.champions;

import java.util.Map;

public class ChampionPositionIdList {
	private Map<String, Map<String, Integer>> picks;

	public ChampionPositionIdList() {
		super();
	}

	public ChampionPositionIdList(Map<String, Map<String, Integer>> picks) {
		super();
		this.picks = picks;
	}

	public Map<String, Map<String, Integer>> getPicks() {
		return picks;
	}

	public void setPicks(Map<String, Map<String, Integer>> picks) {
		this.picks = picks;
	}
	
	
}
