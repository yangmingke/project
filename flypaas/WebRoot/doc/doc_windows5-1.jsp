<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ taglib prefix="s"  uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Windows开发指南</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/doc/css/doc.css" /><%@include file="/front/resource.jsp"%><script type="text/javascript" src="<%=path%>/doc/js/doc.js"></script>
</head>

<body id="5">
<!--公共头部header bof-->
<%@include file="/front/head.jsp" %>
<!--公共头部header eof--> 

<!--中间部分middle bof-->
<div class="middle mid_box doc_box">
  <div class="doc_search">
      <!--<div class="doc_search">
    <div class="serach_wrapper">
      <form method="post">
        <input type="text" value="" placeholder="请简单输入您的关键词，如'快传'" />
        <input type="submit" value="" />
      </form>
    </div>
  </div>-->
  </div>
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
        			<a href="#"><em>账户管理</em></a>
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
        			<a href="#"><em>呼叫</em></a>
        			<dl class="child">
        				<dd><a href="<%=path%>/doc/doc_rest3-1.jsp">双向回拨</a></dd>
        				<dd><a href="<%=path%>/doc/doc_rest3-2.jsp">语音验证码</a></dd>
						<dd class="last"><a href="<%=path%>/doc/doc_rest3-3.jsp">语音通知</a></dd>
        			</dl>
        		</dd>
        		<dd class="parent">
        			<a href="#"><em>SMS</em></a>
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
        				<dd class="last"><a href="<%=path%>/doc/doc_android4-2.jsp">IM消息回调接口</a></dd>
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
        				<dd class="last"><a href="<%=path%>/doc/doc_ios2-2.jsp">初始化及配置代理接口</a></dd>
        			</dl>
        		</dd>
        		<dd class="parent">
        			<a href="#"><em>VoIP接口</em></a>
        			<dl class="child">
        				<dd><a href="<%=path%>/doc/doc_ios3-1.jsp">VoIP能力接口</a></dd>
        				<dd class="last"><a href="<%=path%>/doc/doc_ios3-2.jsp">VoIP能力代理接口</a></dd>
        			</dl>
        		</dd>
        		<dd class="parent">
        			<a href="#"><em>IM接口</em></a>
        			<dl class="child">
        				<dd><a href="<%=path%>/doc/doc_ios4-1.jsp">IM能力接口</a></dd>
        				<dd class="last"><a href="<%=path%>/doc/doc_ios4-2.jsp">IM能力代理接口</a></dd>
        			</dl>
        		</dd>
				<dd><a href="<%=path%>/doc/doc_ios6-1.jsp">视频通话能力接口</a></dd>
				<dd><a href="<%=path%>/doc/doc_ios7-1.jsp">用户在线状态能力接口</a></dd>
				<dd><a href="<%=path%>/doc/doc_ios8-1.jsp">用户在线状态能力代理接口</a></dd>
                <dd><a href="<%=path%>/doc/doc_ios9-1.jsp">智能验证接口</a></dd>
        	</dl>
        </li>
        <li class="active">
        	<span>Windows SDK<i class="parent">&nbsp;</i></span>
        	<dl>
                <dd><a href="<%=path%>/doc/doc_windows5-1.jsp" class="active">Windows开发指南</a></dd>
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
        		<!--<dd><a href="<%=path%>/doc/doc_server1-1.jsp">接收短信</a></dd>
        		<dd><a href="<%=path%>/doc/doc_server2-1.jsp">语音验证码状态通知</a></dd>-->
        		<dd><a href="<%=path%>/doc/doc_server3-1.jsp">呼叫请求</a></dd>
        		<dd><a href="<%=path%>/doc/doc_server4-1.jsp">呼叫建立通知接口</a></dd>
        		<dd class="last"><a href="<%=path%>/doc/doc_server5-1.jsp">呼叫挂机计费接口</a></dd>
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
    <div class="doc_txt">
      <div class="pathbox"><p><a href="<%=path%>/document">帮助文档首页</a> > <a href="<%=path%>/doc/doc_windows5-1.jsp">Windows SDK</a> > <span>Windows开发指南</span></p></div>
      <div class="display_tit">
        <h1><span class="intro">Windows开发指南</span></h1>
      </div>
      <div class="display_ctn">
        <h2 id="tgw5.1.1">1. 概述</h2>
        <p>快传融合通讯开放平台旨在为第三方应用开发者提供丰富完善的注册流程、接入机制、安全策略、管理后台以及不同语言的SDK开发包，为开发者在应用内快速、高效、低成本集成语音业务提供了一站式的服务。本文档旨在为第三方应用开发者在Windows平台下集成flypaas SDK来打造语音业务提供参考，文档预期的读者为第三方应用开发人员、平台开发人员、相关技术人员等。</p>
        <h3 id="tgw5.1.1.1">1.1 介绍</h3>
        <p>快传融合通讯开放平台SDK提供了VoIP通话、落地电话、即时消息等基础能力，REST API除了提供上述功能外，还提供注册账号、 创建Client、营销外呼、语音验证码、各类查询等等。快传融合通讯开放平台Windows SDK 以C++动态库的方式提供给Windows平台开发人员。REST API 可通过HTTPS GET、POST方式访问。</p>
        <h3 id="tgw5.1.1.2">1.2 开发流程</h3>
        <p>快传融合通讯开放平台作为通讯能力的云计算PAAS平台，将传统电信网络的通讯能力、基于IP的通讯能力，通过开放API以及SDK的方式提供给开发者和商家，协助开发者快速、高效、低成本打造融合通讯能力的产品。</p>
        <p>
        1). 您的客户端应用集成快传融合通讯开放平台提供的SDK，同时客户端向您的应用服务器请求分配Client账号信息；<br />
        2). 您的应用服务器通过调用云通讯平台REST API 得到用户账号并返回给您的客户端应用；<br />
        3). 客户端应用通过调用SDK API发起呼叫请求或者监听呼入。
        </p>
        <h2 id="tgw5.1.2">2. VoIP快速体验</h2>
        <p>在快传融合通讯开放平台注册账号，创建Demo账号，并下载获取UcsDemo程序（具体过程请参考以下内容）。在Demo程序中，演示了云通讯平台提供的基础VoIP通话功能。</p>
        <h3 id="tgw5.1.2.1">2.1 申请测试账号</h3>
        <p>在快传融合通讯开放平台上获取Demo账号信息，须注册后创建Demo，即可获得开发VoIP所需的测试账号信息。测试账号信息内容有：开发者账号、开发者账号密码、Client账号、Client账号密码，应用ID。</p>
        <h3 id="tgw5.1.2.2">2.2 环境搭建</h3>
        <p>
        推荐Windows7旗舰版 32位。<br />
        声卡正常的PC机。<br />
        有耳麦的耳机。
        </p>
        <h3 id="tgw5.1.2.3">2.3 Demo</h3>
        <p>下载：在Demo账号信息页面，提供了不同平台下的Demo下载，请选择Windows版下载Ucs Demo功能介绍，Demo演示了flypaas SDK的API接口调用，主要实现的功能：<br />
        免费电话：需要对方的Client账号，双方进行的网络P2P通话，免费通话。<br />
        电话直拨：需要对方的手机号，主叫接入网络电话，被叫接入普通电话。
        </p>
        <h3 id="tgw5.1.2.4">2.4 解压Demo压缩文件</h3>
        <p>解压下载的UCS_DEMO_WINDOWS.zip文件到任意目录。</p>
        <h3 id="tgw5.1.2.5">2.5 运行体验</h3>
        <p>配置账号信息完成，即可运行Demo程序。</p>
        <h2 id="tgw5.1.3">3. 创建自己的应用</h2>
        <p>这一节是为了让开发者能够用最少的代码量和时间，来实现基本的VoIP通话功能。</p>
        <h3 id="tgw5.1.3.1">3.1 SDK介绍</h3>
        <p>SDK下载：快传融合通讯开放平台下载VoIP的Windows SDK。</p>
        <p>SDK文件说明：SDK文件放在文件夹SDK中，其中包含四个文件：<br />
        UCSClient.h为SDK函数头文件。<br />
        UCSNetService.lib为SDK静态库文件。<br />
        UCSNetService.dll为SDK动态库文件。<br />
        NetService.dll为SDK动态库文件。
        </p>
        <h3 id="tgw5.1.3.2">3.2 环境搭建与创建工程</h3>
        <p>推荐Microsoft Visual Studio 2010作为开发环境,开发者新建项目时选择需要的类型模板。</p>
        <h3 id="tgw5.1.3.3">3.3 编写代码</h3>
        <p>介绍代码的实现过程，也可参考Demo的代码实现，加深理解UCSClient.h内SDK函数调用</p>
        <b id="tgw5.1.3.3.1">3.3.1 flypaas SDK初始化</b>
        <pre>
            <code class="code_fn">
{
    <span class="fn_cmt">// 初始化SDK，并传入回调函数</span>
    Ucs_init( UCS_CALLBACKINTERFACE *CallbackInterface );
}
            </code>
        </pre>
        <b id="tgw5.1.3.3.2">3.3.2 登录Client账号</b>
        <pre>
            <code class="code_fn">
