package com.renaud.solr.config;

import java.io.Serializable;

import org.springframework.data.repository.core.EntityInformation;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import com.renaud.solr.config.entity.RepositoryBaseClass;
import com.renaud.solr.config.entity.SolrEntity;

public class SolrRepositoryFactory extends RepositoryFactorySupport{

	@Override
	public <T, ID extends Serializable> EntityInformation<T, ID> getEntityInformation(Class<T> domainClass) {
		return new SolrEntity<>(domainClass);
	}

	@Override
	protected Object getTargetRepository(RepositoryInformation metadata) {
		return new RepositoryBaseClass();
	}

	@Override
	protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
		return RepositoryBaseClass.class;
	}

}
