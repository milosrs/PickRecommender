package com.lol.dataBeans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;

import javax.annotation.PostConstruct;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lol.model.MapPositionsEnum;
import com.lol.model.champions.Champion;
import com.lol.model.champions.ChampionListDto;
import com.lol.model.champions.ChampionsAndRoles;
import com.lol.requestSender.ChampionRequestSender;

@Component
public class ChampionInfoBean implements Observer {
	@Autowired
	private ChampionRequestSender requestSender;
	@Autowired
	private KieSession kieSession;
	@Autowired
	private ObjectMapper objectMapper;
	private ChampionListDto championData;
	private List<ChampionsAndRoles> champRoles;
	private final String realm = "eun1";
	private final String filePath = "./apiData/champions.json";
	
	@PostConstruct
	public void construction() {
		try {
			readFile();
			this.champRoles = readChampionsRoles();
			championInfoIntoList();
			generalizeChampionPositions();
			printChampNameAndPositions();
		} catch (Exception e) {
			System.out.println("Error while reading JSON file.");
			e.printStackTrace();
		}
	}
	
	private void readFile() throws JsonParseException, JsonMappingException, IOException{
		File f = new File(filePath);

		if (!f.exists()) {
			championData = requestSender.sendRequest(realm);
			objectMapper.writeValue(f, championData);
		} else {
			championData = objectMapper.readValue(f, ChampionListDto.class);
		}
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
		File file = new File("./apiData/championClasses.json");
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

	@Override
	public void update(Observable o, Object arg) {
		File f = new File(filePath);
		
		try {
			championData = requestSender.sendRequest(realm);
			objectMapper.writeValue(f, championData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
