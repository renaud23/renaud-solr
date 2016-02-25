package com.renaud.solr.query.pageable;

import java.util.Iterator;

import com.renaud.solr.query.SimpleQuery;
import com.renaud.solr.repository.SolrCrudRepository;

public class DocumentIterable<T> implements Iterable<T>{
	
	private DocumentIterator<T> iterator;
	
	public DocumentIterable(SimpleQuery query, SolrCrudRepository<T, ?> repository){
		this.iterator = new DocumentIterator<>(query, repository);
	}

	@Override
	public Iterator<T> iterator() {
		return iterator;
	}

}
