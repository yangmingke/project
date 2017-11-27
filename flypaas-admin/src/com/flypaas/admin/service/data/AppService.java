package com.flypaas.admin.service.data;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import com.flypaas.admin.model.PageContainer;

/**
 * 信息管理-应用管理
 * 
 * @author xiejiaan
 */
public interface AppService {

	/**
	 * 分页查询应用
	 * 
	 * @param params
	 * @return
	 */
	PageContainer query(Map<String, String> params);

	/**
	 * 修改应用状态：强制下线
	 * 
	 * @param params
	 * @return
	 */
	Map<String, Object> updateStatus(Map<String, String> params);

	/**
	 * 查询开发者的应用ID
	 * 
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> queryAppSidBySid(Map<String, String> params);

	Map<String, Object> view(String appSid);

	/**
	 * 修改应用品牌
	 * 
	 * @param formData
	 * @return
	 */
	Map<String, Object> saveBrand(Map<String, String> params);

	void update(Map para);

	Map<String, Object> createView(Map<String, String> params) throws UnsupportedEncodingException;

	Map<String, Object> create(Map params);

}
