<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>注册成功</title>
<%@include file="/front/resource1.jsp"%>
</head>

<body>
<!--公共头部header bof-->
<%@include file="/front/head1.jsp"%>
<!--公共头部header eof--> 
<% Object object = com.flypaas.utils.UcCenterKit.getScript(request);
	if(null != object){
		out.println((String)object);
	}
%>
<!--主体部分content bof-->
<div class="ft_content ft_content_log">
  <div class="ft_content_wp"> 
    
    <!--注册成功绑定手机 bof-->
    <div class="reg_step">
                <h1>绑定手机<span>（完善一下手机资料方便我们更好的为您提供服务）</span></h1>
            </div>
            <div class="reg_box">
              <form action="/user/verifyMobile" method="post" name="phoneForm" id="phone_form">
                <div class="reg_form">
                    <dl class="txt">
                        <dt>手机号码</dt>
                        <dd><input type="text" id="phone" name="user.mobile" onchange="isExistsPhone(this.value)"/><span class="error" id="phone_error" style="display:none"></span></dd>
                    </dl>
                    <dl class="verify">
                      <dt>手机验证</dt>
                      <dd>
                          <input type="button" value="短信验证" class="msg" onclick="smsCode('smscodeinput','voicecodeinput','log')" id="smscodeinput"/>
                          <em>或</em>
                          <input type="button" value="语音验证" class="voice" onclick="voiceCode('voicecodeinput','smscodeinput')" id="voicecodeinput"/>
                          <input type="hidden" name="user.sid" value="${user.sid }" />
                          <input type="hidden" id="vmovecode"  />
                          <input type="hidden" id="movecode"  />
                        </dd>
                    </dl>
                    <dl class="code">
                        <dd><input type="text" placeholder="验证码" id="inputmovecode"/><span id="move_phone_code_error" class="error" style="display:none">正确</span></dd>
                        <dd class="tips">请输入短信验证码</dd>
                    </dl>
                    <dl class="button">
                        <dd><input type="button" value="提 交" id="infoSubt"/></dd>
                    </dl>
                </div>
                </form>
            </div>
  </div>  

</div>
<!--主体部分content eof--> 

<!--公共底部footer bof-->
<%@include file="/front/foot1.jsp"%>
<!--公共底部footer bof-->
<script type="text/javascript">
$(function(){
	 disabl('smscodeinput','voicecodeinput');
	//绑定手机表单验证
	$("#phone_form").submit(function(){
		if(frm.phone()&&frm.phoneCode()){
			return true;
		}else{
			return false;
		}
	});
});
</script>
</body>
</html>
