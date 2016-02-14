package com.renaud.solr.query.filter;

import java.util.List;

import org.springframework.util.Assert;

import com.google.common.collect.Lists;

public class FilterComposite implements Filter{
	private List<Filter> filters = Lists.newArrayList();
	
	private boolean groupe;
	
	public void appendFilter(Filter filter){
		Assert.notNull(filter, "Le filtre est null.");
		this.filters.add(filter);
	}

	@Override
	public String getFilter() {
		StringBuilder bld = new StringBuilder();
		
		if(groupe) bld.append('(');
		filters.stream().forEach((e)->{ bld.append(e.getFilter()); });
		if(groupe) bld.append(')');
		
		return bld.toString();
	}
	
	public static class Builder {
		private FilterComposite f;
		
		public Builder groupe(){
			f.groupe = true;
			return this;
		}
		
		public Builder appendFilter(Filter filter){
			f.appendFilter(filter);
			return this;
		}
		
		public Builder or(){
			f.appendFilter(()-> " OR ");
			return this;
		}
		
		public Builder and(){
			f.appendFilter(()-> " AND ");
			return this;
		}
		
		public static Builder newInstance(){
			Builder b = new Builder();
			b.f = new FilterComposite();
			return b;
		}
		
		public FilterComposite build(){
			return f;
		}
	}
}
