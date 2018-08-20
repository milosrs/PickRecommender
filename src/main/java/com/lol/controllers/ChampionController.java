package com.lol.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lol.dataBeans.ChampionInfoBean;
import com.lol.dataBeans.SummonerSpellBean;
import com.lol.model.champions.Champion;
import com.lol.model.champions.ChampionListDto;
import com.lol.model.recommendation.GenerateResult;
import com.lol.model.summoner.SummonerDto;
import com.lol.model.summonerSpells.SummonerSpellDto;
import com.lol.model.viewModel.ChampionPicksViewModel;
import com.lol.model.viewModel.ChampionViewModel;
import com.lol.model.viewModel.GenerateResultViewModel;
import com.lol.model.viewModel.SummonerSpellViewModel;
import com.lol.requestSender.SummonerServiceRequestSender;
import com.lol.security.JWTTokenUtil;
import com.lol.service.ChampionService;
import com.lol.service.SummonerService;

@RestController
@RequestMapping("/champions")
public class ChampionController {

	@Autowired
	private ChampionService championService;
	
	@Autowired
	private ChampionInfoBean champInfoBean;
	
	@Autowired
	private SummonerSpellBean spellBean;
	
	@Autowired
	private SummonerServiceRequestSender summmonerRequestSender;
	
	@Autowired
	private SummonerService summonerService;
	
	@Autowired
	private JWTTokenUtil jwtTokenUtil;
	
	@GetMapping("/forList")
	public ResponseEntity<?> getChampionInfoForList(@RequestHeader("Authorization") String token) {
		try {
			token = token.substring(7);
			ChampionListDto list = championService.getAllForList(token);
			List<ChampionViewModel> ret = championService.convertListToViewModel(list);
			
			return ResponseEntity.ok(ret);
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching champion info.");
		}
	}
	
	@GetMapping("/champion/{id}")
	public ResponseEntity<?> getChampionInfo(@PathParam(value="id")String championId,
											 @RequestHeader("Authorization") String token) {
		try {
			token = token.substring(7);
			Champion champ = championService.getOneFullInfo(championId, token);
			ChampionViewModel ret = championService.convertChampionToViewModel(champ);
			
			return ResponseEntity.ok(ret);
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching champion info.");
		}
	}
	
	@GetMapping(value="/version", produces="text/plain")
	public ResponseEntity<?> getVersion(@RequestHeader("Authorization") String token) {
		return ResponseEntity.ok(champInfoBean.getChampionData().getVersion());
	}
	
	@PostMapping("/generate")
	public ResponseEntity<?> generateRecommendedChampions(@RequestBody ChampionPicksViewModel picks,
														  @RequestHeader("Authorization") String token) throws IOException {
		token = token.substring(7);
		SummonerDto summoner = summmonerRequestSender.sendRequest(summonerService.getByUsername(jwtTokenUtil.getUsernameFromToken(token)));
		GenerateResult recommendations = championService.generateRecommendations(picks, summoner);
		GenerateResultViewModel ret = new GenerateResultViewModel();
		
		for(Champion c : recommendations.getChampRecommendations()) {
			ChampionViewModel toAdd = championService.convertChampionToViewModel(c);
			ret.getChampRecommendations().add(toAdd);
		}
		
		for(SummonerSpellDto s : recommendations.getSpellRecommendations()) {
			SummonerSpellViewModel toAdd = spellBean.convertToViewModel(s);
			ret.getSpellRecommendations().add(toAdd);
		}
		
		return ResponseEntity.ok(ret);
	}
	
	
}
