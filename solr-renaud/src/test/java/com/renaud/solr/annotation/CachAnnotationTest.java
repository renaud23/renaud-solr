package com.renaud.solr.annotation;

import java.lang.reflect.Field;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.renaud.solr.annotation.tools.CachAnnotation;
import com.renaud.solr.repository.server.SolrClientFactory;
import com.renaud.solr.test.base.BaseTest;
import com.renaud.solr.test.model.Client;

public class CachAnnotationTest extends BaseTest{
	
	@Autowired
	private CachAnnotation cachAnnotation;

	@Test
	public void getAnnotedField(){
		// W
		Field[] fields = cachAnnotation.getAnnotedFields(Client.class, SolrField.class);
		Field[] fieldId = cachAnnotation.getAnnotedFields(Client.class, SolrId.class);
		// T
		Assert.assertNotNull(fields.length);
		Assert.assertEquals(2, fields.length);
		Assert.assertNotNull(fieldId.length);
		Assert.assertEquals(1, fieldId.length);
	}

	@Override
	public SolrClientFactory getFactory() {
		return null;
	}

	@Override
	public void before() {}
}
