package com.renaud.splr.query.pageable;

import java.util.Iterator;
import java.util.List;

import com.renaud.solr.query.SimpleQuery;
import com.renaud.solr.repository.SolrCrudRepository;


public class DocumentIterator<T> implements Iterator<T>{
	
	private SimpleQuery query;
	
	private SolrCrudRepository<T, ?> repository;
	
	private List<T> documents;
	
	

	public DocumentIterator(SimpleQuery query, SolrCrudRepository<T, ?> repository) {
		this.query = query;
		this.repository = repository;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public T next() {
		// TODO Auto-generated method stub
		return null;
	}


}
