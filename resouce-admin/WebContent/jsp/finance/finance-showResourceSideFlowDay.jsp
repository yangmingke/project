<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.net.URLDecoder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    <script src="${ctx}/plugin/My97DatePicker/WdatePicker.js"></script>  
</head>
<body>
<form method="post" action="">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 资源方流量日消耗</strong></div>
    <div class="padding border-bottom">
	    <ul class="search">
	   		<li>
        		日期筛选：
        	</li>
        	<li>
	     	 	<input type="text"  placeholder="请选择日期" onfocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})" value="${dateTime }" id="dateTime" class="input-text Wdate" style="width:150px; height:38px; display:inline-block"  name="dateTime">
		  		&nbsp;&nbsp;&nbsp;&nbsp;
        	</li>
	      	<li>
		       <input type="text" placeholder="输入关键字搜索资源" id="keyWord" class="input" style="width:250px; line-height:16px;display:inline-block" value="${keyWord }"/>&nbsp;&nbsp;&nbsp;
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
    <table class="table table-hover text-center" >
      <tr class="text-c">
        <th >序号</th>
        <th >资源方公司简称</th>
        <th >接收流量(KB)</th>
        <th >发送流量(KB)</th>
      <!--   <th >总流量(KB)</th> -->
        <th >平均单价(元/GB)</th>
        <th >收入(元)</th>
        <th >日期</th>
      </tr>
      <%int i = 1; %> 
      <c:forEach var="resourceList" items="${page.resultMap}"> 
      <tr>
        <td ><%=i++%></td>
        <td >${resourceList.userName}</td>
        <td >${resourceList.traffic_in}</td>
        <td >${resourceList.traffic_out}</td>
       <%--  <td >${resourceList.traffic_total}</td> --%>
        <td >${resourceList.traffic_feerate}</td>
        <td >${resourceList.traffic_fee}</td>
        <td >${dateTime }</td>
      </tr>
     </c:forEach>
      <tr>
        <td colspan="10">
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

function jumpTo(page){
	if (typeof(page) == "undefined") { 
		page = 1;
	}
	var keyWord = $('#keyWord').val();
	var dateTime = $('#dateTime').val();
	var pageRowCount = $('#pageRowCount').val(); 
	$('body').load('/resourceSideFlowController/queryResourceSideFlowDay',{"page":page,"keyWord":keyWord,"dateTime":dateTime,"pageRowCount":pageRowCount});
}

//页码跳转
function jumpToPage(page){
	if (typeof(page) == "undefined") { 
		page=1;
	}
	var keyWord = $('#keyWord').val();
	var dateTime = $('#dateTime').val();
	var pageRowCount = $('#pageRowCount').val(); 
	$('body').load('/resourceSideFlowController/queryResourceSideFlowDay',{"page":page,"keyWord":keyWord,"dateTime":dateTime,"pageRowCount":pageRowCount});
}


</script>
</body></html>