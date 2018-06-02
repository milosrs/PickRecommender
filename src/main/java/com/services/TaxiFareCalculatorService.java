package com.services;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testEntities.Fare;
import com.testEntities.TaxiRide;

@Service
public class TaxiFareCalculatorService {
	
	@Autowired
	private KieContainer container;
	
	public Long calculateFare(TaxiRide ride, Fare fare) {
		KieSession session = container.newKieSession();
		session.setGlobal("rideFare", fare);
		session.insert(ride);
		session.fireAllRules();
		session.dispose();
		
		return fare.getTotalFare();
	}
}
