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
<nav class="Hui-breadcrumb"><i class="icon-home"></i> 首页 <span class="c-gray en">&gt;</span> 财务管理<span class="c-gray en">&gt;</span> 提款记录<a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="icon-refresh"></i>刷新</a></nav>
<div class="pd-20">
<form action="${ctx}/billFlowController/queryAllBillFlowBynetSid.action" method="post">
  <div class="text-c"> 日期范围：
    <input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}'})" id="datemin" name="datemin" class="input-text Wdate" style="width:120px;" value="${datemin }"> -
    <input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d'})" id="datemax" name="datemax" class="input-text Wdate" style="width:120px;" value="${datemax }">
  	<button type="submit" class="btn btn-success" id="" name=""><i class="icon-search"></i> 搜账单</button>
  </div>
</form>
  <div class="cl pd-5 bg-1 bk-gray mt-20">
    <span class="r">共有数据：<strong></strong> 条</span>
  </div>
  <table class="table table-border table-bordered table-hover table-bg table-sort" style="font-size: 12px;">
    <thead>
      <tr class="text-c">
        <th style="width: 5%">流水号</th>
        <th style="width: 7%">操作人</th>
        <th style="width: 5%">提款金额(元)</th>
        <th style="width: 5%">提款后余额(元)</th>
        <th style="width: 10%">收款账号(支付宝)</th>
        <th style="width: 10%">收款人(支付宝)</th>
        <th style="width: 5%">状态</th>
        <th style="width: 17%">备注</th>
        <th style="width: 5%">创建日期</th>
        <th style="width: 10%">操作</th>
      </tr>
    </thead>
    <tbody><% int i = 1; %>
    <c:forEach var="list" items="${list}">
      <tr class="text-c">
        <td style="width: 5%">${list.id}</td>
        <td style="width: 10%">${list.operUser}</td>
        <td style="width: 10%;text-align: right;">${list.actualFee}.00</td>
        <td style="width: 10%;text-align: right;">${list.balanceShow}</td>
  		<td style="width: 10%">${list.alipayAccount}</td>
        <td style="width: 10%">${list.alipayName}</td>
        <c:if test="${list.status == 0}">
        	<td style="width: 10%">审核中...</td>
        </c:if>
        <c:if test="${list.status == 1}">
        	<td style="width: 10%"><font color="green">审核通过</font></td>
        </c:if>
        <c:if test="${list.status == 2}">
        	<td style="width: 10%"><font color="red">审核未通过</font></td>
        </c:if>
        <c:if test="${list.status == 3}">
        	<td style="width: 10%"><font color="red">作废</font></td>
        </c:if>
        <c:if test="${list.status == 4}">
        	<td style="width: 10%"><font color="green">已转账</font></td>
        </c:if>
        <td style="width: 17%">${list.demo}</td>
        <td style="width: 10%"><fmt:formatDate value="${list.createDate}" pattern="yyyy-MM-dd" /></td>
        <td style="width: 10%">
         	<c:if test="${list.status == 0}">
        		<a style="text-decoration:none" onclick="payOrder_del(this,'${list.id}')" title="作废" href="javascript:;"  class="ml-5" ><font color="red">取消申请</font></a>
        	</c:if>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <div id="pageNav" class="pageNav"></div>
</div>
<script type="text/javascript" src="${ctx}/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/H-ui.admin.js"></script>
<script type="text/javascript" src="${ctx}/layer/layer.min.js"></script>
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
	"aaSorting": [[ 0, "desc" ]],//默认第几个排序
	"bStateSave": true,//状态保存
	"aoColumnDefs": [
	  //{"bVisible": false, "aTargets": [ 2 ]} //控制列的隐藏显示
	  //{"orderable":true,"aTargets":[0,8,9]}// 制定列不参与排序
	]
});
</script>
</body>
</html>