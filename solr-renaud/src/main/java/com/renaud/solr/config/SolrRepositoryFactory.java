package com.renaud.solr.config;

import java.io.Serializable;

import org.springframework.data.repository.core.EntityInformation;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import com.renaud.solr.annotation.tools.CachAnnotation;
import com.renaud.solr.config.entity.SolrEntity;
import com.renaud.solr.repository.SolrCrudRepository;
import com.renaud.solr.repository.bean.SolrBeanService;
import com.renaud.solr.repository.bean.SolrBeanServiceImpl;
import com.renaud.solr.repository.bean.field.StateFieldFactory;
import com.renaud.solr.repository.server.SolrClientFactory;

public class SolrRepositoryFactory extends RepositoryFactorySupport{

	private SolrClientFactory solrClientFactory;

	private StateFieldFactory<?> stateFieldFactory;
	private SolrBeanService<?,?> solrBeanService;
	
	public SolrRepositoryFactory(SolrClientFactory solrClientFactory, CachAnnotation cachAnnotation, StateFieldFactory<?> stateFieldFactory, SolrBeanService<?,?> solrBeanService) {
		this.solrClientFactory = solrClientFactory;
		this.stateFieldFactory = stateFieldFactory;
		this.solrBeanService = solrBeanService;
	}

	@Override
	public <T, ID extends Serializable> EntityInformation<T, ID> getEntityInformation(Class<T> domainClass) {
		return new SolrEntity<>(domainClass);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected Object getTargetRepository(RepositoryInformation metadata) {
		
		SolrCrudRepository repository = getTargetRepositoryViaReflection(metadata, solrBeanService);
		repository.setDomainClass(metadata.getDomainType());
		return repository;
	}

	@Override
	protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
		return SolrCrudRepository.class;
	}

}
