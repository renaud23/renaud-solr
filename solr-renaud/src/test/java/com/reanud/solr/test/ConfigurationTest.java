package com.reanud.solr.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
@ComponentScan(basePackages = { "com.renaud.*" })
public class ConfigurationTest {

	@Bean
	public String test(){
		return "hello !";
	}
}
