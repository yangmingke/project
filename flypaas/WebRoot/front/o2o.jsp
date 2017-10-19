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
    	<div class="ft_banner ft_banner9"></div>
    	<div class="item_box box23">
    		<div class="item_box_wp">                
                <div class="o2o_1">
                    <div class="txt">
                        <h5>订单跟踪</h5>
                        <p>集成云平台短信通知服务，当客户通过线上完成选择并下单支付后，订单信息可通过<br />短信或语音等形式下发至用户的联系手机号，实时通知客户订单的状态。</p>
                        <p class="img"><img src="<%=path%>/front/images1/img30.png" /></p>
                    </div>
                    <div class="txt txt1">
                        <h6>三网到达</h6>
                        <p>支持移动、电信、联通三网短信到达，<br />并提供冗余资源保障服务</p>
                        <p class="doc_link"><a href="http://docs.flypaas.com/doku.php?id=模板短信简易接口">查看短信接口</a></p>
                    </div>
                </div>    			
    		</div>
    	</div>

    	<div class="item_box box23 box24">
            <div class="item_box_wp">                
                <div class="o2o_1">
                    <div class="txt">
                        <h5>轻量级移动客服</h5>
                        <p>为O2O产品应用增加移动客服服务----即时消息，开放平台为商家和用户解决网络传输<br />问题，开发者通过开放型API自定义客服服务。可通过集成移动SDK接入第三方系统、<br />知识库、CRM等成熟系统。</p>
                        <p class="img"><img src="<%=path%>/front/images1/img31.png" /></p>
                    </div>
                    <div class="txt">
                        <ul>
                            <li class="li1">
                            <dl>
                                <dt><img src="<%=path%>/front/images1/img56.png" /></dt>
                                <dd><b>可扩展API</b></dd>
                                <dd>支持富媒体消息，支持依据业务逻辑定义消息类型</dd>
                            </dl>
                        </li>
                        <li class="li2">
                            <dl>
                                <dt><img src="<%=path%>/front/images1/img32.png" /></dt>
                                <dd><b>跨平台扩展</b></dd>
                                <dd>提供成熟iOS、Android、Windows SDK开发包，支持Rest API跨平台扩展</dd>
                            </dl>
                        </li>
                        </ul>
                        <p class="doc_link"><a href="http://docs.flypaas.com/doku.php?id=即时消息接口_android">查看IM消息接口</a></p>
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