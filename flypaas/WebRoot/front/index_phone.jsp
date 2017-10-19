<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="baidu-site-verification" content="H655bLuKzM" />
	<title>快传音视频开放平台_提供音视频通讯加速,一对一视频通话，视频直播等通讯加速API及SDK</title>
	<meta name="keywords" content="一对一音视频通话,音视频通讯,音视频通讯加速,音视频通话,在线教育,视频通话,视频会议,互联网语音,通讯加速,快传,快传技术," >
	<meta name="description" content="快传音视频通讯平台让开发者轻松接入一对一视频通话,音视频通讯加速,视频直播等能力,快速搭建音视频通讯,视频通话/会议应用,通讯加速,音视频引擎让视频更流畅。" >
    <%@include file="/front/resource1.jsp"%>
</head>
<body id="b-01">
	<!--公共头部 ft_header bof-->
    <%@include file="/front/head3.jsp" %>
    <!--网页主体部分-->
	<div id="banner" class="phone_banner phone">
		<img src="<%=path%>/front/images1/phone_banner.png" />
	</div>
	<div id="item-01">
		<div class="phone_about phone">
			<img src="<%=path%>/front/images1/phone_about.png" />
		</div>
	</div>
	<div id="item-02">
		<div id="item-02" class="phone_video_audio1 phone">
			<img src="<%=path%>/front/images1/phone_video_audio1.png" />
		</div>
		<div class="phone_video_audio2 phone">
			<img src="<%=path%>/front/images1/phone_video_audio2.png" />
		</div>
	</div>
	<div id="item-03">
		<div id="item-03" class="phone_can1 phone">
			<img src="<%=path%>/front/images1/phone_can1.png" />
		</div>
		<div class="phone_can2 phone">
			<img src="<%=path%>/front/images1/phone_can2.png" />
		</div>
	</div>
	<div id="item-04">
		<div id="item-04" class="phone_solution1 phone">
			<img src="<%=path%>/front/images1/phone_solution1.png" />
		</div>
		<div class="phone_solution2 phone">
			<img src="<%=path%>/front/images1/phone_solution2.png" />
		</div>
		<div class="phone_solution3 phone">
			<img src="<%=path%>/front/images1/phone_solution3.png" />
		</div>
		<div class="phone_solution4 phone">
			<img src="<%=path%>/front/images1/phone_solution4.png" />
		</div>
	</div>
	<div id="item-05">
		<div id="item-05" class="phone_sdk phone">
			<img src="<%=path%>/front/images1/phone_sdk.png" />
		</div>
	</div>
	<div id="item-06">
		<div id="item-06" class="phone_call_us phone">
			<img src="<%=path%>/front/images1/phone_call_us.png" />
		</div>
	</div>
</body>
</html>