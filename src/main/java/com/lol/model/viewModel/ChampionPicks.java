package com.lol.model.viewModel;

import com.lol.model.champions.Champion;

import java.util.List;
import java.util.Map;

public class ChampionPicks {

	private Map<String, Integer> friendlyTeam;
	private List<Integer> opponentTeam;
	private String playerPosition;
	private String firstPick;
	
	public ChampionPicks() {
		super();
	}

	public ChampionPicks(Map<String, Integer> friendlyTeam, List<Integer> opponentTeam, String playerPosition,
			String firstPick) {
		super();
		this.friendlyTeam = friendlyTeam;
		this.opponentTeam = opponentTeam;
		this.playerPosition = playerPosition;
		this.firstPick = firstPick;
	}

	public Map<String, Integer> getFriendlyTeam() {
		return friendlyTeam;
	}

	public void setFriendlyTeam(Map<String, Integer> friendlyTeam) {
		this.friendlyTeam = friendlyTeam;
	}

	public List<Integer> getOpponentTeam() {
		return opponentTeam;
	}

	public void setOpponentTeam(List<Integer> opponentTeam) {
		this.opponentTeam = opponentTeam;
	}

	public String getPlayerPosition() {
		return playerPosition;
	}

	public void setPlayerPosition(String playerPosition) {
		this.playerPosition = playerPosition;
	}

	public String getFirstPick() {
		return firstPick;
	}

	public void setFirstPick(String firstPick) {
		this.firstPick = firstPick;
	}
}