登录平台
{
    <span class="fn_cmt">// 与快传融合通讯开放平台连接</span>
    Ucs_connectconst(char *accountSid, const char *accountToken, const char *ClientNumber, const char *ClientPwd);
}<br /><br />
连接事件相关回调函数
连接成功：
void OnConnectionSuccessful()
{
    <span class="fn_cmt">// 连接成功后的处理代码</span>
}<br /><br />
连接失败：
void OnConnectFailed(int reason)
{
    <span class="fn_cmt">// 连接失败后的处理代码</span>
}            </code>
        </pre>
        <b id="tgw5.1.3.3.3">3.3.3 创建VoIP免费通话(或电话直拨)</b>
        <pre>
            <code class="code_fn">
创建呼叫
{
    <span class="fn_cmt">// 拨打免费通话(对方Client账号) 或 电话直拨(对方电话号码)</span>
    Ucs_dial(type,(LPCTSTR)calledNumber)
}<br /><br />
连接被叫成功回调函数
void onAlerting(const char *callid)
{
    <span class="fn_cmt">// 连接被叫成功</span>
}<br /><br/>
对方接听回调函数
void onAnswer(const char *callid)
{
    <span class="fn_cmt">// 对方已接听</span>
}<br /><br />
呼叫失败(被叫拒接，被叫忙等原因)的回调函数，可参考错误码查找失败原因
void onDialFailed(const char *callid, int reason)
{
    <span class="fn_cmt">// 呼叫失败，可根据reason查找错误原因</span>
}<br /><br />
通话过程中，对方挂断的回调函数
void OnCallReleased(const char *callid)
{
    <span class="fn_cmt">// 通话过程中，对方挂断电话</span>
}            </code>
        </pre>
        <b id="tgw5.1.3.3.4">3.3.4 接听VoIP通话</b>
        <pre>
            <code class="code_fn">
