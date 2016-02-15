package com.renaud.solr.config;

import org.springframework.data.repository.config.RepositoryConfigurationExtensionSupport;

public class SolrConfExt extends RepositoryConfigurationExtensionSupport{

	@Override
	public String getRepositoryFactoryClassName() {
		return SolrRepositoryFactoryBean.class.getName();
	}

	@Override
	protected String getModulePrefix() {
		return "solr";
	}

}
