package com.renaud.solr.query;

import org.apache.solr.client.solrj.SolrQuery;

import com.renaud.solr.query.filter.Filter;

public class TypedQuery implements Query{
	SolrQuery query;
	
	

	
	
	public static class QueryBuilder {
		TypedQuery query;
		
		
		public static QueryBuilder newQuery(){
			QueryBuilder tq = new QueryBuilder();
			tq.query = new TypedQuery(); 
			return tq;
		}
	}



	@Override
	public void addToken(String token) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void addFilter(Filter filter) {
		// TODO Auto-generated method stub
		
	}
}
