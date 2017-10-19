<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>查看操作日志</title>
</head>

<body>
<div class="main_ctn">
	<h1>
		<a href="javascript:;" class="back" onclick="history.back();">返 回</a>查看操作日志
	</h1>
	<div class="admin_material">
		<p>
			<label>操作日志id</label>
			<span>${data.log_id}</span>
		</p>
		<p>
			<label>管理员id</label>
			<span>${data.sid}</span>
		</p>
		<p>
			<label>管理员账号</label>
			<span>${data.email}</span>
		</p>
		<p>
			<label>真实姓名</label>
			<span>${data.realname}</span>
		</p>
		<p>
			<label>管理员ip</label>
			<span>${data.ip}</span>
		</p>
		<p>
			<label>操作类型</label>
			<span><u:ucparams key="${data.op_type}" type="op_type" /></span>
		</p>
		<p>
			<label>请求url</label>
			<span>${data.page_url}</span>
		</p>
		<p>
			<label>详情</label>
			<span><s:property value="data.op_desc"/></span>
		</p>
		<p>
			<label>创建时间</label>
			<span>${data.create_date}</span>
		</p>
	</div>
</div>
</body>
</html>