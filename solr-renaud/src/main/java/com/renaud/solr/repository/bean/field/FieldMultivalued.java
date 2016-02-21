package com.renaud.solr.repository.bean.field;



import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
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
	private AnnotationAccess<U> annotationMultivaluedSimple;

	@Override
	public List<FieldValue> readBeanValues(U bean, Field f) {
		try {
			List<FieldValue> liste = Lists.newArrayList();
			cachAnnotation.getSolrfieldAnnotation(f).stream()
				.forEach((a)->{
					if(StringUtils.isBlank(a.property())){						
						liste.add(annotationMultivaluedSimple.readBeanValues(bean, f, a));
					} else {
						// TODO
					}
				});
			
			return liste.isEmpty() ? null : liste;
		} catch (Exception e) {
			throw new SolrRepositoryException("Impossible de lire une collection.", e);
		}
	}

	@Override
	public void makeBean(U bean, Field f, FieldMap value) {
//		cachAnnotation.getSolrfieldAnnotation(f).
//		String nestedPropertyName = StringUtils.substring(a.property(), f.getName().length() + 1);
//		try {
//			Iterable<?> cible = (Iterable<?>) PropertyUtils.getProperty(bean, f.getName());
//			Class<?> classGeneric = (Class<?>) ((ParameterizedType) f.getGenericType()).getActualTypeArguments()[0];
//			
//			
//			
//		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
//			throw new SolrRepositoryException("Impossible de créer un bean.", e);
//		}
	}

}
