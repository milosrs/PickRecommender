package com.lol.serviceImpl;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.lol.model.summoner.SummonerAuth;
import com.lol.model.summoner.SummonerDto;
import com.lol.model.summoner.SummonerLoginAuth;
import com.lol.repository.SummonerRepository;
import com.lol.requestSender.SummonerServiceRequestSender;
import com.lol.security.JWTAuthenticationRequest;
import com.lol.security.JWTAuthenticationResponse;
import com.lol.security.JWTTokenUtil;
import com.lol.service.SummonerService;

@Service
public class SummonerServiceImpl implements SummonerService {

	@Autowired
	private SummonerRepository summonerRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private SummonerServiceRequestSender requestSender;
	
	@Autowired
	private JWTTokenUtil jwtTokenUtil;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService myAppUserDetailsService;

	@Value("Authorization")
	private String tokenHeader;

	@Value("mySecret")
	private String secret;
	
	protected final Log logger = LogFactory.getLog(getClass());
	
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
		SummonerAuth userName = summonerRepository.getByUsername(auth.getUsername());
		SummonerAuth toRet = null;
		SummonerDto summonerRiot = requestSender.sendRequest(auth);
		
		if(userName == null && summonerRiot != null) {
			auth.setPassword(encoder.encode(auth.getPassword()));
			toRet = summonerRepository.save(auth);
		} else {
			toRet = null;
		}

		return toRet;
	}

	@Override
	public JWTAuthenticationResponse login(JWTAuthenticationRequest authRequest) throws IOException {
		SummonerAuth summoner = summonerRepository.getByUsername(authRequest.getUsername());
		SummonerDto summonerObj = requestSender.sendRequest(summoner);
		logger.info("username: " + authRequest.getUsername());
		logger.info("username: " + authRequest.getUsername());
		
		if(summoner != null && summonerObj != null) {
			UsernamePasswordAuthenticationToken tokenauth = new UsernamePasswordAuthenticationToken(
					authRequest.getUsername(),
					authRequest.getPassword());
			final Authentication authentication = authenticationManager.authenticate(tokenauth);
			logger.info("authenticated");
			SecurityContextHolder.getContext().setAuthentication(authentication);
			final UserDetails userDetails = myAppUserDetailsService.loadUserByUsername(authRequest.getUsername());
			final String token = jwtTokenUtil.generateToken(userDetails);
			logger.info("token : " + token);
				
			return new JWTAuthenticationResponse(token, summoner.getUsername(), summoner.getRealm());
		} else {
			return null;
		}
	}

}
