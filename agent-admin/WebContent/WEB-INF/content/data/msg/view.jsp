<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>查看消息通知</title>
</head>

<body>
<div class="main_ctn">
	<h1>
		<a href="javascript:;" class="back" onclick="back();">返 回</a>查看消息通知
	</h1>
	<div class="admin_material">
		<p>
			<label>用户ID</label>
			<span>${data.sid}</span>
		</p>
		<p>
			<label>用户名</label>
			<span>${data.email}</span>
		</p>
		<p>
			<label>消息id</label>
			<span>${data.msg_id}</span>
		</p>
		<p>
			<label>消息类型</label>
			<span><u:ucparams key="${data.msg_type}" type="msg_type" /></span>
		</p>
		<p>
			<label>标题</label>
			<span>${data.msg_title}</span>
		</p>
		<p>
			<label>内容</label>
			<span>${data.msg_desc}</span>
		</p>
		<p>
			<label>创建时间</label>
			<span>${data.create_date}</span>
		</p>
		<p>
			<label>是否已读</label>
			<span><u:ucparams key="${data.hasread}" type="msg_hasread" /></span>
		</p>
	</div>
</div>
</body>
</html>