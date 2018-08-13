package com.lol.model.championMastery;

public class ChampionMasteryDto {
	private boolean chestGranted; 	//Is chest granted for this champion or not in current season.
	private int championLevel; 	  	//Champion level for specified player and champion combination.
	private int championPoints;		//Total number of champion points for this player and champion combination - they are used to determine championLevel.
	private long championId; 		//Champion ID for this entry.
	private long playerId;			//Player ID for this entry.
	private long championPointsUntilNextLevel;	//Number of points needed to achieve next level. Zero if player reached maximum champion level for this champion.
	private int tokensEarned;		//The token earned for this champion to levelup.
	private long championPointsSinceLastLevel;		//Number of points earned since current level has been achieved.
	private long lastPlayTime;		//Last time this champion was played by this player - in Unix milliseconds time format.
	
	
	public ChampionMasteryDto() {
		super();
	}
	public ChampionMasteryDto(boolean chestGranted, int championLevel, int championPoints, long championId,
			long playerId, long championPointsUntilNextLevel, int tokensEarned, long championPointsSinceLastLevel,
			long lastPlayTime) {
		super();
		this.chestGranted = chestGranted;
		this.championLevel = championLevel;
		this.championPoints = championPoints;
		this.championId = championId;
		this.playerId = playerId;
		this.championPointsUntilNextLevel = championPointsUntilNextLevel;
		this.tokensEarned = tokensEarned;
		this.championPointsSinceLastLevel = championPointsSinceLastLevel;
		this.lastPlayTime = lastPlayTime;
	}
	public boolean isChestGranted() {
		return chestGranted;
	}
	public void setChestGranted(boolean chestGranted) {
		this.chestGranted = chestGranted;
	}
	public int getChampionLevel() {
		return championLevel;
	}
	public void setChampionLevel(int championLevel) {
		this.championLevel = championLevel;
	}
	public int getChampionPoints() {
		return championPoints;
	}
	public void setChampionPoints(int championPoints) {
		this.championPoints = championPoints;
	}
	public long getChampionId() {
		return championId;
	}
	public void setChampionId(long championId) {
		this.championId = championId;
	}
	public long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}
	public long getChampionPointsUntilNextLevel() {
		return championPointsUntilNextLevel;
	}
	public void setChampionPointsUntilNextLevel(long championPointsUntilNextLevel) {
		this.championPointsUntilNextLevel = championPointsUntilNextLevel;
	}
	public int getTokensEarned() {
		return tokensEarned;
	}
	public void setTokensEarned(int tokensEarned) {
		this.tokensEarned = tokensEarned;
	}
	public long getChampionPointsSinceLastLevel() {
		return championPointsSinceLastLevel;
	}
	public void setChampionPointsSinceLastLevel(long championPointsSinceLastLevel) {
		this.championPointsSinceLastLevel = championPointsSinceLastLevel;
	}
	public long getLastPlayTime() {
		return lastPlayTime;
	}
	public void setLastPlayTime(long lastPlayTime) {
		this.lastPlayTime = lastPlayTime;
	}
}
