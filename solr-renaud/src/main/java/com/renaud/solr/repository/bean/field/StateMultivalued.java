package com.renaud.solr.repository.bean.field;

import java.lang.reflect.Field;
import org.springframework.stereotype.Component;
import com.renaud.solr.annotation.SolrField;


@Component
public class StateMultivalued<U> implements SolrFieldAccess<U>{

	@Override
	public FieldValue read(U bean, SolrField a, Field f) {
		// TODO
		return FieldValue.FieldNull;
	}

	@Override
	public void fill(U bean, SolrField a, Field f, Object value) {
		// TODO
	}

}
