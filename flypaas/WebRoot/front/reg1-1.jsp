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
            	<form action="<%=path %>/user/addEmail" id="register" name="register">
                <p>flypaas 将发送一封验证邮件到你的邮箱，此邮箱将作为登录用户名。<br />点击「发送」按钮，注册账号</p>
                <p><input type="text" placeholder="您的邮箱" id="reg_email" name="vemail"/><span id="email_error"  class="error" style="display:none"></span></p>
                <p><input type="button" value="发 送" id="subBtn"/></p>
                <input id="path_fo_js" type="hidden" value="<%=path%>" />
                <p class="tips">注：当您点击发送默认认同并遵守<a href="<%=path %>/about/items">《快传平台服务条款》</a>的内容</p>
                </form>
            </div>
        </div>        
	</div>
	<!--主体部分 ft_content eof-->

	<!--公共底部 ft_footer bof--> 
	<%-- <%@include file="/front/callus.jsp"%> --%>
	<script type="text/javascript" src="<%=path%>/js/reg.js"></script>
</body>
</html>