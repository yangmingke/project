package com.network.monitor.tag;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;

import com.network.monitor.service.TagService;

/**
 * 字符串截短标签
 * 
 * @author xiejiaan
 */
public class TruncateTag extends BaseTag {
	private static final long serialVersionUID = -2906394202674933714L;

	private String value;// 字符串
	private Integer length;// 截短后的字符串长度
	private boolean dot = true;// 是否需要点号，默认是true
	private boolean wrap = true;// 是否需要包裹span标签，默认是true

	@Override
	public String getTemplateFile() {
		return "truncate.ftl";
	}

	@Override
	public Map<String, Object> getTemplateParams(TagService tagService) {
		String show = value;
		if (length > 0 && length < value.length()) {
			show = value.substring(0, length) + (dot ? "..." : "");
		}

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("title", StringEscapeUtils.escapeHtml4(value));
		params.put("show", StringEscapeUtils.escapeHtml4(show));
		params.put("wrap", wrap);
		return params;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public boolean isDot() {
		return dot;
	}

	public void setDot(boolean dot) {
		this.dot = dot;
	}

	public boolean isWrap() {
		return wrap;
	}

	public void setWrap(boolean wrap) {
		this.wrap = wrap;
	}

}
