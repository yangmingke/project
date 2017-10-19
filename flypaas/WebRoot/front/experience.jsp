<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="description" content="快传融合通讯产品体验提供互联网语音，多媒体信息，语音验证码，双向回拨，营销外呼等功能的免费体验" />
<title>快传融合通讯产品体验_快传融合通讯开放平台</title>
<%@include file="/front/resource.jsp" %>
</head>

<body>
<!--公共头部header bof-->
<%@include file="/front/head.jsp" %>
<!--公共头部header eof--> 

<!--中间部分middle bof-->
<div class="middle mid_box">
  <div class="banner experience_banner">&nbsp;</div>
  <div class="display_box">
  	<div class="display_tit">
  		<h1><span class="doc">web体验</span></h1>
  	</div>
  	<div class="display_ctn web_experience">
  		<!--<dl>
  			<dt><img src="front/images/voice.png" /></dt>
  			<dd><p>提供融合电信网络和IP网络的多种通话能力，实现高质量语音对讲、互联网语音、P2P语音等语音应用。<br />1.智能选择通信电路，保证通话质量；<br />2.自主研发软交换系统，保证性能与语音通话质量；<br />3.接入三大运营商落地网关，实现互联网IP呼叫与传统电信网络的呼叫交换。</p><p><a href="#">查看文档</a></p></dd>
  		</dl>-->
  		<h2>通过Web演示,轻松体验平台功能</h2>
  		<p>双向回拨、语音验证码、落地电话等功能在线体验，满足您轻易使用通讯能力的愿望<br /><span>（部分功能近期将陆续发放）</span></p>
  		<ul>
  			<li>
  				<a href="<%=path%>/freetrial/VoiceVerificationCode" class="unit_img"><img src="<%=path %>/front/images/vcode_icon.png" /></a><a href="<%=path%>/freetrial/VoiceVerificationCode" class="unit_link">语音验证码</a>
  			</li>
  			<li>
  				<a href="<%=path%>/freetrial/cloudNotify" class="unit_img"><img src="<%=path %>/front/images/vnotice_icon.png" /></a><a href="<%=path%>/freetrial/cloudNotify" class="unit_link">语音通知</a>
  			</li>
  			<li>
  				<a href="<%=path%>/freetrial/callinout" class="unit_img"><img src="<%=path %>/front/images/cback_icon.png" /></a><a href="<%=path%>/freetrial/callinout" class="unit_link">双向回拨</a>
  			</li>
  			<li>
  				<a href="<%=path%>/freetrial/multmediasms" class="unit_img"><img src="<%=path %>/front/images/media_icon.png" /></a><a href="<%=path%>/freetrial/multmediasms" class="unit_link">多媒体信息</a>
  			</li>
  			<li>
  				<a href="<%=path%>/freetrial/VoIP" class="unit_img"><img src="<%=path %>/front/images/nvoice_icon.png" /></a><a href="<%=path%>/freetrial/VoIP" class="unit_link">互联网语音</a>
  			</li>
  			<li>
  				<a href="<%=path%>/freetrial/toll" class="unit_img"><img src="<%=path %>/front/images/lcall_icon.png" /></a><a href="<%=path%>/freetrial/toll" class="unit_link">落地电话</a>
  			</li>
  			<li>
  				<a href="<%=path%>/freetrial/telemarket" class="unit_img"><img src="<%=path %>/front/images/market_icon.png" /></a><a href="<%=path%>/freetrial/telemarket" class="unit_link">视频通话</a>
  			</li>
  		</ul>
   	</div>
  </div>
  <div class="clear"></div>
  <div class="display_box">
  	<div class="display_tit">
  		<h1><span class="doc">客户端体验</span></h1>
  	</div>
  	<div class="display_ctn client_experience">
  		<h2>通过客户端（Android、IOS、PC）体验产品通讯能力</h2>
  		<p>说明：(1)根据相应体验终端进入对应的demo下载界面下载并解压SDK压缩包，将其安装到体验终端上</p>
  		<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(2)解压下载的压缩包，将其安装到体验终端上。</p>
  		<p style="text-align:center;"><a href="<%=path %>/product_service/download" class="download">进入下载</a></p>
  		<ul>
  		    <i>&nbsp;</i>
  			<li class="step1">
  			    <em>&nbsp;</em>
  				<p class="img"><img src="<%=path %>/front/images/exp_step1.png" /></p>
  				<p><b>注册成为开发者</b></p>
  				<p>获得在线体验<br />（您需要先注册成为开发者）</p>
  			</li>
  			<li class="step_icon"><img src="<%=path %>/front/images/step_icon.png" /></li>
  			<li class="step2">
  			    <em>&nbsp;</em>
  				<p class="img"><img src="<%=path %>/front/images/exp_step2.png" /></p>
  				<p><b>启动体验终端上的客户端，并使用快传账号登陆</b></p>
  				<p>体验部分功能需要绑定测试号码，默认注册的手机号做为一个测试号，你可以再增加绑定4个号码</p>
  				<p>体验拨打落地电话、回拨电话、外呼通知等只能输入测试号避免骚扰他人</p>
  			</li>
  			<li class="step_icon"><img src="<%=path %>/front/images/step_icon.png" /></li>
  			<li class="step3">
  			    <em>&nbsp;</em>
  				<p class="img"><img src="<%=path %>/front/images/exp_step3.png" /></p>
  				<p><b>启动客户端<br />进行体验</b></p>
  				<p>浏览功能，查看文档</p>
  				<p><a href="http://docs.flypaas.com/doku.php" class="view">查看文档</a></p>
  			</li>
  		</ul>
   	</div>
  </div>
</div>
<!--中间部分middle eof--> 

 

<!--公共版权copyright bof-->
<%@include file="/front/foot_sk.jsp"%><%@include file="/front/foot.jsp" %>
<!--公共版权copyright eof-->


<!--开发组件翻转 bof-->
<script type="text/javascript">
  $(function(){
	$("#h_menu_6").css("color","#05c040");
    $(".develop_unit dl").hover(function(){
      $(this).find("p").show();
      $(this).find("dt").hide();
      $(this).find("dd").hide();
    },function(){
      $(this).find("p").hide();
      $(this).find("dt").show();
      $(this).find("dd").show();
    })
  });
</script>
<!--开发组件翻转 eof-->
</body>
</html>
