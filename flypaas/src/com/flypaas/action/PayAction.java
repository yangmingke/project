package com.flypaas.action;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipayNotify;
import com.alipay.util.AlipaySubmit;
import com.flypaas.constant.SysConstant;
import com.flypaas.constant.UserConstant;
import com.flypaas.entity.AcctBalance;
import com.flypaas.entity.AcctPackageRel;
import com.flypaas.entity.Application;
import com.flypaas.entity.Package;
import com.flypaas.entity.Params;
import com.flypaas.entity.PaymentOrder;
import com.flypaas.entity.Remind;
import com.flypaas.entity.TbFlypaasUser;
import com.flypaas.entity.vo.GatewayVo;
import com.flypaas.manager.OrderManager;
import com.flypaas.utils.DateUtil;
import com.flypaas.utils.Des3Utils;
import com.flypaas.utils.JsonUtil;
import com.flypaas.utils.MD5Util;
import com.flypaas.utils.StrUtil;

@Controller
@Scope("prototype")
@Results({
	@Result(name="newOrderSuc",location="/page/pay/neworder.jsp"),
	@Result(name="orderSmtSuc",type="redirectAction",location="orderInfo",params={"orderId","${orderId}"}),
	@Result(name="orderInfoSuc",location="/page/pay/orderinfo.jsp"),
	@Result(name="extToPaySuc",type="redirectAction",location="toPay",params={"orderId","${orderId}"}),
	@Result(name="toPaySuc",location="/page/pay/toGateWay.jsp"),
	@Result(name="extRechargeView",location="/page/pay/extRecharge.jsp"),
	@Result(name="updateOrderIdSuc",type="redirectAction",location="orderInfo",params={"orderId","${orderId}"})
})
public class PayAction extends BaseAction {
	private Package pck ;
	private AcctBalance acctBalance;
	private PaymentOrder order;
	
	private String orderId;
	private GatewayVo gatewayVo;
	private String resultMsg;
	private String chargeType;
	private String chargeStr;
	private String accountType;
	private String couponNum;
	private String userName;

