package com.flypaas.admin.service.data;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.admin.constant.LogConstant.LogType;
import com.flypaas.admin.constant.SysConstant;
import com.flypaas.admin.dao.MasterDao;
import com.flypaas.admin.model.PageContainer;
import com.flypaas.admin.service.LogService;

/**
 * 信息管理-管理员中心-业务配置
 * 
 * @author zenglb
 */
@Service
@Transactional
public class BusinessConfigServiceImpl implements BusinessConfigService {
	@Autowired
	private MasterDao dao;
	@Autowired
	private LogService logService;

	@Override
	public PageContainer query(Map<String, String> params) {
		params.put("money_rate", SysConstant.money_rate);
		return dao.getSearchPage("businessConfig.query", "businessConfig.queryCount", params);
	}

	@Override
	public Map<String, Object> view(int newsId) {
		return dao.getOneInfo("businessConfig.view", newsId);
	}

	@Override
	public Map<String, Object> save(Map<String, String> params) {
		params.put("money_rate", SysConstant.money_rate);
		Map<String, Object> data = new HashMap<String, Object>();
		String sid = params.get("sid");
		if(null == sid){
			data.put("result", "fail");
			data.put("msg", "参数不对!");
			return data;
		}
		sid = sid.trim();
		if(sid.indexOf("@") >-1){
			 params.put("email", sid);
			 params.remove("sid");
		}
		sid = dao.getOneInfo("businessConfig.findSid", params);
		if (null == sid) {// 查重
			data.put("result", "fail");
			data.put("msg", "开发者没有找到!");
			return data;
		}else{
			params.put("sid", sid);
		}
		if (dao.getSearchSize("businessConfig.check", params) > 0) {// 查重
			data.put("result", "fail");
			data.put("msg", "业务配置已配置,请前往修改!");
			return data;
		}
		
		String id = params.get("id");
		if (StringUtils.isBlank(id)) {// 添加新闻
			int i = dao.insert("businessConfig.insert", params);
			if (i > 0) {
				data.put("result", "success");
				data.put("msg", "添加成功");
			} else {
				data.put("result", "fail");
				data.put("msg", "添加失败");
			}
			logService.add(LogType.add, "信息管理-管理员中心-业务配置：添加业务配置", params, data);

		} else {// 修改新闻
			int i = dao.update("businessConfig.update", params);
			if (i > 0) {
				data.put("result", "success");
				data.put("msg", "修改成功");
			} else {
				data.put("result", "fail");
				data.put("msg", "业务配置不存在，修改失败");
			}
			logService.add(LogType.update, "信息管理-管理员中心-业务配置：修改业务配置", params, data);
		}
		return data;
	}

	@Override
	public Map<String, Object> updateStatus(int id, int status) {
		Map<String, Object> data = new HashMap<String, Object>();
		String msg;
		switch (status) {
		case 0:
			msg = "关闭";
			break;
		case 1:
			msg = "启用";
			break;
		case 2:
			msg = "删除";
			break;
		default:
			data.put("result", "fail");
			data.put("msg", "状态不正确，操作失败");
			return data;
		}

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("status", status);
		int i = dao.update("businessConfig.updateStatus", params);
		if (i > 0) {
			data.put("result", "success");
			data.put("msg", msg + "成功");
		} else {
			data.put("result", "fail");
			data.put("msg", "业务配置不存在，" + msg + "失败");
		}
		logService.add(LogType.update, "信息管理-管理员中心-业务配置：修改业务配置状态", params, data);
		return data;
	}

}
