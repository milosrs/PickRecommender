package com.lol.security;

import java.io.Serializable;
import java.util.List;

import com.lol.model.summoner.SummonerDto;

public class JWTAuthenticationResponse implements Serializable {

	private static final long serialVersionUID = 1L;
    private final String token;
    private final SummonerDto dto;

    public JWTAuthenticationResponse(String token, SummonerDto dto) {
        this.token = token;
        this.dto = dto;
    }

    public String getToken() {
        return this.token;
    }
    
    public SummonerDto getDto() {
    	return this.dto;
    }
}
