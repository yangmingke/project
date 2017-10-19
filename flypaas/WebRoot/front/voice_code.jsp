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
<title>短信/语音验证码_手机验证码体验中心_快传融合通讯开放平台</title>
<script type="text/javascript" src="<%=path%>/js/jquery-1.8.2.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/form.css" />
<link rel='stylesheet' type='text/css' href='<%=path%>/css/style.css' />
<link rel='stylesheet' type='text/css' href='<%=path%>/front/css/base.css' />
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
  		<h1><span>语音验证码</span></h1>
  	</div>
  	<div class="display_ctn">
  		<dl>
  			<dt><img src="<%=path%>/front/images/net_voice.png" /></dt>
  			<dd><p>语音验证码是语音播报验证码的验证方式，通过语音电话直接呼叫用户手机或固定电话，用户接听电话后将会听到语音播报验证码。语音验证码到达率高、安全可靠、并且受政策影响低，解决了短信验证码的到达率及政策性问题。常用于网站、移动客户端、银行金融等用户身份验证，以及支付确认等安全性要求更高的即时服务。</p><p><a href="http://docs.flypaas.com/doku.php?id=语音验证码">查看文档</a></p></dd>
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
              <label for="phone">手机号码</label>
              <input type="text" id="phone" name="user.mobile" placeholder="请输入您的手机号" /><span class="error" id="phone_error" style="display:none"></span></p>
			<p class="code"><label for="verification_code">图形验证码</label><input type="text" id="expvoicecode" placeholder="请输入验证码" /><img src="<%=path%>/checkcode/picCheckCode" id="phonecheckcodepic" align="middle"/><a href="javascript:void(0)" onclick="loadPic('phonecheckcodepic')" >看不清换一张</a><span class="error" id="phone_code_error" style="display:none"></span></p>
			<p class="verify"><input type="button" value="短信验证码" onclick="smsCodeExt()" id="smscodeinput" class="msg_code" /><em>或</em><input type="button" onclick="voiceCodeExt()" id="voicecodeinput" value="语音验证码" class="caller_code" /></p> 
            <p class="msg_code_txt"><input type="text" id="inputmovecode" placeholder="请输入您获取到的验证码"/><span class="error" id="move_phone_code_error" style="display:none"></span></p>
            <p class="btn"><input type="button" id="btn" value="提交" class="submit"/></p>
            <input type="hidden" name="user.sid" value="${user.sid }" />
            <input type="hidden" id="vphonecheckcode"  />
            <input type="hidden" id="vmovecode"  />
            <input type="hidden" id="movecode"  />
            <input type="hidden" id="vp" />
            <input type="hidden" id="exprCount" />
      </form>
	  <div class="exp_desc">
	    <p><b>演示说明：</b></p>
		<p>1、输入您的手机号</p>
		<p>2、选择您要体验的方式：短信验证码或者语音验证码</p>
		<p>3、当手机接收到短信或者来电，您将获得验证码</p>
		<p>4、将获得的验证码输入，并点击提交按钮，系统将会自动判断所输入的验证码是否正确</p>
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
<%@include file="/front/foot_sk.jsp"%><%@include file="/front/foot.jsp" %>
<!--公共版权copyright eof-->
<script type="text/javascript">
$(function(){
	$("#h_menu_2").css("color","#05c040");
	$("#phone").bind("blur",function(){
		exprCount(this.value);
	});
	$("#btn").click(function(){
		var inputmovecode = $("#inputmovecode").val();
		if(!vPhone()){
	      	 return;
		}
		if(!picCk()){
			return; 
		}
		if(inputmovecode==""){
			$("#move_phone_code_error").html("请输入验证码");
			$("#move_phone_code_error").show();
			return;
		}else{
			$("#move_phone_code_error").hide();
		}
		compareMoveCodeExt(inputmovecode);
	});
});
function tips(){
	$("#voicecodeinput").val("语音验证码");
	$("#smscodeinput").val("短信验证码");
}
function smsOptExt(phone){
	var count = $("#exprCount").val();
	if(count!=2){
		$("#move_phone_code_error").text("您今天的体验次数已经用完了，请明天再来体验");
		tips();
		return;
	}
	disablExt();
	smsCodeAjaxExt(phone);
}
function voiceCodeExt(){
		var phone = $("#phone").val();
		if(!vPhone()){
			return;
		}
		if(picCk()){
			var code = $("#expvoicecode").val();
			toCkPic(code,"voiceOptExt("+phone+")");
		}
	}
