<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%-- <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> --%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<!-- <meta content="互联网语音，融合通讯，融合云通讯，即时通讯，IM，视频通讯，语音验证码，VOIP，网络电话，云通讯" name="keywords">
	<meta content="快传融合通讯开放平台让开发者能够轻松构建语音，短信，VoIP，视频等解决方案，无硬件，网络成本，快速搭建和使用电信级通讯能力，让应用都插上通讯的翅膀" name="description">
	<title>快传融合通讯开放平台_提供融合语音，短信，VoIP，视频和IM等通讯API及SDK。</title> -->
	<title>快传音视频开放平台_提供音视频通讯加速,一对一视频通话，视频直播等通讯加速API及SDK</title>
	<meta name="keywords" content="一对一音视频通话,音视频通讯,音视频通讯加速,音视频通话,在线教育,视频通话,视频会议,互联网语音,通讯加速,快传,快传技术," >
	<meta name="description" content="快传音视频通讯平台让开发者轻松接入一对一视频通话,音视频通讯加速,视频直播等能力,快速搭建音视频通讯,视频通话/会议应用,通讯加速,音视频引擎让视频更流畅。" >
	<%-- <%@include file="/front/resource1.jsp"%> --%>
</head>
<body id="b-02">
	<!--公共头部 ft_header bof-->
    <%-- <%@include file="/front/head1.jsp" %> --%>

	<!--主体部分 ft_content bof-->
    <div class="ft_content" id="item-02">
    	<div class="ft_banner ft_banner2"></div>
    	<div class="item_box box9">
    		<div class="item_box_wp">
                <div class="title">
                    <!-- <h1>敏捷灵活的通信服务架构</h1>
                    <h2>可自由选择和配置适合不同客户场景的通信需求，无论是跨平台通话、还是同终端通信</h2> -->
                    <h1>敏捷灵活的通信服务</h1>
                    <h2>自主研发的音视频引擎, 语音增强模块支持AEC、NS、AGC，对适应网络环境的遍历测试和深度开发</h2>
                    <h2>让我们的AEC、抖动缓冲、前向纠错和丢帧补偿技术走在行业前端，能快速适应弱信号和网络的动态切换</h2>
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
                                <span class="txt">音质提高</span>
                            </div>
                            <div class="unfold" style="display:block">
                                <dl>
                                    <dt><img src="<%=path%>/front/images1/img10.png" /></dt>
                                    <dd>
                                        <b>音质提高<a href="http://docs.flypaas.com/doku.php?id=语音通知">查看接口文档>></a></b>
                                    </dd>
                                    <dd>使支持g729、amr-nb、silk、opus、aac编解码。支持32kHz超宽频音质，将普通电话音质提高4倍，网络电话音质提高1倍。码率更低，音质更好</dd>
                                </dl>
                            </div>
                        </li>
                        <li class="li2">
                            <div class="fold">
                                <span class="img"></span>
                                <span class="txt">回声消除</span>
                            </div>
                            <div class="unfold">
                                <dl>
                                    <dt><img src="<%=path%>/front/images1/img42.png" /></dt>
                                    <dd>
                                        <b>回声消除<a href="http://docs.flypaas.com/doku.php?id=双向回拨">查看接口文档>></a></b>
                                    </dd>
                                    <dd>覆盖全平台的回声消除算法，收敛快，ERLE高，保证通话不受干扰</dd>
                                </dl>
                            </div>
                        </li>
                        <li class="li3">
                            <div class="fold">
                                <span class="img"></span>
                                <span class="txt">降噪</span>
                            </div>
                            <div class="unfold">
                                <dl>
                                    <dt><img src="<%=path%>/front/images1/img49.png" /></dt>
                                    <dd>
                                        <b>降噪<a href="http://docs.flypaas.com/doku.php?id=voip开发指南&s[]=直拨">查看接口文档>></a></b>
                                    </dd>
                                    <dd>自适应各种平稳、非平稳噪声环境，让通话者专注于通话，不用担心身处的环境</dd>
                                </dl>
                            </div>
                        </li>
                        <li class="li4">
                            <div class="fold">
                                <span class="img"></span>
                                <span class="txt">丢包对抗</span>
                            </div>
                            <div class="unfold">
                                <dl>
                                    <dt><img src="<%=path%>/front/images1/img50.png" /></dt>
                                    <dd>
                                        <b>丢包对抗<a href="http://docs.flypaas.com/doku.php?id=双向回拨">查看接口文档>></a></b>
                                    </dd>
                                    <dd>先进的丢包恢复技术，前向纠错后端隐藏，20%丢包无感，50%丢包仍可正常沟通</dd>
                                </dl>
                            </div>
                        </li>
                        <li class="li5">
                            <div class="fold">
                                <span class="img"></span>
                                <span class="txt">啸叫控制</span>
                            </div>
                            <div class="unfold">
                                <dl>
                                    <dt><img src="<%=path%>/front/images1/img51.png" /></dt>
                                    <dd>
                                        <b>啸叫控制<a href="http://docs.flypaas.com/doku.php?id=voip开发指南&s[]=直拨">查看接口文档>></a></b>
                                    </dd>
                                    <dd>自动啸叫检测和抑制，近场也不会出现尖锐的杂音</dd>
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
                    <!-- <h1>丰富的附加通讯服务</h1>
                    <h2>为通讯服务提供更具针对性的服务，可供隐私保护（身份验证、私密通话），监控客服服务质量的（通话录音）</h2> -->
                    <h1>高质量的语音通话</h1>
                    <h2>适配各种终端、各种网络，低延迟、不卡顿、高音质、无回声、强降噪效果</h2>
                    <h2>编解码支持g729、amr-nb、silk、opus、aac，全频带codec支持，码率动态切换</h2>
                    <h2>从较差到优异的网络环境，引擎实时保证延时、流畅和音质的平衡，最大保证通过过程的用户体验</h2>
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

        <!-- <div class="ft_banner ft_banner3"></div> -->

        <%-- <%@include file="/front/foot1_0.jsp" %> --%>
	</div>

	<!--主体部分 ft_content eof-->

	<%-- <%@include file="/front/foot1.jsp" %> --%>
</body>
</html>