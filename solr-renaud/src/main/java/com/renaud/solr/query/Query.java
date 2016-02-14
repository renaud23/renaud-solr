package com.renaud.solr.query;

import com.renaud.solr.query.filter.Filter;

public interface Query {
		
	public void addToken(String token);
	
	public void addFilter(Filter filter);
	
	public static SimpleQuery.Builder newQuery(){
		return SimpleQuery.Builder.newInstance();
	}
	
	public static enum DefType {
		edismax, dismax;
	}
}
