<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ taglib prefix="s"  uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>初始化和配置</title>
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
        	</dl>
        </li>
        <li  class="active">
        	<span>Windows SDK<i class="parent">&nbsp;</i></span>
        	<dl>
                <dd><a href="<%=path%>/doc/doc_windows5-1.jsp">Windows开发指南</a></dd>
        		<dd><a href="<%=path%>/doc/doc_windows1-1.jsp">Windows SDK介绍</a></dd>
        		<dd class="parent">
        			<a href="#" class="active"><em>初始化及配置接口</em></a>
        			<dl class="child" style="display:block;">
        				<dd><a href="<%=path%>/doc/doc_windows2-1.jsp" class="active">初始化及配置</a></dd>
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
        		<!--<dd><a href="#">接收短信</a></dd>-->
        		<dd><a href="<%=path%>/doc/doc_server6-1.jsp">呼叫鉴权流程</a></dd>
        		<dd><a href="<%=path%>/doc/doc_server3-1.jsp">呼叫鉴权请求接口</a></dd>
        		<dd><a href="<%=path%>/doc/doc_server4-1.jsp">呼叫建立通知接口</a></dd>
        		<dd><a href="<%=path%>/doc/doc_server5-1.jsp">呼叫挂机计费接口</a></dd>
                <dd><a href="<%=path%>/doc/doc_server7-1.jsp">语音外呼状态通知接口</a></dd>
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
      <div class="pathbox"><p><a href="<%=path%>/document">帮助文档首页</a> > <a href="<%=path%>/doc/doc_windows5-1.jsp">Windows SDK</a> > <a href="<%=path%>/doc/doc_windows2-1.jsp">初始化及配置接口</a> > <span>初始化及配置</span></p></div>
      <div class="display_tit">
        <h1><span class="intro">初始化和配置</span></h1>
      </div>
      <div class="display_ctn">
        <h3 id="tgw2.1.1">1. 初始化</h3>
        <pre>
          <code>
/* 
  函数名:  Ucs_init
  功  能: 初始化SDK
  返回值: 是否成功 0：成功； 非0失败
*/
<span class="key_label">UCSCLIENT_API int Ucs_init( UCS_CALLBACKINTERFACE *CallbackInterface )</span></code>
        </pre>
        <h3 id="tgw2.1.2">2. 注销</h3>
        <pre>
          <code>
/* 
  函数名   : Ucs_unInit
  功能     : 注销Client
  返回值   : 是否成功 0：成功； 非0失败
*/
<span class="key_label">UCSCLIENT_API  int  Ucs_unInit()</span></code>
        </pre>
        <h3 id="tgw2.1.3">3. 明文方式连接服务器</h3>
        <pre>
          <code>
/*
  函数名   : Ucs_connect
  功能     : 连接快传融合通讯开放平台呼叫控制服务器
  参数     : 
             [IN]  accountSid   : 开发者账号
             [IN]  accountToken   : 开发者账号密码
             [IN]  ClientNumber   : Client账号
             [IN]  ClientPwd    : Client密码
  返回值   : 是否连接成功 0：成功； 非0失败
*/
<span class="key_label">UCSCLIENT_API int Ucs_connect(const char *accountSid, const char *accountToken,
      const char *ClientNumber, const char *ClientPwd);</span></code>
        </pre>
        <!--<h3 id="tgw2.1.4">4. 连接服务器(自定义IP与端口)</h3>
        <pre>
          <code>
/*
	函数名   : Ucs_connect
	功能     : 连接快传融合通讯开放平台
	参数     : [IN]  loginServerIp  : 远程IP或域名
			   [IN]  port		: 远程机器端口
			   [IN]  accountSid		: 开发者账号
			   [IN]  accountToken		: 开发者账号密码
			   [IN]  ClientNumber		: Client账号
			   [IN]  ClientPwd		: Client密码
	返回值   : 是否连接成功 0：成功； 非0失败
*/
<span class="key_label">UCSCLIENT_API int Ucs_connect(const char *loginServerIp, int port, const char *accountSid, const char *accountToken, const char *ClientNumber, const char *ClientPwd);</span></code>
        </pre>-->
		<h3 id="tgw2.1.5">4. 连接服务器(token)</h3>
        <pre>
          <code>
/*
	函数名   : Ucs_connect
	功能     : 连接快传融合通讯开放平台
	参数     : [IN]  token		: 账号加密串
	返回值   : 是否连接成功 0：成功； 非0失败
*/
<span class="key_label">UCSCLIENT_API int Ucs_connect(const char *token);</span></code>
        </pre>
		<!--<h3 id="tgw2.1.6">6. 连接服务器(token 自定义IP与端口)</h3>
        <pre>
          <code>
