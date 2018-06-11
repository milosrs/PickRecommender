package com.lol.requestSender.URLBuilder;

public enum RequestUrl {
	SUMMONERS_BY_NAME("summoner/summoners/by-name/"),
	SUMMONERS_BY_ID("summoner/summoners/by-id/"),
	CHAMPIONS_ALL_FOR_SHOW("static-data/champions?locale=en_US&champListData=keys&tags=format&tags=image&dataById=false"),
	CHAMPIONS_BY_ID("static-data/champions/");
	
	private String url;
	
	RequestUrl(String url) {
		this.url = url;
	}
	
	public String url() {
        return url;
    }
}
