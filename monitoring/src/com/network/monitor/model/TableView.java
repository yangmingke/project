package com.network.monitor.model;

import java.util.HashMap;
import java.util.Map;

public class TableView {
	private String title;
	private String[] fields;
	private String[] fieldNames;
	private boolean hasTotal= false;
	private Map<String, Object> data = new HashMap<String, Object>();
	
	public TableView(String title, boolean hasTotal) {
		this.title = title;
		this.hasTotal = hasTotal;
	}
	public TableView(String title) {
		this(title, false);
	}
	public TableView fields(String... vs) {
		this.fields = vs;
		return this;
	}
	public TableView fieldNames(String... vs) {
		this.fieldNames = vs;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String[] getFields() {
		return fields;
	}

	public void setFields(String[] fields) {
		this.fields = fields;
	}

	public String[] getFieldNames() {
		return fieldNames;
	}

	public void setFieldNames(String[] fieldNames) {
		this.fieldNames = fieldNames;
	}
	public boolean isHasTotal() {
		return hasTotal;
	}
	public void setHasTotal(boolean hasTotal) {
		this.hasTotal = hasTotal;
	}
	
	public TableView data(String key,Object value){
		data.put(key, value);
		return this;
	}
	@SuppressWarnings("all")
	public <T> T data(String key){
		return (T)data.get(key);
	}
}
