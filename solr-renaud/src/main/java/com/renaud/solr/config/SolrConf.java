package com.renaud.solr.config;

import java.lang.annotation.Annotation;

import org.springframework.data.repository.config.RepositoryBeanDefinitionRegistrarSupport;
import org.springframework.data.repository.config.RepositoryConfigurationExtension;

public class SolrConf extends RepositoryBeanDefinitionRegistrarSupport {

	@Override
	protected Class<? extends Annotation> getAnnotation() {
		return SolrEnable.class;
	}

	@Override
	protected RepositoryConfigurationExtension getExtension() {
		return new SolrConfExt();
	}

}
