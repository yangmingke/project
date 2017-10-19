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
<meta name="description" content="快传融合通讯网络电话，提供多种网络通话能力，支持语音会议，视频会议，VoIP网络电话等，资费最低，即插即用，是国内最好用的免费网络电话" />
<title>网络电话_语音会议_视频通话_VoIP网络电话_快传融合通讯开放平台</title>
<%@include file="/front/resource.jsp" %>
</head>

<body>
<!--公共头部header bof-->
<%@include file="/front/head.jsp" %>

<!--公共头部header eof--> 

<!--中间部分middle bof-->
<div class="middle mid_box">
  <div class="banner voice_banner">&nbsp;</div>
  <div class="display_box">
  	<div class="display_tit">
  		<h1><span>语音voice</span></h1>
  	</div>
  	<div class="display_ctn">
  		<dl>
  			<dt><img src="<%=path%>/front/images/voice.png" /></dt>
  			<dd><p>提供融合电信网络和IP网络的多种通话能力，实现高质量语音对讲、互联网语音、P2P语音等语音应用。<br />1.智能选择通信电路，保证通话质量；<br />2.自主研发软交换系统，保证性能与语音通话质量；<br />3.接入三大运营商落地网关，实现互联网IP呼叫与传统电信网络的呼叫交换。</p><p><a href="http://docs.flypaas.com/doku.php?id=语音通话接口_android">查看文档</a></p></dd>
  		</dl>
  	</div>
  </div>
  <div class="clear"></div>
  <div class="display_box voice_advantage">
  	<div class="display_tit">
  		<h1><span class="advantage">优势能力</span></h1>
  	</div>
  	<div class="display_ctn">
  		<dl>
  			<dt><img src="<%=path%>/front/images/voice_adv1.png" /></dt>
  			<dd><b>省钱省流量</b></dd>
  			<dd>支持互联网高效压缩技术，编解码类型包括silk,amr等，极大节省用户流量。</dd>
  		</dl>
  		<dl>
  			<dt><img src="<%=path%>/front/images/voice_adv2.png" /></dt>
  			<dd><b>大容量并发</b></dd>
  			<dd>支持大容量、多并发、呼入呼出满足大型呼叫业务需求，保障性能需求及通话质量。</dd>
  		</dl>
  		<dl>
  			<dt><img src="<%=path%>/front/images/voice_adv3.png" /></dt>
  			<dd><b>计费灵活，自主控制</b></dd>
  			<dd>灵活的计费策略支持由paas平台计费，也可以开发者自行计费，更加灵活。</dd>
  		</dl>
  		<dl>
  			<dt><img src="<%=path%>/front/images/voice_adv4.png" /></dt>
  			<dd><b>自适应转换通话模式</b></dd>
  			<dd>支持防火墙穿越功能，自适应选择媒体中转模式和P2P模式，保证通话效果。</dd>
  		</dl>
  		<dl>
  			<dt style="padding-left:15px; width:85px;"><img src="<%=path%>/front/images/voice_adv5.png" /></dt>
  			<dd><b>智能路由</b></dd>
  			<dd>支持呼叫智能路由，用户呼叫的持续时间更快，更好地享受应用带来的通话快感。</dd>
  		</dl>
      <dl>
        <dt><img src="<%=path%>/front/images/voice_adv6.png" /></dt>
        <dd><b>智能选线，质量高</b></dd>
        <dd>支持电信线路智能选线，用户的接通率更高，语音质量更好。</dd>
      </dl>
  	</div>
  </div>  
  <div class="intro_box green voice_unit">
    <div class="intro_wrapper">
    <div class="mid_tit">
    <h1><span>功能组件</span></h1>
  </div>
      <!--<div class="intro_img"> <img src="<%=path%>/front/images/banner_img7.png" /> </div>
      <div class="intro_desc">
        <h2>多媒体信息</h2>
        <p>（1）互联网语音：基于互联网、移动互联网提供点对点、语音实时和语音通话等功能，支持多终端跨平台语音；<br />
（2）落地电话：基于互联网接入普通电话网络实现双方实时语音通话，<br />
（3）双向回拨：由语音系统同时呼叫主叫方与被叫方两个号码，变拨打电话为接听电话。</p>
        <p><a href="#" class="view">&nbsp;</a></p>
      </div>-->
      <dl class="blue">
        <dt><img src="<%=path%>/front/images/unit1.png" alt="互联网语音" /></dt>
       <dd><b>互联网语音</b></dd>
        <dd>基于互联网、移动互联网提供点对点语音实时语音通话支持多终端跨平台语音</dd>
        <dd><a href="<%=path%>/freetrial/VoIP" class="view">&nbsp;</a></dd>
      </dl>
      <dl class="red">
        <dt><img src="<%=path%>/front/images/unit2.png" alt="落地电话"/></dt>
         <dd><b>落地电话</b></dd>
        <dd>基于互联网接入普通电话网络实现双方实时语音通话</dd>
        <dd><a href="<%=path%>/freetrial/toll" class="view">&nbsp;</a></dd>
      </dl>
      <dl class="green">
        <dt><img src="<%=path%>/front/images/unit3.png" alt="双向回拨"/></dt>
        <dd><b>双向回拨</b></dd>
        <dd>由语音系统同时呼叫主叫方与被叫方两个号码，变拨打电话为接听电话</dd>
        <dd><a href="<%=path%>/freetrial/callinout" class="view">&nbsp;</a></dd>
      </dl>
    </div>
  </div>
  <div class="clear"></div>
  <div class="mid_tit">
    <span class="span_in">轻而易举&nbsp;&nbsp;接入应用</span>
    <h1><span class="in">接入流程</span></h1>
  </div>
  <div class="in_box">
  	<p><img src="<%=path%>/front/images/in_img.png" alt="应用接入流程" /></p>
  </div>
</div>
<!--中间部分middle eof--> 

 

<!--公共版权copyright bof-->
<%@include file="/front/foot_sk.jsp"%><%@include file="/front/foot.jsp" %>
<!--公共版权copyright eof-->
<script type="text/javascript">
$(function(){
	$("#h_menu_2").css("color","#05c040");

  //语音通话功能组件鼠标放上去图标高亮变化
    $(".voice_unit dl").hover(function(){
      var img_src = $(this).find("img").attr("src");
      var img_name = img_src.substring(img_src.lastIndexOf("/")+1).replace(".png","");
      $(this).find("img").attr("src","../front/images/"+img_name+"_hover.png");
    },function(){
      var dl_index = $(this).index();
      $(this).find("img").attr("src","../front/images/unit"+dl_index+".png");
    })
    
})
</script>
</body>
</html>
