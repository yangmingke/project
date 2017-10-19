<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>价格_快传融合通讯开放平台</title>
<%@include file="/front/resource.jsp" %>
</head>

<body>
<!--公共头部header bof-->
<%@include file="/front/head.jsp" %>
<!--公共头部header eof--> 

<!--中间部分middle bof-->
<div class="middle mid_box">
  <div class="banner price_banner">&nbsp;</div>
  <div class="price_box">
  	<div class="price_tbg">&nbsp;</div>
	<div class="price_wrapper">
       <table cellpadding="0" cellspacing="0" border="0" width="100%">
       	<tbody>
       	    <tr class="tit"><th rowspan="2">基础通讯服务</th><th>基础套餐</th><th rowspan="2" style="border-right:none;">更多</th></tr>
       	    <tr><th style="border-top:1px #E3F2F4 solid; text-align:center;">无最低消费</th></tr>
       		<tr><td>点对点在线视频</td><td>免费</td><td rowspan="22" class="link"><a href="<%=path%>/about/cooperation">联系商务</a></td></tr>
       		<tr><td>即时消息（IM）</td><td>免费</td></tr>
       		<tr><td>互联网语音通话</td><td>免费</td></tr>
       		<tr><td>国内（运营商）单向呼叫</td><td>0.0300 元/分钟</td></tr>
       		<tr><td>国内（运营商）双向外呼</td><td>0.0600 元/分钟</td></tr>
       		<tr><td>国际通话（费率）</td><td><a href="<%=path%>/user/feeRate">查询费率</a></td></tr>
       		<tr><td>&nbsp;</td><td>&nbsp;</td></tr>
       		<tr class="tit"><td>增值服务</td><td class="mark">语音验证码显号服务需创建正式应用时设置 =></td></tr>
       		<tr><td>短信验证码</td><td>0.0500 元/条</td></tr>
       		<tr><td>语音验证码</td><td>0.0500 元/条</td></tr>
          <tr><td>智能验证</td><td>0.0300 元/条</td></tr>
          <tr><td>语音通知</td><td>0.0500 元/分钟</td></tr>
          <tr><td>通话录音</td><td>0.0100 元/分钟</td></tr>
          <tr class="tit"><td>会议类服务</td><td>&nbsp;</td></tr>
          <tr><td>语音会议（IP）</td><td>免费</td></tr>
          <tr><td>语音会议（落地）</td><td>0.0960 元/分·方</td></tr>
       		<!--<tr><td>语音验证码（400显号）</td><td>0.0500 元/条</td></tr>
       		<tr class="tit"><td>官方显号服务</td><td class="mark">支持官方400、固话号码显示服务 =></td></tr>
       		<tr><td>国内（运营商）单向呼叫</td><td>0.0500 元/分钟</td></tr>
       		<tr><td>国内（运营商）双向外呼</td><td><span class="mark">主叫或被叫显官方号码</span>0.0800 元/分钟</td></tr>
       		<tr><td>国内（运营商）双向外呼</td><td><span class="mark">主叫和被叫显官方号码</span>0.1000 元/分钟</td></tr>
       		<!--<tr><td>&nbsp;</td><td>&nbsp;</td></tr>
       		<tr><td class="tit">其他增值服务</td><td>&nbsp;</td></tr>
       		<tr><td rowspan="2">client许可费</td><td>免费</td></tr>
       		<tr style="background:#f0f2f2; text-align:center;"><td class="client">client许可费 账号10万以下免费，10万以上面议</td></tr>-->
       	</tbody>
       </table>
  	</div>
<div class="price_bbg">&nbsp;</div>
  </div>
  <div class="clear"></div>
</div>
<script type="text/javascript">
	$(function(){
		$(".price_wrapper table td:last-child").css("border-right","none");
		$(".price_wrapper table td:first-child").css({"border-left":"5px solid #96d2d3","border-right":"1px #f0f2f2 solid"});
		$(".price_wrapper table td:nth-child(2),.price_wrapper table th:nth-child(2)").css({"text-align":"center","border-right":"1px #f0f2f2 solid"});
		$(".price_wrapper table td.link").css("border-right","5px solid #96d2d3");
		$(".price_wrapper table td.client").css({"text-align":"center","border-left":"1px #f0f2f2 solid"});
	})
</script>
<!--中间部分middle eof--> 

 

<!--公共版权copyright bof-->
<%@include file="/front/foot_sk.jsp"%><%@include file="/front/foot.jsp" %>
<!--公共版权copyright eof-->
<script type="text/javascript">
$(function(){
	$("#h_menu_4").css("color","#05c040");
})
</script>
</body>
</html>
