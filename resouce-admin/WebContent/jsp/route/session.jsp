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
    <div class="panel-head"><strong class="icon-reorder"> 会话路由</strong></div>
    <div class="padding border-bottom">
      <ul class="search">
      	<li>
          <select id=routeDoamin class="input" style="width:150px; line-height:17px;">
          	<c:forEach var="domain" items="${routeKeyList }">
          		<c:choose>
          			<c:when test="${domain == routeDoamin}">
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
        	<input type="text" placeholder="会话ID关键字" id="sessionKey" class="input" value="${page.parameter.paraKey }" style="width:250px; line-height:17px;display:inline-block" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	        <a href="javascript:void(0)" class="button border-main icon-search" onclick="jumpTo()" title="查询"> </a>
        </li>
        <span class="pageCount">
          	每页显示
	          <select id="pageRowCount" onchange="jumpTo()">
	       		<option value="10" <c:if test="${page.pageRowCount == 10}">selected="selected"</c:if> >10</option>
	       		<option value="30" <c:if test="${page.pageRowCount == 30}">selected="selected"</c:if> >30</option>
	       		<option value="50" <c:if test="${page.pageRowCount == 50}">selected="selected"</c:if> >50</option>
	       		<option value="100" <c:if test="${page.pageRowCount == 100}">selected="selected"</c:if> >100</option>
		      </select>条
	      </span>
      </ul>
    </div>
    <table class="table text-center"  border="1" cellpadding="1" cellspacing="1">
      <tr class="text-c">
        <th >会话ID</th>
        <th >路由策略</th>
        <th >优先级</th>
        <th >COST值</th>
        <th >会话路径</th>
      </tr>
      <c:forEach var="sessionMap" items="${page.rsMap}">  
      	<c:forEach var="session" items="${sessionMap.value}" varStatus="status">
      		<c:if test="${status.index == 0}">
		      	<tr>
	      			<td rowspan="${fn:length(sessionMap.value)}">${sessionMap.key}</td>
	      			<td>
	      				<c:if test="${session.strategy == 1}">质量优先</c:if>
	      				<c:if test="${session.strategy == 2}">价格优先</c:if>
	      				<c:if test="${session.strategy == 3}">性价比优先</c:if>
	      			</td>
	      			<td>${session.route_ind }</td>
	      			<td>${session.path_cost }</td>
	      			<td>${session.path }</td>
		      	</tr>
      		</c:if>
      		<c:if test="${status.index != 0}">
		      	<tr>
	      			<td>
	      				<c:if test="${session.strategy == 1}">质量优先</c:if>
	      				<c:if test="${session.strategy == 2}">价格优先</c:if>
	      				<c:if test="${session.strategy == 3}">性价比优先</c:if>
	      			</td>
	      			<td>${session.route_ind }</td>
	      			<td>${session.path_cost }</td>
	      			<td>${session.path }</td>
		      	</tr>
      		</c:if>
      	</c:forEach>
     </c:forEach>
     <tr>
        <td colspan="9">
        	<div class="pagelist">
        		<c:if test="${page.currentPage != 1}">
        	 		<a onclick="jumpTo(${page.currentPage-1})">上一页</a>
        	 	</c:if>
        	 	<c:forEach items="${page.pageList}" var="item">
				<c:if test="${page.currentPage != item}">
					<a onclick="jumpTo(${item})">${item}</a>
				</c:if>
				<c:if test="${page.currentPage == item}">
					<a class="current">${item}</a>
				</c:if>
				</c:forEach>
				<c:if test="${page.currentPage != page.totalPage}">
	        	 	<a onclick="jumpTo(${page.currentPage+1})">下一页</a>
				</c:if>
         		 <label for="sitename">共&nbsp; ${page.totalPage}&nbsp; 页</label>
        	 </div>
         </td>
      </tr>
    </table>
  </div>
</form>
<script type="text/javascript">
function jumpTo(page){
	if (typeof(page) == "undefined") { 
		page = 1;
	}
	var sessionKey = $('#sessionKey').val();
	var routeDoamin = $('#routeDoamin').val();
	var pageRowCount = $('#pageRowCount').val(); 
	$('body').load('/route/querySession',{"currentPage":page,"sessionKey":sessionKey,"routeDoamin":routeDoamin,"pageRowCount":pageRowCount});
}
</script>
</body></html>