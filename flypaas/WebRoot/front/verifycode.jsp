<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<title>快传音视频开放平台_提供音视频通讯加速,一对一视频通话，视频直播等通讯加速API及SDK</title>
	<meta name="keywords" content="一对一音视频通话,音视频通讯,音视频通讯加速,音视频通话,在线教育,视频通话,视频会议,互联网语音,通讯加速,快传,快传技术," >
	<meta name="description" content="快传音视频通讯平台让开发者轻松接入一对一视频通话,音视频通讯加速,视频直播等能力,快速搭建音视频通讯,视频通话/会议应用,通讯加速,音视频引擎让视频更流畅。" >
    <%@include file="/front/resource.jsp" %>
</head>
</head>
<body>
	<!--公共头部header bof-->
    <%@include file="/front/head.jsp" %>

    <!--公共头部header eof--> 

	<!--主体部分 ft_content bof-->
    <div class="ft_content">
    	<div class="ft_banner1 ft_banner12">
    		<div class="ft_banner_wp">
    			<div class="txt">
    				<h1>验证码平台</h1>
    				<h2>验证码平台主要是通过短信、语音以及智能验证对用户身份进行验证，确保用户操作的安全性，广泛用于网站注册、网上支付、找回密码、用户账户认证、绑定手机号码等业务。我们提供专业验证码服务，保证验证码高到达。</h2>
                    <div class="list">
                        <span class="icon icon1">&nbsp;</span>
                        <span class="icon icon2">&nbsp;</span>
                        <span class="icon icon3">&nbsp;</span>
                        <span class="icon icon4">&nbsp;</span>
                        <span class="icon icon5">&nbsp;</span>
                    </div>
    			</div>
    		</div>
    	</div>
    	<div class="item_box box6 box62">
    		<div class="item_box_wp">
    			<div class="pro_adv_box">
                    <img src="<%=path%>/front/images/pro_adv5.png" alt="验证码" />
                    <div class="pro_adv_txt pro_adv_txt1">
                        <!--<b>专业</b>
                        <p>专注于提供语音、短信验证码通道服务</p>-->
                        <span>用户注册</span>
                    </div>
                    <div class="pro_adv_txt pro_adv_txt2">
                        <!--<b>无扣量</b>
                        <p>实量发送，诚信为本</p>-->
                        <span>手机下载短信优惠券</span>
                    </div>
                    <div class="pro_adv_txt pro_adv_txt3">
                        <!--<b>低拦截</b>
                        <p>与手机安全软件商紧密合作，短信/语音无拦截</p>-->
                        <span>在线支付验证</span>
                    </div>
                    <div class="pro_adv_txt pro_adv_txt4">
                        <!--<b>高到达</b>
                        <p>行业最高到达率，5秒接收，到达率高达99%</p>-->
                        <span>物流提醒手机验证</span>
                    </div>
                    <div class="pro_adv_txt pro_adv_txt5">
                        <!--<b>安全</b>
                        <p>一手资源，正规106通道或线路资源</p>-->
                        <span>APP应用手机验证</span>
                    </div>
                    <div class="pro_adv_txt pro_adv_txt6">
                        <!--<b>便捷</b>
                        <p>一个API接入，方便、简单、灵活</p>-->
                        <span>网站订单通知</span>
                    </div>
                </div>
    		</div>
    	</div>

    	<div class="item_box box7 box72">
    		<div class="item_box_wp">
    			<ul>
                    <li class="li3">
                        <dl>
                            <dt>&nbsp;</dt>
                            <dd class="tit">智能验证平台</dd>
                            <dd>基于云端的用户身份验证，根据云端数据，融合短信/语音验证的一种验证方式</dd>
                            <dd><a href="<%=path%>/doc/codeDesc" class="show">查看演示</a></dd>
                            <dd><a href="http://docs.flypaas.com/doku.php?id=智能验证接口_android">Android文档</a> &nbsp; &nbsp;| &nbsp; &nbsp; <a href="http://docs.flypaas.com/doku.php?id=智能验证接口_ios">iOS文档</a></dd>
                        </dl>
                    </li>
                    <li class="li1">
                        <dl>
                            <dt>&nbsp;</dt>
                            <dd class="tit">短信验证码</dd>
                            <dd>通过运营商短信网关向受众手机发送文字验证码的一种验证码传送方式</dd>
                            <dd><a href="<%=path%>/doc/smsCode" class="show">查看演示</a></dd>
                            <dd><a href="http://docs.flypaas.com/doku.php?id=短信验证码_模板短信">文档>></a></dd>
                        </dl>
                    </li>
                    <li class="li2">
                        <dl>
                            <dt>&nbsp;</dt>
                            <dd class="tit">语音验证码</dd>
                            <dd>通过运营商电话网关向受众手机播放语音验证码的一种验证码传送方式</dd>
                            <dd><a href="<%=path%>/doc/smsCode" class="show">查看演示</a></dd>
                            <dd><a href="http://docs.flypaas.com/doku.php?id=语音验证码">文档>></a></dd>
                        </dl>
                    </li>
                </ul>
    		</div>
    	</div>

        <div class="item_box box5">
            <div class="item_box_wp">
                <p>想了解更多？请致电 400-097-0020</p>
            </div>
        </div>
	</div>
	<!--主体部分 ft_content eof-->

	 

<!--公共版权copyright bof-->
<%@include file="/front/foot_sk.jsp"%><%@include file="/front/foot.jsp" %>
<!--公共版权copyright eof-->
<script type="text/javascript">
$(function(){
    $("#h_menu_2").css("color","#05c040");
})
</script>
</body>
</html>