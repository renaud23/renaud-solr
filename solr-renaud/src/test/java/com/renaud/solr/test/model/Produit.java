package com.renaud.solr.test.model;

public class Produit {
	
	private String id;
	
	private String libelle;
	
	private double prix;
	
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	/*
	 * 
	 */
	public static class Builder{
		private Produit c;
		
		public static Builder newInstance(){
			Builder b = new Builder();
			b.c = new Produit();
			return b;
		}
		
		public Produit build(){
			return c;
		}
		
		public Builder setLibelle(String libelle) {
			c.libelle = libelle;
			return this;
		}
		
		public Builder setId(String id) {
			c.id = id;
			return this;
		}
		public Builder setPrix(double prix) {
			c.prix = prix;
			return this;
		}
	}
}
