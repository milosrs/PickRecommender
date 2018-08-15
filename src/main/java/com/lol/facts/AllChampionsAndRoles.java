package com.lol.facts;

import java.util.List;

import com.lol.model.champions.ChampionsAndRoles;

public class AllChampionsAndRoles {
	private List<ChampionsAndRoles> allChampions;

	public AllChampionsAndRoles(List<ChampionsAndRoles> allChampions) {
		super();
		this.allChampions = allChampions;
	}

	public List<ChampionsAndRoles> getAllChampions() {
		return allChampions;
	}

	public void setAllChampions(List<ChampionsAndRoles> allChampions) {
		this.allChampions = allChampions;
	}
}
