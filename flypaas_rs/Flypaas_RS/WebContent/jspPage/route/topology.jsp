<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.net.URLDecoder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <style type="text/css">
	    .btn-success{color:#fff;text-shadow:0 -1px 0 rgba(0,0,0,0.25);background-color:#51a351;
			background-image:-moz-linear-gradient(top,#62c462,#51a351);
			background-image:-webkit-gradient(linear,0 0,0 100%,from(#62c462),to(#51a351));
			background-image:-webkit-linear-gradient(top,#62c462,#51a351);
			background-image:-o-linear-gradient(top,#62c462,#51a351);
			background-image:linear-gradient(to bottom,#62c462,#51a351);
			background-repeat:repeat-x;border-color:#51a351 #51a351 #387038;
			border-color:rgba(0,0,0,0.1) rgba(0,0,0,0.1) rgba(0,0,0,0.25);
			filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#ff62c462',endColorstr='#ff51a351',GradientType=0);
			filter:progid:DXImageTransform.Microsoft.gradient(enabled=false);
		.btn{display:inline-block;padding:3px 5px;cursor: pointer;font-size:14px;text-align: center;white-space: nowrap;vertical-align: middle;border:1px solid #ccc;border-color:#e6e6e6 #e6e6e6 #b3b3b3 #bfbfbf;*zoom:1;
    </style>
    <title>节点拓扑图</title>  
    <script src="${ctx}/js/jquery-1.8.2.min.js"></script>
</head>
<body>
<div style="text-align: right;">
	<button class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px;"  onclick="javascript:location.replace(location.href);" title="刷新" ><i class="icon-refresh"></i>刷新</button>
</div>
	<div id="main" style="height:820px;"></div>

    <script src="${ctx}/js/echarts.js"></script>
    <script src="${ctx}/js/world.js"></script>
    <script src="${ctx}/js/route/topology.js"></script>
</body>
</html>