package com.lol.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.lol.model.summoner.SummonerAuth;
import com.lol.model.summoner.SummonerDto;
import com.lol.model.summoner.SummonerLoginAuth;
import com.lol.security.JWTAuthenticationRequest;
import com.lol.security.JWTAuthenticationResponse;

@Service
public interface SummonerService {

	public SummonerAuth save(SummonerAuth auth);
	
	public SummonerAuth getByUsername(String username);
	
	public SummonerAuth register(SummonerAuth auth) throws IOException;
	
	public JWTAuthenticationResponse login(JWTAuthenticationRequest authRequest) throws IOException;
}
