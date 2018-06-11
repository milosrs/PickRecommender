package com.lol.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lol.model.summoner.SummonerAuth;
import com.lol.model.summoner.SummonerLoginAuth;
import com.lol.repository.SummonerRepository;

@Service("summonerAuthService")
public class SummonerAuthService implements UserDetailsService {

	@Autowired
	private SummonerRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			System.out.println("Autentikacija!!!!");
			List<SimpleGrantedAuthority> roleAuths = new ArrayList<SimpleGrantedAuthority>();
			SummonerAuth summoner = repository.getByUsername(username);

			if(summoner == null) {
				throw new UsernameNotFoundException("Username not found");
			}
			
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
			/*Set<UserRole> roles = user.getUserRoles();
			
			for(UserRole role:roles) {
				roleAuths.addAll(
						role.getPrivileges().stream()
						.map(priv -> {
							
							SimpleGrantedAuthority grantedAuth = new SimpleGrantedAuthority(priv.getName());
							return grantedAuth;
						})
						.collect(Collectors.toList()));
			}*/
			UserDetails userDetails = new SummonerDetails(new SummonerLoginAuth(summoner.getUsername(), summoner.getPassword()));
			return userDetails;
		}catch(Exception e) {
			throw new UsernameNotFoundException("Username not found. Error with database.");
		}
	}

}
