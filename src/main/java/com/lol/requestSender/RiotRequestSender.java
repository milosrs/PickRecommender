package com.lol.requestSender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lol.requestSender.URLBuilder.RequestUrl;
import com.lol.requestSender.URLBuilder.URLBuilder;

@Service
public abstract class RiotRequestSender<SendInfo, ResponseObject> {
	@Autowired
	protected URLBuilder urlBuilder;
	@Autowired
	protected ObjectMapper mapper;

	protected ResponseObject responseObject;
	
	private final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.79 Safari/537.36";
	private final String RIOT_API_KEY = "RGAPI-b153cc56-22c1-4b52-9573-2697c6de3fe4";
	private final String ACCEPT_CHARSET = "application/x-www-form-urlencoded; charset=UTF-8";
	private final String ACCEPT_LANGUAGE = "en-US,en;q=0.9,sr;q=0.8";
	private final String ORIGIN = "https://developer.riotgames.com";

	public String sendGET(String fullUrl) throws IOException {
		URL obj = new URL(fullUrl);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("Origin", ORIGIN);
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("X-Riot-Token", RIOT_API_KEY);
		con.setRequestProperty("Accept-Charset", ACCEPT_CHARSET);
		con.setRequestProperty("Accept-Language", ACCEPT_LANGUAGE);
		
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			return response.toString();
		} else {
			System.out.println("GET request failed. Headers:");
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getErrorStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
				System.out.println(inputLine);
			}
			in.close();
			
			return null;
		}
	}
	
	public String getLatestVersion(String realm) throws IOException {
		String fullUrl = this.urlBuilder.buildUrl(realm, RequestUrl.VERSIONS, null);
		String resultString = this.sendGET(fullUrl);
		String latestVersion = convertToLatestVersion(resultString);
		
		return latestVersion;
	}
	
	protected String convertToLatestVersion(String json) throws JsonParseException, JsonMappingException, IOException {
		List<String> versions = mapper.readValue(json, new TypeReference<List<String>>(){});
		String latest = versions.get(0);
		
		return latest;
	}
	
	public abstract ResponseObject sendRequest(SendInfo sendInfo) throws IOException;
	
	protected abstract ResponseObject convertToEntity(String json) throws JsonParseException, JsonMappingException, IOException;
}
