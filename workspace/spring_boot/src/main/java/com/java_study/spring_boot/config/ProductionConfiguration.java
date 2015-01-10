package com.java_study.spring_boot.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;

@Configuration
@ComponentScan
//@Profile("product")
@EnableAutoConfiguration
//@SpringBootApplication
@ImportResource({"classpath:appContext.xml", "classpath:com/java_study/spring_boot/config/spring-mvc.xml"})
public class ProductionConfiguration  extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ProductionConfiguration.class);
	}
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(ProductionConfiguration.class, args);
		
	}

}
