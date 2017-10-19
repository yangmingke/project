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
<title>双向回拨体验中心_快传融合通讯开放平台</title>
<%@include file="/front/resource.jsp" %>
</head>

<body>
<!--公共头部header bof-->
<%@include file="/front/head.jsp" %>

<!--公共头部header eof--> 

<!--中间部分middle bof-->
<div class="middle mid_box">
  <div class="banner app_banner">&nbsp;</div>
  <div class="display_box">
  	<div class="display_tit">
  		<h1><span>双向回拨</span></h1>
  	</div>
  	<div class="display_ctn">
  		<dl>
  			<dt><img src="<%=path%>/front/images/callback.png" /></dt>
  			<dd><p>双向回拨是通过PC、WEB或移动客户端经IP网络发起通话请求，由云平台分别向双方拨打落地电话，并由云平台桥接实现通话的方式。在国内或国际漫游的情况下，可以通过双向回拨的通话方式，变拨打电话为接听电话，节省漫游费用。并且通话不受网速影响，通话效果和传统电话一样，不耗费流量。</p><p><a href="http://docs.flypaas.com/doku.php?id=双向回拨">查看文档</a></p></dd>
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
            <p><label>主叫</label><span>${sessionScope.user.mobile }</span></p>
            <div class="select_box">
	          <label>被叫</label>
	          <div class="select"><span>被叫号码</span>
	            <ul style="display:none;">
	              <s:iterator value="nbrList" var="l">
	              	  <s:if test="#l.mobile!=user.mobile">
			              <li onclick="getNbrValue('${l.mobile }')">${l.mobile }</li>
			          </s:if>
	              </s:iterator>
	            </ul>
	          </div>
	         <input type="hidden" id="paramValue" name="paramValue"/>
	        </div>
            <input type="hidden" id="phone"/>
			<p class="code"><label for="verification_code">验证码</label><input type="text" id="callbackcode"  placeholder="请输入验证码" /><img src="<%=path%>/checkcode/picCheckCode" id="phonecheckcodepic" align="middle"/><a href="javascript:void(0)" onclick="loadPic('phonecheckcodepic')" >看不清换一张</a><span class="error" id="phone_code_error" style="display:none"></span></p>
            <p class="btn"><input type="button" value="呼叫" id="callback" class="submit"/></p>
            <input type="hidden" id="vphonecheckcode"  />
      </form>
	  <div class="exp_desc">
	    <p><b>演示说明：</b></p>
		<p>1、您需要在开发者控制台---「测试Demo」内添加「测试号码白名单」才允许进行体验回拨功能。</p>
		<p>2、为保障安全，演示回拨功能被叫号码受限制，同时语音通话时长受限制（正式使用时无此限制）</p>
		<p>3、选择号码后，点击呼叫按钮后，您会接到呼叫来电，接听来电即可体验功能</p>
	  </div>
	 </s:else>
	</div>
  </div> 
  <div class="display_box documentation">
    <div class="display_tit">
      <h1><span class="doc">文档说明</span></h1>
    </div>
    <div class="display_ctn">
      <dl class="intro">
        <dt><img src="<%=path%>/front/images/doc1.png" /></dt>
        <dd><b><a href="http://docs.flypaas.com/doku.php?id=新手指引" target="_blank">接入指引</a></b></dd>
        <dd>从创建应用到应用通过审核的流程介绍</dd>
      </dl>
      <dl class="api">
        <dt><img src="<%=path%>/front/images/doc2.png" /></dt>
        <dd><b><a href="http://docs.flypaas.com/doku.php?id=rest_api介绍" target="_blank">API文档</a></b></dd>
        <dd>API接口描述及说明文档</dd>
      </dl>
      <dl class="audit">
        <dt><img src="<%=path%>/front/images/doc3.png" /></dt> 
        <dd><b><a href="http://docs.flypaas.com/doku.php?id=应用审核规范" target="_blank">审核规范</a></b></dd>
        <dd>快传融合通讯开放平台应用审核规范</dd>
      </dl>
      <dl class="faq">
        <dt><img src="<%=path%>/front/images/doc4.png" /></dt>
        <dd><b><a href="http://docs.flypaas.com/doku.php?id=faqs" target="_blank">常见问题</a></b></dd>
        <dd>为开发者提供产品、技术方面常见问题解答</dd>
      </dl>     
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
	$("#phone").val("");
	$("#h_menu_2").css("color","#05c040");
	
	$("#callback").click(function(){
		if(!vPhone()){
			return;
		}
		if(picCk()){
			var code = $("#callbackcode").val();
			toCkPic(code);
		}
	});
});
function getNbrValue(value){
	$("#phone").val(value);
}
function vPhone(){
		var phone = $("#phone").val();
		if(phone==""){
		  $("#phone_code_error").text("请选择被叫手机号码。");
      	  $("#phone_code_error").show();
      	  return false;
		}else if(!verifyMobile(phone)){
		  $("#phone_code_error").text("请选择被叫手机号码。");
	      $("#phone_code_error").show();
	      return false;
		}
		$("#phone_error").hide();
		return true;
	}
function picCk(){
	var code = $("#callbackcode").val();
	if(code==""){
	  $("#phone_code_error").text("请输入验证码");
   	  $("#phone_code_error").show();
   	  return false;
	}else{
		$("#phone_code_error").hide();
	}
	return true;
}
function toCkPic(code){
	$.ajax({
		url:"<%=path%>/page/user/checkcode.jsp",
		type:"post",
		data:"checkCode="+code,
		dataType: "text",
		success: function (data) {
			if(data!=2){
				callBack();
				$("#callback").val("呼叫中...");
			}else{
				 $("#phone_code_error").text("请输入验证码");
			   	 $("#phone_code_error").show();
			}
        },
        error: function (msg) {
        }
	});
}

function callBack(phone){
	var phone = $("#phone").val();
	$.ajax({
		url:"<%=path%>/freetrial/callBack",
		type:"post",
		data:"phone="+phone,
		dataType: "text",
		success: function (data) {
			$("#callback").val("呼叫");
			popupBox("提示","已接通",null,2);
			loadPic('phonecheckcodepic');
        },
        error: function (msg) {
        	
        }
	});
}
</script>
</body>
</html>
