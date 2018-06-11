package com.lol.controllers;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lol.model.summoner.SummonerAuth;
import com.lol.model.summoner.SummonerDto;
import com.lol.model.summoner.SummonerLoginAuth;
import com.lol.repository.SummonerDao;
import com.lol.requestSender.SummonerServiceRequestSender;
import com.lol.security.JWTAuthenticationRequest;
import com.lol.security.JWTAuthenticationResponse;
import com.lol.security.JWTTokenUtil;
import com.lol.security.SummonerDetails;

@RestController
@RequestMapping(value="/auth")
public class AuthController {

	@Autowired
	private SummonerDao dao;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService myAppUserDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder pwEncoder;
	
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	@Autowired
	private JWTTokenUtil jwtTokenUtil;
	
	@Autowired
	private MessageSource messages;

	@Value("Authorization")
	private String tokenHeader;

	@Value("mySecret")
	private String secret;
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody JWTAuthenticationRequest authRequest) throws IOException {
		try {
			logger.info("username: " + authRequest.getUsername());
			SummonerAuth summoner = dao.get(authRequest.getUsername());
			
			if(summoner != null) {
				UsernamePasswordAuthenticationToken tokenauth = new UsernamePasswordAuthenticationToken(
						authRequest.getUsername(),
						authRequest.getPassword());
				final Authentication authentication = authenticationManager.authenticate(tokenauth);
				logger.info("authenticated");
				SecurityContextHolder.getContext().setAuthentication(authentication);
				final UserDetails userDetails = myAppUserDetailsService.loadUserByUsername(authRequest.getUsername());
				final String token = jwtTokenUtil.generateToken(userDetails);
				logger.info("token : " + token);
				
				return ResponseEntity.ok(new JWTAuthenticationResponse(token, dao.login(new SummonerLoginAuth(authRequest.getUsername(), authRequest.getPassword()))));
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		} catch (BadCredentialsException | UsernameNotFoundException e){
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(SummonerAuth regInfo) {
		ResponseEntity<?> ret = null;
		SummonerAuth userName = dao.get(regInfo.getUsername());
		SummonerAuth toRet = null;
		if(userName == null) {
			try {
				toRet = dao.register(regInfo);
				ret = ResponseEntity.ok(toRet);
			} catch(Exception e) {
				System.out.println("Greska pri registraciji!");
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error registering you. Please, try again.");
			}
		} else {
			String msg = null;
			if(userName != null)
				msg = "Username is already taken. Try again with another username.";
			ret = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(msg);
		}

		return ret;
	}
}
