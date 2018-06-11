package com.lol.requestSender.URLBuilder;

import org.springframework.stereotype.Component;

@Component
public class URLBuilder {
	private String realm;
	private final String riotApiURL = "https://{0}.api.riotgames.com/lol/{1}/v3/{2}/";
	
	public URLBuilder() {
		
	}

	public String buildUrl(String realm, RequestUrl url, String optionalParameter) {
		String urlCpy = riotApiURL;
		String serviceUrl[] = url.url().split("/");
		this.realm = realm;
		urlCpy = this.riotApiURL.replace("{0}", realm)
										 .replace("{1}", serviceUrl[0])
										 .replace("{2}", serviceUrl[1]) + serviceUrl[2];
		
		if(optionalParameter != null) {
			urlCpy += "/" + optionalParameter;
		}
		
		System.out.println("Built a riotApiUrl: " + urlCpy);
		return urlCpy;
	}
	
	public String getRealm() {
		return realm;
	}

	public void setRealm(String realm) {
		this.realm = realm;
	}
}
