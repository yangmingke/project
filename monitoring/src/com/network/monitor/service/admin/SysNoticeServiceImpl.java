package com.network.monitor.service.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.network.monitor.constant.LogConstant.LogType;
import com.network.monitor.dao.StatDao;
import com.network.monitor.model.PageContainer;
import com.network.monitor.service.LogService;

/**
 * 管理员中心-系统通知管理
 * 
 * @author zenglb
 */
@Service
@Transactional
public class SysNoticeServiceImpl implements SysNoticeService {
	@Autowired
	private StatDao dao;
	@Autowired
	private LogService logService;

	@Override
	public PageContainer query(Map<String, String> params) {
		return dao.getSearchPage("sysNotice.query", "sysNotice.queryCount", params);
	}

	@Override
	public Map<String, Object> view(Integer noticeId) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("view", dao.getOneInfo("sysNotice.view", noticeId));
		data.put("allTimeRange", dao.getSearchList("sysNotice.allTimeRange", null));
		return data;
	}

	@Override
	public Map<String, Object> save(Map<String, String> params) {
		Map<String, Object> data = new HashMap<String, Object>();
		if (dao.getSearchSize("sysNotice.check", params) > 0) {// 查重
			data.put("result", "fail");
			data.put("msg", "通知时段名称已被使用，请重新输入");
			return data;
		}

		Integer noticeId = NumberUtils.createInteger(params.get("notice_id"));
		if (noticeId == null) {// 添加审核通知时段
			int i = dao.insert("sysNotice.insert", params);
			if (i > 0) {
				String user_ids = params.get("user_id");
				if (StringUtils.isNotBlank(user_ids)) {
					data = dao.getOneInfo("sysNotice.getId", params.get("name"));
					noticeId = Integer.valueOf(data.get("notice_id").toString());

					List<Map<String, Object>> noticeUserList = new ArrayList<Map<String, Object>>();
					Map<String, Object> p;
					for (String user_id : user_ids.split(",")) {
						p = new HashMap<String, Object>();
						p.put("notice_id", noticeId);
						p.put("user_id", user_id);
						noticeUserList.add(p);
					}
					dao.insert("sysNotice.insertNoticeUser", noticeUserList);// 添加审核通知时段和用户的关系表
				}
				data.put("result", "success");
				data.put("msg", "添加成功");

			} else {
				data.put("result", "fail");
				data.put("msg", "添加失败");
			}
			logService.add(LogType.add, "信息管理-管理员中心-审核通知管理：添加审核通知时段", params, data);

		} else {// 修改审核通知时段
			int i = dao.update("sysNotice.update", params);
			if (i > 0) {
				if ("true".equals(params.get("is_update_user"))) {
					dao.delete("sysNotice.deleteNoticeUser", noticeId);

					String user_ids = params.get("user_id");
					if (StringUtils.isNotBlank(user_ids)) {
						List<Map<String, Object>> noticeUserList = new ArrayList<Map<String, Object>>();
						Map<String, Object> p;
						for (String user_id : user_ids.split(",")) {
							p = new HashMap<String, Object>();
							p.put("notice_id", noticeId);
							p.put("user_id", user_id);
							noticeUserList.add(p);
						}
						dao.insert("sysNotice.insertNoticeUser", noticeUserList);
					}
				}

				data.put("result", "success");
				data.put("msg", "修改成功");

			} else {
				data.put("result", "fail");
				data.put("msg", "审核通知时段不存在，修改失败");
			}
			logService.add(LogType.update, "信息管理-管理员中心-审核通知管理：修改审核通知时段", params, data);
		}
		return data;
	}

	@Override
	public Map<String, Object> updateStatus(int noticeId, int status) {
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
		params.put("notice_id", noticeId);
		params.put("status", status);
		int i = dao.update("sysNotice.updateStatus", params);
		if (i > 0) {
			data.put("result", "success");
			data.put("msg", msg + "成功");
		} else {
			data.put("result", "fail");
			data.put("msg", "审核通知时段不存在，" + msg + "失败");
		}
		logService.add(LogType.update, "信息管理-管理员中心-审核通知管理：修改审核通知时段状态", params, data);
		return data;
	}

}
