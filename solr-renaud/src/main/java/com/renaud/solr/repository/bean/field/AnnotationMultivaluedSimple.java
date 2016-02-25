package com.renaud.solr.repository.bean.field;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.renaud.solr.annotation.SolrField;
import com.renaud.solr.repository.SolrRepositoryException;

@Component
public class AnnotationMultivaluedSimple<U> implements AnnotationAccess<U> {

	@Override
	public FieldValue readBeanValues(U bean, Field f, SolrField a) {	
		try {	
			List<Object> values = Lists.newArrayList();
			((Collection<?>) PropertyUtils.getProperty(bean, f.getName())).stream()
				.filter(o -> o!=null)
				.forEach(values::add);
			return FieldValue.Builder
				.newInstance()
				.setName(a.field())
				.setValue(values)
				.build();
		} catch (Exception e) {
			throw new SolrRepositoryException("Impossible de lire une collection.", e);
		}	
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void fillBean(U bean, Field f, SolrField a, Object value) {
		Class<?> classGeneric = (Class<?>) ((ParameterizedType) f.getGenericType()).getActualTypeArguments()[0];
		try {
			Collection<Object> cible = (Collection<Object>) PropertyUtils.getProperty(bean, f.getName());
			Iterable<?> source = (Iterable<?>) value;
			
			Stream.of(source).map(o->ConvertUtils.convert(o, classGeneric)).forEach(cible::add);
		}
		catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			throw new SolrRepositoryException("Impossible de peupler une collection.", e);
		}
		
		
	}

}
