package com.network.monitor.service.admin;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.network.monitor.constant.UserConstant;
import com.network.monitor.constant.LogConstant.LogType;
import com.network.monitor.dao.StatDao;
import com.network.monitor.model.PageContainer;
import com.network.monitor.service.LogService;
import com.network.monitor.util.MD5;
import com.network.monitor.util.encrypt.EncryptUtils;

/**
 * 管理员中心
 * 
 * @author xiejiaan
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);
	@Autowired
	private StatDao dao;
	@Autowired
	private LogService logService;

	@Override
	public Map<String, Object> getAdmin(String id) {
		return dao.getOneInfo("admin.getAdmin", id);
	}

	@Override
	public Map<String, Object> saveAdmin(Map<String, String> params) {
		LOGGER.debug("保存管理员资料，添加/修改：" + params);

		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> check = dao.getOneInfo("admin.checkAdmin", params);// 查重
		if (check != null) {
			if (params.get("email").equals(check.get("email"))) {
				data.put("result", "fail");
				data.put("msg", "管理账号已被使用，请重新输入");
				return data;

			} else if (params.get("mobile").equals(check.get("mobile"))) {
				data.put("result", "fail");
				data.put("msg", "联系手机已被使用，请重新输入");
				return data;
			}
		}

		String password = params.get("password");
		if (StringUtils.isNotBlank(password)) {
			password = EncryptUtils.encodeMd5(password);
			password = MD5.md5(password);
			params.put("password", password);
		} else {
			params.remove("password");
		}
		String id = params.get("id");
		if (StringUtils.isBlank(id)) {// 添加管理员
			String sid = EncryptUtils.generateSid();

			params.put("sid", sid);
			int i = dao.insert("admin.insertAdmin", params);
			if (i > 0) {
				data.put("result", "success");
				data.put("msg", "添加成功");
				params.put("user_id", String.valueOf(params.get("id")));
				dao.insert("admin.insertAdminRole", params);// 添加角色
			} else {
				data.put("result", "fail");
				data.put("msg", "添加失败");
			}
			logService.add(LogType.add, "管理员中心-管理员列表：添加管理员", params, data);

		} else {// 修改管理员
			int i = dao.update("admin.updateAdmin", params);
			if (i > 0) {
				data.put("result", "success");
				data.put("msg", "修改成功");

				String roleId = params.get("role_id");
				if (StringUtils.isNotBlank(roleId) && !roleId.equals(params.get("old_role_id"))) {// 修改角色
					params.put("user_id", String.valueOf(params.get("id")));
					dao.update("admin.updateAdminRole", params);
				}
			} else {
				data.put("result", "fail");
				data.put("msg", "管理员不存在，修改失败");
			}
			logService.add(LogType.update, "管理员中心-管理员列表：修改管理员", params, data);
		}

		return data;
	}

	@Override
	public Map<String, Object> savePassword(Map<String, String> params) {
		Map<String, Object> data = new HashMap<String, Object>();

		String password = params.get("password");
		password = EncryptUtils.encodeMd5(password);
		password = MD5.md5(password);
		params.put("password", password);

		password = params.get("newPassword");
		password = EncryptUtils.encodeMd5(password);
		password = MD5.md5(password);
		params.put("newPassword", password);

		int i = dao.update("admin.savePassword", params);
		if (i > 0) {
			data.put("result", "success");
			data.put("msg", "修改成功");
		} else {
			data.put("result", "fail");
			data.put("msg", "当前密码错误，修改失败");
		}
		logService.add(LogType.update, "管理员中心：修改密码", params, data);
		return data;
	}

	@Override
	public PageContainer query(Map<String, String> params) {
		return dao.getSearchPage("admin.query", "admin.queryCount", params);
	}

	@Override
	public Map<String, Object> updateStatus(Long id, int status) {
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		String msg;
		switch (status) {
		case UserConstant.USER_STATUS_1:
			msg = "恢复";
			break;
		case UserConstant.USER_STATUS_3:
			msg = "删除";
			break;
		default:
			data.put("result", "fail");
			data.put("msg", "不是恢复或删除，操作失败");
			return data;
		}
		if (Long.valueOf(1).equals(id)) {
			data.put("result", "fail");
			data.put("msg", "不可以删除系统超级管理员");
			return data;
		}

		params.put("id", id);
		params.put("status", status);
		int i = dao.update("admin.updateStatus", params);
		if (i > 0) {
			if (status == UserConstant.USER_STATUS_3) {
				dao.delete("admin.deleteAdminRole", params);
			}
			data.put("result", "success");
			data.put("msg", msg + "成功");
		} else {
			data.put("result", "fail");
			data.put("msg", "管理员不存在，" + msg + "失败");
		}
		logService.add(LogType.delete, "管理员中心-管理员列表：修改管理员状态", msg, params, data);
		return data;
	}

}
