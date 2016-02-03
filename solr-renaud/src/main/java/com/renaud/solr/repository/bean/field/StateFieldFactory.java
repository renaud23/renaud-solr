package com.renaud.solr.repository.bean.field;

import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.renaud.solr.annotation.SolrField;
import com.renaud.solr.repository.SolrRepositoryException;

@Component
public class StateFieldFactory<U> implements SolrFieldAccess<U>{
	
	@Autowired
	private SolrFieldAccess<U> stateSimple;

	@Override
	public FieldValue read(U bean, SolrField a, Field f) {
		SolrFieldAccess<U> strategy = null;
		if(true){
			strategy = stateSimple;
		} else{
			throw new SolrRepositoryException("");
		}
		return strategy.read(bean, a, f);
	}

}
