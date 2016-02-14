package com.renaud.solr.query.filter;

public interface Filter {
	public String getFilter();
	
	
	
	
	
	public static FilterLeaf.Builder newSimpleFilter(){
		FilterLeaf.Builder b = FilterLeaf.Builder.newInstance();
		
		return b;
	}
	
	public static FilterComposite.Builder newFilter(){
		FilterComposite.Builder b = FilterComposite.Builder.newInstance();
		
		return b;
	}
}
