package com.lol.requestSender;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.lol.model.championMastery.ChampionMasteryDto;
import com.lol.requestSender.URLBuilder.RequestUrl;
import java.util.List;

@Service
public class ChampionMasteryRequestSender extends RiotRequestSender<Long, List<ChampionMasteryDto>> {

	@Override
	public List<ChampionMasteryDto> sendRequest(Long sendInfo) throws IOException {
		String fullUrl = this.urlBuilder.buildUrl(this.urlBuilder.getRealm(), RequestUrl.MASTERY_BY_SUMMONER, sendInfo.toString());
		String resultString = this.sendGET(fullUrl);
		ChampionMasteryDto ret = convertToEntity(resultString);
		
		return ret;
	}

	@Override
	protected List<ChampionMasteryDto> convertToEntity(String json)
			throws JsonParseException, JsonMappingException, IOException {
		if(json == null) {
			throw new NullPointerException();
		}
		List<ChampionMasteryDto> ret = mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, ChampionMasteryDto.class));
		return ret;
	}

	public void setRealm(String realm) {
		this.urlBuilder.setRealm(realm);
	}
}
