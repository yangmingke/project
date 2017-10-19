package com.network.monitor.tag;

import java.util.HashMap;
import java.util.Map;

import com.network.monitor.service.TagService;

/**
 * 分页标签
 * 
 * @author chenxj
 */
public class PageRowCount extends BaseTag {
	
	private static final long serialVersionUID = -4193727272630593579L;
	/**
	 * 查询的表单id
	 */
	private String value;

	@Override
	public String getTemplateFile() {
		return "pagerowcount.ftl";
	}

	@Override
	public Map<String, Object> getTemplateParams(TagService tagService) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("value", value);
		return params;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
