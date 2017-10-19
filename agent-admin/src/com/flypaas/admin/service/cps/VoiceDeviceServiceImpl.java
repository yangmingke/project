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
 * 策略管理-音频驱动适配-音频驱动适配清单
 * 
 * @author xiejiaan
 */
@Service
@Transactional
public class VoiceDeviceServiceImpl implements VoiceDeviceService {
	@Autowired
	private CpsDao dao;
	@Autowired
	private LogService logService;

	@Override
	public PageContainer query(Map<String, String> params) {
		return dao.getSearchPage("voiceDevice.query", "voiceDevice.queryCount", params);
	}

	@Override
	public Map<String, Object> view(int id) {
		return dao.getOneInfo("voiceDevice.view", id);
	}

	@Override
	public Map<String, Object> save(Map<String, String> params) {
		Map<String, Object> data = new HashMap<String, Object>();

		if (StringUtils.isBlank(params.get("id"))) {// 添加
			int i = dao.insert("voiceDevice.insert", params);
			if (i > 0) {
				data.put("result", "success");
				data.put("msg", "添加成功");
			} else {
				data.put("result", "fail");
				data.put("msg", "添加失败");
			}
			logService.add(LogType.add, "策略管理-音频驱动适配-音频驱动适配清单：添加音频驱动适配清单", params, data);

		} else {// 修改
			int i = dao.update("voiceDevice.update", params);
			if (i > 0) {
				data.put("result", "success");
				data.put("msg", "修改成功");
			} else {
				data.put("result", "fail");
				data.put("msg", "音频驱动适配清单不存在，修改失败");
			}
			logService.add(LogType.update, "策略管理-音频驱动适配-音频驱动适配清单：修改音频驱动适配清单", params, data);
		}
		return data;
	}

	@Override
	public Map<String, Object> delete(int id) {
		Map<String, Object> data = new HashMap<String, Object>();
		int i = dao.update("voiceDevice.delete", id);
		if (i > 0) {
			data.put("result", "success");
			data.put("msg", "删除成功");
		} else {
			data.put("result", "fail");
			data.put("msg", "音频驱动适配清单不存在，删除失败");
		}
		logService.add(LogType.update, "策略管理-音频驱动适配-音频驱动适配清单：删除音频驱动适配清单", id, data);
		return data;
	}

}
