package com.renaud.solr.repository.bean;

import java.util.Map;

public interface SolrBeanService<U> {
	
	public Map<String, Object> read(U u);
	
	public U fill();
	
}
