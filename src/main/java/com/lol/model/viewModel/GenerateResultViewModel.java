package com.lol.model.viewModel;

import java.util.ArrayList;
import java.util.List;

public class GenerateResultViewModel {
	private List<ChampionViewModel> champRecommendations;
	private List<SummonerSpellViewModel> spellRecommendations;
	
	public GenerateResultViewModel(List<ChampionViewModel> champRecommendations,
			List<SummonerSpellViewModel> spellRecommendations) {
		super();
		this.champRecommendations = champRecommendations;
		this.spellRecommendations = spellRecommendations;
	}
	
	public GenerateResultViewModel() {
		super();
		champRecommendations = new ArrayList<ChampionViewModel>();
		spellRecommendations = new ArrayList<SummonerSpellViewModel>();
	}

	public List<ChampionViewModel> getChampRecommendations() {
		return champRecommendations;
	}

	public void setChampRecommendations(List<ChampionViewModel> champRecommendations) {
		this.champRecommendations = champRecommendations;
	}

	public List<SummonerSpellViewModel> getSpellRecommendations() {
		return spellRecommendations;
	}

	public void setSpellRecommendations(List<SummonerSpellViewModel> spellRecommendations) {
		this.spellRecommendations = spellRecommendations;
	}
}
