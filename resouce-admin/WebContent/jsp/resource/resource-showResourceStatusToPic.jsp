<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.net.URLDecoder" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title></title>  
    <link rel="stylesheet" href="${ctx}/css/pintuer.css">
    <link rel="stylesheet" href="${ctx}/css/admin.css">
    <script src="${ctx}/js/jquery.js"></script>
    <script src="${ctx}/js/pintuer.js"></script>  
    <script src="${ctx}/plugin/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<form method="post" action="">
  <input type="text" value="<%= request.getParameter("ip")%>" id="ip" style="display: none;"/>
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 资源节点状态变化图  &nbsp;(<a href="##" onclick="javascript:void(0)"><%= request.getParameter("ip")%></a>)</strong></div>
    <div class="padding border-bottom">
      <ul class="search">
        <li>
        	时间筛选：
        </li>
        <li>
	      <input type="text"  placeholder="选择日期" onfocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyyMMdd'})" id="dateTime" class="input-text Wdate" style="width:120px;height: 41px;" name="dateMax">
		  &nbsp;&nbsp;&nbsp;&nbsp;
	      <a href="javascript:void(0)" class="button border-main icon-search" onclick="showResourceStatusToPic()" > 查询</a>
        </li>
      </ul>
    </div>
    <div id="main" style="width: 100%;height: 500px;">
    	
    </div>
  </div>
</form>
<script type="text/javascript">
	$(function(){
		$('#dateTime').val(nowtime());
		showResourceStatusToPic();
	});
	function showResourceStatusToPic(){
		var ip = $('#ip').val();
		var dateTime = $('#dateTime').val();
		searchStatus(ip,dateTime);
	}
	function nowtime(){//将当前时间转换成yyyymmdd格式
	    var mydate = new Date();
	    var str = "" + mydate.getFullYear();
	    var mm = mydate.getMonth()+1
	    if(mydate.getMonth()>9){
	     str += mm;
	    }
	    else{
	     str += "0" + mm;
	    }
	    if(mydate.getDate()>9){
	     str += mydate.getDate();
	    }
	    else{
	     str += "0" + mydate.getDate();
	    }
	    return str;
	  }

</script>
<script type="text/javascript" src="${ctx}/js/resource/echarts.min.js"></script>
<script type="text/javascript" src="${ctx}/js/resource/resourceStatusToPic.js"></script>
</body>
</html>