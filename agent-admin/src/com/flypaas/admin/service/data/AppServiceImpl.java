package com.flypaas.admin.service.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.admin.constant.AppConstant;
import com.flypaas.admin.constant.LogConstant.LogType;
import com.flypaas.admin.constant.MsgConstant.MsgType;
import com.flypaas.admin.constant.MsgConstant.TemplateId;
import com.flypaas.admin.constant.SysConstant;
import com.flypaas.admin.dao.MasterDao;
import com.flypaas.admin.model.PageContainer;
import com.flypaas.admin.service.LogService;
import com.flypaas.admin.util.cache.RedisUtils;

/**
 * 信息管理-应用管理
 * 
 * @author xiejiaan
 */
@Service
@Transactional
public class AppServiceImpl implements AppService {
	@Autowired
	private MasterDao dao;
	@Autowired
	private LogService logService;
	@Autowired
	private MsgService msgService;

	@Override
	public PageContainer query(Map<String, String> params) {
		return dao.getSearchPage("app.query", "app.queryCount", params);
	}

	@Override
	public Map<String, Object> updateStatus(Map<String, String> params) {
		Map<String, Object> data = new HashMap<String, Object>();

		int status = NumberUtils.toInt(params.get("status"), -1);// 检查修改的状态
		if (status != AppConstant.STATUS_5) {
			data.put("result", "fail");
			data.put("msg", "不是强制下线，操作失败");
			return data;
		}

		int i = dao.update("app.updateStatus", params);// 修改
		if (i > 0) {
			String appSid = params.get("app_sid");
			params.put("money_rate", SysConstant.money_rate);
			Map<String, Object> app = dao.getOneInfo("app.getApp", params);// 获取应用
			Map<String, Object> templateParams = new HashMap<String, Object>();
			templateParams.put("developer_email", app.get("email"));
			templateParams.put("app_name", app.get("app_name"));
			templateParams.put("app_sid", appSid);
			templateParams.put("app_kind", app.get("app_kind_name"));
			msgService
					.sendMsg(app.get("sid").toString(), MsgType.system_msg, TemplateId.forced_offline, templateParams);// 发送消息

			RedisUtils.flushAppCache(appSid);// 刷新前台缓存信息
			data.put("result", "success");
			data.put("msg", "强制下线成功");
		} else {
			data.put("result", "fail");
			data.put("msg", "应用不存在，强制下线失败");
		}

		logService.add(LogType.update, "信息管理-应用管理：强制下线", params, data);
		return data;
	}

	@Override
	public List<Map<String, Object>> queryAppSidBySid(Map<String, String> params) {
		return dao.getSearchList("app.queryAppSidBySid", params);
	}

	@Override
	public Map<String, Object> view(String appSid) {
		Map<String, Object> data = new HashMap<String, Object>();
		HashMap<String, Object>params = new HashMap<String, Object>();
		params.put("app_sid", appSid);
		params.put("money_rate", SysConstant.money_rate);
		
		Map<String, Object> app = dao.getOneInfo("app.getApp", params);// 获取应用
		if (app != null) {
			data.put("app", app);
			List<Map<String, Object>> callback = dao.getSearchList("app.getCallback", appSid);// 获取回调地址
			data.put("callback", callback);
			List<Map<String, Object>> showbrs = dao.getSearchList("app.getShownbrs", app);// 获取回调地址
			data.put("showbrs", showbrs);
			List<Map<String, Object>> rings = dao.getSearchList("app.getRings", app);// 获取回调地址
			data.put("rings", rings);

		}
		return data;
	}
	
	@Override
	public void update(Map para) {
		dao.update("app.update", para);
	}
	

	@Override
	public Map<String, Object> saveBrand(Map<String, String> params) {
		Map<String, Object> data = new HashMap<String, Object>();
		int i = dao.update("app.saveBrand", params);// 修改
		if (i > 0) {
			String appSid = params.get("app_sid");
			RedisUtils.flushAppCache(appSid);// 刷新前台缓存信息
			data.put("result", "success");
			data.put("msg", "设置品牌成功");
		} else {
			data.put("result", "fail");
			data.put("msg", "应用不存在，设置品牌失败");
		}
		logService.add(LogType.update, "信息管理-应用管理：设置品牌", params, data);
		return data;
	}
}
