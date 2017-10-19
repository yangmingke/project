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
			<label>平台余额</label>
			<span>${data.balance}元</span>
		</p>
		<p>
			<label>联系手机</label>
			<span>${data.mobile}</span>
		</p>
		<p>
			<label>真实姓名</label>
			<span>${data.realname}</span>
		</p>
		<p>
             <label>开发者登录URL</label>
             <span>${data.agent_url}</span>
        </p>
		<hr class="hr" />
		<p class="btn">
 			<input type="button" value="修 改" class="org_btn" onclick="edit('${data.sid}')"/>
 			<input type="button" value="充值" class="org_btn" onclick="charge('${data.sid}')"/>
 			<%-- <input type="button" value="更换logo" class="org_btn" onclick="changeLogo('${data.sid}')"/> --%>
		</p>
	</div>
</div>
      
<script type="text/javascript">
function edit(sid){
	location.href="${ctx}/admin/editForSelf?sid=" + sid;
}
function charge(sid){
	location.href="${ctx}/admin/charge?sid=" + sid;
}
function changeLogo(sid){
	location.href="${ctx}/admin/changeLogo?sid=" + sid;
}
</script>
</body>
</html>