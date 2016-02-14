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
	
	@Test
	public void filter(){
		// G
		Filter f = Filter.newFilter()
				.appendFilter(Filter.newSimpleFilter().field("A").value(1l).build())
				.and()
				.appendFilter(Filter.newSimpleFilter().field("B").value(2l).build())
				.build();
		// W
		String fv = f.getFilter();
		// T
		Assert.assertEquals("A:1 AND B:2", fv.toUpperCase());
	}
	
	@Test
	public void filterComposite(){
		// G
		Filter f = 
				Filter.newFilter()
					.appendFilter(				
						Filter.newFilter()
							.appendFilter(Filter.newSimpleFilter().field("A").value(1l).build())
							.and()
							.appendFilter(Filter.newSimpleFilter().field("B").value(2l).build())
							.groupe()
							.build())
					.and()
					.appendFilter(
							Filter.newSimpleFilter().field("C").value(3l).negate().build())
					.build();
		// W
		String fv = f.getFilter();
		// T
		Assert.assertEquals("(A:1 AND B:2) AND !C:3", fv.toUpperCase());
	}
}
