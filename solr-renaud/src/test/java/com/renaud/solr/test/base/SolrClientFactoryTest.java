package com.renaud.solr.test.base;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.core.CoreContainer;
import org.springframework.stereotype.Component;

import com.renaud.solr.repository.SolrRepositoryException;
import com.renaud.solr.repository.server.SolrClientFactory;

@Component
public class SolrClientFactoryTest implements SolrClientFactory{

	private SolrClient solrClient;
	
	@Override
	public SolrClient getClient()  throws SolrRepositoryException{
		if(solrClient == null){
			String currentDir = System.getProperty("user.dir");
		    CoreContainer container = new CoreContainer(currentDir + "/src/test/resources/solr-home");
		    container.load();
		    solrClient = new EmbeddedSolrServer(container, "core-test");
		}
		
		return solrClient;
	}

	@Override
	public void close()  throws SolrRepositoryException{
		if(solrClient != null){
			try {
				solrClient.close();
				solrClient = null;
			} catch (IOException e) {
				throw new SolrRepositoryException("Impossible de fremer le serveur.", e);
			}
		}
	}

}
