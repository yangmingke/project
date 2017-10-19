package com.ucpaas.commonservice.model.base;

import java.io.Serializable;
import java.util.UUID;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ucpaas.commonservice.constant.Constants;
import com.ucpaas.commonservice.constant.ErrorCode;

/**
 * 基础实体类
 *
 */
@JsonInclude(Include.NON_NULL)
public class BaseInfo implements Serializable {
	private static final long serialVersionUID = -6015210973408629920L;
	
	/**
	 * 操作结果
	 */
	public Boolean actionResult = null;
	
	/**
	 * 错误码
	 */
	public Integer errorCode;
	
	/**
	 * 错误描述
	 */
	public String errorInfo;
	
	/**
	 * 日志Id
	 */
	public String logId = null;
	
	
	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(String errorInfo) {
		this.errorInfo = errorInfo;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o);
	}

	public int hashCode(Object o) {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
	/**
	 * 操作成功
	 * actionResult=true
	 * errorCode=100000
	 * errorInfo=ok
	 */
	public void setResultSuccess(){
		this.actionResult = Constants.RESULT_TRUE;
		this.errorCode = ErrorCode.C100000.getCode();
		this.errorInfo = ErrorCode.C100000.getMsg();
		this.logId= UUID.randomUUID().toString();
	}
	
	/**
	 * 操作失败
	 * actionResult=false
	 * @param errorCode
	 * 
	 */
	public void setResultFail(ErrorCode errorCode){
		this.actionResult = Constants.RESULT_FALSE;
		this.errorCode = errorCode.getCode();
		this.errorInfo = errorCode.getMsg();
		this.logId= UUID.randomUUID().toString();
	}

	public Boolean isActionResult() {
		return actionResult;
	}

	public void setActionResult(Boolean actionResult) {
		this.actionResult = actionResult;
	}
	
	
	
}
