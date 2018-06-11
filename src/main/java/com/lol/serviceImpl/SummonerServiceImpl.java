package com.lol.serviceImpl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.lol.model.summoner.SummonerAuth;
import com.lol.model.summoner.SummonerDto;
import com.lol.model.summoner.SummonerLoginAuth;
import com.lol.repository.SummonerRepository;
import com.lol.requestSender.SummonerServiceRequestSender;
import com.lol.service.SummonerService;

public class SummonerServiceImpl implements SummonerService {

	@Autowired
	private SummonerRepository summonerRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private SummonerServiceRequestSender requestSender;
	
	@Override
	public SummonerAuth save(SummonerAuth auth) {
		return summonerRepository.save(auth);
	}

	@Override
	public SummonerAuth getByUsername(String username) {
		return summonerRepository.getByUsername(username);
	}

	@Override
	public SummonerAuth register(SummonerAuth auth) throws IOException {
		SummonerAuth dbObj = null;
		SummonerDto info = requestSender.sendRequest(auth);
		
		if(summonerRepository.getByUsername(auth.getUsername()) != null && info != null) {
			dbObj = summonerRepository.save(auth);
		}
		
		return dbObj;
	}

	@Override
	public SummonerDto login(SummonerLoginAuth loginInfo) throws IOException {
		SummonerDto ret = null;
		SummonerAuth auth = summonerRepository.getByUsername(loginInfo.getUsername());
		
		if(auth != null) {
			boolean isValid = loginInfo.getPassword().equals(auth.getPassword());
			
			if(isValid) {
				ret = requestSender.sendRequest(auth);
			}
		}
		
		return ret;
	}

}
