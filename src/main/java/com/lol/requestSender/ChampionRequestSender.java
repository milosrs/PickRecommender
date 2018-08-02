package com.lol.requestSender;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.lol.model.champions.Champion;
import com.lol.model.champions.ChampionListDto;
import com.lol.requestSender.URLBuilder.RequestUrl;

@Service
public class ChampionRequestSender extends RiotRequestSender<String, ChampionListDto>{

	@Override
	public ChampionListDto sendRequest(String realm) throws IOException {
		String fullUrl = this.urlBuilder.buildUrl(realm, RequestUrl.CHAMPIONS_ALL_FOR_SHOW, null);
		String resultString = this.sendGET(fullUrl);
		ChampionListDto result = convertToEntity(resultString);
		
		return result;
	}
	
	public Champion sendRequest(String id, String realm) throws IOException {
		String fullUrl = this.urlBuilder.buildUrl(realm, RequestUrl.CHAMPIONS_BY_ID, id);
		String resultString = this.sendGET(fullUrl);
		Champion result = convertToChampEntity(resultString);
		
		return result;
	}
	
	public String getLatestVersion(String realm) throws IOException {
		String fullUrl = this.urlBuilder.buildUrl(realm, RequestUrl.VERSIONS, null);
		String resultString = this.sendGET(fullUrl);
		String latestVersion = convertToLatestVersion(resultString);
		
		return latestVersion;
	}

	@Override
	protected ChampionListDto convertToEntity(String json)
			throws JsonParseException, JsonMappingException, IOException {
		ChampionListDto ret = mapper.readValue(json, ChampionListDto.class);
		
		return ret;
	}
	
	protected Champion convertToChampEntity(String json) throws JsonParseException, JsonMappingException, IOException {
		Champion ret = mapper.readValue(json, Champion.class);
		
		return ret;
	}
	
	protected String convertToLatestVersion(String json) throws JsonParseException, JsonMappingException, IOException {
		List<String> versions = mapper.readValue(json, new TypeReference<List<String>>(){});
		String latest = versions.get(0);
		
		return latest;
	}
}
