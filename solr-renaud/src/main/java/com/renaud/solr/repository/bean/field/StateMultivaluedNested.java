package com.renaud.solr.repository.bean.field;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import org.apache.commons.beanutils.NestedNullException;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import com.google.common.collect.Lists;
import com.renaud.solr.annotation.SolrField;
import com.renaud.solr.annotation.tools.ClassUtil;
import com.renaud.solr.repository.SolrRepositoryException;


@Component
public class StateMultivaluedNested<U> implements SolrFieldAccess<U>{
	
	public final static String NULL_ITERABLE_VALUE = "NULL_ITERABLE_VALUE";

	@SuppressWarnings({ "unchecked" })
	@Override
	public FieldValue readBeanValues(U bean, SolrField a, Field f) {
		try {
			List<Object> values = Lists.newArrayList();
			Collection<?> source = (Collection<U>) PropertyUtils.getProperty(bean, f.getName());
			if(!CollectionUtils.isEmpty(source)){
				String nestedPropertyName = StringUtils.substring(a.property(), f.getName().length() + 1);
				for(Object o : source){
					Object value = NULL_ITERABLE_VALUE;
					try{
						value = PropertyUtils.getProperty(o, nestedPropertyName);
					}catch (NestedNullException e){}
					values.add(value);
				}
			}
			
			return FieldValue.Builder
					.newInstance()
					.setName(a.field())
					.setValue(values)
					.build();
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			throw new SolrRepositoryException("Impossible de lire la valeur d'un champ.", e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void fillBean(U bean, SolrField a, Field f, Object value) {
		
		
		try {
			Class<?> typeListe =  PropertyUtils.getPropertyType(bean, f.getName());
			Collection<U> cible = (Collection<U>) PropertyUtils.getProperty(bean, f.getName());
			String nestedPropertyName = StringUtils.substring(a.property(), f.getName().length() + 1);
			Class<?> classGeneric = (Class<?>) ((ParameterizedType) f.getGenericType()).getActualTypeArguments()[0];
			
			if(cible == null){
				cible = (Collection<U>) ClassUtil.createCollection(typeListe);
			}


			
	// TODO
			
			System.out.println(value.getClass());
			
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			throw new SolrRepositoryException("Impossible de remplir un bean.", e);
		}
		
		
		
//		try {
//			String listeName = f.getName();
//			String beanName = StringUtils.substring(a.beanName(), listeName.length() + 1);
//
//			Class<?> typeListe = PropertyUtils.getPropertyType(bean, listeName);
//			Collection<Object> collection = StateUtils.createCollection(typeListe);
//
//			if (solrValue != null && solrValue instanceof Collection) {
//				ParameterizedType listeType = (ParameterizedType) f.getGenericType();
//				Class<?> classGeneric = (Class<?>) listeType.getActualTypeArguments()[0];
//				for (Object o : ((Collection<?>) solrValue)) {
//					Object value = classGeneric.newInstance();
//
//					ReflectUtils.instanciateAttributs(beanName, value);
//					Object oConverted = ConvertUtils.convert(o, PropertyUtils.getPropertyType(value, beanName));
//					PropertyUtils.setProperty(value, beanName, oConverted);
//					collection.add(value);
//				}
//			}
//			if (collection != null)
//				PropertyUtils.setProperty(bean, listeName, collection);
//		}
//		catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | InstantiationException e) {
//			throw new SolrInseeException(ERROR_FILL_FIELD + f.getName(), e);
//		}
	}

}
