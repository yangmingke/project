<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.net.URLDecoder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>传输流量图</title>  
    <link rel="stylesheet" href="${ctx}/css/pintuer.css">
    <link rel="stylesheet" href="${ctx}/css/admin.css">
    <script src="${ctx}/js/jquery.js"></script>
    <script src="${ctx}/js/pintuer.js"></script>  
</head>
<body>
	<div class="padding border-bottom">
      <ul class="search">
        <li>
          <select id="domain" class="input" style="width:150px; line-height:17px;">
          	<c:forEach var="domain" items="${routeKeyList }">
          		<c:choose>
          			<c:when test="${domain == routeDomain}">
			            <option value="${domain}" selected="selected">路由域：${fn:substringAfter(domain,"RTPP_CONCURR_ZSET_")}</option>
          			</c:when>
          			<c:otherwise>
          				<option value="${domain}">路由域：${fn:substringAfter(domain,"RTPP_CONCURR_ZSET_")}</option>
          			</c:otherwise>
          		</c:choose>
          	</c:forEach>
          </select>
        </li>
        <li>
          <select id="netLevel" class="input" style="width:150px; line-height:17px;">
          	<option value="">----节点层级----</option>
          	<option value="0">未分配</option>
          	<option value="1">核心层</option>
          	<option value="2">骨干层</option>
          	<option value="3">接入层</option>
          </select>
        </li>
        <li>
	        &nbsp;&nbsp;&nbsp;
	        <a href="javascript:void(0)" class="button border-main icon-search" onclick="getData()" title="查询"> </a>
        </li>
      </ul>
    </div>
	<div id="main" style="height:720px;"></div>

    <script src="${ctx}/js/echarts.min.js"></script>
    <script src="${ctx}/js/world.js"></script>
    <script src="${ctx}/js/trafficMap.js"></script>
</body>
</html>