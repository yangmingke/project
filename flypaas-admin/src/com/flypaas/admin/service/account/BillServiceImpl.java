package com.flypaas.admin.service.account;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.admin.constant.LogConstant.LogType;
import com.flypaas.admin.constant.SysConstant;
import com.flypaas.admin.dao.MasterDao;
import com.flypaas.admin.model.PageContainer;
import com.flypaas.admin.service.LogService;

@Service
@Transactional
public class BillServiceImpl implements BillService {
	@Autowired
	private MasterDao dao;
	@Autowired
	private LogService logService;

	@Override
	public PageContainer query(Map<String, String> params) {
		params.put("money_rate", SysConstant.money_rate);
		return dao.getSearchPage("bill.query", "bill.queryCount", params);
	}

	@Override
	public Map<String, Object> getBill(Map<String, String> params) {
		params.put("money_rate", SysConstant.money_rate);
		return dao.getOneInfo("bill.findById", params);
	}

	@Override
	public Map<String, Object> updateCloseOrder(String sid, Map<String, String> params) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("sid", sid);
		params.put("status", "5");// 关闭
		int tmp = dao.update("bill.updateCloseOrder", params);
		if (tmp > 0) {
			data.put("result", "success");
			data.put("msg", "关闭成功");
		} else {
			data.put("result", "fail");
			data.put("msg", "订单号未找到！");
		}
		logService.add(LogType.update, "账务管理-账单信息：关闭", params, data);
		return data;
	}
}