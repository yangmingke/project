package com.flypaas.admin.service.account;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.admin.constant.LogConstant.LogType;
import com.flypaas.admin.constant.PackageConstant;
import com.flypaas.admin.constant.SysConstant;
import com.flypaas.admin.dao.MasterDao;
import com.flypaas.admin.model.PageContainer;
import com.flypaas.admin.service.LogService;

/**
 * 账务管理-套餐变更日志
 * 
 * @author xiejiaan
 */
@Service
@Transactional
public class ChangePackageServiceImpl implements ChangePackageService {
	@Autowired
	private MasterDao dao;
	@Autowired
	private LogService logService;

	@Override
	public PageContainer query(Map<String, String> params) {
		params.put("money_rate", SysConstant.money_rate);
		params.put("event_id_1017", String.valueOf(PackageConstant.event_id_1017));
		return dao.getSearchPage("changePackage.query", "changePackage.queryCount", params);
	}

	@Override
	public Map<String, Object> history(String sid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sid", sid);
		params.put("money_rate", SysConstant.money_rate);
		params.put("event_id_1017", PackageConstant.event_id_1017);

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("developer", dao.getOneInfo("changePackage.developer", params));
		data.put("history", dao.getSearchList("changePackage.history", params));
		return data;
	}

	@Override
	public Map<String, Object> restore(int id) {
		Map<String, Object> data = new HashMap<String, Object>();
		int i = dao.update("changePackage.insertLog", id);
		if (i > 0) {
			dao.update("changePackage.restore", id);
			data.put("result", "success");
			data.put("msg", "还原成功");
		} else {
			data.put("result", "fail");
			data.put("msg", "套餐变更日志不存在，还原失败");
		}
		logService.add(LogType.update, "账务管理-套餐变更日志：还原", id, data);
		return data;
	}

}
