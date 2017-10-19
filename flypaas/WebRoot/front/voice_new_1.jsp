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
	<script type="text/javascript">
		$(function(){
			location.hash = "#li1";
		});
	</script>
</head>
<body>
	<!--公共头部 ft_header bof-->
    <%@include file="/front/head1.jsp" %>

	<!--主体部分 ft_content bof-->
    <div class="ft_content">
    	<div class="ft_banner ft_banner2"></div>
    	<div class="item_box box9">
    		<div class="item_box_wp">
                <div class="title">
                    <h1>敏捷灵活的通信服务架构</h1>
                    <h2>可自由选择和配置适合不同客户场景的通信需求，无论是跨平台通话、还是同终端通信</h2>
                </div>
                <div class="voice_1">
                    <p class="img"><img src="<%=path%>/front/images1/img7.png" /></p>
                    <p class="doc_link"><a href="http://docs.flypaas.com/doku.php?id=语音通话接口_android">查看文档</a></p>
                </div>    			
    		</div>
    	</div>

    	<div class="item_box box10">
    		<div class="item_box_wp">
    			<div class="voice_2">
                    <ul>
                        <li class="li1" id="li1">
                            <div class="fold" style="display:none;">
                                <span class="img"></span>
                                <span class="txt">语音通知</span>
                            </div>
                            <div class="unfold" style="display:block">
                                <dl>
                                    <dt><img src="<%=path%>/front/images1/img10.png" /></dt>
                                    <dd>
                                        <b>语音通知<a href="http://docs.flypaas.com/doku.php?id=语音通知">查看接口文档>></a></b>
                                    </dd>
                                    <dd>使用语音外呼的模式将指定的语音呼入至接听人，可通过这种方式为针对性的客户提供会议通知、活动通知，并可通过API接口程序化控制呼出时间、呼出效果反馈</dd>
                                </dl>
                            </div>
                        </li>
                        <li class="li2">
                            <div class="fold">
                                <span class="img"></span>
                                <span class="txt">点击呼叫</span>
                            </div>
                            <div class="unfold">
                                <dl>
                                    <dt><img src="<%=path%>/front/images1/img42.png" /></dt>
                                    <dd>
                                        <b>点击呼叫<a href="http://docs.flypaas.com/doku.php?id=双向回拨">查看接口文档>></a></b>
                                    </dd>
                                    <dd>通过APP应用内按钮或浏览器网页按钮点击并发起IP通话、运营商线路通话服务，减少用户交互，提升用户体验</dd>
                                </dl>
                            </div>
                        </li>
                        <li class="li3">
                            <div class="fold">
                                <span class="img"></span>
                                <span class="txt">直拨通话</span>
                            </div>
                            <div class="unfold">
                                <dl>
                                    <dt><img src="<%=path%>/front/images1/img49.png" /></dt>
                                    <dd>
                                        <b>直拨通话<a href="http://docs.flypaas.com/doku.php?id=voip开发指南&s[]=直拨">查看接口文档>></a></b>
                                    </dd>
                                    <dd>无论是智能终端、浏览器模式，通过APP或者网页发起通话，接通方为手机用户或固话用户，常见集成至与企业服务相关的移动应用、企业客服座席。</dd>
                                </dl>
                            </div>
                        </li>
                        <li class="li4">
                            <div class="fold">
                                <span class="img"></span>
                                <span class="txt">回拨通话</span>
                            </div>
                            <div class="unfold">
                                <dl>
                                    <dt><img src="<%=path%>/front/images1/img50.png" /></dt>
                                    <dd>
                                        <b>回拨通话<a href="http://docs.flypaas.com/doku.php?id=双向回拨">查看接口文档>></a></b>
                                    </dd>
                                    <dd>同时通过平台方发起主叫和被叫双方，定制通话方满足不同需求的客户服务，企业服务易可根据具体业务需求为客户提供定制服务</dd>
                                </dl>
                            </div>
                        </li>
                        <li class="li5">
                            <div class="fold">
                                <span class="img"></span>
                                <span class="txt">互联网通话</span>
                            </div>
                            <div class="unfold">
                                <dl>
                                    <dt><img src="<%=path%>/front/images1/img51.png" /></dt>
                                    <dd>
                                        <b>互联网通话<a href="http://docs.flypaas.com/doku.php?id=voip开发指南&s[]=直拨">查看接口文档>></a></b>
                                    </dd>
                                    <dd>基于互联网纯网络通话，无运营商和地域限制，拥有更清晰的语音质量，支持语音三方密钥的加密传输</dd>
                                </dl>
                            </div>
                        </li>
                        <li class="li6">
                            <div class="fold">
                                <span class="img"></span>
                                <span class="txt">语音会议</span>
                            </div>
                            <div class="unfold">
                                <dl>
                                    <dt><img src="<%=path%>/front/images1/img52.png" /></dt>
                                    <dd>
                                        <b>语音会议<a href="http://docs.flypaas.com/doku.php?id=语音群聊">查看接口文档>></a></b>
                                    </dd>
                                    <dd>同时桥接多人基于IP、电话语音的会议服务，基于API控制会议时长、成员邀请、禁音、移除等功能。</dd>
                                </dl>
                            </div>
                        </li>
                    </ul>         
                </div>
    		</div>
    	</div>

    	<div class="item_box box11" id="item_box box11">
    		<div class="item_box_wp">
                <div class="title">
                    <h1>丰富的附加通讯服务</h1>
                    <h2>为通讯服务提供更具针对性的服务，可供隐私保护（身份验证、私密通话），监控客服服务质量的（通话录音）</h2>
                </div>
    			<div class="voice_3">
                    <ul>
                        <li class="li1">
                            <dl>
                                <dt></dt>
                                <dd><b>语音验证码</b></dd>
                                <dd>通过API接口控制平台以语音播放验证码的方式校验用户身份</dd>
                                <dd><a href="http://docs.flypaas.com/doku.php?id=语音验证码">查看文档 >></a></dd>
                            </dl>
                        </li>
                        <li class="li2">
                            <dl>
                                <dt></dt>
                                <dd><b>语音群聊</b></dd>
                                <dd>基于互联网纯网络语音多方语音沟通服务</dd>
                                <dd><a href="http://docs.flypaas.com/doku.php?id=语音群聊">查看文档 >></a></dd>
                            </dl>
                        </li>
                        <li class="li3">
                            <dl>
                                <dt></dt>
                                <dd><b>语音呼转</b></dd>
                                <dd>提供国内多个城市号码租赁服务，可为移动手机号提供语音呼转服务</dd>
                                <dd><a href="http://docs.flypaas.com/doku.php?id=开启呼转业务">查看文档 >></a></dd>
                            </dl>
                        </li>
                        <li class="li4">
                            <dl>
                                <dt></dt>
                                <dd><b>通话录音</b></dd>
                                <dd>监控客户服务质量，对客户语音应答过程录音，便于录音存档，电子侦查</dd>
                                <dd><a href="http://docs.flypaas.com/doku.php?id=呼叫鉴权请求接口&s[]=录音">查看文档 >></a></dd>
                            </dl>
                        </li>
                    </ul>     
                </div>
    		</div>
    	</div>

        <div class="ft_banner ft_banner3"></div>

        <%@include file="/front/foot1_0.jsp" %>
	</div>

	<!--主体部分 ft_content eof-->

	<%@include file="/front/foot1.jsp" %>
</body>
</html>