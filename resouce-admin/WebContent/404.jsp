<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en-US">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>404 - 对不起，您查找的页面不存在！</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/error.css">
</head>
<body>
<div id="wrapper"><a class="logo" href="/"></a>
  <div id="main">
    <div id="header">
      <h1><span class="icon">!</span>404<span class="sub">page not found</span></h1>
    </div>
    <div id="content">
      <h2>您打开的这个的页面不存在！</h2>
      <p>当您看到这个页面,表示您的访问出错,这个错误是您打开的页面不存在,请确认您输入的地址是正确的,如果是在本站点击后出现这个页面,请联系管理员进行处理</p>
      <div class="utilities">
          <div class="input-container" style="font: 13px 'TeXGyreScholaRegular', Arial, sans-serif;color: #696969; text-shadow: 0 1px white;text-decoration: none;">
          </div>
        <a class="button right" href="#" onClick="history.go(-1);return true;">返回</a>
        <div class="clear"></div>
      </div>
    </div>
  </div>
</div>
</html>