package com.lol.dataBeans;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lol.model.recommendation.RuneRecommendation;
import com.lol.model.reforgedRunes.ReforgedRuneDto;
import com.lol.model.reforgedRunes.ReforgedRunePathDto;
import com.lol.model.viewModel.ReforgedRuneViewModel;
import com.lol.model.viewModel.RuneRecommendationViewModel;
import com.lol.requestSender.RunesRequestSender;

@Component
public class RunesBean implements Observer{

	@Autowired
	private RunesRequestSender requestSender;
	@Autowired
	private ObjectMapper objectMapper;
	
	private List<ReforgedRunePathDto> runes;
	private final String realm = "eun1";
	private final String filePath = "./apiData/runesPath.json";

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
			runes = requestSender.sendRequest(realm);
			objectMapper.writeValue(f, runes);
		} else {
			runes = objectMapper.readValue(f, objectMapper.getTypeFactory().constructCollectionType(List.class, ReforgedRunePathDto.class));
		}
	}
	
	public RunesRequestSender getRequestSender() {
		return requestSender;
	}

	public void setRequestSender(RunesRequestSender requestSender) {
		this.requestSender = requestSender;
	}

	public List<ReforgedRunePathDto> getRunes() {
		return runes;
	}

	public void setRunes(List<ReforgedRunePathDto> runes) {
		this.runes = runes;
	}

	@Override
	public void update(Observable o, Object arg) {
		File f = new File(filePath);
		
		try {
			runes = requestSender.sendRequest(realm);
			objectMapper.writeValue(f, runes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public RuneRecommendationViewModel runesRecommendationToRunesRecommendationViewModel(RuneRecommendation recommendation) {
		RuneRecommendationViewModel ret = new RuneRecommendationViewModel();
		List<ReforgedRuneViewModel> primary = new ArrayList<ReforgedRuneViewModel>();
		List<ReforgedRuneViewModel> secondary = new ArrayList<ReforgedRuneViewModel>();
		
		ret.setPrimaryRunesPathName(recommendation.getPrimaryRunesPathName());
		ret.setSecondaryRunesPathName(recommendation.getSecondaryRunesPathName());
		
		for(ReforgedRuneDto r : recommendation.getPrimaryRunes()) {
			primary.add(runeToViewModel(r));
		}
		
		for(ReforgedRuneDto r : recommendation.getSecondaryRunes()) {
			secondary.add(runeToViewModel(r));
		}
		
		ret.setPrimaryRunes(primary);
		ret.setSecondaryRunes(secondary);
		
		return ret;
	}
	
	public ReforgedRuneViewModel runeToViewModel(ReforgedRuneDto rune) {
		ReforgedRuneViewModel ret = new ReforgedRuneViewModel();
		
		ret.setName(rune.getName());
		ret.setDescription(rune.getShortDesc());
		ret.setIcon(rune.getIcon());
		
		return ret;
	}
}
