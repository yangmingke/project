<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>404页面</title>
<%@include file="/front/resource1.jsp"%>
</head>

<body>
<!--公共头部header bof-->
<%@include file="/front/head1.jsp"%>
<!--公共头部header eof--> 

<!--主体部分content bof-->
<div class="ft_content ft_content_reg">
  <div class="ft_content_wp">
      <div class="error_page">
        <ul>
          <li class="num">404</li>
          <li class="txt">
            <h2>抱歉，页面未找到</h2>
            <p>没有找到您请求的页面，如有需要请尝试搜索相关内容</p>
<!--             <p class="search"><input type="text" value="" /><input type="submit" value="搜 索" class="org_btn" /></p> -->
          </li>
        </ul>
      </div>
    </div>
</div>

<!--主体部分content eof--> 

<!--公共底部footer bof-->

<!--公共底部footer bof-->


</body>
</html>
