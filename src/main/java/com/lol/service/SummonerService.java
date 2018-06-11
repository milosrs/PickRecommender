package com.lol.service;

import java.io.IOException;

import com.lol.model.summoner.SummonerAuth;
import com.lol.model.summoner.SummonerDto;
import com.lol.model.summoner.SummonerLoginAuth;

public interface SummonerService {

	public SummonerAuth save(SummonerAuth auth);
	
	public SummonerAuth getByUsername(String username);
	
	public SummonerAuth register(SummonerAuth auth) throws IOException;
	
	public SummonerDto login(SummonerLoginAuth loginInfo) throws IOException;
}
