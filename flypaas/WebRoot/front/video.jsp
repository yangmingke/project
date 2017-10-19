<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%-- <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> --%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>快传音视频开放平台_提供音视频通讯加速,一对一视频通话，视频直播等通讯加速API及SDK</title>
	<meta name="keywords" content="一对一音视频通话,音视频通讯,音视频通讯加速,音视频通话,在线教育,视频通话,视频会议,互联网语音,通讯加速,快传,快传技术," >
	<meta name="description" content="快传音视频通讯平台让开发者轻松接入一对一视频通话,音视频通讯加速,视频直播等能力,快速搭建音视频通讯,视频通话/会议应用,通讯加速,音视频引擎让视频更流畅。" >
	<%-- <%@include file="/front/resource1.jsp"%> --%>
</head>
<body id="b-03">
	<!--公共头部 ft_header bof-->
    <%-- <%@include file="/front/head1.jsp" %> --%>

	<!--主体部分 ft_content bof-->
    <div class="ft_content" id="item-03">
    	<div class="ft_banner ft_banner5"></div>
    	<div class="item_box box14">
    		<div class="item_box_wp">
                <div class="title">
                    <h1>更具吸引力的沟通体验</h1>
                    <h2>实时高清视频接口，跨平台提供融合实时视频、语音、文本、共享屏幕等功能</h2>
                </div>
                <div class="video_1">
                    <p class="img"><img src="<%=path%>/front/images1/img17.png" /></p>
                    <ul>
                        <li class="video_1_left">
                            <dl>
                                <dt>更简单</dt>
                                <dd>基于云平台开放接口支持视频流量快速接入、快速分发，开发者不需要专注硬件和网络基础建设，快速搭建业务以应对复杂的市场环境</dd>
                            </dl>
                        </li>
                        <li class="video_1_right">
                            <dl>
                                <dt>更快速</dt>
                                <dd>提供基于移动终端iOS、Android的成熟SDK封装方案，和基于WebRTC跨浏览器PC平台的标准接口接入，开发者只需专注业务逻辑处理</dd>
                            </dl>
                        </li>
                    </ul>
                </div>    			
    		</div>
    	</div>

    	<div class="item_box box15">
    		<div class="item_box_wp">
    			<div class="video_2">
                    <ul>
                        <li>
                            <dl>
                                <%-- <dt>iOS SDK</dt>
                                <dd class="img"><img src="<%=path%>/front/images1/ios_icon.png" /></dd>
                                <dd>支持 iOS 6.0+, iPhone 4S+, iPod Touch 5+, iPad 2+, iPad Mini 以上设备</dd> --%>
                                <dt>iOS SDK</dt>
                                <dd class="img"><img src="<%=path%>/front/images1/ios_icon.png" /></dd>
                                <dd>支持H.264、自研H.265软编码技术。H.265在与同分辨率和码流上，可减少带宽30%以上</dd>
                            </dl>
                        </li>
                        <li>
                            <dl>
                                <%-- <dt>Android SDK</dt>
                                <dd class="img"><img src="<%=path%>/front/images1/android_icon.png" /></dd>
                                <dd>支持Android 4.0+ 以上设备</dd> --%>
                                <dt>Android SDK</dt>
                                <dd class="img"><img src="<%=path%>/front/images1/android_icon.png" /></dd>
                                <dd>支持android、ios硬件编解码，cpu及内存开销显著减少，手机不发烫，减少能量消耗</dd>
                            </dl>
                        </li>
                        <li>
                            <dl>
                                <%-- <dt>Web SDK</dt>
                                <dd class="img"><img src="<%=path%>/front/images1/windows_icon.png" /></dd>
                                <dd>支持WebRTC Chrome、firefox 、以上浏览器，IE浏览器需要安装插件</dd> --%>
                                <dt>Web SDK</dt>
                                <dd class="img"><img src="<%=path%>/front/images1/windows_icon.png" /></dd>
                                <dd>支持网络状况探测，视频码率动态调整，深度优化的FEC和NACK抗丢包技术，保证3G/4G/wifi等各种网络状态下的视频画质与流畅度</dd>
                            </dl>
                        </li>
                    </ul>         
                </div>
    		</div>
    	</div>

        <div class="item_box box16">
            <div class="item_box_wp">
                <h4>常见应用领域</h4>
            </div>          
            <div class="item_box_img"><img src="<%=path%>/front/images1/img55.png" width="100%" /></div>  
        </div>

        <%-- <%@include file="/front/foot1_0.jsp" %> --%>
	</div>

	<!--主体部分 ft_content eof-->

	<%-- <%@include file="/front/foot1.jsp" %> --%>
</body>
</html>