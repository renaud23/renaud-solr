package com.renaud.solr.repository.bean.field;

import java.lang.reflect.Field;

import com.renaud.solr.annotation.SolrField;


public interface SolrFieldAccess<U> {
	FieldValue read(U bean, SolrField a, Field f);
	
	void fill(U bean, SolrField a, Field f, Object value);

}
