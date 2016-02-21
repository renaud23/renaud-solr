package com.renaud.solr.repository.bean.field;

import java.lang.reflect.Field;
import java.util.List;



public interface FieldAccess<U> {
	List<FieldValue> readBeanValues(U bean, Field f);
	
	U makeBean(Field f, FieldMap value);

}
