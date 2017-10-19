package com.flypaas.admin.service.business;

import java.util.Map;

import com.flypaas.admin.model.PageContainer;

/**
 * 业务管理-短信
 * 
 * @author xiejiaan
 */
public interface SmsService {

	/**
	 * 分页查询短信
	 * 
	 * @param params
	 * @return
	 */
	PageContainer query(Map<String, String> params);

	/**
	 * 查看短信
	 * 
	 * @param params
	 * @return
	 */
	Map<String, Object> view(Map<String, String> params);

}
