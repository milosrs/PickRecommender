package com.lol.service;

import org.springframework.stereotype.Service;

import com.lol.model.recommendation.RuneRecommendation;

@Service
public interface RuneService {

	public RuneRecommendation recommendRunes(Integer championId, String playerPosition);
	
}
