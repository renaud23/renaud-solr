package com.renaud.solr.config;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.data.repository.config.AnnotationRepositoryConfigurationSource;
import org.springframework.data.repository.config.RepositoryConfigurationExtensionSupport;
import com.renaud.solr.repository.server.SolrClientFactory;

public class SolrConfExt extends RepositoryConfigurationExtensionSupport{

	@Override
	public String getRepositoryFactoryClassName() {
		return SolrRepositoryFactoryBean.class.getName();
	}
	


	@SuppressWarnings({ "unused", "unchecked" })
	@Override
	public void postProcess(BeanDefinitionBuilder builder, AnnotationRepositoryConfigurationSource config) {
		AnnotationAttributes attributes = config.getAttributes();
		Class<? extends SolrClientFactory> clientFactoryClass = (Class<? extends SolrClientFactory>) config.getAttributes().get("clientFactoryClass");
		builder.addPropertyValue("clientFactoryClass", clientFactoryClass);
	}

	@Override
	protected String getModulePrefix() {
		return "solr";
	}

}
