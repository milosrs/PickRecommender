package com.lol.requestSender;

import java.io.IOException;

import org.mockito.exceptions.misusing.NullInsteadOfMockException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.lol.model.summonerSpells.SummonerSpellListDto;
import com.lol.requestSender.URLBuilder.RequestUrl;

@Service
public class SummonerSpellsRequestSender extends RiotRequestSender<String, SummonerSpellListDto> {

	@Override
	public SummonerSpellListDto sendRequest(String realm) throws IOException {
		String fullUrl = urlBuilder.buildUrl(realm, RequestUrl.SUMMONER_SPELLS, null);
		String resultString = sendGET(fullUrl);
		
		return convertToEntity(resultString);
	}

	@Override
	protected SummonerSpellListDto convertToEntity(String json)
			throws JsonParseException, JsonMappingException, IOException {
		if(json == null) {
			throw new NullInsteadOfMockException("Json is null. Can't convert to spell list.");
		}
		return mapper.readValue(json, SummonerSpellListDto.class);
	}

}
