package com.renaud.solr.repository.bean.field;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.renaud.solr.annotation.tools.CachAnnotation;

@Component
public class FieldSimple<U> implements FieldAccess<U>{

	@Autowired
	private CachAnnotation cachAnnotation;
	@Autowired
	private AnnotationAccess<U> annotationAccessFactory;

	@Override
	public List<FieldValue> readBeanValues(U bean, Field f) {
		return cachAnnotation.getSolrfieldAnnotation(f)
			.stream()
			.map(a->annotationAccessFactory.readBeanValues(bean, f, a))
			.filter(a -> a != null)
			.collect(Collectors.toList());
	}

	@Override
	public void makeBean(U bean, Field f, FieldMap fieldMap) {
		cachAnnotation.getSolrfieldAnnotation(f).stream().forEach(a->annotationAccessFactory.fillBean(bean, f, a,fieldMap.isField(a.field()) ? fieldMap.get(a.field()).getValue(): null));
	}
}
