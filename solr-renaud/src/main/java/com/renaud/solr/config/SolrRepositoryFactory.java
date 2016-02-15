package com.renaud.solr.config;

import java.io.Serializable;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import com.renaud.solr.annotation.tools.CachAnnotation;
import com.renaud.solr.config.entity.SolrEntity;
import com.renaud.solr.repository.SolrCrudRepository;
import com.renaud.solr.repository.SolrRepositoryException;
import com.renaud.solr.repository.bean.SolrBeanService;
import com.renaud.solr.repository.server.SolrClientFactory;

public class SolrRepositoryFactory extends RepositoryFactorySupport{

	
	private Class<? extends SolrClientFactory> clientFactoryClass;

	
	private SolrBeanService<?,?> solrBeanService;
	
	public SolrRepositoryFactory(CachAnnotation cachAnnotation, SolrBeanService<?,?> solrBeanService) {
		this.solrBeanService = solrBeanService;
	}

	@Override
	public <T, ID extends Serializable> EntityInformation<T, ID> getEntityInformation(Class<T> domainClass) {
		return new SolrEntity<>(domainClass);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected Object getTargetRepository(RepositoryInformation metadata) {
		try {
			SolrCrudRepository repository = getTargetRepositoryViaReflection(metadata, this.solrBeanService);
			repository.setDomainClass(metadata.getDomainType());
			repository.setSolrClientFactory(clientFactoryClass.newInstance());
			
			return repository;
		} catch (InstantiationException | IllegalAccessException e) {
			throw new SolrRepositoryException("Impossible d'instancier la factory pour le client solr.", e);
		}		
	}

	@Override
	protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
		return SolrCrudRepository.class;
	}

	public void setClientFactoryClass(Class<? extends SolrClientFactory> clientFactoryClass) {
		this.clientFactoryClass = clientFactoryClass;
	}

}
