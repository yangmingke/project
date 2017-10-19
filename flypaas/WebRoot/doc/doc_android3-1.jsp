<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ taglib prefix="s"  uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>VoIP通话控制接口</title>
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
        <li class="active">
        	<span>Android SDK<i class="parent">&nbsp;</i></span>
        	<dl>
                <dd><a href="<%=path%>/doc/doc_android6-1.jsp">Android开发指南</a></dd>
        		<dd><a href="<%=path%>/doc/doc_android1-1.jsp">Android SDK介绍</a></dd>
        		<dd><a href="<%=path%>/doc/doc_android2-1.jsp">初始化及配置接口</a></dd>
        		<dd class="parent">
        			<a href="#" class="active"><em>VoIP接口</em></a>
        			<dl class="child" style="display:block;">
        				<dd><a href="<%=path%>/doc/doc_android3-1.jsp" class="active">VoIP通话控制接口</a></dd>
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
      <div class="pathbox"><p><a href="<%=path%>/document">帮助文档首页</a> > <a href="<%=path%>/doc/doc_android6-1.jsp">Android SDK</a> > <a href="<%=path%>/doc/doc_android3-1.jsp">VoIP接口</a> > <span>VoIP通话控制接口</span></p></div>
      <div class="display_tit">
        <h1 style="width:320px;"><span class="intro">VoIP通话控制接口</span></h1>
      </div>
      <div class="display_ctn">
        <p>目的是提供发送VoIP和互联网语音的能力接口。</p>
        <h3 id="tga3.1.1">1. 注册VoIP电话监听器</h3>
        <pre>
          <code>
/**
 * 注册VoIP电话监听器，监听VoIP电话的各种状态
 * @param csl  CallStateListener监听器实例  
 * @return void
 */
<span class="key_label">UCSCall. addCallStateListener(CallStateListener csl)</span></code>
        </pre>
        <h3 id="tga3.1.2">2. 发起呼叫（不含回拨）</h3>
        <pre>
          <code>
/**
 *发起呼叫
 * @param mContext  当前程序上下文 ApplicationContext 
 * @param callType 拨打电话方式，类型为：VOIP:免费, DIRECT：直拨, CALL_AUTO：智能呼叫(入参为手机号码) 
 * @param calledNumber 被叫手机号码或Client账号
 * @return void 
 */
<span class="key_label">UCSCall. dial(Context mContext,CallType callType,String calledNumner);</span></code>
        </pre>
        <h3 id="tga3.1.3">3. 发起回拨呼叫</h3>
        <pre>
          <code>
/**
 * @param mContext
 * @param calledNumner：被叫号码
 * @param fromSerNum：呼叫主叫所要显示的号码（没有可以不传）
 * @param toSerMum：呼叫被叫所要显示的号码（没有可以不传）
 */
<span class="key_label">UCSCall.callBack(Context mContext,String calledNumner,String fromSerNum,String toSerMum)</span></code>
        </pre>
        <h3 id="tga3.1.4">4. 释放通话</h3>
        <pre>
          <code>
/**
 * 释放通话
 * @param callid  当前通话id (目前版本为空)
 * @return void 
 */
<span class="key_label">UCSCall.hangUp(String callid)</span></code>
        </pre>
        <h3 id="tga3.1.5">5. 接听来电</h3>
        <pre>
          <code>
/**
 * 被叫接听
 * @param callid  当前通话id 
 * @return void 
 */
<span class="key_label">UCSCall.answer(String callid)</span></code>
        </pre>
        <h3 id="tga3.1.6">6. 发送DTMF</h3>
        <pre>
          <code>
/**
 * 发送DTMF
 * @param mContext  Context 
 * @param keyCode  KeyEnvent CODE 
 * @param call_dtmf  用于显示输入的keyCode值
 * @return void
 */
<span class="key_label">UCSCall.sendDTMF(Context mContext, int keyCode, EditText call_dtmf)</span></code>
        </pre>
        <h3 id="tga3.1.7">7. 设置扬声器</h3>
        <pre>
          <code>
/**
 * 设置扬声器
 * @param isSpeakerphoneOn   false:关闭  true:打开 
 * @return void
 */
<span class="key_label">UCSCall.setSpeakerphone(boolean isSpeakerphoneOn)</span></code>
        </pre>
        <h3 id="tga3.1.8">8. 获取扬声器状态</h3>
        <pre>
          <code>
/**
 * 获取扬声器状态
 * @param 
 * @return boolean  true：开启 ；false：关闭
 */
<span class="key_label">UCSCall.isSpeakerphoneOn()</span></code>
        </pre>
        <h3 id="tga3.1.9">9. 设置静音</h3>
        <pre>
          <code>
/**
 * 设置静音
 * @param isMicMute  true：静音 ；false：正常
 * @return void
 */
<span class="key_label">UCSCall.setMicMute(boolean isMicMute)</span></code>
        </pre>
        <h3 id="tga3.1.10">10. 获取静音状态</h3>
        <pre>
          <code>
/**
 * 获取静音状态
 * @param 
 * @return boolean  true：静音 ；false：正常
 */
<span class="key_label">UCSCall.isMicMute()</span></code>
        </pre>
		<h3 id="tga3.1.11">11. 播放来电铃声</h3>
        <pre>
          <code>
/**
 * 播放来电铃声
 * @param  isVibrator:是否振动
 * @return void
 */
<span class="key_label">UCSCall.startRinging(boolean isVibrator)</span></code>
        </pre>		
		<h3 id="tga3.1.12">12. 停止播放来电铃声</h3>
        <pre>
          <code>
/**
 * 停止播放来电铃声
 * @return void
 */
<span class="key_label">UCSCall.stopRinging()</span></code>
        </pre>
		<h3 id="tga3.1.13">13. 播放去电铃声</h3>
        <pre>
          <code>
/**
 * 播放去电铃声(彩铃)
 * @param fileName  音频裸数据文件名称,为PCM格式，放在assets目录下
 * @return void
 */
<span class="key_label">UCSCall .startCallRinging(String fileName) </span><a href="http://docs.flypaas.com/doku.php?=Android 将MP3制做成PCM铃声文件">如何转为PCM格式</a></code>
        </pre>
		<h3 id="tga3.1.14">14. 停止播放去电铃声</h3>
        <pre>
          <code>
/**
 * 停止播放去电铃声
 * @return void
 */
<span class="key_label">UCSCall .stopCallRinging()</span></code>
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
      <li><a href="#tga3.1.1">1. 注册VoIP电话监听器</a></li>
      <li><a href="#tga3.1.2">2. 发起呼叫（不含回拨）</a></li>
      <li><a href="#tga3.1.3">3. 发起回拨呼叫</a></li>
	  <li><a href="#tga3.1.4">4. 释放通话</a></li>
      <li><a href="#tga3.1.5">5. 接听来电</a></li>
	  <li><a href="#tga3.1.6">6. 发送DTMF</a></li>
      <li><a href="#tga3.1.7">7. 设置扬声器</a></li>
	  <li><a href="#tga3.1.8">7. 获取扬声器状态</a></li>
	  <li><a href="#tga3.1.9">9. 设置静音</a></li>
      <li><a href="#tga3.1.10">10. 获取静音状态</a></li>
	  <li><a href="#tga3.1.11">11. 播放来电铃声</a></li>
	  <li><a href="#tga3.1.12">12. 停止播放来电铃声</a></li>
	  <li><a href="#tga3.1.13">13. 播放去电铃声</a></li>
	  <li><a href="#tga3.1.14">14. 停止播放去电铃声</a></li>
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
