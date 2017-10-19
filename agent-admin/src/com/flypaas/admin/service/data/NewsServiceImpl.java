package com.flypaas.admin.service.data;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.admin.constant.LogConstant.LogType;
import com.flypaas.admin.dao.MasterDao;
import com.flypaas.admin.model.PageContainer;
import com.flypaas.admin.service.LogService;

/**
 * 信息管理-管理员中心-新闻管理
 * 
 * @author xiejiaan
 */
@Service
@Transactional
public class NewsServiceImpl implements NewsService {
	@Autowired
	private MasterDao dao;
	@Autowired
	private LogService logService;

	@Override
	public PageContainer query(Map<String, String> params) {
		return dao.getSearchPage("news.query", "news.queryCount", params);
	}

	@Override
	public Map<String, Object> view(int newsId) {
		return dao.getOneInfo("news.view", newsId);
	}

	@Override
	public Map<String, Object> save(Map<String, String> params) {
		Map<String, Object> data = new HashMap<String, Object>();
		if (dao.getSearchSize("news.check", params) > 0) {// 查重
			data.put("result", "fail");
			data.put("msg", "标题已被使用，请重新输入");
			return data;
		}

		String newsId = params.get("news_id");
		if (StringUtils.isBlank(newsId)) {// 添加新闻
			int i = dao.insert("news.insert", params);
			if (i > 0) {
				data.put("result", "success");
				data.put("msg", "添加成功");
			} else {
				data.put("result", "fail");
				data.put("msg", "添加失败");
			}
			logService.add(LogType.add, "信息管理-管理员中心-新闻管理：添加新闻", params, data);

		} else {// 修改新闻
			int i = dao.update("news.update", params);
			if (i > 0) {
				data.put("result", "success");
				data.put("msg", "修改成功");
			} else {
				data.put("result", "fail");
				data.put("msg", "新闻不存在，修改失败");
			}
			logService.add(LogType.update, "信息管理-管理员中心-新闻管理：修改新闻", params, data);
		}
		return data;
	}

	@Override
	public Map<String, Object> updateStatus(int newsId, int status) {
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
		params.put("news_id", newsId);
		params.put("status", status);
		int i = dao.update("news.updateStatus", params);
		if (i > 0) {
			data.put("result", "success");
			data.put("msg", msg + "成功");
		} else {
			data.put("result", "fail");
			data.put("msg", "新闻不存在，" + msg + "失败");
		}
		logService.add(LogType.update, "信息管理-管理员中心-新闻管理：修改新闻状态", params, data);
		return data;
	}

}
