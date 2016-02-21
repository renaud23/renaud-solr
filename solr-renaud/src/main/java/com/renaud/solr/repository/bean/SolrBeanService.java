package com.renaud.solr.repository.bean;

import java.io.Serializable;
import java.util.List;

import com.renaud.solr.repository.bean.field.FieldValue;

public interface SolrBeanService<U, ID extends Serializable> {
	
	public List<FieldValue> readBean(U u);
	
	public U createBean(List<FieldValue> fields, Class<U> clazz);
	
}
