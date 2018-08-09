package com.lol.model.champions;

import java.util.List;

import com.lol.model.ChampionRolesEnum;

public class ChampionsAndRoles {
	private Champion champion;
	private List<ChampionRolesEnum> mainRole;
	private List<ChampionRolesEnum> subroles;
	
	public ChampionsAndRoles(Champion champion, List<ChampionRolesEnum> mainRole, List<ChampionRolesEnum> subroles) {
		super();
		this.champion = champion;
		this.mainRole = mainRole;
		this.subroles = subroles;
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
}
