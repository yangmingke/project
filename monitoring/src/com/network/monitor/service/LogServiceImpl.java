package com.network.monitor.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.network.monitor.constant.LogConstant.LogType;
import com.network.monitor.dao.StatDao;
import com.network.monitor.util.web.AuthorityUtils;
import com.network.monitor.util.web.StrutsUtils;

/**
 * 日志业务
 * 
 * @author xiejiaan
 */
@Service
@Transactional
public class LogServiceImpl implements LogService {
	private static final Logger LOGGER = LoggerFactory.getLogger(LogServiceImpl.class);
	@Autowired
	private StatDao dao;

	@Override
	public boolean add(LogType logType, Object... desc) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", AuthorityUtils.getLoginUserId());
		params.put("page_id", "");
		params.put("page_url", StrutsUtils.getRequestURI());
		params.put("op_type", logType.getValue());
		params.put("op_desc", StringUtils.join(desc, ", "));
		params.put("ip", StrutsUtils.getClientIP());

		int i = dao.insert("common.addLog", params);
		if (i > 0) {
			LOGGER.debug("添加操作日志成功：" + params);
			return true;

		} else {
			LOGGER.error("添加操作日志失败：" + params);
			return false;
		}
	}
}
