package com.renaud.solr.query.filter;

import org.springframework.util.Assert;
import com.google.common.base.Objects;

public class FilterLeaf implements Filter{

	private String field;
	private String value;
	private boolean quoted = false;
	private boolean negate = false;
	
	public String toString(){
		return Objects
				.toStringHelper(FilterLeaf.class)
				.add("field", field)
				.add("value", value)
				.add("negate", negate)
				.toString();
	}

	@Override
	public String getFilter() {
		Assert.notNull(field, "Le champ du filtre est null.");
		Assert.notNull(value, "La valeur du filtre est null.");
		
		StringBuilder bld = new StringBuilder();
		if(negate){
			bld.append('!');
		}
		bld.append(field);
		bld.append(':');
		String val = value;
		if(quoted){
			val = "\"" + value + "\"";
		}
		bld.append(val);
		
		return bld.toString();
	}


	public static class Builder {
		private FilterLeaf f;
		
		public Builder field(String field){
			f.field = field;
			return this;
		}
		
		public Builder value(Object value){
			f.value = value.toString();
			return this;
		}
		
		public Builder quoted(){
			f.quoted = true;
			return this;
		}
		
		public Builder unquoted(){
			f.quoted = false;
			return this;
		}
		
		public Builder negate(){
			f.negate = true;
			return this;
		}
		
		public static Builder newInstance(){
			Builder b = new Builder();
			b.f = new FilterLeaf();
			return b;
		}
		
		public FilterLeaf build(){
			return f;
		}
	}
}
