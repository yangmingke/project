<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>充值日志</title>
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
			<label>账单id</label>
			<span>${data.pay_id}</span>
		</p>
		
		<p>
			<label>付款时间</label>
			<span>${data.pay_date}</span>
		</p>
		<p>
			<label>出账时间</label>
			<span>${data.pay_result_date}</span>
		</p>
		 <p>
			<label>pptid</label>
			<span>${data.ppt_id}</span>
		</p>
		   	<h1>
		<a href="javascript:void();" class="back" onclick="history.back()">返 回</a>
	</h1>
	</div>
</div>
      

</body>
</html>