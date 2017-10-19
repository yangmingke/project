<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>快传音视频开放平台_提供音视频通讯加速,一对一视频通话，视频直播等通讯加速API及SDK</title>
<meta name="keywords" content="一对一音视频通话,音视频通讯,音视频通讯加速,音视频通话,在线教育,视频通话,视频会议,互联网语音,通讯加速,快传,快传技术," >
<meta name="description" content="快传音视频通讯平台让开发者轻松接入一对一视频通话,音视频通讯加速,视频直播等能力,快速搭建音视频通讯,视频通话/会议应用,通讯加速,音视频引擎让视频更流畅。" >
<meta name="baidu-site-verification" content="EtMHBfc7G8" />
<link rel="stylesheet" type="text/css" href="<%=path%>/sso/css/bbs_log.css" />

<script type="text/javascript" src="<%=path%>/js/jquery-1.8.2.js"></script>
<script type="text/javascript" src="<%=path%>/js/cookie.js"></script>
<script type="text/javascript" src="<%=path%>/js/form.js"></script>
<script type="text/javascript" src="<%=path%>/js/function.js"></script>
<script type="text/javascript" src="<%=path%>/js/plugins/jquery.JPlaceholder.js"></script>
<script type="text/javascript" src="<%=path%>/js/md5.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-ui/js/core.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-ui/js/datepicker.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-ui/js/widget.js"></script>
</head>
<body id="this_is_the_login_page">

<!--用户登录头部header bof-->
<div class="login_header">
  <div class="login_header_wrapper">
    <div class="logo"><a title="快传融合通讯开放平台" href="<%=path%>/" target="_blank"><img alt="快传融合通讯开放平台" src="<%=path%>/sso/images/logo.png" /></a></div>
  </div>
</div>
<!--用户登录头部header eof--> 

<!--用户登录内容部分content bof-->
<div class="login_content">
  <div class="login_content_wrapper">
    <div class="loginbox">
      <div class="loginboxinner">
        <h1>快传通行证</h1>
        <form  name="logForm"  id="login">
          <div class="field username"><label>账号：</label>
            <input type="text" name="userid" id="log_username" value=""  placeholder="手机号或邮箱" />
          </div>
          <div class="field password"><label>密码：</label>
            <input type="password" name="password" id="log_password" value=""  placeholder="请输入密码"/>
          </div>
          <p class="code" id="temp"> </p>
          <p class="remember">
        	<input type="checkbox" value="" />
        		记住用户名 <a href="<%=path%>/page/user/forget_pwd.jsp" class="blue">忘记密码</a>
      		</p>
            <input type="hidden" id="remusername" />
     		<input type="hidden" id="checkcode"  />
     		<p class="form_tips">
     			<span class="error" id="error" style="display: none;" ></span>
     		</p>
            <p class="btn">
            <input type="button" value="登 录" class="log" id="log_btn" onclick="logSubmit('login')" />
            <input type="button" value="立即注册" class="reg" id="reg_btn" onclick="location.href='<%=path%>/user/toSign'"  />
          </p>
        </form>
        <input id="path_fo_js" type="hidden" value="<%=path%>" />
        <input id="fr" type="hidden" value="${param.fr}"/>
      </div>
    </div>
  </div>
</div>
<!--用户登录内容content eof--> 

<!--用户登录底部footer bof-->
<div class="login_footer">
  <div class="login_footer_wrapper">
    <p>&copy;深圳市快传技术有限公司. All Rights Reserved<br />粤ICP备xxxxxxxx号-1 </p>
  </div>
</div>
<!--用户登录底部footer eof-->

<script type="text/javascript" src="<%=path%>/js/login.js"></script>
</body>
<% Object object = com.flypaas.utils.UcCenterKit.getScript(request);
	if(null != object){
		out.println((String)object);
	}
%>
</html>
