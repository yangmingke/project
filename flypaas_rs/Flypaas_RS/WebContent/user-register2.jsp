<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
    <title>快传科技有限公司资源开发平台注册界面</title>
 
<link type="text/css" rel="stylesheet" href="${ctx}/register_files/base.css">
<link type="text/css" rel="stylesheet" href="${ctx}/register_files/global.css">
<link type="text/css" rel="stylesheet" href="${ctx}/register_files/base-v1.css">
<link type="text/css" rel="stylesheet" href="${ctx}/register_files/global-v1.css">  
<link rel="stylesheet" href="${ctx}/register_files/serviceFreephone.css">   
<link rel="stylesheet" type="text/css" href="${ctx}/register_files/login&amp;register.css">
<link rel="stylesheet" href="${ctx}/register_files/layer.css" id="layui_layer_skinlayercss">
<script type="text/javascript" src="${ctx}/register_files/CheckInvitejs.aspx"></script>
<script type="text/javascript" src="${ctx}/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${ctx}/js/register/register.js"></script>
</head>
<body>

<!--[if lte IE 7]>
<div class="xdw-tips">
<p>您正在使用IE低级浏览器，为了您的小顶账号安全和更好的产品体验</p>
<p>强烈建议您立即 <a target="_blank" href="http://windows.microsoft.com/zh-cn/internet-explorer/download-ie">升级IE浏览器</a> 或者用更快更安全的 <a target="_blank" href="https://www.baidu.com/s?ie=UTF-8&wd=%E8%B0%B7%E6%AD%8C%E6%B5%8F%E8%A7%88%E5%99%A8">谷歌浏览器chrome</a></p>
</div>
<![endif]-->
		<div id="container">
		    <div class="com_wrap">
		        <div class="main_wrap w1180">
		            <div class="LR_cont">
		                <div class="LR_title">
		                    <p class="tit">新用户注册邮箱</p>
		                </div>
		                <div class="register_cont fl">
		                    <form action="#" method="post" >
		                        <ul class="LR_ul">
		                            <li><p class="word">邮箱：</p><input type="text" id="email" name="email" placeholder="请输入邮箱"><p style="color: red; display: none;" id="message" >邮箱已注册,请重新输入</p></li>
		                            <li><p class="word">&nbsp;</p>
		                                <button type="button" onclick="validateCode()" >注册邮箱</button>
		                            </li>
		                        </ul>
		                    </form>
		                </div>
		                <div class="right_com fr">
		                    <dl>
		                        <dt><img src="${ctx}/register_files/dgg_logo1.png"></dt>
		                        <dd>已经是“快传网”平台用户？赶快登录吧！</dd>
		                        <dd><a href="${ctx}/login.jsp" id="login">马上登录</a></dd>
		                    </dl>
		                </div>
		                <div class="clearfix"></div>
		            </div>
		        </div>
		    </div>
		</div>
	</body>
</html>