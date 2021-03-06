package com.flypaas.admin.service.data;

import java.util.Map;

import com.flypaas.admin.model.PageContainer;

/**
 * 信息管理-开发者管理
 * 
 * @author xiejiaan
 */
public interface AgentService {

	/**
	 * 分页查询开发者
	 * 
	 * @param params
	 * @return
	 */
	PageContainer query(Map<String, String> params);

	/**
	 * 获取开发者资料
	 * 
	 * @param sid
	 * @return
	 */
	Map<String, Object> getDeveloper(String sid);

	/**
	 * 保存开发者资料
	 * 
	 * @param params
	 * @return
	 */
	Map<String, Object> saveDeveloper(Map<String, String> params);

	/**
	 * 获取开发者主账号信息
	 * 
	 * @param sid
	 * @return
	 */
	Map<String, Object> getMainAccount(String sid);

	/**
	 * 修改开发者状态：关闭、重新激活
	 * 
	 * @param params
	 * @return
	 */
	Map<String, Object> updateStatus(Map<String, String> params);


	/**
	 * 发送通知
	 * 
	 * @param params
	 * @return
	 */
	Map<String, Object> sendNotice(Map<String, String> params);

	/**
	 * 设置或取消为代理商
	 * 
	 * @param params
	 * @return
	 */
	Map<String, Object> setProxy(Map<String, String> params);

	Map<String, Object> editUrl(Map<String, String> formData);
}
