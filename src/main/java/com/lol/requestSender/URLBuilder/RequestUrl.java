package com.lol.requestSender.URLBuilder;

public enum RequestUrl {
	SUMMONERS_BY_NAME("summoner/summoners/by-name/"),
	SUMMONERS_BY_ID("summoner/summoners/by-id/"),
	CHAMPIONS_ALL_FOR_SHOW("static-data/champions?locale=en_US&champListData=keys&tags=all&dataById=false"),
	CHAMPIONS_BY_ID("static-data/champions/"),
	VERSIONS("static-data/versions/"),
	MASTERY_BY_SUMMONER("champion-mastery/v3/champion-masteries/by-summoner/");
	
	private String url;
	
	RequestUrl(String url) {
		this.url = url;
	}
	
	public String url() {
        return url;
    }
}
