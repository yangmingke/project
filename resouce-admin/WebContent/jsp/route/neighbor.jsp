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
<form method="post" action="">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> <c:out value="${ip}"/> 邻居节点列表</strong></div>
    <table class="table table-hover text-center">
      <tr class="text-c">
        <th >节点IP地址</th>
        <th >节点名称</th>
        <th >性价比Cost<a style="color:green" href="/route/queryNeighborById?ip=${ip}&routeDomain=${routeDomain}&order=ASC&sort=RTPP_NBR_perf_">&nbsp;&nbsp;&#8593;</a><a style="color:red" href="/route/queryNeighborById?ip=${ip}&order=DESC&sort=RTPP_NBR_perf_">&nbsp;&nbsp;&#8595;</a></th>
        <th >质量Cost<a style="color:green" href="/route/queryNeighborById?ip=${ip}&routeDomain=${routeDomain}&order=ASC&sort=RTPP_NBR_quality_">&nbsp;&nbsp;&#8593;</a><a style="color:red" href="/route/queryNeighborById?ip=${ip}&order=DESC&sort=RTPP_NBR_quality_">&nbsp;&nbsp;&#8595;</a></th>
        <th >价格Cost<a style="color:green" href="/route/queryNeighborById?ip=${ip}&routeDomain=${routeDomain}&order=ASC&sort=RTPP_NBR_price_">&nbsp;&nbsp;&#8593;</a><a style="color:red" href="/route/queryNeighborById?ip=${ip}&order=DESC&sort=RTPP_NBR_price_">&nbsp;&nbsp;&#8595;</a></th>
        <th >资源所属地区</th>
        <th >网络运营商</th>
        <th >网络层</th>
        <th >当前状态</th>
        <th >边界路由</th>
      </tr>
      <c:forEach var="resourceList" items="${neighborRtppList}">  
      <tr>
        <td >${resourceList.ip}</td>
        <td >${resourceList.name}</td>
        <td >${resourceList.perf}</td>
        <td >${resourceList.quality}</td>
        <td >${resourceList.price}</td>
        <td >${resourceList.cityName}</td>
        <td >${resourceList.operatorName}</td>
        <td >
        	<c:if test="${resourceList.net_level == 0}">
	        	<c:out value="未知"></c:out>
	        </c:if>
	        <c:if test="${resourceList.net_level == 1}">
	        	<c:out value="核心层"></c:out>
	        </c:if>
	        <c:if test="${resourceList.net_level == 2}">
	        	<c:out value="骨干层"></c:out>
	        </c:if>
	        <c:if test="${resourceList.net_level == 3}">
	        	<c:out value="接入层"></c:out>
	        </c:if>
        </td>
        <td >
	        <c:if test="${resourceList.status == 0}">
	        	<c:out value="初始状态"></c:out>
	        </c:if>
	        <c:if test="${resourceList.status == 1}">
	        	<c:out value="审核中"></c:out>
	        </c:if>
	        <c:if test="${resourceList.status == 2}">
	        	<c:out value="审核失败"></c:out>
	        </c:if>
	        <c:if test="${resourceList.status == 3}">
	        	<c:out value="就绪状态"></c:out>
	        </c:if>
	        <c:if test="${resourceList.status == 4}">
	        	<c:out value="暂停使用"></c:out>
	        </c:if>
	        <c:if test="${resourceList.status == 5}">
	        	<c:out value="离线状态"></c:out>
	        </c:if>
	        <c:if test="${resourceList.status == 6}">
	        	<c:out value="在线状态"></c:out>
	        </c:if> 
        </td>
        <td >
	        <c:if test="${resourceList.is_bdr == 0}">
		        	<c:out value="否"></c:out>
		    </c:if>
		    <c:if test="${resourceList.is_bdr == 1}">
		        	<c:out value="是"></c:out>
		    </c:if>
        </td>
      </tr>
     </c:forEach>
    </table>
  </div>
</form>
<div class="form-group" style="text-align: center;padding-top: 40px;">
  <div class="field">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <button id="back" class="button bg-main icon-undo" type="button" onclick="backD()"> 返回</button>
  </div>
</div> 
<script type="text/javascript">
	function backD(){
		window.location.href="/route/queryDomain";
	}
	function showResource(ip){
		$('body').load('/route/queryResourceById?ip=' + ip);
	}

</script>
</body></html>