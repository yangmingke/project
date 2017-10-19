<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="description" content="快传融合通讯即时通讯提供网络即时传送文字，多媒体，语音等在线实时交流能力，接入简单" />
<title>IM_即时消息_短信平台_企业即时通讯_快传融合通讯开放平台</title>
<%@include file="/front/resource.jsp" %>
</head>

<body>
<!--公共头部header bof-->
<%@include file="/front/head.jsp" %>
<!--公共头部header eof--> 

<!--中间部分middle bof-->
<div class="middle mid_box">
  <div class="banner im_banner">&nbsp;</div>
  <div class="display_box">
  	<div class="display_tit">
  		<h1><span>即时消息IM</span></h1>
  	</div>
  	<div class="display_ctn">
  		<dl>
  			<dt><img src="<%=path%>/front/images/message.png" /></dt>
  			<dd><p>为个人或企业开发者提供基于网络传送文字、多媒体、语音等在线实时交流能力。<br />我们为个人和企业开发者打造一个基于云端通讯基础自由可扩展的IP在线即时通讯服务平台，提供基于Native 源生ios、Android、PC SDK开发包，让您轻松无缝接入。</p><p><a href="http://docs.flypaas.com/doku.php?id=即时消息接口_android">查看文档</a></p></dd>
  		</dl>
  	</div>
  </div>
  <div class="clear"></div>
  <div class="display_box im_advantage">
  	<div class="display_tit">
  		<h1><span class="advantage">优势能力</span></h1>
  	</div>
  	<div class="display_ctn">
  		<dl>
  			<dt><img src="<%=path%>/front/images/graffiti_1.png" /></dt>
  			<dd><b>丰富的消息类型</b></dd>
  			<dd>支持各种类型的即时消息通信，包括文本、语音、视频附件、名片、表情、位置信息等。</dd>
  		</dl>
  		<dl>
  			<dt><img src="<%=path%>/front/images/graffiti_2.png" /></dt>
  			<dd><b>安全可靠</b></dd>
  			<dd>基于分布式的云端网络传输存储消息，更安全、快速。</dd>
  		</dl>
  		<dl>
  			<dt><img src="<%=path%>/front/images/graffiti_3.png" /></dt>
  			<dd><b>用户自定义消息</b></dd>
  			<dd>支持用户自定义类型的即时消息通信，用户可以自定义传输类型，灵活性更强。</dd>
  		</dl>
  		<dl>
  			<dt><img src="<%=path%>/front/images/graffiti_4.png" /></dt>
  			<dd><b>接入方便快捷</b></dd>
  			<dd>提供基于源生平台各种类型SDK开发包（windows,ios,android等），便于快速根据开发将服务集成在自有应用内。</dd>
  		</dl>
  		<dl class="last">
  			<dt><img src="<%=path%>/front/images/graffiti_5.png" /></dt>
  			<dd><b>容量无限的扩展</b></dd>
  			<dd>基于云集群部署，更容易容量扩展。</dd>
  		</dl>
  	</div>
  </div>  
  <div class="intro_box green">
    <div class="intro_wrapper">
    <div class="mid_tit">
    <h1><span>功能组件</span></h1>
  </div>
      <div class="intro_img"> <img src="<%=path%>/front/images/banner_img7.png" alt="多媒体信息"/> </div>
      <div class="intro_desc">
        <h2>多媒体信息</h2>
        <p>多媒体消息是基于IP网络的通讯方式，支持两人或多人之间的文字、图片、语音、视频等形式的消息传递，比传统短信沟通更灵活方便、及时、资费低，支持离线消息管理、群组管理和群组消息广播等。合作伙伴可以快速实现微信类的即时通讯功能。</p>
        <p><a href="<%=path%>/freetrial/multmediasms" class="view">&nbsp;</a></p>
      </div>
    </div>
  </div>
  <div class="clear"></div>
  <div class="mid_tit">
    <span class="span_in">轻而易举&nbsp;&nbsp;接入应用</span>
    <h1><span class="in">接入流程</span></h1>
  </div>
  <div class="in_box">
  	<p><img src="<%=path%>/front/images/in_img.png" alt="应用接入流程"/></p>
  </div>
</div>
<!--中间部分middle eof--> 

 

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
