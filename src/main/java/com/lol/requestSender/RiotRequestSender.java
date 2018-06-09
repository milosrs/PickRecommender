package com.lol.requestSender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lol.requestSender.URLBuilder.URLBuilder;

@Service
public abstract class RiotRequestSender<S, R> {
	@Autowired
	protected URLBuilder urlBuilder;
	@Autowired
	protected ObjectMapper mapper;

	protected R responseObject;
	
	private final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.79 Safari/537.36";
	private final String RIOT_API_KEY = "api_key=RGAPI-b153cc56-22c1-4b52-9573-2697c6de3fe4";

	public String sendGET(String fullUrl) throws IOException {
		URL obj = new URL(fullUrl);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("X-Riot-Token", RIOT_API_KEY);
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
				System.out.println(inputLine);
			}
			in.close();

			// print result
			return response.toString();
		} else {
			System.out.println("GET request not worked");
			return null;
		}
	}
	
	public abstract R sendRequest(S sendInfo) throws IOException;
	
	protected abstract R convertToEntity(String json) throws JsonParseException, JsonMappingException, IOException;
}
