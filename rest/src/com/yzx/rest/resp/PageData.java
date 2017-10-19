package com.yzx.rest.resp;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)  
@XmlRootElement  
//@XmlSeeAlso(value={Teacher.class})
public class PageData<T> extends BaseResp{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2882197652648568761L;

	@XmlElement  
    private PageResp pageResp;  
      
    @XmlElementWrapper(name="pageData")  
    public List<T> data;  
  
    public PageResp getPageResp() {  
        return pageResp;  
    }  
  
    public void setPageinfo(PageResp pageResp) {  
        this.pageResp = pageResp;  
    }  
  
    public List<T> getData() {  
        return data;  
    }  
  
    public void setData(List<T> data) {  
        this.data = data;  
    }  
}
