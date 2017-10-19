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
<body id="b-02">
	<!--公共头部 ft_header bof-->
    <%@include file="/front/head1.jsp" %>

	<!--主体部分 ft_content bof-->
    <div class="ft_content">
    	<div class="ft_banner ft_banner1"></div>
    	<div class="item_box box5">
    		<div class="item_box_wp">
                <div class="title">
                    <h1>短信</h1>
                    <h2>企业服务专用短信送达服务，无盲点覆盖三网，严格内容审核，保障身份验证服务</h2>
                </div>
                <div class="message_1">
                    <ul>
                        <li>
                            <dl>
                                <dt><img src="<%=path%>/front/images1/img1.png" /></dt>
                                <dd class="tit">短信验证码</dd>
                                <dd>通过短信的方式发送验证码到用户手机，用户接收后通过验证码的提交确认身份真实。</dd>
                            </dl>
                        </li>
                        <li>
                            <dl>
                                <dt><img src="<%=path%>/front/images1/img2.png" /></dt>
                                <dd class="tit">短信通知</dd>
                                <dd>通过短信的方式给用户发送订单通知、会议通知等行业应用类信息。</dd>
                            </dl>
                        </li>
                    </ul>
                    <p class="doc_link"><a href="http://docs.flypaas.com/doku.php?id=短信验证码_模板短信">查看短信开发文档</a></p>
                </div>    			
    		</div>
    	</div>

    	<div class="item_box box6">
    		<div class="item_box_wp">
    			<div class="message_2">
                    <ul>
                        <li class="li1">
                            <img src="<%=path%>/front/images1/img3.png" />
                        </li>
                        <li class="li2">
                            <dl class="dl1">
                                <dt><img src="<%=path%>/front/images1/img4.png" /></dt>
                                <dd>基于云平台自主开发的分发系统，监控短信服务内容及服务质量， 拥有99%身份验证服务到达率</dd>
                            </dl>
                            <dl class="dl2">
                                <dt><img src="<%=path%>/front/images1/img5.png" /></dt>
                                <dd>无需硬件支持，自主资源配置系统实时监控，实时响应资源峰值并发</dd>
                            </dl>
                        </li>
                    </ul>         
                </div>
    		</div>
    	</div>

    	<div class="item_box box7">
    		<div class="item_box_wp">
                <h4>实用场景</h4>
    			<div class="message_4">
                    <p><img src="<%=path%>/front/images1/img6.png" /></p>         
                </div>
    		</div>
    	</div>

        <%@include file="/front/foot1_0.jsp" %>
	</div>

	<!--主体部分 ft_content eof-->

	<%@include file="/front/foot1.jsp" %>
</body>
</html>