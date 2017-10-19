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
<meta name="description" content="快传融合通讯网络电话，专业提供VoIP网络电话通讯解决方案，企业语音解决方案，提供SDK接口，让你轻松解决互联网通话难题" />
<title>互联网语音体验中心_快传融合通讯开放平台</title>
<%@include file="/front/resource.jsp" %>
</head>

<body>
<!--公共头部header bof-->
<%@include file="/front/head.jsp" %>

<!--公共头部header eof--> 

<!--中间部分middle bof-->
<div class="middle mid_box">
  <div class="banner app_banner">&nbsp;</div>
  <div class="display_box">
  	<div class="display_tit">
  		<h1><span class="net_voice">互联网语音</span></h1>
  	</div>
  	<div class="display_ctn">
  		<dl>
  			<dt><img src="<%=path%>/front/images/net_voice.png" /></dt>
  			<dd><p>互联网语音是基于IP网络实现的点对点实时语音通话方式，通过将模拟声音讯号（Voice）数字化，以数据包的形式在IP网络间实时传递，支持多终端跨平台（APP、Web 、PC）。在WiFi环境下，双方可以真正实现免费通话，自由连通，包括国内长途和国际长途通话。在非WiFi环境下，双方将会消耗一定的流量（1分钟约0.125M）。</p><p><a href="http://docs.flypaas.com/doku.php?id=语音通话接口_android">查看文档</a></p></dd>
  		</dl>
  	</div>
  </div>
  <div class="clear"></div>
  <div class="display_wrapper">
  <div class="display_box unit_advantage">
  	<div class="display_tit">
  		<h1><span class="unit_adv">组件优势</span></h1>
  	</div>
  	<div class="display_ctn">
  		<dl>
  			<dt><img src="<%=path%>/front/images/unit_adv1.png" /></dt>
  			<dd><b>开发简单</b></dd>
  			<dd>开发者在平台注册后，就可使用我们的云端REST API和客户端SDK包。只需要加入几行代码，就能实现互联网语音能力。</dd>
  		</dl>
  		<dl>
  			<dt><img src="<%=path%>/front/images/unit_adv2.png" /></dt>
  			<dd><b>跨平台</b></dd>
  			<dd>iPhone手机、Android手机以及PC电脑之间只要都安装客户端软件，即可相互免费语音对讲，让沟通无界限。</dd>
  		</dl>
  		<dl>
  			<dt><img src="<%=path%>/front/images/unit_adv3.png" /></dt>
  			<dd><b>保证通话质量</b></dd>
  			<dd>自主研发系统，媒体智能路由技术，保证语音通话清晰顺畅，可与传统电话相媲美。</dd>
  		</dl>  		
  	</div>
  </div>  
  <div class="display_box documentation">
    <div class="display_tit">
      <h1><span class="doc">文档说明</span></h1>
    </div>
    <div class="display_ctn">
      <dl class="intro">
        <dd><b><a href="http://docs.flypaas.com/doku.php?id=新手指引" target="_blank">接入指引</a></b></dd>
        <dd>从创建应用到应用通过审核的流程介绍</dd>
      </dl>
      <dl class="api">
        <dd><b><a href="http://docs.flypaas.com/doku.php?id=rest_api介绍" target="_blank">API文档</a></b></dd>
        <dd>API接口描述及说明文档</dd>
      </dl>
      <dl class="audit">
        <dd><b><a href="http://docs.flypaas.com/doku.php?id=应用审核规范" target="_blank">审核规范</a></b></dd>
        <dd>快传融合通讯开放平台应用审核规范</dd>
      </dl>
      <dl class="faq">
        <dd><b><a href="http://docs.flypaas.com/doku.php?id=faqs" target="_blank">常见问题</a></b></dd>
        <dd>为开发者提供产品、技术方面常见问题解答</dd>
      </dl>     
    </div>
  </div> 
  </div> 
  <div class="clear"></div>
</div>
<!--中间部分middle eof--> 

 

<!--公共版权copyright bof-->
<%@include file="/front/foot_sk.jsp"%><%@include file="/front/foot.jsp" %>
<!--公共版权copyright eof-->
<script type="text/javascript">
$(function(){
	$("#h_menu_2").css("color","#05c040");
})
</script>
</body>
</html>
