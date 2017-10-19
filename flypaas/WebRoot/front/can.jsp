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
    <link rel="stylesheet" type="text/css" href="/front/css1/style-index_new.css">
    <script src="<%=path%>/front/other/js/analytics.js"></script>
    <script src="<%=path%>/front/other/js/hm.js"></script>
    <%-- <script src="<%=path%>/front/other/js/jquery.min.js"></script> --%>
</head>

<body id="b-04">
	<%-- <%@include file="/front/head1.jsp"%> --%>
    <div class="main" id="item-04">
		<div class="network__wrold">
            <div class="network__wrold--map">
                <div class="container flex">
                    <div class="flex__item"></div>
                    <div class="network__wrold__info flex__item">
                        <i class="icon icon__earth__white"></i>
                        <h2 class="video__custom__title">CAN</h2>
                        <p class="video__custom__desc">CAN（Communication Accelerate  Network）通讯加速网络依托国内外CDN/IDC、云平台和运营商的共享网络节点，以开放源码的方式，部署成为媒体转发节点（RTPP：含节点注册、媒体转发和流量统计功能），形成遍及全球的分布式智能媒体转发网络。</p>
                        <p class="video__custom__desc">通过PaaS开放平台的导流，帮助资源方提升网络节点资源的复用率，并且根据RTPP的出口总流量进行计费。</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="network__resolution">
            <div class="container flex">
                <div class="flex__item">
                    <div class="resolution__info">
                        <i class="icon icon__netword__chart"></i>
                        <h2 class="resolution__title">超低延时</h2>
                        <p class="resolution__desc">全球端到端，平均延时仅 76ms</p>
                        <p class="resolution__desc">通过拥有多项国际专利的独特Last Mile算法</p>
                        <p class="resolution__desc">保证在网络基础条件落后地区，或极端网络环境下</p>
                        <p class="resolution__desc">通话质量远高于业内平均水平，通话服务稳定性强</p>
                    </div>
                </div>

                <div class="flex__item">
                    <div class="network__resolution__media">
                        <div class="video__advantage__block flex__item">
                            <h5 class="video__advantage__title">低延迟</h5>
                            <div class="row__title">
                                <div class="row">
                                    <div class="col">&nbsp;传输路线</div>
                                    <div class="col">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;P2P</div>
                                    <div class="col">&nbsp;&nbsp;快传 flyPaaS</div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col">亚洲－中国</div>
                                <div class="col align-center">256ms</div>
                                <div class="col align-center">113ms</div>
                            </div>
                            <div class="row">
                                <div class="col">中国－北美</div>
                                <div class="col align-center">226ms</div>
                                <div class="col align-center">135ms</div>
                            </div>
                            <div class="row">
                                <div class="col">欧洲－中国</div>
                                <div class="col align-center">212ms</div>
                                <div class="col align-center">156ms</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="network__wrold">
            <div class="network__wrold--map">
                <div class="container flex">
                    <div class="flex__item"></div>
                    <div class="network__wrold__info flex__item">
                        <i class="icon icon__earth__white"></i>
                        <h2 class="video__custom__title">广域全球</h2>
                        <p class="video__custom__desc">全球部署近 100 个数据中心</p>
                        <p class="video__custom__desc">支持全球 200+ 国家和地区通话</p>
                    </div>
                </div>
            </div>
        </div>

        <div class="video__server network__video__server">
            <div class="container flex">
                <div class="flex__item network__server">
                    <i class="icon icon__safe"></i>
                    <h2 class="server__title">抗丢包、抗抖动<br>弱网环境也能通信</h2>
                    <p class="server__desc">就近接入：全球部署，分布式架构，让用户就近接入</p>
                    <p class="server__desc">动态路由：实时监控传输线路动态，选择最通畅线路进行传输</p>
                    <p class="server__desc">丢包重传：通过重传机制恢复丢失数据包，有效降低丢包率</p>
                </div>

                <div class="flex__item">
                    <div class="network__resolution__media right">
                        <div class="video__advantage__block flex__item">
                            <h5 class="video__advantage__title">超强抗丢包</h5>
                            <div class="row__title">
                                <div class="row">
                                    <div class="col">&nbsp;传输路线</div>
                                    <div class="col">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;丢包率</div>
                                    <div class="col">&nbsp;&nbsp;快传 flyPaaS</div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col">亚洲－中国</div>
                                <div class="col align-center">70.1%</div>
                                <div class="col align-center">0.8%</div>
                            </div>
                            <div class="row">
                                <div class="col">中国－北美</div>
                                <div class="col align-center">73.6%</div>
                                <div class="col align-center">0.6%</div>
                            </div>
                            <div class="row">
                                <div class="col">欧洲－中国</div>
                                <div class="col align-center">87.3%</div>
                                <div class="col align-center">0.8%</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- <div class="network__compare">
            <div class="container">
                <h3 class="network__compare__title">可靠性为同类产品最佳</h3>
                <div class="flex">
                    <div class="flex__item">
                        <i class="icon icon__clock"></i>
                        <p class="compare__item__title">高可用</p>
                        <p class="compare__item__desc">端到端主动监测，7x24 小时监控可用性</p>
                        <p class="compare__item__desc">网络一旦故障，动态路由自动避开</p>
                        <p class="compare__item__desc">呼叫连通率达 99.9%，可用度达 99.99%</p>
                    </div>
                    <div class="flex__item">
                        <i class="icon icon__line__hart"></i>
                        <p class="compare__item__title">QoE 体验质量保障</p>
                        <p class="compare__item__desc">7x24 小时 QoE 体验质量保障</p>
                        <p class="compare__item__desc">突破跨国、跨网通信屏障</p>
                    </div>
                    <div class="flex__item">
                        <i class="icon icon__folder"></i>
                        <p class="compare__item__title">端到端加密</p>
                        <p class="compare__item__desc">只传输不存储，100%安全</p>
                        <p class="compare__item__desc">全网 256 加密，美国 HIPAA 安全认证</p>
                    </div>
                </div>
            </div>
        </div> -->

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