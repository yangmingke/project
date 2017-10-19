<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>快传音视频开放平台_提供音视频通讯加速,一对一视频通话，视频直播等通讯加速API及SDK</title>
	<meta name="keywords" content="一对一音视频通话,音视频通讯,音视频通讯加速,音视频通话,在线教育,视频通话,视频会议,互联网语音,通讯加速,快传,快传技术," >
	<meta name="description" content="快传音视频通讯平台让开发者轻松接入一对一视频通话,音视频通讯加速,视频直播等能力,快速搭建音视频通讯,视频通话/会议应用,通讯加速,音视频引擎让视频更流畅。" >
	<%@include file="/front/resource1.jsp"%>
</head>
</head>
<body id="b-02">
	<!--公共头部 ft_header bof-->
    <%@include file="/front/head1.jsp" %>

	<!--主体部分 ft_content bof-->
    <div class="ft_content">
    	<div class="ft_banner ft_banner6"></div>
        <div class="item_box box17">
            <div class="item_box_wp">
                <div class="exp_tit">
                    <span>Web直接体验</span>
                    <ul>
                        <li onclick="location.href='/freetrial/VoiceVerificationCode'">身份验证(短信)(语音)</li>
                        <li onclick="location.href='/freetrial/callinout'">双向回拨</li>
                        <li class="current" onclick="location.href='/freetrial/cloudNotify'">语音通知</li>
                    </ul>
                </div>
            </div>
        </div>
    	<div class="item_box box18">
    		<div class="item_box_wp">
                <div class="exp_1">
                	<s:if test="#session.user==null">
                        <h2>登陆后才能体验哦！</h2>
                    </s:if>
                    <s:else>
                    <div class="exp_left">                    
                    <form  method="post" name="phoneForm" id="phone_form">
                        <ul>
                            <li>
                                <label>接听号码</label>
                                    <input type="text" id="phone" placeholder="请输入您的手机号" />
                                    <span class="error" id="phone_error" style="display:none"></span>
                            </li>
                            <li>
                                <label>通知内容</label>
                                <textarea id="content" name="content" placeholder="请输入您要通知的内容"></textarea>
                            	<span class="error" id="content_error" style="display:none"></span>
                            </li>
                            <li class="code">
                                <label>验证码</label>
                                <input type="text" id="expvoicecode" placeholder="请输入验证码" />
                                <img src="<%=path%>/checkcode/picCheckCode" id="phonecheckcodepic" align="middle"/>
                                <a href="javascript:void(0)" onclick="loadPic('phonecheckcodepic')" class="refresh">点击刷新</a>
                                <span class="error" id="phone_code_error" style="display:none"></span>
                            </li>
                            <li class="button">
                                <input type="button" value="提 交" id="btn"/>
                            </li>
                            <input type="hidden" id="movecode"  />
            				<input type="hidden" id="exprCount" />
                        </ul>
                        </form>
                    </div>
                    <div class="exp_right">
                        <div class="intro">
                            <h5>语音通知</h5>
                            <p>通过预置程序将需要传递的消息通过外呼指定号码并播报的形式进行通知。常见应用于需要即时通知的服务，例如校园广播通知，公司企业活动广播、客户关怀通知。拥有相较于短信更直接的通知方式。</p>
                            <p>支持通过管理平台上传预先录制的更复杂的语音内容，或通过平台使用TTS（文本转语音）进行自动转录并播放</p>
                            <b>演示说明</b>
                            <p>1、请先使用平台帐号登录</p>
                            <p>2、请输入接听手机号</p>
                            <p>3、请输入您要通知的内容</p>
                            <p>4、点击「提交」，等待手机来电，并接听</p>
                        </div>
                    </div>
                    </s:else>
                </div>    			
    		</div>
    	</div>

    	<div class="item_box box19">
    		<div class="item_box_wp">
    			<div class="exp_2">
                    <h6>客户端体验</h6>      
                    <p class="step">
                        <span>第一步：注册平台账号</span>
                        <em></em>
                        <span>第二步：下载相应安装包</span>
                        <em></em>
                        <span>第三步：真机安装体验</span>
                    </p>  
                    <div class="exp_display">
                        <ul>
                            <li class="text">
                                <strong>跨平台演示应用</strong>
                                <p class="p_list">
                                    <span class="span1">在线体验语音、直拨、回拨方式通话<br />电话会议、身份校验验证码服务</span>
                                    <span class="span2">体验即时消息、群聊<br />一对一视频</span>
                                </p>
                                <p class="p_link"><a href="<%=path%>/product_service/download?downloadType=i"><i class="ios"></i>iOS 平台 iPA下载</a></p>
                                <p class="p_link"><a href="<%=path%>/product_service/download?downloadType=a"><i class="android"></i>Android 平台 APK下载</a></p>
                                <p class="p_link"><a href="<%=path%>/product_service/download?downloadType=w"><i class="windows"></i>Windows 平台下载</a></p>
                                <p class="tips">注：使用演示应用前需先完成平台帐号注册，部分演示功能需提前在网站管理中心自助配置【测试应用白名单】</p>
                            </li>
                            <li class="img"><img src="<%=path%>/front/images1/img22.png" /></li>
                            <li class="img"><img src="<%=path%>/front/images1/img23.png" /></li>
                        </ul>
                    </div>
                </div>
    		</div>
    	</div>

        <%@include file="/front/foot1_0.jsp" %>
	</div>

	<!--主体部分 ft_content eof-->

	<%@include file="/front/foot1.jsp" %>
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
		  $("#phone_error").text("请输入正确的手机号码");
      	  $("#phone_error").fadeIn();
      	  return false;
		}else if(!verifyMobile(phone)){
		  $("#phone_error").text("请输入正确的手机号码");
	      $("#phone_error").fadeIn();
	      return false;
		}
		$("#phone_error").hide();
		return true;
	}
	function vContent(){
		var content = $("#content").val();
		if(content==""){
		  $("#content_error").text("请输入正确的通知内容");
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
					 $("#phone_code_error").text("验证码有误，请重新输入");
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