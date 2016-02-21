package com.renaud.solr.repository.bean.field;

import java.lang.reflect.Field;
import com.renaud.solr.annotation.SolrField;

public interface AnnotationAccess<U> {
	
	FieldValue readBeanValues(U bean, Field f, SolrField a);
	
	void fillBean(U bean, Field f, SolrField a, Object value);
}
