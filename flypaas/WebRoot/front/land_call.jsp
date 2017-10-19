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
<meta name="description" content="快传融合通讯落地电话，完全免费享受与普通电话一样的通话，应用简单接入，多终端实现" />
<title>落地电话体验中心_快传融合通讯开放平台</title>
<%@include file="/front/resource.jsp" %>
</head>

<body>
<!--公共头部header bof-->
<%@include file="/front/head.jsp" %>

<!--公共头部header eof--> 

<!--中间部分middle bof-->
<div class="middle mid_box">
  <div class="banner vcode_banner">&nbsp;</div>
  <div class="display_box">
  	<div class="display_tit">
  		<h1><span>落地电话</span></h1>
  	</div>
  	<div class="display_ctn">
  		<dl>
  			<dt><img src="<%=path%>/front/images/telephone.png" /></dt>
  			<dd><p>落地电话是主叫方通过IP网络呼叫，被叫方通过传统电话线路接听的通话方式，通过在网页、PC软件或移动客户端集成落地电话能力，可以经IP网络呼叫普通固定电话和手机，包括国内长途和国际长途电话。通话时会消耗主叫方的上网流量（1分钟约0.125M），而被叫方和接听传统普通电话完全一样，并且完全免费。</p><p><a href="http://docs.flypaas.com/doku.php?id=VoIP开发指南">查看文档</a></p></dd>
  		</dl>
  	</div>
  </div>
  <div class="clear"></div>
  <div class="display_box documentation">
    <div class="display_tit">
      <h1><span class="doc">文档说明</span></h1>
    </div>
    <div class="display_ctn">
      <dl class="intro">
        <dt><img src="<%=path%>/front/images/doc1.png" /></dt>
        <dd><b><a href="http://docs.flypaas.com/doku.php?id=新手指引" target="_blank">接入指引</a></b></dd>
        <dd>从创建应用到应用通过审核的流程介绍</dd>
      </dl>
      <dl class="api">
        <dt><img src="<%=path%>/front/images/doc2.png" /></dt>
        <dd><b><a href="http://docs.flypaas.com/doku.php?id=rest_api介绍" target="_blank">API文档</a></b></dd>
        <dd>API接口描述及说明文档</dd>
      </dl>
      <dl class="audit">
        <dt><img src="<%=path%>/front/images/doc3.png" /></dt> 
        <dd><b><a href="http://docs.flypaas.com/doku.php?id=应用审核规范" target="_blank">审核规范</a></b></dd>
        <dd>快传融合通讯开放平台应用审核规范</dd>
      </dl>
      <dl class="faq">
        <dt><img src="<%=path%>/front/images/doc4.png" /></dt>
        <dd><b><a href="http://docs.flypaas.com/doku.php?id=faqs" target="_blank">常见问题</a></b></dd>
        <dd>为开发者提供产品、技术方面常见问题解答</dd>
      </dl>     
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
