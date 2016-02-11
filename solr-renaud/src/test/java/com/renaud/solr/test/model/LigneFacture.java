package com.renaud.solr.test.model;


public class LigneFacture {
	private String idFacture;
	private Produit produit;
	private int quantite;
	
	
	
	public String getIdFacture() {
		return idFacture;
	}
	public void setIdFacture(String idFacture) {
		this.idFacture = idFacture;
	}
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	
	/*
	 * 
	 */
	public static class Builder{
		private LigneFacture c;
		
		public static Builder newInstance(){
			Builder b = new Builder();
			b.c = new LigneFacture();
			return b;
		}
		
		public LigneFacture build(){
			return c;
		}
		
		public Builder setIdFacture(String idFacture) {
			c.idFacture = idFacture;
			return this;
		}
		
		public Builder setProduit(Produit produit) {
			c.produit = produit;
			return this;
		}

		public Builder setQuantite(int quantite) {
			c.quantite = quantite;
			return this;
		}
	}
}
