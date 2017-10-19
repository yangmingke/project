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
			<label>order_id</label>
			<span>${data.order_id}</span>
		</p>
		<p>
			<label>sid</label>
			<span>${data.sid}</span>
		</p>
		<p>
			<label>charge</label>
			<span>${data.charge} </span>
		</p>
		<p>
			<label>pay_id</label>
			<span>${data.pay_id}</span>
		</p>
		
		<p>
			<label>pay_date</label>
			<span>${data.pay_date}</span>
		</p>
			<h1>
		<a href="javascript:void();" class="back" onclick="history.back()">返 回</a>
	</h1>
	</div>
</div>
      

</body>
</html>