package com.ucp.bean;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class BaseInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o);
	}
	public int hashCode(Object o) {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}