package com.lol.serviceImpl;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lol.dataBeans.ChampionInfoBean;
import com.lol.dataBeans.RunesBean;
import com.lol.model.MapPositionsEnum;
import com.lol.model.champions.Champion;
import com.lol.model.champions.ChampionsAndRoles;
import com.lol.model.recommendation.RuneRecommendation;
import com.lol.service.RuneService;
import com.lol.util.Utils;

@Service
public class RuneServiceImpl implements RuneService {

	@Autowired
	private Utils utils;
	
	@Autowired
	private KieContainer kieContainer;
	
	@Autowired
	private RunesBean runesBean;
	
	@Autowired
	private ChampionInfoBean championsBean;
	
	private KieSession runesSession;
	
	@Override
	public RuneRecommendation recommendRunes(Integer championId, String playerPosition) {
		ChampionsAndRoles champRole = null;
		Champion champ = championsBean.getChampionDataById(championId);
		MapPositionsEnum position = MapPositionsEnum.valueOf(playerPosition);
		RuneRecommendation ret = null;
		
		for(ChampionsAndRoles c : championsBean.getChampRoles()) {
			if(c.getChampion().getId() == champ.getId()) {
				champRole = c;
				break;
			}
		}
		
		if(champRole != null) {
			fireRuneSession(position, champRole);
			
			if(runesSession.getGlobal("recommendation") instanceof RuneRecommendation) {
				ret = (RuneRecommendation)runesSession.getGlobal("recommendation");
			}
		}
		
		return ret;
	}
	
	
	public void fireRuneSession(MapPositionsEnum playerPosition, ChampionsAndRoles champion) {
		runesSession = kieContainer.newKieSession("rune-rules");
		runesSession.insert(playerPosition);
		runesSession.insert(champion);
		runesSession.setGlobal("util", utils);
		runesSession.setGlobal("allRunes", runesBean.getRunes());
		runesSession.fireAllRules();
	}
}
