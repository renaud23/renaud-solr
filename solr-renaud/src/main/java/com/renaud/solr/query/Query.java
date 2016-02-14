package com.renaud.solr.query;

import com.renaud.solr.query.filter.Filter;

public interface Query {
	public void addToken(String token);
	
	public void addFilter(Filter filter);
}
