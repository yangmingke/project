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
<title>我们的服务_快传融合通讯开放平台</title>
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
        <li><a href="<%=path%>/about/companyIntro"><i class="company">&nbsp;</i>公司介绍</a></li>
        <li class="active"><a href="<%=path%>/about/service"><i class="service">&nbsp;</i>我们的服务</a></li>
        <li><a href="<%=path%>/about/items"><i class="items">&nbsp;</i>服务条款</a></li>
        <li><a href="<%=path%>/about/report"><i class="report">&nbsp;</i>媒体报道</a></li>
        <li><a href="<%=path%>/about/news"><i class="news">&nbsp;</i>新闻中心</a></li>
        <li><a href="<%=path%>/about/partners"><i class="news">&nbsp;</i>合作伙伴</a></li>
        <li><a href="<%=path%>/about/cooperation"><i class="cooperation">&nbsp;</i>联系我们</a></li>
      </ul>
    </div>
    <div class="about_txt">
      <div class="display_tit">
        <h1><span class="service">我们的服务</span></h1>
      </div>
      <div class="display_ctn">
        <p>快传融合通讯开放平台是深圳市快传技术有限公司为互联网、移动互联网的广大开发者和企业提供通讯能力的云计算PaaS(Platform as a Service, 平台即服务)平台服务商。</p>
        <p>平台融合三大运营商和互联网IP网络，通过云端开放的REST API和客户端SDK（Android、iOS、Windows SDK）的方式将通讯能力提供给开发者和企业，让企业和开发者能够轻松构建语音、短信、VoIP、视频等解决方案，无硬件、网络成本，快速搭建和使用电信级通讯能力。</p>
        <p>快传全面支持Android、iOS、Web等多种平台，在语音、即时消息、VoIP、视频等能力做了极致的优化，让开发者摆脱繁重的通讯底层开发，最大限度地缩短产品开发周期，最短的时间内让应用拥有通讯能力。</p>
        <p>一、主要能力：</p>
        <p>1. 语音能力<br />
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;支持落地电话、点对点语音电话、双向回拨等语音通讯能力；<br />
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;支持互联网高效压缩技术，最低达到4kbps，极大节省用户流量；<br />
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;支持号码透传显号；<br />
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;支持多终端、跨平台（APP、Web、PC）语音；</p>
        <p>2. 即时通讯<br />
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;支持文本、图片、音频、视频等多媒体消息；<br />
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;支持用户自定义扩展消息种类，如位置、表情等；<br />
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;支持多终端、跨平台（APP、Web、PC）语音；</p>
        <p>3. 视频通讯<br />
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;支持点对点视频，包括P2P模式、中转模式；<br />
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;支持VP8，H.264，VP9等主流编码；<br />
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;动态码率自适应，网络智能路由，确保视频流畅度；<br />
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;支持多终端、跨平台（APP、Web、PC）语音；</p>
        <p>二、服务</p>
        <p>1. 自助化服务</p>
        <p>平台提倡高效的自助化服务，通过从源头优化简易的API、灵活的编程语言，细致的产品说明和文档引导，到提供与开发环境一致的测试demo，仅为开发者能简单、灵活的为应用接入通讯能力。</p>
        <p>我们的追求是：让个每个应用都插上通讯的翅膀。</p>
        <p>2.强大的技术支撑系统网</p>
        <p>为开发者解决技术难点，提供技术支持是我们服务的第一步。快传开发者社区、技术周刊、QQ群和不定时的技术交流沙龙等多渠道技术支持与服务，技术工程师一对一技术支持服务，不仅保证通讯质量的实时优化，更保障开发者的高并发通讯需求。</p>      
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
