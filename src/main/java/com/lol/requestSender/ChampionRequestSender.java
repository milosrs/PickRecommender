package com.lol.requestSender;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
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
}
