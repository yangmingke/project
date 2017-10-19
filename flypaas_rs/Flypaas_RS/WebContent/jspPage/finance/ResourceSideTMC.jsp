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
<nav class="Hui-breadcrumb"><i class="icon-home"></i> 首页 <span class="c-gray en">&gt;</span> 资源管理<span class="c-gray en">&gt;</span> 资源节点月消耗<a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="icon-refresh"></i>刷新</a><a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:history.go(-1)" title="刷新" ><i class="icon-refresh"></i>返回</a></nav>
<div class="pd-20">
  <form method="post" action="${ctx}/accountSideAcctController/queryResourceSideTMC.action">
	<div class="text-c"> 
	  	查询月份：
	    <input type="text" onfocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyyMM'})" id="datemin" class="input-text Wdate" style="width:120px;" name="date">
	    <input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d'})" id="datemax" class="input-text Wdate" style="width:120px; display: none;" > 
	    <!-- <input type="text" class="input-text" style="width:250px" placeholder="输入资源IP" id="" name=""> --><button type="submit" class="btn btn-success" id="" name=""><i class="icon-search"></i> 搜索</button>
	</div>
  </form>
  <div class="cl pd-5 bg-1 bk-gray mt-20">
    <span class="r">共有数据：<strong></strong> 条</span>
  </div>
  <table class="table table-border table-bordered table-hover table-bg table-sort" style="font-size: 12px;">
    <thead>
      <tr class="text-c">
        <th style="width: 3%"> 序号</th>
        <th style="width: 15%">接收流量(KB)</th>
        <th style="width: 15%">发送流量(KB)</th>
        <th style="width: 15%">总流量(KB)</th>
        <th style="width: 15%">平均单价(RMB/GB)</th>
        <th style="width: 15%">账户收入(RMB)</th>
        <th style="width: 15%">日期</th>
      </tr>
    </thead>
    <tbody><% int i = 1; %>
    <c:forEach var="list" items="${list}">
      <tr class="text-c">
        <td style="width: 3%"><%=i++ %></td>
        <td style="width: 15%">${list.trafficIn}</td>
        <td style="width: 15%">${list.trafficOut}</td>
        <td style="width: 15%">${list.trafficTotal}</td>
        <td style="width: 15%">${list.price}</td>
        <td style="width: 15%">${list.fee}</td>
        <td style="width: 15%"><fmt:formatDate value="${list.createTime}" pattern="yyyy-MM-dd" /></td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <div id="pageNav" class="pageNav"></div>
</div>
<script type="text/javascript" src="${ctx}/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${ctx}/plugin/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.dataTables.min.js"></script>
<script type="text/javascript">
/* window.onload = (function(){
    // optional set
    pageNav.pre="&lt;上一页";
    pageNav.next="下一页&gt;";
    // p,当前页码,pn,总页面
    pageNav.fn = function(p,pn){$("#pageinfo").text("当前页:"+p+" 总页: "+pn);};
    //重写分页状态,跳到第三页,总页33页
    pageNav.go(1,5);
}); */
$('.table-sort').dataTable({
	//"bFilter": true,//过滤功能
	"bPaginate": true,//翻页信息
	"bInfo": true,//数量信息
	"aaSorting": [[ 0, "asc" ]],//默认第几个排序
	"bStateSave": false,//状态保存
	"aoColumnDefs": [
	  //{"bVisible": false, "aTargets": [ 4 ]}, //控制列的隐藏显示
	  //{"orderable":true,"aTargets":[0,8,9]}// 制定列不参与排序
	]
});
</script>
</body>
</html>