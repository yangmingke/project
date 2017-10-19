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
        <div class="ft_banner ft_banner14"></div>
        <div class="item_box solution_box box37">
            <div class="item_box_wp">                
                <div class="solution_7">
                    <div class="txt">
                        <h5>实时保护客户隐私</h5>
                        <p>无论是房产、医疗、租车、电子商务等领域，通讯号码作为常用联系客户的方式，<br />保障客户联系方式的个人隐私尤为重要。快传为企业服务提供完善的解决方案，满足商业服务的同时保障用户个人隐私。标识用户联系方式不再需要显示客户号码，取而代之为“虚拟按钮”，在分类信息、个人售卖、租车等公共领域用户之间的沟通只需沟通发起方点击“虚拟按钮”便可连通双方进行语音沟通。</p>
                        <p class="doc_link"><a href="http://docs.flypaas.com/doku.php?id=语音通知">查看完整的语音服务</a></p>
                        <p class="img"><img src="<%=path%>/front/images1/img45.png" /></p>
                    </div>
                </div>              
            </div>
        </div>

        <div class="item_box solution_box box38">
            <div class="item_box_wp">                
                <div class="solution_8">
                    <div class="txt">
                        <h5>匿名呼叫</h5>
                        <p>点击呼叫的过程同时为客户去除“来电显示”服务，只为客户提供呼叫语音并且保护号码的显示 大部分客户信息的泄漏为中间服务环节，为防止中间环节泄漏客户信息，针对内部人员隐藏最终用户的个人隐私信息也是保护隐私的方式。</p>
                        <p class="img"><img src="<%=path%>/front/images1/img53.png" /></p>
                    </div>
                </div>              
            </div>
        </div>

        <div class="item_box solution_box box43">
            <div class="item_box_wp">                
                <div class="solution_11">
                    <div class="txt">
                        <h5>基于IP语音的加密服务</h5>
                        <p class="p1">硬件级：基于硬件芯片级加解密服务可用于定制安全手机等硬件设备</p>
                        <p class="p1">软件级：以通信协议及动态无障碍更新的方式，IP语音的网络传输除采用公认安全级别的通信协议外，也可支持采用三方密钥的加解码服务增强通信安全，保障用户隐私。</p>
                        <p class="img"><img src="<%=path%>/front/images1/img54.png" /></p>
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