package com.flypaas.admin.service.data;

import java.io.File;
import java.util.Map;

import com.flypaas.admin.model.PageContainer;

/**
 * 信息管理-版本管理
 * 
 * @author zenglb
 */
public interface VersionService {

	/**
	 * 分页查询
	 * 
	 * @param params
	 * @return
	 */
	PageContainer query(Map<String, String> params);

	/**
	 * 修改状态
	 * 
	 * @param params
	 * @return
	 */
	Map<String, Object> updateStatus(Map<String, String> params);

	/**
	 * 保存
	 * 
	 * @param params
	 * @return
	 */
	Map<String, Object> save(Integer id, File version_file, String version_index, Map<String, Object> params);

	/**
	 * 明细页面
	 * 
	 * @param id
	 * @return
	 */
	Map<String, Object> view(Integer id);
}
