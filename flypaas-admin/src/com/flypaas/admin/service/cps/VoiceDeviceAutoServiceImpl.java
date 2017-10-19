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
 * 策略管理-音频驱动适配-音频驱动智能适配
 * 
 * @author xiejiaan
 */
@Service
@Transactional
public class VoiceDeviceAutoServiceImpl implements VoiceDeviceAutoService {
	@Autowired
	private CpsDao dao;
	@Autowired
	private LogService logService;

	@Override
	public PageContainer query(Map<String, String> params) {
		return dao.getSearchPage("voiceDeviceAuto.query", "voiceDeviceAuto.queryCount", params);
	}

	@Override
	public Map<String, Object> view(int id) {
		return dao.getOneInfo("voiceDeviceAuto.view", id);
	}

	@Override
	public Map<String, Object> save(Map<String, String> params) {
		Map<String, Object> data = new HashMap<String, Object>();
		if (dao.getSearchSize("voiceDeviceAuto.check", params) > 0) {// 查重
			data.put("result", "fail");
			data.put("msg", "手机品牌、优先级已被使用，请重新输入");
			return data;
		}

		if (StringUtils.isBlank(params.get("id"))) {// 添加
			int i = dao.insert("voiceDeviceAuto.insert", params);
			if (i > 0) {
				data.put("result", "success");
				data.put("msg", "添加成功");
			} else {
				data.put("result", "fail");
				data.put("msg", "添加失败");
			}
			logService.add(LogType.add, "策略管理-音频驱动适配-音频驱动智能适配：添加音频驱动智能适配", params, data);

		} else {// 修改
			int i = dao.update("voiceDeviceAuto.update", params);
			if (i > 0) {
				data.put("result", "success");
				data.put("msg", "修改成功");
			} else {
				data.put("result", "fail");
				data.put("msg", "音频驱动智能适配不存在，修改失败");
			}
			logService.add(LogType.update, "策略管理-音频驱动适配-音频驱动智能适配：修改音频驱动智能适配", params, data);
		}
		return data;
	}

	@Override
	public Map<String, Object> delete(int id) {
		Map<String, Object> data = new HashMap<String, Object>();
		int i = dao.update("voiceDeviceAuto.delete", id);
		if (i > 0) {
			data.put("result", "success");
			data.put("msg", "删除成功");
		} else {
			data.put("result", "fail");
			data.put("msg", "音频驱动智能适配不存在，删除失败");
		}
		logService.add(LogType.update, "策略管理-音频驱动适配-音频驱动智能适配：删除音频驱动智能适配", id, data);
		return data;
	}

}
