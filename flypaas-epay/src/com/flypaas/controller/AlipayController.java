package com.flypaas.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipaySubmit;
import com.flypaas.entity.GatewayVo;

@Controller
@RequestMapping("/alipay")
public class AlipayController {
	
	@RequestMapping("/toAlipay")
	public void toAlipay(HttpServletRequest request,HttpServletResponse response,GatewayVo gateway) throws IOException{
		////////////////////////////////////请求参数//////////////////////////////////////
		PrintWriter out = response.getWriter();
		//商户订单号，商户网站订单系统中唯一订单号，必填
//		String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		String out_trade_no = new String(gateway.getMerData().getBytes("ISO-8859-1"),"UTF-8");
		//订单名称，必填
//		String subject = new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"),"UTF-8");
		String subject = new String(gateway.getProductDesc().getBytes("ISO-8859-1"),"UTF-8");
		//付款金额，必填
//		String total_fee = new String(request.getParameter("WIDtotal_fee").getBytes("ISO-8859-1"),"UTF-8");
		String total_fee = new String(gateway.getPayAmount().getBytes("ISO-8859-1"),"UTF-8");
		//商品描述，可空
//		String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"),"UTF-8");
		String body = new String(gateway.getProductDesc().getBytes("ISO-8859-1"),"UTF-8");
		//////////////////////////////////////////////////////////////////////////////////
		
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", AlipayConfig.service);
		sParaTemp.put("partner", AlipayConfig.partner);
		sParaTemp.put("seller_id", AlipayConfig.seller_id);
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", AlipayConfig.payment_type);
		sParaTemp.put("notify_url", AlipayConfig.notify_url);
		sParaTemp.put("return_url", AlipayConfig.return_url);
		sParaTemp.put("anti_phishing_key", AlipayConfig.anti_phishing_key);
		sParaTemp.put("exter_invoke_ip", AlipayConfig.exter_invoke_ip);
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("subject", subject);
		sParaTemp.put("total_fee", total_fee);
		sParaTemp.put("body", body);
		//其他业务参数根据在线开发文档，添加参数.文档地址:https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.O9yorI&treeId=62&articleId=103740&docType=1
		//如sParaTemp.put("参数名","参数值");
		
		//建立请求
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","确认");
		out.println(sHtmlText);
		
	}
}
