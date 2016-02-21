package com.renaud.solr.repository.bean.field;

import java.io.Serializable;
import java.util.Map;

import com.google.common.collect.Maps;

public class FieldMap implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2079671938111100170L;
	
	private Map<String, FieldValue> data = Maps.newHashMap();
	
	public FieldMap(){}
	
	public FieldMap(Iterable<FieldValue> fields){
		for(FieldValue v : fields){
			this.data.put(v.getName(), v);
		}
	}
	
	public boolean isField(String field){
		return data.containsKey(field);
	}
	
	public FieldValue get(String fieldName){
		return data.get(fieldName);
	}
	
	public void put(String fieldName,  FieldValue value){
		this.data.put(fieldName,value);
	}
}
