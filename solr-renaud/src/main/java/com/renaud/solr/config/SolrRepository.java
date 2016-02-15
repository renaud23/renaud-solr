package com.renaud.solr.config;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;
import com.renaud.solr.query.SimpleQuery;
import com.renaud.solr.repository.SolrRepositoryException;
import com.renaud.solr.repository.server.SolrClientFactory;
import com.renaud.splr.query.pageable.DocumentIterable;

public interface SolrRepository<T,ID extends Serializable> extends Repository<T, ID>, CrudRepository<T, ID>, PagingAndSortingRepository<T, ID>{

	DocumentIterable<T> query(SimpleQuery quey) throws SolrRepositoryException;
	
	SolrClientFactory getSolrClientFactory();
}
