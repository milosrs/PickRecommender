package com.lol.model;

import com.lol.model.champions.Champion;

import java.util.List;

public class ChampionPicks {

	private List<Champion> friendlyTeam;
	private List<Champion> opponentTeam;
	
	public ChampionPicks(List<Champion> friendlyTeam, List<Champion> opponentTeam) {
		super();
		this.friendlyTeam = friendlyTeam;
		this.opponentTeam = opponentTeam;
	}
	
	public List<Champion> getFriendlyTeam() {
		return friendlyTeam;
	}
	public void setFriendlyTeam(List<Champion> friendlyTeam) {
		this.friendlyTeam = friendlyTeam;
	}
	public List<Champion> getOpponentTeam() {
		return opponentTeam;
	}
	public void setOpponentTeam(List<Champion> opponentTeam) {
		this.opponentTeam = opponentTeam;
	}
	
	
}
