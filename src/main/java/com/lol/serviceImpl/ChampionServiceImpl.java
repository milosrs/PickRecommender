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
import com.lol.model.MapPositionsEnum;
import com.lol.model.TeamTypesEnum;
import com.lol.model.champions.Champion;
import com.lol.model.champions.ChampionListDto;
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
		MapPositionsEnum[] positionOrder = new MapPositionsEnum[5];
		Map<MapPositionsEnum, Champion> friendlyChampions = new HashMap<MapPositionsEnum, Champion>();
		List<Champion> enemyChampions = new ArrayList<Champion>();
		List<Champion> recommendations = new ArrayList<Champion>();
		int i = 0;
		
		for(String key : picks.getFriendlyTeam().keySet()) {
			friendlyChampions.put(MapPositionsEnum.enumFactory(key.toUpperCase()), convertIdToChampion(picks.getFriendlyTeam().get(key)));
			positionOrder[i++] = MapPositionsEnum.enumFactory(key.toUpperCase());
		}
		
		for(Integer key: picks.getOpponentTeam()) {
			enemyChampions.add(convertIdToChampion(key));
		}
		
		PlayerGenerativeData playerGenData = new PlayerGenerativeData(friendlyChampions, enemyChampions,
				MapPositionsEnum.valueOf(picks.getPlayerPosition().toUpperCase()),
				TeamTypesEnum.valueOf(picks.getFirstPick().toUpperCase()), positionOrder);
		
		countersSession.insert(playerGenData);
		countersSession.setGlobal("recommendations", recommendations);
		countersSession.fireAllRules();
		
		Object droolsRet = countersSession.getGlobal("recommendations");
		
		if(droolsRet instanceof List){
		    if(((List)droolsRet).size()>0 && (((List)droolsRet).get(0) instanceof Champion)){
		    	recommendations = (List<Champion>) droolsRet;
		    }
		}
		
		countersSession.dispose();
		
		return recommendations;
	}

	private Champion convertIdToChampion(Integer idToAdd) {
		Champion toRet = null;
		
		if(idToAdd != null) {
			for(String id: championInfo.getChampionData().getKeys().keySet()) {
				if(idToAdd == Integer.parseInt(id)) {
					toRet = championInfo.getChampionDataByKey(id);
					break;
				}
			}
		}
		
		
		return toRet;
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
