package com.renaud.solr.test.model;

import com.renaud.solr.annotation.SolrField;
import com.renaud.solr.annotation.SolrId;

public class Client {
	
	@SolrId
	private String id;
	
	@SolrField(field = "nom")
	private String nom;
	
	@SolrField(field = "nom")
	@SolrField(field = "prenom")
	private String prenom;
	
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
