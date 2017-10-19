<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%@ taglib prefix="u"  uri="/flypaas-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="description" content="快传融合通讯语音验证码，通过语音电话呼叫用户终端，实现语音播报内容的方式解决短信验证码安全或无法接收问题，快传支持用户定制个性化语音验证码" />
<title>下载中心_快传融合通讯开放平台</title>
<%@include file="/front/resource.jsp" %>
</head>

<body>

<!--公共头部header bof-->
<%@include file="/front/head.jsp" %>

<!--公共头部header eof--> 

<div class="middle mid_box download_box">
  <div class="banner download_banner">&nbsp;</div>
  <div class="download_tit">
  	<ul>
  		<li class="name">SDK下载</li>
  		<li class="ctn">快传融合通讯开放平台SDK是永久免费的，只需要花费你几分钟的时间就可以部署到你的项目中去</li>
  	</ul>
  </div>  
  <div class="download_tabtit">
  	<ul>
  		<li class="android active" >&nbsp;</li>
  		<li class="ios" >&nbsp;</li>
  		<li class="windows">&nbsp;</li>
  	</ul>
  </div>
  <div class="clear"></div>
  
  
  <div class="download_tabctn">
			<div class="tabctn">
				<em class="android">&nbsp;</em>
				<div class="link android">
					<ul>	
						<s:iterator value="sdkAList" id="a">
						<li><b><s:property value="#a.name"/></b> <a href="<%=path%>/file/view?fileName=<u:des3 value="${a.path}" />" onclick="updateDownloadCount('<s:property value="#a.key" />','<s:property value="#a.version" />')">下载</a><a
							href="javascript:void(0);" class="version_link">版本信息</a></li>
						</s:iterator>
					</ul>
				</div>
				<div class="version_ctn">
					<s:iterator value="sdkAList" id="a">
						<div class="version_list">
							<h2>
								<span><s:property value="#a.name"/> 更新日志</span>
							</h2>
							<s:action name="versionDesc" namespace="/download" executeResult="true" flush="false">
								<s:param name="key" value="#a.key"></s:param>
							</s:action>
						</div>
					</s:iterator>
				</div>
			</div>
			<div class="tabctn" style="display: none;">
				<em class="ios">&nbsp;</em>
				<!--<p class="link"><a href="#"><span>iOS SDK 下载<br /><i>V1.0.1&nbsp;&nbsp;&nbsp;&nbsp;下载次数：6922</i></span></a></p>-->
				<div class="link ios">
					<ul>
						<s:iterator value="sdkIList" id="i">
						<li><b><s:property value="#i.name"/></b> <a href="<%=path%>/file/view?fileName=<u:des3 value="${i.path}" />" onclick="updateDownloadCount('<s:property value="#i.key" />','<s:property value="#i.version" />')">下载</a><a
							href="javascript:void(0);" class="version_link">版本信息</a></li>
						</s:iterator>
					</ul>
				</div>
				<div class="version_ctn">
					<s:iterator value="sdkIList" id="ios">
						<div class="version_list">
							<h2>
								<span><s:property value="#ios.name"/> 更新日志</span>
							</h2>
							<s:action name="versionDesc" namespace="/download" executeResult="true" flush="false">
								<s:param name="key" value="#ios.key"></s:param>
							</s:action>
						</div>
					</s:iterator>
				</div>
			</div>
			<div class="tabctn" style="display: none;">
				<em class="windows">&nbsp;</em>
				<!--<p class="link"><a href="#"><span>Windows SDK 下载<br /><i>V1.0.1&nbsp;&nbsp;&nbsp;&nbsp;下载次数：6922</i></span></a></p>-->
				<div class="link windows">
					<ul>
						<s:iterator value="sdkWList" id="w">
						<li><b><s:property value="#w.name"/></b> <a href="<%=path%>/file/view?fileName=<u:des3 value="${w.path}" />" onclick="updateDownloadCount('<s:property value="#w.key" />','<s:property value="#w.version" />')">下载</a><a
							href="javascript:void(0);" class="version_link">版本信息</a></li>
						</s:iterator>
					</ul>
				</div>
				<div class="version_ctn">
					<s:iterator value="sdkWList" id="win">
						<div class="version_list">
							<h2>
								<span><s:property value="#win.name"/> 更新日志</span>
							</h2>
							<s:action name="versionDesc" namespace="/download" executeResult="true" flush="false">
								<s:param name="key" value="#win.key"></s:param>
							</s:action>
						</div>
					</s:iterator>
				</div>
			</div>
		</div>
		
		
		
		
  <div class="download_tit download_tit1">
  	<ul>
  		<li class="name">DEMO下载</li>
  	</ul>
  </div> 
  <div class="download_demo">
  	<h3>Client Demo:</h3>
  	<p class="link client">
  		<s:iterator value="clientList" id="c">
	  		<a href="<%=path%>/file/view?fileName=<u:des3 value="${c.path}" />" onclick="updateDownloadCount('<s:property value="#c.key" />','<s:property value="#c.version" />')" <s:if test="#c.key==2111||#c.key==2121">class="android"</s:if><s:elseif test="#c.key==2112||#c.key==2122">class="ios"</s:elseif><s:elseif test="#c.key==2113||#c.key==2123">class="windows"</s:elseif>><span><s:property value="#c.name"/> <br /><i>V<s:property value="#c.version"/>
	  		&nbsp;&nbsp;&nbsp;&nbsp;下载次数：<i <s:if test="#c.key==2111||#c.key==2121">id="demo_andriod"</s:if><s:elseif test="#c.key==2112||#c.key==2122">id="demo_ios"</s:elseif><s:else>id="demo_windows"</s:else>><s:property value="#c.count"/></i></i></span></a>
  		</s:iterator>
  	</p>
  	<h3>REST Server Demo:</h3>
  	<p class="link rest">
  		<s:iterator value="restList" id="rest">
	  		<a href="<%=path%>/file/view?fileName=<u:des3 value="${rest.path}" />" onclick="updateDownloadCount('<s:property value="#rest.key" />','<s:property value="#rest.version" />')" <s:if test="#rest.key==2211">class="php"</s:if><s:elseif test="#rest.key==2212">class="java"</s:elseif><s:elseif test="#rest.key==2213">class="python"</s:elseif><s:elseif test="#rest.key==2214">class="cc"</s:elseif>><span><s:property value="#rest.name"/> <br /><i>V<s:property value="#rest.version"/>
	  		&nbsp;&nbsp;&nbsp;&nbsp;下载次数：<i <s:if test="#rest.key==2211">id="rest_php"</s:if><s:elseif test="#rest.key==2212">id="rest_java"</s:elseif><s:elseif test="#rest.key==2213">id="rest_python"</s:elseif><s:else>id="rest_cSharp"</s:else>><s:property value="#rest.count"/></i></i></span></a>
  		</s:iterator>
  	</p>
  </div>