	private Logger logger = LoggerFactory.getLogger(PayAction.class);
	/*----------------------------------------跳转到新增订单页----------------------------------*/
	@Action("/pay/newOrder")
	public String newOrder(){
		String sid = getSessionUser().getSid();
		if(!StrUtil.isEmpty(accountType)){
			chargeStr = SysConstant.SECURITY_MONEY+"";
		}
		//开发者当前套餐信息
		AcctPackageRel rel = packageRelService.getAcctPackageRel(sid);
		if(rel!=null){
			pck = packageService.getPackage(rel.getPackageId());
		}
		//用户余额
		acctBalance = acctBalanceService.getAcctBalanceBySid(sid);
		return "newOrderSuc" ;
	}
	/*----------------------------------------新增订单----------------------------------*/
	@Action("/pay/newOrderSmt")
	public String newOrderSmt(){
		long charge = 0 ;
		try {
			charge = Long.parseLong(chargeStr);
		} catch (Exception e) {
			logger.error("输入金额字符串不合法");
			logger.error(e.getMessage());
			StrUtil.writeMsg(response, "充值金额不合法，请重新填写",null);
			return null ;
		}
		accountType = Des3Utils.decodeDes3(accountType); 
		if(!StrUtil.isEmpty(accountType)&& accountType.equals("1")){
			if(charge<SysConstant.SECURITY_MONEY){
				StrUtil.writeMsg(response, "充值金额不合法，请重新填写",null);
				return null ;
			}
		}
		if(StrUtil.isEmpty(accountType)){
			accountType="0";
		}
		
		chargeType = Des3Utils.decodeDes3(chargeType);
		List<Params> types= paramsService.getParams(SysConstant.RECHARGE_TYPE);
		boolean boo = false ;
		for(Params p : types){
			if(p.getParamKey().equals(chargeType)){
				boo = true ;
				break;
			}
		}
		if(!boo){
			logger.error("充值类型不合法");
			StrUtil.writeMsg(response, "充值类型不合法，请重新填写",null);
			return null ;
		}
		
		if(!StrUtil.isEmpty(accountType)){
			List<String> accountTypes = Arrays.asList(SysConstant.ACCOUNT_TYPE);
			if(!accountTypes.contains(accountType)){
				StrUtil.writeMsg(response, "账户类型非法，请重新填写",null);
				return null ;
			}
					
		}
		if(!StrUtil.checkMoney(""+charge)){
			logger.error("充值金额不合法");
			StrUtil.writeMsg(response, "充值金额不合法，请重新填写",null);
			return null ;
		}
		String sid = getSessionUser().getSid();
		
		if(charge>SysConstant.MAX_CHARGE||charge<0){
			StrUtil.writeMsg(response, "充值金额超出范围，请重新填写",null);
			return null ;
		}
		String extAppSid = session.getAttribute("extAppSid")==null?null:session.getAttribute("extAppSid").toString();
		order = payMentOrderService.addPaymentOrder(charge,sid,accountType,chargeType,extAppSid);
		orderId = Des3Utils.encodeDes3(""+order.getOrderId());
		logger.info("---------------------------新增订单成功------------------------");
		logger.info("订单内容"+order);
		
		if(!StrUtil.isEmpty(extAppSid)){
			return "extToPaySuc";
		}
		return "orderSmtSuc";
	}
	/*----------------------------------------订单详情----------------------------------*/
	@Action("/pay/orderInfo")
	public String orderInfo(){
		orderId = Des3Utils.decodeDes3(orderId);
		if(StrUtil.isEmpty(orderId)){
			StrUtil.writeMsg(response, "订单不存在",null);
			return null ;
		}
		userName = getSessionUser().getEmail();
		order = payMentOrderService.get(Long.parseLong(orderId));
		return "orderInfoSuc";
	}
	/*----------------------------------------更新订单号重新支付----------------------------------*/
	@Action("/pay/updateOrder")
	public String updateOrderId(){
		String orderId1 = Des3Utils.decodeDes3(orderId);
		if(StrUtil.isEmpty(orderId1)){
			StrUtil.writeMsg(response, "订单不存在",null);
			return null;
		}
		order = payMentOrderService.get(Long.parseLong(orderId1));
		if(SysConstant.CHARGERESULT_3.equals(order.getStatus())){
			StrUtil.writeMsg(response, "该订单已经支付成功",null);
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("payId",StrUtil.getUUID());
		map.put("orderId", orderId1);
		map.put("status", SysConstant.CHARGERESULT_1);
		map.put("sid", getSessionUser().getSid());
		payMentOrderService.updateOrderId(map);
		toPay();
		return "toPaySuc" ;
	}
	/*----------------------------------------支付----------------------------------*/
	@Action("/pay/toPay")
	public String toPay(){
		PaymentOrder o = new PaymentOrder();
		orderId = Des3Utils.decodeDes3(orderId);
		if(StrUtil.isEmpty(orderId)){
			logger.info("--------------------订单号码不存在----------------------");
			StrUtil.writeMsg(response, "订单号码不存在", null);
			return null ;
		}
		o.setOrderId(Long.parseLong(orderId));
		o.setStatus(SysConstant.CHARGERESULT_2);
		o.setPayDate(DateUtil.getCurrentDate());
		o.setSid(getSessionUser().getSid());
		//更新状态
		payMentOrderService.update(o);
		logger.info("--------------------更新订单状态成功,准备提交到支付网关----------------------");
		logger.info("参数:"+JsonUtil.toJsonStr(o));
		
		order = payMentOrderService.get(Long.parseLong(orderId));
		OrderManager.instance(request);
		gatewayVo = OrderManager.buildGatewayDto(order);
		logger.info("--------------------组装支付参数完毕.提交到支付网关----------------------");
		logger.info("参数:"+JsonUtil.toJsonStr(gatewayVo));
		//OrderManager.check(gatewayVo);
		return "toPaySuc" ;
	}
	
	/*-----------------------------------------callback--------------------------------*/
	@Action("/pay/payResult")
	public void callBack(){
		logger.info("回调的url:"+request.getQueryString());
		String result = (String) request.getParameter("result");
		String payId = (String) request.getParameter("orderId");
		String payAmount = (String) request.getParameter("payAmount");
		String desStr = (String) request.getParameter("merData");
		String slt = StrUtil.checkStrArray(result,orderId,payAmount,desStr);
		logger.info("------------------开始充值-------------------");
		logger.info("****result："+result);
		logger.info("****payId："+payId);
		logger.info("****payAmount："+payAmount);
		logger.info("****merDate："+desStr);
		
		if(slt!=null && slt.equals("false")){
			//失败
			logger.info("------------------充值失败!参数非法-------------------");
			return ;
		}
		
		desStr = Des3Utils.decodeDes3(desStr);
		if(StrUtil.isEmpty(desStr)){
			logger.info("------------------充值失败!关键加密信息非法-------------------");
			return ;
		}
		
		
		String [] desArray = desStr.split(SysConstant.SYS_DIVISION);
		String sid = desArray[0];
		orderId = desArray[1];
		PaymentOrder order = new PaymentOrder();
		order.setSid(sid);
		order.setOrderId(Long.parseLong(orderId));
		order = payMentOrderService.getByPayId(order);
		if(StrUtil.isEmpty(order)){
			logger.info("------------------充值失败!订单不存在-------------------");
			return ;
		}
		
		//已经充值的响应比较慢的直接返回success，
		if(order.getStatus().equals(SysConstant.CHARGERESULT_3)){
			logger.info("------------------充值已经成功!无需再次更新-------------------");
			return;
		}
		
		if(result!=null && result.equals(SysConstant.SUCCESS)){
			payMentOrderService.callBack(order, payAmount, sid);
			logger.info("------------------充值成功！-------------------");
			//更新余额提醒
			int count = remindService.get(sid);
			if(count>0){
				Remind remind = new Remind();
				remind.setSid(sid);
				remind.setStatus(UserConstant.REMIND_STATUS_0);
				remindService.update(remind);
				logger.info("------------------更新余额提醒成功！-------------------");
			}
			resultMsg = SysConstant.SUCCESS;
			printMsg(resultMsg);
		}else{
			//失败
			logger.info("------------------充值失败！-------------------");
			order.setStatus(SysConstant.CHARGERESULT_4);
			order.setOrderId(Long.parseLong(orderId));
			payMentOrderService.update(order);
			logger.info("------------------订单失败，本地订单状态修改成功！-------------------");
		}
	}
	
	@Action("/alipay/toAlipay")
	public void toAlipay() throws Exception{
		////////////////////////////////////请求参数//////////////////////////////////////
		PrintWriter out = response.getWriter();
		
		// 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
		String partner = new String(AlipayConfig.partner);
		// 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
		String seller_id = new String(AlipayConfig.seller_id);
		//商户订单号，商户网站订单系统中唯一订单号，必填
		//String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		String out_trade_no = new String(gatewayVo.getMerData().getBytes("ISO-8859-1"),"UTF-8");
		//订单名称，必填
		//String subject = new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"),"UTF-8");
		String subject = new String(gatewayVo.getProductDesc().getBytes("ISO-8859-1"),"UTF-8");
		//付款金额，必填
		//String total_fee = new String(request.getParameter("WIDtotal_fee").getBytes("ISO-8859-1"),"UTF-8");
		String total_fee = new String(gatewayVo.getPayAmount().getBytes("ISO-8859-1"),"UTF-8");
		//商品描述，可空
		//String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"),"UTF-8");
		String body = new String(gatewayVo.getProductDesc().getBytes("ISO-8859-1"),"UTF-8");
		//////////////////////////////////////////////////////////////////////////////////
		
		
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", AlipayConfig.service);
		sParaTemp.put("partner", partner);
		sParaTemp.put("seller_id", seller_id);
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", AlipayConfig.payment_type);
		sParaTemp.put("notify_url", SysConstant.LOCAL_HOST + AlipayConfig.notify_url);
		sParaTemp.put("return_url", SysConstant.LOCAL_HOST + AlipayConfig.return_url);
		sParaTemp.put("anti_phishing_key", AlipayConfig.anti_phishing_key);
		sParaTemp.put("exter_invoke_ip", AlipayConfig.exter_invoke_ip);
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("subject", subject);
		sParaTemp.put("total_fee", total_fee);
		sParaTemp.put("body", body);
		//其他业务参数根据在线开发文档，添加参数.文档地址:https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.O9yorI&treeId=62&articleId=103740&docType=1
		//如sParaTemp.put("参数名","参数值");
		
		//建立请求
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"post","确认");
		System.out.println(sHtmlText);
		out.println(sHtmlText);
	}
	
