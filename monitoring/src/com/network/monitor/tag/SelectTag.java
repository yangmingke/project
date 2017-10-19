package com.network.monitor.tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.network.monitor.service.TagService;
import com.network.monitor.util.JsonUtils;

/**
 * 下拉框标签，三种使用方式： <br/>
 * 1.设置dictionaryType；<br/>
 * 2.设置sqlId<br/>
 * 3.设置data
 * 
 * @author xiejiaan
 */
public class SelectTag extends BaseTag {
	private static final long serialVersionUID = -9180665327982636417L;

	private String id = "";// html的id、name标记
	private String dictionaryType;// 字典类型，即tb_ucpaas_params.param_type字段
	private String includeValue;// 包含的值，多个值用,分割
	private String excludeValue;// 排除的值，多个值用,分割
	private String sqlId;// 查询sql的id，即tagMapper.xml中的id
	private String sqlParams;// sql参数值，json格式
	private String data;// 直接传入的数据，json格式，如[{value:'1',text:'文本1'},{value:'2',text:'文本2'}]
	private String value = "";// 选中的值
	private String placeholder = "";// 占位符
	private String callback = "";// 选择后的回调函数，3个参数value、text、isInit（是否初始化）
	private String clazz = "";// 添加样式clazz
	private String style = "";// 添加样式

	@Override
	public String getTemplateFile() {
		return "select.ftl";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getTemplateParams(TagService tagService) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("value", value);
		params.put("placeholder", placeholder);
		params.put("callback", callback);
		params.put("clazz", clazz);
		params.put("style", style);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (StringUtils.isNotBlank(dictionaryType) || StringUtils.isNotBlank(sqlId)) {// 查询数据字典表
			Map<String, Object> sqlP = new HashMap<String, Object>();
			sqlP.put("placeholder", placeholder);
			if (includeValue != null) {
				sqlP.put("includeValue", includeValue.split(","));
			}
			if (excludeValue != null) {
				sqlP.put("excludeValue", excludeValue.split(","));
			}
			if (StringUtils.isNotBlank(dictionaryType)) {
				list = tagService.getTagDataForDictionary(dictionaryType, sqlP);
			} else {
				if (StringUtils.isNotBlank(sqlParams)) {
					sqlP.putAll(JsonUtils.toObject(sqlParams, Map.class));
				}
				list = tagService.getTagDataForSql(sqlId, sqlP);
			}

		} else if (StringUtils.isNotBlank(data)) {// 静态数据
			list = JsonUtils.toObject(data, List.class);
		}
		params.put("list", list);
		return params;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDictionaryType() {
		return dictionaryType;
	}

	public void setDictionaryType(String dictionaryType) {
		this.dictionaryType = dictionaryType;
	}

	public String getIncludeValue() {
		return includeValue;
	}

	public void setIncludeValue(String includeValue) {
		this.includeValue = includeValue;
	}

	public String getExcludeValue() {
		return excludeValue;
	}

	public void setExcludeValue(String excludeValue) {
		this.excludeValue = excludeValue;
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

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
}
