package com.renaud.solr.annotation;

import java.lang.reflect.Field;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.reanud.solr.repository.BaseTest;
import com.renaud.solr.annotation.tools.CachAnnotation;
import com.renaud.solr.test.model.Client;

public class CachAnnotationTest extends BaseTest{
	
	@Autowired
	private CachAnnotation cachAnnotation;

	@Test
	public void getAnnotedField(){
		// W
		Field[] fields = cachAnnotation.getAnnotedField(Client.class, SolrField.class);
		Field[] fieldId = cachAnnotation.getAnnotedField(Client.class, SolrId.class);
		// T
		Assert.assertNotNull(fields.length);
		Assert.assertEquals(2, fields.length);
		Assert.assertNotNull(fieldId.length);
		Assert.assertEquals(1, fieldId.length);
	}
}
