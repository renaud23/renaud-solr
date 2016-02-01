package com.renaud.solr.repository.bean;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renaud.solr.annotation.tools.CachAnnotation;

@Service
public class SolrBeanServiceImpl<U> implements SolrBeanService<U>{
	
	@Autowired
	private CachAnnotation cachAnnotation;

	@Override
	public Map<String, Object> read(U u) {
		
		return null;
	}

	@Override
	public U fill() {
		// TODO Auto-generated method stub
		return null;
	}



}