</div>

<script type="text/javascript">
	$(function(){

    //样式处理
    $(".download_demo p.rest a:eq(2),.download_demo p.rest a:eq(5),.download_demo p.rest a:eq(8),.download_demo p.client a:eq(2),.download_demo p.client a:eq(5),.download_demo p.client a:eq(8)").css("margin-right","0px");

		$("#h_menu_6").css("color","#05c040");
		$(".download_tabtit ul li").click(function(){
			var li_index = $(this).index();
			$(this).addClass("active").siblings("li").removeClass("active");
			$(".download_tabctn").find(".tabctn").eq(li_index).show().siblings(".tabctn").hide();
		});
		 //音频视频版本点击隐藏显示
	    $("a.version_link").click(function(){
	      var link_index = $(this).parent("li").index();
	      $(this).parents(".link").siblings(".version_ctn").find(".version_list").eq(link_index).slideToggle("slow").siblings(".version_list").hide("slow");
	    });
	});
	function updateDownloadCount(key,version){
		$.ajax({
			url:"/download/updateDownloadCount",
			type:"post",
			data:"key="+key+"&version="+version,
			dataType: "text",
			success: function (data) {
	        },
	        error: function (msg) {
	        }
		});
	}
</script>
<!--中间部分middle eof--> 

 

<!--公共版权copyright bof-->
<%@include file="/front/foot_sk.jsp"%><%@include file="/front/foot.jsp" %>
<!--公共版权copyright eof-->

</body>
</html>
