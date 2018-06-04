package com.lol.model.summoner;

public class SummonerDto {
	private int profileIconId; 	//ID of the summoner icon associated with the summoner.
	private String name; 	   	//Summoner name.
	private Long summonerLevel;	//Summoner level associated with the summoner.
	private Long revisionDate; 	//Date summoner was last modified specified as epoch milliseconds. The following events will update this timestamp: profile icon change, playing the tutorial or advanced tutorial, finishing a game, summoner name change
	private Long id;			//Summoner ID.
	private Long accountId;		//Account ID.
	
	public SummonerDto(int profileIconId, String name, Long summonerLevel, Long revisionDate, Long id, Long accountId) {
		super();
		this.profileIconId = profileIconId;
		this.name = name;
		this.summonerLevel = summonerLevel;
		this.revisionDate = revisionDate;
		this.id = id;
		this.accountId = accountId;
	}
	public SummonerDto() {
		super();
	}
	public int getProfileIconId() {
		return profileIconId;
	}
	public void setProfileIconId(int profileIconId) {
		this.profileIconId = profileIconId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getSummonerLevel() {
		return summonerLevel;
	}
	public void setSummonerLevel(Long summonerLevel) {
		this.summonerLevel = summonerLevel;
	}
	public Long getRevisionDate() {
		return revisionDate;
	}
	public void setRevisionDate(Long revisionDate) {
		this.revisionDate = revisionDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
}
