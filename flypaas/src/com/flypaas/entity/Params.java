package com.flypaas.entity;

import org.apache.ibatis.type.Alias;
@Alias(value="tb_flypaas_params")
public class Params {
	private long paramId ;
	private String paramType;
	private String paramTypeName;
	private String paramKey ;
	private String paramValue;
	private String paramOrder ;
	private TbSrvCallback tcallBack ;
	
	public long getParamId() {
		return paramId;
	}
	public void setParamId(long paramId) {
		this.paramId = paramId;
	}
	public String getParamType() {
		return paramType;
	}
	public void setParamType(String paramType) {
		this.paramType = paramType;
	}
	public String getParamTypeName() {
		return paramTypeName;
	}
	public void setParamTypeName(String paramTypeName) {
		this.paramTypeName = paramTypeName;
	}
	public String getParamKey() {
		return paramKey;
	}
	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	public String getParamOrder() {
		return paramOrder;
	}
	public void setParamOrder(String paramOrder) {
		this.paramOrder = paramOrder;
	}
	public TbSrvCallback getTcallBack() {
		return tcallBack;
	}
	public void setTcallBack(TbSrvCallback tcallBack) {
		this.tcallBack = tcallBack;
	}
	
	
	
}
