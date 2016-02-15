package com.renaud.solr.test.base;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.renaud.solr.config.SolrEnable;

@Profile("test")
@Configuration
@SolrEnable(basePackages = { "com.renaud.solr" })
@ComponentScan( basePackages = { "com.renaud.solr" })
public class ConfigurationTest {


}
