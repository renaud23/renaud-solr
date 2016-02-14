package com.renaud.solr.query.filter;

import java.util.List;

import org.springframework.util.Assert;

import com.google.common.collect.Lists;

public class FilterComposite implements Filter{
	private List<Filter> filters = Lists.newArrayList();
	

	
	public void addFilter(Filter filter){
		Assert.notNull(filter, "Le filtre est null.");
		this.filters.add(filter);
	}



	@Override
	public String getFilter() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
