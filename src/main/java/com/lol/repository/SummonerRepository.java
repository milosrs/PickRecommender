package com.lol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lol.model.summoner.SummonerAuth;

@Repository
public interface SummonerRepository extends JpaRepository<SummonerAuth, String> {

	public SummonerAuth getByUsername(String username);
}
