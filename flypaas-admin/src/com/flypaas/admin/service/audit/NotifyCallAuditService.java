package com.flypaas.admin.service.audit;

import java.util.List;
import java.util.Map;

import com.flypaas.admin.model.PageContainer;

/**
 * 审核管理-语音通知审核
 * 
 * @author zenglb
 */
public interface NotifyCallAuditService {

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
	 * 任务调度
	 * 
	 * @return
	 */
	List<Map<String, Object>> findAllTasks();

	/**
	 * 设置中间状态：发送中
	 * 
	 * @param list
	 */
	void updateRun(List<Map<String, Object>> list);

	/**
	 * 发送语音通知
	 * 
	 * @param map
	 */
	void processNotifyCall(Map<String, Object> map);
}
