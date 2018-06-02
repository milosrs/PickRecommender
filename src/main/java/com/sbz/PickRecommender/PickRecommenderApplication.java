package com.sbz.PickRecommender;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.services.TaxiFareCalculatorService;
import com.testEntities.Fare;
import com.testEntities.TaxiRide;

@SpringBootApplication
public class PickRecommenderApplication {

	@Autowired
	private TaxiFareCalculatorService taxiFareCalculatorService;
	
	public static void main(String[] args) {
		SpringApplication.run(PickRecommenderApplication.class, args);
	}
}
