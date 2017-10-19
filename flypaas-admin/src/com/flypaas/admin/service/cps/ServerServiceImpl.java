package com.flypaas.admin.service.cps;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.admin.constant.LogConstant.LogType;
import com.flypaas.admin.dao.CpsDao;
import com.flypaas.admin.model.PageContainer;
import com.flypaas.admin.service.LogService;

/**
 * 策略管理-策略权限配置-服务器地址
 * 
 * @author xiejiaan
 */
@Service
@Transactional
public class ServerServiceImpl implements ServerService {
	@Autowired
	private CpsDao dao;
	@Autowired
	private LogService logService;

	private void processForm(Map<String, String> params) {
		String server = params.get("server");
		if (StringUtils.isBlank(server)) {
			server = "1";
		}
		int s = Integer.parseInt(server);
		String table = null;
		switch (s) {
		case 1:
			table = "t_cs_list";// CS服务器
			break;
		case 2:
			table = "t_rtpp_list";// RTPP服务器
			break;
		case 3:
			table = "t_vps_list";// VPS服务器
			break;
		}
		params.put("table", table);
	}

	@Override
	public PageContainer query(Map<String, String> params) {
		processForm(params);
		return dao.getSearchPage("server.query", "server.queryCount", params);
	}

	@Override
	public Map<String, Object> view(Map<String, String> params) {
		processForm(params);
		return dao.getOneInfo("server.view", params);
	}

	@Override
	public Map<String, Object> save(Map<String, String> params) {
		processForm(params);
		Map<String, Object> data = new HashMap<String, Object>();
		if (dao.getSearchSize("server.check", params) > 0) {// 查重
			data.put("result", "fail");
			data.put("msg", "ip地址、端口号已被使用，请重新输入");
			return data;
		}

		if (StringUtils.isBlank(params.get("id"))) {// 添加
			int i = dao.insert("server.insert", params);
			if (i > 0) {
				data.put("result", "success");
				data.put("msg", "添加成功");
			} else {
				data.put("result", "fail");
				data.put("msg", "添加失败");
			}
			logService.add(LogType.add, "策略管理-策略权限配置-服务器地址：添加服务器地址", params, data);

		} else {// 修改
			int i = dao.update("server.update", params);
			if (i > 0) {
				data.put("result", "success");
				data.put("msg", "修改成功");
			} else {
				data.put("result", "fail");
				data.put("msg", "服务器地址不存在，修改失败");
			}
			logService.add(LogType.update, "策略管理-策略权限配置-服务器地址：修改服务器地址", params, data);
		}
		return data;
	}

	@Override
	public Map<String, Object> delete(Map<String, String> params) {
		processForm(params);
		Map<String, Object> data = new HashMap<String, Object>();
		int i = dao.update("server.delete", params);
		if (i > 0) {
			data.put("result", "success");
			data.put("msg", "删除成功");
		} else {
			data.put("result", "fail");
			data.put("msg", "服务器地址不存在，删除失败");
		}
		logService.add(LogType.update, "策略管理-策略权限配置-服务器地址：删除服务器地址", params, data);
		return data;
	}

}
