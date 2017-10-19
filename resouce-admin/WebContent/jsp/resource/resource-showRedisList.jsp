<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.net.URLDecoder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    <div class="panel-head"><strong class="icon-reorder"> 节点实时状态</strong></div>
    <div class="padding border-bottom">
      <ul class="search">
      	<li>
          <select id="routeDomain" name="routeDomain" class="input" style="width:150px; line-height:17px;">
          	<c:forEach var="domain" items="${routeKeyList }">
          		<c:choose>
          			<c:when test="${fn:substringAfter(domain,'RTPP_CONCURR_ZSET_') == routeDomain}">
			            <option value="${fn:substringAfter(domain,"RTPP_CONCURR_ZSET_")}" selected="selected">路由域：${fn:substringAfter(domain,"RTPP_CONCURR_ZSET_")}</option>
          			</c:when>
          			<c:otherwise>
          				<option value="${fn:substringAfter(domain,"RTPP_CONCURR_ZSET_")}">路由域：${fn:substringAfter(domain,"RTPP_CONCURR_ZSET_")}</option>
          			</c:otherwise>
          		</c:choose>
          	</c:forEach>
          </select>
        </li>
        <li>
          <select id="isRunning" name="isRunning" class="input" style="width:150px; line-height:17px;">
          	<option value="">----使用情况----</option>
          	<option value="1" <c:if test="${isRunning == '1'}">selected="selected"</c:if>>正在使用</option>
          	<option value="0" <c:if test="${isRunning == '0'}">selected="selected"</c:if>>未被使用</option>
          </select>
        </li>
        <li>
          <select id="status" name="status" class="input" style="width:150px; line-height:17px;">
          	<option value="">----实时状态----</option>
          	<option value="3" <c:if test="${status == '3'}">selected="selected"</c:if>>就绪状态</option>
          	<option value="5" <c:if test="${status == '5'}">selected="selected"</c:if>>离线状态</option>
          	<option value="6" <c:if test="${status == '6'}">selected="selected"</c:if>>在线状态</option>
          </select>
        </li>
        <li>
	        <input type="text" placeholder="输入节点IP地址搜索资源" id="keyword" value="${ip}" class="input" style="width:250px; line-height:17px;display:inline-block" />&nbsp;&nbsp;&nbsp;
	        <input type="text" placeholder="输入资源方名称关键字" id="username" value="${username}" class="input" style="width:250px; line-height:17px;display:inline-block" />&nbsp;&nbsp;&nbsp;
	        <a href="javascript:void(0)" class="button border-main icon-search" onclick="jumpToPage()" title="查询"> </a>
        </li>
        <span class="pageCount">
          	每页显示
	          <select id="pageRowCount" onchange="jumpToPage()">
	       		<option value="10" <c:if test="${page.pageRowCount == 10}">selected="selected"</c:if> >10</option>
	       		<option value="30" <c:if test="${page.pageRowCount == 30}">selected="selected"</c:if> >30</option>
	       		<option value="50" <c:if test="${page.pageRowCount == 50}">selected="selected"</c:if> >50</option>
	       		<option value="100" <c:if test="${page.pageRowCount == 100}">selected="selected"</c:if> >100</option>
		      </select>条
	      </span>
      </ul>
    </div>
    <table class="table table-hover text-center">
      <tr class="text-c">
    	<th >序号</th>
      	<th >资源方名称</th>
        <th >节点名称</th>
        <th >节点IP地址</th>
        <th >实时并发量</th>
        <th >实时接收流量(KB/S)</th>
        <th >实时发送流量(KB/S)</th>
        <th >实时状态</th>
        <th >运行时长</th>
        <th >版本号</th>
        <th >操作</th>
      </tr>
      <%int i = 1; %> 
      <c:forEach var="redisList" items="${page.result}">
	    <tr class="text-c">
	    	<td ><%=i++%></td>
	    	<td >${redisList.username}</td>
	  		<td >${redisList.name}</td>
	  		<td >${redisList.ip}</td>
	  		<td >${redisList.concurrency}</td>
	  		<td >${redisList.trafficIn}</td>
	  		<td >${redisList.trafficOut}</td>
	  		<c:if test="${redisList.status == 0}">
	        	<td class="user-status" style="width: 15%"><span class="label label-success">初始状态</span></td>
			</c:if>
			<c:if test="${redisList.status == 1}">
			    <td class="user-status" style="width: 15%"><span class="label label-warning">审核中</span></td>
			</c:if>
			<c:if test="${redisList.status == 2}">
			    <td class="user-status" style="width: 15%"><span class="label label-secondary">审核失败</span></td>
			</c:if>
			<c:if test="${redisList.status == 3}">
			    <td class="user-status" style="width: 15%"><span class="label label-danger">就绪状态</span></td>
			</c:if>
			<c:if test="${redisList.status == 4}">
			    <td class="user-status" style="width: 15%"><span class="label label-info">暂停使用</span></td>
			</c:if>
			<c:if test="${redisList.status == 5}">
			   	<td class="user-status" style="width: 15%"><span class="label label-inverse" style="color: red;">离线状态</span></td>
			</c:if>
			<c:if test="${redisList.status == 6}">
			   	<td class="user-status" style="width: 15%"><span class="label label-inverse" style="color: green;">在线状态</span></td>
			</c:if>
	  		<td >${redisList.uptime}</td>
			<td >${redisList.version}</td>
			<td>
				<div class="button-group"> 
        			<a class="button border-blue" href="javascript:void(0)" onclick="showNeighbor('${redisList.ip}','${routeDomain}')"  title="配置邻居节点"><span class="icon-group"></span> </a> 
        		</div>
			</td>
	  	</tr>
      </c:forEach>
      <tr>
        <td colspan="9">
        	<div class="pagelist">
        		<c:if test="${page.currentPage != 1}">
        	 		<a onclick="jumpToPage(${page.currentPage-1})">上一页</a>
        	 	</c:if>
        	 	<c:forEach items="${page.pageList}" var="item">
				<c:if test="${page.currentPage != item}">
					<a onclick="jumpToPage(${item})">${item}</a>
				</c:if>
				<c:if test="${page.currentPage == item}">
					<a class="current">${item}</a>
				</c:if>
				</c:forEach>
				<c:if test="${page.currentPage != page.totalPage}">
	        	 	<a onclick="jumpToPage(${page.currentPage+1})">下一页</a>
				</c:if>
         		 <label for="sitename">共&nbsp; ${page.totalPage}&nbsp; 页</label>
        	 </div>
         </td>
      </tr>
    </table>
  </div>
</form>
<script type="text/javascript">
	//页码跳转
	function jumpToPage(page){
		if (typeof(page) == "undefined") { 
			page = 1;
		}
		var IP = $('#keyword').val();
		var routeDomain = $('#routeDomain').val();
		var isRunning=$('#isRunning').val();
		var status=$('#status').val();
		var username=$('#username').val();
		var pageRowCount = $('#pageRowCount').val(); 
		
		$('body').load('/flushRedisController/flushRedisListFenYe.action',{"currentPage":page,"IP":IP,"routeDomain":routeDomain,"isRunning":isRunning,"status":status,"username":username,"pageRowCount":pageRowCount});
	}
	function showNeighbor(ip,routeDomain){
		window.location.href="/resourceController/showNeighbor?ip=" + ip +"&routeDomain=" + routeDomain;
	}
</script>
</body>
</html>