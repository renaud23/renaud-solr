package com.renaud.solr.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.renaud.solr.repository.server.SolrClientFactory;
import com.renaud.solr.test.base.BaseTest;
import com.renaud.solr.test.model.Adresse;
import com.renaud.solr.test.model.LoremAdresseBuilder;
import com.renaud.solr.test.repository.AdresseRepository;

public class SolrCrudRepositoryAdresseTest extends BaseTest{
	@Autowired
	private AdresseRepository adresseRepository;
	
	@Test
	public void findAll(){
		// G
		List<Adresse> adresses = Lists.newArrayList();
		for(int i=0;i<331;i++){
			adresses.add(LoremAdresseBuilder.getAdresse());
		}
		adresseRepository.save(adresses);
		// W
		Iterable<Adresse> it = adresseRepository.findAll();
		// T
		int i = 0;
		for(Adresse c : it){
			i++;
			adresses.contains(c);
		}
		Assert.assertEquals(adresses.size(), i);
	}

	@Override
	public SolrClientFactory getFactory() {
		return adresseRepository.getSolrClientFactory();
	}
}
