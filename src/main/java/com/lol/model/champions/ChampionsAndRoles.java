package com.lol.model.champions;

import java.util.List;

import com.lol.model.ChampionRolesEnum;
import com.lol.model.MapPositionsEnum;

public class ChampionsAndRoles {
	private String name;
	private Champion champion;
	private List<ChampionRolesEnum> mainRole;
	private List<ChampionRolesEnum> subroles;
	private List<MapPositionsEnum> playedPositions;
	
	public ChampionsAndRoles(Champion champion, List<ChampionRolesEnum> mainRole, List<ChampionRolesEnum> subroles, List<MapPositionsEnum> playedPositions, String name) {
		super();
		this.champion = champion;
		this.mainRole = mainRole;
		this.subroles = subroles;
		this.playedPositions = playedPositions;
		this.name = name;
	}

	public ChampionsAndRoles() {
		super();
	}

	public Champion getChampion() {
		return champion;
	}

	public void setChampion(Champion champion) {
		this.champion = champion;
	}

	public List<ChampionRolesEnum> getMainRole() {
		return mainRole;
	}

	public void setMainRole(List<ChampionRolesEnum> mainRole) {
		this.mainRole = mainRole;
	}

	public List<ChampionRolesEnum> getSubroles() {
		return subroles;
	}

	public void setSubroles(List<ChampionRolesEnum> subroles) {
		this.subroles = subroles;
	}

	public List<MapPositionsEnum> getPlayedPositions() {
		return playedPositions;
	}

	public void setPlayedPositions(List<MapPositionsEnum> playedPositions) {
		this.playedPositions = playedPositions;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
