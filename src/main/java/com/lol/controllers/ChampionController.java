package com.lol.controllers;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lol.model.champions.Champion;
import com.lol.model.champions.ChampionListDto;
import com.lol.service.ChampionService;

@RestController
@RequestMapping("/champions")
public class ChampionController {

	@Autowired
	private ChampionService championService;
	
	@GetMapping("/forList")
	public ResponseEntity<?> getChampionInfoForList(@RequestHeader("Bearer") String token) {
		try {
			ChampionListDto list = championService.getAllForList(token);
			
			return ResponseEntity.ok(list);
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching champion info.");
		}
	}
	
	@GetMapping("/champion/{id}")
	public ResponseEntity<?> getChampionInfo(@PathParam(value="id")String championId,
											 @RequestHeader("Bearer") String token) {
		try {
			Champion champ = championService.getOneFullInfo(championId, token);
			
			return ResponseEntity.ok(champ);
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching champion info.");
		}
	}
}
