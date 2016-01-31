package com.renaud.solr.test.model;


import org.springframework.stereotype.Component;

import com.renaud.solr.repository.SolrCrudRepository;

@Component
public class ClientRepository extends SolrCrudRepository<Client, String>{}
