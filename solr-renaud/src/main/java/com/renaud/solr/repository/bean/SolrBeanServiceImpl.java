package com.renaud.solr.repository.bean;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.renaud.solr.annotation.SolrField;
import com.renaud.solr.annotation.SolrFields;
import com.renaud.solr.annotation.tools.CachAnnotation;
import com.renaud.solr.repository.SolrRepositoryException;
import com.renaud.solr.repository.bean.field.FieldValue;
import com.renaud.solr.repository.bean.field.SolrFieldAccess;
import com.renaud.solr.repository.bean.field.StateFieldFactory;

@Component
public class SolrBeanServiceImpl<U,ID extends Serializable> implements SolrBeanService<U, ID>, SolrFieldAccess<U>{
	
	@Autowired
	private CachAnnotation cachAnnotation;
	@Autowired
	private StateFieldFactory<U> stateFieldFactory;

	@Override
	public List<FieldValue> read(U bean) {
		List<FieldValue> values = Lists.newArrayList();
		Stream.of(cachAnnotation.getAnnotedFields(bean.getClass(), SolrFields.class))
			.forEach((field)->{
				
						Stream.of(field.getAnnotation(SolrFields.class).value())
									.map((a)->{ return this.readBeanValues(bean, a, field); })
									.forEach(values::add);
			});

		Stream.of(cachAnnotation.getAnnotedFields(bean.getClass(), SolrField.class))
					.map((field)->{ return this.readBeanValues(bean, field.getAnnotation(SolrField.class), field); })
					.forEach(values::add);
		
		return values;
	}

	@Override
	public U fill(List<FieldValue> fields, Class<U> clazz) {
		try {
			U bean = clazz.newInstance();
			
			Stream.of(cachAnnotation.getAnnotedFields(bean.getClass(), SolrField.class))
			.filter(f->f.getAnnotation(SolrField.class).read())
			.forEach(
					(field)->{this.fill(bean, field.getAnnotation(SolrField.class), field, fields); });
			
			
			Stream.of(cachAnnotation.getAnnotedFields(bean.getClass(), SolrFields.class)).forEach(
					(field)->{
						Stream.of(field.getAnnotation(SolrFields.class).value())
						.filter(a->a.read())
						.forEach(
								(a)->{ this.fill(bean, a, field, fields); });
					});
			 
			return bean;
		} catch (InstantiationException | IllegalAccessException e) {
			throw new SolrRepositoryException("");
		}
	}

	@Override
	public FieldValue readBeanValues(U bean, SolrField a, Field f){
		return stateFieldFactory.readBeanValues(bean, a, f);
	}

	@Override
	public void fillBean(U bean, SolrField a, Field f, Object value) {
		stateFieldFactory.fillBean(bean, a, f, value);
		
	}
	
	private void fill(U bean, SolrField a, Field field,  List<FieldValue> fields){
		try{
			Object value = fields.stream().filter((f)-> {return Objects.equal(f.getName(), a.field());}).findFirst().get().getValue();
			this.fillBean(bean, a, field, value);
		}catch(NoSuchElementException e){}
	}
	
}
