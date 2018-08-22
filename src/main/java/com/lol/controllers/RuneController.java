package com.lol.controllers;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.lol.dataBeans.RunesBean;
import com.lol.model.recommendation.RuneRecommendation;
import com.lol.model.viewModel.RuneRecommendationViewModel;
import com.lol.security.JWTTokenUtil;
import com.lol.service.RuneService;

@RestController
@RequestMapping("/runes")
public class RuneController {

	@Autowired
	private RuneService runeService;
	
	@Autowired
	private RunesBean runesBean;
	
	@PostMapping("/{championId}")
	public ResponseEntity<?> getChampionInfoForList(@PathVariable("championId") Integer championId, @RequestBody String playerPosition) {
		RuneRecommendation recommendations = runeService.recommendRunes(championId, playerPosition);
		RuneRecommendationViewModel ret = runesBean.runesRecommendationToRunesRecommendationViewModel(recommendations);
		
		return ResponseEntity.ok(ret);
	}
}
