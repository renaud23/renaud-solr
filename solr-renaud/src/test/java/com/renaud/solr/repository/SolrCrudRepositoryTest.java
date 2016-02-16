package com.renaud.solr.repository;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.collect.Lists;
import com.renaud.solr.repository.server.SolrClientFactory;
import com.renaud.solr.test.base.BaseTest;
import com.renaud.solr.test.base.ClientRepository;
import com.renaud.solr.test.model.Adresse;
import com.renaud.solr.test.model.Client;
import com.renaud.solr.test.model.LoremClientBuilder;



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
		Assert.assertEquals("Aristide Briand", c.getAdresse().getRue());
		Assert.assertEquals(new Integer(3), c.getAdresse().getNumero());
	}
	
	@Test
	public void saveAll(){
		// G
		List<Client> clients = Lists.newArrayList();
		for(int i=0;i<10;i++){
			clients.add(LoremClientBuilder.getClient());
		}
		// W
		clientRepository.save(clients);
		// T
		clients.stream().forEach(this::findAndCompare);
	}
	
	@Test
	public void findAll(){
		// G
		List<Client> clients = Lists.newArrayList();
		for(int i=0;i<20;i++){
			clients.add(LoremClientBuilder.getClient());
		}
		clientRepository.save(clients);
		// W
		Iterable<Client> it = clientRepository.findAll();
		// T
		int i = 0;
		for(Client c : it){
			i++;
			clients.contains(c);
		}
		Assert.assertEquals(clients.size(), i);
		
	}
	
	private void findAndCompare(Client client){
		// W
		Client fromIndex = clientRepository.findOne(client.getId());
		// T
		Assert.assertNotNull(fromIndex);
		Assert.assertEquals(client.getId(), fromIndex.getId());
		Assert.assertEquals(client.getNom(), fromIndex.getNom());
		Assert.assertEquals(client.getPrenom(), fromIndex.getPrenom());
		Assert.assertEquals(client.getAdresse().getRue(), fromIndex.getAdresse().getRue());
		Assert.assertEquals(client.getAdresse().getNumero(), fromIndex.getAdresse().getNumero());
	}

	@Override
	public SolrClientFactory getFactory() {
		return clientRepository.getSolrClientFactory();
	}
}
