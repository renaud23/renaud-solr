package com.renaud.solr.test.model;

import com.google.common.base.Objects;
import com.renaud.solr.annotation.SolrEntity;
import com.renaud.solr.annotation.SolrField;
import com.renaud.solr.annotation.SolrId;
import com.renaud.solr.query.filter.FilterLeaf;

@SolrEntity
public class Client {
	
	@SolrId
	@SolrField(field = "id")
	private String id;
	@SolrField(field = "nom")
	private String nom;
	@SolrField(field = "prenom")
	private String prenom;
	@SolrField(field="adresse_rue", property="adresse.rue")
	@SolrField(field="adresse_numero", property="adresse.numero")
	@SolrField(field="adresse_code_postal", property="adresse.codePostal")
	@SolrField(field="adresse_ville", property="adresse.ville")
	private Adresse adresse;
	
	
	public String toString(){
		return Objects
				.toStringHelper(Client.class)
				.add("id", id)
				.add("prenom", prenom)
				.add("nom", nom)
				.add("adresse", adresse)
				.toString();
	}
	
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
	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	
	
	
	public static class Builder{
		private Client c;
		
		public static Builder newInstance(){
			Builder b = new Builder();
			b.c = new Client();
			return b;
		}
		
		public Client build(){
			return c;
		}
		
		public Builder setAdresse(Adresse adresse) {
			c.adresse = adresse;
			return this;
		}
		
		public Builder setNom(String nom) {
			c.nom = nom;
			return this;
		}
		
		public Builder setPrenom(String prenom) {
			c.prenom = prenom;
			return this;
		}
		
		public Builder setId(String id) {
			c.id = id;
			return this;
		}
	}
}
