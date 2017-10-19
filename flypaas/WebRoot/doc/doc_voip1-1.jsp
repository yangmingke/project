<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ taglib prefix="s"  uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>VoIP开发指南_快传融合通讯开放平台</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/doc/css/doc.css" /><%@include file="/front/resource.jsp"%><script type="text/javascript" src="<%=path%>/doc/js/doc.js"></script>
</head>

<body id="5">
<!--公共头部header bof-->
<%@include file="/front/head.jsp" %>

<!--公共头部header eof--> 

<!--中间部分middle bof-->
<div class="middle mid_box doc_box">
  <div class="display_wrapper">
    <div class="doc_menu">
      <div class="menu_tit">
        <h1><span class="home"><a href="<%=path%>/doc/doc.jsp">文档首页</a></span></h1>
      </div>
       <ul>
        <li>
        	<span>新手指引<i class="parent">&nbsp;</i></span>
        	<dl style="display:none;">        		
                <dd><a href="<%=path%>/doc/doc_guide4-1.jsp">注册成为开发者</a></dd>
                <dd><a href="<%=path%>/doc/doc_guide5-1.jsp">管理中心使用指南</a></dd>   
				<dd><a href="<%=path%>/doc/doc_guide6-1.jsp">应用创建流程</a></dd>
                <dd><a href="<%=path%>/doc/doc_guide7-1.jsp">充值流程</a></dd>  
                <dd><a href="<%=path%>/doc/doc_guide3-1.jsp">应用审核规范</a></dd>				
                <dd><a href="<%=path%>/doc/doc_guide8-1.jsp">开发者资质审核规范</a></dd>                   
                <dd><a href="<%=path%>/doc/doc_guide2-1.jsp">开发者协议</a></dd>                
        	</dl>
        </li>
        <li>
        	<span>REST API<i class="parent">&nbsp;</i></span>
        	<dl style="display:none;">
        		<dd><a href="<%=path%>/doc/doc_rest1-1.jsp">REST介绍</a></dd>
        		<dd class="parent">
        			<a href="javascript:void(0)"><em>账户管理</em></a>
        			<dl class="child">
        				<dd><a href="<%=path%>/doc/doc_rest2-1.jsp">开发者账号信息查询</a></dd>
        				<dd><a href="<%=path%>/doc/doc_rest2-2.jsp">申请Client账号</a></dd>
        				<dd><a href="<%=path%>/doc/doc_rest2-3.jsp">释放Client账号</a></dd>
        				<dd><a href="<%=path%>/doc/doc_rest2-4.jsp">获取Client账号</a></dd>
        				<dd><a href="<%=path%>/doc/doc_rest2-5.jsp">Client信息查询(Client账号)</a></dd>
                        <dd><a href="<%=path%>/doc/doc_rest2-9.jsp">Client信息查询(手机号码)</a></dd>
        				<dd><a href="<%=path%>/doc/doc_rest2-6.jsp">应用话单下载</a></dd>
        				<!--<dd><a href="<%=path%>/doc/doc_rest2-7.jsp">Client话单下载</a></dd>-->
        				<dd class="last"><a href="<%=path%>/doc/doc_rest2-8.jsp">Client账号充值</a></dd>
        			</dl>
        		</dd>
        		<dd class="parent">
        			<a href="javascript:void(0)"><em>呼叫</em></a>
        			<dl class="child">
        				<dd><a href="<%=path%>/doc/doc_rest3-1.jsp">双向回拨</a></dd>
        				<dd><a href="<%=path%>/doc/doc_rest3-2.jsp">语音验证码</a></dd>
						<dd class="last"><a href="<%=path%>/doc/doc_rest3-3.jsp">语音通知</a></dd>
        			</dl>
        		</dd>
        		<dd class="parent">
        			<a href="javascript:void(0)"><em>SMS</em></a>
        			<dl class="child">
        				<dd class="last"><a href="<%=path%>/doc/doc_rest4-1.jsp">短信验证码（模板短信）</a></dd>
        			</dl>
        		</dd>
        	</dl>
        </li>
        <li>
        	<span>Android SDK<i class="parent">&nbsp;</i></span>
        	<dl style="display:none;">
        	    <dd><a href="<%=path%>/doc/doc_android6-1.jsp">Android开发指南</a></dd>
        		<dd><a href="<%=path%>/doc/doc_android1-1.jsp">Android SDK介绍</a></dd>
        		<dd><a href="<%=path%>/doc/doc_android2-1.jsp">初始化及配置接口</a></dd>
        		<dd class="parent">
        			<a href="#"><em>VoIP接口</em></a>
        			<dl class="child">
        				<dd><a href="<%=path%>/doc/doc_android3-1.jsp">VoIP通话控制接口</a></dd>
        				<dd class="last"><a href="<%=path%>/doc/doc_android3-2.jsp">VoIP状态回调接口</a></dd>
        			</dl>
        		</dd>
        		<dd class="parent">
        			<a href="#"><em>IM接口</em></a>
        			<dl class="child">
        				<dd><a href="<%=path%>/doc/doc_android4-1.jsp">IM消息接口</a></dd>
        				<dd class="last"><a href="<%=path%>/doc/doc_android4-1.jsp">IM消息回调接口</a></dd>
        			</dl>
        		</dd>
				<dd><a href="<%=path%>/doc/doc_android7-1.jsp">视频通话控制接口</a></dd>
				<dd><a href="<%=path%>/doc/doc_android8-1.jsp">用户在线查询能力接口</a></dd>
                <dd><a href="<%=path%>/doc/doc_android9-1.jsp">智能验证接口</a></dd>
                <dd><a href="<%=path%>/doc/doc_android5-1.jsp">注意事项</a></dd>
        	</dl>
        </li>
        <li>
        	<span>iOS SDK<i class="parent">&nbsp;</i></span>
        	<dl style="display:none;">
        	    <dd><a href="<%=path%>/doc/doc_ios5-1.jsp">iOS开发指南</a></dd>
        		<dd><a href="<%=path%>/doc/doc_ios1-1.jsp">iOS SDK介绍</a></dd>
        		<dd class="parent">
        			<a href="#"><em>初始化及配置接口</em></a>
        			<dl class="child">
        				<dd><a href="<%=path%>/doc/doc_ios2-1.jsp">初始化及配置</a></dd>
        				<dd class="last"><a href="<%=path%>/doc/doc_ios2-1.jsp">初始化及配置代理接口</a></dd>
        			</dl>
        		</dd>
        		<dd class="parent">
        			<a href="#"><em>VoIP接口</em></a>
        			<dl class="child">
        				<dd><a href="<%=path%>/doc/doc_ios3-1.jsp">VoIP能力接口</a></dd>
        				<dd class="last"><a href="<%=path%>/doc/doc_ios3-1.jsp">VoIP能力代理接口</a></dd>
        			</dl>
        		</dd>
        		<dd class="parent">
        			<a href="#"><em>IM接口</em></a>
        			<dl class="child">
        				<dd><a href="<%=path%>/doc/doc_ios4-1.jsp">IM能力接口</a></dd>
        				<dd class="last"><a href="<%=path%>/doc/doc_ios4-1.jsp">IM能力代理接口</a></dd>
        			</dl>
        		</dd>
				<dd><a href="<%=path%>/doc/doc_ios6-1.jsp">视频通话能力接口</a></dd>
				<dd><a href="<%=path%>/doc/doc_ios7-1.jsp">用户在线状态能力接口</a></dd>
				<dd><a href="<%=path%>/doc/doc_ios8-1.jsp">用户在线状态能力代理接口</a></dd>
                <dd><a href="<%=path%>/doc/doc_ios9-1.jsp">智能验证接口</a></dd>
        	</dl>
        </li>
        <li>
        	<span>Windows SDK<i class="parent">&nbsp;</i></span>
        	<dl style="display:none;">
                <dd><a href="<%=path%>/doc/doc_windows5-1.jsp">Windows开发指南</a></dd>
        		<dd><a href="<%=path%>/doc/doc_windows1-1.jsp">Windows SDK介绍</a></dd>
        		<dd class="parent">
        			<a href="#"><em>初始化及配置接口</em></a>
        			<dl class="child">
        				<dd><a href="<%=path%>/doc/doc_windows2-1.jsp">初始化及配置</a></dd>
        				<dd class="last"><a href="<%=path%>/doc/doc_windows2-2.jsp">初始化及配置回调函数</a></dd>
        			</dl>
        		</dd>
        		<dd class="parent">
        			<a href="#"><em>VoIP接口</em></a>
        			<dl class="child">
        				<dd><a href="<%=path%>/doc/doc_windows3-1.jsp">VoIP能力接口</a></dd>
        				<dd class="last"><a href="<%=path%>/doc/doc_windows3-2.jsp">VoIP能力回调函数</a></dd>
        			</dl>
        		</dd>
        		<dd class="parent">
        			<a href="#"><em>IM接口</em></a>
        			<dl class="child">
        				<dd><a href="<%=path%>/doc/doc_windows4-1.jsp">IM能力接口</a></dd>
        				<dd class="last"><a href="<%=path%>/doc/doc_windows4-2.jsp">IM能力回调函数</a></dd>
        			</dl>
        		</dd>
				<dd><a href="<%=path%>/doc/doc_windows6-1.jsp">视频能力接口</a></dd>
        	</dl>
        </li>
        <li>
        	<span>应用服务器接口<i class="parent">&nbsp;</i></span>
        	<dl style="display:none;">
        		<!--<dd><a href="#">接收短信</a></dd>
        		<dd><a href="#">语音验证码状态通知</a></dd>-->
        		<dd><a href="<%=path%>/doc/doc_server6-1.jsp">呼叫鉴权流程</a></dd>
        		<dd><a href="<%=path%>/doc/doc_server3-1.jsp">呼叫鉴权请求接口</a></dd>
        		<dd><a href="<%=path%>/doc/doc_server4-1.jsp">呼叫建立通知接口</a></dd>
        		<dd><a href="<%=path%>/doc/doc_server5-1.jsp">呼叫挂机计费接口</a></dd>
                <dd><a href="<%=path%>/doc/doc_server7-1.jsp">语音外呼状态通知接口</a></dd>>
        	</dl>
        </li>
         <!--<li><span onclick="location.href='<%=path%>/doc/doc_demo1-1.jsp'">DEMO<i class="parent">&nbsp;</i></span></li>-->
        <li><!--<span onclick="location.href='doc_errorcode1-1.html'" style="background:#fff; width:188px;">错误代码<i class="parent">&nbsp;</i></span>-->
		    <span>错误代码<i class="parent">&nbsp;</i></span>
        	<dl style="display:none;">
        		<dd><a href="<%=path%>/doc/doc_errorcode1-1.jsp">Android错误码</a></dd>
        		<dd><a href="<%=path%>/doc/doc_errorcode2-1.jsp">iOS错误码</a></dd>
        		<dd><a href="<%=path%>/doc/doc_errorcode3-1.jsp">Windows错误码</a></dd>
                <dd class="last"><a href="<%=path%>/doc/doc_errorcode4-1.jsp">REST错误码</a></dd>
        	</dl>
		</li>         
        <li><span onclick="location.href='<%=path%>/doc/doc_faq1-1.jsp'">常见问题<i class="parent">&nbsp;</i></span></li>
		<li><span onclick="location.href='<%=path%>/doc/doc_dguide1-1.jsp'">开发者引导<i class="parent">&nbsp;</i></span></li>
      </ul>
    </div>
    <div class="doc_txt doc_home">
      <!--<div class="pathbox"><p><a href="<%=path%>/document">帮助文档首页</a> > <span>文档首页</span></p></div>-->
      <div class="display_tit">
        <h1><span class="intro">VoIP开发指南</span></h1>
      </div>
      <div class="display_ctn">
        <p>VoIP落地通话API基于VoIP技术，采用优秀的语音压缩算法对语音数据编码进行压缩处理，经过IP网络实现数据包的稳定传送，然后通过电信网与手机、固话用户建立通话，为开发者实现基于IP网络的PC客户端、移动App、或更多智能终端设备与手机，固话之间的通话功能。 能够让您的软件或APP实现通过网络拨打手机、固话的功能，如CRM/ERP类企业管理软件集成该接口，就能让CRM实现点击呼叫客户；社交类App嵌入该接口，即可实现skype类似的网络电话功能。</p>
        <h3 id="tgv1.1.1">支持平台</h3>
		<p>Android、IOS、Windows等平台</p>
		<h3 id="tgv1.1.2">开发流程介绍</h3>
		<b id="tgv1.1.2.1">Android</b>
		<ol>
		<li>1. 在快传融合通讯开放平台注册开发者帐号，<a href="<%=path%>/doc/doc_guide6-1.jsp#tg6.1.2">创建应用</a>并且申请上线。</li>
		<li>2. 快传融合通讯开放平台为开发者提供了<a href="<%=path%>/doc/doc_android1-1.jsp">《Android SDK》</a>，在android平台需要填入帐号信息后调用<a href="<%=path%>/doc/doc_android2-1.jsp">初始化接口</a>对SDK进行初始化。</li>
		<li>3. 初始化完成后调用<a href="<%=path%>/doc/doc_android3-1.jsp">VoIP能力接口</a>即可实现VoIP直拨电话。</li>
		<li>4. 如需SDK的接口调用示例，可以下载快传融合通讯开放平台提供的<a href="<%=path%>/product_service/download">Android Demo</a>。</li>		
		</ol>
		<b id="tgv1.1.2.2">iOS</b>
		<ol>
		<li>1. 在快传融合通讯开放平台注册开发者帐号，<a href="<%=path%>/doc/doc_guide6-1.jsp#tg6.1.2">创建应用</a>并且申请上线。</li>
		<li>2. 快传融合通讯开放平台为开发者提供了<a href="<%=path%>/doc/doc_ios1-1.jsp">《iOS SDK》</a>，在iOS平台需要填入帐号信息后调用<a href="<%=path%>/doc/doc_ios2-1.jsp">初始化接口</a>对SDK进行初始化。</li>
		<li>3. 初始化完成后调用<a href="<%=path%>/doc/doc_ios3-1.jsp">VoIP能力接口</a>即可实现VoIP直拨电话。</li>
		<li>4. 如需SDK的接口调用示例，可以下载快传融合通讯开放平台提供的<a href="<%=path%>/product_service/download">iOS Demo</a>。</li>		
		</ol>
		<b id="tgv1.1.2.3">Windows</b>
		<ol>
		<li>1. 在快传融合通讯开放平台注册开发者帐号，<a href="<%=path%>/doc/doc_guide6-1.jsp#tg6.1.2">创建应用</a>并且申请上线。</li>
		<li>2. 快传融合通讯开放平台为开发者提供了<a href="<%=path%>/doc/doc_windows1-1.jsp">《Windows SDK》</a>，在Windows平台需要填入帐号信息后调用<a href="<%=path%>/doc/doc_windows2-1.jsp">初始化接口</a>对SDK进行初始化。</li>
		<li>3. 初始化完成后调用<a href="<%=path%>/doc/doc_windows3-1.jsp">VoIP能力接口</a>即可实现VoIP直拨电话。</li>
		<li>4. 如需SDK的接口调用示例，可以下载快传融合通讯开放平台提供的<a href="<%=path%>/product_service/download">Windows Demo</a>。</li>		
		</ol>
	    <b id="tgv1.1.2.4">呼叫鉴权</b>
		<ol>
		<li>1. 快传融合通讯开放平台为开发者提供了较为方便的鉴权通知，用于开发者对其客户进行呼叫控制和管理计费。接口请求与相应的参数请参考<a href="<%=path%>/doc/doc_server3-1.jsp">《鉴权接口》</a>。</li>
		<li>2. 为了便于开发者在其服务器部署鉴权模块，快传融合通讯开放平台提供了更为方便的操作方式，详细的鉴权流程请参考<a href="<%=path%>/doc/doc_server6-1.jsp">《鉴权流程》</a>。</li>		
		</ol>
		<h3 id="tgv1.1.3">DEMO演示</h3>
		<p>Android/IOS/windows：<a href="<%=path%>/product_service/download">Demo下载</a></p>
		<h3 id="tgv1.1.4">收费方式</h3>
		<p>
		计费单位：按分钟计费<br />
		对应价格："(国内)(运营商)单向外呼"，报价请进入<a href="<%=path%>/doc/price.jsp">价格</a>查看</p>
	  </div>
    </div>
  </div>
  <div class="clear"></div>
