package com.lol.requestSender;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.lol.model.summoner.SummonerAuth;
import com.lol.model.summoner.SummonerDto;
import com.lol.requestSender.URLBuilder.RequestUrl;
import com.lol.requestSender.URLBuilder.URLBuilder;

@Service
public class SummonerServiceRequestSender extends RiotRequestSender<SummonerAuth, SummonerDto> {
	
	@Override
	public SummonerDto sendRequest(SummonerAuth sendInfo) throws IOException {
		String fullUrl = this.urlBuilder.buildUrl(sendInfo.getRealm(), RequestUrl.SUMMONERS_BY_NAME, sendInfo.getUsername());
		String resultString = this.sendGET(fullUrl);
		SummonerDto result = convertToEntity(resultString);
		return result;
	}
	
	@Override
	protected SummonerDto convertToEntity(String json) throws JsonParseException, JsonMappingException, IOException {
		if(json == null) {
			throw new NullPointerException();
		}
		SummonerDto ret = mapper.readValue(json, SummonerDto.class);
		return ret;
	}
}
