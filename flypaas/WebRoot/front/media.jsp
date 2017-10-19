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
<meta name="description" content="快传融合通讯多媒体信息，基于网络通讯，支持两人或多人之间的文字、图片、语音、视频等形式的消息传递，让你快速实现类微信即时通讯功能" />
<title>多媒体信息体验中心__快传融合通讯开放平台</title>
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
  		<h1><span class="media">多媒体信息</span></h1>
  	</div>
  	<div class="display_ctn">
  		<dl>
  			<dt><img src="<%=path%>/front/images/media.png" /></dt>
  			<dd><p>多媒体消息是基于IP网络的通讯方式，支持两人或多人之间的文字、图片、语音、视频等形式的消息传递，比传统短信沟通更灵活方便、及时、资费低，支持离线消息管理、群组管理和群组消息广播等。合作伙伴可以快速实现微信类的即时通讯功能。</p><p><a href="http://docs.flypaas.com/doku.php?id=即时消息接口_android">查看文档</a></p></dd>
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
  			<dt><img src="<%=path%>/front/images/unit_adv4.png" /></dt>
  			<dd><b>灵活多样</b></dd>
  			<dd>支持用户自定义扩展信息种类，包括文本、语音、视频、附件、名片、表情、位置信息等。</dd>
  		</dl>
  		<dl>
  			<dt><img src="<%=path%>/front/images/unit_adv5.png" /></dt>
  			<dd><b>多终端</b></dd>
  			<dd>支持多终端、跨平台（APP、Windows、Android）通讯，无需改变开发习惯，即可快速接入通讯服务。</dd>
  		</dl>
  		<dl>
  			<dt><img src="<%=path%>/front/images/unit_adv6.png" /></dt>
  			<dd><b>安全可靠</b></dd>
  			<dd>基于分布式的云端网络传输存储消息，严密的安全审查机制，信息传输更安全快速。</dd>
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
