package com.renaud.solr.query;

public interface SolrQuery {
	
	void addFacet();
	void addFilter();
	void setQ();
	void setDefType();
	void setCollection();

}
