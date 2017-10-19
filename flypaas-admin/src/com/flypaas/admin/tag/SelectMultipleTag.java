package com.flypaas.admin.tag;

import java.util.Map;

import com.flypaas.admin.service.TagService;

/**
 * 多选下拉框标签，三种使用方式： <br/>
 * 1.设置dictionaryType；<br/>
 * 2.设置sqlId<br/>
 * 3.设置data
 * 
 * @author xiejiaan
 */
public class SelectMultipleTag extends SelectTag {
	private static final long serialVersionUID = -4702649304462322001L;

	private int size = 10;// 显示个数，默认是10
	private boolean showSelectAll = true;// 是否显示全选，默认是true
	private boolean disabled = false;// 是否不可用，默认是false

	@Override
	public String getTemplateFile() {
		return "selectMultiple.ftl";
	}

	@Override
	public Map<String, Object> getTemplateParams(TagService tagService) {
		Map<String, Object> params = super.getTemplateParams(tagService);
		params.put("size", size);
		params.put("showSelectAll", showSelectAll);
		params.put("disabled", disabled);
		return params;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isShowSelectAll() {
		return showSelectAll;
	}

	public void setShowSelectAll(boolean showSelectAll) {
		this.showSelectAll = showSelectAll;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

}
