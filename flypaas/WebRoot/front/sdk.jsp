<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%-- <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> --%>
<!DOCTYPE html>
<!-- saved from url=(0027)http://www.agora.io/cn/sdk/ -->
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>快传音视频开放平台_提供音视频通讯加速,一对一视频通话，视频直播等通讯加速API及SDK</title>
	<meta name="keywords" content="一对一音视频通话,音视频通讯,音视频通讯加速,音视频通话,在线教育,视频通话,视频会议,互联网语音,通讯加速,快传,快传技术," >
	<meta name="description" content="快传音视频通讯平台让开发者轻松接入一对一视频通话,音视频通讯加速,视频直播等能力,快速搭建音视频通讯,视频通话/会议应用,通讯加速,音视频引擎让视频更流畅。" >
   <%--  <%@include file="/front/resource1.jsp"%>
    <link rel="stylesheet" type="text/css" href="/front/css1/style-index_new.css">
    <script async="" src="<%=path%>/front/other/js/analytics.js">
    </script><script src="<%=path%>/front/other/js/hm.js"></script>
    <script src="<%=path%>/front/other/js/jquery.min.js"></script> --%>
</head>

<body id="b-06">
	<%-- <%@include file="/front/head1.jsp" %> --%>
    <div class="main" id="item-06">
        <div class="sdk__monitor">

            <h2 class="sdk__advantage__title">一个极简 SDK 全平台支持 &amp; 互通</h2>
            <div class="code__show">
                <script>
                    $(function() {
                        $('body').on('click', '.code__show__tab', function() {
                            $('.code__show__tab').removeClass('code__show-active');
                            $(this).addClass('code__show-active');
                            $('.code__show__item').hide().eq($(this).index()).show();
                        });
                    });
                </script>
                <div class="code__show__tabs">
                    <span class="code__show__tab">Android</span> |
                    <span class="code__show__tab">Web</span> |
                    <span class="code__show__tab">Windows</span> |
                    <span class="code__show__tab code__show-active">iOS / MacOS</span>
                </div>
                <div class="code__show__items">
                    <div class="code__show__item" style="display: none;">
                        <!--<span class="code-green"></span>
                        <span class="code-orange"></span>
                        <span class="code-blue"></span>
                        <span class="code-yellow"></span>
                        <span class="code-purple"></span>-->
                        <pre><span class="code-green">RtcEngine</span> rtcEngine = <span class="code-green">RtcEngine</span>.create(mContext, appId, mEngineEventHandler.mRtcEventHandler);
rtcEngine.joinChannel(<span class="code-purple">null</span>, channel, <span class="code-orange">"Extraoptional data"</span>, uid);
mRtcEngine.leaveChannel();
                        </pre>
                    </div>
                    <div class="code__show__item hide" style="display: none;">
                        <pre><span class="code-blue">var</span> client = AgoraRTC.createRtcClient();
client.init(appId, <span class="code-purple">function</span>() {
    client.join(appId, channel, <span class="code-orange">undefined</span>, successCallback, errorCallback);
}, errorCallback);
                        </pre>
                    </div>
                    <div class="code__show__item hide" style="display: none;">
                        <pre><span class="code-green">BOOL</span> <span class="code-yellow">CAgoraObject::JoinChannel</span>(LPCSTR lpChannelName, UINT nUID){
	<span class="code-blue">int</span> nRet = <span class="code-yellow">0</span>;
	nRet = m_lpAgoraEngineEx-&gt;joinChannel(<span class="code-blue">NULL</span>, lpChannelName, <span class="code-blue">NULL</span>, nUID);
	<span class="code-purple">return</span> nRet == <span class="code-yellow">0</span> ? <span class="code-blue">TRUE</span> : <span class="code-blue">FALSE</span>;
}
                        </pre>
                    </div>
                    <div class="code__show__item hide" style="display: block;">
                        <pre><span class="code-purple">let</span> engine = AgoraRtcEngineKit.sharedEngineWithAppId(<span class="code-orange">"AppId"</span>, delegate: self)
