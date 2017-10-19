<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
</head>
</head>
<body>
	<!--公共头部 ft_header bof-->
	<%@include file="/front/head2.jsp"%>
	<!--公共头部 ft_header eof-->

	<!--主体部分 ft_content bof-->
    <div class="ft_content ft_content_reg">
    	<div class="ft_content_wp">
            <div class="reg_step">
                <img src="<%=path%>/front/images1/reg_step1.png" />
            </div>
            <div class="reg_box">
                <p>flypaas 已将注册链接发送至您提供的邮箱内，请登录您的邮箱点击邮件内注册链接完成后续信息补全。</p>
                <p class="large">您的邮箱<span>${email }</span>邮件已发送</p>
                <input type="hidden" value="${email }" id="email" />
                <p><input type="button" value="登录邮箱" class="w140" id="toExtEmail"/></p>
            </div>
        </div>        
	</div>
	<!--主体部分 ft_content eof-->

	<!--公共底部 ft_footer bof--> 
	<%-- <%@include file="/front/callus.jsp"%> --%>
</body>
</html>