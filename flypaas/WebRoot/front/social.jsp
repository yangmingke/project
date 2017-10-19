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
    	<div class="ft_banner ft_banner8"></div>
    	<div class="item_box box20">
    		<div class="item_box_wp">                
                <div class="social_1">
                    <div class="txt">
                        <h6>即时通讯</h6>
                        <p>无论是真人社交领域或者陌生人社交领域，即时语音为社交提供直接便利的沟通方式。<br />基于IP通讯、运营商线路的语音需要处理专业复杂的编解码、线路自适应、<br />语音传输等技术障碍，基于云平台接口即时为社交应用提供IP语音、<br />多媒体文本、图片等信息。</p>
                        <p class="doc_link"><a href=http://docs.flypaas.com/doku.php?id=即时消息接口_android">查看即时消息接口</a></p>
                        <p class="img"><img src="<%=path%>/front/images1/img24.png" /></p>
                    </div>
                </div>    			
    		</div>
    	</div>

    	<div class="item_box box21">
            <div class="item_box_wp">                
                <div class="social_2">
                    <div class="txt">
                        <h6>实名认证</h6>
                        <p>通过短信验证码、语音验证码下发验证码的形式与<br />网络ID进行绑定，完成实名认证保障<br />熟人社交的真实性、可靠性。</p>
                        <p class="doc_link"><a href="http://docs.flypaas.com/doku.php?id=短信验证码_模板短信">查看短信验证码接口</a></p>
                        <p class="img"><img src="<%=path%>/front/images1/img25.png" /></p>
                    </div>
                </div>              
            </div>
        </div>

        <div class="item_box box22">
            <div class="item_box_wp">
                <div class="social_3">
                    <ul>
                        <li class="li1">
                            <dl>
                                <dt><img src="<%=path%>/front/images1/img26.png" /></dt>
                                <dd><b>结构化API</b></dd>
                                <dd>高度抽象的可扩展性API可通过API自定义传输结构化信息，不局限于常见文本、语音信息</dd>
                            </dl>
                        </li>
                        <li class="li2">
                            <dl>
                                <dt><img src="<%=path%>/front/images1/img27.png" /></dt>
                                <dd><b>传输安全</b></dd>
                                <dd>私有化传输协议结合分布式数据中心系统，系统上保障信息服务的传输安全和一致性</dd>
                            </dl>
                        </li>
                        <li class="li3">
                            <dl>
                                <dt><img src="<%=path%>/front/images1/img28.png" /></dt>
                                <dd><b>秒秒到达</b></dd>
                                <dd>可选主动、被动信息传输模式，满足信息实时更新、准时效率到达</dd>
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