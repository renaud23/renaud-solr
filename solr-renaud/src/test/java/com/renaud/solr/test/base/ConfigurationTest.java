package com.renaud.solr.test.base;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
@ComponentScan( basePackages = { "com.renaud.solr" })
public class ConfigurationTest {


}
