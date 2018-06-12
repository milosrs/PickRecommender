package com.lol.security;

import java.io.Serializable;
import java.util.List;

import com.lol.model.summoner.SummonerDto;

public class JWTAuthenticationResponse implements Serializable {

	private static final long serialVersionUID = 1L;
    private final String token;
    private String username;
    private String realm;

    public JWTAuthenticationResponse(String token, String username, String realm) {
        this.token = token;
        this.username = username;
        this.realm = realm;
    }

    public String getToken() {
        return this.token;
    }
    
    public String getUsername() {
    	return this.username;
    }
    
    public String getRealm() {
    	return this.realm;
    }
}
