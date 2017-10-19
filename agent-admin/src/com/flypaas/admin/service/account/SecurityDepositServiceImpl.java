package com.flypaas.admin.service.account;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.admin.constant.BillConstant;
import com.flypaas.admin.constant.LogConstant.LogType;
import com.flypaas.admin.constant.MsgConstant;
import com.flypaas.admin.constant.MsgConstant.MsgType;
import com.flypaas.admin.constant.MsgConstant.TemplateId;
import com.flypaas.admin.constant.SysConstant;
import com.flypaas.admin.dao.MasterDao;
import com.flypaas.admin.model.PageContainer;
import com.flypaas.admin.service.LogService;
import com.flypaas.admin.service.data.MsgService;

/**
 * 账务管理-保障金账户查询
 * 
 * @author zenglb
 */
@Service
@Transactional
public class SecurityDepositServiceImpl implements SecurityDepositService {
	@Autowired
	private MasterDao dao;
	@Autowired
	private LogService logService;
	@Autowired
	private MsgService msgService;

	@Override
	public PageContainer query(Map<String, String> params) {
		params.put("money_rate", SysConstant.money_rate);
		return dao.getSearchPage("securityDeposit.query", "securityDeposit.queryCount", params);
	}

	@Override
	public Map<String, Object> view(String sid, Long id, Map<String, String> rechargeParams,
			Map<String, String> consumptionParams) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id", id);
		data.put("sid", sid);
		data.put("money_rate", SysConstant.money_rate);
		Map<String, Object> entity = dao.getOneInfo("securityDeposit.getEntity", data);// 获取开发者账务
		if (entity != null) {
			data.put("entity", entity);

			rechargeParams.put("money_rate", SysConstant.money_rate);
			rechargeParams.put("payStatus", BillConstant.PAY_STATUS_3 + "");
			data.put("recharge_page", dao.getSearchPage("securityDeposit.queryRecharge",
					"securityDeposit.queryRechargeCount", rechargeParams));// 获取充值记录

			consumptionParams.put("money_rate", SysConstant.money_rate);
			consumptionParams.put("payStatus", BillConstant.PAY_STATUS_3 + "");
			data.put("consumption_page", dao.getSearchPage("securityDeposit.queryConsumption",
					"securityDeposit.queryConsumptionCount", consumptionParams));// 获取充值记录
		}
		return data;
	}

	@Override
	public Map<String, Object> updateEnableFlag(String sid, Map<String, String> params) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("sid", sid);
		String enableFlag = params.get("enableFlag");
		TemplateId tempId = MsgConstant.TemplateId.security_id_22;
		if ("1".equals(enableFlag)) {// 解锁
			params.put("is_contract", "1");
			params.put("s_status", "4");
			params.put("t_status", enableFlag);
			tempId = MsgConstant.TemplateId.security_id_23;
		} else if ("3".equals(enableFlag)) {// 锁定
			params.put("is_contract", "0");
			params.put("s_status", "5");
			params.put("t_status", enableFlag);
			tempId = MsgConstant.TemplateId.security_id_22;
		} else {
			data.put("result", "fail");
			data.put("msg", "参数错误，操作失败");
			return data;
		}
		params.put("t_status_old", params.get("enableFlagOld"));
		int tmp = updateEnableFlagDao(params);
		String msg = params.get("mngType");
		if (tmp > 0) {
			data.put("result", "success");
			data.put("msg", msg + "成功");
			Map<String, Object> templateParams = new HashMap<String, Object>();
			msgService.sendMsg(params.get("sid"), MsgType.system_msg, tempId, templateParams);// 发送消息
		} else {
			data.put("result", "fail");
			data.put("msg", "账户信息不存在或状态不对，" + msg + "失败");
		}
		logService.add(LogType.update, "账务管理-保障金管理：状态变更 ：" + msg, params, data);
		return data;
	}

	private int updateEnableFlagDao(Map<String, String> params) {
		return dao.update("securityDeposit.updateEnableFlag", params);
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
			tmp = recharge(params);
		} else if ("扣费".equals(mngType)) {
			tmp = deduction(params);
			//data.put("result", "fail");
			//data.put("msg", "暂不支持扣费");
			//return data;
		} else {
			data.put("result", "fail");
			data.put("msg", "参数错误，操作失败");
			return data;
		}
		String msg = params.get("mngType");
		if (tmp > 0) {
			if ("充值".equals(mngType)) {
				params.put("security_money", SysConstant.SECURITY_MONEY + "");
				dao.update("securityDeposit.setContractAtRechange", params);
			}
			data.put("result", "success");
			data.put("msg", msg + "成功");
			Map<String, Object> templateParams = new HashMap<String, Object>();
			msgService.sendMsg(params.get("sid"), MsgType.system_msg, TemplateId.security_id_24, templateParams);// 发送消息
		} else {
			data.put("result", "fail");
			data.put("msg", "账户信息不存在或状态不对，" + msg + "失败");
		}
		logService.add(LogType.update, "账务管理-保障金管理：余额变更 ：" + msg, params, data);
		return data;
	}

	public int recharge(Map<String, String> params) {
		int tmp = -1;
		tmp = dao.update("securityDeposit.rechargeOrGifts", params);
		if (tmp > 0) {
			params.put("charge", params.get("cv"));
			params.put("charge_type", BillConstant.RECHARGE_TYPE_RECHARG);
			params.put("status", BillConstant.PAY_STATUS_3 + "");
			dao.update("securityDeposit.insertPayOrder", params);
		}
		return tmp;
	}

	public int deduction(Map<String, String> params) {
		int tmp = -1;
		tmp = dao.update("securityDeposit.deduction", params);
		if (tmp > 0) {
			params.put("charge", params.get("cv"));
			params.put("to_account_type", "4");//管理员提现
			params.put("frm_account_type", "1");//保障金账户
			dao.update("securityDeposit.insertDeductionLog", params);
		}
		return tmp;
	}
}
