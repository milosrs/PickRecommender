package com.lol.requestSender;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.lol.model.reforgedRunes.ReforgedRunePathDto;
import com.lol.requestSender.URLBuilder.RequestUrl;

@Service
public class RunesRequestSender extends RiotRequestSender<String, ReforgedRunePathDto>{

	@Override
	public ReforgedRunePathDto sendRequest(String realm) throws IOException {
		String fullUrl = urlBuilder.buildUrl(realm, RequestUrl.RUNE_PATHS, null);
		String json = sendGET(fullUrl);
		
		return convertToEntity(json);
	}

	@Override
	protected ReforgedRunePathDto convertToEntity(String json)
			throws JsonParseException, JsonMappingException, IOException {
		if(json == null) {
			throw new NullPointerException("Unable to convert to Runes Path. JSON is null.");
		}
		return mapper.readValue(json, ReforgedRunePathDto.class);
	}

}
