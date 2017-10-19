<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ taglib prefix="s"  uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>rest开发文档</title>
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
        <li class="active">
        	<span>REST API<i class="parent">&nbsp;</i></span>
        	<dl>
        		<dd><a href="<%=path%>/doc/doc_rest1-1.jsp" class="active">REST介绍</a></dd>
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
      <div class="pathbox">
        <p><a href="<%=path%>/document">帮助文档首页</a> >  <a href="<%=path%>/doc/doc_rest1-1.jsp">REST API</a> > <span>REST介绍</span></p>
      </div>
      <div class="display_tit">
        <h1><span class="intro">REST介绍</span></h1>
      </div>
      <div class="display_ctn">
        <h3 id="tgr1.1.1">REST 简介</h3>
        <p>快传融合通讯开放平台rest 接口为开发者提供<a href="<%=path%>/doc/doc_rest2-1.jsp">开发者账号信息查询</a>、<a href="<%=path%>/doc/doc_rest2-2.jsp">申请Client</a>、<a href="<%=path%>/doc/doc_rest2-3.jsp">释放Client</a>、<a href="<%=path%>/doc/doc_rest2-4.jsp">获取Client</a>、<a href="<%=path%>/doc/doc_rest2-5.jsp">Client信息查询(Client账号)</a>、<a href="<%=path%>/doc/doc_rest2-9.jsp">Client信息查询(手机号码)</a>、<a href="<%=path%>/doc/doc_rest2-6.jsp">应用话单下载</a>、<!--<a href="<%=path%>/doc/doc_rest2-7.jsp">Client话单下载</a>、--><a href="<%=path%>/doc/doc_rest2-8.jsp">Client充值</a>、<a href="<%=path%>/doc/doc_rest4-1.jsp">模版短信</a>、<a href="<%=path%>/doc/doc_rest3-1.jsp">双向回拨</a>、<a href="<%=path%>/doc/doc_rest3-2.jsp">语音验证码</a>等功能。</p>
        <p>在使用快传融合通讯开放平台REST API前，请您在网站首页注册账号，您将一个主账户和若干Client，您可以通过Client管理客户信息以及控制用户使用情况。</p>
        <p>API是基于rest原则上的，您可以使用几乎任何客户端在任何编程语言与REST API进行交互，以及编写和测试应用程序。</p>
        <h3 id="tgr1.1.2">Base URL</h3>
        <p>文档中所有请求的URL地址都须加上如下Base URL：</p>
        <p class="code"><span>https://api.flypaas.com/{SoftVersion}/</span></p>
        <p>注意： 为了确保数据隐私和安全， REST API须通过HTTPS方式请求。</p>
        <h3 id="tgr1.1.3">统一请求包头</h3>
        <b>请求URL格式</b>
        <p class="code"><span>{SoftVersion}/Accounts/{accountSid}/{function}/{operation}?sig={SigParameter}</span></p>
        <b>HTTP标准包头字段（必填）</b>
        <p class="code"><span>Accept:application/xml;<br />Content-Type:application/xml;charset=utf-8;<br />Content-Length:256;</span></p>
        <h3 id="tgr1.1.4">属性说明</h3>
        <table cellspacing="0" cellpadding="0" border="0" width="100%;">
        	<tbody>
        		<tr>
        			<th style="width:20%;">属性</th><th style="width:15%;">类型</th><th style="width:15%;">约束</th><th style="width:50%;">说明</th>
        		</tr>
        		<tr>
        			<td>SoftVersion</td><td>String</td><td>必选</td><td><span>快传REST API版本号。</span><span>当前版本号为：2014-06-30</span></td>
        		</tr>
        		<tr>
        			<td>accountSid</td><td>String</td><td>必选</td><td><span>开发者账号ID。由32个英文字母和阿拉伯数字组成的开发者账号唯一标识符。</span></td>
        		</tr>
        		<tr>
        			<td>SigParameter</td><td>String</td><td>必选</td><td><span>请求URL必须带有此参数。</span></td>
        		</tr>
        		<tr>
        			<td>Accept</td><td>String</td><td>必选</td><td><span>客户端响应接收数据格式：application/xml、application/json</span></td>
        		</tr>
        		<tr>
        			<td>Content-Type</td><td>String</td><td>必选</td><td><span>类型：application/xml;charset=utf-8、application/json;charset=utf-8</span></td>
        		</tr>
        		<tr>
        			<td>Authorization</td><td>String</td><td>必选</td><td><span>验证信息。</span></td>
        		</tr>
        		<tr>
        			<td>function</td><td>String</td><td>可选</td><td><span>业务功能。</span></td>
        		</tr>
        		<tr>
        			<td>operation</td><td>String</td><td>可选</td><td><span>业务操作，业务功能的各类具体操作分支。</span></td>
        		</tr>
        	</tbody>
        </table>
        <b>说明</b>
        <p class="explain">1. SoftVersion是当前使用的REST API版本号，开发时须填写正确的版本号。</p>
        <p class="explain">2. SigParameter是REST API 验证参数<br />
        <span>• URL后必须带有sig参数，sig= MD5（账户Id + 账户授权令牌 + 时间戳），共32位(注:转成大写)</span><br />
        <span>• 使用MD5加密（账户Id + 账户授权令牌 + 时间戳），共32位。</span><br />
        <span>• 时间戳是当前系统时间（24小时制），格式"yyyyMMddHHmmss"。时间戳有效时间为50分钟。</span>
        </p>
        <p class="explain">3. Authorization是包头验证信息<br />
        <span>• 使用Base64编码（账户Id + 冒号 + 时间戳）</span><br />
        <span>• 冒号为英文冒号</span><br />
        <span>• 时间戳是当前系统时间（24小时制），格式"yyyyMMddHHmmss"，需与SigParameter中时间戳相同。</span>
        </p>
        <p class="explain">4. function描述对应业务能力，operation描述业务能力的具体操作。例如：/Calls/voiceCode</p>
        <h3>数据报文格式</h3>
        <p>REST API支持两种主流的报文格式：XML和JSON。</p>
        <p>通过请求包头的字段Content-Type及Accept，即可决定请求包体和响应包体的格式，如：</p>
        <p class="code"><span>Content-Type:application/xml;charset=utf-8;Accept:application/xml; </span></p>
        <p>表示请求类型格式是XML，要求服务器响应的包体类型也是XML；</p>
        <p class="code"><span>Content-Type:application/json;charset=utf-8;Accept:application/json; </span></p>
        <p>表示请求类型格式是JSON，要求服务器响应类型也是JSON；</p>
      </div>
    </div>
  </div>
  <div class="clear"></div>
</div>
<div class="doc_aside" id="doc_aside">
    <h4>目录 [<a href="javascript:void(0);" id="aside_fold_link">隐藏</a>] </h4>
  <div class="aside_ctn">
    <ul>
      <li><a href="#tgr1.1.1">1. REST 简介</a></li>
	  <li><a href="#tgr1.1.2">2. Base URL</a></li>
      <li><a href="#tgr1.1.3">3. 统一请求包头</a></li>
	  <li><a href="#tgr1.1.4">4. 属性说明</a></li>
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
