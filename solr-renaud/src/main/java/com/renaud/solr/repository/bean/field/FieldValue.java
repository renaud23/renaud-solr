package com.renaud.solr.repository.bean.field;

import java.io.Serializable;

import com.google.common.base.Objects;


public class FieldValue implements Serializable{
	
	public final static FieldValue FieldNull = FieldValue.Builder.newInstance().setName(null).setValue(null).build();

	/**
	 * 
	 */
	private static final long serialVersionUID = -1236195649663829782L;

	private String name;
	
	private Object value;

	public FieldValue() {}

	public FieldValue(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.name, this.value);
	}

	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}
		if (!(object instanceof FieldValue)) {
			return false;
		}
		final FieldValue other = (FieldValue) object;
		return Objects.equal(this.name, other.name) && Objects.equal(this.value, other.value);
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(FieldValue.class).add("name", this.name).add("value", this.value).toString();
	}
	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	
	public Object getValue() {
		return value;
	}

	
	public void setValue(Object value) {
		this.value = value;
	}
	
	
	public static class Builder{
		private FieldValue f;
		
		public static Builder newInstance(){
			Builder b = new Builder();
			b.f = new FieldValue();
			return b;
		}
		
		public Builder setName(String name) {
			f.name = name;
			return this;
		}
		
		public Builder setValue(Object value) {
			f.value = value;
			return this;
		}
		
		public FieldValue build(){
			return f;
		}
	}
}
