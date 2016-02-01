package com.renaud.solr.repository;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
@ComponentScan( basePackages = { "com.renaud.solr" })
public class ConfigurationTest {


}
