package com.renaud.solr.annotation.tools;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class CachAnnotation {

	@Cacheable
	public Field[] getAnnotedFields(Class<?> clazz, Class<? extends Annotation> annotationClass){
		return ClassUtil.getAnnotatedDeclaredFields(clazz, annotationClass, true);
	}
}
