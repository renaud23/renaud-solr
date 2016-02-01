package com.renaud.solr.repository.core;

public interface SolrBeanService<U> {
	
	public void read(U u);
	
	public U fill();
	
}
