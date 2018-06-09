package com.lol.requestSender.URLBuilder;

import org.springframework.stereotype.Service;

@Service
public class URLBuilder {
	private String realm;
	private String riotApiURL = "https://{0}.api.riotgames.com/lol/{1}/v3/{2}/";
	
	public URLBuilder() {
		
	}

	public String buildUrl(String realm, RequestUrl url, String optionalParameter) {
		String serviceUrl[] = url.url().split("/");
		this.realm = realm;
		this.riotApiURL = this.riotApiURL.replace("{0}", realm)
										 .replace("{1}", serviceUrl[0])
										 .replace("{2}", serviceUrl[1]) + "/" + serviceUrl[2];
		
		if(optionalParameter != null) {
			riotApiURL += optionalParameter;
		}
		
		System.out.println("Built a riotApiUrl: " + riotApiURL);
		return riotApiURL;
	}
	
	public String getRealm() {
		return realm;
	}

	public void setRealm(String realm) {
		this.realm = realm;
	}

	public String getRiotApiURL() {
		return riotApiURL;
	}
}
