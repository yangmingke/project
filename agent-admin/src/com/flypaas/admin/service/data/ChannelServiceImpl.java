package com.flypaas.admin.service.data;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.admin.constant.LogConstant.LogType;
import com.flypaas.admin.dao.MasterDao;
import com.flypaas.admin.model.PageContainer;
import com.flypaas.admin.service.LogService;

/**
 * 信息管理-渠道管理
 * 
 * @author zenglb
 */
@Service
@Transactional
public class ChannelServiceImpl implements ChannelService {
	@Autowired
	private MasterDao dao;
	@Autowired
	private LogService logService;
	@Autowired
	private MsgService msgService;

	@Override
	public PageContainer query(Map<String, String> params) {
		return dao.getSearchPage("channel.query", "channel.queryCount", params);
	}

	@Override
	public Map<String, Object> updateStatus(Map<String, String> params) {
		Map<String, Object> data = new HashMap<String, Object>();
		int i = dao.update("channel.updateStatus", params);// 修改
		if (i > 0) {
			data.put("result", "success");
			data.put("msg", "操作成功");
		} else {
			data.put("result", "fail");
			data.put("msg", "渠道不存在或原始状态不对，操作4失败");
		}

		logService.add(LogType.update, "信息管理-渠道管理：渠道状态变更", params, data);
		return data;
	}

	@Override
	public Map<String, Object> view(Integer id) {
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> entity = dao.getOneInfo("channel.getEntity", id);// 获取应用
		if (entity != null) {
			data.put("entity", entity);
		}
		return data;
	}

	@Override
	public Map<String, Object> save(Map<String, String> params) {
		Map<String, Object> data = new HashMap<String, Object>();
		if (dao.getSearchSize("channel.check", params) > 0) {// 查重
			data.put("result", "fail");
			data.put("msg", "渠道名称已被使用，请重新输入");
			return data;
		}

		Integer id = NumberUtils.createInteger(params.get("id"));
		if (id == null) {// 添加渠道
			int i = dao.insert("channel.insert", params);
			if (i > 0) {
				data.put("result", "success");
				data.put("msg", "添加成功");

			} else {
				data.put("result", "fail");
				data.put("msg", "添加失败");
			}
			logService.add(LogType.add, "信息管理-渠道管理：添加渠道", params, data);

		} else {// 修改渠道
			int i = dao.update("channel.update", params);
			if (i > 0) {
				data.put("result", "success");
				data.put("msg", "修改成功");

			} else {
				data.put("result", "fail");
				data.put("msg", "渠道不存在，修改失败");
			}
			logService.add(LogType.update, "信息管理-渠道管理：修改渠道", params, data);
		}
		return data;
	}
}
