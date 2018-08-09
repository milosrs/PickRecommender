package com.lol.service;

import java.io.IOException;
import java.util.List;

import com.lol.model.champions.Champion;
import com.lol.model.champions.ChampionListDto;
import com.lol.model.viewModel.ChampionPicks;
import com.lol.model.viewModel.ChampionViewModel;

public interface ChampionService {

	public ChampionListDto getAllForList(String realm) throws IOException;
	
	public Champion getOneFullInfo(String champKey, String token) throws IOException;
	
	public List<Champion> generateRecommendations(ChampionPicks picks);

	public void executeDroolsTest();
	
	public List<ChampionViewModel> convertListToViewModel(ChampionListDto championListDto);
	
	public ChampionViewModel convertChampionToViewModel(Champion champ);
}
