package com.flypaas.admin.service.cps;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.admin.constant.LogConstant.LogType;
import com.flypaas.admin.dao.CpsDao;
import com.flypaas.admin.model.PageContainer;
import com.flypaas.admin.service.LogService;

/**
 * 策略管理-音频驱动适配-音频驱动适配异常
 * 
 * @author xiejiaan
 */
@Service
@Transactional
public class VoiceDeviceExceptionServiceImpl implements VoiceDeviceExceptionService {
	@Autowired
	private CpsDao dao;
	@Autowired
	private LogService logService;

	@Override
	public PageContainer query(Map<String, String> params) {
		return dao.getSearchPage("voiceDeviceException.query", "voiceDeviceException.queryCount", params);
	}

	@Override
	public Map<String, Object> view(int id) {
		return dao.getOneInfo("voiceDeviceException.view", id);
	}

	@Override
	public Map<String, Object> delete(int id) {
		Map<String, Object> data = new HashMap<String, Object>();
		int i = dao.update("voiceDeviceException.delete", id);
		if (i > 0) {
			data.put("result", "success");
			data.put("msg", "删除成功");
		} else {
			data.put("result", "fail");
			data.put("msg", "音频驱动适配异常不存在，删除失败");
		}
		logService.add(LogType.update, "策略管理-音频驱动适配-音频驱动适配异常：删除音频驱动适配异常", id, data);
		return data;
	}
}
