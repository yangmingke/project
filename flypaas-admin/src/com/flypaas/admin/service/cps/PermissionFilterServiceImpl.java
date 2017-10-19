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
import com.flypaas.admin.util.JsonUtils;

/**
 * 策略管理-策略权限配置-策略权限过滤
 * 
 * @author xiejiaan
 */
@Service
@Transactional
public class PermissionFilterServiceImpl implements PermissionFilterService {
	@Autowired
	private CpsDao dao;
	@Autowired
	private LogService logService;
	/**
	 * permission字段（配置权限）包含的元素
	 */
	private static final String[] permissionConfig = { "iceenable", "audiofec", "logreport", "autoadapter",
			"vqmenable", "prtpenable", "nettype", "vp8enable", "h264enable", "startbitrate", "minbitrate",
			"maxbitrate", "firstpt" };

	@Override
	public PageContainer query(Map<String, String> params) {
		return dao.getSearchPage("permissionFilter.query", "permissionFilter.queryCount", params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> view(int id) {
		Map<String, Object> data = dao.getOneInfo("permissionFilter.view", id);
		data.putAll(JsonUtils.toObject(data.get("permission").toString(), Map.class));
		return data;
	}

	@Override
	public Map<String, Object> save(Map<String, String> params) {
		Map<String, Object> data = new HashMap<String, Object>();
		if (dao.getSearchSize("permissionFilter.check", params) > 0) {// 查重
			data.put("result", "fail");
			data.put("msg", "用户帐号已被使用，请重新输入");
			return data;
		}

		Map<String, String> permission = new HashMap<String, String>();
		for (String key : permissionConfig) {
			permission.put(key, params.get(key));
		}
		params.put("permission", JsonUtils.toJson(permission));

		if (StringUtils.isBlank(params.get("id"))) {// 添加
			int i = dao.insert("permissionFilter.insert", params);
			if (i > 0) {
				data.put("result", "success");
				data.put("msg", "添加成功");
			} else {
				data.put("result", "fail");
				data.put("msg", "添加失败");
			}
			logService.add(LogType.add, "策略管理-策略权限配置-策略权限过滤：添加策略权限过滤", params, data);

		} else {// 修改
			int i = dao.update("permissionFilter.update", params);
			if (i > 0) {
				data.put("result", "success");
				data.put("msg", "修改成功");
			} else {
				data.put("result", "fail");
				data.put("msg", "策略权限过滤不存在，修改失败");
			}
			logService.add(LogType.update, "策略管理-策略权限配置-策略权限过滤：修改策略权限过滤", params, data);
		}
		return data;
	}

	@Override
	public Map<String, Object> delete(int id) {
		Map<String, Object> data = new HashMap<String, Object>();
		int i = dao.update("permissionFilter.delete", id);
		if (i > 0) {
			data.put("result", "success");
			data.put("msg", "删除成功");
		} else {
			data.put("result", "fail");
			data.put("msg", "策略权限过滤不存在，删除失败");
		}
		logService.add(LogType.update, "策略管理-策略权限配置-策略权限过滤：删除策略权限过滤", id, data);
		return data;
	}

}
