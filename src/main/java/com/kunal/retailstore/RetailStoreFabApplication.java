package com.kunal.retailstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@SpringBootApplication
public class RetailStoreFabApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetailStoreFabApplication.class, args);
	}
	
	@Bean 
	public Jackson2ObjectMapperBuilder objectMapperBuilder(){
	    Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
	    builder.failOnUnknownProperties(true);
	    return builder;
	}

}