engine.enableVideo()
engine.joinChannelByKey(<span class="code-blue">nil</span>, channelName: <span class="code-orange">"channelName"</span>, info: <span class="code-blue">nil</span>, uid: <span class="code-yellow">0</span>, joinSuccess: <span class="code-blue">nil</span>)
                        </pre>
                    </div>
                </div>
            </div>
            <br/>
            <p class="sdk__advantage__desc"><span>API 接口丰富，灵活调用</span> | <span>最快 30 分钟可完成接入</span></p>
            <div class="flex sdk__advantage_items">
                <div class="flex__item"><i class="icon icon__repeat"></i><span>支持 Web 和 Native 互通</span></div>
                <div class="flex__item"><i class="icon icon__sdk__server"></i><span>5000+ 机型适配</span></div>
                <div class="flex__item"><i class="icon icon__phone"></i><span>跨平台支持</span></div>
            </div>
        </div>

        <div class="sdk__resolution">
            <div class="container flex">
                <div class="flex__item">
                    <div class="resolution__info">
                        <i class="icon icon__resolution"></i>
                        <h2 class="resolution__title">开放第三方接入</h2>
                        <p class="resolution__desc">支持第三方的功能API接入和自定义<br>滤镜、美颜、加密、人脸识别、鉴黄、卡通换脸等均支持第三方接入
                        </p>
                    </div>
                </div>

                <div class="flex__item">
                    <div class="sdk__resolution__featrues">
                        <div class="flex">
                            <div class="flex__item sdk__featrues__items">
                                <i class="icon icon__monitor"></i>
                                <span>滤镜</span>
                            </div>
                            <div class="flex__item sdk__featrues__items">
                                <i class="icon icon__woman"></i>
                                <span>美颜</span>
                            </div>
                            <div class="flex__item sdk__featrues__items">
                                <i class="icon icon__envelope"></i>
                                <span>加密</span>
                            </div>
                        </div>
                        <div class="flex">
                            <div class="flex__item sdk__featrues__items">
                                <i class="icon icon__emoji"></i>
                                <span>人脸识别</span>
                            </div>
                            <div class="flex__item sdk__featrues__items">
                                <i class="icon icon__unlink"></i>
                                <span>鉴黄</span>
                            </div>
                            <div class="flex__item sdk__featrues__items">
                                <i class="icon icon__face"></i>
                                <span>卡通换脸</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="sdk__monitor">
            <div class="container flex">
                <div class="sdk__monitor__media flex__item">
                    <img src="<%=path%>/front/other/img/sdk/monitor_show.png" alt="">
                </div>
                <div class="sdk__monitor__info flex__item">
                    <i class="icon icon__custom"></i>
                    <h2 class="video__custom__title">通话质量数据实时监控</h2>
                    <ul class="video__custom__list">
                        <li>· 通话质量数据</li>
                        <li>· 终端用户分布地区</li>
                        <li>· 通话设备平台分布</li>
                        <li>· 网络状况分布</li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="live__cdn">
            <div class="live__cdn--map">
                <div class="container flex">
                    <div class="flex__item">
                        <h2 class="server__title">SDK下载</h2>
                        <ul class="live__cdn__list">
                            <li>以PaaS开放平台的方式为开发者提供实时音视频通讯云加速SDK，开发者在flyPaaS平台上注册后，调用快传提供的云加速SDK，即可满足各种应用场景中的实时音视频通讯需求，开发者只需根据通讯产生的媒体转发总流量付费</li>
                            <li>使用github提供下载、使用指南等</li>
                            <li><br/></li>
                            <li>
                            <a href="https://github.com/" class="jump_github">前往github下载>></a>
                            </li>
                        </ul>
                    </div>
                    <div class="flex__item"></div>
                </div>
            </div>
        </div>

        <div class="container sdk__featrues flex">
            <div class="flex__item">
                <i class="icon icon__mic"></i>
                <div class="sdk__featrues__info">
                    <strong class="sdk__featrues__title">超宽频音质</strong>
                    <p class="sdk__featrues__desc">32kHz超宽频音质</p>
                    <p class="sdk__featrues__desc">将普通电话音质提高4倍</p>
                </div>
            </div>
            <div class="flex__item">
                <i class="icon icon__picture"></i>
                <div class="sdk__featrues__info">
                    <strong class="sdk__featrues__title">超高清画质</strong>
                    <p class="sdk__featrues__desc">最高支持720P超清画质，高清晰度低码率</p>
                    <p class="sdk__featrues__desc">与网络深度结合，基于人眼视觉体验质量优化</p>
                </div>
            </div>
        </div>

    </div>

    <script>
        $(function() {
            $('.btn').hover(function() {
                $(this).addClass('btn--gradient').removeClass('border--gradient');
            }, function() {
                $(this).addClass('border--gradient').removeClass('btn--gradient');
            })
        });
        $(function() {
            var tipsItem = $('.flow__tips__item'),
                hoverClassName = 'tips__item-active';
            $('.flow__tips__item').mouseover(function() {
                    tipsItem.removeClass(hoverClassName);
                    $(this).addClass(hoverClassName);
                })
                .mouseout(function() {
                    $(this).removeClass(hoverClassName);
                });
        });
    </script>

    <script src="<%=path%>/front/other/js/cookie.js"></script>
    <script>
        // select language
        $('#js_select_language').click(function() {
            var lang = $(this).data('language');
            Cookies.set('language', lang, {
                expires: 1024
            });
            window.location.href = window.location.origin + '/' + lang + '/';
        });
    </script>
    <script>
        var _hmt = _hmt || [];
        (function() {
            var hm = document.createElement("script");
            hm.src = "//hm.baidu.com/hm.js?71179bbad7c652f8289d970d29f36014";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(hm, s);
        })();
        (function(i, s, o, g, r, a, m) {
            i['GoogleAnalyticsObject'] = r;
            i[r] = i[r] || function() {
                (i[r].q = i[r].q || []).push(arguments)
            }, i[r].l = 1 * new Date();
            a = s.createElement(o), m = s.getElementsByTagName(o)[0];
            a.async = 1;
            a.src = g;
            m.parentNode.insertBefore(a, m)
        })(window, document, 'script', 'https://www.google-analytics.com/analytics.js', 'ga');
        ga('create', 'UA-76402370-1', 'auto');
        ga('send', 'pageview');
    </script>
	<%-- <%@include file="/front/foot1.jsp" %> --%>
</body>
</html>