	@Action("/pay/aliPayResult")
	public void aliPayResult() throws IOException{
		PrintWriter out = response.getWriter();
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//支付宝交易号
		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

		//交易状态
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
		
		//交易金额
		String total_fee = new String(request.getParameter("total_fee").getBytes("ISO-8859-1"),"UTF-8");
		total_fee = total_fee.substring(0,total_fee.length()-3);
		
		//卖家号
		String seller_id = new String(request.getParameter("seller_id").getBytes("ISO-8859-1"),"UTF-8");
		

		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

		if(AlipayNotify.verify(params)){//验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
			//请在这里加上商户的业务逻辑程序代码
			logger.info("------------------开始充值-------------------");
			logger.info("****out_trade_no："+out_trade_no);
			logger.info("****merDate："+trade_no);
			logger.info("****trade_status："+trade_status);
			
			
			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			
			if(trade_status.equals("TRADE_FINISHED")){
				//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
					//如果有做过处理，不执行商户的业务程序
					
				//注意：
				//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
			} else if (trade_status.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
					//如果有做过处理，不执行商户的业务程序
				
//				String desStr = Des3Utils.decodeDes3(out_trade_no);//out_trade_no长度不固定，当超过一定长度，支付宝不支持
//				if(StrUtil.isEmpty(desStr)){
//					logger.info("------------------充值失败!关键加密信息非法-------------------");
//					return ;
//				}
//				String [] desArray = desStr.split(SysConstant.SYS_DIVISION);
				String [] desArray = out_trade_no.split(SysConstant.SYS_DIVISION);
				String sid = desArray[0];
				orderId = desArray[1];
				boolean orderStatus = true;
				PaymentOrder order = new PaymentOrder();
				order.setOrderId(Long.parseLong(orderId));
				order.setSid(sid);
				order = payMentOrderService.getByPayId(order);
				
				if(StrUtil.isEmpty(order)){
					logger.info("------------------充值失败!订单不存在-------------------");
					orderStatus = false;
				}
				if(orderStatus == true){
					Long money = Long.parseLong(total_fee) * SysConstant.RATE;//SysConstant.payRate;
					if(order.getCharge() != money){
						logger.info("------------------充值失败!订单金额错误-------------------");
						orderStatus = false;
					}
					//已经充值的响应比较慢的直接返回success，
					if(order.getStatus().equals(SysConstant.CHARGERESULT_3)){
						logger.info("------------------充值已经成功!无需再次更新-------------------");
						orderStatus = false;
					}
				}
				
				if(!seller_id.equals(AlipayConfig.seller_id)){
					logger.info("------------------充值失败!收款支付宝账号错误-------------------");
					orderStatus = false;
				}
				
				if(orderStatus == true){
					payMentOrderService.callBack(order,String.valueOf(Long.parseLong(total_fee) * SysConstant.payRate), sid);
					logger.info("------------------充值成功！-------------------");
					//更新余额提醒
					int count = remindService.get(sid);
					if(count>0){
						Remind remind = new Remind();
						remind.setSid(sid);
						remind.setStatus(UserConstant.REMIND_STATUS_0);
						remindService.update(remind);
						logger.info("------------------更新余额提醒成功！-------------------");
					}
				}
					
				//注意：
				//付款完成后，支付宝系统发送该交易状态通知
			}

			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
				
			out.print("success");	//请不要修改或删除

			//////////////////////////////////////////////////////////////////////////////////////////
		}else{//验证失败
			logger.info("------------------充值失败！-------------------");
			order.setStatus(SysConstant.CHARGERESULT_4);
			order.setOrderId(Long.parseLong(orderId));
			payMentOrderService.update(order);
			logger.info("------------------订单失败，本地订单状态修改成功！-------------------");
			out.print("fail");
		}
	}
	
