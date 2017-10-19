package com.flypaas.admin.service.audit;

import java.util.List;
import java.util.Map;

import com.flypaas.admin.model.PageContainer;

/**
 * 审核管理-铃声审核
 * 
 * @author zenglb
 */
public interface RingAuditService {

	/**
	 * 查询
	 * 
	 * @param params
	 * @return
	 */
	PageContainer query(Map<String, String> params);

	/**
	 * 审核
	 * 
	 * @param params
	 * @return
	 */
	Map<String, Object> audit(Map<String, String> params);

	/**
	 * 保存补充原因
	 * 
	 * @param params
	 * @return
	 */
	Map<String, Object> saveReason(Map<String, String> params);

	/**
	 * cb地址列表
	 * @param cb
	 * @return
	 */
	List<Map<String, Object>> reloadCbIpList(boolean cb);

	/**
	 * 保存上传结果
	 * @param params
	 */
	void saveUploadResult(Map<String, Object> params,boolean canLog);

	/**
	 *查找
	 * @param id
	 * @return
	 */
	Map<String, Object> getOneById(String id);
}
