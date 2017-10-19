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
<title>激活失败</title>
<%@include file="/page/resource.jsp" %>
</head>
<body>
<%@include file="/page/head.jsp" %>

<!--主体部分content bof-->
<div class="content">
  <div class="content_wrapper"> 
    
    <!--用户注册 bof--->
    <div class="reg_box">
      <div class="reg_info reg_success">
        <p style="font-size:24px; margin-bottom:40px;margin-left:50%">系统错误！</p>
      </div>
    </div>
    <!--用户注册 eof--> 
  </div>
</div>
<!--主体部分content eof--> 


<!--公共底部footer bof-->
<%@include file="foot.jsp" %>
<!--公共底部footer bof--> 


</body>
</html>
