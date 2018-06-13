package com.lol.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import com.lol.model.PickTypes;
import com.lol.model.champions.Champion;
import com.lol.model.champions.ChampionListDto;
import com.lol.model.champions.ChampionPositionIdList;
import com.lol.service.ChampionService;

@RestController
@RequestMapping("/champions")
public class ChampionController {

	@Autowired
	private ChampionService championService;
	
	@GetMapping("/forList")
	public ResponseEntity<?> getChampionInfoForList(@RequestHeader("Authorization") String token) {
		try {
			token = token.substring(7);
			ChampionListDto list = championService.getAllForList(token);
			
			return ResponseEntity.ok(list);
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
			
			return ResponseEntity.ok(champ);
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching champion info.");
		}
	}
	
	@PostMapping("/generate")
	public ResponseEntity<?> generateRecommendedChampions(@RequestBody ChampionPositionIdList picks,
														  @RequestHeader("Authorization") String token) throws IOException {
		token = token.substring(7);
		List<Champion> friendlyChampions = new ArrayList<Champion>();
		List<Champion> enemyChampions = new ArrayList<Champion>();
		List<Champion> recommendations = null;
		
		for(String key : picks.getPicks().keySet()) {
			if(key == PickTypes.FRIEND.getType()) {
				addChampToList(friendlyChampions, picks.getPicks().get(key), token);
			} else if (key == PickTypes.OPPONENT.getType()) {
				addChampToList(enemyChampions, picks.getPicks().get(key), token);
			}
		}
		
		return ResponseEntity.ok(recommendations);
	}
	
	private void addChampToList(List<Champion> list, Map<String, Integer> positionIdMap, String userToken) throws IOException {
		for(String position : positionIdMap.keySet()) {
			Integer key = positionIdMap.get(position);
			
			list.add(championService.getOneFullInfo(key.toString(), userToken));		
		}
	}
}
