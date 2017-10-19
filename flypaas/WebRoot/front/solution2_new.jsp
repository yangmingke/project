<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>快传音视频开放平台_提供音视频通讯加速,一对一视频通话，视频直播等通讯加速API及SDK</title>
	<meta name="keywords" content="一对一音视频通话,音视频通讯,音视频通讯加速,音视频通话,在线教育,视频通话,视频会议,互联网语音,通讯加速,快传,快传技术," >
	<meta name="description" content="快传音视频通讯平台让开发者轻松接入一对一视频通话,音视频通讯加速,视频直播等能力,快速搭建音视频通讯,视频通话/会议应用,通讯加速,音视频引擎让视频更流畅。" >
	<%@include file="/front/resource1.jsp"%>
</head>
<body id="b-03">
	<!--公共头部 ft_header bof-->
    <%@include file="/front/head1.jsp" %>

	<!--主体部分 ft_content bof-->
    <div class="ft_content">
    	<div class="ft_banner ft_banner13"></div>
    	<div class="item_box solution_box box34">
            <div class="item_box_wp">                
                <div class="solution_4">
                    <div class="txt">
                        <h5>移动端消息服务</h5>
                        <p>移动智能应用的兴起带动任何一个垂直行业领域的服务的深耕细作，为每一个应用配套一套在线智能客服系统基本是每一款成功应用必备的功能。</p>
                        <p>实时响应客户需求，通过在线智能客服解决大部分通用性需求的同时也为更多用户提供更苛刻的精细化服务。</p>
                        <p class="doc_link"><a href="http://docs.flypaas.com/doku.php?id=即时消息接口_android">查看即时消息接口</a></p>
                        <p class="img"><img src="<%=path%>/front/images1/img42.png" /></p>
                    </div>
                </div>              
            </div>
        </div>

    	<div class="item_box solution_box box35">
            <div class="item_box_wp">                
                <div class="solution_5">
                    <div class="txt">
                        <h5>直拨服务</h5>
                        <p>直拨服务一般作为座席端的标准配置服务，客户诉求通过移动端发送至客服服务平台，<br />为内部服务人员提供直接由网页端或系统客户端直接发起呼叫客户的方式，<br />沟通更便捷和简单，呼叫成本更低。</p>
                        <p class="doc_link"><a href="http://docs.flypaas.com/doku.php?id=voip开发指南&s[]=直拨">查看直拨接口</a></p>
                        <p class="img"><img src="<%=path%>/front/images1/img43.png" /></p>
                    </div>
                </div>              
            </div>
        </div>

        <div class="item_box solution_box box36">
            <div class="item_box_wp">                
                <div class="solution_6">
                    <div class="txt">
                        <h5>会议服务</h5>
                        <p>客户服务过程中，如需提供专家服务，可采用平台会议接口为客户提供三方以上的沟通方式。<br />开放平台提供跨平台无障碍的会议接通方式，通话的任何一方可为IP线路或常见的<br />运营商线路。并可通过接口控制会议模式、会议时长、会议通话控制等多种服务</p>
                        <p class="doc_link"><a href="http://docs.flypaas.com/doku.php?id=语音群聊">查看会议接口</a></p>
                        <p class="img"><img src="<%=path%>/front/images1/img44.png" /></p>
                    </div>
                </div>              
            </div>
        </div>

        <%@include file="/front/foot1_0.jsp" %>
	</div>

	<!--主体部分 ft_content eof-->

	<%@include file="/front/foot1.jsp" %>
</body>
</html>