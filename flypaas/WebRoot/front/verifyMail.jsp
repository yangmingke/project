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
<title>激活邮箱</title>
<%@include file="/front/resource.jsp"%>
</head>

<body>
<!--公共头部header bof-->
<%@include file="/front/head.jsp"%>
<!--公共头部header eof--> 

<!--主体部分content bof-->
<div class="ft_content ft_content_log">
  <div class="ft_content_wp"> 
    
    <!--用户注册 bof-->
    <div class="reg_box">
      <div class="reg_step"><img src="<%=path %>/images/reg_step_2.png" /></div>
      <div class="reg_info">
        <div class="activate_email">
          <h1 class="mail">就差一步了，赶紧激活你的账号吧！</h1>
          <p>请确认激活邮箱：您的邮箱：<span>${user.email}</span></p>
          <p>
          <a href="javascript:void(0)" class="link">激活账号</a>
          </p>
          <form id="activeFrm" action="/user/verifyMailOpt">
          <input type="hidden" value="${user.sid}" name="sid" />
          <input type="hidden" value="${user.email}" name="email" />
          </form>
        </div>
      </div>
    </div>
    <!--用户注册 eof--> 
  </div>  

</div>
<!--主体部分content eof--> 

<!--公共底部footer bof-->
<%@include file="/front/foot.jsp"%>
<!--公共底部footer bof-->
<script type="text/javascript">
	$(function(){
		$(".link").click(function(){
			$(".link").text("激活中...");
			$(".link").unbind("click");
			$("#activeFrm").submit();
		});
	});
</script>
</body>
</html>
