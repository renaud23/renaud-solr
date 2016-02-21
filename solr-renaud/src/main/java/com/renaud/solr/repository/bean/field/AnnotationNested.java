package com.renaud.solr.repository.bean.field;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import com.renaud.solr.annotation.SolrField;
import com.renaud.solr.annotation.tools.ClassUtil;
import com.renaud.solr.repository.SolrRepositoryException;

@Component
public class AnnotationNested<U> implements AnnotationAccess<U>{

	@Override
	public FieldValue readBeanValues(U bean, Field f, SolrField a) {
		Assert.notNull(bean, "Le bean est null.");
		try {
			if(PropertyUtils.isReadable(bean, a.property())){
				return FieldValue.Builder
						.newInstance()
						.setName(a.field())
						.setValue(PropertyUtils.getProperty(bean, a.property()))
						.build();
			} else{
				return null;
			}
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			throw new SolrRepositoryException("Impossible de lire une propriété.", e);
		}
	}

	@Override
	public void fillBean(U bean, Field f, SolrField a, Object value) {
		if(value != null){
			try {
				ClassUtil.instanciateAttributs(a.property(), bean);
				if(PropertyUtils.isWriteable(bean, a.property())){
					PropertyUtils.setProperty(bean, a.property(), ConvertUtils.convert(value, PropertyUtils.getPropertyType(bean, a.property())));
				}
			} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException e) {
				throw new SolrRepositoryException("fillBean", e);
			}
		}
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
