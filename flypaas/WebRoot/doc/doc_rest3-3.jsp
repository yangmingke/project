<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ taglib prefix="s"  uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>语音通知</title>
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
        <li> <span>新手指引<i class="parent">&nbsp;</i></span>
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
        <li class="active"> <span>REST API<i class="parent">&nbsp;</i></span>
          <dl>
            <dd><a href="<%=path%>/doc/doc_rest1-1.jsp">REST介绍</a></dd>
            <dd class="parent"> <a href="javascript:void(0)"><em>账户管理</em></a>
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
            <dd class="parent"> <a href="javascript:void(0)" class="active"><em>呼叫</em></a>
              <dl class="child" style="display:block;">
                <dd><a href="<%=path%>/doc/doc_rest3-1.jsp">双向回拨</a></dd>
                <dd><a href="<%=path%>/doc/doc_rest3-2.jsp">语音验证码</a></dd>
                <dd class="last"><a href="<%=path%>/doc/doc_rest3-3.jsp" class="active">语音通知</a></dd>
              </dl>
            </dd>
            <dd class="parent"> <a href="javascript:void(0)"><em>SMS</em></a>
              <dl class="child">
                <dd class="last"><a href="<%=path%>/doc/doc_rest4-1.jsp">短信验证码（模板短信）</a></dd>
              </dl>
            </dd>
          </dl>
        </li>
        <li> <span>Android SDK<i class="parent">&nbsp;</i></span>
          <dl style="display:none;">
            <dd><a href="<%=path%>/doc/doc_android1-1.jsp">Android SDK介绍</a></dd>
            <dd><a href="<%=path%>/doc/doc_android2-1.jsp">初始化及配置接口</a></dd>
            <dd class="parent"> <a href="#"><em>VoIP接口</em></a>
              <dl class="child">
                <dd><a href="<%=path%>/doc/doc_android3-1.jsp">VoIP通话控制接口</a></dd>
                <dd class="last"><a href="<%=path%>/doc/doc_android3-2.jsp">VoIP状态回调接口</a></dd>
              </dl>
            </dd>
            <dd class="parent"> <a href="#"><em>IM接口</em></a>
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
        <li> <span>iOS SDK<i class="parent">&nbsp;</i></span>
          <dl style="display:none;">
            <dd><a href="<%=path%>/doc/doc_ios1-1.jsp">iOS SDK介绍</a></dd>
            <dd class="parent"> <a href="#"><em>初始化及配置接口</em></a>
              <dl class="child">
                <dd><a href="<%=path%>/doc/doc_ios2-1.jsp">初始化及配置</a></dd>
                <dd class="last"><a href="<%=path%>/doc/doc_ios2-2.jsp">初始化及配置代理接口</a></dd>
              </dl>
            </dd>
            <dd class="parent"> <a href="#"><em>VoIP接口</em></a>
              <dl class="child">
                <dd><a href="<%=path%>/doc/doc_ios3-1.jsp">VoIP能力接口</a></dd>
                <dd class="last"><a href="<%=path%>/doc/doc_ios3-2.jsp">VoIP能力代理接口</a></dd>
              </dl>
            </dd>
            <dd class="parent"> <a href="#"><em>IM接口</em></a>
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
        <li> <span>Windows SDK<i class="parent">&nbsp;</i></span>
          <dl style="display:none;">
            <dd><a href="<%=path%>/doc/doc_windows1-1.jsp">Windows SDK介绍</a></dd>
            <dd class="parent"> <a href="#"><em>初始化及配置接口</em></a>
              <dl class="child">
                <dd><a href="<%=path%>/doc/doc_windows2-1.jsp">初始化及配置</a></dd>
                <dd class="last"><a href="<%=path%>/doc/doc_windows2-2.jsp">初始化及配置回调函数</a></dd>
              </dl>
            </dd>
            <dd class="parent"> <a href="#"><em>VoIP接口</em></a>
              <dl class="child">
                <dd><a href="<%=path%>/doc/doc_windows3-1.jsp">VoIP能力接口</a></dd>
                <dd class="last"><a href="<%=path%>/doc/doc_windows3-2.jsp">VoIP能力回调函数</a></dd>
              </dl>
            </dd>
            <dd class="parent"> <a href="#"><em>IM接口</em></a>
              <dl class="child">
                <dd><a href="<%=path%>/doc/doc_windows4-1.jsp">IM能力接口</a></dd>
                <dd class="last"><a href="<%=path%>/doc/doc_windows4-2.jsp">IM能力回调函数</a></dd>
              </dl>
            </dd>
			<dd><a href="<%=path%>/doc/doc_windows6-1.jsp">视频能力接口</a></dd>
          </dl>
        </li>
        <li> <span>应用服务器接口<i class="parent">&nbsp;</i></span>
          <dl style="display:none;">
            <!--<dd><a href="#">接收短信</a></dd>
        		<dd><a href="#">语音验证码状态通知</a></dd>-->
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
        <p><a href="<%=path%>/document">帮助文档首页</a> >  <a href="<%=path%>/doc/doc_rest1-1.jsp">REST API</a> > <a href="<%=path%>/doc/doc_rest3-1.jsp">呼叫</a> > <span>语音通知</span></p>
      </div>
      <div class="display_tit">
        <h1><span class="intro">语音通知</span></h1>
      </div>
      <div class="display_ctn">        
        <h2 id="tgr3.3.1">1、接口目的</h2>
        <p>主要是满足开发者语音通知的业务需求，提供REST API接口，接口如下所述。</p>
        <h2 id="tgr3.3.2">2、接口规则</h2>
        <p>① 非协议用户，上传的语音或文本内容，后台须审核，审核通过之后外呼，满足及时性要求不高的客户需求。</p>
        <p>② 协议用户，上传的语音或文本内容，后台不审核直接外呼，满足及时性要求高的客户需求。</p>
        <p>③ 语音文件形式：先在管理中心上传语音文件，后台审核通过之后，后续开发者每次传入语音文件名或ID，调用接口就可以自动外呼出去，无须审核，及时性比较高。</p>
        <p>④ 文本内容形式：在调用接口上上传文本内容，后台审核通过之后，平台自动转换成语音文件后，自动外呼出去，需要审核，及时性比较低。</p>
        <h2 id="tgr3.3.3">3、接口详情</h2>
        <h3 id="tgr3.3.3.1">3.1 请求</h3>
        <b>• 请求地址</b>
        <p class="code"><span>/{SoftVersion}/Accounts/{accountSid}/Calls/voiceNotify</span></p>
        <b>• 请求包头</b>
        <p><span>详情请查阅统一请求包头，并使用开发者账号进行验证。</span></p>
        <b>• 请求包体</b>
        <table cellspacing="0" cellpadding="0" border="0" width="100%;">
          <tbody>
            <tr>
              <th style="width:20%;">属性</th>
              <th style="width:15%;">类型</th>
              <th style="width:15%;">约束</th>
              <th style="width:50%;">说明</th>
            </tr>
            <tr>
              <td class="red">appId</td>
              <td class="red">String</td>
              <td class="red">必选</td>
              <td><span class="red">应用Id</span></td>
            </tr>
            <tr>
              <td>to</td>
              <td>String</td>
              <td>必选</td>
              <td><span>被叫号码，可以是正常手机号码、固定号码，被叫为座机时需要添加区号，如：075512345678。</span></td>
            </tr>
            <tr>
              <td>type</td>
              <td>String</td>
              <td>必选</td>
              <td><span>内容类型：0：文本；1：语音ID</span></td>
            </tr>
            <tr>
              <td>content</td>
              <td>String</td>
              <td>必选</td>
              <td><span>当type为0时：文本内容，平台负责将该内容转成语音，呼通指定号码后，播放该语音文件；当type为1时：表示语音ID（语音文件必须先上传）。</span></td>
            </tr>
            <tr>
              <td>toSerNum</td>
              <td>String</td>
              <td>可选</td>
              <td><span>语音通知的被叫侧显示的号码。可显示手机号码、400号码或固话。查阅<a style="text-decoration:none;" href="http://docs.flypaas.com/doku.php?=显号规则说明">显号规则</a>。</span></td>
            </tr>
            <tr>
              <td>playTimes</td>
              <td>String</td>
              <td>可选</td>
              <td><span>循环播放次数，1－3次，默认播放1次。</span></td>
            </tr>
          </tbody>
        </table>
        <b>• XML请求示例</b>
        <pre>
          <code class="code_fn">
