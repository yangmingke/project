<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="renderer" content="webkit|ie-comp|ie-stand" />
<title>第三方账号登录</title>
<%@include file="/front/resource.jsp" %>
<script type="text/javascript" src="<%=path%>/js/reg.js"></script>
<style type="text/css">
.top_right{display:none;}
form label {width:85px;}
</style>
</head>

<body>
<!--公共头部header bof-->
<%@include file="/front/head.jsp" %>
<!--公共头部header eof--> 


<!--主体部分content bof-->
<div class="ft_content ft_content_log">
  <div class="ft_content_wp"> 
    
    <!--用户注册 bof--->
    <div class="reg_box">
    	<div>
	      <h1 id="auth_h1" style="display:inline-block;">绑定${auth_bind.auth_type_name}账号</h1>
	      <a id="auth_a" style="color: #3c84bf;" href="javascript:;" onclick="changeBind(this)">已有快传账号？</a>
      </div>
      <div class="reg_info">
      	<form method="post" action="/user/userAdd" name="regForm" id="register">
          <p>
            <label for="reg_email">昵称</label>
            <input type="text" id="userName"  name="user.userName" value="${auth_bind.auth_username}" />
            <span id="userName_error"  class="error" style="display:none"></span>
            <span class="tips">允许输入字母、数字或者字母数字组合，4-15个字符，昵称通用于快传论坛等其他服务。</span>
          </p>
          <p>
            <label for="reg_email">邮箱</label>
            <input type="text" id="reg_email"  name="user.email" value="${auth_bind.auth_email}" />
            <span id="email_error"  class="error" style="display:none"></span>
            <span class="tips">很重要！作为登录账号，若修改则需要通过邮箱认证。</span>
		  </p>
          <p>
            <label for="reg_password">密码</label>
            <input type="password" value="" id="reg_password" name="user.password"/>
			<span class="tips">字母、数字或者英文符号，最短6位，区分大小写</span>
            <span id="pwd_error" class="error" style="display:none"></span>
          </p>
          <p>
            <label for="reg_password_confirm">确认密码</label>
            <input type="password" value="" id="reg_password_confirm" />
            <span id="pwd_confrim_error" class="error" style="display:none"></span>
          </p>
          <p class="agreen">
            <input type="checkbox" value="" checked="checked"/>
            	同意<a href="<%=path %>/about/items">《快传平台服务条款》</a></p>
          <p class="btn">
			<a href="javascript:void(0)" id="subBtn" class="link" onclick="authReg()">注册并绑定</a>
          </p>
          <input type="hidden" id="vM" />
          <input type="hidden" id="vU" />
          <input type="hidden" id="auth_email" value="${auth_bind.auth_email}" />
          <input type="hidden" name="authType" value="${auth_bind.auth_type}" />
	      <input type="hidden" name="authId" value="${auth_bind.auth_id}" />
        </form>
        
       <form method="post" name="logForm" style="display:none;" id="logForm">
		  <p>
            <label>邮箱或手机号</label>
	        <input type="text" name="userid" id="log_username" value="${auth_bind.auth_email}" />
		  </p>
	      <p>
            <label>密码</label>
	        <input type="password" name="password" id="log_password" />
	      </p>
		  <p class="code" id="temp">
		  </p>
	      <p class="form_tips" style="height:20px;visibility:visible; margin-bottom: 0px;">
	      	<span class="error" id="error" style="margin-left:-115px;position:static; display: none;" ></span>
	      </p>
	      <p class="btn">
	        <input type="button" value="登录并绑定" onclick="logSubmit('login')" class="log" id="log_btn" />
	      </p>
	      <input type="hidden" id="remusername" />
	      <input type="hidden" id="checkcode" />
          <input type="hidden" id="authType" value="${auth_bind.auth_type}" />
	      <input type="hidden" id="authId" value="${auth_bind.auth_id}" />
    </form>
      </div>
    </div>
    <!--用户注册 eof--> 
  </div>
</div>
<!--主体部分content eof--> 


<!--公共底部footer bof-->
<%@include file="/front/foot.jsp" %>
<!--公共底部footer bof--> 

<script type="text/javascript">
$(function(){
	$("#login").remove();
	
	var auth_error = ${auth_error!=null};	//第三方账号登录：错误
	var auth_fail = ${auth_fail!=null};		//第三方账号登录：失败
	
	if(auth_error || auth_fail){
		$("#auth_a, #register").hide();
		$("#logForm").show();
		$("#auth_h1").text("第三方账号登录");
		$("#log_btn").val("登 录");
	}
	if(auth_error){
		errorInfo("${auth_error}", false);
	}else if(auth_fail){
		$("#log_username").val("${auth_fail.yzx_email}")
		loginCallback("${auth_fail.error_code}");
		
	}
	
	$("#register p.agreen input").click(function(){
		if($(this).attr("checked")){
			$("#register p.btn input").removeClass("unchecked");
			$("#register p.btn input").removeAttr("disabled");
		}
		else{
			$("#register p.btn input").addClass("unchecked");
			$("#register p.btn input").attr("disabled","disabled");
		}
	});
});

function changeBind(a){
	if($("#register").is(":visible")){
		$("#register").hide();
		$("#logForm").show();
		$(a).text("没有快传账号？");
	}else{
		$("#register").show();
		$("#logForm").hide();
		$(a).text("已有快传账号？");
	}
}

function authReg(){
	if($("#reg_email").val() == $("#auth_email").val()){
		$("#register").attr("action", "/auth/userAdd");
	}else{
		$("#register").attr("action", "/user/userAdd");
	}
}
</script>

</body>
</html>
