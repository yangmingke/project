<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.net.URLDecoder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
    <div class="panel-head"><strong class="icon-reorder">提现记录</strong></div>
    <div class="padding border-bottom">
      <ul class="search" style="padding-left:10px;">
          <li>
            <select id="status" class="input" style="width:200px; line-height:17px;">
              <option value="">提现状态：所有</option>
              <option value="0" <c:if test="${page.parameter.status == 0}">selected="selected"</c:if> >未审核</option>
              <option value="1" <c:if test="${page.parameter.status == 1}">selected="selected"</c:if> >审核通过</option>
              <option value="2" <c:if test="${page.parameter.status == 2}">selected="selected"</c:if> >审核未通过</option>
              <option value="3" <c:if test="${page.parameter.status == 3}">selected="selected"</c:if> >作废</option>
              <option value="4" <c:if test="${page.parameter.status == 4}">selected="selected"</c:if> >已转账</option>
            </select>
          </li>
          日期范围：
		  <input type="text"  placeholder="申请日期起" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}'})" id="datemin" class="input-text Wdate" style="width:120px;height: 41px;" name="dateMin" value="${page.parameter.datemin }">
		    -
		  <input type="text"  placeholder="申请日期止" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d'})" id="datemax" class="input-text Wdate" style="width:120px;height: 41px;" name="dateMax" value="${page.parameter.datemax }">
		  &nbsp;&nbsp;&nbsp;&nbsp;
		   
      	  <input type="text" placeholder="公司简称、申请人搜索" id="keyword" class="input" style="width:250px; line-height:17px;display:inline-block" value="${page.parameter.keyword }"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <a href="javascript:void(0)" class="button border-main icon-search" onclick="jumpTo()" title="查询"> </a>
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
      <tr>
        <th width="5%">序号</th>
        <th>资源方ID</th>
        <th>资源方公司简称</th>
        <th>提现金额</th>
        <th>提现后余额</th>  
        <th>申请人</th>
        <th>提现状态</th>
        <th>申请时间</th>
        <th>审批时间</th>
        <th>转账时间</th>
        <th>操作</th>
      </tr> 
      <% int i = 1; %>
   	  <c:forEach var="flow" items="${page.resultMap}">   
	   	  <tr>
	         <td><%= i++ %></td>
	         <td>${flow.main_sid}</td>
	         <td>${flow.username}</td>
	         <td>${flow.actual_fee}</td>
	         <td>${flow.balance}</td>        
	         <td>${flow.oper_user}</td>
	         <td>
	         	<c:choose>
	         		<c:when test="${flow.status == '0'}">
	         			<font color="green">未审批</font>
	         		</c:when>
	         		<c:when test="${flow.status == '1'}">
	         			<font color="green">审核通过</font>
	         		</c:when>
	         		<c:when test="${flow.status == '2'}">
	         			<font color="red">审核未通过</font>
	         		</c:when>
	         		<c:when test="${flow.status == '3'}">
	         			<font color="red">作废</font>
	         		</c:when>
	         		<c:when test="${flow.status == '4'}">
	         			<font color="green">已转账</font>
	         		</c:when>
	         	</c:choose>
	         </td>
	         <td><fmt:formatDate value="${flow.create_date}" pattern="yyyy-MM-dd HH:mm" /></td>
	         <td><fmt:formatDate value="${flow.adult_date}" pattern="yyyy-MM-dd HH:mm" /></td>
	         <td><fmt:formatDate value="${flow.finsh_date}" pattern="yyyy-MM-dd HH:mm" /></td>
	         <td>
				<div class="button-group"> <a class="button border-green" href="javascript:void(0)" onclick="queryBillFlow('${flow.id}')" title="审核"><span class="icon-legal"></span> </a> </div>
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
	//条件查询
	function jumpTo(page){
		if (typeof(page) == "undefined") { 
			page = 1;
		}
		var status = $('#status').val();
		var datemin = $('#datemin').val();
		var datemax = $('#datemax').val();
		var keyword = $('#keyword').val();
		var pageRowCount = $('#pageRowCount').val(); 
		$('body').load('/accountManagement/record',{"page":page,"status":status,"datemin":datemin,"datemax":datemax,"keyword":keyword,"pageRowCount":pageRowCount});
	}
	function queryBillFlow(id){
		window.location.href="/accountManagement/queryBillFlow?id="+id;
	}
	
</script>
</body>
</html>