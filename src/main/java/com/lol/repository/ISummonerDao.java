package com.lol.repository;

import java.io.IOException;

import com.lol.model.summoner.SummonerAuth;
import com.lol.model.summoner.SummonerDto;
import com.lol.model.summoner.SummonerLoginAuth;

public interface ISummonerDao {

	public SummonerDto login(SummonerLoginAuth loginInfo) throws IOException;
	
	public SummonerAuth register(SummonerAuth registerInfo) throws IOException;
}
