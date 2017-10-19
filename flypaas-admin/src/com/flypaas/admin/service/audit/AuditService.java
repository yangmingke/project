package com.flypaas.admin.service.audit;

import java.util.Map;

/**
 * 公共的审核业务
 * 
 * @author xiejiaan
 */
public interface AuditService {

	/**
	 * 保存审核记录，包括添加、修改
	 * 
	 * @param auditParams
	 * @return 受影响行数
	 */
	int saveAudit(Map<String, Object> auditParams);

	/**
	 * 保存补充原因
	 * 
	 * @param auditType
	 *            审核类型
	 * @param auditedId
	 *            被审核ID
	 * @param auditDesc
	 *            审核原因
	 * @return 受影响行数
	 */
	int saveReason(int auditType, String auditedId, String auditDesc);
}
