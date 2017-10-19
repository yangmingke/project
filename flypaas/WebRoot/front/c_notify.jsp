<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="description" content="快传融合通讯语音验证码，通过语音电话呼叫用户终端，实现语音播报内容的方式解决短信验证码安全或无法接收问题，快传支持用户定制个性化语音验证码" />
<title>语音通知_手机验证码体验中心_快传融合通讯开放平台</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/form.css" />
<%@include file="/front/resource.jsp" %>
<script type="text/javascript" src="<%=path%>/js/md5.js"></script>
<script type="text/javascript" src="<%=path %>/js/form.js"></script>
<script type="text/javascript" src="<%=path %>/js/function.js"></script>
</head>

<body>
<!--公共头部header bof-->
<%@include file="/front/head.jsp" %>

<!--公共头部header eof--> 

<!--中间部分middle bof-->
<div class="middle mid_box">
  <div class="banner vcode_banner">&nbsp;</div>
  <div class="display_box">
  	<div class="display_tit">
  		<h1><span>语音通知</span></h1>
  	</div>
  	<div class="display_ctn">
  		<dl>
  			<dt><img src="<%=path%>/front/images/net_voice.png" /></dt>
  			<dd><p>通过预置程序将需要传递的消息通过外呼指定号码并播报的形式进行通知。常见应用于需要即时通知的服务，例如校园广播通知，公司企业活动广播、客户关怀通知。拥有相较于短信更直接的通知方。支持通过管理平台上传预先录制的更复杂的语音内容，或通过平台使用TTS（文本转语音）进行自动转录并播放。</p><p><a href="http://docs.flypaas.com/doku.php?id=语音通知">查看文档</a></p></dd>
  		</dl>
  	</div>
  </div>
  <div class="clear"></div>
  
  <div class="display_box online_exp">
    <div class="display_tit">
      <h1><span class="doc">在线体验</span></h1>
    </div>
    <div class="display_ctn">
    <s:if test="#session.user==null">
  		<h2 style="background:#f2f2f2; color:#2f2f2f; font-size:28px; padding:10px; text-align:center; border-radius:5px; margin:0px 40px;">登陆后才能体验哦！</h2>
  	</s:if>
  	<s:else>
	  <form  method="post" name="phoneForm" id="phone_form">
            <p class="p_phone">
              <label for="phone">接听号码</label>
              <input type="text" id="phone" placeholder="请输入您的手机号" /><span class="error" id="phone_error" style="display:none"></span></p>
            <p class="p_phone">
            <label for="phone">通知内容</label>
            <input type="text" id="content" name="content" placeholder="请输入您要通知的内容" /><span class="error" id="content_error" style="display:none"></span></p>
			<p class="code"><label for="verification_code">图形验证码</label><input type="text" id="expvoicecode" placeholder="请输入验证码" /><img src="<%=path%>/checkcode/picCheckCode" id="phonecheckcodepic" align="middle"/><a href="javascript:void(0)" onclick="loadPic('phonecheckcodepic')" >看不清换一张</a><span class="error" id="phone_code_error" style="display:none"></span></p>
            <p class="btn"><input type="button" id="btn" value="提交" class="submit"/></p>
            <input type="hidden" id="movecode"  />
            <input type="hidden" id="exprCount" />
      </form>
	  <div class="exp_desc">
	    <p><b>演示说明：</b></p>
		<p>1、输入接听手机号</p>
		<p>2、请输入您要通知的内容</p>
		<p>3、点击「提交」等待手机来电</p>
	  </div>
	</s:else>
	</div>
  </div>  

  <div class="display_wrapper">
  <div class="display_box unit_advantage">
  	<div class="display_tit">
  		<h1><span class="unit_adv">组件优势</span></h1>
  	</div>
  	<div class="display_ctn">
  		<dl>
  			<dt><img src="<%=path%>/front/images/unit_adv7.png" /></dt>
  			<dd><b>账户安全</b></dd>
  			<dd>用户绑定了手机或固话，只可用绑定过的号码拨打指定电话并获取随机密码，才可以进行登录或转账等一系列的操作。</dd>
  		</dl>
  		<dl>
  			<dt><img src="<%=path%>/front/images/unit_adv5.png" /></dt>
  			<dd><b>用户信息更真实</b></dd>
  			<dd>只有通过验证的用户才可以注册，这样保证了用户的联系信息资料的100%的准确性。</dd>
  		</dl>
  		<dl>
  			<dt><img src="<%=path%>/front/images/unit_adv6.png" /></dt>
  			<dd><b>语音导航个性化</b></dd>
  			<dd>可以根据公司、网站、电商、游戏等行业性质、领域的不同，定制个性化语音提示及验证码类型</dd>
  		</dl>  		
  	</div>
  </div>  
  <div class="display_box documentation">
    <div class="display_tit">
      <h1><span class="doc">文档说明</span></h1>
    </div>
    <div class="display_ctn">
      <dl class="intro">
        <dd><b><a href="http://docs.flypaas.com/doku.php?id=新手指引" target="_blank">接入指引</a></b></dd>
        <dd>从创建应用到应用通过审核的流程介绍</dd>
      </dl>
      <dl class="api">
        <dd><b><a href="http://docs.flypaas.com/doku.php?id=rest_api介绍" target="_blank">API文档</a></b></dd>
        <dd>API接口描述及说明文档</dd>
      </dl>
      <dl class="audit">
        <dd><b><a href="http://docs.flypaas.com/doku.php?id=应用审核规范" target="_blank">审核规范</a></b></dd>
        <dd>快传融合通讯开放平台应用审核规范</dd>
      </dl>
      <dl class="faq">
        <dd><b><a href="http://docs.flypaas.com/doku.php?id=faqs" target="_blank">常见问题</a></b></dd>
        <dd>为开发者提供产品、技术方面常见问题解答</dd>
      </dl>     
    </div>
  </div> 
  </div> 
  <div class="clear"></div>
