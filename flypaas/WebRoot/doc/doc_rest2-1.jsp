<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ taglib prefix="s"  uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>开发者账号信息查询</title>
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
        		<dd><a href="<%=path%>/doc/doc_rest1-1.jsp">REST介绍</a></dd>
        		<dd class="parent">
        			<a href="javascript:void(0)" class="active"><em>账户管理</em></a>
        			<dl class="child" style="display:block;">
        				<dd><a href="<%=path%>/doc/doc_rest2-1.jsp" class="active">开发者账号信息查询</a></dd>
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
        <p><a href="<%=path%>/document">帮助文档首页</a> > <a href="<%=path%>/doc/doc_rest1-1.jsp">REST API</a> > <a href="<%=path%>/doc/doc_rest2-1.jsp">账户管理</a> > <span>开发者账号信息管理</span></p>
      </div>
      <div class="display_tit">
        <h1><span class="intro">开发者账号信息查询</span></h1>
      </div>
      <div class="display_ctn">
        <p>账户信息查询是快传融合通讯开放平台提供的一个账户管理API，通过此REST API可以方便开发者对账户进行管理。</p>
        <p>通过HTTPS GET方式提交请求，快传融合通讯开放平台收到请求后，返回开发者账号信息。</p>
        <h3 id="tgr2.1.1">1.1 请求</h3>
        <b id="tgr2.1.1.1">• 请求地址</b>
        <p class="code"><span>/{SoftVersion}/Accounts/{accountSid}</span></p>
        <b id="tgr2.1.1.2">• 请求包头</b>
        <p><span>详情请查阅统一请求包头，并使用开发者账号进行验证。</span></p>
        <b id="tgr2.1.1.3">• 请求包体</b>
        <p><span>无</span></p>
        <b id="tgr2.1.1.4">• XML请求示例</b>
		<pre>
          <code class="code_fn">
GET/2014-06-30/Accounts/e03bc9106c6ed0eaebfce8c368fdcd48.xml?sig=EAC64ABD6CB753EFAC0E8C33D79CB50C
Host:api.flypaas.com
Accept:application/xml
Content-Type:application/xml;charset=utf-8
Authorization:ZTAzYmM5MTA2YzZlZDBlYWViZmNlOGMzNjhmZGNkNDg6MjAxNDA2MjMxODU1NDA=</code>
        </pre>
        <b id="tgr2.1.1.5">• JSON请求示例</b>
		<pre>
          <code class="code_fn">
GET/2014-06-30/Accounts/e03bc9106c6ed0eaebfce8c368fdcd48?sig=BBDE934B41922D03834D38B3EAD77752
Host:api.flypaas.com
Accept:application/json
Content-Type:application/json;charset=utf-8
Authorization:ZTAzYmM5MTA2YzZlZDBlYWViZmNlOGMzNjhmZGNkNDg6MjAxNDA2MjMxODU0NDQ=</code>
        </pre>
        <h3 id="tgr2.1.2">1.2 响应</h3>
        <b id="tgr2.1.2.1">• 响应包体</b>
        <table cellspacing="0" cellpadding="0" border="0" width="100%;">
          <tbody>
            <tr>
              <th style="width:20%;">属性</th>
              <th style="width:15%;">类型</th>
			  <th style="width:15%;">约束</th>
              <th style="width:50%;">说明</th>
            </tr>
            <tr>
              <td>respCode</td>
              <td>String</td>
			  <td>必选</td>
              <td><span>请求状态码，取值000000（成功）</span></td>
            </tr>
            <tr>
              <td>accountSid</td>
              <td>String</td>
			  <td>必选</td>
              <td><span>开发者账号Id。由32个英文字母和阿拉伯数字组成的开发者账号唯一标识符</span></td>
            </tr>
            <tr>
              <td>nickName</td>
              <td>String</td>
			  <td>必选</td>
              <td><span>开发者账号昵称，由32个英文字母、汉字和阿拉伯数字组成</span></td>
            </tr>
            <tr>
              <td>createDate</td>
              <td>String</td>
			  <td>必选</td>
              <td><span>开发者账号的创建时间</span></td>
            </tr>
            <tr>
              <td>updateDate</td>
              <td>String</td>
			  <td>必选</td>
              <td><span>开发者账号的最近更新时间</span></td>
            </tr>
            <tr>
              <td>email</td>
              <td>String</td>
			  <td>必选</td>
              <td><span>绑定的邮箱地址</span></td>
            </tr>
            <tr>
              <td>mobile</td>
              <td>String</td>
			  <td>必选</td>
              <td><span>绑定的手机号码，目前只能绑定国内的手机号码</span></td>
            </tr>
            <tr>
              <td class="red">type</td>
              <td class="red">String</td>
			  <td>必选</td>
              <td><span class="red">开发者账号类型，1 个人、2 企业</span></td>
            </tr>
            <tr>
              <td class="red">status</td>
              <td class="red">String</td>
			  <td>必选</td>
              <td><span class="red">开发者账号状态， 0 注册未激活、1 邮箱已激活、5 锁定、6 关闭</span></td>
            </tr>
            <tr>
              <td>balance</td>
              <td>String</td>
			  <td>必选</td>
              <td><span>开发者账号余额</span></td>
            </tr>
          </tbody>
        </table>
        <b id="tgr2.1.2.2">• XML响应示例</b>		
        <pre>
          <code class="code_fn">
