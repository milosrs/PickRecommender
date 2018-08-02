package com.lol.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.kie.api.builder.KieRepository;
import org.kie.api.internal.utils.KieService;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lol.ChampionInfoBean;
import com.lol.model.PickTypes;
import com.lol.model.champions.Champion;
import com.lol.model.champions.ChampionListDto;
import com.lol.model.champions.ChampionPositionIdList;
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
	
	@Autowired
	private KieSession kieSession;
	
	@Autowired
	private ChampionInfoBean championInfo;
	
	@Override
	public ChampionListDto getAllForList(String token) throws IOException {
		ChampionListDto ret = null;
		
		if(isAccountValid(token)) {
			ret = championInfo.getChampionData();
		}
				
		return ret;
	}

	@Override
	public Champion getOneFullInfo(String champKey, String token) throws IOException {
		Champion ret = null;
		
		if(isAccountValid(token)) {
			ret = championInfo.getChampionDataByKey(champKey);
		}
				
		return ret;
	}
	
	public boolean isAccountValid(String token) {
		String username = jwtTokenUtil.getUsernameFromToken(token);
		SummonerAuth summoner = summonerRepository.getByUsername(username);
		
		return summoner != null;
	}

	@Override
	public List<Champion> generateRecommendations(ChampionPositionIdList picks) {
		List<Champion> friendlyChampions = new ArrayList<Champion>();
		List<Champion> enemyChampions = new ArrayList<Champion>();
		
		populateList(picks.getFriendlyTeamIds(), friendlyChampions);
		populateList(picks.getOpponentTeamIds(), enemyChampions);
		
		return null;
	}
	
	private void populateList(List<String> picks, List<Champion> toPopulate) {
		for(String pick: picks) {
			toPopulate.add(championInfo.getChampionDataByKey(pick));
		}
	}

	@Override
	public void executeDroolsTest() {
		kieSession.insert(new ChampionListDto());
		kieSession.fireAllRules();
	}
}
