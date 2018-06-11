package com.lol.requestSender.URLBuilder;

public enum RequestUrl {
	SUMMONERS_BY_NAME("summoner/summoners/by-name/"),
	SUMMONERS_BY_ID("summoner/summoners/by-id/");
	
	private String url;
	
	RequestUrl(String url) {
		this.url = url;
	}
	
	public String url() {
        return url;
    }
}
