package com.renaud.solr.query;

import java.util.List;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.renaud.solr.query.filter.Filter;

public class SimpleQuery implements Query{

	private List<Filter> filters = Lists.newArrayList();
	
	private List<String> tokens = Lists.newArrayList();
	
	private int start;
	
	private int rows;
	
	public String toString(){
		return Objects
				.toStringHelper(SimpleQuery.class)
				.add("filters", filters)
				.add("tokens", tokens)
				.add("start", start)
				.add("rows", rows)
				.toString();
	}

	@Override
	public void addToken(String token) {
		tokens.add(token);
	}

	@Override
	public void addFilter(Filter filter) {
		filters.add(filter);
	}
	
	public static class Builder {
		private SimpleQuery f;
	
		public Builder addToken(String token) {
			f.addToken(token);
			return this;
		}

		public Builder addFilter(Filter filter) {
			f.addFilter(filter);
			return this;
		}
		public Builder setStart(int start) {
			f.start = start;
			return this;
		}
		public Builder setRows(int rows) {
			f.rows = rows;
			return this;
		}
		public static Builder newInstance(){
			Builder b = new Builder();
			b.f = new SimpleQuery();
			return b;
		}
		
		public SimpleQuery build(){
			return f;
		}
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public List<Filter> getFilters() {
		return filters;
	}

	public void setFilters(List<Filter> filters) {
		this.filters = filters;
	}

	public List<String> getTokens() {
		return tokens;
	}

	public void setTokens(List<String> tokens) {
		this.tokens = tokens;
	}
}
