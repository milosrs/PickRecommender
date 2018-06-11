package com.lol.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lol.model.summoner.SummonerAuth;
import com.lol.security.JWTAuthenticationRequest;
import com.lol.security.JWTAuthenticationResponse;
import com.lol.service.SummonerService;

@RestController
@RequestMapping("/auth")
public class SummonerController {

	@Autowired
	private SummonerService summonerService;
	
	@GetMapping("/test")
	public String test() {
		return "Test";
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody SummonerAuth auth) throws IOException {
		try {
			SummonerAuth ret = summonerService.register(auth);
			
			if(ret == null) {
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("You are already registered!");
			}
			
			return ResponseEntity.ok(ret);
		} catch(Exception e) {
			System.out.println("Greska pri registraciji!");
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error registering you. Please, try again.");
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody JWTAuthenticationRequest authRequest) throws IOException {
		try{
			JWTAuthenticationResponse resp =  summonerService.login(authRequest);
			
			if(resp == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
			}
			
			return ResponseEntity.ok(resp);
		} catch(BadCredentialsException | UsernameNotFoundException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error logging in. Please, try again later.");
		}
	}
	
}
