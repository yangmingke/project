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
<title>公司介绍_快传融合通讯开放平台</title>
<%@include file="/front/resource.jsp" %>
</head>

<body>
<!--公共头部header bof-->
<%@include file="/front/head.jsp" %>
<!--公共头部header eof--> 

<!--中间部分middle bof-->
<div class="middle mid_box about_box">
  <div class="banner im_banner">&nbsp;</div>  
  <div class="display_wrapper">
    <div class="about_menu">
      <div class="menu_tit">
        <h1><span class="about">关于我们</span></h1>
      </div>
      <ul>
        <li class="active"><a href="<%=path%>/about/companyIntro"><i class="company">&nbsp;</i>公司介绍</a></li>
        <li><a href="<%=path%>/about/service"><i class="service">&nbsp;</i>我们的服务</a></li>
        <li><a href="<%=path%>/about/items"><i class="items">&nbsp;</i>服务条款</a></li>
        <li><a href="<%=path%>/about/report"><i class="report">&nbsp;</i>媒体报道</a></li>
        <li><a href="<%=path%>/about/news"><i class="news">&nbsp;</i>新闻中心</a></li>
        <li><a href="<%=path%>/about/partners"><i class="news">&nbsp;</i>合作伙伴</a></li>
        <li><a href="<%=path%>/about/cooperation"><i class="cooperation">&nbsp;</i>联系我们</a></li>
      </ul>
    </div>
    <div class="about_txt">
      <div class="display_tit">
        <h1><span class="company">公司介绍</span></h1>
      </div>
      <div class="display_ctn">
        <p>深圳市快传技术有限公司（以下简称快传），是一家致力于创建开放性融合通讯服务平台，为广大开发者提供低门槛高效率创业机会的通讯服务提供商。</p>
        <p>快传融合通讯开放平台是深圳市快传技术有限公司为互联网、移动互联网的广大开发者和企业提供通讯能力的云计算PaaS平台。平台融合三大运营商和互联网IP网络，通过云端开放的Rest API和客户端SDK（Android、IOS、Windows SDK）的方式将通讯能力提供给开发者和企业，让企业和开发者能够轻松构建语音、短信、VOIP、视频等解决方案，无硬件、网络成本，快速搭建和使用电信级通讯能力。</p>
		<p>快传拥有十年VoIP通讯行业运营积累，成功运营、开发累计注册超2亿用户的产品，集结了VoIP行业70%以上的电信资源。公司拥有一批追求“创新、速度、完美”的专业化互联网运营团队，其核心成员均来自传统电信、互联网行业、知名IT企业，平均从业经验10年以上，具有较深的产品研发能力及运营服务经验，对通信、互联网行业有着深刻的理解，拥有较强的技术应用及模式创新能力。</p>
        <p>快传致力于通讯服务行业，锻造业界领先的通讯能力，臻于完善。公司核心使命：<br />&nbsp;&nbsp;&nbsp;&nbsp;我们提供更便捷、更全面的电信级通信能力；<br />&nbsp;&nbsp;&nbsp;&nbsp;打破传统通讯技术壁垒，引导全民通讯模式创新；<br />&nbsp;&nbsp;&nbsp;&nbsp;让人类沟通更便捷，成本更低廉，体验更多样；<br />&nbsp;&nbsp;&nbsp;&nbsp;成为一家受人尊敬的服务公众互联网通讯行业。</p>
        <p>快传的愿景是让每个应用插上通讯的翅膀，并以最开放的技术为全球开发者提供最专业最好用的融合通讯服务。</p>
      </div>
    </div>
  </div>
  <div class="clear"></div>
</div>
<!--中间部分middle eof--> 

 

<!--公共版权copyright bof-->
<%@include file="/front/foot_sk.jsp"%><%@include file="/front/foot.jsp" %>
<!--公共版权copyright eof-->

</body>
</html>
