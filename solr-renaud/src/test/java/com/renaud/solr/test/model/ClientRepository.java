package com.renaud.solr.test.model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.renaud.solr.repository.SolrCrudRepository;
import com.renaud.solr.repository.server.SolrClientFactory;

@Component
public class ClientRepository extends SolrCrudRepository<Client, String>{
	
	@Autowired
	private SolrClientFactory solrClientFactory;

	@Override
	public SolrClientFactory getClientFactory() {
		return solrClientFactory;
	}

	@Override
	public Class<Client> getBeanClassType() {
		return Client.class;
	}
	
	
	public void query(){
		
	}
}
