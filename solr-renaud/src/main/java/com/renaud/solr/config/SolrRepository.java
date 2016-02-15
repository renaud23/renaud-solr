package com.renaud.solr.config;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;

public interface SolrRepository<T,ID extends Serializable> extends Repository<T, ID>, CrudRepository<T, ID>, PagingAndSortingRepository<T, ID>{
//	void setCoreName();
}
