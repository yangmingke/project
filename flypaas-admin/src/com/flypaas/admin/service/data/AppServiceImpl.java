package com.flypaas.admin.service.data;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
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
import com.flypaas.admin.util.StrUtils;
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

	@Override
	public Map<String, Object> createView(Map<String, String> params) throws UnsupportedEncodingException {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("sid", params.get("sid"));
		data.put("username", URLDecoder.decode(params.get("username"), "utf-8"));
		
		List<Map<String, Object>> appKindList = dao.getSearchList("params.queryParamsByType","app_kind");
		data.put("appKindList", appKindList);
		
		List<Map<String, Object>> industryList = dao.getSearchList("params.queryParamsByType","industry");
		data.put("industryList", industryList);
		
		return data;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> create(Map params) {
		Map<String, Object> data = new HashMap<String, Object>();
		int appCount = dao.getSearchSize("app.appNameOfDeveloperExist", params);
		if(appCount > 0){
			data.put("result", "fail");
			data.put("msg", "应用名称已被占用！创建失败");
			return data;
		}
		String uuid = StrUtils.getUUID();
		params.put("appSid", uuid);
		params.put("createDate", new Date());
		params.put("updateDate", new Date());
		params.put("appType", AppConstant.APP_TYPE);
		params.put("status", AppConstant.STATUS_1);
		params.put("callFr", 0);
		params.put("ckNum", 0);
		params.put("brand", AppConstant.APP_BRAND);
		int count = addApp(params);
		if(count == 0){
			data.put("result", "fail");
			data.put("msg", "创建失败，请联系管理员");
			return data;
		}
		data.put("result", "success");
		data.put("msg", "创建成功");
		return data;
	}

	private int addApp(Map<String, Object> params) {
		//应用
		int count = dao.insert("app.add", params);
		//白名单
		String uuid = (String) params.get("appSid");
		String whiteListStr = (String) params.get("whiteListStr");
		if(whiteListStr!=null && whiteListStr.length()>0){
			createWhiteList(uuid,whiteListStr);
		}
		return count;
	}

	private void createWhiteList(String appSid,String whiteListStr){
		Map<String, Object> whiteList = new HashMap<String, Object>();
		whiteList.put("appSid", appSid);
		whiteList.put("whiteAddress", whiteListStr);
		whiteList.put("wType", AppConstant.WHITE_TYPE);
		whiteList.put("createDate", new Date());
		whiteList.put("ipdateDate", new Date());
		whiteList.put("status", AppConstant.CHECK_STATUS);
		dao.insert("app.addWhiteList", whiteList);
	}
}
