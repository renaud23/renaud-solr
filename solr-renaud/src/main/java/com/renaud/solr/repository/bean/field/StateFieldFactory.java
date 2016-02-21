package com.renaud.solr.repository.bean.field;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
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
	public FieldValue readBeanValues(U bean, SolrField a, Field f) {
		return getStrategy(bean, a, f).readBeanValues(bean, a, f);
	}
	
	
	@Override
	public void fillBean(U bean, SolrField a, Field f, Object value) {

//		Object value = fields.stream().filter((f)-> {return Objects.equal(f.getName(), a.field());}).findFirst().get().getValue();
		Pair<SolrFieldAccess<U>, Object> pair = getStrategyValue(bean, a, f, value);
		pair.getLeft().fillBean(bean, a, f, pair.getRight());
	}
	
	private Pair<SolrFieldAccess<U>, Object> getStrategyValue(U bean, SolrField a, Field f, Object value){
		Assert.notNull(bean, "Le bean be doit pas être null.");
		SolrFieldAccess<U> strategy = getStrategy(bean, a, f);
		Object v = null;
		
		
		
		
		return new MutablePair<>(strategy, v);
	}
	
	
	private SolrFieldAccess<U> getStrategy(boolean nested, boolean iterable){
		SolrFieldAccess<U> strategy = null;
	
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
	
	private SolrFieldAccess<U> getStrategy(U bean, SolrField a, Field f){
		Assert.notNull(bean, "Le bean be doit pas être null.");
		
		boolean nested = !StringUtils.isBlank(a.property());
		boolean iterable = false;
		try {
			iterable = Iterable.class.isAssignableFrom(PropertyUtils.getPropertyType(bean, f.getName()));
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {}
		
		return getStrategy(nested, iterable);
	}

}
