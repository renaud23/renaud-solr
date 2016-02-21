package com.renaud.solr.annotation.tools;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.renaud.solr.annotation.SolrField;
import com.renaud.solr.annotation.SolrFields;

@Component
public class CachAnnotation {

	@Cacheable
	public Field[] getAnnotedFields(Class<?> clazz, Class<? extends Annotation> annotationClass){
		return ClassUtil.getAnnotatedDeclaredFields(clazz, annotationClass, true);
	}
	
	@Cacheable
	public List<SolrField> getSolrfieldAnnotation(Field field){
		List<SolrField> annotations = Lists.newArrayList();
		
		if(field.isAnnotationPresent(SolrFields.class)){
			annotations.addAll(Stream.of(field.getAnnotation(SolrFields.class).value()).collect(Collectors.toList()));
		}
		if(field.isAnnotationPresent(SolrField.class)){
			annotations.add(field.getAnnotation(SolrField.class));
		}
		
		return annotations;
	}
}
