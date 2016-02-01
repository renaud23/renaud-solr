package com.renaud.solr.repository;

import java.io.IOException;
import java.io.Serializable;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.data.repository.CrudRepository;

import com.renaud.solr.repository.server.SolrClientFactory;

public abstract class SolrCrudRepository <T, ID extends Serializable> implements CrudRepository<T, ID> {
	
	public abstract SolrClientFactory getClientFactory();

	@Override
	public <S extends T> S save(S entity) {
		SolrInputDocument document = new SolrInputDocument();
		document.addField("", null);
		try {
			
			getClientFactory().getClient().add(document);
			
			return null;
		} catch (SolrServerException | IOException e) {
			throw new SolrRepositoryException(SolrRepositoryException.OPERATION_EN_CHANTIER);
		}
	}

	@Override
	public <S extends T> Iterable<S> save(Iterable<S> entities) {
		throw new SolrRepositoryException(SolrRepositoryException.OPERATION_EN_CHANTIER);
	}

	@Override
	public T findOne(ID id) {
		throw new SolrRepositoryException(SolrRepositoryException.OPERATION_EN_CHANTIER);
	}

	@Override
	public boolean exists(ID id) {
		throw new SolrRepositoryException(SolrRepositoryException.OPERATION_EN_CHANTIER);
	}

	@Override
	public Iterable<T> findAll() {
		throw new SolrRepositoryException(SolrRepositoryException.OPERATION_EN_CHANTIER);
	}

	@Override
	public Iterable<T> findAll(Iterable<ID> ids) {
		throw new SolrRepositoryException(SolrRepositoryException.OPERATION_EN_CHANTIER);
	}

	@Override
	public long count() {
		throw new SolrRepositoryException(SolrRepositoryException.OPERATION_EN_CHANTIER);
	}

	@Override
	public void delete(ID id) {
		throw new SolrRepositoryException(SolrRepositoryException.OPERATION_EN_CHANTIER);
	}

	@Override
	public void delete(T entity) {
		throw new SolrRepositoryException(SolrRepositoryException.OPERATION_EN_CHANTIER);
	}

	@Override
	public void delete(Iterable<? extends T> entities) {
		throw new SolrRepositoryException(SolrRepositoryException.OPERATION_EN_CHANTIER);
	}

	@Override
	public void deleteAll() {
		throw new SolrRepositoryException(SolrRepositoryException.OPERATION_EN_CHANTIER);
	}

}
