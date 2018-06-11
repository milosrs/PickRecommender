package com.lol.serviceImpl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lol.model.champions.Champion;
import com.lol.model.champions.ChampionListDto;
import com.lol.model.summoner.SummonerAuth;
import com.lol.repository.SummonerRepository;
import com.lol.requestSender.ChampionRequestSender;
import com.lol.security.JWTTokenUtil;
import com.lol.service.ChampionService;

@Service
public class ChampionServiceImpl implements ChampionService {

	@Autowired
	private ChampionRequestSender requestSender;
	
	@Autowired
	private JWTTokenUtil jwtTokenUtil;
	
	@Autowired
	private SummonerRepository summonerRepository;
	
	@Override
	public ChampionListDto getAllForList(String token) throws IOException {
		ChampionListDto list = null;
		String username = jwtTokenUtil.getUsernameFromToken(token);
		SummonerAuth summoner = summonerRepository.getByUsername(username);
		
		list = requestSender.sendRequest(summoner.getRealm());
				
		return list;
	}

	@Override
	public Champion getOneFullInfo(String champKey, String token) throws IOException {
		Champion champ = null;
		String username = jwtTokenUtil.getUsernameFromToken(token);
		SummonerAuth summoner = summonerRepository.getByUsername(username);
		
		champ = requestSender.sendRequest(champKey, summoner.getRealm());
				
		return champ;
	}

}
