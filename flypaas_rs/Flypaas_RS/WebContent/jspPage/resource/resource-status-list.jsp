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
<title>资源节点状态</title>
</head>
<body>
<nav class="Hui-breadcrumb"><i class="icon-home"></i> 首页 <span class="c-gray en">&gt;</span> 资源管理<span class="c-gray en">&gt;</span> 资源节点状态<a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="icon-refresh"></i>刷新</a><a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:history.go(-1)" title="刷新" ><i class="icon-refresh"></i>返回</a></nav>
<div class="pd-20">
  <div class="text-c"> 日期范围：
    <input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}'})" id="datemin" class="input-text Wdate" style="width:120px;">
    -
    <input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d'})" id="datemax" class="input-text Wdate" style="width:120px;">
    <input type="text" class="input-text" style="width:250px;display: none;" placeholder="输入资源名称/IP" id="ip" name="" value="${ip}">
    <button type="button" class="btn btn-success" id="" name="" onclick="searchResource()"><i class="icon-search"></i> 搜资源</button>
  </div>
  <div class="cl pd-5 bg-1 bk-gray mt-20">
    <span class="l"><a href="javascript:;" class="btn btn-danger radius"><i class="icon-trash"></i> 导出节点状态记录表</a></span>
    <span class="r">共有数据：<strong></strong> 条</span>
  </div>
  <table class="table table-border table-bordered table-hover table-bg table-sort" style="font-size: 12px;">
    <thead>
      <tr class="text-c">
        <th style="width: 3%">序号</th>
        <th style="width: 7%">ID</th>
        <th style="width: 15%">时间</th>
        <th style="width: 15%">IP</th>
        <th style="width: 10%">名称</th>
        <th style="width: 10%">并发量</th>
        <th style="width: 10%">入流量(KB)</th>
        <th style="width: 10%">出流量(KB)</th>
        <th style="width: 10%">流入吞吐率(KB/S)</th>
        <th style="width: 10%">流出吞吐率(KB/S)</th>
      </tr>
    </thead>
    <tbody><% int i = 1; %>
    <c:forEach var="list" items="${list}">
      <tr class="text-c">
        <td ><%=i++ %></td>
        <td >${list.id}</td>
        <td ><fmt:formatDate value="${list.time}" pattern="yyyy-MM-dd hh:mm" /></td>
        <td >${list.ip}</td>
        <td >${list.name}</td>
        <td >${list.concurrency}</td>
        <td >${list.trafficIn}</td>
        <td >${list.trafficOut}</td>
        <td >${list.throughputIn}</td>
        <td >${list.throughputOut}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <div id="pageNav" class="pageNav"></div>
</div>
<script type="text/javascript" src="${ctx}/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${ctx}/layer/layer.min.js"></script>
<script type="text/javascript" src="${ctx}/js/pagenav.cn.js"></script>
<script type="text/javascript" src="${ctx}/js/H-ui.js"></script>
<script type="text/javascript" src="${ctx}/js/H-ui.admin.js"></script>
<script type="text/javascript" src="${ctx}/plugin/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.dataTables.min.js"></script>
<script type="text/javascript">
window.onload = (function(){
    // optional set
    pageNav.pre="&lt;上一页";
    pageNav.next="下一页&gt;";
    // p,当前页码,pn,总页面
    pageNav.fn = function(p,pn){$("#pageinfo").text("当前页:"+p+" 总页: "+pn);};
    //重写分页状态,跳到第三页,总页33页
    pageNav.go(1,5);
});
$('.table-sort').dataTable({
	//"bFilter": true,//过滤功能
	"bPaginate": true,//翻页信息
	"bInfo": true,//数量信息
	"aaSorting": [[ 1, "desc" ]],//默认第几个排序
	"bStateSave": false,//状态保存
	"aoColumnDefs": [
	  {"bVisible": false, "aTargets": [ 1 ]}, //控制列的隐藏显示
	  {"orderable":true,"aTargets":[0,8,9]}// 制定列不参与排序
	]
});
</script>
</body>
</html>