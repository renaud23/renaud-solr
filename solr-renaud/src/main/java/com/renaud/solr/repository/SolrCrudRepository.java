package com.renaud.solr.repository;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;

import com.google.common.collect.Lists;
import com.renaud.solr.config.SolrRepository;
import com.renaud.solr.query.Query;
import com.renaud.solr.query.SimpleQuery;
import com.renaud.solr.query.result.SolrResponse;
import com.renaud.solr.repository.bean.SolrBeanService;
import com.renaud.solr.repository.bean.field.FieldValue;
import com.renaud.solr.repository.server.SolrClientFactory;
import com.renaud.splr.query.pageable.DocumentIterable;
import com.renaud.splr.query.pageable.DocumentIterator;

public class SolrCrudRepository <T, ID extends Serializable> implements SolrRepository<T, ID> {
	
	private Class<T> domainClass;

	private SolrBeanService<T,ID> solrBeanService;
	
	private SolrClientFactory solrClientFactory;
	

	public SolrCrudRepository(SolrBeanService<T, ID> solrBeanService) {
		this.solrBeanService = solrBeanService;
	}

	@Override
	public <S extends T> S save(S entity) {
		Assert.notNull(entity, "Impossible de sauvegarder un bean null.");
		SolrInputDocument document = new SolrInputDocument();
		solrBeanService
			.read(entity)
			.stream()
			.filter((field)-> {return field.getValue() != null && !StringUtils.isBlank(field.getName());})
			.forEach((field)->{ document.addField(field.getName(), field.getValue()); });
		try {
			this.solrClientFactory.getClient().add(document);
			
			return entity;
		} catch (SolrServerException | IOException e) {
			throw new SolrRepositoryException("Impossible de sauver une entit�.");
		}
	}

	@Override
	public <S extends T> Iterable<S> save(Iterable<S> entities) {
		Assert.notNull(entities, "Impossible de sauvegarder une collection null.");
		for(S e : entities){
			this.save(e);
		}
		return entities;
	}

	@Override
	public T findOne(ID id) {
		try {
			SolrDocument  document = this.solrClientFactory.getClient().getById(id.toString());
			if(document != null){
				List<FieldValue> fields = 
						document.getFieldNames().stream().map((name)->{ 
								return  FieldValue.Builder.newInstance()
											.setName(name)
											.setValue(document.get(name)).build(); })
							.collect(Collectors.toList());
				
				return solrBeanService.fill(fields, domainClass);	
			} else {
				return null;
			}
		} catch (SolrRepositoryException | SolrServerException | IOException e) {
			throw new SolrRepositoryException("Impossible de lire dans l'index.", e);
		}
	}
	
	@Override
	public Iterable<T> findAll() {
		return new DocumentIterable<>(Query.newQuery()
				.addToken("*:*")
				.setStart(0)
				.setRows(10)
				.build(), this); 
	}
	
	@Override
	public SolrResponse<T> query(SimpleQuery query) throws SolrRepositoryException {
		SolrQuery sq = new SolrQuery();
		
		StringBuilder bld = new StringBuilder();
		query.getTokens().stream().forEach( (a)->{bld.append(a);});
		sq.setQuery(bld.toString());
		
		query.getFilters().forEach((f)->{ sq.addFilterQuery(f.getFilter()); });
		
		
		try {
			QueryResponse  sr = this.solrClientFactory.getClient().query(sq);
			List<T> documents = Lists.newArrayList();
			for(SolrDocument document : sr.getResults()){
				List<FieldValue> fields = 
						document.getFieldNames().stream().map((name)->{ 
								return  FieldValue.Builder.newInstance()
											.setName(name)
											.setValue(document.get(name)).build(); })
							.collect(Collectors.toList());
				
				 documents.add(solrBeanService.fill(fields, domainClass));
			}
			
			
			return null;
		} catch (SolrServerException | IOException e) {
			throw new SolrRepositoryException("Impossible de passer une requ�te.", e);
		}
	}

	@Override
	public boolean exists(ID id) {
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

	/* ******************************************************** */
	
	public SolrResponse<T> getResponse(Query query){
		throw new SolrRepositoryException(SolrRepositoryException.OPERATION_EN_CHANTIER);
	}
	
	/* ******************************************************** */
	
	@Override
	public Iterable<T> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* ******************************************** */
	public Class<T> getDomainClass() {
		return domainClass;
	}

	public void setDomainClass(Class<T> domainClass) {
		this.domainClass = domainClass;
	}

	public void setSolrClientFactory(SolrClientFactory solrClientFactory) {
		this.solrClientFactory = solrClientFactory;
	}

	@Override
	public SolrClientFactory getSolrClientFactory() {
		return this.solrClientFactory;
	}
	
}
