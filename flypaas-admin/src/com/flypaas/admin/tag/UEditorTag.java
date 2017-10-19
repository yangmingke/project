package com.flypaas.admin.tag;

import java.util.HashMap;
import java.util.Map;

import com.flypaas.admin.service.TagService;

/**
 * UEditor富文本框标签
 * 
 * @author xiejiaan
 */
public class UEditorTag extends BaseTag {
	private static final long serialVersionUID = -2906394202674933714L;

	private String id;// 生成的富文本框id标示、隐藏的textarea的name属性（id为ueditor_textarea_<name>）
	private String value;// 富文本框内容
	private String type = "edit";// 类型：edit、view，默认是edit

	@Override
	public String getTemplateFile() {
		return "ueditor.ftl";
	}

	@Override
	public Map<String, Object> getTemplateParams(TagService tagService) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("type", type);
		params.put("value", value);
		return params;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
