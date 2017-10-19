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
</head>
<body id="b-02">
	<!--公共头部 ft_header bof-->
    <%@include file="/front/head1.jsp" %>

	<!--主体部分 ft_content bof-->
    <div class="ft_content">
    	<div class="ft_banner ft_banner4"></div>
    	<div class="item_box box12">
    		<div class="item_box_wp">
                <div class="title">
                    <h1>应需扩展，自由定义</h1>
                    <h2>无论是集成至APP或APP对接系统平台，都允许基于API无缝扩展即时通讯语音、视频、推送等服务</h2>
                </div>
                <div class="im_1">
                    <ul>
                        <li>
                            <dl>
                                <dt><img src="<%=path%>/front/images1/img15.png" /></dt>
                                <dd class="tit">单聊</dd>
                                <dd>支持千万级同时并发的消息系统服务，依赖全国各大数据接入点，拥有低延迟率，自定义可扩展接口，可便捷的与现有客服系统直接对接</dd>
                            </dl>
                        </li>
                        <li>
                            <dl>
                                <dt><img src="<%=path%>/front/images1/img16.png" /></dt>
                                <dd class="tit">群聊</dd>
                                <dd>支持百人群组的即时消息推送服务，开放式架构服务应需扩展，与平台统一接口可随时扩展基于IP、PSTN电话服务，支持跨平台架构搭建</dd>
                            </dl>
                        </li>
                    </ul>
                </div>    			
    		</div>
    	</div>

    	<div class="item_box box13">
    		<div class="item_box_wp">
    			<div class="im_2">
                    <ul>
                        <li class="li1">
                            <dl>
                                <dt><img src="<%=path%>/front/images1/img11.png" /></dt>
                                <dd>支持文本、图片、语音、视频或者是结构化数据，允许自定义结构化数据创造出与众不同的应用产品</dd>
                            </dl>
                        </li>
                        <li class="li2">
                            <dl>
                                <dt><img src="<%=path%>/front/images1/img12.png" /></dt>
                                <dd>依赖平台统一分发能力，支持跨平台SDK集成，可快速集成至移动终端（iOS、Android、Web）</dd>
                            </dl>
                        </li>
                        <li class="li3">
                            <dl>
                                <dt><img src="<%=path%>/front/images1/img13.png" /></dt>
                                <dd>支持离线缓存信息，账号未上线时，可通过接口控制平台存储、上线下发消息</dd>
                            </dl>
                        </li>
                        <li class="li4">
                            <dl>
                                <dt><img src="<%=path%>/front/images1/img14.png" /></dt>
                                <dd>支持集成系统推送服务以及第三方推送服务</dd>
                            </dl>
                        </li>
                    </ul>         
                </div>
    		</div>
    	</div>

        <%@include file="/front/foot1_0.jsp" %>
	</div>

	<!--主体部分 ft_content eof-->

	<%@include file="/front/foot1.jsp" %>
</body>
</html>