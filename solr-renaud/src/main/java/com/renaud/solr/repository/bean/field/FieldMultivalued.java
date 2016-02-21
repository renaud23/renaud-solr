package com.renaud.solr.repository.bean.field;



import java.lang.reflect.Field;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.renaud.solr.annotation.tools.CachAnnotation;
import com.renaud.solr.repository.SolrRepositoryException;

@Component
public class FieldMultivalued<U> implements FieldAccess<U>{
	
	@Autowired
	private CachAnnotation cachAnnotation;
	@Autowired
	private AnnotationAccess<U> annotationAccessFactory;

	@Override
	public List<FieldValue> readBeanValues(U bean, Field f) {
		return Lists.newArrayList();
	}

	@Override
	public void makeBean(U bean, Field f, FieldMap value) {
		// TODO
	}

}
