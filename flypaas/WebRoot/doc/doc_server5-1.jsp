<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ taglib prefix="s"  uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>呼叫挂机计费接口</title>
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
        <li  class="active">
        	<span>应用服务器接口<i class="parent">&nbsp;</i></span>
        	<dl style="display:block;">
        		<!--<dd><a href="#">接收短信</a></dd>-->
        		<dd><a href="<%=path%>/doc/doc_server6-1.jsp">呼叫鉴权流程</a></dd>
        		<dd><a href="<%=path%>/doc/doc_server3-1.jsp">呼叫鉴权请求接口</a></dd>
        		<dd><a href="<%=path%>/doc/doc_server4-1.jsp">呼叫建立通知接口</a></dd>
        		<dd><a href="<%=path%>/doc/doc_server5-1.jsp" class="active">呼叫挂机计费接口</a></dd>
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
        <p><a href="<%=path%>/document">帮助文档首页</a> > <a href="<%=path%>/doc/doc_server6-1.jsp">应用服务器接口</a> > <span>呼叫挂机计费接口</span></p>
      </div>
      <div class="display_tit">
        <h1 style="width:320px;"><span class="intro">呼叫挂机计费接口</span></h1>
      </div>
      <div class="display_ctn">
        <h3 id="tgs5.1.1">1.1 平台请求</h3>
        <b id="tgs5.1.1.1">• 请求地址</b>
        <p>需要第三方自行配置URL地址。</p>
        <b id="tgs5.1.1.2">• 请求包体</b>
        <table cellspacing="0" cellpadding="0" border="0" width="100%;">
          <tbody>
            <tr>
              <th style="width:20%;">属性</th>
              <th style="width:15%;">类型</th>
              <th style="width:15%;">约束</th>
              <th style="width:50%;">说明</th>
            </tr>
            <tr>
              <td>event</td>
              <td>string</td>
              <td>必选</td>
              <td><span>值为：callhangup</span></td>
            </tr>
            <tr>
              <td>callid</td>
              <td>string</td>
              <td>必选</td>
              <td><span>呼叫的唯一标识（沿用原来机制，由sdk组件生成）</span></td>
            </tr>
            <tr>
              <td>accountid</td>
              <td>string</td>
              <td>必选</td>
              <td><span>开发者账号id</span></td>
            </tr>
            <tr>
              <td>appid</td>
              <td>string</td>
              <td>必选</td>
              <td><span>应用id</span></td>
            </tr>
            <tr>
              <td>calltype</td>
              <td>int</td>
              <td>必选</td>
              <td><span>0：直拨，1：免费，2：回拨</span></td>
            </tr>
            <tr>
              <td>callertype</td>
              <td>int</td>
              <td>必选</td>
              <td><span>主叫号码类型，0：Client账号，1：普通电话</span></td>
            </tr>            
            <tr>
              <td>caller</td>
              <td>string</td>
              <td>必选</td>
              <td><span>主叫号码</span><span>普通电话：18612345678</span><span>Client号码：60000000000017</span></td>
            </tr>
            <tr>
              <td>calledtype</td>
              <td>int</td>
              <td>必选</td>
              <td><span>被叫号码类型，0：Client账号，1：普通电话</span></td>
            </tr>
            <tr>
              <td>called</td>
              <td>string</td>
              <td>必选</td>
              <td><span>被叫号码</span><span>普通电话：18612345678</span><span>Client号码：60000000000017</span></td>
            </tr>
            <tr>
              <td>starttime</td>
              <td>string</td>
              <td>必选</td>
              <td><span>开始通话时间</span><span>时间格式如：2014-06-16 16:47:28
</span></td>
            </tr>
            <tr>
              <td>stoptime</td>
              <td>string</td>
              <td>必选</td>
              <td><span>结束通话时间</span><span>时间格式如：2014-06-16 17:31:14</span></td>
            </tr>
            <tr>
              <td>length</td>
              <td>int</td>
              <td>必选</td>
              <td><span>通话时长(s)</span></td>
            </tr>
            <tr>
              <td>recordurl</td>
              <td>String</td>
              <td>可选</td>
              <td><span>通话录音完整下载地址，默认为空。</span></td>
            </tr>
            <tr>
              <td>reason</td>
              <td>int</td>
              <td>必选</td>
              <td><span>挂机原因描述，0：正常挂断；1：余额不足；2：媒体超时；255：其他原因。</span></td>
            </tr>
          </tbody>
        </table>
        <b id="tgs5.1.1.3">• XML请求示例</b>
        <pre>
          <code class="code_fn">
POST /coolweb/callhangup HTTP/1.1
Host: 192.168.0.109:8080
Content-Type:text/xml;charset=utf-8
Accept:application/xml
Content-Length: 515

&lt;?xml version="1.0"?&gt;
&lt;request&gt;
    &lt;event&gt;callhangup&lt;/event&gt;
    &lt;callid&gt;60000000000008mRrDm254582&lt;/callid&gt;
    &lt;accountid&gt;aae25ec101fc12087516bc6564d0aa73&lt;/accountid&gt;
    &lt;appid&gt;0e0ad5c8ba5c4225b9eff2f4c0259196&lt;/appid&gt;
    &lt;calltype&gt;0&lt;/calltype&gt;
    &lt;callertype&gt;0&lt;/callertype&gt;
    &lt;caller&gt;60000000000008&lt;/caller&gt;
    &lt;calledtype&gt;1&lt;/calledtype&gt;
    &lt;called&gt;18612345678&lt;/called&gt;
    &lt;length&gt;8&lt;/length&gt;
    &lt;starttime&gt;2014-07-11 10:29:27&lt;/starttime&gt;
    &lt;stoptime&gt;2014-07-11 10:29:35&lt;/stoptime&gt;
    &lt;reason&gt;0&lt;/reason&gt;
&lt;/request&gt;</code>
        </pre>
        <h3 id="tgs5.1.2">1.2 第三方AS响应</h3>
        <b id="tgs5.1.2.1">响应包体</b>
        <table cellspacing="0" cellpadding="0" border="0" width="100%;">
          <tbody>
            <tr>
              <th style="width:20%;">属性</th>
              <th style="width:15%;">类型</th>
              <th style="width:15%;">约束</th>
              <th style="width:50%;">说明</th>
            </tr>
            <tr>
              <td class="red">retcode</td>
              <td class="red">int</td>
              <td>必选</td>
              <td><span class="red">返回错误码，0：成功，非0：失败</span></td>
            </tr>            
            <tr>
              <td>reason</td>
              <td>String</td>
              <td>可选</td>
              <td><span>原因描述</span></td>
            </tr>
          </tbody>
        </table>
        <b id="tgs5.1.2.2">• XML响应示例</b>
        <pre>
          <code class="code_fn">
&lt;?xml version="1.0" encoding="UTF-8"?&gt;
&lt;response&gt;
    &lt;retcode&gt;0&lt;/retcode&gt;
    &lt;reason&gt;100013&lt;/reason&gt;
&lt;/response&gt;</code>
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
      <li><a href="#tgs5.1.1">1. 平台请求</a>
      <ul>
        <li><a href="#tgs5.1.1.1">1.1 请求地址</a></li>
		<li><a href="#tgs5.1.1.2">1.2 请求包体</a></li>
        <li><a href="#tgs5.1.1.3">1.3  XML请求示例</a></li>
      </ul>
      </li>
	  <li><a href="#tgs5.1.2">2. 第三方AS响应</a>
      <ul>
        <li><a href="#tgs5.1.2.1">2.1 响应包体</a></li>
		<li><a href="#tgs5.1.2.2">2.2 XML响应示例</a></li>
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