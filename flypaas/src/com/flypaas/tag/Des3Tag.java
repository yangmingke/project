package com.flypaas.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.flypaas.utils.Des3Utils;

/**
 * des3加密标签
 * 
 */
public class Des3Tag extends TagSupport {
	private static final long serialVersionUID = -4489831165631536606L;
	private String value;// 需要加密的字符串

	@Override
	public int doStartTag() throws JspException {
		try {
			String result =Des3Utils.encodeDes3(value);
			this.pageContext.getOut().print(result);
		} catch (IOException e) {
			throw new JspException(e);
		}
		return 0;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
