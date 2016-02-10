package com.renaud.solr.test.model;

import com.renaud.solr.annotation.SolrEntity;

@SolrEntity
public class Adresse {

	private String rue;	
	private Integer numero;	
	private String codePostal;
	private String ville;
	
	public Adresse(){}
	
	public Adresse(String rue, Integer numero, String codePostal, String ville) {
		this.rue = rue;
		this.numero = numero;
		this.codePostal = codePostal;
		this.ville = ville;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	
	public static class Builder{
		private Adresse a;
		
		public static Builder newInstance(){
			Builder b = new Builder();
			b.a = new Adresse();
			return b;
		}
		
		public Adresse build(){
			return a;
		}
		
		public Builder setRue(String rue) {
			a.rue = rue;
			return this;
		}
	
		public Builder setNumero(Integer numero) {
			a.numero = numero;
			return this;
		}
	
		public Builder setCodePostal(String codePostal) {
			a.codePostal = codePostal;
			return this;
		}
	
		public Builder setVille(String ville) {
			a.ville = ville;
			return this;
		}
	}
}
