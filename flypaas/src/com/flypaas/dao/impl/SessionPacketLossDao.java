package com.flypaas.dao.impl;

import java.util.List;
import java.util.Map;

public interface SessionPacketLossDao {

	/**
	 * 查询会话丢包
	 * @param model
	 * @return
	 */
	public List<Map<String, Object>> getSessionPacketLoss(Map<String, Object> model);
}
