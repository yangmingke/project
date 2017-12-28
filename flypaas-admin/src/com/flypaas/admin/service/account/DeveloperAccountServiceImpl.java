package com.flypaas.admin.service.account;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.admin.constant.BillConstant;
import com.flypaas.admin.constant.LogConstant.LogType;
import com.flypaas.admin.constant.MsgConstant.MsgType;
import com.flypaas.admin.constant.MsgConstant.TemplateId;
import com.flypaas.admin.constant.SysConstant;
import com.flypaas.admin.dao.CdrDao;
import com.flypaas.admin.dao.MasterDao;
import com.flypaas.admin.dao.StatDao;
import com.flypaas.admin.model.PageContainer;
import com.flypaas.admin.service.BillDtlService;
import com.flypaas.admin.service.LogService;
import com.flypaas.admin.service.data.MsgService;
import com.flypaas.admin.util.rest.utils.DateUtil;

/**
 * 账务管理-开发者账务
 * 
 * @author xiejiaan
 */
@Service
@Transactional
public class DeveloperAccountServiceImpl implements DeveloperAccountService {
	@Autowired
	private MasterDao dao;
	@Autowired
	private CdrDao cdrDao;
	@Autowired
	private LogService logService;
	@Autowired
	private BillDtlService billDtlService;
	@Autowired
	private MsgService msgService;

	@Override
	public PageContainer query(Map<String, String> params) {
		params.put("money_rate", SysConstant.money_rate);
		return dao.getSearchPage("developerAccount.query", "developerAccount.queryCount", params);
	}

