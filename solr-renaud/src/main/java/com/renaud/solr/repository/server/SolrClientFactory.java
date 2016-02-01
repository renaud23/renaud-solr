package com.renaud.solr.repository.server;

import org.apache.solr.client.solrj.SolrClient;

import com.renaud.solr.repository.SolrRepositoryException;

public interface SolrClientFactory {
	
	SolrClient getClient() throws SolrRepositoryException;
	
	void close() throws SolrRepositoryException;
}
