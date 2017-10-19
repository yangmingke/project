package com.flypaas.admin.service.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.admin.constant.AppConstant;
import com.flypaas.admin.constant.ClientConstant;
import com.flypaas.admin.constant.LogConstant.LogType;
import com.flypaas.admin.constant.MsgConstant.MsgType;
import com.flypaas.admin.constant.MsgConstant.TemplateId;
import com.flypaas.admin.constant.UserConstant;
import com.flypaas.admin.dao.MasterDao;
import com.flypaas.admin.model.PageContainer;
import com.flypaas.admin.service.LogService;
import com.flypaas.admin.service.account.DeveloperAccountService;
import com.flypaas.admin.util.ConfigUtils;
import com.flypaas.admin.util.cache.RedisUtils;
import com.flypaas.admin.util.web.HttpClientUtils;

/**
 * 信息管理-开发者管理
 * 
 * @author xiejiaan
 */
@Service
@Transactional
public class DeveloperServiceImpl implements DeveloperService {
	@Autowired
	private MasterDao dao;
	@Autowired
	private LogService logService;
	@Autowired
	private MsgService msgService;
	@Autowired
	private DeveloperAccountService developerAccountService;

	@Override
	public PageContainer query(Map<String, String> params) {
		return dao.getSearchPage("developer.query", "developer.queryCount", params);
	}

	@Override
	public Map<String, Object> getDeveloper(String sid) {
		Map<String, Object> data = dao.getOneInfo("developer.getDeveloper", sid);
		if (null != data) {
			List<Map<String, Object>> pics = dao.getSearchList("developer.getUserPic", sid);
			data.put("pics", pics);
		}
		return data;
	}

	@Override
	public Map<String, Object> saveDeveloper(Map<String, String> params) {
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> check = dao.getOneInfo("developer.checkDeveloper", params);// 查重
		if (check != null) {
			if (params.get("email").equalsIgnoreCase(check.get("email").toString())) {
				data.put("result", "fail");
				data.put("msg", "开发者账号已被使用，请重新输入");
				return data;

			} else if (params.get("mobile").equals(check.get("mobile"))) {
				data.put("result", "fail");
				data.put("msg", "联系手机已被使用，请重新输入");
				return data;
			}
		}

		int i = dao.update("developer.saveDeveloper", params);// 修改
		if (i > 0) {
			RedisUtils.flushDeveloperCache(params.get("sid"));// 刷新前台缓存信息
		}
		if (null == params.get("sale_name")) {
			params.put("sale_name", "");
		}
		if (null == params.get("sale_mobile")) {
			params.put("sale_mobile", "");
		}
		Long t = dao.getOneInfo("developer.hasSale", params);
		if (null != t && t > 0) {
			i = dao.update("developer.updateSale", params);// 修改
		} else {
			i = dao.update("developer.saveSale", params);// 保存
		}

		if ((boolean) dao.getOneInfo("developer.existsRemind", params)) {// 是否存在余额提醒
			dao.update("developer.updateRemind", params);
		} else {
			dao.insert("developer.insertRemind", params);
		}

		data.put("result", "success");
		data.put("msg", "修改成功");
		logService.add(LogType.update, "信息管理-开发者管理：修改开发者资料", params, data);
		return data;
	}

	@Override
	public Map<String, Object> getMainAccount(String sid) {
		List<Map<String, Object>> list = dao.getSearchList("developer.getTokenAndRestUrl", sid);// Token和Rest-URL
		if (list.size() == 0) {
			return null;
		}
		Map<String, Object> data = list.get(0);
		for (Map<String, Object> map : list) {
			if ("1".equals(map.get("rest_param_key"))) {
				data.put("rest_ip", map.get("rest_param_value"));
			}
			data.put("rest_port", map.get("rest_param_value"));
		}

		
		return data;
	}

	@Override
	public Map<String, Object> updateStatus(Map<String, String> params) {
		Map<String, Object> data = new HashMap<String, Object>();

		int status = NumberUtils.toInt(params.get("status"), -1);
		if (status != UserConstant.STATUS_1 && status != UserConstant.STATUS_6) {
			data.put("result", "fail");
			data.put("msg", "状态不正确，操作失败");
			return data;
		}

		String msg = status == UserConstant.STATUS_1 ? "解锁" : "关闭";
		int i = dao.update("developer.updateStatus", params);// 修改
		if (i > 0) {
			String sid = params.get("sid");
			if (status == UserConstant.STATUS_6) {
				Map<String, Object> p = new HashMap<String, Object>();
				p.put("sid", sid);
				p.put("app_status", AppConstant.STATUS_3);
				p.put("client_status", ClientConstant.STATUS_0);
				p.put("wallet_status", UserConstant.WALLET_STATUS_3);
				dao.update("developer.closeApp", p);// 删除应用
				dao.update("developer.closeClient", p);// 关闭client
				dao.update("developer.closeWallet", p);// 注销钱包

				for (Map<String, Object> map : dao.getSearchList("developer.getAllApp", sid)) {
					RedisUtils.flushAppCache(map.get("app_sid").toString());// 刷新应用的Redis缓存
				}
				for (Map<String, Object> map : dao.getSearchList("developer.getAllClient", sid)) {
					RedisUtils.flushClientCache(map.get("client_number").toString());// 刷新client的Redis缓存
				}

				HttpClientUtils.get(ConfigUtils.flypaas_domain + "/ext/closeAcct?sid=" + sid);// 开发者退出登录
				/*
				 * Map<String, Object> templateParams = new HashMap<String,
				 * Object>(); templateParams.put("developer_email",
				 * dao.getOneInfo("developer.getDeveloperEmail", sid));
				 * msgService.sendMsg(sid, MsgType.system_msg,
				 * TemplateId.developer_close, templateParams);// 发送消息
				 */
			}

			RedisUtils.flushDeveloperCache(sid);// 刷新开发者的Redis缓存
			data.put("result", "success");
			data.put("msg", msg + "成功");
		} else {
			data.put("result", "fail");
			data.put("msg", "开发者不存在，" + msg + "失败");
		}

		logService.add(LogType.update, "信息管理-开发者管理：修改开发者状态", msg, params, data);
		return data;
	}

