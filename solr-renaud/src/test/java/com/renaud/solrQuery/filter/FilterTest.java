package com.renaud.solrQuery.filter;



import org.junit.Assert;
import org.junit.Test;

import com.renaud.solr.query.filter.Filter;


public class FilterTest {

	@Test
	public void simpleFiltre(){
		// G
		Filter f = Filter.newSimpleFilter().field("champ").value("valeur").build();
		// W
		String fv = f.getFilter();
		// T
		Assert.assertEquals("champ:valeur", fv);
	}
	
	
}
