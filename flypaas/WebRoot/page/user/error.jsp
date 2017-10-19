<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>登录页面</title>

<%@include file="/page/resource.jsp" %>
</head>

<body>
<%@include file="/page/head.jsp" %>


<!--主体部分content bof-->
<div class="content">
  <div class="content_wrapper"> </div>
</div>
<!--主体部分content eof--> 


<!--公共底部footer bof-->
<%@include file="../foot.jsp" %>
<!--公共底部footer bof--> 
验证失败。
</body>
</html>
