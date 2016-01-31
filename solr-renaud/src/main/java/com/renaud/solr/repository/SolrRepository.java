package com.renaud.solr.repository;

import java.io.Serializable;

import org.springframework.data.repository.Repository;

public class SolrRepository<T, ID extends Serializable> implements Repository<T, ID>{

}
