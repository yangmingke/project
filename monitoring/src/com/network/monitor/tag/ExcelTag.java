package com.network.monitor.tag;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.network.monitor.service.TagService;
import com.network.monitor.util.JsonUtils;
import com.network.monitor.util.web.StrutsUtils;

/**
 * @author chenxj
 * @todo Excel 导出标签
 * @date 2015年7月2日
 */
public class ExcelTag extends BaseTag{

	private static final long serialVersionUID = 7774187373683178636L;

	private List<Map<String, Object>> data ;
	private String head;
	private String content;
	private String fileName;
	private String title;
	private String sqlId;
	private String sqlparams;
	Logger logger = LoggerFactory.getLogger(ExcelTag.class);
	
	@Override
	public String getTemplateFile() {
		return "excel.ftl";
	}

	@Override
	public Map<String, Object> getTemplateParams(TagService tagService) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("data", JsonUtils.toJson(data));
		params.put("head", head);
		params.put("content", content);
		params.put("fileName", fileName);
		params.put("title", title);
		params.put("sqlId", sqlId);
		params.put("sqlparams", sqlparams);
		StrutsUtils.getSession().setAttribute("excel",params);
		return params;
	}
	
	public List<Map<String, Object>> getData() {
		return data;
	}
	public void setData(List<Map<String, Object>> data) {
		this.data = data;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getSqlId() {
		return sqlId;
	}

	public void setSqlId(String sqlId) {
		this.sqlId = sqlId;
	}

	public String getSqlparams() {
		return sqlparams;
	}

	public void setSqlparams(String sqlparams) {
		this.sqlparams = sqlparams;
	}

	
}
