package com.lol.repository;

import java.io.IOException;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lol.model.summoner.SummonerLoginAuth;
import com.lol.model.summoner.SummonerAuth;
import com.lol.model.summoner.SummonerDto;
import com.lol.requestSender.SummonerServiceRequestSender;
import com.mysql.fabric.Response;

@Repository
@Transactional
public class SummonerDao extends AbstractDao<SummonerAuth, String> implements ISummonerDao{
	
	@Autowired
	private SummonerServiceRequestSender requestSender;
	
	public SummonerDao(SessionFactory sf, String rootObjID) {
		super(sf);
		super.setRootObjID("Username");
	}
	
	public SummonerAuth register(SummonerAuth registerInfo) throws IOException {
		SummonerAuth dbObj = null;
		SummonerDto info = requestSender.sendRequest(registerInfo);
		
		if(this.get(registerInfo.getUsername()) == null && info != null) {
			dbObj = this.insert(registerInfo);
		}
		
		return dbObj;
	}
	
	public SummonerDto login(SummonerLoginAuth loginInfo) throws IOException {
		SummonerDto ret = null;
		SummonerAuth auth = this.get(loginInfo.getUsername());
		
		if(auth != null) {
			boolean isValid = loginInfo.getPassword().equals(auth.getPassword());
			
			if(isValid) {
				ret = requestSender.sendRequest(auth);
			}
		}
		
		return ret;
	}
}
