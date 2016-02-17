package com.renaud.solr.test.model;

import java.util.Random;

import de.svenjacobs.loremipsum.LoremIpsum;

public class LoremClientBuilder {
	private static Random RND = new Random();
	private static int ID = 1;
	
	private static LoremIpsum newLorem(){
		return new LoremIpsum();
	}
	
	public static Client getClient(){
		return Client.Builder.newInstance()
				.setId(String.valueOf(ID++))
				.setNom(new LoremIpsum().getWords(10, 2))
				.setPrenom(newLorem().getWords(10, 3))
				.setAdresse(Adresse.Builder.newInstance()
						.setRue(newLorem().getWords(3))
						.setNumero(RND.nextInt(120) + 1)
						.setCodePostal(String.valueOf(RND.nextInt(80000) + 10000))
						.setVille(newLorem().getWords(10, 3))
						.build())
				.build();
	}
	
	
	
}
