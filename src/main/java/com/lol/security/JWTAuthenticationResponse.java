package com.lol.security;

import java.io.Serializable;
import java.util.List;

import com.lol.model.summoner.SummonerDto;

public class JWTAuthenticationResponse implements Serializable {

	private static final long serialVersionUID = 1L;
    private final String token;
    private String username;
    private String realm;
    private Long id;

    public JWTAuthenticationResponse(String token, String username, String realm, Long id) {
        this.token = token;
        this.username = username;
        this.realm = realm;
        this.id = id;
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

	public Long getId() {
		return id;
	}
}
