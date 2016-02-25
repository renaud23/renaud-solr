package com.renaud.solr.repository.bean.field;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import java.util.stream.Stream;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.google.common.collect.Lists;
import com.renaud.solr.annotation.tools.CachAnnotation;
import com.renaud.solr.annotation.tools.ClassUtil;
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

	@SuppressWarnings("unchecked")
	@Override
	public void makeBean(U bean, Field f, FieldMap value) {
		try {
			Collection<Object> cible =  (Collection<Object>) PropertyUtils.getProperty(bean, f.getName());
//			Class<?> classGeneric = (Class<?>) ((ParameterizedType) f.getGenericType()).getActualTypeArguments()[0];
			if(cible == null){
				Class<?> typeListe =  PropertyUtils.getPropertyType(bean, f.getName());
				cible = (Collection<Object>) ClassUtil.createCollection(typeListe);
				PropertyUtils.setProperty(bean, f.getName(), cible);
			}
			cachAnnotation.getSolrfieldAnnotation(f).stream()
				.forEach((a)->{
						if(StringUtils.isBlank(a.property())){						
							annotationMultivaluedSimple.fillBean(bean, f, a, value.get(a.field()).getValue());
						} else {
							// TODO
						}
				});
//			cachAnnotation.getSolrfieldAnnotation(f).stream()
//				.map(a -> Stream.of((Iterable<?>) value.get(a.field()))
//					.filter(o -> o != null)
//					.map(o -> ConvertUtils.convert(o,classGeneric)))
//						.forEach(cible::add);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			throw new SolrRepositoryException("Impossible de remplir une collection.", e);
		}
	}

}
