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
<title>激活邮件已发送</title>
<%@include file="/front/resource.jsp" %>
</head>

<body>
<%@include file="/front/head.jsp" %>



<!--主体部分content bof-->
<div class="ft_content ft_content_log">
  <div class="ft_content_wp"> 
    
    <!--用户注册 bof--->
    <div class="reg_box">
      <div class="reg_step"><img src="<%=path%>/images/reg_step_2.png" /></div>
      <div class="reg_info">
        <div class="activate_email">
          <p>请前往邮箱验证邮箱有效性<br />
            注：邮箱账号作为登录账号，如邮箱内无法收到验证邮件，<br />
            请点击<a href="javascript:void(0)" onclick="handSubmit('activeFrm')">重新发送邮箱激活码</a>
          <s:form id="activeFrm" action="sendverifyMail" namespace="/user" theme="simple">
          <input type="hidden" value="${sid}" name="sid" />
          <input type="hidden" value="${email}" name="email" />
          </s:form>
          </p>
        </div>
      </div>
    </div>
    <!--用户注册 eof--> 
  </div>  

</div>
<!--主体部分content eof--> 


<!--公共底部footer bof-->
<%@include file="/front/foot.jsp" %>
<!--公共底部footer bof--> 


</body>
</html>
