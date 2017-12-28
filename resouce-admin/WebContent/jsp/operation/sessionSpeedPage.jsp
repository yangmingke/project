<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.net.URLDecoder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title></title>  
    <link rel="stylesheet" href="${ctx}/css/pintuer.css">
    <link rel="stylesheet" href="${ctx}/css/admin.css">
    <script src="${ctx}/js/jquery.js"></script>
    <script src="${ctx}/js/pintuer.js"></script>
</head>
<body>
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 会话传输速率查询</strong></div>
    <div class="padding border-bottom">
	    <ul class="search">
	    	<li>
	          <select id="routeDoamin" class="input" style="width:150px; line-height:17px;">
	          	<c:forEach var="domain" items="${routeKeyList }">
	          		<c:choose>
	          			<c:when test="${domain == routeDefaultKey}">
				            <option value='${fn:substringAfter(domain,"RTPP_CONCURR_ZSET_")}' selected="selected">路由域：${fn:substringAfter(domain,"RTPP_CONCURR_ZSET_")}</option>
	          			</c:when>
	          			<c:otherwise>
	          				<option value='${fn:substringAfter(domain,"RTPP_CONCURR_ZSET_")}'>路由域：${fn:substringAfter(domain,"RTPP_CONCURR_ZSET_")}</option>
	          			</c:otherwise>
	          		</c:choose>
	          	</c:forEach>
	          </select>
	        </li>
	        <li>
	        	<select id="routePolicy" class="input" style="width:150px; line-height:17px;">
	        		<option value="1">质量优先</option>
	        		<option value="2">价格优先</option>
	        		<option value="3">性价比优先</option>
	        	</select>
	        </li>
	        <li>
	        	<select id="routeId" class="input" style="width:150px; line-height:17px;">
	        		<option value="0">主</option>
	        		<option value="1">备</option>
	        	</select>
	        </li>
	        <li>
	        	<select id="routeType" class="input" style="width:150px; line-height:17px;">
	        		<option value="out">出流量</option>
	        		<option value="in">入流量</option>
	        	</select>
	        </li>
	      	<li>
		       <input type="text" placeholder="会话id" id="sessionID" class="input" style="width:250px; line-height:16px;display:inline-block" value="${sessionID}"/>&nbsp;&nbsp;&nbsp;
		       <a href="javascript:void(0)" class="button border-main icon-search" onclick="queryData()" title="查询"></a>
		       <span><font color="red" id="error" style="display: none;"></font></span>
	      	</li>
	    </ul>
    </div>
  </div>
      <span style="font-size: 16px">路由：</span><span id="path" style="font-size: 16px"></span> 
  <div id="main" style="height:480px;"></div>
<script type="text/javascript" src="${ctx}/js/echarts.min.js"></script>
<script type="text/javascript" src="${ctx}/js/sessionSpeed.js"></script>
</body>
</html>