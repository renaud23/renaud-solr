package com.renaud.solr.test.model;

import java.util.List;

import org.springframework.data.annotation.Id;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.renaud.solr.annotation.SolrEntity;
import com.renaud.solr.annotation.SolrField;

@SolrEntity
public class Client {
	
	@Id
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
	@SolrField(field="tags")
	private List<String> tags = Lists.newArrayList();
	@SolrField(field="contact_id", property="contacts.id")
	@SolrField(field="contact_adresse_ville", property="contacts.adresse.ville")
	private List<Client> contacts = Lists.newArrayList();
	
	public String toString(){
		return Objects
				.toStringHelper(Client.class)
				.add("id", id)
				.add("prenom", prenom)
				.add("nom", nom)
				.add("adresse", adresse)
				.add("tags", tags)
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
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
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
		
		public Builder addTag(String tag) {
			c.tags.add(tag);
			return this;
		}
		
		public Builder addContact(Client contact) {
			c.contacts.add(contact);
			return this;
		}
	}
}
