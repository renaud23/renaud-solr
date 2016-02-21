package com.renaud.solr.repository.bean.field;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.renaud.solr.repository.SolrRepositoryException;

@Component
public class FieldAccessFactory<U> implements FieldAccess<U>{

	@Autowired
	private FieldAccess<U> fieldSimple;
	@Autowired
	private FieldAccess<U> fieldMultivalued;
	
	@Override
	public List<FieldValue> readBeanValues(U bean, Field f) {
		return getStrategy(bean, f).readBeanValues(bean, f);
	}
	
	@Override
	public U makeBean(Field f, FieldMap value) {
		throw new SolrRepositoryException("EN CHANTIER");
	}
	
	
	private FieldAccess<U> getStrategy(U bean, Field f){
		boolean iterable = false;
		try {
			iterable = Iterable.class.isAssignableFrom(PropertyUtils.getPropertyType(bean, f.getName()));
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {}
		FieldAccess<U> strategy = null;
		
		if(!iterable){
			strategy = fieldSimple;
		} else {
			strategy = fieldMultivalued;
		}
		
		return strategy;
	}


//	@Override
//	public FieldValue readBeanValues(U bean, SolrField a, Field f) {
//		return getStrategy(bean, a, f).readBeanValues(bean, a, f);
//	}
//	
//	
//	@Override
//	public void fillBean(U bean, SolrField a, Field f, Object value) {
//		getStrategy(bean, a, f).fillBean(bean, a, f, value);
//	}
//	
//	private SolrFieldAccess<U> getStrategy(U bean, SolrField a, Field f){
//		Assert.notNull(bean, "Le bean be doit pas être null.");
//		SolrFieldAccess<U> strategy = null;
//		
//		boolean nested = !StringUtils.isBlank(a.property());
//		boolean iterable = false;
//		try {
//			iterable = Iterable.class.isAssignableFrom(PropertyUtils.getPropertyType(bean, f.getName()));
//		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {}
//		if(!nested && !iterable){
//			strategy = stateSimple;
//		} else if(nested && !iterable){
//			strategy = stateSimpleNested;
//		} else if(!nested && iterable){
//			strategy = stateSimple;
//		} else if(nested && iterable){
//			strategy = stateMultivaluedNested;
//		} else {
//			throw new SolrRepositoryException("Stratégie non reconnue.");
//		}
//		return strategy;
//	}

}
