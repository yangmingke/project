package com.flypaas.admin.service;

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

import com.flypaas.admin.constant.DbConstant.DbType;
import com.flypaas.admin.constant.DbConstant.TablePrefix;
import com.flypaas.admin.constant.DbConstant.TableSchema;
import com.flypaas.admin.constant.RoleConstant;
import com.flypaas.admin.constant.UserConstant;
import com.flypaas.admin.dao.CommonDao;
import com.flypaas.admin.dao.MasterDao;
import com.flypaas.admin.util.MD5;
import com.flypaas.admin.util.web.AuthorityUtils;

/**
 * 公共业务
 * 
 * @author xiejiaan
 */
@Service
@Transactional
public class CommonServiceImpl implements CommonService {
	private static final Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);
	@Autowired
	private MasterDao masterDao;
	@Autowired
	private CommonDao commonDao;

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

		Map<String, Object> params = new HashMap<String, Object>();
		// password = EncryptUtils.encodeMd5(password);
		password = MD5.md5(password);
		params.put("username", username);
		params.put("password", password);
		Map<String, Object> user = masterDao.getOneInfo("common.findUserForLogin", params);

		if (user == null) {
			data.put("result", "fail");
			data.put("msg", "用户名或密码错误，请重新输入");
			return data;
		}

		if (Integer.parseInt(user.get("roleStatus").toString()) == RoleConstant.status_0) {
			data.put("result", "fail");
			data.put("msg", "管理身份（" + user.get("roleName") + "）已删除，登录失败");
			return data;
		}
		switch (Integer.parseInt(user.get("status").toString())) {
		case UserConstant.STATUS_5:
			data.put("result", "fail");
			data.put("msg", "用户已锁定，登录失败");
			return data;
		case UserConstant.STATUS_6:
			data.put("result", "fail");
			data.put("msg", "用户已删除，登录失败");
			return data;
		}

		masterDao.update("common.updateLoginInfo", user.get("sid"));// 更新登录信息

		logger.debug("登录成功：" + username);
		AuthorityUtils.setLoginUser(user.get("sid").toString(), Integer.parseInt(user.get("roleId").toString())); // 保存当前登录用户
		data.put("result", "success");
		return data;
	}

	@Override
	public boolean hasTable(DbType dbType, TableSchema tableSchema, String tableName) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("table_schema", tableSchema.name());
		params.put("table_name", tableName);
		return commonDao.getDao(dbType).getOneInfo("common.hasTable", params);
	}

	@Override
	public String queryRemotePath(String table, String localPath) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("table_name", table);
		params.put("local_path", localPath);
		return commonDao.getStatDao().getOneInfo("common.queryRemontePath", params);
	}

	@Override
	public Map<String, Object> monitor() {
		Map<String, Object> data = new HashMap<String, Object>();
		String master_time = masterDao.getOneInfo("common.getCurrentTime", null);
		String stat_time = commonDao.getStatDao().getOneInfo("common.getCurrentTime", null);
		if (master_time != null && stat_time != null) {
			data.put("monitor", "success");
			data.put("master_time", master_time);
			data.put("stat_time", stat_time);
		}
		return data;
	}

	public List<Map<String, Object>> loadCityByProvinceId(Map<String, Object> params) {
		return masterDao.getSearchList("tag.loadCityByProvinceId", params);
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
			logger.error("时间转换【失败】：startDate=" + startDate + ", endDate=" + endDate, e);
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

		List<Map<String, Object>> existList = masterDao.getSearchList("common.getExistTable", tableList);// 查询在日期范围内存在的语音表、短信表、即时消息表
		for (Map<String, Object> map : existList) {
			data.add(map.get("table_name").toString());
		}
		return data;
	}

	@Override
	public Long generatedGlobalId() {
		Map<String,Object> sqlParams = new HashMap<String, Object>();
		masterDao.insert("common.generatedGlobalId", sqlParams);
		Long id = (Long) sqlParams.get("id");
		if(id == null){
			throw new NullPointerException(" generatedGlobalId error !");
		}
		return id;
	}
}
