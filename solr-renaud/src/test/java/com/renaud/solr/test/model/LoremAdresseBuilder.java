package com.renaud.solr.test.model;

import java.util.Random;
import java.util.UUID;

import de.svenjacobs.loremipsum.LoremIpsum;

public class LoremAdresseBuilder {
	private static Random RND = new Random();
	
	private static LoremIpsum newLorem(){
		return new LoremIpsum();
	}
	
	public static Adresse getAdresse(){
		return Adresse.Builder.newInstance()
						.setRue(UUID.randomUUID().toString())
						.setNumero(RND.nextInt(120) + 1)
						.setCodePostal(String.valueOf(RND.nextInt(80000) + 10000))
						.setVille(UUID.randomUUID().toString())
						.build();
	}
	
	
	
}
