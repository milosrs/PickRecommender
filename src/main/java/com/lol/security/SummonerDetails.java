package com.lol.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lol.model.summoner.SummonerLoginAuth;

public class SummonerDetails extends SummonerLoginAuth implements UserDetails {

	private static final long serialVersionUID = 1L;
	@JsonIgnore
	private boolean enabled;
	private List<SimpleGrantedAuthority> grantedAuthorities;
	
	public SummonerDetails(SummonerLoginAuth login) {
		super();
		this.password = login.getPassword();
		this.username = login.getUsername();
		grantedAuthorities = new ArrayList<SimpleGrantedAuthority>();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return grantedAuthorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
