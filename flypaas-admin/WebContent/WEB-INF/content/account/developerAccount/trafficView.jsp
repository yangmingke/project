<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>开发者流量使用情况</title>
</head>

<body>
	<div class="main_search">
        <ul>
         <li class="time">
           	<u:date id="dateTime" value="${data.today}" maxToday="" dateFmt="yyyy-MM-dd"/>
         </li>
          <li>
            <input type="button" value="查 询" class="search" id="search" onclick="queryData('${data.sid}');"/>
            <a class="back" onclick="window.history.go(-1);" style="margin-top: 5px;"/>返回</a>
          </li>
        </ul>
    </div>
    <div class="clear"></div>
    <span style="font-size:16px;" id="totalDayTraffic">日总流量:1000M</span>
    <span style="font-size:16px; padding-left: 5%;" id="totalDayFee">日总费用:1000元</span>
    <div id="main" style="height:480px;"></div>

<script type="text/javascript">
	$(function(){
		queryData('${data.sid}');
	});
</script>
<script type="text/javascript" src="${ctx}/js/echarts.min.js"></script>
<script type="text/javascript" src="${ctx}/js/account/developerAccount/developerTraffic.js"></script>
</body>
</html>