<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en-US">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>500 - 对不起，服务器内部错误！</title>
<link rel="stylesheet" type="text/css" href="${ctx}/css/error.css">
</head>
<body>
<div id="wrapper"><a class="logo" href="/"></a>
  <div id="main">
    <div id="header">
      <h1><span class="icon">!</span>500<span class="sub">Internal Server Error</span></h1>
    </div>
    <div id="content">
      <h2>服务器内部错误！</h2>
      <p>当您看到这个页面,表示服务器内部错误</p>
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