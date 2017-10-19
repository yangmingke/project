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
<link rel="stylesheet" type="text/css" href="${ctx}/css/page.css" />
<!--[if IE 7]>
<link href="http://www.bootcss.com/p/font-awesome/assets/css/font-awesome-ie7.min.css" rel="stylesheet" type="text/css" />
<![endif]-->
<title>首页</title>
<style type="text/css">
*{ margin:0; padding:0; list-style:none;}
a{ text-decoration:none;}
a:hover{ text-decoration:none;}
.tcdPageCode{padding: 15px 20px;text-align: left;color: #ccc;text-align:center;}
.tcdPageCode a{display: inline-block;color: #428bca;display: inline-block;height: 25px;	line-height: 25px;	padding: 0 10px;border: 1px solid #ddd;	margin: 0 2px;border-radius: 4px;vertical-align: middle;}
.tcdPageCode a:hover{text-decoration: none;border: 1px solid #428bca;}
.tcdPageCode span.current{display: inline-block;height: 25px;line-height: 25px;padding: 0 10px;margin: 0 2px;color: #fff;background-color: #428bca;	border: 1px solid #428bca;border-radius: 4px;vertical-align: middle;}
.tcdPageCode span.disabled{	display: inline-block;height: 25px;line-height: 25px;padding: 0 10px;margin: 0 2px;	color: #bfbfbf;background: #f2f2f2;border: 1px solid #bfbfbf;border-radius: 4px;vertical-align: middle;}

</style>
</head>
<body>
<nav class="Hui-breadcrumb"><i class="icon-home"></i> 首页 <span class="c-gray en">&gt;</span> 日志管理<span class="c-gray en">&gt;</span> 日志列表<a class="btn btn-success radius r mr-20" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="icon-refresh"></i>刷新</a></nav>
<div class="pd-20" style="padding-top:20px;">
	<form method="post" action="${ctx}/logController/queryLog.action">
		<div class="text-c"> 日期范围：
		    <input type="text" onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'datemax\')||\'%y-%M-%d\'}'})" id="datemin" class="input-text Wdate" style="width:120px;" name="dateMin" value="${dateMin }">
		    -
		    <input type="text" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'datemin\')}',maxDate:'%y-%M-%d'})" id="datemax" class="input-text Wdate" style="width:120px;" name="dateMax" value="${dateMax }">
		    <button type="submit" class="btn btn-success" id="" name=""><i class="icon-search"></i> 搜日志</button>
		</div>
	</form>
<div class="cl pd-5 bg-1 bk-gray mt-20">
    <span class="l"><a href="javascript:;" class="btn btn-danger radius"><i class="icon-trash"></i> 打印日志</a></span>
    <span class="r">共有数据：<strong id="count">${count}</strong> 条</span>
</div>
  <table  class="table table-border table-bordered table-hover table-bg table-sort" style="font-size: 12px;">
    <thead>
      <tr class="text-c">
      	<th style="width: 3%">序号</th>
      	<th style="width: 32%">访问地址</th>
        <th style="width: 10%">操作类型</th>
        <th style="width: 10%">操作内容</th>
        <th style="width: 15%">访问者IP</th>
        <th style="width: 10%">操作人员</th>
        <th style="width: 15%">操作时间</th>
      </tr>
    </thead>
    <tbody id="dataTable">
    <%int i=1; %>
    <c:forEach var ="logList" items="${logList}">
      <tr class="text-c">
      	<td>${logList.logId}</td>
      	<td>${logList.pageUrl}</td>
        <td>${logList.opType}</td>
        <td>${logList.opDesc}</td>
        <td>${logList.ip}</td>
        <td>${logList.username}</td>
        <td><fmt:formatDate value="${logList.createDate}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
      </tr>
    </c:forEach>
    
    </tbody>
  </table>
  <div id="pageControl" style="float: right">
		<!-- 上一页 按钮 -->
		<c:if test="${page.currentPage != 1}">
			<a href="/logController/queryLog?page=${page.currentPage-1}"><input type="button" name="lastPage" value="上一页" /></a>
		</c:if>
		<!-- 页数列表 -->
		<c:forEach items="${page.pageList}" var="item">
			<c:if test="${page.currentPage != item}">
			<a href="/logController/queryLog?page=${item}" >${item}</a>
			</c:if>
			<c:if test="${page.currentPage == item}">
				<a class="currentPage">${item}</a>
			</c:if>
		</c:forEach>
		<!-- 下一页 按钮 -->
		<c:if test="${page.currentPage != page.totalPage}">
			<a href="/logController/queryLog?page=${page.currentPage + 1}"><input type="button" name="nextPage" value="下一页" /></a>&nbsp;
		</c:if>
		<!-- 直接跳转 -->
		共&nbsp;${page.totalPage}&nbsp;页  第<input type="text" id="jumpTo" />页 <input type="button" value="跳转" onclick="jumpTo(${page.totalPage})" />
	</div>
</div>
<script type="text/javascript" src="${ctx}/js/page.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${ctx}/layer/layer.min.js"></script>
<script type="text/javascript" src="${ctx}/js/pagenav.cn.js"></script>
<script type="text/javascript" src="${ctx}/js/H-ui.admin.js"></script>
<script type="text/javascript" src="${ctx}/js/H-ui.js"></script>
<script type="text/javascript" src="${ctx}/plugin/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.dataTables.min.js"></script> 
<!-- <script type="text/javascript">
/*  window.onload = (function(){
    // optional set
    pageNav.pre="&lt;上一页";
    pageNav.next="下一页&gt;";
    // p,当前页码,pn,总页面
    pageNav.fn = function(p,pn){$("#pageinfo").text("当前页:"+p+" 总页: "+pn);};
    //重写分页状态,跳到第三页,总页33页
    var pageNum = $('#pageNum').val();
    pageNav.go(1,pageNum);
}); */
 $('.table-sort').dataTable({
	//"lengthMenu":false,//显示数量选择 
	"bFilter": false,//过滤功能
	"bPaginate": false,//翻页信息
	"bInfo": false,//数量信息
	"aaSorting": [[ 1, "desc" ]],//默认第几个排序
	"bStateSave": true,//状态保存
	"aoColumnDefs": [
	  //{"bVisible": true, "aTargets": [ 5 ]}, //控制列的隐藏显示
	  //{"orderable":true,"aTargets":[0,8,9]}// 制定列不参与排序
	]
}); 
</script> -->

</body>
</html>