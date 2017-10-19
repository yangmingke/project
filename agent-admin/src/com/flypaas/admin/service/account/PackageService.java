package com.flypaas.admin.service.account;

import java.util.Map;

import com.flypaas.admin.model.PageContainer;

/**
 * 账务管理-资费套餐
 * 
 * @author xiejiaan
 */
public interface PackageService {

	/**
	 * 分页查询资费套餐
	 * 
	 * @param params
	 * @return
	 */
	PageContainer query(Map<String, String> params);

	/**
	 * 查看资费套餐
	 * 
	 * @param packageId
	 *            套餐id
	 * @return
	 */
	Map<String, Object> view(Integer packageId);

	/**
	 * 保存资费套餐，包括添加、修改
	 * 
	 * @param params
	 * @return
	 */
	Map<String, Object> save(Map<String, String> params);

	/**
	 * 修改资费套餐状态：关闭、启用、删除
	 * 
	 * @param packageId
	 * @param status
	 * @return
	 */
	Map<String, Object> updateStatus(int packageId, int status);

	/**
	 * 开发者转移
	 * 
	 * @param sid
	 *            为null则转移该套餐的所有开发者
	 * @param packageId
	 *            原来的套餐id
	 * @param newPackageId
	 *            新的套餐id
	 * @param isSendMsg
	 *            是否推送消息
	 * @return
	 */
	Map<String, Object> developerTransfer(String sid, int packageId, int newPackageId, boolean isSendMsg);

}
