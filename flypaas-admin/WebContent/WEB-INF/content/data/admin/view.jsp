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
			<label>管理员身份</label>
			<span>${data.role_name}</span>
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
			<label>联系手机</label>
			<span>${data.mobile}</span>
		</p>
		<u:authority menuId="31">
			<hr class="hr" />
			<%-- <p class="btn">
	 			<input type="button" value="修 改" class="org_btn" onclick="edit('${data.sid}')"/>
			</p> --%>
		</u:authority>
	</div>
</div>
      
<script type="text/javascript">
function edit(sid){
	location.href="${ctx}/admin/editForSelf?sid=" + sid;
}
</script>
</body>
</html>