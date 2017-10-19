<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="http://libs.useso.com/js/html5shiv/3.7/html5shiv.min.js"></script>
<script type="text/javascript" src="http://libs.useso.com/js/respond.js/1.4.2/respond.min.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/css3pie/2.0beta1/PIE_IE678.js"></script>
<![endif]-->
<link type="text/css" rel="stylesheet" href="${ctx}/css/H-ui.css"/>
<link type="text/css" rel="stylesheet" href="${ctx}/css/H-ui.admin.css"/>
<link type="text/css" rel="stylesheet" href="${ctx}/font/font-awesome.min.css"/>
<link type="text/css" rel="stylesheet" href="${ctx}/css/1.0.8/iconfont.css"/>
<!--[if IE 7]>
<link href="http://www.bootcss.com/p/font-awesome/assets/css/font-awesome-ie7.min.css" rel="stylesheet" type="text/css" />
<![endif]-->
<title>资源管理</title>
</head>
<body>
<nav class="Hui-breadcrumb"><i class="icon-home"></i> 首页 <span class="c-gray en">&gt;</span> 资源管理<span class="c-gray en">&gt;</span> 节点实时状态<a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="icon-refresh"></i>刷新</a></nav>
<div class="pd-20">
  <form action="${ctx}/flushRedisController/queryRedisList.action" method="post">
	  <div class="text-c"> 
	    <input type="text" class="input-text" style="width:250px" placeholder="输入节点IP地址" id="" name="ip" value="${ip }"><button type="submit" class="btn btn-success" id="" name=""><i class="icon-search"></i> 搜资源</button>
	  </div><%-- ${ctx}/jspPage/resource/resource-add.jsp --%>
  </form>
  <div class="cl pd-5 bg-1 bk-gray mt-20">
    <span class="r">共有数据：<strong>${resourceCount}</strong> 条</span>
  </div>
  <table class="table table-border table-bordered table-hover table-bg table-sort" style="font-size: 12px;">
    <thead>
      <tr class="text-c">
       <th style="width: 5%">序号</th>
        <th style="width: 10%">节点名称</th>
        <th style="width: 15%">节点IP地址</th>
        <th style="width: 15%">状态</th>
        <th style="width: 15%">并发量</th>
        <th style="width: 20%">接收流量(KB/S)</th>
        <th style="width: 20%">发送流量(KB/S)</th>
      </tr>
    </thead>
    <tbody><%int i = 1; %>
    <c:forEach var="redisList" items="${redisList}">
  		<tr class="text-c">
  			<td style="width: 5%"><%=i++ %></td>
  			<td style="width: 10%">${redisList.name}</td>
  			<td style="width: 15%">${redisList.ip}</td>
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
			   	<td class="user-status" style="width: 15%"><span class="label label-inverse">离线状态</span></td>
			</c:if>
			<c:if test="${redisList.status == 6}">
			   	<td class="user-status" style="width: 15%"><span class="label label-success">在线状态</span></td>
			</c:if>
  			<td style="width: 15%">${redisList.concurrency}</td>
  			<td style="width: 20%">${redisList.trafficIn}</td>
  			<td style="width: 20%">${redisList.trafficOut}</td>
  		</tr>
  	</c:forEach>
    </tbody>
  </table>
  </div>
<!--   <div id="pageNav" class="pageNav"></div> -->
<script type="text/javascript" src="${ctx}/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/H-ui.js"></script>
<script type="text/javascript" src="${ctx}/js/H-ui.admin.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${ctx}/js/pagenav.cn.js"></script>
<script type="text/javascript">
$('.table-sort').dataTable({
	//"lengthMenu":false,//显示数量选择 
	"bFilter": true,//过滤功能
	"bPaginate": true,//翻页信息
	"bInfo": true,//数量信息
	"aaSorting": [[ 1, "desc" ]],//默认第几个排序
	"bStateSave": true,//状态保存
	"aoColumnDefs": [
	  //{"bVisible": true, "aTargets": [ 5 ]}, //控制列的隐藏显示
	  //{"orderable":true,"aTargets":[0,8,9]}// 制定列不参与排序
	]
});
</script>
</body>
</html>