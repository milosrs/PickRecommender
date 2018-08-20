package com.lol.requestSender;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.lol.model.reforgedRunes.ReforgedRunePathDto;
import com.lol.requestSender.URLBuilder.RequestUrl;

@Service
public class RunesRequestSender extends RiotRequestSender<String, List<ReforgedRunePathDto>>{

	@Override
	public List<ReforgedRunePathDto> sendRequest(String realm) throws IOException {
		String fullUrl = urlBuilder.buildUrl(realm, RequestUrl.RUNE_PATHS, null);
		String json = sendGET(fullUrl);
		
		return convertToEntity(json);
	}

	@Override
	protected List<ReforgedRunePathDto> convertToEntity(String json)
			throws JsonParseException, JsonMappingException, IOException {
		if(json == null) {
			throw new NullPointerException("Unable to convert to Runes Path. JSON is null.");
		}
		return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, ReforgedRunePathDto.class));
	}

}