	@Override
	public Map<String, Object> view(String sid, Map<String, String> rechargeParams,
			Map<String, String> consumptionParams) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("sid", sid);
		data.put("money_rate", SysConstant.money_rate);
		Map<String, Object> account = dao.getOneInfo("developerAccount.getAccount", data);// 获取开发者账务
		if (account != null) {
			data.put("account", account);

			rechargeParams.put("money_rate", SysConstant.money_rate);
			rechargeParams.put("payStatus", BillConstant.PAY_STATUS_3 + "");
			// consumptionParams.put("money_rate", SysConstant.money_rate);
			data.put("recharge_page", dao.getSearchPage("developerAccount.queryRecharge",
					"developerAccount.queryRechargeCount", rechargeParams));// 获取充值记录
			/*
			 * data.put("consumption_page",
			 * dao.getSearchPage("developerAccount.queryConsumption",
			 * "developerAccount.queryConsumptionCount", consumptionParams));//
			 * 获取消费日志
			 */
		}
		return data;
	}

	@Override
	public Map<String, Object> updateEnableFlag(String sid, Map<String, String> params) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("sid", sid);
		int tmp = updateEnableFlagDao(params);
		String msg = params.get("mngType");
		if (tmp > 0) {
			data.put("result", "success");
			data.put("msg", msg + "成功");
			String enableFlag = params.get("enableFlag");
			if ((BillConstant.ENABLE_FLAG_CANCELLED + "").equals(enableFlag)
					|| (BillConstant.ENABLE_FLAG_FROZEN + "").equals(enableFlag)) { // 已注销，冻结，通知
				Map<String, Object> templateParams = new HashMap<String, Object>();
				Map<String, Object> pp = new HashMap<String, Object>();
				pp.put("sid", params.get("sid"));
				pp.put("money_rate", SysConstant.money_rate);
				Map<String, Object> account = dao.getOneInfo("developerAccount.getAccount", pp);// 获取开发者账务
				templateParams.put("developer_email", account.get("email"));
				templateParams.put("acct_id", account.get("acct_id"));
				templateParams.put("biz_type", msg);
				msgService.sendMsg(params.get("sid"), MsgType.system_msg, TemplateId.template_id_13, templateParams);// 发送消息
			}

		} else {
			data.put("result", "fail");
			data.put("msg", "账户信息不存在或状态不对，" + msg + "失败");
		}
		logService.add(LogType.update, "账务管理-开发者账务：状态变更 ：" + msg, params, data);
		return data;
	}

	@Override
	public int updateEnableFlagDao(Map<String, String> params) {
		return dao.update("developerAccount.updateEnableFlag", params);
	}

	@Override
	public Map<String, Object> updateBalance(String sid, Map<String, String> params) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("sid", sid);
		String mngType = params.get("mngType");
		String changeValue = params.get("changeValue");
		if (!NumberUtils.isNumber(changeValue) || !changeValue.equals(params.get("changeValueCheck"))) {
			data.put("result", "fail");
			data.put("msg", "金额填写错误，操作失败");
			return data;
		}
		Double cv = Double.valueOf(changeValue);// 换算转化
		cv = cv * SysConstant.money_rate_int;
		params.put("cv", String.valueOf(cv.longValue()));

		int tmp = -1;
		if ("充值".equals(mngType)) {
			tmp = billDtlService.recharge(params);
		} else if ("赠送".equals(mngType)) {
			tmp = billDtlService.gifts(params);
		} else if ("扣费".equals(mngType)) {
			tmp = billDtlService.deduction(params);
		} else {
			data.put("result", "fail");
			data.put("msg", "参数错误，操作失败");
			return data;
		}
		String msg = params.get("mngType");
		if (tmp > 0) {
			data.put("result", "success");
			data.put("msg", msg + "成功");
			Map<String, Object> templateParams = new HashMap<String, Object>();

			Map<String, Object> pp = new HashMap<String, Object>();
			pp.put("sid", params.get("sid"));
			pp.put("money_rate", SysConstant.money_rate);
			Map<String, Object> account = dao.getOneInfo("developerAccount.getAccount", pp);// 获取开发者账务
			templateParams.put("developer_email", account.get("email"));
			templateParams.put("acct_id", account.get("acct_id"));
			templateParams.put("created", new SimpleDateFormat("yyyy/M/dd HH:mm:ss").format(new Date()));
			templateParams.put("biz_type", msg);
			templateParams.put("fee", changeValue);
			templateParams.put("createDate", new SimpleDateFormat("yyyy/M/dd").format(new Date()));
			msgService.sendMsg(params.get("sid"), MsgType.system_msg, TemplateId.template_id_14, templateParams);// 发送消息
		} else {
			data.put("result", "fail");
			data.put("msg", "账户信息不存在或状态不对，" + msg + "失败");
		}
		logService.add(LogType.update, "账务管理-开发者账务：余额变更 ：" + msg, params, data);
		return data;
	}

	@Override
	public Map<String, Object> saveCreditBalance(Map<String, String> params) {
		Map<String, Object> data = new HashMap<String, Object>();
		String credit_balance = params.get("credit_balance");
		if (!NumberUtils.isNumber(credit_balance)) {
			data.put("result", "fail");
			data.put("msg", "金额填写错误，操作失败");
			return data;
		}
		Double cv = Double.valueOf(credit_balance);// 换算转化
		cv = cv * SysConstant.money_rate_int;
		params.put("cv", String.valueOf(cv.longValue()));
		int tmp = -1;
		tmp = dao.update("developerAccount.saveCreditBalance", params);
		if (tmp > 0) {
			data.put("result", "success");
			data.put("msg", "设置成功");

		} else {
			data.put("result", "fail");
			data.put("msg", "账户信息不存在或状态不对,设置失败");
		}
		logService.add(LogType.update, "账务管理-开发者账务：设置信用额度" , params, data);
		return data;
	}

	@Override
	public Map<String, Object> queryTraffic(Map<String, String> sqlParams) {
		Map<String, Object> data = new HashMap<String, Object>();
		String date = sqlParams.get("date");
		sqlParams.put("date", date.replaceAll("-", "")); //将data去除“-”
		List<Map<String, Object>> developerTrafficList = cdrDao.getSearchList("developerAccount.queryTraffic", sqlParams);
		data.put("developerTrafficList", developerTrafficList);
		return data;
	}

	@Override
	public PageContainer queryFeeTime(Map<String, String> params) {
		String date = params.get("date");
		if(date == null || "".equals(date)){
			date = DateUtil.getStrCurrentDate();
		}
		date = date.replaceAll("-", "");
		params.put("date", date);
		PageContainer page = cdrDao.getSearchPage("developerAccount.queryFeeTime", "developerAccount.queryFeeTimeCount", params);
		return page;
	}
}