有呼叫呼入回调函数
void onIncomingCall (const char* callid, int callType, const char* caller)
{
    <span class="fn_cmt">// 有VoIP电话呼入处理</span>
}<br /><br />
接通电话
{
    <span class="fn_cmt">// 接听VoIP电话，使用callid参数标识接听某个具体的VoIP电话</span>
    Ucs_answer(callid);
}<br /><br />
接听成功的代理函数
void onAnswer(const char *callid)
{
    <span class="fn_cmt">// 接听成功</span>
}            </code>
        </pre>
        <b id="tgw5.1.3.3.5">3.3.5 挂断VoIP通话</b>
        <pre>
            <code class="code_fn">
{
    <span class="fn_cmt">// 释放呼叫</span>
    Ucs_hangUp(callid);
}          </code>
        </pre>
        <h3 id="tgw5.1.3.4">3.4 编译运行和测试</h3>
        <p>启动调试</p>

      </div>
    </div>
  </div>
  <div class="clear"></div>
</div>
<div class="doc_aside" id="doc_aside">
    <h4>目录 [<a href="javascript:void(0);" id="aside_fold_link">隐藏</a>] </h4>
  <div class="aside_ctn">
    <ul>
      <li><a href="#tgw5.1.1">1. 概述</a>
      <ul>
        <li><a href="#tgw5.1.1.1">1.1 介绍</a></li>
		<li><a href="#tgw5.1.1.2">1.2 开发流程</a></li>
      </ul>
      </li>
	  <li><a href="#tgw5.1.2">2. VoIP快速体验</a>
      <ul>
        <li><a href="#tgw5.1.2.1">2.1 申请测试账号</a></li>
		<li><a href="#tgw5.1.2.2">2.2 环境搭建</a></li>
		<li><a href="#tgw5.1.2.3">2.3 Demo</a></li>
		<li><a href="#tgw5.1.2.4">2.4 解压Demo压缩文件</a></li>
		<li><a href="#tgw5.1.2.5">2.5 运行体验</a></li>
      </ul>
      </li>
	  <li><a href="#tgw5.1.3">3. 创建自己的应用</a>
      <ul>
        <li><a href="#tgw5.1.3.1">3.1  SDK介绍</a></li>
		<li><a href="#tgw5.1.3.2">3.2 环境搭建与创建工程</a></li>
		<li><a href="#tgw5.1.3.3">3.3 编写代码</a>
		<ul>
        <li><a href="#tgw5.1.3.3.1">3.3.1 flypaas SDK初始化</a></li>
		<li><a href="#tgw5.1.3.3.2">3.3.2 登录Client账号</a></li>
		<li><a href="#tgw5.1.3.3.3">3.3.3 创建VoIP免费通话(或电话直拨)</a></li>
		<li><a href="#tgw5.1.3.3.4">3.3.4 接听VoIP通话</a></li>
		<li><a href="#tgw5.1.3.3.5">3.3.5 挂断VoIP通话</a></li>
      </ul>    
	  </li>
	  <li><a href="#tgw5.1.3.4">3.4 编译运行和测试</a></li>
      </ul>
      </li>
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
