package com.lol.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.Globals;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lol.dataBeans.ChampionInfoBean;
import com.lol.dataBeans.RunesBean;
import com.lol.facts.RunesNames;
import com.lol.model.MapPositionsEnum;
import com.lol.model.champions.Champion;
import com.lol.model.champions.ChampionsAndRoles;
import com.lol.model.recommendation.RuneRecommendation;
import com.lol.model.reforgedRunes.ReforgedRuneDto;
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
		MapPositionsEnum position = MapPositionsEnum.convertShortToEnum(playerPosition);
		RuneRecommendation ret = null;
		
		for(ChampionsAndRoles c : championsBean.getChampRoles()) {
			if(c.getChampion().getId() == champ.getId()) {
				champRole = c;
				break;
			}
		}
		
		if(champRole != null) {
			RunesNames names = new RunesNames();
			fireRuneSession(position, champRole, names);
			
			String primaryName = (String)runesSession.getGlobal("primaryName");
			String secondaryName = (String)runesSession.getGlobal("secondaryName");
			List<ReforgedRuneDto> primaryRunes = (List<ReforgedRuneDto>)runesSession.getGlobal("primary");
			List<ReforgedRuneDto> secondaryRunes = (List<ReforgedRuneDto>)runesSession.getGlobal("secondary");
			
			ret = new RuneRecommendation(names.getPrimaryName(), names.getSecondaryName(), primaryRunes, secondaryRunes);
		}
		
		runesSession.dispose();
		
		return ret;
	}
	
	
	public void fireRuneSession(MapPositionsEnum playerPosition, ChampionsAndRoles champion, RunesNames names) {
		runesSession = kieContainer.newKieSession("rune-rules");
		runesSession.insert(playerPosition);
		runesSession.insert(champion);
		runesSession.insert(names);
		runesSession.setGlobal("util", utils);
		runesSession.setGlobal("primaryName", "");
		runesSession.setGlobal("secondaryName", "");
		runesSession.setGlobal("allRunes", runesBean.getRunes());
		runesSession.setGlobal("primary", new ArrayList<ReforgedRuneDto>());
		runesSession.setGlobal("secondary", new ArrayList<ReforgedRuneDto>());
		runesSession.fireAllRules();
	}
}