	@Override
	public Map<String, Object> securityCorrection(String sid) {
		Map<String, Object> data = new HashMap<String, Object>();
		int i = dao.update("developer.securityCorrection", sid);
		if (i > 0) {
			Map<String, Object> templateParams = new HashMap<String, Object>();
			templateParams.put("developer_email", dao.getOneInfo("developer.getDeveloperEmail", sid));
			msgService.sendMsg(sid, MsgType.system_msg, TemplateId.security_correction, templateParams);// 发送消息

			data.put("result", "success");
			data.put("msg", "安全校正成功");
		} else {
			data.put("result", "fail");
			data.put("msg", "开发者不存在，安全校正失败");
		}

		logService.add(LogType.update, "信息管理-开发者管理：安全校正", sid, data);
		return data;
	}

	@Override
	public Map<String, Object> sendNotice(Map<String, String> params) {
		Map<String, Object> data = new HashMap<String, Object>();
		if (StringUtils.isBlank(params.get("sid"))) {
			return data;
		}
		boolean result = msgService.sendMsg(params.get("sid"), MsgType.admin_msg, params.get("msg_title"),
				params.get("msg_desc"));
		if (result) {
			data.put("result", "success");
			data.put("msg", "发送通知成功");
		} else {
			data.put("result", "fail");
			data.put("msg", "发送通知失败");
		}

		logService.add(LogType.add, "信息管理-开发者管理：发送通知", params, data);
		return data;
	}

	@Override
	public Map<String, Object> setHeavybuyer(Map<String, String> params) {
		Map<String, Object> data = new HashMap<String, Object>();

		int isHeavybuyer = NumberUtils.toInt(params.get("is_heavybuyer"), -1);
		if (isHeavybuyer != 0 && isHeavybuyer != 1) {
			data.put("result", "fail");
			data.put("msg", "值不正确，操作失败");
			return data;
		}

		String msg = isHeavybuyer == 0 ? "取消为大客户" : "设置为大客户";
		int i = dao.update("developer.setHeavybuyer", params);// 修改
		if (i > 0) {
			RedisUtils.flushDeveloperCache(params.get("sid"));// 刷新开发者的Redis缓存
			data.put("result", "success");
			data.put("msg", msg + "成功");
		} else {
			data.put("result", "fail");
			data.put("msg", "开发者不存在，" + msg + "失败");
		}

		logService.add(LogType.update, "信息管理-开发者管理：设置或取消为大客户", msg, params, data);
		return data;
	}
	
	
	@Override
	public Map<String, Object> setProxy(Map<String, String> params) {
		Map<String, Object> data = new HashMap<String, Object>();

		int isProxy = NumberUtils.toInt(params.get("isProxy"), -1);
		if (isProxy != 0 && isProxy != 1) {
			data.put("result", "fail");
			data.put("msg", "值不正确，操作失败");
			return data;
		}

		String msg = isProxy == 0 ? "取消为代理商" : "设置为代理商";
		int i = dao.update("developer.setProxy", params);// 修改
		if (i > 0) {
			RedisUtils.flushDeveloperCache(params.get("sid"));// 刷新开发者的Redis缓存
			data.put("result", "success");
			data.put("msg", msg + "成功");
		} else {
			data.put("result", "fail");
			data.put("msg", "开发者不存在，" + msg + "失败");
		}

		logService.add(LogType.update, "信息管理-开发者管理：设置或取消为代理商", msg, params, data);
		return data;
	}

	@Override
	public Map<String, Object> saveSale(Map<String, String> params) {
		Map<String, Object> data = new HashMap<String, Object>();
		Long t = dao.getOneInfo("developer.hasSale", params);
		int i = -1;
		if (null != t && t > 0) {
			i = dao.update("developer.updateSale", params);// 修改
		} else {
			i = dao.update("developer.saveSale", params);// 保存
		}
		if (i > 0) {
			RedisUtils.flushDeveloperCache(params.get("sid"));// 刷新开发者的Redis缓存
			data.put("result", "success");
			data.put("msg", "保存成功");
		} else {
			data.put("result", "fail");
			data.put("msg", "开发者不存在，保存失败");
		}

		logService.add(LogType.update, "信息管理-开发者管理：保存销售经理信息", params, data);
		return data;
	}

}
