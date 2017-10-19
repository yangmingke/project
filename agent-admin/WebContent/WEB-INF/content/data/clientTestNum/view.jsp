<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>管理员资料</title>
</head>

<body>
<div class="main_ctn">


	<h1>管理员资料</h1>
	<div class="admin_material">
	       <p>
			<label>用户ID</label>
			<span>${data.sid}</span>
		</p>
		<p>
			<label>client号码</label>
			<span>${data.client_number}</span>
		</p>
		<p>
			<label>app</label>
			<span>${data.app_sid}</span>
		</p>
		<p>
			<label>创建时间</label>
			<span>${data.create_date} </span>
		</p>
		<p>
			<label>电话</label>
			<span>${data.mobile}</span>
		</p>
		
		
	<h1>
		<a href="javascript:void();" class="back" onclick="history.back()">返 回</a>
	</h1>
	</div>
</div>
      

</body>
</html>