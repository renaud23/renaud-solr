package com.renaud.solr.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.renaud.solr.test.base.BaseTest;
import com.renaud.solr.test.model.Adresse;
import com.renaud.solr.test.model.Client;
import com.renaud.solr.test.model.ClientRepository;

public class SolrCrudRepositoryTest extends BaseTest{
	
	@Autowired
	private ClientRepository clientRepository;

	@Test
	public void save(){
		Client  c = new Client();
		c.setId("1");
		c.setNom("Genevois");
		c.setPrenom("Renaud");
		c.setAdresse(new Adresse("Aristide Briand", 3, "92170", "Vanves"));
		
		clientRepository.save(c);
	}
}
