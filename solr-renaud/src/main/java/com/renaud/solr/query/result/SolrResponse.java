package com.renaud.solr.query.result;

import java.util.List;

import org.springframework.data.domain.Pageable;


public interface SolrResponse<T> extends Pageable{
	long getNumFounds();
	long getStart();
	List<T> getDocuments();
}
