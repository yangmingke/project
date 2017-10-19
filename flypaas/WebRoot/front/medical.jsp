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
    	<div class="ft_banner ft_banner11"></div>
    	<div class="item_box box28">
    		<div class="item_box_wp">                
                <div class="medical_1">
                    <div class="txt">
                        <h6>多方通话</h6>
                        <p>基于跨平台、跨网络的实时多方通话，可同时链接IP网络、<br />运营商通话线路，桥接多方通话。拥有跨平台下一致的通话语音技术，<br />可通过API接口实时链入和控制通话服务。满足多种复杂环境下患者和医生的即时沟通。</p>
                        <p class="img"><img src="<%=path%>/front/images1/img37.png" /></p>
                    </div>
                </div>    			
    		</div>
    	</div>

    	<div class="item_box box28 box29">
            <div class="item_box_wp">                
                <div class="medical_1 medical_2">
                    <div class="txt">
                        <h6>跨平台视频</h6>
                        <p>通过iOS、Android、Web跨平台视频SDK可以为患者和医生<br />提供实时视频服务，并且支持VP8、H264主动自适应<br />线路宽带的高清在线视频编码方案。<br />无关硬件设备，免除部署单独购买硬件的烦恼，<br />提高业务快速上线能力。</p>
                        <p class="img"><img src="<%=path%>/front/images1/img38.png" /></p>
                    </div>
                </div>              
            </div>
        </div>

        <div class="item_box box30">
            <div class="item_box_wp">                
                <div class="medical_3">
                    <div class="txt">
                        <h5>预约通知</h5>
                        <p class="large">以短信、语音的形式提前将视频会议预约通知医生和患者</p>
                        <dl class="dl1">
                            <dt>三网到达</dt>
                            <dd>支持移动、电信、联通三网短信到达，并提供冗余资源保障服务</dd>
                        </dl>
                        <dl class="dl2">
                            <dt>可扩展API</dt>
                            <dd>支持富媒体消息，支持依据业务逻辑定义消息类型</dd>
                        </dl>
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