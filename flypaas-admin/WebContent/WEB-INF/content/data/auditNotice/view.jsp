<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>查看审核通知时段</title>
</head>

<body>
<div class="main_ctn">
	<h1>
		<a href="javascript:;" class="back" onclick="history.back();">返 回</a>查看审核通知时段
	</h1>
	<div class="admin_material">
		<p>
			<label>通知时段id</label>
			<span>${data.view.notice_id}</span>
		</p>
		<p>
			<label style="width:90px;">通知时段名称</label>
			<span>${data.view.name}</span>
		</p>
		<p>
			<label>时段</label>
			<span>${data.view.start_date}至${data.view.end_date}</span>
		</p>
		<p>
			<label>接收管理员</label>
			<span>${data.view.mobile}</span>
		</p>
		<p>
			<label>更新时间</label>
			<span>${data.view.update_date}</span>
		</p>
		<p>
			<label>状态</label>
			<span>${data.view.status_name}</span>
		</p>
	</div>
</div>
</body>
</html>