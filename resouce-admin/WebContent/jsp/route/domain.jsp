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
    <div class="panel-head"><strong class="icon-reorder"> 路由域：<c:out value="${fn:substringAfter(concurrent,'RTPP_CONCURR_ZSET_')}"/> 节点列表</strong></div>
    <div class="padding border-bottom">
      <ul class="search">
        <li>
          <select id="domain" class="input" style="width:150px; line-height:17px;">
          	<c:forEach var="domain" items="${routeKeyList }">
          		<c:choose>
          			<c:when test="${domain == concurrent}">
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
	        <input type="text" placeholder="节点IP地址关键字" id="ipKeyWord" class="input" value="${ipKeyWord}" style="width:250px; line-height:17px;display:inline-block" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
    <table class="table table-hover text-center">
      <tr class="text-c">
        <th >节点IP地址</th>
        <th >节点名称</th>
        <th >节点所属地区</th>
        <th >网络运营商</th>
        <th >网络层</th>
        <th >并发量上限</th>
        <th >带宽上限(MB)</th>
        <th >当前状态</th>
        <th >边界路由</th>
        <th >操作</th>
      </tr>
      <c:forEach var="resourceList" items="${page.result}">  
      <tr>
        <td >${resourceList.ip}</td>
        <td >${resourceList.name}</td>
        <td >${resourceList.cityName}</td>
        <td >${resourceList.operatorName}</td>
        <td >
        	<c:if test="${resourceList.net_level == 0}">
	        	<c:out value="未分配"></c:out>
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
        <td >${resourceList.concurrency_limit}</td>
        <td >${resourceList.bandwidth_limit}</td>
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
        <td>
        	<div class="button-group"> 
        		<a class="button border-green" href="javascript:void(0)" onclick="showResource('${resourceList.ip}')" title="详细信息"><span class="icon-list-alt"></span></a>
        	</div>
        	<div class="button-group"> 
        		<a class="button border-blue" href="javascript:void(0)" onclick="neighbor('${resourceList.ip}')" title="邻居节点"><span class="icon-group"></span></a>
        	</div>
        </td>   
      </tr>
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

function showResource(ip){
	$('body').load('/route/queryResourceById?ip=' + ip + "&routeDomain="+"${concurrent}");
}

function neighbor(ip){
	$('body').load('/route/queryNeighborById?ip=' + ip + "&routeDomain="+"${concurrent}");
}

function jumpTo(page){
	if (typeof(page) == "undefined") { 
		page = 1;
	}
	var concurrent = $('#domain').val();
	var ipKeyWord = $('#ipKeyWord').val();
	var pageRowCount = $('#pageRowCount').val(); 
	$('body').load('/route/queryDomain',{"currentPage":page,"concurrent":concurrent,"ipKeyWord":ipKeyWord,"pageRowCount":pageRowCount});
}

</script>
</body></html>