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
<title>500页面</title>
<%@include file="/front/resource1.jsp"%>
</head>

<body>
<!--公共头部header bof-->

<!--公共头部header eof--> 

<!--主体部分content bof-->
<div class="content">
  <div class="content_wrapper">
    <div class="content_box">
      <div class="error_page">
        <ul>
          <li class="num">500</li>
          <li class="txt">
            <h2>抱歉，服务器内部错误</h2>
            <p>我们正在努力修复，请稍后重试</p>
<!--             <p class="search"><input type="text" value="" /><input type="submit" value="搜 索" class="org_btn" /></p> -->
          </li>
        </ul>
      </div>
    </div>
  </div>
</div>

<!--主体部分content eof--> 

<!--公共底部footer bof-->

<!--公共底部footer bof-->

</body>
</html>
