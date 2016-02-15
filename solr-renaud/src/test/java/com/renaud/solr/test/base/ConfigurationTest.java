package com.renaud.solr.test.base;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.renaud.solr.config.SolrRepositoryEnable;

@Profile("test")
@Configuration
@SolrRepositoryEnable(basePackages = { "com.renaud.solr" }, clientFactoryClass = SolrClientFactoryTest.class)
@ComponentScan( basePackages = { "com.renaud.solr" })
public class ConfigurationTest {


}
