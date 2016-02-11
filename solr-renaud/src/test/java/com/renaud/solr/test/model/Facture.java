package com.renaud.solr.test.model;

import java.util.List;

import com.google.common.collect.Lists;

public class Facture {
	
	private String idFacture;
	
	private List<LigneFacture> lignes = Lists.newArrayList();
	
	public String getIdFacture() {
		return idFacture;
	}
	public void setIdFacture(String idFacture) {
		this.idFacture = idFacture;
	}
	public List<LigneFacture> getLignes() {
		return lignes;
	}
	public void setLignes(List<LigneFacture> lignes) {
		this.lignes = lignes;
	}
	
	/*
	 * 
	 */
	public static class Builder{
		private Facture c;
		
		public static Builder newInstance(){
			Builder b = new Builder();
			b.c = new Facture();
			return b;
		}
		
		public Facture build(){
			return c;
		}
		
		public Builder setIdFacture(String idFacture) {
			c.idFacture = idFacture;
			return this;
		}
		
		public Builder addLigne(LigneFacture ligne){
			c.lignes.add(ligne);
			return this;
		}
	}
}
