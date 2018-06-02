package com.sbz.PickRecommender;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.services.TaxiFareCalculatorService;
import com.testEntities.Fare;
import com.testEntities.TaxiRide;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDrools {
	
	@Autowired
	private TaxiFareCalculatorService taxiFareCalculatorService;
	
	@Test
	public void whenNightSurchargeFalseAndDistLessThan10_thenFixWithoutNightSurcharge() {
	    TaxiRide taxiRide = new TaxiRide();
	    taxiRide.setIsNightSurcharge(false);
	    taxiRide.setDistanceInMile(9L);
	    Fare rideFare = new Fare();
	    Long totalCharge = taxiFareCalculatorService.calculateFare(taxiRide, rideFare);
	  
	    assertNotNull(totalCharge);
	    assertEquals(Long.valueOf(70), totalCharge);
	}
}
