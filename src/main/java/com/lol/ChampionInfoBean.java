package com.lol;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lol.model.ChampionRolesEnum;
import com.lol.model.MapPositionsEnum;
import com.lol.model.champions.Champion;
import com.lol.model.champions.ChampionListDto;
import com.lol.model.champions.ChampionsAndRoles;
import com.lol.requestSender.ChampionRequestSender;

@Component
public class ChampionInfoBean extends TimerTask implements InitializingBean{

	@Autowired
	private ChampionRequestSender requestSender;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private KieSession kieSession;
	
	private ChampionListDto championData;
	private final String realm = "eun1";
	private long daily = 1000*60*60*24;
	private List<ChampionsAndRoles> champRoles;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		championData = requestSender.sendRequest(realm);
		Timer timer = new Timer();
		timer.schedule(this, 0, daily);
	}

	public ChampionRequestSender getRequestSender() {
		return requestSender;
	}

	public void setRequestSender(ChampionRequestSender requestSender) {
		this.requestSender = requestSender;
	}

	public ChampionListDto getChampionData() {
		return championData;
	}

	public void setChampionData(ChampionListDto championData) {
		this.championData = championData;
	}

	@Override
	public void run() {
		String currentVersion = championData.getVersion();
		String[] currentVersionFragments = currentVersion.split(".");
		String latestVersion = null;
		String[] latestVersionFragments = null;
		
		try {
			latestVersion = requestSender.getLatestVersion(realm);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		latestVersionFragments = latestVersion.split(".");
		
		if(latestVersionGreaterThanCurrent(currentVersionFragments, latestVersionFragments)) {
			try {
				championData = requestSender.sendRequest(realm);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		try {
			this.champRoles = readChampionsRoles();
			championInfoIntoList();
			generalizeChampionPositions();
			printChampNameAndPositions();
		} catch (Exception e) {
			System.out.println("Error while reading JSON file.");
			e.printStackTrace();
		}
	}
	
	private void championInfoIntoList() {
		for(ChampionsAndRoles car : this.champRoles) {
			for(String key: championData.getData().keySet()) {
				Champion champ = championData.getData().get(key);
				String champName = car.getName();
				
				if(champ.getName().equals(champName)) {
					car.setChampion(championData.getData().get(key));
					break;
				}
			}
		}
	}
	
	private List<ChampionsAndRoles> readChampionsRoles() throws JsonParseException, JsonMappingException, IOException {
		File file = new File("./championClasses.json");
		List<ChampionsAndRoles> ret = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, ChampionsAndRoles.class));
		
		return ret;
	}
	
	private boolean generalizeChampionPositions() {
		for(ChampionsAndRoles car : champRoles) {
			kieSession.insert(car);
		}
		
		int firedRules = kieSession.fireAllRules();
		kieSession.dispose();
		
		return firedRules > 0;
	}
	
	private void printChampNameAndPositions() {
		for(ChampionsAndRoles car : champRoles) {
			Champion champ = car.getChampion();
			if(champ != null) {
				String msg = champ.getName() + " is played ";
				
				if(car.getPlayedPositions() != null) {
					for(MapPositionsEnum position: car.getPlayedPositions()) {
						msg += position.toString() + ", ";
					}	
				}
				
				System.out.println(msg);
			}
		}
	}

	private boolean latestVersionGreaterThanCurrent(String[] current, String[] latest) {
		boolean shouldUpdate = latest.length > current.length || 
				   				latest.length < current.length;
		
		if(!shouldUpdate && current.length == latest.length) {
			for(int i = 0; i < current.length; i++) {
				long cvf = Long.parseLong(current[i]);
				long lvf = Long.parseLong(latest[i]);
				
				if(lvf > cvf) {
					shouldUpdate = true;
					break;
				}
			}
		}
		
		return shouldUpdate;
	}

	public Champion getChampionDataByKey(String champKey) {
		return champKey == null ? null : championData.getData().get(champKey);
	}
	
	public Champion getChampionDataById(Integer champKey) {
		Champion toRet = null;
		
		if(champKey != null) {
			for(ChampionsAndRoles info : champRoles) {
				if(info.getChampion().getId() == champKey) {
					toRet = info.getChampion();
					break;
				}
			}
		}
		
		return toRet;
	}

	public List<ChampionsAndRoles> getChampRoles() {
		return champRoles;
	}

	public void setChampRoles(List<ChampionsAndRoles> champRoles) {
		this.champRoles = champRoles;
	}
	
}
