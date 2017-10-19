package com.flypaas.admin.action.data;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipayNotify;
import com.alipay.util.AlipaySubmit;
import com.flypaas.admin.action.BaseAction;
import com.flypaas.admin.service.data.AdminService;
import com.flypaas.admin.util.ConfigUtils;
import com.flypaas.admin.util.web.AuthorityUtils;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 信息管理-管理员中心
 * 
 * @author xiejiaan
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "view", location = "/WEB-INF/content/data/admin/view.jsp"),
		@Result(name = "editForSelf", location = "/WEB-INF/content/data/admin/editForSelf.jsp"),
		@Result(name = "editPassword", location = "/WEB-INF/content/data/admin/editPassword.jsp"),
		@Result(name = "payReturnUrl", location = "/WEB-INF/content/data/admin/return_url.jsp"),
		@Result(name = "query", location = "/WEB-INF/content/data/admin/query.jsp"),
		@Result(name = "charge", location = "/WEB-INF/content/data/admin/recharge.jsp"),
		@Result(name = "changeLogo", location = "/WEB-INF/content/data/admin/changeLogo.jsp"),
		@Result(name = "orderView", location = "/WEB-INF/content/data/admin/orderView.jsp"),
		@Result(name="toPaySuc",location = "/WEB-INF/content/data/admin/toGateWay.jsp"),
		@Result(name = "edit", location = "/WEB-INF/content/data/admin/edit.jsp") })
public class AdminAction extends BaseAction {
	private static final long serialVersionUID = -1234214414349439397L;
	@Autowired
	private AdminService adminService;
	private File logoImg;
	private String file_txt;

	/**
	 * 管理员资料
	 * 
	 * @return
	 */
	@Action("/admin/view")
	public String view() {
		data = adminService.getAdmin(AuthorityUtils.getLoginSid());
		return "view";
	}

	/**
	 * 修改管理员自己的资料页面
	 * 
	 * @return
	 */
	@Action("/admin/editForSelf")
	public String editForSelf() {
		data = adminService.getAdmin(AuthorityUtils.getLoginSid());
		return "editForSelf";
	}
	
	/**
	 * 管理员recharge页面
	 * 
	 * @return
	 */
	@Action("/admin/charge")
	public String charge() {
		data = adminService.getAdmin(AuthorityUtils.getLoginSid());
		return "charge";
	}
	
	/**
	 * 管理员recharge页面
	 * 
	 * @return
	 */
	@Action("/admin/changeLogo")
	public String changeLogo() {
		return "changeLogo";
	}
	
	/**
	 * 保存
	 * 
	 * @return
	 */
	@Action("/admin/uploadLogo")
	public String uploadLogo() {
		String path = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/");
		String fileName = null;
		if(null != logoImg){
			String sid  = StrutsUtils.getParameterTrim("sid");
			path = ConfigUtils.resource_base_path +"/"+ sid + "/";
			fileName  = sid +
					file_txt.substring(file_txt.lastIndexOf("."));	
		}
		/*data = invoiceAuditService.save(logoImg,path,fileName,StrutsUtils.getFormData());*/
		data = adminService.getAdmin(AuthorityUtils.getLoginSid());
		return "view";
	}
	
	/**
	 * 新增订单
	 * 
	 * @return
	 */
	@Action("/admin/newOrderSmt")
	public String newOrderSmt() {
		data = adminService.newOrderSmt(StrutsUtils.getFormData());
		return "orderView";
	}
	
	/*----------------------------------------支付----------------------------------*/
	@Action("/admin/toPay")
	public String toPay(){
		data = adminService.toPay(StrutsUtils.getFormData());
		return "toPaySuc";
	}
	
	@Action("/admin/toAlipay")
	public void toAlipay() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();   
		HttpServletResponse response = ServletActionContext.getResponse();   
		////////////////////////////////////请求参数//////////////////////////////////////
		PrintWriter out = response.getWriter();
		
		// 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
//		String partner = new String(AlipayConfig.partner);
		// 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
//		String seller_id = new String(AlipayConfig.seller_id);
		//商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = new String(request.getParameter("merData").getBytes("ISO-8859-1"),"UTF-8");
		//订单名称，必填
		String subject = new String(request.getParameter("productDesc").getBytes("ISO-8859-1"),"UTF-8");
		//付款金额，必填
		String total_fee = new String(request.getParameter("payAmount").getBytes("ISO-8859-1"),"UTF-8");
		//商品描述，可空
		String body = new String(request.getParameter("productDesc").getBytes("ISO-8859-1"),"UTF-8");
		
		
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", AlipayConfig.service);
		sParaTemp.put("partner", AlipayConfig.partner);
		sParaTemp.put("seller_id", AlipayConfig.seller_id);
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", AlipayConfig.payment_type);
		sParaTemp.put("notify_url", ConfigUtils.host + AlipayConfig.notify_url);
		sParaTemp.put("return_url", ConfigUtils.host + AlipayConfig.return_url);
//		sParaTemp.put("notify_url", AlipayConfig.notify_url);
//		sParaTemp.put("return_url", AlipayConfig.return_url);
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
	
	@SuppressWarnings("rawtypes")
	@Action("/admin/aliPayResult")
	public void aliPayResult() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();   
		HttpServletResponse response = ServletActionContext.getResponse();  
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
//				String [] desArray = desStr.split(SysConstant.SYS_DIVISION);
		String [] desArray = out_trade_no.split(ConfigUtils.SYS_DIVISION);
		String sid = desArray[0];
		String orderId = desArray[1];
		Map<String,Object> order = new HashMap<String,Object>();

