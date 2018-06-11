package com.lol.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lol.model.summoner.SummonerAuth;

public interface SummonerRepository extends JpaRepository<SummonerAuth, String> {

	public SummonerAuth getByUsername(String username);
}
