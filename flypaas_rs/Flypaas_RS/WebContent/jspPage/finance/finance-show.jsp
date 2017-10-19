<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<!--[if IE 7]>
<link href="http://www.bootcss.com/p/font-awesome/assets/css/font-awesome-ie7.min.css" rel="stylesheet" type="text/css" />
<![endif]-->
<title>资源方账单管理</title>
<style type="text/css">
	#tb tr{
		line-height: 50px;
	}
	#tb {
		font-size:16px !important;
		border:1px #CCCCCC solid;
	}
</style>
</head>

<body>
<nav class="Hui-breadcrumb"><i class="icon-home"></i> 首页 <span class="c-gray en">&gt;</span> 账务管理<span class="c-gray en">&gt;</span> 账单管理<a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="icon-refresh"></i>刷新</a></nav>
<div class="pd-20">
  <div class="text-c" style="padding-top: 50px;"> 请选择月份：
    <select class="select" name="years" id="yearsId">
    	<option value="0">年份</option>
    	<option value="2015">2015</option>
    	<option value="2016">2016</option>
    	<option value="2017">2017</option>
    	<option value="2018">2018</option>
    	<option value="2019">2019</option>
    	<option value="2020">2020</option>
    	<option value="2021">2021</option>
    	<option value="2022">2022</option>
    	<option value="2023">2023</option>
    	<option value="2024">2024</option>
    	<option value="2025">2025</option>
    </select>
    &nbsp;&nbsp;&nbsp;&nbsp;
    <select class="select" name="mouth" id="mouthId" >
    	<option value="0">月份</option>
    	<option value="01">01</option>
    	<option value="02">02</option>
    	<option value="03">03</option>
    	<option value="04">04</option>
    	<option value="05">05</option>
    	<option value="06">06</option>
    	<option value="07">07</option>
    	<option value="08">08</option>
    	<option value="09">09</option>
    	<option value="10">10</option>
    	<option value="11">11</option>
    	<option value="12">12</option>
    </select>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <button type="button" class="btn btn-success" id="" name="" onclick="searchFinance()"> 查询</button>
    <br/><br/>
    <hr/>
    <table align="center" style="font-size:16px;" id="tb" border="1">
    	<tr>
    		<td>查询日期</td>
    		<td>费用项</td>
    		<td>花费(RMB)</td>
    		<td>平均单价(RMB/G)</td>
    		<td>总数量(个)</td>
    		<td>实际费用(RMB)</td>
    		<td>账单生成时间</td>
    	</tr>
    	<tr>
    		<td></td>
    		<td id="event_name"></td>
    		<td id="fee"></td>
    		<td id="itemfee"></td>
    		<td id="total"></td>
    		<td id="actual_fee"></td>
    		<td id="create_date"></td>
    	</tr>
    </table>
  </div>
	
</div>	
<script type="text/javascript" src="${ctx}/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript">
$(function(){
	var years = $('#yearsId').val();
	var mouth = $('#mouthId').val();
	$.ajax({
		url:"/accountController/queryAccount.action",
		dataType: "json",
		data:{
			"years":years,
			"mouth":mouth
		},
		success:function(data){
			var obj = eval("(" + data + ")");
			$('#event_name').html(obj.eventName)
			$('#fee').html(obj.fee);
			$('#itemfee').html(obj.itemfee);
			$('#total').html(obj.total);
			$('#actual_fee').html(obj.actualFee);
			$('#create_date').html(new Date(obj.createDate).toLocaleString().replace(/年|月/g,'-').replace('日',''));
		}
	});
});
</script>
</body>
</html>