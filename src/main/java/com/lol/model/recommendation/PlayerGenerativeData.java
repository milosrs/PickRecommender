package com.lol.model.recommendation;

import java.util.List;
import java.util.Map;

import com.lol.model.MapPositionsEnum;
import com.lol.model.TeamTypesEnum;
import com.lol.model.champions.Champion;
import com.lol.model.champions.ChampionsAndRoles;

public class PlayerGenerativeData {
	private MapPositionsEnum[] friendlyTeamPositionOrder;
	private Map<MapPositionsEnum, ChampionsAndRoles> friendlyTeam;
	private List<ChampionsAndRoles> opponentTeam;
	private MapPositionsEnum playerPosition;
	private TeamTypesEnum firstPick;
	
	public PlayerGenerativeData() {
		super();
	}

	public PlayerGenerativeData(Map<MapPositionsEnum, ChampionsAndRoles> friendlyTeam, List<ChampionsAndRoles> opponentTeam,
			MapPositionsEnum playerPosition, TeamTypesEnum firstPick, MapPositionsEnum[] friendlyTeamPositionOrder) {
		super();
		this.friendlyTeam = friendlyTeam;
		this.opponentTeam = opponentTeam;
		this.playerPosition = playerPosition;
		this.firstPick = firstPick;
		this.friendlyTeamPositionOrder = friendlyTeamPositionOrder;
	}

	public Map<MapPositionsEnum, ChampionsAndRoles> getFriendlyTeam() {
		return friendlyTeam;
	}

	public void setFriendlyTeam(Map<MapPositionsEnum, ChampionsAndRoles> friendlyTeam) {
		this.friendlyTeam = friendlyTeam;
	}

	public List<ChampionsAndRoles> getOpponentTeam() {
		return opponentTeam;
	}

	public void setOpponentTeam(List<ChampionsAndRoles> opponentTeam) {
		this.opponentTeam = opponentTeam;
	}

	public MapPositionsEnum getPlayerPosition() {
		return playerPosition;
	}

	public void setPlayerPosition(MapPositionsEnum playerPosition) {
		this.playerPosition = playerPosition;
	}

	public TeamTypesEnum getFirstPick() {
		return firstPick;
	}

	public void setFirstPick(TeamTypesEnum firstPick) {
		this.firstPick = firstPick;
	}

	public MapPositionsEnum[] getFriendlyTeamPositionOrder() {
		return friendlyTeamPositionOrder;
	}

	public void setFriendlyTeamPositionOrder(MapPositionsEnum[] friendlyTeamPositionOrder) {
		this.friendlyTeamPositionOrder = friendlyTeamPositionOrder;
	}
}
