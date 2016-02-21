package com.renaud.solr.repository.bean.field;

import java.lang.reflect.Field;
import org.springframework.stereotype.Component;
import com.renaud.solr.annotation.SolrField;
import com.renaud.solr.repository.SolrRepositoryException;

@Component
public class AnnotationNested<U> implements AnnotationAccess<U>{

	@Override
	public FieldValue readBeanValues(U bean, Field f, SolrField a) {
		return FieldValue.FieldNull;
	}

	@Override
	public void fillBean(U bean, Field f, SolrField a, Object value) {
		throw new SolrRepositoryException("EN CHANTIER");
	}





//	@Override
//	public FieldValue readBeanValues(U bean, SolrField a, Field f) {
//		try {
//			Object value = null;
//			try{
//				value = PropertyUtils.getProperty(bean, a.property());
//			}catch(NestedNullException e){}
//			
//			return FieldValue.Builder
//					.newInstance()
//					.setName(a.field())
//					.setValue(value)
//					.build();
//		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
//			throw new SolrRepositoryException("Impossible de lire la valeur d'un champ.", e);
//		}
//	}
//
//	@Override
//	public void fillBean(U bean, SolrField a, Field f, Object value) {
//		try {
//			ClassUtil.instanciateAttributs(a.property(), bean);
//			Class<?> type = PropertyUtils.getPropertyType(bean, a.property());
//			Object valueFinale = ConvertUtils.convert(value, type);
//			PropertyUtils.setProperty(bean, a.property(), valueFinale);
//		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException e) {
//			throw new SolrRepositoryException("Impossible de ...", e);
//		}
//		
//	}

}
