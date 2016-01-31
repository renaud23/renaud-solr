package com.reanud.solr.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.renaud.solr.test.model.Client;
import com.renaud.solr.test.model.ClientRepository;

public class SolrCrudRepositoryTest extends BaseTest{
	
	@Autowired
	private ClientRepository clientRepository;

	@Test
	public void save(){
		Client  c = new Client();
		
		clientRepository.save(c);
	}
}
