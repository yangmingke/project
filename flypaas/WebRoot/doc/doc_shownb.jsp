<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ taglib prefix="s"  uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>显号规则说明_快传融合通讯开放平台</title>
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
    <div class="doc_txt doc_home">
      <!--<div class="pathbox"><p><a href="<%=path%>/document">帮助文档首页</a> > <span>文档首页</span></p></div>-->
      <div class="display_tit">
        <h1><span class="intro">显号规则说明</span></h1>
      </div>
      <div class="display_ctn shownb_ctn">
        <h2 id="tgsh1.1.1">显号规则目的</h2>
		<p>为了规范回拨、落地、语音验证码显号业务流程，减少显号带来的风险。</p>
		<h2 id="tgsh1.1.2">显号规则总则</h2>
		<p>使用回拨、落地、语音验证码能力接口，涉及到显号业务需求时，必须按照显号规则说明使用，否则请求的业务能力将不会显号。</p>
		<p>显号规则如下所示：</p>
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
		    <thead>
			    <tr><th colspan="3">显号</th></tr>
			</thead>
		    <tbody>
			    <tr><th>直拨</th><th>协议用户</th><th>非协议用户</th></tr>
				<tr><td rowspan="3">被叫</td><td>手机号（除被叫手机号）</td><td style="text-align:center;">Client绑定的手机号</td></tr>
                <tr><td>官号</td><td>官号</td></tr>
                <tr><td>非上述号码，被叫不显号</td><td>非上述号码，被叫不显号</td></tr>
                <tr><th>回拨</th><th>协议用户</th><th>非协议用户</th></tr>
                <tr><td rowspan="3">主叫</td><td>官号</td><td>官号</td></tr>
                <tr><td>手机号（除主叫手号）</td><td>——</td></tr>
                <tr><td>非上述号码，不显号</td><td>非官号，不显号</td></tr>
                <tr><td rowspan="3">被叫</td><td>手机号（除被叫手机号）</td><td>Client绑定的手机号</td></tr>
                <tr><td>官号</td><td>官号</td></tr>
                <tr><td>非上述号码，被叫不显号</td><td>非上述号码，被叫不显号</td></tr>
			</tbody>
		</table>
		<h2 id="tgsh1.1.3">显号使用流程</h2>
        <p>进入创建应用界面，选中开启显号服务选项，会弹出回调地址配置选项及显号服务配置选项<span style="color:#f00;">（个人开发者无此选项）</span>：</p>
        <p><img src="<%=path%>/doc/images/doc_img73.png" /></p>
        <p><img src="<%=path%>/doc/images/doc_img74.png" /></p>
        <b style="color:#999; font-size:12px; font-family:arial;">注解：</b>
        <p style="color:#999; font-size:12px; font-family:arial;">• 回调地址的目的：平台会将每路通话的数据通过回调地址通知给开发者AS，方便开发者实现自主计费。</p>
        <p style="color:#999; font-size:12px; font-family:arial;">• 回调地址的作用：<br />
        A、呼叫鉴权请求接口：开发者可以通过该接口的AS响应参数retcode、displaynumber、allowedcalltime分别控制每路通话是否允许建立通话、是否显号以及允许通话时长。<br />
        B、呼叫建立通知接口：平台在呼叫建立时，将通过该接口通知开发者AS当前通话详情。<br />
        C、呼叫挂机计费接口：当用户挂机或其他原因导致通话结束时，将通过该接口通知开发者AS通话结束，以及整个会话的详情，包括通话创建时间、通话结束时间以及通话时长等信息。
        </p>
        <p style="color:#999; font-size:12px; font-family:arial;">③ 接口或回调传值显号：开启显号并配置了所需的回调地址后，即可使用业务接口的显号服务。</p>
        <p style="color:#999; font-size:12px; font-family:arial;">备注：显号规则详情，请参考显号规则描述</p>
        <h2 id="tgsh1.1.4">显号规则描述</h2>
        <h3 id="tgsh1.1.4.1">名称解释：</h3>
        <b>1. 为什么要制定显号规则？</b>
        <p>为了呼叫时被叫端能够显示开发者设置的号码，并且避免一些带有营销、广告性质的号码，我们很贴心的为广大开发者制定了显号规则。</p>
        <b>2、显号规则针对哪几类用户？</b>
        <p>协议用户：在企业认证审核通过后，可以申请成为协议用户。企业需要与快传签署合作协议并交纳一定的保证金。协议用户可显示任意手机号码。<br />非协议用户：个人开发者和未成为协议用户的企业。</p>
        <b>3、什么叫官方号码？</b>
        <p>创建应用时开发者选择开启官方显号服务及开启语音验证显号服务中填写的号码均为官方号码。官方号码格式为固话或400号码，该号码最终会显示在被叫端。</p>
        <b>4、配置了号码就可以显号了吗？</b>
        <p>当开发者提交应用后，还需要经过我们后台人员的应用审核，来确定这些号码是否合法。审核通过后就可以显号了。</p>
        <br />
        <h3 id="tgsh1.1.4.2">语音验证码显号规则</h3>
        <p>面向所有开发者。若需要语音验证码显号，勾选“开启语音验证码服务”的同时填入固话或者400号码（在上图二配置）即可。</p>
        <br />
        <h3 id="tgsh1.1.4.3">直拨显号规则</h3>
        <p>① 直拨显号必须配回调地址，否则直拨将不予显号。</p>        
        <p>③ <span style="color:#53b231;">协议用户：</span> displaynumber的值为合法的电话号码（即11位标准的手机号码、带区号的固话号码、400号码），则displaynumber的值作为被叫侧显示号码；若displaynumber的值为空或00000000000，则被叫侧不显号。</p>
        <p>② <span style="color:#53b231;">非协议用户：</span> displaynumber的值为手机号码、400号码或固话号码，验证通过后将作为被叫侧显示号码；若displaynumber的值为空或00000000000，则被叫侧不显号。</p>
        <br />
        <h3 id="tgsh1.1.4.4">回拨显号规则</h3>
        <p style="color:#53b231;">协议用户：</p>
        <ol>
            已配回调地址（在图一中配置）：
            <li>① Sdk或Rest回拨接口中的toSerNum和fromSerNum为合法的电话号码或者为空。</li>
            <li>② 若①传入的号码都为空，则主被叫侧都不显号。</li>
            <li>③ 开发者AS响应displaynumbe参数值为：<br />a：为空，不做验证判断，并且toSerNum和fromSerNum分别作为最终主被叫侧显示的号码；<br />
            b：不为空，则覆盖toSerNum号码并做为被叫侧显示号码;<br />
            c：若为00000000000，则toSerNum被叫侧不显号。</li>
        </ol>
        <ol>
            未配置回调地址：
            <li>① Sdk或Rest回拨接口中的toSerNum和fromSerNum为合法的电话号码或者为空。</li>
            <li>② 若①传入的号码都为空，则主被叫侧都不显号。</li>
        </ol>
        <br />
        <p style="color:#53b231;">非协议用户：</p>
        <ol>
            已配回调地址（在图一中配置）：
            <li>① Sdk或Rest回拨接口中的toSerNum参数值若为：<br />
            a：为空，不做验证判断；<br />b：不为空，先验证是否是官方号码，不是则验证是否是与Client绑定的手机号码，都不是则回拨请求不会显号。
            </li>
            <li>② Sdk或Rest回拨接口中的fromSerNum参数值若为：<br />
            a：为空，不做验证判断；<br />
            b：不为空，只验证是否是官方号码，不是则回拨请求不会显号。
            </li>
            <li>③ 开发者AS响应displaynumbe参数值为：<br />
            a：为空，不做验证判断；<br />
            b：不为空，先验证是否是官方号码，不是则验证是否是与Client绑定的手机号码，都不是则回拨请求不会显号；<br />
            c：验证通过，则覆盖SDK或Rest回拨接口中的toSerNum号码并做为被叫侧显示号码;<br />
            d：若为00000000000，则toSerNum被叫侧不显号。</li>
            <li>④ 若①②③传入的号码都为空，则不做验证判断，并且主被叫侧都不显号。</li>
        </ol>
        <ol>
            未配置回调地址：
            <li>① Sdk或Rest回拨接口中的toSerNum参数值若为：<br />
            a：为空，不做验证判断；<br />b：不为空，先验证是否是官方号码，不是则验证是否是与Client绑定的手机号码，都不是则回拨请求不会显号。
            </li>
            <li>② Sdk或Rest回拨接口中的fromSerNum参数值若为：<br />
            a：为空，不做验证判断；<br />
            b：不为空，只验证是否是官方号码，不是则回拨请求不会显号。
            </li>
            <li>③ 若①②传入的号码都为空，则主被叫侧都不显号。</li>
        </ol>
	  </div>
    </div>
  </div>
  <div class="clear"></div>
