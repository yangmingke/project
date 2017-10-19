<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ taglib prefix="s"  uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>管理中心使用指南</title>
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
        <li  class="active">
        	<span>新手指引<i class="parent">&nbsp;</i></span>
        	<dl>
                <dd><a href="<%=path%>/doc/doc_guide4-1.jsp">注册成为开发者</a></dd>
                <dd><a href="<%=path%>/doc/doc_guide5-1.jsp" class="active">管理中心使用指南</a></dd>   
				<dd><a href="<%=path%>/doc/doc_guide6-1.jsp">应用创建流程</a></dd>
                <dd><a href="<%=path%>/doc/doc_guide7-1.jsp">充值流程</a></dd>  
                <dd><a href="<%=path%>/doc/doc_guide3-1.jsp">应用审核规范</a></dd>				
                <dd><a href="<%=path%>/doc/doc_guide8-1.jsp">开发者资质审核规范</a></dd>                   
                <dd><a href="<%=path%>/doc/doc_guide2-1.jsp">开发者协议</a></dd>
        	</dl>
        </li>
        <li >
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
    <div class="doc_txt doc_guide">
      <div class="pathbox"><p><a href="<%=path%>/doc/doc.jsp">帮助文档首页</a> >  <a href="<%=path%>/doc/doc_guide4-1.jsp">新手指引</a> > <span>管理中心使用指南</span></p></div>
      <div class="display_tit">
        <h1><span class="intro">管理中心使用指南</span></h1>
      </div>
      <div class="display_ctn">
        <h3 id="tgg5.1.1">开发者中心</h3>
        <p>开发者中心主要包括：开发者信息、资费配置、认证信息、消息通知和修改密码等。</p>
        <p>
                        开发者信息：主要查看开发者基本信息、账户余额、应用情况，并可进行信息修改、配置余额提醒和对账户进行充值等；<br />
                        资费配置：主要查看套餐资费详情及进行套餐配置或者修改资费套餐；<br />
                        认证信息：个人或者企业开发者选择对应的认证入口进行认证，上传认证要求的相关资料并提交进入审核资格；当认证审核通过后，用户可查看认证的资料或者信息。<br />
        </p>
        <p>• 在“开发者中心”->“开发者信息”一栏中，可以查看开发者的基本信息、账户状况和应用情况等信息。<br />
        <img src="<%=path%>/doc/images/doc_img35.png" width="100%" /></p>
        <p>• 在“开发者信息”页中点击“充值”、“修改基本信息”、“配置余额不足提醒”可以对账户进行充值、修改开发者信息和设置余额提醒。<br />
        <img src="<%=path%>/doc/images/doc_img36.png" width="100%" /></p>
        <p>• 在“开发者中心”->“资费配置”一栏中，可以查看资费套餐的详细和进行资费配置等。<br />
        <img src="<%=path%>/doc/images/doc_img37.png" width="100%" /></p>
        <p>• 在“开发者中心”->“认证信息”一栏中，开发者可以根据具体情况选择作为个人或企业开发者进行资质认证，填写提交相关资料，上传认证照片，完成实名认证（当完成认证后，点击“认证信息”可以查看认证情况和认证资料）。<br />
        <img src="<%=path%>/doc/images/doc_img38.png" width="100%" /></p>
        <h3 id="tgg5.1.2">应用中心</h3>
        <p>应用中心主要包括：应用管理、测试DEMO、短信管理等。</p>
        <p>
                        应用管理：主要为应用列表，可以查看应用信息，编辑、创建、上线及删除应用等；<br />
                        测试DEMO：主要查看测试DEMO的详细信息（开发者账号、REST服务器信息、Client账号密码）、填写和验证测试号码等;<br />
                        短信管理：管理开发者账号下管理的号码，添加管理短信模板。<br />
        </p>
        <p>• 在“应用中心”->“应用管理”一栏中，可以查看应用信息，编辑、创建、上线及删除应用等。<br />
        <img src="<%=path%>/doc/images/doc_img39.png" width="100%" /></p>
        <p>• 在“应用中心”页中点击任一应用的名称可进入“应用详情”页面查看当前应用的状态、修改应该名称和配置应用信息等。<br />
        <img src="<%=path%>/doc/images/doc_img40.png" width="100%" /></p>
        <p>• 在“应用中心”->“测试DEMO”一栏中，可获取Demo演示需要的主账户、应用、子账户等信息。<br />
        <span style="color:#f00; padding-left:8px;">注意：测试Demo的应用无法创建子账户。测试Demo下的子账户不可在其他应用下使用。</span><br />
        <img src="<%=path%>/doc/images/doc_img41.png" width="100%" /></p>
        <p>• 在“应用中心”->“测试DEMO” ->“测试号码”一栏中，开发者可以根据实际情况填写5个号码供测试使用，可以填写固定电话或者手机号。添加的号码需要通过验证，验证后才可以在应用未上线的状态下供应用测试，每次变更号码需要再次验证。<br />
        <img src="<%=path%>/doc/images/doc_img42.png" width="100%" /></p>
        <p>• 验证测试号码，点击”收听语音验证码“，系统将会拨打电话到您提供的号码上，请将收听到的号码填写入2中，确定即可，只能选择已验证过的号码进行测试。<br />
        <img src="<%=path%>/doc/images/doc_img43.png" width="100%" /></p>
        <p>• 在“应用中心”->“短信管理”一栏中，开发者可以管理开发账号下的号码，添加管理短信模板。<br />
        <img src="<%=path%>/doc/images/doc_img44.png" width="100%" /></p>
        <h3 id="tgg5.1.3">账务账单</h3>
        <p>账务账单主要包括：消费账单、月结账单、充值详单等。</p>
        <p>
                        消费账单：可以查询到某个应用45天内的消费详细数据（包括语音、短信、Client账号、语音验证码、即时通讯等）;<br />
                        月结账单：可以查询到每个月的消费详单;<br />
                        充值详单：可以查看账户充值的详细信息。<br />
        </p>
        <p>• 在“账务账单”->“消费账单”一栏中，可选择相应的某一应用及消费时间，查询应用消费的详细数据。<br />
        <img src="<%=path%>/doc/images/doc_img45.png" width="100%" /></p>
        <p>• 在“账务账单”->“月结账单”一栏中，可选择查询对应月份内该账号下应用消费的详细数据。<br />
        <img src="<%=path%>/doc/images/doc_img46.png" width="100%" /></p>
        <p>• 在“账务账单”->“充值详单”一栏中，可以根据日期查询对应时间内操作的充值详情。<br />
        <img src="<%=path%>/doc/images/doc_img47.png" width="100%" /></p>
     </div>
    </div>
  </div>
  <div class="clear"></div>
</div>
<div class="doc_aside" id="doc_aside">
    <h4>目录 [<a href="javascript:void(0);" id="aside_fold_link">隐藏</a>] </h4>
  <div class="aside_ctn">
    <ul>
      <li><a href="#tgg5.1.1">1. 开发者中心</a></li>
      <li><a href="#tgg5.1.2">2. 应用中心</a></li>  
      <li><a href="#tgg5.1.3">3. 账务账单</a></li>  	  
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