&lt;?xml version="1.0" encoding="UTF-8" standalone="yes"?&gt;
&lt;resp&gt;
    &lt;respCode&gt;000000&lt;/respCode&gt;
    &lt;account&gt; <span class="fn_cmt">/*account为节点*/</span>
        &lt;accountSid&gt;e03bc9106c6ed0eaebfce8c368fdcd48&lt;/accountSid&gt;
        &lt;balance&gt;9.5&lt;/balance>
        &lt;createDate&gt;2014-06-20 19:00:11&lt;/createDate&gt;
        &lt;email&gt;michael_bay@126.com&lt;/email&gt;
        &lt;mobile/&gt;
        &lt;nickName&gt;QQQ&lt;/nickName&gt;
        &lt;status&gt;1&lt;/status&gt;
        &lt;type&gt;1&lt;/type&gt;
        &lt;updateDate&gt;2014-06-23 15:25:58&lt;/updateDate&gt;
    &lt;/account&gt;
&lt;/resp&gt;</code>
        </pre>
		<b id="tgr2.1.2.3">• JSON响应示例</b>
        <pre>
          <code class="code_fn">
{
 "resp"    : {
    "respCode"   : "000000",
    "account"    : {
        "accountSid" : "e03bc9106c6ed0eaebfce8c368fdcd48",
        "balance"    : 9.5,
        "createDate" : "2014-06-20 19:00:11",
        "email"      : "michael_bay@126.com",
        "mobile"     : "",
        "nickName"   : "QQQ",
        "status"     : 1,
        "type"       : 1,
        "updateDate" : "2014-06-23 15:25:58"
        }
    }
}</code>
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
      <li><a href="#tgr2.1.1">1. 请求</a>
      <ul>
        <li><a href="#tgr2.1.1.1">1.1 请求地址</a></li>
		<li><a href="#tgr2.1.1.2">1.2 请求包头</a></li>
		<li><a href="#tgr2.1.1.3">1.3 请求包体</a></li>
		<li><a href="#tgr2.1.1.4">1.4 XML请求示例</a></li>
		<li><a href="#tgr2.1.1.5">1.5 JSON请求示例</a></li>        
      </ul>
      </li>
	  <li><a href="#tgr2.1.2">2. 响应</a>
      <ul>
        <li><a href="#tgr2.1.2.1">2.1 响应包体</a></li>
		<li><a href="#tgr2.1.2.2">2.2 XML响应示例</a></li>
		<li><a href="#tgr2.1.2.3">2.3 JSON响应示例</a></li>     
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
