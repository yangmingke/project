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
</head>
<body>
	<form method="post" action="">
	  <div class="panel admin-panel">
	    <div class="panel-head"><strong class="icon-reorder"> 资源方用户列表</strong></div>
	    <table class="table table-hover text-center">
	      <tr>
	        <th width="5%">序号</th>
	        <th>用户ID</th>
	        <th>用户名</th>
	        <th>邮箱</th>  
	        <th>手机</th>
	        <th>真实姓名</th>
	        <th>地址</th>
	        <th>担任角色</th>
	        <th>操作</th>       
	      </tr> 
	      <% int i = 1; %>
	   	  <c:forEach var="user" items="${userList}">   
	   	  <tr>
	         <td><%= i++ %></td>
	         <td width="5%">${user.sid}</td>
	         <td>${user.username}</td>
	         <td>${user.email}</td>
	         <td>${user.mobile}</td>
	         <td>${user.realname}</td>
	         <td>${user.address}</td>
	         <c:forEach var="role" items="${user.role}">
	         	<td>${role.roleName}</td>
	         </c:forEach>
	         <td><div class="button-group"> <a class="button border-green" href="javascript:void(0)" onclick="notice('${user.sid}','${user.username}','${user.netSid}')" title="通知"><span class="icon-bell" ></span></a> </div></td>
	      </tr>
	      </c:forEach>
	    </table>
	  </div>
	</form>
	<div class="form-group" style="text-align: center;padding-top: 40px;">
      <div class="field">
        <button id="back" class="button bg-main icon-undo" type="button" onclick="back()"> 返回</button>
      </div>
    </div>  
<script type="text/javascript">
	function back(){
		$('body').load('/account/queryAccount');
	}
	
	function notice(sid,username,netSid){
		$('body').load('/notice/toNoticePage',{"sid":sid,"username":username,"netSid":netSid});
	}


</script>
</body></html>