<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
<title>Insert title here</title>
</head>
<body>
<nav class="Hui-breadcrumb"><i class="icon-home"></i> 首页 <span class="c-gray en">&gt;</span> 资源管理<span class="c-gray en">&gt;</span> 节点流量日消耗<a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="icon-refresh"></i>刷新</a></nav>
<div class="pd-20">
	<form method="post" action="${ctx}/resourceAcctController/queryResourceTDC.action">
	<div class="text-c"> 
	  	查询日期：
	    <input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}',maxDate:'%y-%M-%d'})" value="${date}" id="datemin" class="input-text Wdate" style="width:120px;" name="date">
	    <input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d'})" id="datemax" class="input-text Wdate" style="width:120px; display: none;" > 
	    <!-- <input type="text" class="input-text" style="width:250px" placeholder="输入资源IP" id="" name=""> --><button type="submit" class="btn btn-success" id="" name=""><i class="icon-search"></i> 搜索</button>
	</div>
	</form>
	<div class="cl pd-5 bg-1 bk-gray mt-20">
	    <%-- <span class="r">共有数据：<strong>${resourceCount}</strong> 条</span> --%>
	    <span class="r">节点流量日消耗</span>
	</div>
	<table class="table table-border table-bordered table-hover table-bg table-sort" style="font-size: 12px;">
    <thead>
      <tr class="text-c">
        <th style="width: 3%">序号</th>
        <th style="width: 13%">节点IP地址</th>
        <!-- <th style="width: 10%">开始计费时间</th>
        <th style="width: 10%">结束计费时间</th> -->
        <th style="width: 7%">接收流量(KB)</th>
        <th style="width: 7%">发送流量(KB)</th>
        <!-- <th style="width: 10%">总流量(KB)</th> -->
        <th style="width: 10%">单价(元/GB)</th>
        <th style="width: 10%">收入(元)</th>
        <th style="width: 10%">日期</th>
      </tr>
    </thead>
    <tbody><% int i = 1; %>
    <c:forEach var="list" items="${list}">
      <tr class="text-c">
        <td style="width: 3%"><%=i++ %></td>
        <td style="width: 13%">${list.ip}</td>
        <%-- <td style="width: 11%"><fmt:formatDate value="${list.startTime}" pattern="yyyy-MM-dd hh:mm" /></td>
        <td style="width: 11%"><fmt:formatDate value="${list.endTime}" pattern="yyyy-MM-dd hh:mm" /></td> --%>
        <td style="width: 10%">${list.trafficIn}</td>
        <td style="width: 10%">${list.trafficOut}</td>
        <%-- <td style="width: 10%">${list.trafficTotal}</td> --%>
        <td style="width: 10%">${list.price}</td>
        <td style="width: 10%">${list.fee}</td>
        <td style="width: 12%"><fmt:formatDate value="${list.createTime}" pattern="yyyy-MM-dd" /></td>
      </tr>
    </c:forEach>
    </tbody>
    <tbody>
    	<tr class="text-c">
        <td style="width: 3%">总计</td>
        <td style="width: 13%">-</td>
        <%-- <td style="width: 11%"><fmt:formatDate value="${list.startTime}" pattern="yyyy-MM-dd hh:mm" /></td>
        <td style="width: 11%"><fmt:formatDate value="${list.endTime}" pattern="yyyy-MM-dd hh:mm" /></td> --%>
        <td style="width: 10%">${total.trafficIn}</td>
        <td style="width: 10%">${total.trafficOut}</td>
        <%-- <td style="width: 10%">${list.trafficTotal}</td> --%>
        <td style="width: 10%">-</td>
        <td style="width: 10%">${total.fee}</td>
        <td style="width: 12%">${date }</td>
      </tr>
    </tbody>
    </table>
</div>
</body>
<script type="text/javascript" src="${ctx}/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="${ctx}/plugin/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.dataTables.min.js"></script>
<script type="text/javascript">
$('.table-sort').dataTable({
	"bFilter": true,//过滤功能
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


<!-- 用于Ajax请求 -->
<!-- <script type="text/javascript">
	$(function(){
		//var date = $('#date').val();
		var date = "2017-1-11";
		$.ajax({
			url:"/resourceAcctController/queryResourceTDCAjax.action",
			type:"post",
			data:{
				"date":date
			},
			dataType:"json",
			success:function resultMsg(data){
				var json = eval("(" + data + ")")
				$.each(json, function (n, value) {
					var trs = "";
			        trs += "<tr class = 'text-c' >"+
						        "<td>" + Number(n+1)  + "</td>"+
						        "<td>" + value.ip + "</td>"+
						        "<td>"+"<fmt:formatDate value="${value.startTime }" pattern='yyyy-MM-dd' />"+"</td>"+
						        "<td>" + value.endTime + "</td>"+
						        "<td>" + value.trafficIn + "</td>"+
						        "<td>" + value.trafficOut + "</td>"+
						        "<td>" + value.trafficTotal + "</td>"+
						        "<td>" + value.price + "</td>"+
						        "<td>" + value.fee + "</td>"+
						        "<td>" + value.createTime + "</td>"+
			        		"</tr>";
			        $('tbody').append(trs);
			    });	
			}
			
		})
	})
</script> -->
</html>