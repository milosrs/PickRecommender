package com.lol;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.services")
public class DroolsConfig {
	private static String drlFile = "taxirule.drl";
	
	@Bean
	public KieContainer kieContainer() {
		KieServices services = KieServices.Factory.get();
		
		KieFileSystem fileSystem = services.newKieFileSystem();
		fileSystem.write(ResourceFactory.newClassPathResource(drlFile));
		KieBuilder builder = services.newKieBuilder(fileSystem);
		builder.buildAll();
		KieModule module = builder.getKieModule();
		
		return services.newKieContainer(module.getReleaseId());
	}
}