		if(AlipayNotify.verify(params)){//验证成功
			//////////////////////////////////////////////////////////////////////////////////////////
			//请在这里加上商户的业务逻辑程序代码
			System.out.println("------------------开始充值-------------------");
			System.out.println("****out_trade_no："+out_trade_no);
			System.out.println("****merDate："+trade_no);
			System.out.println("****trade_status："+trade_status);
			
			
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
				boolean orderStatus = true;
//				PaymentOrder order = new PaymentOrder();
				order.put("orderId", orderId);
				order.put("sid", sid);
				order = adminService.getByPayId(order);
//				order.setOrderId(Long.parseLong(orderId));
//				order.setSid(sid);
//				order = payMentOrderService.getByPayId(order);
				
				if(order == null || order.isEmpty()){
					System.out.println("------------------充值失败!订单不存在-------------------");
					orderStatus = false;
				}
				if(orderStatus == true){
					long money = Long.parseLong(total_fee) * ConfigUtils.RATE;//SysConstant.payRate;
					long charge = (Long) order.get("charge");
					if(charge != money){
						System.out.println("------------------充值失败!订单金额错误-------------------");
						orderStatus = false;
					}
					//已经充值的响应比较慢的直接返回success，
					if(order.get("status").toString().equals(ConfigUtils.CHARGERESULT_3)){
						System.out.println("------------------充值已经成功!无需再次更新-------------------");
						orderStatus = false;
					}
				}
				
				if(!seller_id.equals(AlipayConfig.seller_id)){
					System.out.println("------------------充值失败!收款支付宝账号错误-------------------");
					orderStatus = false;
				}
				
				if(orderStatus == true){
					adminService.callBack(order,String.valueOf(Long.parseLong(total_fee) * ConfigUtils.payRate), sid);
					System.out.println("------------------充值成功！-------------------");
					//更新余额提醒
					/*int count = adminService.getRemind(sid);
//					int count = remindService.get(sid);
					if(count>0){
						Map<String,Object> remind = new HashMap<String,Object>();
						remind.put("sid", sid);
						remind.put("status", ConfigUtils.REMIND_STATUS_0);
						adminService.updateRemind(remind);
//						Remind remind = new Remind();
//						remind.setSid(sid);
//						remind.setStatus(UserConstant.REMIND_STATUS_0);
//						remindService.update(remind);
						System.out.println("------------------更新余额提醒成功！-------------------");
					}*/
				}
					
				//注意：
				//付款完成后，支付宝系统发送该交易状态通知
			}

			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
				
			out.print("success");	//请不要修改或删除

			//////////////////////////////////////////////////////////////////////////////////////////
		}else{//验证失败
			System.out.println("------------------充值失败！-------------------");
			order.put("status", ConfigUtils.CHARGERESULT_4);
			order.put("orderId", orderId);
			adminService.updatePayMentOrder(order);
//			order.setStatus(ConfigUtils.CHARGERESULT_4);
//			order.setOrderId(Long.parseLong(orderId));
//			payMentOrderService.update(order);
			System.out.println("------------------订单失败，本地订单状态修改成功！-------------------");
			out.print("fail");
		}
	}
	
	/**
	 * 支付返回页面
	 * 
	 * @return
	 */
	@Action("/admin/payReturn")
	public String payReturn() {
		return "payReturnUrl";
	}
	
	/**
	 * 保存管理员资料，包括添加、修改
	 * 
	 * @return
	 */
	@Action("/admin/save")
	public void save() {
		data = adminService.saveAdmin(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}

	/**
	 * 修改密码页面
	 * 
	 * @return
	 */
	@Action("/admin/editPassword")
	public String editPassword() {
		StrutsUtils.setAttribute("sid", AuthorityUtils.getLoginSid());
		return "editPassword";
	}

	/**
	 * 保存密码
	 * 
	 * @return
	 */
	@Action("/admin/savePassword")
	public void savePassword() {
		data = adminService.savePassword(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}

	/**
	 * 管理员列表
	 * 
	 * @return
	 */
	@Action("/admin/query")
	public String query() {
		page = adminService.query(StrutsUtils.getFormData());
		return "query";
	}

	/**
	 * 添加管理员
	 * 
	 * @return
	 */
	@Action("/admin/add")
	public String add() {
		return "edit";
	}

	/**
	 * 修改管理员
	 * 
	 * @return
	 */
	@Action("/admin/edit")
	public String edit() {
		String sid = StrutsUtils.getParameterTrim("sid");
		if (StringUtils.isNotBlank(sid)) {
			data = adminService.getAdmin(sid);
		}
		return "edit";
	}

	/**
	 * 修改状态：恢复、删除
	 * 
	 * @return
	 */
	@Action("/admin/updateStatus")
	public void updateStatus() {
		String sid = StrutsUtils.getParameterTrim("sid");
		String status = StrutsUtils.getParameterTrim("status");
		if (StringUtils.isNotBlank(sid) && NumberUtils.isDigits(status)) {
			data = adminService.updateStatus(sid, Integer.parseInt(status));
			StrutsUtils.renderJson(data);
		}
	}

	/**
	 * 发送短信验证码
	 * 
	 * @return
	 */
	@Action("/admin/sendVerifyCode")
	public void sendVerifyCode() {
		String mobile = StrutsUtils.getParameterTrim("mobile");
		if (NumberUtils.isDigits(mobile) && mobile.matches("1\\d{10}")) {
			data = adminService.sendVerifyCode(mobile);
			StrutsUtils.renderJson(data);
		}
	}

	public File getLogoImg() {
		return logoImg;
	}

	public void setLogoImg(File logoImg) {
		this.logoImg = logoImg;
	}

	public String getFile_txt() {
		return file_txt;
	}

	public void setFile_txt(String file_txt) {
		this.file_txt = file_txt;
	}
}
