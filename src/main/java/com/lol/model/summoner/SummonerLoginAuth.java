package com.lol.model.summoner;

import javax.persistence.Entity;
import javax.persistence.Id;

public class SummonerLoginAuth {
	private String username;
	private String password;
	
	public SummonerLoginAuth() {
		super();
	}
	public SummonerLoginAuth(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
