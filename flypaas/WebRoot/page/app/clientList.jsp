<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="u" uri="/flypaas-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>flypaas开放平台管理中心——应用列表</title>
<%@include file="/page/resource.jsp"%>
<script type="text/javascript" src="<%=path%>/page/js/app.js"></script>
</head>
<body id="02-1">
	<!--头部header bof-->
	<%@include file="/page/head.jsp"%>
	<!--头部header eof-->

	<!--主体content bof-->
	<div class="content">

		<!--侧边side bof-->
		<%@include file="/page/left.jsp"%>
		<!--侧边side bof-->

		<!--右侧main bof-->
		<div class="main">
			<div class="breadcrumbs">
				<ul>
					<li><a href="<%=path %>/app/appManager">应用管理</a></li>
					<li class="active"><a href="<%=path%>/app/appManager">应用列表</a></li>
				</ul>
			</div>
			<!--搜索search_box bof-->
			<div class="search_box">
				<div class="back_button"><a href="<%=path%>/app/appManager">应用列表</a></div>
				<s:form namespace="/app" theme="simple"  action="clientList" method="post" name="appform" id="appform"  >
					<input type="hidden" id="appSid" name="appSid" value="${appSid}">
					<input type="hidden" id="currentPage" name="page.currentPage"/>
				</s:form>
			</div>

			<!--表格列表 table_box bof-->
			<div class="item_box demo_list">
			<div class="table_box">
				<table cellpadding="0" cellspacing="0" border="0">
					<thead>
						<tr>
							<th style="text-decoration: none;text-align:center;">序号</th>
							<th style="text-decoration: none;text-align:center;">SDKID</th>
							<th style="text-decoration: none;text-align:center;">更新时间</th>
							<th style="text-decoration: none;text-align:center;">创建时间</th>
						</tr>
					</thead>
					<tbody>
						<s:if test="page!=null&&page.list.size()>0">
							<s:iterator value="page.list" var="client" status="index">
								<tr>
									<td style="text-decoration: none;text-align:center;">${index.index+1 }</td>
									<td style="text-decoration: none;text-align:center;">${client.client_number }</td>
									<td style="text-decoration: none;text-align:center;"> <fmt:formatDate value="${client.update_date }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
									<td style="text-decoration: none;text-align:center;"><fmt:formatDate value="${client.create_date }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
								</tr>
							</s:iterator>
						</s:if>
					</tbody>
				</table>
			</div></div>
			<u:page page="${page}" formId="appform"></u:page>
		</div>
		<!--右侧main bof-->

	</div>
	<!--主体content eof-->


	<!--底部footer bof-->
	<%@include file="/page/foot.jsp"%>
	<!--底部footer eof-->


	<script type="text/javascript">
	</script>
</body>
</html>