function voiceOptExt(phone){
	var count = $("#exprCount").val();
	if(count!=2){
		$("#move_phone_code_error").text("您今天的体验次数已经用完了，请明天再来体验");
		tips();
		return;
	}
	disablExt();
	voiceCodeAjaxExt(phone);
}
function smsCodeExt(){
		var phone = $("#phone").val();
		if(!vPhone()){
			return;
		}
		if(picCk()){
			var code = $("#expvoicecode").val();
			toCkPic(code,"smsOptExt("+phone+")");
		}
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
	function voiceCodeAjaxExt(phone){
		$("#voicecodeinput").attr("disabled", true);
		$.ajax({
			url:"<%=path%>/checkcode/voiceExtCode",
			type:"post",
			data:"phone="+phone,
			dataType: "text",
			success: function (data) {
				if(data=="fail"){
					$("#move_phone_code_error").text("您今天的体验次数已经用完了，请明天再来体验");
					$("#voicecodeinput").val("语音验证码");
					$("#smscodeinput").val("短信验证码");
					$("#move_phone_code_error").show();
				}else{
					$("#move_phone_code_error").text("");
					$("#move_phone_code_error").hide();
		         	$("#movecode").val(data);
		         	loadPic('phonecheckcodepic');
		         	time1("voicecodeinput","smscodeinput","语音验证码",phone);				
		        }
	        },
	        error: function (msg) {
	        	 $("#movecode").val("");
	        }
		});
	}
	function smsCodeAjaxExt(phone){
		$("#smscodeinput").attr("disabled", true);
		$.ajax({
			url:"<%=path%>/checkcode/smsExtCode",
			type:"post",
			data:"phone="+phone,
			dataType: "text",
			success: function (data) {
				if(data=="fail"){
					$("#move_phone_code_error").text("您今天的体验次数已经用完了，请明天再来体验");
					$("#voicecodeinput").val("语音验证码");
					$("#smscodeinput").val("短信验证码");
					$("#move_phone_code_error").show();
				}else{
					$("#move_phone_code_error").text("");
					$("#move_phone_code_error").hide();
		            $("#movecode").val(data);
		            loadPic('phonecheckcodepic');
		            time1("smscodeinput","voicecodeinput","短信验证码",phone);
				}
	        },
	        error: function (msg) {
	        	 $("#movecode").val("");
	        }
		});
	}
	function compareMoveCodeExt(value){
		var phone = $("#phone").val();
		var movecode = $("#movecode").val();
		value = hex_md5(phone+value);
		if(movecode!=value){
			popupBox("提示","验证码不对哦",null,2);
		}else{
			popupBox("提示","验证码正确",null,2);
		}
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
	function disablExt(){
		$("#smscodeinput").attr("disabled", true);
		$("#voicecodeinput").attr("disabled", true);
	}
	function exprCount(phone){
		$.ajax({
			url:"<%=path%>/checkcode/exprCount",
			type:"post",
			data:"phone="+phone+"&expType="+1,
			dataType: "text",
			success: function (data) {
				if(data=="fail"){
					$("#exprCount").val("1");
					$("#move_phone_code_error").text("您今天的体验次数已经用完了，请明天再来体验");
					$("#move_phone_code_error").show();
				}else{
					$("#exprCount").val("2");
					$("#move_phone_code_error").hide();
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
