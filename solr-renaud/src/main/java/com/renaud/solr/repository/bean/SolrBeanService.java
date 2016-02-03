package com.renaud.solr.repository.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.renaud.solr.repository.bean.field.FieldValue;

public interface SolrBeanService<U, ID extends Serializable> {
	
	public List<FieldValue> read(U u);
	
	public U fill();
	
}
