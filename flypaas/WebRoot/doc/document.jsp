<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ taglib prefix="s"  uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>文档中心</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/doc/css/doc.css" /><%@include file="/front/resource.jsp"%><script type="text/javascript" src="<%=path%>/doc/js/doc.js"></script>
</head>

<body id="5">
<!--公共头部header bof-->
<%@include file="/front/head.jsp" %>
<!--公共头部header eof--> 

<!--中间部分middle bof-->
<div class="middle mid_box">
  <div class="banner doc_banner">
  	<div class="banner_wrapper"><img src="<%=path%>/doc/images/doc_banner.png" usemap="#doc_map" /><map id="doc_map" name="doc_map"><area alt="" href="<%=path%>/doc/doc.jsp" target="_blank" coords="0,258,172,321" shape="rect"><area alt="" href="<%=path%>/product_service/download" target="_blank" coords="191,258,364,321" shape="rect"></map></div>
  </div>
  <div class="display_box">
  	<div class="display_tit">
  		<h1><span class="doc">文档索引</span></h1>
  	</div>
  	<div class="display_ctn document_index">
  		<div class="index_ctn">
  			<h3>新手指引</h3>
  			<ul>
  				<li><a href="<%=path%>/doc/doc_guide4-1.jsp">注册成为开发者</a></li>
  				<li><a href="<%=path%>/doc/doc_guide5-1.jsp">管理使用指南</a></li>
  				<li><a href="<%=path%>/doc/doc_guide6-1.jsp">应用创建流程</a></li>
  				<li><a href="<%=path%>/doc/doc_guide7-1.jsp">充值流程</a></li>
  				<li><a href="<%=path%>/doc/doc_guide3-1.jsp">应用审核规范</a></li>
  				<li><a href="<%=path%>/doc/doc_guide8-1.jsp">开发者资质审核规范</a></li>
  				<li><a href="<%=path%>/doc/doc_guide2-1.jsp">开发者协议</a></li>
  			</ul>
  		</div>
  		<div class="index_ctn">
  			<h3>REST API</h3>
  			<ul>
  				<li><a href="<%=path%>/doc/doc_rest1-1.jsp">REST 介绍</a></li>
  				<li><a href="<%=path%>/doc/doc_rest2-1.jsp">开发者账号信息查询</a></li>
  				<li><a href="<%=path%>/doc/doc_rest2-2.jsp">申请Client账号</a></li>
  				<li><a href="<%=path%>/doc/doc_rest2-3.jsp">释放Client账号</a></li>
  				<li><a href="<%=path%>/doc/doc_rest2-4.jsp">获取Client账号</a></li>
  				<li><a href="<%=path%>/doc/doc_rest2-5.jsp">Client信息查询(Client账号)</a></li>
				<li><a href="<%=path%>/doc/doc_rest2-9.jsp">Client信息查询(手机号码)</a></li>
  				<li><a href="<%=path%>/doc/doc_rest2-6.jsp">应用话单下载</a></li>
  				<!--<li><a href="<%=path%>/doc/doc_rest2-7.jsp">Client话单下载</a></li>-->
  				<li><a href="<%=path%>/doc/doc_rest2-8.jsp">Client账号充值</a></li>
  				<li><a href="<%=path%>/doc/doc_rest3-1.jsp">双向回拨</a></li>
  				<li><a href="<%=path%>/doc/doc_rest3-2.jsp">语音验证码</a></li>
          <li><a href="<%=path%>/doc/doc_rest3-3.jsp">语音通知</a></li>
  				<li><a href="<%=path%>/doc/doc_rest4-1.jsp">短信验证码(模板短信)</a></li>
  			</ul>
  		</div>
  		<div class="index_ctn">
  			<h3>Android SDK</h3>
  			<ul>
  				<li><a href="<%=path%>/doc/doc_android6-1.jsp">Android开发指南</a></li>
  				<li><a href="<%=path%>/doc/doc_android1-1.jsp">Android SDK介绍</a></li>
  				<li><a href="<%=path%>/doc/doc_android2-1.jsp">初始化及配置接口</a></li>
  				<li><a href="<%=path%>/doc/doc_android5-1.jsp">注意事项</a></li>
  				<li><a href="<%=path%>/doc/doc_android3-1.jsp">VoIP通话控制接口</a></li>
  				<li><a href="<%=path%>/doc/doc_android3-2.jsp">VoIP状态回调接口</a></li>
  				<li><a href="<%=path%>/doc/doc_android4-1.jsp">IM消息接口</a></li>
  				<li><a href="<%=path%>/doc/doc_android4-2.jsp">IM消息回调接口</a></li>
				<li><a href="<%=path%>/doc/doc_android7-1.jsp">视频通话控制接口</a></li>
				<li><a href="<%=path%>/doc/doc_android8-1.jsp">用户在线查询能力接口</a></li>
        		<li><a href="<%=path%>/doc/doc_android9-1.jsp">智能验证接口</a></li>
  			</ul>
  		</div>
  		<div class="index_ctn">
  			<h3>iOS SDK</h3>
  			<ul>
  				<li><a href="<%=path%>/doc/doc_ios5-1.jsp">iOS开发指南</a></li>
  				<li><a href="<%=path%>/doc/doc_ios1-1.jsp">iOS SDK介绍</a></li>
  				<li><a href="<%=path%>/doc/doc_ios2-1.jsp">初始化及配置</a></li>
  				<li><a href="<%=path%>/doc/doc_ios2-2.jsp">初始化及配置代理接口</a></li>
  				<li><a href="<%=path%>/doc/doc_ios3-1.jsp">VoIP能力接口</a></li>
  				<li><a href="<%=path%>/doc/doc_ios3-2.jsp">VoIP能力代理接口</a></li>
  				<li><a href="<%=path%>/doc/doc_ios4-1.jsp">IM能力接口</a></li>
  				<li><a href="<%=path%>/doc/doc_ios4-2.jsp">IM能力代理接口</a></li>
				<li><a href="<%=path%>/doc/doc_ios6-1.jsp">视频通话能力接口</a></li>
				<li><a href="<%=path%>/doc/doc_ios7-1.jsp">用户在线状态能力接口</a></li>
				<li><a href="<%=path%>/doc/doc_ios8-1.jsp">用户在线状态能力代理接口</a></li>
                <li><a href="<%=path%>/doc/doc_ios9-1.jsp">智能验证接口</a></li>
  			</ul>
  		</div>
  		<div class="index_ctn">
  			<h3>Windows SDK</h3>
  			<ul>
  				<li><a href="<%=path%>/doc/doc_windows5-1.jsp">Windows开发指南</a></li>
  				<li><a href="<%=path%>/doc/doc_windows1-1.jsp">Windows SDK介绍</a></li>
  				<li><a href="<%=path%>/doc/doc_windows2-1.jsp">初始化及配置</a></li>
  				<li><a href="<%=path%>/doc/doc_windows2-2.jsp">初始化及配置回调函数</a></li>
  				<li><a href="<%=path%>/doc/doc_windows3-1.jsp">VoIP能力接口</a></li>
  				<li><a href="<%=path%>/doc/doc_windows3-2.jsp">VoIP能力回调函数</a></li>
  				<li><a href="<%=path%>/doc/doc_windows4-1.jsp">IM能力接口</a></li>
  				<li><a href="<%=path%>/doc/doc_windows4-2.jsp">IM能力回调函数</a></li>
				<li><a href="<%=path%>/doc/doc_windows6-1.jsp">视频能力接口</a></li>
  			</ul>
  		</div>
  		<div class="index_ctn">
  			<h3>应用服务器接口</h3>
  			<ul>
  				<li><a href="<%=path%>/doc/doc_server6-1.jsp">呼叫鉴权流程</a></li>
  				<li><a href="<%=path%>/doc/doc_server3-1.jsp">呼叫鉴权请求接口</a></li>
  				<li><a href="<%=path%>/doc/doc_server4-1.jsp">呼叫建立通知接口</a></li>
  				<li><a href="<%=path%>/doc/doc_server5-1.jsp">呼叫挂机计费接口</a></li>
          <li><a href="<%=path%>/doc/doc_server7-1.jsp">语音外呼状态通知接口</a></li>
  			</ul>
  		</div>
  		<div class="index_ctn">
  			<h3>DEMO</h3>
  			<ul>
  				<li><a href="<%=path%>/product_service/download">DEMO下载</a></li>
  			</ul>
  		</div>
  		<div class="index_ctn">
  			<h3>错误代码</h3>
  			<ul>
  				<li><a href="<%=path%>/doc/doc_errorcode1-1.jsp">Android错误码</a></li>
  				<li><a href="<%=path%>/doc/doc_errorcode2-1.jsp">iOS错误码</a></li>
  				<li><a href="<%=path%>/doc/doc_errorcode3-1.jsp">Windows错误码</a></li>
  				<li><a href="<%=path%>/doc/doc_errorcode4-1.jsp">REST错误码</a></li>
  			</ul>
  		</div>
  		<div class="index_ctn">
  			<h3>常见问题</h3>
  			<ul>
  				<li><a href="<%=path%>/doc/doc_faq1-1.jsp">常见问题</a></li>
  			</ul>
  		</div>
		<div class="index_ctn">
  			<h3>开发者引导</h3>
  			<ul style="margin-bottom:0px;">
  				<li><a href="<%=path%>/doc/doc_dguide1-1.jsp">开发者引导</a></li>
  			</ul>
  		</div>
  	</div>
  </div>
</div>
<!--中间部分middle eof--> 

<!--公共底部bottom bof-->

<!--公共底部bottom eof--> 

<!--公共版权copyright bof-->
<%@include file="/front/foot.jsp" %>
<!--公共版权copyright eof-->

</body>
</html>
