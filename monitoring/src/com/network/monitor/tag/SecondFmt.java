package com.network.monitor.tag;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;

public class SecondFmt extends TagSupport {
	private static final long serialVersionUID = -5757798842593949787L;
	private String value;
	private String fmt;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getFmt() {
		return fmt;
	}

	public void setFmt(String fmt) {
		this.fmt = fmt;
	}

	@Override
	public int doStartTag() throws JspException {
		long v = 0;
		if(StringUtils.isNotBlank(value)){
			if (StringUtils.isNumeric(value)) {
				v = Long.valueOf(value);
			}else {
				try {
					BigDecimal d = new BigDecimal(value);
					v  = d.longValue();
				} catch (Throwable e) {}
			}
		}
		
		long h = v / 3600;
		long m = (v % 3600) / 60;
		long s = (v % 60);
		String result = fmt;
		if (StringUtils.isBlank(result)) {
			result = "hh:mm:ss";
		}
		result = result.replace("hh", String.format("%02d", h));
		result = result.replace("mm", String.format("%02d", m));
		result = result.replace("ss", String.format("%02d", s));
		result = result.replace("h", h + "");
		result = result.replace("m", m + "");
		result = result.replace("s", s + "");
		try {
			this.pageContext.getOut().print(result);
		} catch (IOException e) {
			throw new JspException(e);
		}
		return 0;
	}
}
