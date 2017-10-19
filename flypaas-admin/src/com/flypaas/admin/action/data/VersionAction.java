package com.flypaas.admin.action.data;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.admin.action.BaseAction;
import com.flypaas.admin.service.data.VersionService;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 信息管理-版本管理
 * 
 * @author zenglb
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "query", location = "/WEB-INF/content/data/version/query.jsp"),
		@Result(name = "edit", location = "/WEB-INF/content/data/version/edit.jsp")})
public class VersionAction extends BaseAction {
	private static final long serialVersionUID = 4296765223715779624L;
	private VersionService versionService;
	private Integer id;
	private String version_index;
	private String version_name_key;
	private File version_file;
	private String version_fileFileName;
	private String version_fileContentType;
	private String version_desc;
	
	@Resource
	public void setVersionService(VersionService versionService) {
		this.versionService = versionService;
	}

	/**
	 * 查询
	 * 
	 * @return
	 */
	@Action("/version/query")
	public String query() {
		page = versionService.query(StrutsUtils.getFormData());
		return "query";
	}

	/**
	 * 修改状态
	 * 
	 * @return
	 */
	@Action("/version/updateStatus")
	public void updateStatus() {
		data = versionService.updateStatus(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}
	

	/**
	 * 保存
	 * 
	 * @return
	 */
	@Action("/version/save")
	public void save() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("version_index", version_index);
		params.put("version_name_key", version_name_key);
		if(null != version_file){
			params.put("version_fileFileName", version_fileFileName);
			params.put("version_fileContentType", version_fileFileName.substring(version_fileFileName.lastIndexOf(".")+1));	
		}
		params.put("version_index", version_index);
		params.put("version_desc", version_desc);
		data = versionService.save(id,version_file,version_name_key,params);
		StrutsUtils.renderJson(data);
	}

	@Action("/version/edit")
	public String edit() {
		String id = StrutsUtils.getParameterTrim("id");
		if (StringUtils.isNotBlank(id)) {
			data = versionService.view(Integer.valueOf(id));
		}else{
			data = new HashMap<String, Object>();
			data.put("entity", new HashMap<String, Object>());
		}
		return "edit";
	}

	public String getVersion_index() {
		return version_index;
	}

	public void setVersion_index(String version_index) {
		this.version_index = version_index;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVersion_name_key() {
		return version_name_key;
	}

	public void setVersion_name_key(String version_name_key) {
		this.version_name_key = version_name_key;
	}

	public File getVersion_file() {
		return version_file;
	}

	public void setVersion_file(File version_file) {
		this.version_file = version_file;
	}

	public String getVersion_fileFileName() {
		return version_fileFileName;
	}

	public void setVersion_fileFileName(String version_fileFileName) {
		this.version_fileFileName = version_fileFileName;
	}

	public String getVersion_fileContentType() {
		return version_fileContentType;
	}

	public void setVersion_fileContentType(String version_fileContentType) {
		this.version_fileContentType = version_fileContentType;
	}

	public String getVersion_desc() {
		return version_desc;
	}

	public void setVersion_desc(String version_desc) {
		this.version_desc = version_desc;
	}
}
