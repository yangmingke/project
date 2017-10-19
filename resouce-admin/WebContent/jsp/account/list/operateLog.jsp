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
    <div class="panel-head"><strong class="icon-reorder"> 用户操作日志</strong></div>
    <div class="padding border-bottom">
      <ul class="search" style="padding-left:10px;">
          <li>
            <select id="roleId" class="input" style="width:200px; line-height:17px;">
              <option value="">用户角色：所有</option>
              <option value="0" <c:if test="${roleId == 0}">selected="selected"</c:if> >管理员</option>
              <option value="1" <c:if test="${roleId == 1}">selected="selected"</c:if> >运营人员</option>
              <option value="2" <c:if test="${roleId == 2}">selected="selected"</c:if> >财务人员</option>
            </select>
          </li>
          日期范围：
		  <input type="text"  placeholder="操作日期起" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}'})" id="datemin" class="input-text Wdate" value="${datemin }" style="width:120px;height: 41px;" name="dateMin">
		    -
		  <input type="text"  placeholder="操作日期止" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d'})" id="datemax" value="${datemax }" class="input-text Wdate" style="width:120px;height: 41px;" name="dateMax">
		  &nbsp;&nbsp;&nbsp;&nbsp;
		   
      	  <input type="text" placeholder="操作关键字" id="operate" class="input" style="width:250px; line-height:17px;display:inline-block" value="${operate }"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
        <th>用户ID</th>
        <th>用户名称</th>
        <th>用户角色</th>
        <th>操作</th>  
        <th>操作ip地址</th>
        <th>操作时间</th>
      </tr> 
      <% int i = 1; %>
   	  <c:forEach var="user" items="${page.result}">   
	   	  <tr>
	         <td><%= i++ %></td>
	         <td>${user.sid}</td>
	         <td>${user.username}</td>
	         <td>${user.roleName}</td>
	         <td>${user.opDesc}</td>        
	         <td>${user.ip}</td>
	         <td><fmt:formatDate value="${user.createDate}" pattern="yyyy-MM-dd HH:mm" /></td>
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
    <div class="form-group" style="text-align: center;padding-top: 40px;">
      <div class="field">
        <button id="back" class="button bg-main icon-undo" type="button" onclick="queryAccount()"> 返回</button>
      </div>
    </div> 
  </div>
  <input id="netSid" value="${netSid}" hidden="hidden">
  <input id="roleId_para" value="${para.roleId}" hidden="hidden">
  <input id="datemin_para" value="${para.datemin}" hidden="hidden">
  <input id="datemax_para" value="${para.datemax}" hidden="hidden">
  <input id="operate_para" value="${para.operate}" hidden="hidden">
</form>
<script type="text/javascript">
	//条件查询
	function jumpTo(page){
		if (typeof(page) == "undefined") { 
			page = 1;
		}
		var roleId = $('#roleId').val();
		var datemin = $('#datemin').val();
		var datemax = $('#datemax').val();
		var operate = $('#operate').val();
		var netSid = $('#netSid').val();
		var pageRowCount = $('#pageRowCount').val();
		
		$('body').load('/log/queryLog',{"page":page,"roleId":roleId,"datemin":datemin,"datemax":datemax,"operate":operate,"netSid":netSid,"pageRowCount":pageRowCount});
	}
	//页码跳转
	function jumpToPage(page){
		if (typeof(page) == "undefined") { 
			page = 1;
		}
		var roleId = $('#roleId_para').val();
		var datemin = $('#datemin_para').val();
		var datemax = $('#datemax_para').val();
		var operate = $('#operate_para').val();
		var netSid = $('#netSid').val();
		var pageRowCount = $('#pageRowCount').val();
		
		$('body').load('/log/queryLog',{"page":page,"roleId":roleId,"datemin":datemin,"datemax":datemax,"operate":operate,"netSid":netSid,"pageRowCount":pageRowCount});
	}
	
	function queryAccount(){
		$('body').load('/account/queryAccount');
	}
</script>
</body>
</html>