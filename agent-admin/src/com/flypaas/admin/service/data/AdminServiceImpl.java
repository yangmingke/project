package com.flypaas.admin.service.data;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.admin.constant.LogConstant.LogType;
import com.flypaas.admin.constant.UserConstant;
import com.flypaas.admin.dao.MasterDao;
import com.flypaas.admin.model.PageContainer;
import com.flypaas.admin.service.LogService;
import com.flypaas.admin.util.ConfigUtils;
import com.flypaas.admin.util.JsonUtils;
import com.flypaas.admin.util.MD5;
import com.flypaas.admin.util.StrUtils;
import com.flypaas.admin.util.api.RestUtils;
import com.flypaas.admin.util.api.RestUtils.SmsTemplateId;
import com.flypaas.admin.util.encrypt.EncryptUtils;
import com.flypaas.admin.util.rest.utils.DateUtil;

/**
 * 信息管理-管理员中心
 * 
 * @author xiejiaan
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);
	@Autowired
	private MasterDao dao;
	@Autowired
	private LogService logService;

	@Override
	public Map<String, Object> getAdmin(String sid) {
		return dao.getOneInfo("admin.getAdmin", sid);
	}

	@Override
	public Map<String, Object> saveAdmin(Map<String, String> params) {
		LOGGER.debug("保存管理员资料，添加/修改：" + params);

		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> check = dao.getOneInfo("admin.checkAdmin", params);// 查重
		if (check != null) {
			if (params.get("email").equals(check.get("email"))) {
				data.put("result", "fail");
				data.put("msg", "管理账号已被使用，请重新输入");
				return data;

			} else if (params.get("mobile").equals(check.get("mobile"))) {
				data.put("result", "fail");
				data.put("msg", "联系手机已被使用，请重新输入");
				return data;
			}
		}
		String agentUrl = params.get("agent_url");
		if (StringUtils.isNotEmpty(agentUrl)) {
			agentUrl = agentUrl.contains("http://") || agentUrl.contains("https://")? agentUrl : "http://" + agentUrl;
			params.put("agent_url", agentUrl);
		}

		String password = params.get("password");
		if (StringUtils.isNotBlank(password)) {
			password = EncryptUtils.encodeMd5(password);
			password = MD5.md5(password);
			params.put("password", password);
		} else {
			params.remove("password");
		}
		String sid = params.get("sid");
		if (StringUtils.isBlank(sid)) {// 添加管理员
			sid = EncryptUtils.generateSid();

			params.put("sid", sid);
			int i = dao.insert("admin.insertAdmin", params);
			if (i > 0) {
				data.put("result", "success");
				data.put("msg", "添加成功");

				dao.insert("admin.insertAdminRole", params);// 添加角色
			} else {
				data.put("result", "fail");
				data.put("msg", "添加失败");
			}
			logService.add(LogType.add, "信息管理-管理员中心-管理员列表：添加管理员", params, data);

		} else {// 修改管理员
			int i = dao.update("admin.updateAdmin", params);
			if (i > 0) {
				data.put("result", "success");
				data.put("msg", "修改成功");

				String roleId = params.get("role_id");
				if (StringUtils.isNotBlank(roleId) && !roleId.equals(params.get("old_role_id"))) {// 修改角色
					dao.update("admin.updateAdminRole", params);
				}
			} else {
				data.put("result", "fail");
				data.put("msg", "管理员不存在，修改失败");
			}
			logService.add(LogType.update, "信息管理-管理员中心-管理员列表：修改管理员", params, data);
		}

		return data;
	}

	@Override
	public Map<String, Object> savePassword(Map<String, String> params) {
		Map<String, Object> data = new HashMap<String, Object>();

		String password = params.get("password");
		password = EncryptUtils.encodeMd5(password);
		password = MD5.md5(password);
		params.put("password", password);

		password = params.get("newPassword");
		password = EncryptUtils.encodeMd5(password);
		password = MD5.md5(password);
		params.put("newPassword", password);

		int i = dao.update("admin.savePassword", params);
		if (i > 0) {
			data.put("result", "success");
			data.put("msg", "修改成功");
		} else {
			data.put("result", "fail");
			data.put("msg", "当前密码错误，修改失败");
		}
		logService.add(LogType.update, "信息管理-管理员中心：修改密码", params, data);
		return data;
	}

	@Override
	public PageContainer query(Map<String, String> params) {
		return dao.getSearchPage("admin.query", "admin.queryCount", params);
	}

	@Override
	public Map<String, Object> updateStatus(String sid, int status) {
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		String msg;
		switch (status) {
		case UserConstant.STATUS_1:
			msg = "恢复";
			break;
		case UserConstant.STATUS_6:
			msg = "删除";
			break;
		default:
			data.put("result", "fail");
			data.put("msg", "不是恢复或删除，操作失败");
			return data;
		}

		params.put("sid", sid);
		params.put("status", status);
		int i = dao.update("admin.updateStatus", params);
		if (i > 0) {
			if (status == UserConstant.STATUS_6) {
				dao.delete("admin.deleteAdminRole", params);
			}

			data.put("result", "success");
			data.put("msg", msg + "成功");
		} else {
			data.put("result", "fail");
			data.put("msg", "管理员不存在，" + msg + "失败");
		}
		logService.add(LogType.delete, "信息管理-管理员中心-管理员列表：修改管理员状态", msg, params, data);
		return data;
	}

	@Override
	public Map<String, Object> sendVerifyCode(String mobile) {
		Map<String, Object> data = new HashMap<String, Object>();
		if (dao.getSearchSize("admin.checkMobile", mobile) > 0) {
			data.put("result", "fail");
			data.put("msg", "联系手机已经被绑定，请重新输入");
			return data;
		}

		String verifyCode = RandomStringUtils.randomNumeric(4);
		boolean result = RestUtils.sendTemplateSMS(SmsTemplateId.verify_code, mobile, verifyCode);
		if (result) {
			data.put("encrypt_verify_code", EncryptUtils.encodeMd5(mobile + verifyCode));
			data.put("result", "success");
			data.put("msg", "发送短信验证码成功");
		} else {
			data.put("result", "fail");
			data.put("msg", "发送短信验证码失败，请联系管理员");
		}
		logService.add(LogType.update, "信息管理-管理员中心-管理员资料-修改：获取验证码", mobile, verifyCode, data);
		return data;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map newOrderSmt(Map<String, String> formData) {
		Integer charge = Integer.parseInt(formData.get("charge"))* ConfigUtils.RATE;
		formData.put("recharge", charge.toString());
		formData.put("createDate", DateUtil.getCurrentDate2Str());
		formData.put("payDate", DateUtil.getCurrentDate2Str());
		formData.put("status", ConfigUtils.CHARGERESULT_1);
		formData.put("payId", StrUtils.getUUID());
		formData.put("accountType",ConfigUtils.account_type_2);
		Integer orderId = dao.getOneInfo("admin.getLargestOrderId", null);//获取最大主键ID，并锁表
		orderId++;
		formData.put("orderId", orderId.toString());
		int count = dao.insert("admin.newOrderSmt", formData);
		if(count == 0){
			return null;
		}
		return formData;
	}

	@Override
	public Map<String, Object> toPay(Map<String, String> formData) {
		formData.put("sid",formData.get("superSid"));
		formData.put("status", ConfigUtils.CHARGERESULT_2);
		formData.put("payDate", DateUtil.getCurrentDate2Str());
		dao.update("admin.updatePayOrder", formData);
		Map<String, Object> order = dao.getOneInfo("admin.getPayOrderById", formData);
		System.out.println("--------------------更新订单状态成功,准备提交到支付网关----------------------");
		System.out.println("参数:"+JsonUtils.toJsonStr(order));
		Map<String, Object> GatewayDto = buildGatewayDto(order);
		System.out.println("--------------------组装支付参数完毕.提交到支付网关----------------------");
		System.out.println("参数:"+JsonUtils.toJsonStr(GatewayDto));
		return GatewayDto;
	}

	private Map<String, Object> buildGatewayDto(Map<String, Object> order) {
		Map<String, Object> gatewayDto = new HashMap<String, Object>();
		String orderId = order.get("orderId").toString();
		String sid = order.get("sid").toString();
		gatewayDto.put("payAmount",String.valueOf(order.get("charge")));
		gatewayDto.put("productDesc", ConfigUtils.PRODUCT_DESC);
		gatewayDto.put("merData", sid + ConfigUtils.SYS_DIVISION + orderId);
		gatewayDto.put("commitUrl", ConfigUtils.PAY_GATEWAY_URL);
    	System.out.println("------------------------组装MerData:"+sid+":"+ConfigUtils.SYS_DIVISION+":"+orderId+"------------------------");
        return gatewayDto;
	}

	@Override
	public Map<String, Object> getByPayId(Map<String, Object> order) {
		return dao.getOneInfo("admin.getPayOrder", order);
	}

	@Override
	public void callBack(Map<String, Object> order, String payAmount, String sid) {
		
		//更新订单状态
		long money = Integer.parseInt(payAmount)/ConfigUtils.payRate;
		System.out.println("订单号:"+order.get("orderId").toString()+"  充值成功！充值金额："+money);
//		AcctBalance b = acctBalanceService.getAcctBalanceBySid(sid);
		Map<String,Object> o = new HashMap<String,Object>();
		o.put("charge", money*ConfigUtils.RATE);
		o.put("status", ConfigUtils.CHARGERESULT_3);
		o.put("orderId", order.get("orderId"));
		o.put("payResultDate",DateUtil.getCurrentDate());
		o.put("sid", sid);
		dao.update("admin.updatePayOrder", o);
//		PaymentOrder o = new PaymentOrder();
//		o.setCharge(money*SysConstant.RATE);
//		o.setStatus(SysConstant.CHARGERESULT_3);
//		o.setOrderId(order.getOrderId());
//		o.setPayResultDate(DateUtil.getCurrentDate());
//		o.setSid(sid);
//		o.setChargeBalance(Long.parseLong(new BigDecimal(b.getBalance()*SysConstant.RATE+"").toPlainString())+money*SysConstant.RATE);
//		payMentOrderDao.update(o);
		System.out.println("-----------------更新本地订单状态成功！--------------------");
		
		//普通账户充值
		Map<String,Object> acctBalance = new HashMap<String,Object>() ;
//		acctBalance = new AcctBalance();
		acctBalance.put("sid", sid);
		acctBalance.put("balance", money*ConfigUtils.RATE);
		dao.update("admin.updateAcctBalance", acctBalance);
//		acctBalance.setSid(sid);
//		acctBalance.setBalance(money*ConfigUtils.RATE);
//		acctBalanceDao.update(acctBalance);
		System.out.println("------------------更新余额成功！-------------------");
	}

	@Override
	public void updatePayMentOrder(Map<String, Object> order) {
		dao.update("admin.updatePayOrder", order);
	}

	@Override
	public int getRemind(String sid) {
		return dao.getOneInfo("admin.getCountBySid", sid);
	}

	@Override
	public void updateRemind(Map<String, Object> remind) {
		dao.update("billdtl.updateRemindBySid", remind);
	}

}
