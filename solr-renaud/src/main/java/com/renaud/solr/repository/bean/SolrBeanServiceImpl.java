package com.renaud.solr.repository.bean;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import com.google.common.collect.Lists;
import com.renaud.solr.annotation.SolrField;
import com.renaud.solr.annotation.SolrFields;
import com.renaud.solr.annotation.tools.CachAnnotation;
import com.renaud.solr.repository.SolrRepositoryException;
import com.renaud.solr.repository.bean.field.FieldValue;
import com.renaud.solr.repository.bean.field.FieldAccess;
import com.renaud.solr.repository.bean.field.FieldMap;

@Component
public class SolrBeanServiceImpl<U,ID extends Serializable> implements SolrBeanService<U, ID>{
	
	@Autowired
	private CachAnnotation cachAnnotation;
	@Autowired
	private FieldAccess<U> fieldAccessFactory;
	
	@Override
	public List<FieldValue> readBean(U u) {
		Assert.notNull(u, "Impossible de traiter un objet null.");
		List<FieldValue> values = Lists.newArrayList();
		Stream.of(cachAnnotation.getAnnotedFields(u.getClass(), SolrField.class))
			.map(f->fieldAccessFactory.readBeanValues(u, f))
			.filter(a-> a != null)
			.forEach(values::addAll);
		Stream.of(cachAnnotation.getAnnotedFields(u.getClass(), SolrFields.class))
			.map(f->fieldAccessFactory.readBeanValues(u, f))
			.filter(a-> a != null)
			.forEach(values::addAll);
		
		return values;
	}
	
	@Override
	public U createBean(List<FieldValue> fields, Class<U> clazz) {
		try {
			U bean = clazz.newInstance();
			Stream.of(cachAnnotation.getAnnotedFields(clazz, SolrField.class))
				.forEach(f->fieldAccessFactory.makeBean(bean, f, new FieldMap(fields)));
			Stream.of(cachAnnotation.getAnnotedFields(clazz, SolrFields.class))
				.forEach(f->fieldAccessFactory.makeBean(bean, f, new FieldMap(fields)));
			
			return bean;
		} catch (InstantiationException | IllegalAccessException e) {
			throw new SolrRepositoryException("Impossible de créer un bean.", e);
		}
	}
}
