package com.renaud.solr.query.result;

import org.springframework.data.domain.Pageable;

public interface SolrResponse<T> extends Pageable{
	int getNumFounds();
}
