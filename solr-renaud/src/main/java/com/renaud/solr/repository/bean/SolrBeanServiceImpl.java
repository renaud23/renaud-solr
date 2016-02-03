package com.renaud.solr.repository.bean;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.renaud.solr.annotation.SolrField;
import com.renaud.solr.annotation.SolrFields;
import com.renaud.solr.annotation.tools.CachAnnotation;
import com.renaud.solr.repository.bean.field.FieldValue;
import com.renaud.solr.repository.bean.field.SolrFieldAccess;
import com.renaud.solr.repository.bean.field.StateFieldFactory;

@Service
public class SolrBeanServiceImpl<U,ID extends Serializable> implements SolrBeanService<U, ID>, SolrFieldAccess<U>{
	
	@Autowired
	private CachAnnotation cachAnnotation;
	@Autowired
	private StateFieldFactory<U> stateFieldFactory;

	@Override
	public List<FieldValue> read(U bean) {
//		return Stream.concat(
//				Stream.of(cachAnnotation.getAnnotedFields(bean.getClass(), SolrFields.class)),
//				Stream.of(cachAnnotation.getAnnotedFields(bean.getClass(), SolrField.class)))				
//					.map((field)->{ return this.read(bean, field.getAnnotation(SolrField.class), field); })
//					.collect(Collectors.toList());
		
		List<FieldValue> val = Lists.newArrayList();
		Stream.of(cachAnnotation.getAnnotedFields(bean.getClass(), SolrFields.class))
		.forEach((field)->{ Stream.of(field.getAnnotation(SolrField.class)).forEach((a)->{ val.add(this.read(bean,a,field)); }); });
			
			
			
		
		return Stream.of(cachAnnotation.getAnnotedFields(bean.getClass(), SolrField.class))
					.map((field)->{ return this.read(bean, field.getAnnotation(SolrField.class), field); })
					.collect(Collectors.toList());
	}

	@Override
	public U fill() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FieldValue read(U bean, SolrField a, Field f){
		return stateFieldFactory.read(bean, a, f);
	}
}
