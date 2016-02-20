package com.renaud.solr.repository.bean.field;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import com.renaud.solr.annotation.SolrField;
import com.renaud.solr.repository.SolrRepositoryException;

@Component
public class StateFieldFactory<U> implements SolrFieldAccess<U>{

	@Autowired
	private SolrFieldAccess<U> stateSimple;
	@Autowired
	private SolrFieldAccess<U> stateSimpleNested;
	@Autowired
	private SolrFieldAccess<U> stateMultivaluedNested;

	@Override
	public FieldValue read(U bean, SolrField a, Field f) {
		return getStrategy(bean, a, f).read(bean, a, f);
	}
	
	
	@Override
	public void fill(U bean, SolrField a, Field f, Object value) {
		getStrategy(bean, a, f).fill(bean, a, f, value);
	}
	
	private SolrFieldAccess<U> getStrategy(U bean, SolrField a, Field f){
		Assert.notNull(bean, "Le bean be doit pas être null.");
		SolrFieldAccess<U> strategy = null;
		
		boolean nested = !StringUtils.isBlank(a.property());
		boolean iterable = false;
		try {
			Class<?> clazz = PropertyUtils.getPropertyType(bean, f.getName());
			iterable = Iterable.class.isAssignableFrom(clazz);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {}
		if(!nested && !iterable){
			strategy = stateSimple;
		} else if(nested && !iterable){
			strategy = stateSimpleNested;
		} else if(!nested && iterable){
			strategy = stateSimple;
		} else if(nested && iterable){
			strategy = stateMultivaluedNested;
		} else {
			throw new SolrRepositoryException("Stratégie non reconnue.");
		}
		return strategy;
	}

}
