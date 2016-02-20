package com.renaud.solr.repository.bean.field;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Component;
import com.renaud.solr.annotation.SolrField;
import com.renaud.solr.repository.SolrRepositoryException;

@Component
public class StateSimple<U> implements SolrFieldAccess<U>{

	@Override
	public FieldValue readBeanValues(U bean, SolrField a, Field f) {
		try {
			return FieldValue.Builder
					.newInstance()
					.setName(a.field())
					.setValue(PropertyUtils.getProperty(bean, f.getName()))
					.build();
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			throw new SolrRepositoryException("Impossible de lire la valeur d'un champ.", e);
		}
	}

	@Override
	public void fillBean(U bean, SolrField a, Field f, Object value) {
		try {
			BeanUtils.setProperty(bean, a.field(), value);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new SolrRepositoryException("",e);
		}
	}

}
