<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>client账务明细</title>
</head>

<body>
<div class="main_ctn">


	<h1>client账单</h1>
	<div class="admin_material">

		<p>
			<label>client号码</label>
			<span>${data.client_number}</span>
		</p>
		<p>
			<label>余额</label>
			<span>${data.balance}</span>
		</p>
		<p>
			<label>最后操作时间</label>
			<span>${data.update_date} </span>
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