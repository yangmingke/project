package com.network.monitor.tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.network.monitor.service.TagService;
import com.network.monitor.util.JsonUtils;

/**
 * 树形下拉框，2种使用方式：1.设置sqlId；2.设置data
 * 
 * @author xiejiaan
 */
public class TreeSelectTag extends BaseTag {
	private static final long serialVersionUID = 3293957244711869955L;

	private String id;// html的id、name标记
	private String sqlId;// 查询sql的id，即tagMapper.xml中的id
	private String sqlParams;// sql参数值，json格式

	// 直接传入的数据，json格式，如 [ {id:1, pId:0, name: "父节点1"},
	// {id:11, pId:1, name: "子节点1"}, {id:12, pId:1, name: "子节点2"} ]
	private String data;
	private String value;// 选中的值
	private String placeholder;// 占位符
	private boolean showPname;// 选择后是否显示父节点名称
	private String beforeClick;// 用于捕获单击节点之前的事件回调函数，并且根据返回值确定是否允许单击操作，2个参数：treeId,treeNode
	private String onClick;// 用于捕获节点被点击的事件回调函数，4个参数：id, name, pid, pname

	@Override
	public String getTemplateFile() {
		return "treeSelect.ftl";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getTemplateParams(TagService tagService) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("value", value);
		params.put("placeholder", placeholder);
		params.put("showPname", showPname);
		params.put("beforeClick", beforeClick);
		params.put("onClick", onClick);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (StringUtils.isNotBlank(sqlId)) {// 查询数据字典表
			Map<String, Object> sqlP = new HashMap<String, Object>();
			if (StringUtils.isNotBlank(sqlParams)) {
				sqlP.putAll(JsonUtils.toObject(sqlParams, Map.class));
			}
			list = tagService.getTagDataForSql(sqlId, sqlP);
			data = JsonUtils.toJson(list);
		}
		params.put("data", data);
		return params;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSqlId() {
		return sqlId;
	}

	public void setSqlId(String sqlId) {
		this.sqlId = sqlId;
	}

	public String getSqlParams() {
		return sqlParams;
	}

	public void setSqlParams(String sqlParams) {
		this.sqlParams = sqlParams;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	public boolean isShowPname() {
		return showPname;
	}

	public void setShowPname(boolean showPname) {
		this.showPname = showPname;
	}

	public String getBeforeClick() {
		return beforeClick;
	}

	public void setBeforeClick(String beforeClick) {
		this.beforeClick = beforeClick;
	}

	public String getOnClick() {
		return onClick;
	}

	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}

}