</div>
<!--中间部分middle eof--> 

 

<!--公共版权copyright bof-->
<%@include file="/front/foot_sk.jsp"%>
<%@include file="/front/foot.jsp" %>
<!--公共版权copyright eof-->
<script type="text/javascript">
$(function(){
	$("#h_menu_2").css("color","#05c040");
	$("#phone").bind("blur",function(){
		exprCount(this.value);
	});
	$("#content").bind("blur",function(){
		var vl = $(this).val();
		if(vl.length>50){
			$(this).val(vl.substring(0,50));
		}
	});
	$("#btn").click(function(){
		var expr = $("#exprCount").val();
		if(expr==1){
			$("#phone_code_error").text("您今天的体验次数已经用完了，请明天再来体验");
			return;
		}else{
			$("#phone_code_error").hide();
		}
		if(!vPhone()){
	      	 return;
		}
		if(!vContent()){
			return;
		}
		if(!picCk()){
			return; 
		}
		var expvoicecode = $("#expvoicecode").val();
		if(expvoicecode==""){
			$("#phone_code_error").html("请输入验证码");
			$("#phone_code_error").show();
			return;
		}else{
			$("#phone_code_error").hide();
		}
		toCkPic(expvoicecode,"notifyOpt()");
	});
});

	function notifyOpt(){
		var expr = $("#exprCount").val();
		if(expr==1){
			$("#phone_code_error").text("您今天的体验次数已经用完了，请明天再来体验");
			return;
		}else{
			$("#phone_code_error").hide();
		}
		var phone = $("#phone").val();
		var content = $("#content").val();
		$.ajax({
			url:"<%=path%>/notify/cloudNotify",
			type:"post",
			data:"phone="+phone+"&content="+content,
			dataType: "text",
			success: function (data) {
				popupBox("提示","通知成功",null,2);
				//刷新验证码
				loadPic('phonecheckcodepic');
				
	        },
	        error: function (msg) {
	        	popupBox("提示","通知失败",null,2);
	        	//刷新验证码
				loadPic('phonecheckcodepic');
	        }
		});
	}
	
	function vPhone(){
		var phone = $("#phone").val();
		if(phone==""){
		  $("#phone_error").text("请输入正确的手机号码。");
      	  $("#phone_error").fadeIn();
      	  return false;
		}else if(!verifyMobile(phone)){
		  $("#phone_error").text("请输入正确的手机号码。");
	      $("#phone_error").fadeIn();
	      return false;
		}
		$("#phone_error").hide();
		return true;
	}
	function vContent(){
		var content = $("#content").val();
		if(content==""){
		  $("#content_error").text("请输入正确的通知内容。");
      	  $("#content_error").fadeIn();
      	  return false;
		}
		$("#content_error").hide();
		return true;
	}
	
	function picCk(){
		var code = $("#expvoicecode").val();
		if(code==""){
		  $("#phone_code_error").text("请输入验证码");
       	  $("#phone_code_error").fadeIn();
       	  return false;
		}else{
			$("#phone_code_error").fadeOut();
		}
		return true;
	}
	function toCkPic(code,obj){
		$.ajax({
			url:"<%=path%>/page/user/checkcode.jsp",
			type:"post",
			data:"checkCode="+code,
			dataType: "text",
			success: function (data) {
				if(data!=2){
					eval(obj);
				}else{
					 $("#phone_code_error").text("验证码有误，请重新输入。");
	        	     $("#phone_code_error").fadeIn();
				}
	        },
	        error: function (msg) {
	        }
		});
	}
	function exprCount(phone){
		$.ajax({
			url:"<%=path%>/checkcode/exprCount",
			type:"post",
			data:"phone="+phone+"&expType="+2,
			dataType: "text",
			success: function (data) {
				if(data=="fail"){
					$("#exprCount").val("1");
					$("#phone_code_error").text("您今天的体验次数已经用完了，请明天再来体验");
					$("#phone_code_error").show();
				}else{
					$("#exprCount").val("2");
					$("#phone_code_error").hide();
				}
				
	        },
	        error: function (msg) {
	        	$("#exprCount").val("1");
	        }
		});
	}
</script>
</body>
</html>
