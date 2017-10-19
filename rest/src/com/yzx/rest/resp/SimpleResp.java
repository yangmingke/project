package com.yzx.rest.resp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement  
@XmlAccessorType(XmlAccessType.FIELD)
public class SimpleResp<T> extends BaseResp{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1597397859959147088L;
	@XmlElement
	private T result;
	public T getResult() {
        return result;  
    }  
    public void setResult(T result) {
        this.result = result;  
    }
}
