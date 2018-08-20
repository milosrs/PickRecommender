package com.lol.model.recommendation;

import java.util.ArrayList;
import java.util.List;

import com.lol.model.champions.Champion;
import com.lol.model.summonerSpells.SummonerSpellDto;

public class GenerateResult {
	private List<Champion> champRecommendations;
	private List<SummonerSpellDto> spellRecommendations;
	
	public GenerateResult(List<Champion> champRecommendations, List<SummonerSpellDto> spellRecommendations) {
		super();
		this.champRecommendations = champRecommendations;
		this.spellRecommendations = spellRecommendations;
	}

	public GenerateResult() {
		super();
		champRecommendations = new ArrayList<Champion>();
		spellRecommendations = new ArrayList<SummonerSpellDto>();
	}

	public List<Champion> getChampRecommendations() {
		return champRecommendations;
	}

	public void setChampRecommendations(List<Champion> champRecommendations) {
		this.champRecommendations = champRecommendations;
	}

	public List<SummonerSpellDto> getSpellRecommendations() {
		return spellRecommendations;
	}

	public void setSpellRecommendations(List<SummonerSpellDto> spellRecommendations) {
		this.spellRecommendations = spellRecommendations;
	}
}
