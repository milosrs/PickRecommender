package com.lol;


import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
@EnableAsync
@EnableConfigurationProperties
@EnableAutoConfiguration
public class PickRecommenderApplication extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(PickRecommenderApplication.class, args);
	}
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Bean
	public KieContainer kieContainer() throws IOException {
	    return KieServices.Factory.get().getKieClasspathContainer();
	}
}