</div>
<div class="doc_aside" id="doc_aside">
    <h4>目录 [<a href="javascript:void(0);" id="aside_fold_link">隐藏</a>] </h4>
  <div class="aside_ctn">
    <ul>
      <li><a href="#tgsh1.1.1">1. 显号规则目的</a></li>
	  <li><a href="#tgsh1.1.2">2. 显号规则总则</a></li>
	  <li><a href="#tgsh1.1.3">3. 显号使用流程</a></li>
      <li><a href="#tgsh1.1.4">4. 显号规则描述</a>
      <ul>
        <li><a href="#tgsh1.1.4.1">4.1 名称解释</a></li>
        <li><a href="#tgsh1.1.4.2">4.2 语音验证码显号规则</a></li>
        <li><a href="#tgsh1.1.4.3">4.3 直拨显号规则</a></li>
        <li><a href="#tgsh1.1.4.4">4.4 回拨显号规则</a></li>
      </ul>
      </li>
    </ul>
  </div>
</div>
<div class="aside_fold" id="aside_fold" style="display:none;"><img src="<%=path%>/doc/images/aside_fold.png" />目录导航</div>
<a href="javascript:void(0);" id="go_top" style="display:none;">&nbsp;</a>
<script type="text/javascript">
$(function(){
$("table tr td:last-child").css("text-align","center");
})
</script>
<!--中间部分middle eof--> 

<!--公共底部bottom bof-->
<%@include file="/doc/doc_foot.jsp" %>
<%@include file="/front/foot.jsp" %>
<!--公共底部bottom eof--> 

<script type="text/javascript">
$(function(){
	$("#h_menu_5").css("color","#05c040");
})
</script>
</body>
</html>
