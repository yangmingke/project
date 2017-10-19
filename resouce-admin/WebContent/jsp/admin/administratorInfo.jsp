<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>管理员信息</title>  
    <link rel="stylesheet" href="${ctx}/css/pintuer.css">
    <link rel="stylesheet" href="${ctx}/css/admin.css">
    <script src="${ctx}/js/jquery.js"></script>
    <script src="${ctx}/js/pintuer.js"></script>  
    <style type="text/css">
		.print{
			font-weight:normal; color:#333;
			padding-bottom:7px;display:block;line-height:20px; color:#202020;
			float:left;width:15%;text-align:left;padding:7px 7px 7px 10px;white-space:nowrap;text-overflow:ellipsis;overflow:hidden;
		}
    </style>
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head"><strong><span class="icon-pencil-square-o"></span> 管理员信息</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="">
      <div class="form-group">
        <div class="label" style="width:40%;">
          <label>管理员SID：</label>
        </div>
        <div class="print">
          <label>${adminSession.sid}</label>
        </div>
      </div>
      <div class="form-group">
         <div class="label" style="width:40%;">
          <label>管理员账号：</label>
        </div>
        <div class="print">
          <label>${adminSession.username}</label>
        </div>
      </div>
      <div class="form-group">
         <div class="label" style="width:40%;">
          <label>管理员名称：</label>
        </div>
        <div class="print">
          <label>${adminSession.realname}</label>
        </div>
      </div>
      <div class="form-group">
         <div class="label" style="width:40%;">
          <label>登陆次数：</label>
        </div>
        <div class="print">
          <label>${adminSession.loginTimes}</label>
        </div>
      </div>
    </form>
  </div>
</div>
</body></html>