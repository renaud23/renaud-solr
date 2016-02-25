package com.renaud.solr.query.pageable;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class SolrPageable implements Pageable{
	
	private int pageNumber = 0;
	private int pageSize = 10;
	private int offset = 0;
	private Sort sort = new Sort(Sort.DEFAULT_DIRECTION);

	@Override
	public int getPageNumber() {
		return pageNumber;
	}

	@Override
	public int getPageSize() {
		return pageSize;
	}

	@Override
	public int getOffset() {
		return offset;
	}

	@Override
	public Sort getSort() {
		return sort;
	}

	@Override
	public Pageable next() {
		return null;
	}

	@Override
	public Pageable previousOrFirst() {
		return null;
	}

	@Override
	public Pageable first() {
		return null;
	}

	@Override
	public boolean hasPrevious() {
		return false;
	}
}
