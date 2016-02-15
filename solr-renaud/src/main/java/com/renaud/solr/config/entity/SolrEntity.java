package com.renaud.solr.config.entity;

import java.io.Serializable;
import org.springframework.data.repository.core.EntityInformation;

public class SolrEntity<T,ID extends Serializable> implements EntityInformation<T,ID>{
	
	private Class<T> domainClass;

	public SolrEntity(Class<T> domainClass) {
		this.domainClass = domainClass;
	}

	@Override
	public Class<T> getJavaType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isNew(T entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ID getId(T entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<ID> getIdType() {
		// TODO Auto-generated method stub
		return null;
	}

}
