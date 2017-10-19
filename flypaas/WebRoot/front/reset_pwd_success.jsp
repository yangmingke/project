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
<title>重置密码 - 成功</title>
<%@include file="/front/resource1.jsp" %>
</head>

<body>
<%@include file="/front/head1.jsp" %>


<!--主体部分content bof-->
<!-- 
<div class="ft_content ft_content_log">
  <div class="ft_content_wp">
	  <div class="reg_box pwd_box reset_pwd">
		<div class="float_tit">
			<h3>重置密码</h3>
		</div>
	  	<div class="float_ctn">
		    <p class="tips success">重置密码成功</p>
		    <p>密码重置成功，使用新密码进行<a href="<%=path%>/">登录网站</a></p>
	  	</div>
	  </div>
  </div>  

</div>
 -->
   <div class="ft_content ft_content_reg">
    	<div class="ft_content_wp">
            <div class="reg_step">
                <img src="<%=path%>/front/images1/forget_pwd_step4.png" />
            </div>
            <div class="reg_box forget_pwd_box">
                <p class="mascot"><img src="<%=path%>/front/images1/mascot.png" alt="快传融合通讯开放平台" /></p>
                <p>登录密码已完成修改，请在登录窗口使用新设置密码登录</p>
                <p class="link"><a href="<%=path %>/login">返回首页</a></p>
            </div>
        </div>        
	</div>
<!--主体部分content eof--> 


<!--公共底部footer bof-->
<%@include file="/front/foot1.jsp" %>
<!--公共底部footer bof--> 


</body>
</html>
