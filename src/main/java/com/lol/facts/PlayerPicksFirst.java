package com.lol.facts;

import java.util.List;

import com.lol.model.champions.ChampionsAndRoles;

public class PlayerPicksFirst {
	private List<ChampionsAndRoles> champs;

	public PlayerPicksFirst() {
		super();
	}

	public PlayerPicksFirst(List<ChampionsAndRoles> champs) {
		super();
		this.champs = champs;
	}

	public List<ChampionsAndRoles> getChamps() {
		return champs;
	}

	public void setChamps(List<ChampionsAndRoles> champs) {
		this.champs = champs;
	}
	
}
