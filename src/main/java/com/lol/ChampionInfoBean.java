package com.lol;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lol.model.champions.Champion;
import com.lol.model.champions.ChampionListDto;
import com.lol.requestSender.ChampionRequestSender;

@Component
public class ChampionInfoBean extends TimerTask implements InitializingBean{

	@Autowired
	private ChampionRequestSender requestSender;
	private ChampionListDto championData;
	private final String realm = "eun1";
	private long daily = 1000*60*60*24;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		championData = requestSender.sendRequest(realm);
		Timer timer = new Timer();
		timer.schedule(this, 0, daily);
	}

	public ChampionRequestSender getRequestSender() {
		return requestSender;
	}

	public void setRequestSender(ChampionRequestSender requestSender) {
		this.requestSender = requestSender;
	}

	public ChampionListDto getChampionData() {
		return championData;
	}

	public void setChampionData(ChampionListDto championData) {
		this.championData = championData;
	}

	@Override
	public void run() {
		String currentVersion = championData.getVersion();
		String[] currentVersionFragments = currentVersion.split(".");
		String latestVersion = null;
		String[] latestVersionFragments = null;
		
		try {
			latestVersion = requestSender.getLatestVersion(realm);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		latestVersionFragments = latestVersion.split(".");
		
		if(latestVersionGreaterThanCurrent(currentVersionFragments, latestVersionFragments)) {
			try {
				championData = requestSender.sendRequest(realm);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private boolean latestVersionGreaterThanCurrent(String[] current, String[] latest) {
		boolean shouldUpdate = latest.length > current.length || 
				   				latest.length < current.length;
		
		if(!shouldUpdate && current.length == latest.length) {
			for(int i = 0; i < current.length; i++) {
				long cvf = Long.parseLong(current[i]);
				long lvf = Long.parseLong(latest[i]);
				
				if(lvf > cvf) {
					shouldUpdate = true;
					break;
				}
			}
		}
		
		return shouldUpdate;
	}

	public Champion getChampionDataByKey(String champKey) {
		return championData.getData().get(champKey);
	}
}
