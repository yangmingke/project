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
<link rel="stylesheet" type="text/css" href="${ctx}/css/page.css" />
<!--[if IE 7]>
<link href="http://www.bootcss.com/p/font-awesome/assets/css/font-awesome-ie7.min.css" rel="stylesheet" type="text/css" />
<![endif]-->
<title>资源管理</title>
<style type="text/css">
	#div_left{  
		width:200px;
		float:left;
		margin:10px;
		margin-top: 0px;
	}
	#main{
		height:700px;
		width: 85%;
		margin-left: 220px;
		margin-top: 0px;
	}
	#div_top{
		margin-top: 20px;
	}
	#div_top2{
		float: right;
	}
	tr{
		cursor:pointer;
	}
</style>
</head>
<body>
<nav class="Hui-breadcrumb"><i class="icon-home"></i> 首页 <span class="c-gray en">&gt;</span> 资源管理<span class="c-gray en">&gt;</span> 节点流量消耗图<a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="icon-refresh"></i>刷新</a></nav>
<div class="text-c"> 
	<form method="post" action="${ctx}/resourceController/queryAllResourceToStatusPic?page=1">
		<div style="float: left;">
			<input type="text" class="input-text" style="width:200px; " placeholder="输入节点名称或IP" id="IPOrName" name="ipOrName"> <button type="submit" class="btn btn-success" id="searchResource" name="" ><i class="icon-search"></i> 搜资源</button>
		</div>
	</form>
  	 显示日期：
   	<input type="text"  onfocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyyMMdd',maxDate:'%y-%M-%d'})" id="dateTime" class="input-text Wdate" style="width:120px;"  name="dateTime">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   	<input hidden="hidden" id="ip">
<hr>
</div>
<div id="div_left">
	<table class="table table-border table-bordered table-hover table-bg table-sort" style="font-size: 12px; "  >
	 <thead>
      <tr class="text-c" style="height: 45px;">
      	<th>节点名称</th>
      	<th>节点IP地址</th>
      </tr>
     </thead>
     <tbody><% int i = 1; %>
    <c:forEach var="resourceList" items="${resourceList}">
      <tr class="text-t" onclick="searchStatus('${resourceList.ip}')" style="height: 40px;">
      	<td>${resourceList.name}</td>
      	<td>${resourceList.ip}</td>
      </tr>
    </c:forEach>
	</table>
	<div id="pageControl" style="float: left; padding: 5px;">
		<!-- 上一页 按钮 -->
		<c:if test="${page.currentPage != 1}">
			<a href="/resourceController/queryAllResourceToStatusPic?page=${page.currentPage-1}"><input type="button" name="lastPage" value="上一页" /></a>
		</c:if>
		<c:if test="${page.currentPage != page.totalPage}">
			<a href="/resourceController/queryAllResourceToStatusPic?page=${page.currentPage + 1}"><input type="button" name="nextPage" value="下一页"  /></a>&nbsp;
		</c:if>
	</div>
</div>
<div  id="main">
	
</div>
</body>
<script type="text/javascript" src="${ctx}/js/jquery-1.12.3.min.js"></script>
<script type="text/javascript" src="${ctx}/js/page.js"></script>
<script type="text/javascript" src="${ctx}/plugin/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/resource/queryResourceStatus.js"></script>
<script type="text/javascript" src="${ctx}/js/echarts.common.min.js"></script>
<script type="text/javascript">
</script>
</html>