package com.ucpaas.commonservice.model.base;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ucpaas.commonservice.constant.ErrorCode;

/**
 * 操作结果对象
 * 
 * @author luke
 * 
 */
@JsonInclude(Include.NON_NULL)
public class RespInfo<T extends BaseInfo> {
	
	private Integer respCode;
	
	private String desc;
	/**
	 * 存放单个对象
	 */
	private T data;

	/**
	 * 存放集合对象
	 */
	private List<T> listData;

	public RespInfo() {
	}

	public RespInfo(ErrorCode errorCode) {
		this.respCode = errorCode.getCode();
		this.desc = errorCode.getMsg();
	}

	public T getData() {
		return data;
	}

	/**
	 * 设置单个对象
	 * 
	 * @param data
	 */
	public void setData(T data) {
		
		this.data = data;
	}

	public List<T> getListData() {
		return listData;
	}

	/**
	 * 设置集合对象
	 * 
	 * @param listData
	 */
	public void setListData(List<T> listData) {
		
		this.listData = listData;
	}

	public Integer getRespCode() {
		return respCode;
	}

	public void setRespCode(Integer respCode) {
		this.respCode = respCode;
	}
	
	public void setErrorCode(ErrorCode errorCode){
		this.respCode = errorCode.getCode();
		this.desc=errorCode.getMsg();
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
