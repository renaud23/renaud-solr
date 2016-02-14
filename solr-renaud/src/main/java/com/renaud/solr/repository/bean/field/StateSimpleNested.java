package com.renaud.solr.repository.bean.field;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Component;
import com.renaud.solr.annotation.SolrField;
import com.renaud.solr.annotation.tools.ClassUtil;
import com.renaud.solr.repository.SolrRepositoryException;

@Component
public class StateSimpleNested<U> implements SolrFieldAccess<U>{

	@Override
	public FieldValue read(U bean, SolrField a, Field f) {
		try {
			return FieldValue.Builder
					.newInstance()
					.setName(a.field())
					.setValue(PropertyUtils.getProperty(bean, a.property()))
					.build();
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			throw new SolrRepositoryException("Impossible de lire la valeur d'un champ.", e);
		}
	}

	@Override
	public void fill(U bean, SolrField a, Field f, Object value) {
		try {
			ClassUtil.instanciateAttributs(a.property(), bean);
			Class<?> type = PropertyUtils.getPropertyType(bean, a.property());
			Object valueFinale = ConvertUtils.convert(value, type);
			PropertyUtils.setProperty(bean, a.property(), valueFinale);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException e) {
			throw new SolrRepositoryException("Impossible de ...", e);
		}
		
	}

}
