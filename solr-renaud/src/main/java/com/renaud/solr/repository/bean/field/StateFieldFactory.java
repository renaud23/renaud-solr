package com.renaud.solr.repository.bean.field;

import java.lang.reflect.Field;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.renaud.solr.annotation.SolrField;
import com.renaud.solr.repository.SolrRepositoryException;

@Component
public class StateFieldFactory<U> implements SolrFieldAccess<U>{
	
	@Autowired
	private SolrFieldAccess<U> stateSimple;
	@Autowired
	private SolrFieldAccess<U> stateSimpleNested;

	@Override
	public FieldValue read(U bean, SolrField a, Field f) {
		return getStrategy(bean, a, f).read(bean, a, f);
	}
	
	

	@Override
	public void fill(U bean, SolrField a, Field f, Object value) {
		getStrategy(bean, a, f).fill(bean, a, f, value);
	}
	
	
	private SolrFieldAccess<U> getStrategy(U bean, SolrField a, Field f){
		SolrFieldAccess<U> strategy = null;
		
		boolean nested = !StringUtils.isBlank(a.property());
		boolean iterable = false;
		
		if(!nested && !iterable){
			strategy = stateSimple;
		} else if(nested && !iterable){
			strategy = stateSimpleNested;
		} else {
			throw new SolrRepositoryException("...");
		}
		return strategy;
	}

}