	@Action("/pay/extrecharge")
	public String extrecharge(){
		Object appSid = request.getParameter("appSid");
		Object sid = request.getParameter("sid");
		Object token = request.getParameter("token");
		if(StrUtil.isEmpty(appSid)||StrUtil.isEmpty(sid)||StrUtil.isEmpty(token)){
			StrUtil.writeMsg(response, "参数异常", null);
			return null;
		}
		TbFlypaasUser user = userService.findUserById(sid.toString());
		if(StrUtil.isEmpty(user)){
			StrUtil.writeMsg(response, "用户不存在", null);
			return null;
		}
		Application app = appService.getAppById(appSid.toString());
		if(StrUtil.isEmpty(app)){
			StrUtil.writeMsg(response, "应用不存在", null);
			return null;
		}
		if(!MD5Util.GetMD5Code(user.getToken()).equals(token.toString())){
			StrUtil.writeMsg(response, "令牌错误", null);
			return null;
		}
		//登陆
		Object userObj = session.getAttribute("user");
		if(StrUtil.isEmpty(userObj)){
			recordSession(user);
			session.setAttribute("extAppSid", appSid);
			session.setAttribute("extToken", token);
		}
		return "extRechargeView";
	}
	/*---------------------------------get set--------------------------------*/
	public Package getPck() {
		return pck;
	}

	public void setPck(Package pck) {
		this.pck = pck;
	}

	public AcctBalance getAcctBalance() {
		return acctBalance;
	}

	public void setAcctBalance(AcctBalance acctBalance) {
		this.acctBalance = acctBalance;
	}

	public PaymentOrder getOrder() {
		return order;
	}

	public void setOrder(PaymentOrder order) {
		this.order = order;
	}
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public GatewayVo getGatewayVo() {
		return gatewayVo;
	}
	public void setGatewayVo(GatewayVo gatewayVo) {
		this.gatewayVo = gatewayVo;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	public String getChargeType() {
		return chargeType;
	}
	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}
	public String getChargeStr() {
		return chargeStr;
	}
	public void setChargeStr(String chargeStr) {
		this.chargeStr = chargeStr;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getCouponNum() {
		return couponNum;
	}
	public void setCouponNum(String couponNum) {
		this.couponNum = couponNum;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
