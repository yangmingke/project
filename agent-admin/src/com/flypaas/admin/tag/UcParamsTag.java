package com.flypaas.admin.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.flypaas.admin.util.FlypaasParamUtils;

/**
 * 参数格式化标签，2种使用方式： 1.设置type； 2.设置sqlId
 * 
 * @author xiejiaan
 */
public class UcParamsTag extends TagSupport {
	private static final long serialVersionUID = -5757798842593949787L;
	private String key;// 字典值，即tb_flypaas_params.param_key字段
	private String type;// 字典类型，即tb_flypaas_params.param_type字段
	private String sqlId;// 查询sql的id，即tagMapper.xml中的id

	@Override
	public int doStartTag() throws JspException {
		try {
			String result = FlypaasParamUtils.get(key, type, sqlId);
			if (null == result) {
				result = "";
			}
			this.pageContext.getOut().println(result);
		} catch (IOException e) {
			throw new JspException(e);
		}
		return 0;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSqlId() {
		return sqlId;
	}

	public void setSqlId(String sqlId) {
		this.sqlId = sqlId;
	}
}
