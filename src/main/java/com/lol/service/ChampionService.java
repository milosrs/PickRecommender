package com.lol.service;

import java.io.IOException;

import com.lol.model.champions.Champion;
import com.lol.model.champions.ChampionListDto;

public interface ChampionService {

	public ChampionListDto getAllForList(String realm) throws IOException;
	
	public Champion getOneFullInfo(String champKey, String token) throws IOException;
}
