package com.flypaas.admin.service.data;

import java.util.Map;

import com.flypaas.admin.model.PageContainer;

/**
 * 信息管理-管理员中心-业务配置
 * 
 * @author zenglb
 */
public interface BusinessConfigService {

	/**
	 * 查询
	 * 
	 * @param params
	 * @return
	 */
	PageContainer query(Map<String, String> params);

	/**
	 * 查看
	 * 
	 * @param newsId
	 * @return
	 */
	Map<String, Object> view(int newsId);

	/**
	 * 保存
	 * 
	 * @param params
	 * @return
	 */
	Map<String, Object> save(Map<String, String> params);

	/**
	 * 修改状态：关闭、启用、删除
	 * 
	 * @param newsId
	 * @param status
	 * @return
	 */
	Map<String, Object> updateStatus(int id, int status);

}
