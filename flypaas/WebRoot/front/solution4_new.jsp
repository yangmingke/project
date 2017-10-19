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
<body id="b-03">
	<!--公共头部 ft_header bof-->
    <%@include file="/front/head1.jsp" %>

	<!--主体部分 ft_content bof-->
    <div class="ft_content">
    	<div class="ft_banner ft_banner15"></div>
    	<div class="item_box box39">
    		<div class="item_box_wp">
                <div class="title">
                    <h1>语音、短信通达全球</h1>
                    <h2>全球互联时代，基于API接口轻松为应用接入全球语音、短信服务，服务全球客户，拓展全球业务</h2>
                </div>
                <div class="solution_9">
                    <ul>
                        <li class="li1">
                            <dl>
                                <dt><img src="<%=path%>/front/images1/img47.png" /></dt>
                                <dd>语音：230+国家或地区到达</dd>
                            </dl>
                        </li>
                        <li class="li2">
                            <dl>
                                <dt><img src="<%=path%>/front/images1/img48.png" /></dt>
                                <dd>短信：215+国家或地区到达</dd>
                            </dl>
                        </li>
                    </ul>
                    <p class="doc_link"><a href="<%=path%>/price">查看国际资费</a></p>
                </div>    			
    		</div>
    	</div>

    	<div class="item_box box40">
    		<div class="item_box_wp">
                <h4>漫游全球解决方案</h4>
    			<div class="solution_10">
                    <ul>
                        <li class="li1">
                            <dl>
                                <dt></dt>
                                <dd class="tit">号码服务</dd>
                                <dd>提供国内多个城市本地号码租赁服务，供漫游期间呼转使用，真正享受本地资费</dd>
                            </dl>
                        </li>
                        <li class="li2">
                            <dl>
                                <dt></dt>
                                <dd class="tit">语音留言</dd>
                                <dd>通过API在线集成平台录音接口，可为客户提供离线语音信箱服务</dd>
                            </dl>
                        </li>
                        <li class="li3">
                            <dl>
                                <dt></dt>
                                <dd class="tit">跨平台SDK</dd>
                                <dd>提供成熟SDK封装方案，减少业务开发时间，快速服务业务</dd>
                            </dl>
                        </li>
                        <li class="li4">
                            <dl>
                                <dt></dt>
                                <dd class="tit">在线、离线</dd>
                                <dd>允许客户自由选择服务在线或离线</dd>
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