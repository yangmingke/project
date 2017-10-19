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
<title>用户注册</title>
<%@include file="/front/resource.jsp" %>
<script type="text/javascript" src="<%=path%>/js/reg.js"></script>
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
      <div class="reg_step"><img src="<%=path%>/images/reg_step_1.png" /></div>
      <div class="reg_info">
        <s:form  action="userAdd" namespace="/user" theme="simple" method="post" name="regForm" id="register">
          <p>
            <label for="reg_email">昵称</label>
            <input type="text" id="userName"  name="user.userName"/>
            <span id="userName_error"  class="error" style="display:none"></span>
            <span class="tips">允许输入字母、数字或者字母数字组合，4-15个字符，昵称通用于快传论坛等其他服务。</span>
          </p>
          <p>
            <label for="reg_email">邮箱</label>
            <input type="text" value="" id="reg_email"  name="user.email"/><span id="email_error"  class="error" style="display:none"></span>
            <span class="tips">很重要！作为登录账号，需要通过邮箱认证。</span> </p>
          <p>
            <label for="reg_password">密码</label>
            <input type="password" value="" id="reg_password" name="user.password"/>
			<span class="tips">字母、数字或者英文符号，最短8位，区分大小写</span>
            <span id="pwd_error" class="error" style="display:none"></span>
          </p>
          <p>
            <label for="reg_password_confirm">确认密码</label>
            <input type="password" value="" id="reg_password_confirm" /><span id="pwd_confrim_error" class="error" style="display:none"></span>
          </p>
          <p class="agreen">
            <input type="checkbox" value=""/>
            同意<a href="<%=path %>/about/items">《快传平台服务条款》</a></p>
          <input type="hidden" id="vM" />
          <input type="hidden" id="vU" />
          <p class="btn">
            <input type="button" id="subBtn" disabled="disabled" class="unchecked" value="注册" />
<!-- 			<a href="javascript:void(0)" id="subBtn"  class="link unchecked">注册</a> -->
          </p>
        </s:form>
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
</script>

</body>
</html>
