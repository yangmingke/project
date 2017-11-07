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
    <title> 丢包监控</title>  
    <link rel="stylesheet" href="${ctx}/css/pintuer.css">
    <link rel="stylesheet" href="${ctx}/css/admin.css">
    <script src="${ctx}/js/jquery.js"></script>
    <script src="${ctx}/js/pintuer.js"></script>  
    <script src="${ctx}/plugin/My97DatePicker/WdatePicker.js"></script>  
</head>
<body>
	<ul class="search" style="padding-left:10px;">
          <li>
         	 <input type="text"  value="${date}" placeholder="请选择日期" onfocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})" id="dateTime" class="input-text Wdate" style="width:150px; height:32px; display:inline-block"  name="dateTime">
          </li>
          <a href="javascript:void(0)" class="button border-main icon-search" onclick="queryData()" title="查询"> </a>
      </ul>
	<div id="main" style="height:480px;"></div>

    <script src="${ctx}/js/echarts.min.js"></script>
    <script src="${ctx}/js/nodeConcurrenceMonitoring.js"></script>
</body>
</html>