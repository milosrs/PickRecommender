package com.lol.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lol.ChampionInfoBean;
import com.lol.Test;
import com.lol.model.PickTypes;
import com.lol.model.champions.Champion;
import com.lol.model.champions.ChampionListDto;
import com.lol.model.viewModel.ChampionPicks;
import com.lol.model.summoner.SummonerAuth;
import com.lol.model.viewModel.ChampionViewModel;
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
	private KieSession countersSession;
	
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
	public List<Champion> generateRecommendations(ChampionPicks picks) {
		List<Champion> friendlyChampions = new ArrayList<Champion>();
		List<Champion> enemyChampions = new ArrayList<Champion>();
		List<Champion> recommendations = new ArrayList<Champion>();
		
		for(String key : picks.getFriendlyTeam().keySet()) {
			friendlyChampions.add(convertIdToChampion(picks.getFriendlyTeam().get(key)));
		}
		
		for(Integer key: picks.getOpponentTeam()) {
			enemyChampions.add(convertIdToChampion(key));
		}
		
		countersSession.insert(friendlyChampions);
		countersSession.insert(enemyChampions);
		countersSession.setGlobal("recommendations", recommendations);
		countersSession.setGlobal("firstPick", picks.getFirstPick());
		countersSession.setGlobal("playerPosition", picks.getPlayerPosition());
		countersSession.fireAllRules();
		
		recommendations = (List<Champion>) countersSession.getGlobal("recommendations");
		
		return recommendations;
	}

	private Champion convertIdToChampion(Integer idToAdd) {
		Champion toRet = null;
		
		for(String id: championInfo.getChampionData().getKeys().keySet()) {
			if(idToAdd == Integer.parseInt(id)) {
				toRet = championInfo.getChampionDataByKey(id);
				break;
			}
		}
		
		return toRet;
	}

	@Override
	public void executeDroolsTest() {
		ChampionListDto help = new ChampionListDto();
		Test test = new Test();
		help.setVersion(null);
	
		kieSession.insert(help);
		kieSession.insert(test);
		kieSession.fireAllRules();
		System.out.println("Test value: " + test.getTest());
		kieSession.dispose();
	}
	
	public List<ChampionViewModel> convertListToViewModel(ChampionListDto championListDto) {
		List<ChampionViewModel> ret = new ArrayList<ChampionViewModel>();
		
		for(String key : championListDto.getKeys().keySet()) {
			String name = championListDto.getKeys().get(key);
			Champion champ = championListDto.getData().get(name);
			ChampionViewModel toAdd = convertChampionToViewModel(champ);
			
			ret.add(toAdd);
		}
		
		return ret;
	}
	
	public ChampionViewModel convertChampionToViewModel(Champion champ) {
		return new ChampionViewModel(champ.getId(), champ.getImage(), champ.getName(), champ.getTitle());
	}
}
