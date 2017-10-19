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
                <img src="<%=path%>/front/images1/reg_step3.png" />
            </div>
            <div class="reg_box">
                <p class="mascot"><img src="<%=path%>/front/images1/mascot.png" /></p>
                <p>注册完成，欢迎您注册成为开放平台的开发者！如您在使用过程中有任何疑问请联系我们。${user.agentUrl }</p>
                <c:if test="${user.agentUrl == null}">
                	<p class="link"><a href="<%=path%>/index">返回首页</a></p>
                </c:if>
                <c:if test="${user.agentUrl != null}">
                	<p class="link"><a href="${user.agentUrl}">返回首页</a></p>
                </c:if>
            </div>
        </div>        
	</div>
	<!--主体部分 ft_content eof-->

	<!--公共底部 ft_footer bof--> 
	<%@include file="/front/foot1.jsp"%>
</body>
</html>