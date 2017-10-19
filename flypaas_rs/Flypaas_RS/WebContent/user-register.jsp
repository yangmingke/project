<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
    <title>注册</title>
 
<link type="text/css" rel="stylesheet" href="${ctx}/register_files/base.css">
<link type="text/css" rel="stylesheet" href="${ctx}/register_files/global.css">
<link type="text/css" rel="stylesheet" href="${ctx}/register_files/base-v1.css">
<link type="text/css" rel="stylesheet" href="${ctx}/register_files/global-v1.css">  
<link rel="stylesheet" href="${ctx}/register_files/serviceFreephone.css">   
<link rel="stylesheet" type="text/css" href="${ctx}/register_files/login&amp;register.css">
<link rel="stylesheet" href="${ctx}/register_files/layer.css" id="layui_layer_skinlayercss">
<script type="text/javascript" src="${ctx}/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${ctx}/js/register/register.js"></script>
</head>
<body>

<!--[if lte IE 7]>
<div class="xdw-tips">
<p>您正在使用IE低级浏览器，为了您的账号安全和更好的产品体验</p>
<p>强烈建议您立即 <a target="_blank" href="http://windows.microsoft.com/zh-cn/internet-explorer/download-ie">升级IE浏览器</a> 或者用更快更安全的 <a target="_blank" href="https://www.baidu.com/s?ie=UTF-8&wd=%E8%B0%B7%E6%AD%8C%E6%B5%8F%E8%A7%88%E5%99%A8">谷歌浏览器chrome</a></p>
</div>
<![endif]-->
		<div id="container">
		    <div class="com_wrap">
		        <div class="main_wrap w1180">
		            <div class="LR_cont">
		                <div class="LR_title">
		                    <p class="tit">新用户注册</p>
		                    <!--<p class="right_btn"><span class="vip_com">普通会员</span><span class="vip_bus">商家会员</span></p>-->
		                </div>
		                <div class="register_cont fl">
		                    <form id="registerid" method="post" onsubmit="return submit();">
		                        <ul class="LR_ul">
		                        	<li><input type="text" id="assignMessage" name="assignMessage"  value="${assignMessage}"  style="display: none;"></li>
		                        	<c:if test="${assignMessage == null}">
		                        		<li><input type="text" id="sid" name="sid"  value="${sid}"  style="display: none;"></li>
			                            <li><input type="text" id="netId" name="netId" value="${netId}" style="display: none;"></li>
			                            <li><p class="word">邮箱：</p><input type="text" id="emailId" name="emailId" value="${email}" readonly="readonly" style="border: 0" ></li>
			                            <li><p class="word">公司简称：</p><input type="text" id="uId" name="username" placeholder="请输入公司简称"></li>
			                            <li><p class="word">手机号：</p><input type="text" id="telId" name="mobile" placeholder="请输入手机号码"></li>
			                            <li><p class="word">公司全称：</p><input type="text" id="rId" name="realname" placeholder="请输入公司全称"></li>
			                            <li><p class="word">登录密码：</p><input type="password" id="pwd" name="password" placeholder="请输入6~20位的数字字母组合"></li>
			                            <li><p class="word">确认密码：</p><input type="password" id="pwd2" name="" placeholder="请重复上面的密码"></li>
			                            <li><p class="word">支付宝账户：</p><input type="text" id="alipayAccount" name="alipayAccount" placeholder="请输入支付宝用户名，用于提现"></li>
			                            <li><p class="word">支付宝姓名：</p><input type="text" id="alipayName" name="alipayName" placeholder="请输入支付宝真实姓名，用于提现"></li>
			                            <li><p class="txt"><label><input type="checkbox" id="stateId" checked="checked" name="state" value="1">我已阅读并同意<span style="color:#0058ab;cursor: pointer;" onclick="purepop(&#39;快传注册协议&#39;,&#39;#tank&#39;)">《快传注册协议》</span></label>
			                            </p></li>
		                        	</c:if>
		                        </ul>
		                    </form>
		                    <c:if test="${assignMessage != null}">
			                    <script>
			                        alert($('#assignMessage').val());
			                        location.href="${ctx}/login.jsp";
			                    </script>
		                	</c:if>
		                     <ul class="LR_ul">
		                   		 <li>
			                   		 <p class="word">&nbsp;</p>
			                         <button id="submitid" onclick="submit()">注册</button>
		                         </li>
		                     </ul>
		                </div>
		                
		                <div class="right_com fr">
		                    <dl>
		                        <dt><img src="${ctx}/register_files/dgg_logo1.png"></dt>
		                        <dd>已经是“快传合作方”平台会员？赶快登录吧！</dd>
		                        <dd><a href="${ctx}/login.jsp">马上登录</a></dd>
		                    </dl>
		                </div>
		                <div class="clearfix"></div>
		            </div>
		        </div>
		    </div>
		</div>
		<style type="text/css">
		    .cf:after{clear:both;height:0;content:"";display:block;overflow:hidden;visibility:hidden;}
		    .cf{zoom:1;}
		    .cover_bg{width: 100%;background: #000;filter:alpha(opacity=50);-moz-opacity:0.5;-khtml-opacity:0.5;opacity: 0.5;position: fixed;left: 0;top: 0;z-index: 99;height: 100%;}
		    #swt-custom *{margin:0;padding:0;}
		    #swt-custom{margin:0;padding:0;width: 465px;height: 300px;overflow: hidden;background: url(http://oape39bcp.bkt.clouddn.com/kefu/images/xdw2.jpg) no-repeat;position: relative;z-index:99999;margin-left:-21px;margin-top:-40px;}
		    #swt-custom .swt-custom-close{display: block;position: absolute;top:10px;right: 10px;height: 15px;width: 15px;cursor: pointer;}
		    #swt-custom .swt-custom-box{display: block;padding-top:184px;}
		    #swt-custom .swt-custom-box .zxbtn .btn1{display: block;width: 126px;height: 40px;line-height: 40px;color: #fff;text-align: center;font-size: 14px;cursor: pointer;font-family:微软雅黑;background-color:#ff9914;margin:0 auto;border-radius:6px;font-weight:bold}
		</style>
	</body>
</html>