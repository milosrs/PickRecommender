package com.lol.dataBeans;

import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lol.model.champions.ChampionListDto;
import com.lol.model.summonerSpells.SummonerSpellDto;
import com.lol.model.summonerSpells.SummonerSpellListDto;
import com.lol.model.viewModel.SummonerSpellViewModel;
import com.lol.requestSender.SummonerSpellsRequestSender;

@Component
public class SummonerSpellBean implements Observer {

	@Autowired
	private SummonerSpellsRequestSender requestSender;
	@Autowired
	private ObjectMapper objectMapper;
	
	private SummonerSpellListDto summonerSpells;
	private final String realm = "eun1";
	private final String filePath = "./apiData/summonerSpells.json";
	
	public SummonerSpellListDto getSummonerSpells() {
		return summonerSpells;
	}

	public void setSummonerSpells(SummonerSpellListDto summonerSpells) {
		this.summonerSpells = summonerSpells;
	}
	
	@PostConstruct
	public void construction() {
		try {
			readFile();
		} catch (Exception e) {
			System.out.println("Error while reading JSON file.");
			e.printStackTrace();
		}
	}
	
	private void readFile() throws JsonParseException, JsonMappingException, IOException{
		File f = new File(filePath);

		if (!f.exists()) {
			summonerSpells = requestSender.sendRequest(realm);
			objectMapper.writeValue(f, summonerSpells);
		} else {
			summonerSpells = objectMapper.readValue(f, SummonerSpellListDto.class);
		}
	}
	
	public SummonerSpellViewModel convertToViewModel(SummonerSpellDto spell) {
		SummonerSpellViewModel ret = new SummonerSpellViewModel();
		ret.setDescription(spell.getDescription());
		ret.setName(spell.getName());
		ret.setFullImagePath(spell.getImage().getFull());
		
		return ret;
	}

	@Override
	public void update(Observable o, Object arg) {
		File f = new File(filePath);
		
		try {
			summonerSpells = requestSender.sendRequest(realm);
			objectMapper.writeValue(f, summonerSpells);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
