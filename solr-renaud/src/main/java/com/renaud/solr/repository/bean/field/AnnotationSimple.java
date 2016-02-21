package com.renaud.solr.repository.bean.field;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.renaud.solr.annotation.SolrField;
import com.renaud.solr.repository.SolrRepositoryException;

@Component
public class AnnotationSimple<U> implements AnnotationAccess<U>{

	@Override
	public FieldValue readBeanValues(U bean, Field f, SolrField a) {
		Assert.notNull(bean, "Impossible de lire un bean null.");
		try {
			return FieldValue.Builder
					.newInstance()
					.setName(a.field())
					.setValue(PropertyUtils.getProperty(bean, a.field()))
					.build();
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			throw new SolrRepositoryException("readBeanValues", e);
		}
	}

	@Override
	public void fillBean(U bean, Field f, SolrField a, Object value) {
		// TODO Auto-generated method stub
		
	}
	
	
}