</div>
<div class="doc_aside" id="doc_aside">
    <h4>目录 [<a href="javascript:void(0);" id="aside_fold_link">隐藏</a>] </h4>
  <div class="aside_ctn">
    <ul>
      <li><a href="#tgv1.1.1">1. 支持平台</a></li>
      <li><a href="#tgv1.1.2">2. 开发流程介绍</a>
      <ul>
        <li><a href="#tgv1.1.2.1">2.1 Android</a></li>
        <li><a href="#tgv1.1.2.2">2.2 iOS</a></li>
		<li><a href="#tgv1.1.2.3">2.3 Windows</a></li>
		<li><a href="#tgv1.1.2.4">2.4 呼叫鉴权</a></li>
      </ul>
      </li>
	  <li><a href="#tgv1.1.3">3. DEMO演示</a></li>
	  <li><a href="#tgv1.1.4">4. 收费方式</a></li>
    </ul>
  </div>
</div>
<div class="aside_fold" id="aside_fold" style="display:none;"><img src="<%=path%>/doc/images/aside_fold.png" />目录导航</div>
<a href="javascript:void(0);" id="go_top" style="display:none;">&nbsp;</a>
<!--中间部分middle eof--> 

<!--公共底部bottom bof-->

<!--公共底部bottom eof--> 

<!--公共版权copyright bof-->
<%@include file="/front/foot.jsp" %>
<!--公共版权copyright eof-->
<script type="text/javascript">
$(function(){
	$("#h_menu_5").css("color","#05c040");
})
</script>
</body>
</html>
