package com.renaud.solr.test.base;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import com.renaud.solr.repository.server.SolrClientFactory;

@ActiveProfiles("test")
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ConfigurationTest.class }, loader = AnnotationConfigContextLoader.class)
public abstract class BaseTest {
	
	private final static Logger logger = LoggerFactory.getLogger(BaseTest.class);
	
	public abstract SolrClientFactory getFactory();
	
	@After
	public void after(){
		SolrClientFactory factory = getFactory();
		if(factory != null){
			String currentDir = System.getProperty("user.dir");
			try {
				factory.close();
				FileUtils.deleteDirectory(new File(currentDir + "/src/test/resources/solr-home/conf/data"));
			} catch (IOException e) {
				logger.error("Impossible de vider le core de test.", e);
			}
		}
	}
	
	@Before
	public void before(){
		
	}
}
