<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>后台管理中心</title>  
    <link rel="stylesheet" href="${ctx}/css/pintuer.css">
    <link rel="stylesheet" href="${ctx}/css/admin.css">
    <script src="${ctx}/js/jquery.js"></script>   
</head>
<body style="background-color:#f2f9fd;">
<div class="header bg-main">
  <div class="logo margin-big-left fadein-top">
    <h1><img src="images/y.jpg" class="radius-circle rotate-hover" height="50" alt="" />后台管理中心</h1>
  </div>
  <div class="head-l" style="float: right;padding-right: 5%;"> &nbsp;&nbsp;<a class="button button-little bg-red" href="/adminController/logout"><span class="icon-power-off"></span> 退出登录</a> </div>
</div>
<div class="leftnav">
  <div class="leftnav-title"><strong><span class="icon-list"></span>菜单列表</strong></div>
  <h2 class="on"><span class="icon-user"></span>管理员中心</h2>
  <ul  style="display:block">
    <li><a href="${ctx}/jsp/admin/administratorInfo.jsp" target="right"><span class="icon-caret-right"></span>管理员信息</a></li>
    <li><a href="${ctx}/jsp/admin/editPwd.jsp" target="right"><span class="icon-caret-right"></span>修改密码</a></li>
  </ul> 
  <h2><span class="icon-male"></span>资源方管理</h2>
  <ul>
    <li><a href="/account/queryAccount" target="right"><span class="icon-caret-right"></span>资源方列表</a></li>
    <li><a href="${ctx}/jsp/account/open/openAccont.jsp" target="right"><span class="icon-caret-right"></span>资源方开户</a></li>  
  	<li><a href="${ctx}/systemContrller/sysConfigList" target="right"><span class="icon-caret-right"></span>系统配置</a></li>
  </ul>
  <h2><span class="icon-rocket"></span>资源管理</h2>
  <ul>
    <li><a href="${ctx}/resourceController/queryResourceFenYe.action" target="right"><span class="icon-caret-right"></span>节点信息列表</a></li>
    <li><a href="${ctx}/flushRedisController/flushRedisListFenYe.action" target="right"><span class="icon-caret-right"></span>节点实时状态</a></li>
  </ul> 
  <h2><span class="icon-cogs"></span>运维管理</h2>
  <ul>
  	<li><a href="${ctx}/jsp/operation/sessionPaketLossPage.jsp" target="right"><span class="icon-caret-right"></span>会话丢包查询</a></li>
    <li><a href="${ctx}/jsp/operation/packetLoss.jsp" target="right"><span class="icon-caret-right"></span>业务丢包监控</a></li>
    <li><a href="${ctx}/jsp/operation/packetLossSource.jsp" target="right"><span class="icon-caret-right"></span>丢包来源分布</a></li>
  </ul>
  <h2><span class="icon-rss"></span>路由管理</h2>
  <ul>
    <li><a href="/route/queryDomain" target="right"><span class="icon-caret-right"></span>路由域管理</a></li>
    <li><a href="/route/transmitPage" target="right"><span class="icon-caret-right"></span>路由查找</a></li> 
    <li><a href="/route/analysisPage" target="right"><span class="icon-caret-right"></span>路由分析</a></li> 
    <li><a href="/route/querySession" target="right"><span class="icon-caret-right"></span>会话路由</a></li>
    <li><a href="/route/topologyPage" target="right"><span class="icon-caret-right"></span>节点拓扑图</a></li> 
    <li><a href="/route/linkStatusPage" target="right"><span class="icon-caret-right"></span>链路状态图</a></li>  
    <li><a href="/route/trafficMapPage" target="right"><span class="icon-caret-right"></span>传输流量图</a></li> 
  </ul>
  <h2><span class="icon-jpy"></span>财务管理</h2>
  <ul>
    <li><a href="/resourceFlowController/queryResourceFlowDay.action" target="right"><span class="icon-caret-right"></span>节点流量日消耗</a></li>
    <li><a href="/resourceFlowController/queryResourceFlowMonth.action" target="right"><span class="icon-caret-right"></span>节点流量月消耗</a></li>
    <li><a href="/resourceSideFlowController/queryResourceSideFlowDay.action" target="right"><span class="icon-caret-right"></span>资源方流量日消耗</a></li>  
    <li><a href="/resourceSideFlowController/queryResourceSideFlowMonth.action" target="right"><span class="icon-caret-right"></span>资源方流量月消耗</a></li>   
    <li><a href="${ctx}/accountManagement/queryAccount" target="right"><span class="icon-caret-right"></span>账号管理</a></li>     
    <li><a href="${ctx}/accountManagement/record" target="right"><span class="icon-caret-right"></span>提现记录</a></li>
  </ul>   
</div>
<script type="text/javascript">
$(function(){
  $(".leftnav h2").click(function(){
	  if($(this).is('.on')){
		  return;
	  }
	  $(".leftnav h2").next().slideUp(200);
	  $(this).next().slideToggle(200);	
	  $(".leftnav h2").removeClass("on");
	  $(this).toggleClass("on"); 
  })
  $(".leftnav ul li a").click(function(){
	    $("#a_leader_txt").text($(this).text());
  		$(".leftnav ul li a").removeClass("on");
		$(this).addClass("on");
  })
});
</script>
<div class="admin">
  <iframe scrolling="auto" rameborder="0" src="${ctx}/jsp/admin/administratorInfo.jsp" name="right" width="100%" height="99%"></iframe>
</div>
</body>
</html>