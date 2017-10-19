package com.network.monitor.service.admin;

import java.util.Map;

import com.network.monitor.model.PageContainer;

/**
 * 管理员中心
 * 
 * @author xiejiaan
 */
public interface AdminService {

	/**
	 * 获取管理员资料
	 * 
	 * @param sid
	 * @return
	 */
	Map<String, Object> getAdmin(String id);

	/**
	 * 保存管理员资料，包括添加、修改
	 * 
	 * @param admin
	 * @return
	 */
	Map<String, Object> saveAdmin(Map<String, String> admin);

	/**
	 * 保存密码
	 * 
	 * @param params
	 * @return
	 */
	Map<String, Object> savePassword(Map<String, String> params);

	/**
	 * 查询管理员
	 * 
	 * @param params
	 * @return
	 */
	PageContainer query(Map<String, String> params);

	/**
	 * 修改状态：恢复、删除
	 * 
	 * @param sid
	 * @param status
	 * @return
	 */
	Map<String, Object> updateStatus(Long id, int status);
}
