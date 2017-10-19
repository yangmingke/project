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
<nav class="Hui-breadcrumb"><i class="icon-home"></i> 首页 <span class="c-gray en">&gt;</span> 资源管理<span class="c-gray en">&gt;</span> 资源节点列表<a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="icon-refresh"></i>刷新</a></nav>
<div class="pd-20">
  <div class="text-c"> 日期范围：
    <input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}'})" id="datemin" class="input-text Wdate" style="width:120px;">
    -
    <input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d'})" id="datemax" class="input-text Wdate" style="width:120px;">
    <input type="text" class="input-text" style="width:250px" placeholder="输入资源名称" id="" name=""><button type="submit" class="btn btn-success" id="" name=""><i class="icon-search"></i> 搜资源</button>

  </div>
  <div class="cl pd-5 bg-1 bk-gray mt-20">
    <span class="r">共有数据：<strong>${resourceCount}</strong> 条</span>
  </div>
  <table class="table table-border table-bordered table-hover table-bg table-sort" style="font-size: 12px;">
    <thead>
      <tr class="text-c">
        <th style="width: 5%">序号</th>
        <th style="width: 10%">资源名称</th>
        <th style="width: 10%">IP</th>
        <th style="width: 8%">定价(分/KB)</th>
        <th style="width: 8%">带宽上限(MB)</th>
        <th style="width: 8%">并发量上线</th>
        <th style="width: 12%">创建时间</th>
        <th style="width: 10%">当前状态</th>
      </tr>
    </thead>
    <tbody><% int i = 1; %>
    <c:forEach var="resourceList" items="${resourceList}">
      <tr class="text-c">
        <td><%= i++ %></td>
        <td>${resourceList.name}</td>
        <td><a href="${ctx}/financeController/queryFinanceByIp.action?ip=${resourceList.ip}">${resourceList.ip}</a></td>
        <td>${resourceList.price}</td>
        <td>${resourceList.bandwidthLimit}</td>
        <td>${resourceList.concurrencyLimit}</td>
        <td><fmt:formatDate value="${resourceList.createDate}" pattern="yyyy-MM-dd" /></td>
        <c:if test="${resourceList.status == 0}">
        	<td class="user-status"><span class="label label-success">正常工作</span></td>
        </c:if>
        <c:if test="${resourceList.status == 1}">
        	<td class="user-status"><span class="label label-warning">审核中...</span></td>
        </c:if>
        <c:if test="${resourceList.status == 2}">
        	<td class="user-status"><span class="label label-secondary">不通过</span></td>
        </c:if>
        <c:if test="${resourceList.status == 3}">
        	<td class="user-status"><span class="label label-danger">故障</span></td>
        </c:if>
        <c:if test="${resourceList.status == 4}">
        	<td class="user-status"><span class="label label-info">暂停</span></td>
        </c:if>
        <c:if test="${resourceList.status == 5}">
        	<td class="user-status"><span class="label label-inverse">下线</span></td>
        </c:if>
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
	//"lengthMenu":false,//显示数量选择 
	"bFilter": true,//过滤功能
	"bPaginate": true,//翻页信息
	"bInfo": true,//数量信息
	"aaSorting": [[ 1, "desc" ]],//默认第几个排序
	"bStateSave": true,//状态保存
	"aoColumnDefs": [
	  {"bVisible": true, "aTargets": [ 0 ]}, //控制列的隐藏显示
	  {"orderable":true,"aTargets":[0,7]}// 制定列不参与排序
	]
});
</script>
</body>
</html>