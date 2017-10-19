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
<link rel="stylesheet" type="text/css" href="${ctx}/css/page.css" />
<title>资源管理</title>
</head>
<body>
	<div id="pageControl">
		<!-- 上一页 按钮 -->
		<c:if test="${page.currentPage != 1}">
			<a href="/demoController/queryDemo?page=${page.currentPage-1}"><input type="button" name="lastPage" value="上一页" /></a>
		</c:if>
		<!-- 页数列表 -->
		<c:forEach items="${page.pageList}" var="item">
			<c:if test="${page.currentPage != item}">
			<a href="/demoController/queryDemo?page=${item}" >${item}</a>
			</c:if>
			<c:if test="${page.currentPage == item}">
				<a class="currentPage">${item}</a>
			</c:if>
		</c:forEach>
		<!-- 下一页 按钮 -->
		<c:if test="${page.currentPage != page.totalPage}">
			<a href="/demoController/queryDemo?page=${page.currentPage + 1}"><input type="button" name="nextPage" value="下一页" /></a>
		</c:if>
		<!-- 直接跳转 -->
		共${page.totalPage}页  第<input type="text" id="jumpTo" />页 <input type="button" value="跳转" onclick="jumpTo(${page.totalPage})" />
	</div>
	<script type="text/javascript" src="${ctx}/js/jquery-1.12.3.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/page.js"></script>
</body>
</html>