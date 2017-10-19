package com.flypaas.admin.service.data;

import java.util.Map;

import com.flypaas.admin.model.PageContainer;

/**
 * 信息管理-管理员中心-审核通知管理
 * 
 * @author xiejiaan
 */
public interface AuditNoticeService {

	/**
	 * 查询审核通知时段
	 * 
	 * @param params
	 * @return
	 */
	PageContainer query(Map<String, String> params);

	/**
	 * 查看审核通知时段
	 * 
	 * @param noticeId
	 * @return
	 */
	Map<String, Object> view(Integer noticeId);

	/**
	 * 保存审核通知时段，包括添加、修改
	 * 
	 * @param params
	 * @return
	 */
	Map<String, Object> save(Map<String, String> params);

	/**
	 * 修改审核通知时段状态：关闭、启用、删除
	 * 
	 * @param noticeId
	 * @param status
	 * @return
	 */
	Map<String, Object> updateStatus(int noticeId, int status);

}
