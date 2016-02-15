package com.renaud.solr.config;

import java.io.Serializable;

import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.data.repository.core.support.TransactionalRepositoryFactoryBeanSupport;

public class SolrRepositoryFactoryBean<T extends Repository<S, ID>, S, ID extends Serializable> extends
TransactionalRepositoryFactoryBeanSupport<T, S, ID> {

	@Override
	protected RepositoryFactorySupport doCreateRepositoryFactory() {
		return new SolrRepositoryFactory();
	}

}
