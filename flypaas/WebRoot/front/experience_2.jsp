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
                        <li class="current" onclick="location.href='/freetrial/callinout'">双向回拨</li>
                        <li onclick="location.href='/freetrial/cloudNotify'">语音通知</li>
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
                                <label>主叫号码</label>
                                <span class="label_span">${sessionScope.user.mobile }</span>
                            </li>
                            <li>
                                <label>被叫号码</label>
                                <div class="select_box">
                                    <span class="txt">选择号码</span>
                                    <ul class="select">
                                      <s:iterator value="nbrList" var="l">
						              	  <s:if test="#l.mobile!=user.mobile">
								              <li onclick="getNbrValue('${l.mobile }')">${l.mobile }</li>
								          </s:if>
						              </s:iterator>
                                    </ul>
                                </div>
                                <span class="error" id="phone_error" style="display:none"></span>
                            </li>
                            <input type="hidden" id="paramValue" name="paramValue"/>
                            <input type="hidden" id="phone"/>
                            <li class="code">
                                <label>验证码</label>
                                <input type="text" id="callbackcode"  placeholder="验证码" />
                                <img src="<%=path%>/checkcode/picCheckCode" id="phonecheckcodepic" align="middle"/>
                                <a href="javascript:void(0)" onclick="loadPic('phonecheckcodepic')" class="refresh">点击刷新</a>
                                <span class="error" id="phone_code_error" style="display:none"></span>
                            </li>
                            <li class="button">
                                <input type="button" value="提 交" id="callback"/>
                            </li>
                            <input type="hidden" id="vphonecheckcode"  />
                        </ul>
                     </form>
                    </div>
                    <div class="exp_right">
                        <div class="intro">
                            <h5>双向回拨</h5>
                            <p>通过以平台接口桥接运营商话务的双方进行在线通话，让通话的双方都处在运营商话务服务下的一种方式。具备可桥接性，常见于双方因无宽带网络情况下，并且需同时使用运营商话务服务或者需可定制的桥接的企业客户服务中。</p>
                            <p>注：被叫号码 可选择 官方客服号码 400-097-0020，如您要测试其他号码，需在开发者控制内，将号码添加进【测试号码白名单】后方可在此处体验使用</p>
                            <b>演示说明</b>
                            <p>1、请先使用平台帐号登录</p>
                            <p>2、主叫号码：为平台首先呼通的一方号码</p>
                            <p>3、被叫号码：主叫号码接听后有语音提示，平台同时呼叫被叫号码</p>
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
				  $("#phone_error").text("请选择被叫手机号码");
		      	  $("#phone_error").show();
		      	  return false;
				}else if(!verifyMobile(phone)){
				  $("#phone_error").text("请选择被叫手机号码");
			      $("#phone_error").show();
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