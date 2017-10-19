<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>查看音频驱动适配异常</title>
</head>

<body>
<div class="main_ctn">
	<h1>
		<a href="javascript:;" class="back" onclick="history.back();">返 回</a>查看音频驱动适配异常
	</h1>
	<div class="admin_material">
		<p>
			<label>id</label>
			<span>${data.id}</span>
		</p>
		<p>
			<label>平台信息</label>
			<span>${data.pv}</span>
		</p>
		<p>
			<label>版本信息</label>
			<span>${data.app_ver}</span>
		</p>
		<p>
			<label>客户端SDK版本</label>
			<span>${data.ver}</span>
		</p>
		<p>
			<label>系统版本</label>
			<span>${data.system_ver}</span>
		</p>
		<p>
			<label>系统API LEVEL</label>
			<span>${data.api_level}</span>
		</p>
		<p>
			<label>手机品牌</label>
			<span>${data.phonebrand}</span>
		</p>
		<p>
			<label>型号</label>
			<span>${data.phonemodel}</span>
		</p>
		<p>
			<label>imei</label>
			<span>${data.imei}</span>
		</p>
		<p>
			<label>mac</label>
			<span>${data.mac}</span>
		</p>
		<p>
			<label>创建时间</label>
			<span>${data.createtime}</span>
		</p>
		<p>
			<label>更新时间</label>
			<span>${data.updatetime}</span>
		</p>
	</div>
</div>
</body>
</html>