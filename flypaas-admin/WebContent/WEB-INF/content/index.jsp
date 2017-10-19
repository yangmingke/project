<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>登录页面</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/css/a_log.css" />
	<script type="text/javascript" src="${ctx}/js/cookie.js"></script>
	<script type="text/javascript" src="${ctx}/js/hex_ha_ha_ha_ha_ha.js"></script>
</head>

<body>
<!--登录 bof-->
<div class="log_box">
	<div class="log_ctn">
		<div class="log_inner">
			<h1>登录</h1>
			<form method="post" id="loginForm" onsubmit="return false;">
				<p><input type="text" id="username" name="username"  placeholder="用户名" /></p>
				<p><input type="password" id="passwordInput" placeholder="请输入密码" /></p>
				<p><input type="text" id="randCheckCode" name="randCheckCode" placeholder="请输入验证码" style="width: 220px;" />
				<img alt="验证码" id="randCheckCode_img" src="${ctx}/checkCode" onclick="$(this).attr('src','${ctx}/checkCode?' + Math.random())"/>
				</p>
				<p><input type="checkbox" id="remember" /> 记住登录名</p>
				<p><span id="errorInfo" class="error" style="display:none;"></span></p>
				<p class="btn"><input type="submit" value="登 录" onclick="login(this)"/></p>
				<input type="hidden" id="password" name="password"/>
			</form>
		</div>
	</div>
</div>

<div class="log_footer">
	<p><a href="#" title="关于我们">关于我们</a><span>|</span><a href="#" title="联系我们">联系我们</a><span>|</span><a href="#" title="友情合作">友情合作</a><span>|</span><a href="#" title="渠道商接入">渠道商接入</a><span>|</span><a href="#" title="关注我们">关注我们</a><span>|</span><a href="#" title="网站地图">网站地图</a><span>|</span><a href="#" title="最新资讯">最新资讯</a><span>|</span><a href="#" title="接口文档">接口文档</a></p>
	<p>&copy; Copyright 2013快传平台 XXX.com</p>
	<p>All Rights Reserved 深圳市快传技术有限公司</p>
</div>
<!--登录 bof-->

<script type="text/javascript">
	$(function(){
		var username = getcookie("login_username");
		if($.trim(username) !=""){
			$("#username").val(username);
			$("#remember").click();
		}
	});
	
	function login(btn){
		var username = $.trim($("#username").val());
		var password = $.trim($("#passwordInput").val());
		var randCheckCode =  $.trim($("#randCheckCode").val());
		var error = [];
		if(username==""){
			error.push("用户名不能为空");
		}
		if(password==""){
			error.push("密码不能为空");
		}
		if(randCheckCode==""){
			error.push("验证码不能为空");
		}
		
		if(error!=""){
			$("#errorInfo").html(error.join("，")).show();
			return;
		}
		$("#errorInfo").hide();
		
		var options = {
			beforeSubmit : function() {
				$(btn).attr("disabled", true);
			},
			success : function(data) {
				$(btn).attr("disabled", false);
				
				if(data.result==null){
					$("#errorInfo").html("服务器错误，请联系管理员").show();
					return;
				}
				
				if(data.result=="isLogin"){ // 已登录
					$("#errorInfo").html(data.msg).show();
					window.setTimeout(function(){
						location.href=data.url;
					}, 2000);
					return;
				}else if(data.result=="fail"){
					$("#errorInfo").html(data.msg).show();
					$("#randCheckCode_img").click();
					return;
				}
				
				if($("#remember").is(":checked")){
					addcookie("login_username", username, 30*24);
				}else{
					deletecookie("login_username");
				}
				location.href=data.url;
			},
			url : "${ctx}/login",
			type : "post",
			timeout:30000
		};
		$("#password").val(hex_ha_ha_ha_ha_ha(password));
		$("#loginForm").ajaxSubmit(options);
	}
</script>
</body>
</html>