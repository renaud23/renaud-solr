package com.renaud.solr.repository;

public class SolrRepositoryException extends RuntimeException{
	
	public final static String OPERATION_EN_CHANTIER = "Operation en chantier";

	/**
	 * 
	 */
	private static final long serialVersionUID = -1581113631211332154L;

	public SolrRepositoryException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public SolrRepositoryException(String arg0) {
		super(arg0);
	}

}
