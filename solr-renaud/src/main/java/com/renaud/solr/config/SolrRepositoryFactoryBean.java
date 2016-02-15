package com.renaud.solr.config;

import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.data.repository.core.support.TransactionalRepositoryFactoryBeanSupport;
import com.renaud.solr.annotation.tools.CachAnnotation;
import com.renaud.solr.repository.bean.SolrBeanService;
import com.renaud.solr.repository.bean.field.StateFieldFactory;
import com.renaud.solr.repository.server.SolrClientFactory;



public class SolrRepositoryFactoryBean<T extends Repository<S, ID>, S, ID extends Serializable> extends
			TransactionalRepositoryFactoryBeanSupport<T, S, ID> {
	
	@Autowired
	private SolrClientFactory solrClientFactory;
	@Autowired
	private CachAnnotation cachAnnotation;
	@Autowired
	private StateFieldFactory<?> stateFieldFactory;
	@Autowired
	private SolrBeanService<?,?> solrBeanService;
	
	
	@Override
	protected RepositoryFactorySupport doCreateRepositoryFactory() {
		return new SolrRepositoryFactory(solrClientFactory, cachAnnotation, stateFieldFactory, solrBeanService);
	}

}
