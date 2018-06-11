package com.lol;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
@EnableAsync
public class PickRecommenderApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(PickRecommenderApplication.class, args);
	}
	protected final Log logger = LogFactory.getLog(getClass());
	
}
