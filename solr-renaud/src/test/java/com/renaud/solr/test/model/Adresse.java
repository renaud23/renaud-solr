package com.renaud.solr.test.model;

import com.google.common.base.Objects;
import com.renaud.solr.annotation.SolrEntity;
import com.renaud.solr.annotation.SolrField;

@SolrEntity
public class Adresse {
	
	public static int ID = 0;
	
	@SolrField(field = "id")
	private String id;
	@SolrField(field = "adresse_rue")
	private String rue;	
	@SolrField(field = "adresse_numero")
	private Integer numero;	
	@SolrField(field = "adresse_code_postal")
	private String codePostal;
	@SolrField(field = "adresse_ville")
	private String ville;
	
	public Adresse(){}
	
	public Adresse(String rue, Integer numero, String codePostal, String ville) {
		this.rue = rue;
		this.numero = numero;
		this.codePostal = codePostal;
		this.ville = ville;
	}
	
	public String toString(){
		return Objects
				.toStringHelper(Adresse.class)
				.add("id", id)
				.add("numero", numero)
				.add("rue", rue)
				.add("codePostal", codePostal)
				.add("ville", ville)
				.toString();
	}
	
	public boolean equals(Object o){
		boolean state = false;
		if(o instanceof Adresse){
			Adresse c = (Adresse) o;
			state = Objects.equal(this.id, c.id) && 
					Objects.equal(this.rue, c.rue) && 
					Objects.equal(this.numero, c.numero) &&
					Objects.equal(this.codePostal, c.codePostal) &&
					Objects.equal(this.ville, c.ville);
		}
		
		return state;
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
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static class Builder{
		private Adresse a;
		
		public static Builder newInstance(){
			Builder b = new Builder();
			b.a = new Adresse();
			return b;
		}
		
		public Adresse build(){
			this.a.id = String.valueOf(ID++);
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
