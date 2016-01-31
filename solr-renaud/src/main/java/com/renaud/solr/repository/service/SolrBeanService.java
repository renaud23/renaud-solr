package com.renaud.solr.repository.service;

public interface SolrBeanService<U> {
	public void read(U u);
	
	public U fill();
}