POST/2014-06-30/Accounts/e03bc9106c6ed0eaebfce8c368fdcd48/Calls/voiceNotify.xml?sig=841D35E63BB81C1D13918B4DD6CD988E
Host:api.flypaas.com
Accept:application/xml
Content-Type:application/xml;charset=utf-8
Authorization:ZTAzYmM5MTA2YzZlZDBlYWViZmNlOGMzNjhmZGNkNDg6MjAxNDA2MjMxODQ1MjM=

&lt;?xml version="1.0" encoding="UTF-8" standalone="yes"?&gt;
&lt;voiceNotify&gt;
  &lt;appId&gt;e462aba25bc6498fa5ada7eefe1401b7&lt;/appId&gt;
  &lt;to&gt;18612345678&lt;/to&gt;
  &lt;type&gt;0&lt;/type&gt;
  &lt;content&gt;测试&lt;/content&gt;
  &lt;toSerNum&gt;075512345678&lt;/toSerNum&gt;
  &lt;playTimes&gt;3&lt;/playTimes&gt;
&lt;/voiceNotify&gt;</code>
        </pre>
        <b>• JSON请求示例</b>
        <pre>
          <code class="code_fn">
POST/2014-06-30/Accounts/e03bc9106c6ed0eaebfce8c368fdcd48/Calls/voiceNotify?sig=4D3C2549D3A487841CB3D84EA7421FCD
Host:api.flypaas.com
Accept:application/json
Content-Type:application/json;charset=utf-8

