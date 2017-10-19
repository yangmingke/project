package com.network.monitor.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.network.monitor.constant.AdminConstant;
import com.network.monitor.constant.DbConstant.TablePrefix;
import com.network.monitor.dao.StatDao;
import com.network.monitor.model.TaskInfo;
import com.network.monitor.util.MD5;
import com.network.monitor.util.web.AuthorityUtils;

/**
 * 公共业务
 * 
 */
@Service
@Transactional
public class CommonServiceImpl implements CommonService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonServiceImpl.class);
	@Autowired
	private StatDao dao;

	@Override
	public Map<String, Object> login(String username, String password) {
		Map<String, Object> data = new HashMap<String, Object>();

		if (AuthorityUtils.isLogin()) {// 已登录
			data.put("result", "isLogin");
			data.put("msg", "当前已登录，请勿重复登录");
			return data;
		}

		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			data.put("result", "fail");
			data.put("msg", "用户名或密码不能为空");
			return data;
		}
		password = MD5.md5(password);
		Map<String, Object> params = new HashMap<String, Object>();
		// password = EncryptUtils.encodeMd5(password);
		params.put("username", username);
		params.put("password", password);
		Map<String, Object> user = dao.getOneInfo("common.findUserForLogin", params);

		if (user == null) {
			data.put("result", "fail");
			data.put("msg", "用户名或密码错误，请重新输入");
			return data;
		}

		if (Integer.parseInt(user.get("status").toString()) == AdminConstant.status_0) {
			data.put("result", "fail");
			data.put("msg", "用户已删除，登录失败");
			return data;
		}

		//dao.update("common.updateLoginInfo", user.get("sid"));// 更新登录信息

		LOGGER.debug("登录成功：" + username);
		AuthorityUtils.setLoginUser(user.get("id").toString(), String.valueOf(user.get("realname"))); // 保存当前登录用户
		data.put("result", "success");
		return data;
	}

	@Override
	public boolean hasTable(String table_name, String table_schema) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("table_name", table_name);
		params.put("table_schema", table_schema);
		Map<String, Object> tmp = dao.getOneInfo("common.countTableByName", params);
		Integer total = Integer.valueOf(String.valueOf(tmp.get("total")));
		if (total > 0) {
			return true;
		}
		return false;
	}

	@Override
	public String queryRemotePath(String table, String localPath) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("table_name", table);
		params.put("local_path", localPath);
		Map<String, Object> tmp = dao.getOneInfo("common.queryRemontePath", params);
		return null == tmp ? null : String.valueOf(tmp.get("remote_path"));
	}

	@Override
	public Map<String, Object> monitor() {
		Map<String, Object> data = new HashMap<String, Object>();
		String time = dao.getOneInfo("common.getCurrentTime", null);
		if (time != null) {
			data.put("monitor", "success");
			data.put("time", time);
		}
		return data;
	}
	@Override
	public List<String> getExistTable(TablePrefix tablePrefix, String startDate, String endDate) {
		List<String> data = new ArrayList<String>();
		String tablePrefixStr = tablePrefix.name();

		if (StringUtils.isBlank(tablePrefixStr) || StringUtils.isBlank(startDate) || StringUtils.isBlank(endDate)) {
			return data;
		}
		DateTime d1 = null;
		DateTime d2 = null;
		try {
			d1 = new DateTime(DateUtils.parseDateStrictly(startDate.substring(0, 10), "yyyy-MM-dd"));
			d2 = new DateTime(DateUtils.parseDateStrictly(endDate.substring(0, 10), "yyyy-MM-dd"));
		} catch (Throwable e) {
			LOGGER.error("时间转换【失败】：startDate=" + startDate + ", endDate=" + endDate, e);
			return data;
		}
		if (d1.isAfter(d2)) {
			return data;
		}
		int between = Days.daysBetween(d1, d2).getDays();
		List<String> tableList = new ArrayList<String>();
		for (int i = 0; i <= between; i++) {
			tableList.add(tablePrefixStr + d1.plusDays(i).toString("yyyyMMdd"));
		}

		List<Map<String, Object>> existList = dao.getSearchList("common.getExistTable", tableList);// 查询在日期范围内存在的语音表、短信表、即时消息表
		for (Map<String, Object> map : existList) {
			data.add(map.get("table_name").toString());
		}
		return data;
	}

	@Override
	public List<Map<String, Object>> getExcelData(Map<String, Object> map) {
		return dao.getSearchList(map.get("sqlId").toString(), map);
	}

	@Override
	public boolean callProcedure(TaskInfo taskInfo) {
		return dao.callProcedure(taskInfo);
	}
}
