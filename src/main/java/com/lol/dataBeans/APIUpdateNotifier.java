package com.lol.dataBeans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lol.requestSender.ChampionRequestSender;
import com.lol.requestSender.RiotRequestSender;

@Component
public class APIUpdateNotifier extends Observable {
	@Autowired
	protected ObjectMapper objectMapper;
	@Autowired
	protected ChampionRequestSender requestSender;
	@Autowired
	private ChampionInfoBean championBean;
	@Autowired
	private RunesBean runesBean;
	@Autowired
	private SummonerSpellBean spellsBean;
	
	private final String realm = "eun1";
	private final long daily = 1000*60*60*24;
	private final String filePath = "./apiData/version.txt";
	
	private String currentVersion;
	private TimerTask task;
	
	@PostConstruct
	public void construct() {
		try {
			readFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		task = new TimerTask() {
			@Override
			public void run() {
				if(shouldUpdate()) {
					notifyObservers();
				}
			}
		};
		
		this.addObserver(championBean);
		this.addObserver(runesBean);
		this.addObserver(spellsBean);
		startTask();
	}
	
	protected boolean shouldUpdate() {
		String[] currentVersionFragments = currentVersion.split(".");
		String latestVersion = null;
		String[] latestVersionFragments = null;
		
		try {
			latestVersion = requestSender.getLatestVersion(realm);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		latestVersionFragments = latestVersion.split(".");
		
		return latestVersionGreaterThanCurrent(currentVersionFragments, latestVersionFragments);
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
	
	private void readFile() throws JsonParseException, JsonMappingException, IOException {
		File f = new File(filePath);
		
		if(!f.exists()) {
			f.createNewFile();
			FileOutputStream fos = new FileOutputStream(f);
			
			currentVersion = requestSender.getLatestVersion(realm);
			byte b[] = currentVersion.getBytes();
			
			fos.write(b);
			fos.close();
		} else {
			FileInputStream fis = new FileInputStream(f);
			
			try( BufferedReader br =
			           new BufferedReader( new InputStreamReader(fis, currentVersion)))
			   {
			      StringBuilder sb = new StringBuilder();
			      String line;
			      while(( line = br.readLine()) != null ) {
			         sb.append( line );
			         sb.append( '\n' );
			      }
			      currentVersion = sb.toString();
			   }
		}
	}

	private void startTask() {
		Timer timer = new Timer();
		timer.schedule(task, 0, daily);
	}
}