Authorization:ZTAzYmM5MTA2YzZlZDBlYWViZmNlOGMzNjhmZGNkNDg6MjAxNDA2MjMxODQ3MzQ=  
{  
  "voiceNotify"  : {     
    "appId"      : "e462aba25bc6498fa5ada7eefe1401b7",     
    "to"         : "18612345678",
    "type"       : "0",   
    "content"    : "测试",
    "toSerNum"  : "075512345678",  
    "playTimes" : "3"     
  } 
}</code>
        </pre>
        <h3 id="tgr3.3.3.2">3.2 第三方AS响应</h3>
        <b>• 响应包体</b>
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
              <td>int</td>
              <td>必选</td>
              <td><span>返回错误码，000000：成功，非000000:失败</span></td>
            </tr>
          </tbody>
        </table>
        <b>• XML响应示例</b>
        <pre>
          <code class="code_fn">
&lt;?xml version="1.0" encoding="utf-8" standalone="yes"?&gt;
&lt;resp&gt;
  &lt;respCode&gt;000000&lt;/respCode&gt;
&lt;/resp&gt;</code>
        </pre>
        <b>• JSON响应示例</b>
        <pre>
          <code class="code_fn">
{"resp":{"respCode":"000000"}} </code>
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
      <li><a href="#tgr3.3.1">1、接口目的</a></li>
      <li><a href="#tgr3.3.2">2、接口规则</a></li>
      <li><a href="#tgr3.3.3">3、接口详情</a>
        <ul>
          <li><a href="#tgr3.3.3.1">3.1 请求</a></li>
          <li><a href="#tgr3.3.3.2">3.2 第三方AS响应</a></li>    
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
