package com.ucp.bean;

import java.util.Map;

public class MemInfo {

	private String key;
	private String type;
	private String field;
	private Map<String,String> hashMap;
	public Map<String, String> getHashMap() {
		return hashMap;
	}
	public void setHashMap(Map<String, String> hashMap) {
		this.hashMap = hashMap;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getExpries() {
		return expries;
	}
	public void setExpries(int expries) {
		this.expries = expries;
	}
	private int expries;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	private String value;
	private long time;
	private String callId;
	public String getCallId() {
		return callId;
	}
	public void setCallId(String callId) {
		this.callId = callId;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	
}
