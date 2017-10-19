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
import com.flypaas.admin.util.cache.RedisUtils;

/**
 * 信息管理-管理员中心-敏感字管理
 * 
 * @author xiejiaan
 */
@Service
@Transactional
public class KeywordServiceImpl implements KeywordService {
	@Autowired
	private MasterDao dao;
	@Autowired
	private LogService logService;

	@Override
	public PageContainer query(Map<String, String> params) {
		return dao.getSearchPage("keyword.query", "keyword.queryCount", params);
	}

	@Override
	public Map<String, Object> view(int wordId) {
		return dao.getOneInfo("keyword.view", wordId);
	}

	@Override
	public Map<String, Object> save(Map<String, String> params) {
		Map<String, Object> data = new HashMap<String, Object>();
		if (dao.getSearchSize("keyword.check", params) > 0) {// 查重
			data.put("result", "fail");
			data.put("msg", "敏感字已被使用，请重新输入");
			return data;
		}

		String wordId = params.get("word_id");
		params.put("word", params.get("word").toLowerCase());
		if (StringUtils.isBlank(wordId)) {// 添加敏感字
			dao.insert("keyword.insert", params);
			data.put("result", "success");
			data.put("msg", "添加成功");
			logService.add(LogType.add, "信息管理-管理员中心-敏感字管理：添加敏感字", params);
		} else {// 修改敏感字
			dao.update("keyword.update", params);
			data.put("result", "success");
			data.put("msg", "修改成功");
			logService.add(LogType.update, "信息管理-管理员中心-敏感字管理：修改敏感字", params);
		}
		RedisUtils.flushKeywordCache();// 刷新缓存
		return data;
	}

	@Override
	public Map<String, Object> delete(Map<String, String> params) {
		dao.delete("keyword.delete", params);
		RedisUtils.flushKeywordCache();// 刷新缓存

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("result", "success");
		data.put("msg", "删除成功");
		logService.add(LogType.delete, "信息管理-管理员中心-敏感字管理：删除敏感字", params);
		return data;
	}

}
