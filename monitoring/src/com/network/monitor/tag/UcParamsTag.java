package com.network.monitor.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.network.monitor.util.UcpaasParamUtils;

/**
 * 数值显示
 * 
 * @author xiejiaan
 */
public class UcParamsTag extends TagSupport {
	private static final long serialVersionUID = -5757798842593949787L;
	private String type;
	private String key;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			String result = UcpaasParamUtils.get(type, key);
			if(null == result){
				result = "";
			}
			this.pageContext.getOut().println(result);
		} catch (IOException e) {
			throw new JspException(e);
		}
		return 0;
	}
}
