package com.lol.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lol.ChampionInfoBean;
import com.lol.facts.AllChampionsAndRoles;
import com.lol.model.MapPositionsEnum;
import com.lol.model.TeamTypesEnum;
import com.lol.model.champions.Champion;
import com.lol.model.champions.ChampionListDto;
import com.lol.model.champions.ChampionsAndRoles;
import com.lol.model.recommendation.PlayerGenerativeData;
import com.lol.model.viewModel.ChampionPicksViewModel;
import com.lol.model.summoner.SummonerAuth;
import com.lol.model.summoner.SummonerDto;
import com.lol.model.viewModel.ChampionViewModel;
import com.lol.repository.SummonerRepository;
import com.lol.requestSender.ChampionMasteryRequestSender;
import com.lol.requestSender.ChampionRequestSender;
import com.lol.requestSender.SummonerServiceRequestSender;
import com.lol.security.JWTTokenUtil;
import com.lol.service.ChampionService;

@Service
public class ChampionServiceImpl implements ChampionService {

	@Autowired
	private ChampionRequestSender requestSender;
	
	@Autowired
	private ChampionMasteryRequestSender champMasteryRequestSender;
	
	@Autowired
	private SummonerServiceRequestSender summonerRequestSender;
	
	@Autowired
	private JWTTokenUtil jwtTokenUtil;
	
	@Autowired
	private SummonerRepository summonerRepository;
	
	@Autowired
	private KieContainer kieContainer;
	
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Champion> generateRecommendations(ChampionPicksViewModel picks, SummonerDto summoner) {
		List<Champion> recommendations = new ArrayList<Champion>();
		MapPositionsEnum[] positionOrder = fillFriendlyPositions(picks);
		Map<MapPositionsEnum, ChampionsAndRoles> friendlyChampions = fillFriendlyChampRoles(picks);
		List<ChampionsAndRoles> enemyChampions = fillEnemyChampRoles(picks);
		
		AllChampionsAndRoles acar = new AllChampionsAndRoles(championInfo.getChampRoles());
		PlayerGenerativeData playerGenData = new PlayerGenerativeData(friendlyChampions, enemyChampions,
				MapPositionsEnum.valueOf(picks.getPlayerPosition().toUpperCase()),
				TeamTypesEnum.valueOf(picks.getFirstPick().toUpperCase()), positionOrder);
		
		
		fireContersSession(playerGenData, acar, summoner);
		Object droolsRet = countersSession.getGlobal("recommendations");
		
		if(droolsRet instanceof List){
		    if(((List)droolsRet).size()>0 && (((List)droolsRet).get(0) instanceof Champion)){
		    	recommendations = (List<Champion>) droolsRet;
		    }
		}
		
		countersSession.dispose();
		
		return recommendations;
	}
	
	private void fireContersSession(PlayerGenerativeData playerGenData, AllChampionsAndRoles acar, SummonerDto summoner) {
		List<Champion> recommendations = new ArrayList<Champion>();
		countersSession = kieContainer.newKieSession("counter-rules");
		countersSession.insert(playerGenData);
		countersSession.insert(summoner);
		countersSession.insert(acar);
		countersSession.setGlobal("championMasteryRequestSender", champMasteryRequestSender);
		countersSession.setGlobal("recommendations", recommendations);
		countersSession.fireAllRules();
	}
	
	private ChampionsAndRoles findChampInList(Champion champ) {
		ChampionsAndRoles toAdd = null;
		
		for(ChampionsAndRoles champRole: championInfo.getChampRoles()) {
			if(champ != null) {
				if(champRole.getChampion().getId() == champ.getId()) {
					toAdd = champRole;
					break;
				}
			}
		}
		
		return toAdd;
	}
	
	private List<ChampionsAndRoles> fillEnemyChampRoles(ChampionPicksViewModel picks) {
		List<ChampionsAndRoles> enemyChampions = new ArrayList<ChampionsAndRoles>();
		
		for(Integer key: picks.getOpponentTeam()) {
			Champion champ = championInfo.getChampionDataById(key);
			ChampionsAndRoles toAdd = findChampInList(champ);
			enemyChampions.add(toAdd);
		}
		
		return enemyChampions;
	}
	
	private Map<MapPositionsEnum, ChampionsAndRoles> fillFriendlyChampRoles(ChampionPicksViewModel picks) {
		Map<MapPositionsEnum, ChampionsAndRoles> friendlyChampions = new HashMap<MapPositionsEnum, ChampionsAndRoles>();
		
		for(String key : picks.getFriendlyTeam().keySet()) {
			Integer id = picks.getFriendlyTeam().get(key);
			Champion champ = championInfo.getChampionDataById(id);
			ChampionsAndRoles toAdd = findChampInList(champ);
			
			friendlyChampions.put(MapPositionsEnum.enumFactory(key.toUpperCase()), toAdd);
		}
		
		return friendlyChampions;
	}
	
	private MapPositionsEnum[] fillFriendlyPositions(ChampionPicksViewModel picks) {
		int i = 0;
		MapPositionsEnum[] positionOrder = new MapPositionsEnum[5];
		
		for(String key : picks.getFriendlyTeam().keySet()) {
			positionOrder[i++] = MapPositionsEnum.enumFactory(key.toUpperCase());
		}
		
		return positionOrder;
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
