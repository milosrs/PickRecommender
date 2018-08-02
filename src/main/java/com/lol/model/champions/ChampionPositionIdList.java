package com.lol.model.champions;

import java.util.List;

public class ChampionPositionIdList {
	private List<String> friendlyTeamIds;
	private List<String> opponentTeamIds;

	public ChampionPositionIdList() {
		super();
	}

	public ChampionPositionIdList(List<String> friendlyTeamId, List<String> opponentTeamId) {
		super();
		this.friendlyTeamIds = friendlyTeamId;
		this.opponentTeamIds = opponentTeamId;
	}

	public List<String> getFriendlyTeamIds() {
		return friendlyTeamIds;
	}

	public void setFriendlyTeamIds(List<String> friendlyTeamId) {
		this.friendlyTeamIds = friendlyTeamId;
	}

	public List<String> getOpponentTeamIds() {
		return opponentTeamIds;
	}

	public void setOpponentTeamIds(List<String> opponentTeamId) {
		this.opponentTeamIds = opponentTeamId;
	}
}
