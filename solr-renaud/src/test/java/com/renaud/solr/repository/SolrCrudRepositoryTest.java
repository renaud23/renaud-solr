package com.renaud.solr.repository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.renaud.solr.repository.server.SolrClientFactory;
import com.renaud.solr.test.base.BaseTest;
import com.renaud.solr.test.model.Adresse;
import com.renaud.solr.test.model.Client;
import com.renaud.solr.test.model.ClientRepository;



public class SolrCrudRepositoryTest extends BaseTest{
	
	@Autowired
	private ClientRepository clientRepository;

	@Test
	public void save(){
		Client  c = new Client();
		c.setId("1");
		c.setNom("Genevois");
		c.setPrenom("Renaud");
		c.setAdresse(new Adresse("Aristide Briand", 3, "92170", "Vanves"));
		
		clientRepository.save(c);
	}
	
	
	@Test
	public void findOne(){
		// G
		clientRepository.save(Client.Builder.newInstance()
				.setId("renaud@genevois")
				.setNom("Genevois")
				.setPrenom("Renaud")
				.setAdresse(Adresse.Builder.newInstance()
						.setRue("Aristide Briand")
						.setNumero(3)
						.setCodePostal("92170")
						.setVille("Vanves")
						.build())
				.build());
		// W
		Client c = clientRepository.findOne("renaud@genevois");
		// T
		Assert.assertNotNull(c);
		Assert.assertEquals("renaud@genevois", c.getId());
		Assert.assertEquals("Genevois", c.getNom());
		Assert.assertEquals("Renaud", c.getPrenom());
		Assert.assertEquals("Aristide Briand", c.getAdresse().getVille());
		Assert.assertEquals(new Integer(3), c.getAdresse().getNumero());
	}

	@Override
	public SolrClientFactory getFactory() {
		return clientRepository.getClientFactory();
				
	}
}
