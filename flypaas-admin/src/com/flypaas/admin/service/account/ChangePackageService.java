package com.flypaas.admin.service.account;

import java.util.Map;

import com.flypaas.admin.model.PageContainer;

/**
 * 账务管理-套餐变更日志
 * 
 * @author xiejiaan
 */
public interface ChangePackageService {

	/**
	 * 查询套餐变更日志
	 * 
	 * @param params
	 * @return
	 */
	PageContainer query(Map<String, String> params);

	/**
	 * 查看变更历史
	 * 
	 * @param sid
	 * @return
	 */
	Map<String, Object> history(String sid);

	/**
	 * 还原
	 * 
	 * @param id
	 * @return
	 */
	Map<String, Object> restore(int id);

}
