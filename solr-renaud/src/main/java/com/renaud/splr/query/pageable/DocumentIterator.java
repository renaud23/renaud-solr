package com.renaud.splr.query.pageable;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import com.renaud.solr.query.SimpleQuery;
import com.renaud.solr.query.result.SolrResponse;
import com.renaud.solr.repository.SolrCrudRepository;


public class DocumentIterator<T> implements Iterator<T>{
	
	private SimpleQuery query;
	
	private SolrCrudRepository<T, ?> repository;
	
	private List<T> documents;
	
	private int start;
	
	private int rows;
	
	private long numfounds = -1l;
	
	private Iterator<T> it;
	
	private boolean hasNext = false;

	public DocumentIterator(SimpleQuery query, SolrCrudRepository<T, ?> repository) {
		this.query = query;
		this.repository = repository;
		this.rows = query.getRows();
		this.start = -this.rows;
		this.getNextPage();

	}

	@Override
	public boolean hasNext() {
		if(this.it != null){
			if(!this.it.hasNext()){
				getNextPage();
			}
		} 
		return hasNext;
	}

	@Override
	public T next() {
		if(this.hasNext()){
			return it.next();
		} else throw new NoSuchElementException();
	}

	private void getNextPage(){
		if(numfounds == -1 || numfounds > start){
			start += rows;
			this.query.setStart(start);
			SolrResponse<T> response = this.repository.query(query);
			if(response.getNumFounds() > 0){
				hasNext = true;
				numfounds = response.getNumFounds();
				this.it = response.getDocuments().iterator();
			} else {
				hasNext = false;
			}
		} else {
			hasNext = false;
			it = null;
		}
	}

}
