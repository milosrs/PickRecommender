package com.lol.service;

import java.io.IOException;
import java.util.List;

import com.lol.model.champions.Champion;
import com.lol.model.champions.ChampionListDto;
import com.lol.model.recommendation.GenerateResult;
import com.lol.model.summoner.SummonerDto;
import com.lol.model.viewModel.ChampionPicksViewModel;
import com.lol.model.viewModel.ChampionViewModel;

public interface ChampionService {

	public ChampionListDto getAllForList(String realm) throws IOException;
	
	public Champion getOneFullInfo(String champKey, String token) throws IOException;
	
	public GenerateResult generateRecommendations(ChampionPicksViewModel picks, SummonerDto summoner);
	
	public List<ChampionViewModel> convertListToViewModel(ChampionListDto championListDto);
	
	public ChampionViewModel convertChampionToViewModel(Champion champ);
}
