<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>忘记密码</title>
<%@include file="/front/resource1.jsp"%>
</head>

<body>
	<%@include file="/front/head2.jsp"%>

	<!--主体部分content bof-->
	<div class="ft_content ft_content_reg">
    	<div class="ft_content_wp">
            <div class="reg_step">
                <img src="<%=path%>/front/images1/forget_pwd_step1.png" />
            </div>
            <div class="reg_box forget_pwd_box">
                <div class="reg_form">
                	<form method="post"
						action="<%=path %>/user/sendResetPwd" name="pwdForm" id="forget_pwd">
	                    <dl>
	                        <dt>请输入您的注册邮箱</dt>
	                        <dd>
	                        <input type="text" name="email" id="resetemail" value="" placeholder="请输入您的注册邮箱" />
								<span id="resetemail_error" class="error" style="display:none"></span>
	                        </dd>
	                    </dl>
	                    <dl class="code">
	                        <dd>
	                        <input type="text" placeholder="验证码" id="code"/>
	                        <img src="<%=path %>/checkcode/picCheckCode" id='checkcodepic'/>
	                        <a href="javascript:void(0)" onclick="loadPic('checkcodepic')" class="refresh">点击刷新</a>
	                        <span id="code_error" class="error" style="display:none"></span>
	                        </dd>
	                    </dl>
	                    <dl class="button">
	                        <dd><input type="button" id="resetPwdBtn" value="提 交" /></dd>
	                    </dl>
	                </form>
                </div>
                <p class="tips"><span>如还有其他帐号问题疑问请联系<a href="mailto:support@flypaas.com">support@flypaas.com</a></span></p>
            </div>
        </div>        
	</div>
	<!--主体部分content eof-->
	

	<!--公共底部footer bof-->
	<%-- <%@include file="/front/callus.jsp"%> --%>
	<!--公共底部footer bof-->


<script type="text/javascript">
$(function(){
	$("#resetPwdBtn").click(function(){
		if(resetFrm.mail()){
			resetFrm.mailExits();
		}
	});	
});
var resetFrm = {
		mail:function(){
			var reg_mail = $("#resetemail").val();
			if(reg_mail==""){
				$("#resetemail_error").fadeIn();
		        $("#resetemail_error").text("请输入注册时所填邮箱");
				$("#resetemail").focus();
				return false ;
			}else{
				if(!verifyMail(reg_mail)){
					$("#resetemail_error").fadeIn();
			        $("#resetemail_error").text("邮箱格式不正确");
					$("#resetemail").focus();
					return false ;
				}
			}
			return true;
		},
		compareCode:function(){
			var code = $("#code").val();
			$.ajax({
				url:"<%=path%>/page/user/checkcode.jsp",
				type:"post",
				data:"checkCode="+code,
				dataType: "text",
				success: function (data) {
		          if(data!=1){
		        	  $("#code_error").fadeIn();
		        	  $("#code_error").text("验证码错误");
		        	  loadPic('checkcodepic');
		  			  $("#code").focus();
		  			  return false;
		          }
		          $("#forget_pwd").submit();
		        },
		        error: function (msg) {
		        }
			});
		},
		mailExits:function(){
			var mail = $("#resetemail").val();;
			$.ajax({
				url:"<%=path%>/user/ckMobileAndmailEnable",
						type : "post",
						data : "vemail=" + mail,
						dataType : "text",
						success : function(data) {
							if (data == "0") {
								$("#resetemail_error").fadeIn();
								$("#resetemail_error").text("该注册邮箱不存在");
								$("#resetemail").focus();
							} else {
								$("#resetemail_error").hide();
								resetFrm.compareCode();
								
							}
						},
						error : function(msg) {
						}
				});
		}
		
}

	</script>
</body>
</html>
