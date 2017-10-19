package com.flypaas.admin.service.data;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.admin.constant.LogConstant.LogType;
import com.flypaas.admin.dao.MasterDao;
import com.flypaas.admin.model.PageContainer;
import com.flypaas.admin.service.LogService;
import com.flypaas.admin.service.TagService;

/**
 * 信息管理-管理员管理-前台页面配置
 * 
 * @author zenglb
 */
@Service
@Transactional
public class PageConfigServiceImpl implements PageConfigService {
	@Autowired
	private MasterDao dao;
	@Autowired
	private LogService logService;
	@Autowired
	private MsgService msgService;
	@Autowired
	private TagService tagService;

	@Override
	public PageContainer query(Map<String, String> params) {
		return dao.getSearchPage("pageConfig.query", "pageConfig.queryCount", params);
	}

	@Override
	public Map<String, Object> updateStatus(Map<String, String> params) {
		Map<String, Object> data = new HashMap<String, Object>();
		int i = dao.update("pageConfig.updateStatus", params);// 修改
		if (i > 0) {
			data.put("result", "success");
			data.put("msg", "操作成功");
		} else {
			data.put("result", "fail");
			data.put("msg", "配置不存在或原始状态不对，操作失败");
		}

		logService.add(LogType.update, "信息管理-管理员管理-前台页面配置：状态变更", params, data);
		return data;
	}

	@Override
	public Map<String, Object> view(Integer id) {
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> entity = dao.getOneInfo("pageConfig.getEntity", id);// 获取应用
		if (entity != null) {
			data.put("entity", entity);
		}
		return data;
	}

	@Override
	public Map<String, Object> save(Map<String, String> params){
		Map<String, Object> data = new HashMap<String, Object>();
		if (dao.getSearchSize("pageConfig.check", params) > 0) {// 查重
			data.put("result", "fail");
			data.put("msg", "已配置，请重新编辑");
			return data;
		}
		String id = params.get("id");
		if (id == null) {// 添加
			int i = dao.insert("pageConfig.insert", params);
			if (i > 0) {
				data.put("result", "success");
				data.put("msg", "添加成功");

			} else {
				data.put("result", "fail");
				data.put("msg", "添加失败");
			}
			logService.add(LogType.add, "信息管理-管理员管理-前台页面配置：添加", params, data);

		} else {// 修改
			int i = dao.update("pageConfig.update", params);
			if (i > 0) {
				data.put("result", "success");
				data.put("msg", "修改成功");

			} else {
				data.put("result", "fail");
				data.put("msg", "配置不存在，修改失败");
			}
			logService.add(LogType.update, "信息管理-管理员管理-前台页面配置：修改", params, data);
		}
		return data;
	}

}
