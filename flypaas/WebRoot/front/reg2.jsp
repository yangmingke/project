<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
request.setAttribute("path", path);
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="keywords" content="一对一音视频通话,音视频通讯,音视频通讯加速,音视频通话,在线教育,视频通话,视频会议,互联网语音,通讯加速,快传,快传技术," >
	<meta name="description" content="快传音视频通讯平台让开发者轻松接入一对一视频通话,音视频通讯加速,视频直播等能力,快速搭建音视频通讯,视频通话/会议应用,通讯加速,音视频引擎让视频更流畅。" >
	<title>注册——快传通行证</title>
	<%@include file="/front/resource1.jsp"%>
	<% Object object = com.flypaas.utils.UcCenterKit.getScript(request);
		if(null != object){
			out.println((String)object);
		}
	%>
</head>
</head>
<body>
	<!--公共头部 ft_header bof-->
	<c:if test="${user.superSid == null}">
		<%@include file="/front/head1.jsp"%>
	</c:if>
	<!--公共头部 ft_header eof-->

	<!--主体部分 ft_content bof-->
    <div class="ft_content ft_content_reg">
    	<div class="ft_content_wp">
            <div class="reg_step">
                <img src="<%=path%>/front/images1/reg_step2.png" />
            </div>
            <div class="reg_box">
            	<form action="<%=path %>/user/verifyMailOpt" id="userInfoFrm" name="userInfoFrm" method="post">
	                <div class="reg_form">
	                    <dl class="txt">
	                        <dt>已验证邮箱</dt>
	                        <dd>${user.email }</dd>
	                    </dl>
	                    <dl>
	                        <dt><i class="mark"></i>密码</dt>
	                        <dd><input type="password" placeholder="密码" id="password1" name="user.password"/><span id="password1_error" class="error" style="display:none">字符不符合规范</span></dd>
	                        <dd class="tips">允许字母+数字，最少8个字符</dd>
	                    </dl>
	                    <dl>
	                        <dt><i class="mark"></i>确认密码</dt>
	                        <dd><input type="password" placeholder="再次输入密码" id="password2"/><span id="password2_error" class="error" style="display:none">正确</span></dd>
	                    </dl>
	                    <dl>
	                        <dt><i class="mark"></i>昵称</dt>
	                        <dd>
	                        <input type="text" placeholder="昵称" id="userName" name="user.userName"/>
	                        <input type="hidden" id="vusername"/>
	                        <span id="userName_error" class="error" style="display:none">昵称已被使用</span></dd>
	                        <dd class="tips">允许中文、字母、数字，不能超过6个汉字或12个字符</dd>
	                    </dl>
	                   <dl>
	                        <dt><i class="mark"></i>手机号码</dt>
	                        <dd>
	                        <input type="text" placeholder="请输入手机号" name="user.mobile" id="phone"/>
	                        <span class="error" id="phone_error" style="display:none">
	                        </dd>
	                    </dl>
	                    <dl class="verify">
	                    	<dd>
	                            <!-- <input type="button" value="短信验证" class="msg" onclick="smsCode('smscodeinput','voicecodeinput','log')" id="smscodeinput"/>
	                            <em>或</em>
	                            <input type="button" value="语音验证" class="voice" onclick="voiceCode('voicecodeinput','smscodeinput')" id="voicecodeinput"/>
	                        	<input type="hidden" id="vmovecode"  />
	           					<input type="hidden" id="movecode"  /> -->
	           					<input id="path_fo_js" type="hidden" value="<%=path%>" />
	           					<input type="hidden" value="${user.sid }" name="user.sid" />
	           					<input type="hidden" value="${user.email }" name="user.email" />
	                        </dd>
	                    </dl>
	                   <!--  <dl class="code">
	                        <dd><input type="text" placeholder="验证码" id="inputmovecode"/><span id="move_phone_code_error" class="error" style="display:none">正确</span></dd>
	                        <dd class="tips">请输入接收到的验证码</dd>
	                    </dl> -->
	                    <dl class="button">
	                        <dd><input type="button" value="提 交" id="infoSubt"/></dd>
	                    </dl>
	                </div>
                </form>
            </div>
        </div>        
	</div>
	<!--主体部分 ft_content eof-->

	<!--公共底部 ft_footer bof--> 
	<%@include file="/front/foot1.jsp"%>
	<script type="text/javascript" src="<%=path%>/js/reg.js"></script>
</body>
</html>