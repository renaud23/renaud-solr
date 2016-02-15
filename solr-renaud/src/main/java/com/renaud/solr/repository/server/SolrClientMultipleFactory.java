package com.renaud.solr.repository.server;

import org.apache.solr.client.solrj.SolrClient;

import com.renaud.solr.repository.SolrRepositoryException;

public class SolrClientMultipleFactory implements SolrClientFactory{

	@Override
	public SolrClient getClient() throws SolrRepositoryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() throws SolrRepositoryException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCoreName(String coreName) {
		// TODO Auto-generated method stub
		
	}

}
