<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>查看服务器地址</title>
</head>

<body>
<div class="main_ctn">
	<h1>
		<a href="javascript:;" class="back" onclick="history.back();">返 回</a>查看服务器地址
	</h1>
	<div class="admin_material">
		<p>
			<label>服务器类型</label>
			<span><u:ucparams type="cps_server" key="${param.server}" /></span>
		</p>
		<p>
			<label>id</label>
			<span>${data.id}</span>
		</p>
		<p>
			<label>ip地址</label>
			<span>${data.ipaddress}</span>
		</p>
		<p>
			<label>端口号</label>
			<span>${data.port}</span>
		</p>
		<p>
			<label>描述信息</label>
			<span>${data.describe}</span>
		</p>
		<p>
			<label>创建时间</label>
			<span>${data.createtime}</span>
		</p>
	</div>
</div>
</body>
</html>