<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<LINK rel="Bookmark" href="/favicon.ico" >
<LINK rel="Shortcut Icon" href="/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="http://libs.useso.com/js/html5shiv/3.7/html5shiv.min.js"></script>
<script type="text/javascript" src="http://libs.useso.com/js/respond.js/1.4.2/respond.min.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/css3pie/2.0beta1/PIE_IE678.js"></script>
<![endif]-->
<link href="${ctx}/css/H-ui.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/H-ui.login.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="js/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>快传技术有限公司资源开放平台登录界面</title>
<meta name="keywords" content="H-ui.admin v2.0,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
<meta name="description" content="H-ui.admin v2.0，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body onload="load()">
<input type="hidden" id="first_login" name="TenantId" value="true" />
<input type="hidden" id="TenantId" name="TenantId" value="" />
<div class="header"></div>
<div class="loginWraper">
  ${error.message}
  <div id="loginform" class="loginBox">
    <form id="login_form" action="#" method="post">
      <div class="formRow user">
        <input id="user" name="email" type="text" placeholder="邮箱" class="input_text input-big">
      </div>
      <div class="formRow password">
        <input id="pwd" name="password" type="password" placeholder="密码" class="input_text input-big">
      </div>
      <div class="formRow yzm">
        <input class="check_input" type="text"  placeholder="请输入验证码" style="width: 150px; height: 36px;" >
             <img id="imgVerify" src="" alt="点击更换验证码" width="112" height="36" onclick="getVerify(this);">
       </div>
      <div class="formRow">
	      <div class="login_form_warn" style="display: none;">
	             <span class="warn_text" style="color: red"></span>
	      </div>
      </div>
    </form>
    <div class="formRow">
        <input name="" type="submit" class="btn radius btn-success btn-big" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;" onclick="login()" >
        <input name="" type="reset" class="btn radius btn-default btn-big" value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;"><br/><br/>
      <a href="${ctx}/user-register2.jsp">还没有账号? 点击注册>>>></a>
      </div>
  </div>
</div>
<div class="footer"><a href="www.flypaas.com">快传技术有限公司官网>></a></div>
<script type="text/javascript" src="${ctx}/js/H-ui.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.min.1.8.1.js"></script>
<script type="text/javascript" src="${ctx}/js/login/login.js"></script>
<script >
	function load(){
		if(top.location!=self.location){
	        top.location=self.location;
	    }
		
	}
	$(document.body).ready(function () {
	    //首次获取验证码
	    $("#imgVerify").attr("src","loginController/getVerify.action?"+Math.random());
	});
</script>
</body>
</html>