/*
	函数名   : Ucs_connect
	功能     : 连接快传融合通讯开放平台呼叫控制服务器
	参数     : [IN]  loginServerIp  : 远程IP或域名
			   [IN]  port		: 远程机器端口
               [IN]  token		:账号加密串
	返回值   : 是否连接成功 0：成功； 非0失败
*/
<span class="key_label">UCSCLIENT_API int Ucs_connect(const char *loginServerIp, int port, const char *token);</span></code>
        </pre>
		<h3 id="tgw2.1.7">5. 设置IP地址和端口号</h3>
        <pre>
          <code>
/*
	函数名   : Ucs_isConnected()
	功能     : 设置IP地址和端口号
    参数     : [IN]  listServerIp  : 远程IP或域名
			   [IN]  port		: 远程机器端口
	返回值   : 是否成功 true：在线； false离线
*/
<span class="key_label">UCSCLIENT_API int Ucs_setListServer(const char *listServerIp, int port);</span></code>
        </pre>-->
		<h3 id="tgw2.1.8">5. 获取连接服务器状态</h3>
        <pre>
          <code>
/*
	函数名   : Ucs_isConnected()
	功能     : 获取当前账号与服务器的连接状态
	返回值   : 是否成功 true：在线； false离线
*/
<span class="key_label">UCSCLIENT_API bool Ucs_isConnected();</span></code>
        </pre>
		<h3 id="tgw2.1.9">6. 获取SDK版本号</h3>
        <pre>
          <code>
/*
	函数名   : Ucs_getSDKVersion()
	功能     : 获取当前SDK版本号
	返回值   : 版本字符串
*/
<span class="key_label">UCSCLIENT_API  const char *  Ucs_getSDKVersion ()</span></code>
        </pre>
		<h3 id="tgw2.1.10">7. 获取多用户在线状态</h3>
        <pre>
          <code>
/*
	函数名  : Ucs_queryUserState
	功能    : 获取多用户在线状态
	参数    : [IN]	friendNum	:查询client账号/phone号码数组
	          [IN]	size		:client/phone数组数量
			  [IN]	numType		:查询类型0：按client查询；1:按phone查询
    返回值  : 是否成功 0：成功；非0 :失败
*/
<span class="key_label">UCSCLIENT_API int Ucs_queryUserState (const __int64 *friendNum, int size, int numType);</span></code>
        </pre>
		<h3 id="tgw2.1.11">8. 获取单用户在线状态</h3>
        <pre>
          <code>
/*
	函数名  : Ucs_ queryOneUserState
	功能    : 获取单用户在线状态
	参数    : [IN]	friendNum	:查询client账号/phone号码数组
	          [IN]	numType		:查询类型0：按client查询；1:按phone查询
    返回值  : 是否成功 0：成功；非0 :失败
*/ 
<span class="key_label">UCSCLIENT_API int Ucs_queryOneUserState (const __int64 *friendNum, int numType);</span></code>
        </pre>
      </div>
    </div>
  </div>
  <div class="clear"></div>
</div>
<div class="doc_aside" id="doc_aside">
    <h4>目录 [<a href="javascript:void(0);" id="aside_fold_link">隐藏</a>] </h4>
  <div class="aside_ctn">
    <ul>
      <li><a href="#tgw2.1.1">1. 初始化</a></li>
	  <li><a href="#tgw2.1.2">2. 注销</a></li>
	  <li><a href="#tgw2.1.3">3. 明文方式连接服务器</a></li>
	  <!--<li><a href="#tgw2.1.4">4. 连接服务器(自定义IP与端口)</a></li>-->
	  <li><a href="#tgw2.1.5">4. 连接服务器(token)</a></li>
	  <!--<li><a href="#tgw2.1.6">6. 连接服务器(token 自定义IP与端口)</a></li>
	  <li><a href="#tgw2.1.7">5. 设置IP地址和端口号</a></li>-->
	  <li><a href="#tgw2.1.8">5. 获取连接服务器状态</a></li>
	  <li><a href="#tgw2.1.9">6. 获取SDK版本号</a></li>
	  <li><a href="#tgw2.1.10">7. 获取多用户在线状态</a></li>
	  <li><a href="#tgw2.1.11">8. 获取单用户在线状态</a></li>
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
