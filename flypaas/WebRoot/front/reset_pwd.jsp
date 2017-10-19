<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%@ taglib prefix="u"  uri="/flypaas-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>重置密码</title>
<%@include file="/front/resource1.jsp"%>
</head>

<body>
	<%@include file="/front/head1.jsp"%>


	<!--主体部分content bof-->
	
	<div class="ft_content ft_content_reg">
    	<div class="ft_content_wp">
            <div class="reg_step">
                <img src="<%=path%>/front/images1/forget_pwd_step3.png" />
            </div>
            <div class="reg_box forget_pwd_box">
                <div class="reg_form">
                    <dl class="txt">
                        <dt>您的邮箱</dt>
                        <dd><s:property value="user.email" /></dd>
                    </dl>
                    <form  action="<%=path %>/user/userResetPwd" method="post"
						 name="pwdForm" id="reset_pwd">
	                    <dl>
	                        <dt><i class="mark"></i>密码</dt>
	                        <dd><input type="password" value="" name="user.password"
								id="resetPwd" placeholder="密码" /><span id="resetPwd_error" style="display:none" class="error" id="error"></span></dd>
	                        <dd class="tips">允许字母、数字，最少8个字符</dd>
	                    </dl>
	                    <dl>
	                        <dt><i class="mark"></i>确认密码</dt>
	                        <dd><input type="password" value="" id="resetPwd_confirm"
								placeholder="确认密码" /><span id="resetPwd_confirm_error" style="display:none" class="error" id="error"></span></dd>
	                    </dl>
	                    <dl class="button">
	                        <dd><input type="button" value="提 交"
								onclick="handSubmit('reset_pwd')" /></dd>
	                    </dl>
	                    <input type="hidden" value="<u:des3 value='${vcode }'/>" name="vcode"/>
						<input type="hidden" value="${sessionScope.rdmCode }" name="sign"/>
                    </form>
                </div>
                <p class="tips"><span>如还有其他帐号问题疑问请联系<a href="mailto:support@flypaas.com">support@flypaas.com</a></span></p>
            </div>
        </div>        
	</div>
	<!--主体部分content eof-->


	<!--公共底部footer bof-->
	<%@include file="/front/foot1.jsp"%>
	<!--公共底部footer bof-->


	<script type="text/javascript">
		$(function() {
			$("#resetPwd").change(function() {
				var resetPwd = $(this).val();
				if (resetPwd == "") {
					$("#resetPwd_error").fadeIn();
					$("#resetPwd_error").text("请输入新密码");
					$("#resetPwd").focus();
				}else if(spaceExists(resetPwd)){
			        $("#resetPwd_error").fadeIn();
					$("#resetPwd_error").text("密码不能为包含空格");
					$("#resetPwd").focus();
			        return false;
			  	}else if ((resetPwd.length<8)||(resetPwd.length>16)) {
					$("#resetPwd_error").fadeIn();
					$("#resetPwd_error").text("密码长度不符合规范,8-16位");
					$("#resetPwd").focus();
				}else if(!vPwd(resetPwd)){
		        	$("#resetPwd_error").fadeIn();
		            $("#resetPwd_error").text("密码不符合规范");
		 			$("#resetPwd").focus();
				}else {
					$("#resetPwd_error").hide();
				}
			});

			$("#resetPwd_confirm").change(function() {
				var resetPwd_confirm = $(this).val();
				var resetPwd = $("#resetPwd").val();
				if (resetPwd_confirm == "") {
					$("#resetPwd_confirm_error").fadeIn();
					$("#resetPwd_confirm_error").text("请输入确认密码");
					$("#resetPwd_confirm").focus();
				} else {
					if (resetPwd != resetPwd_confirm) {
						$("#resetPwd_confirm_error").fadeIn();
						$("#resetPwd_confirm_error").text("新密码和确认密码不一样");
						$("#resetPwd_confirm").focus();
					}
				}
			});
			$("#reset_pwd").submit(function() {
				var resetPwd = $("#resetPwd").val();
				var resetPwd_confirm = $("#resetPwd_confirm").val();
				if (resetPwd == "") {
					$("#resetPwd_error").fadeIn();
					$("#resetPwd_error").text("请输入新密码");
					$("#resetPwd").focus();
					return false;
				}else if(spaceExists(resetPwd)){
			        $("#resetPwd_error").fadeIn();
					$("#resetPwd_error").text("密码不能为包含空格");
					$("#resetPwd").focus();
			        return false;
			  	}else if ((resetPwd.length<8)||(resetPwd.length>16)) {
					$("#resetPwd_error").fadeIn();
					$("#resetPwd_error").text("密码长度不符合规范,8-16位");
					$("#resetPwd").focus();
					return false;
				}else if(!vPwd(resetPwd)){
		        	$("#resetPwd_error").fadeIn();
		            $("#resetPwd_error").text("密码不符合规范");
		 			$("#resetPwd").focus();
		 			return false;
				}

				if (resetPwd_confirm == "") {
					$("#resetPwd_confirm_error").fadeIn();
					$("#resetPwd_confirm_error").text("请输入确认密码");
					$("#resetPwd_confirm").focus();
					return false;
				} else {
					if (resetPwd != resetPwd_confirm) {
						$("#resetPwd_confirm_error").fadeIn();
						$("#resetPwd_confirm_error").text("新密码和确认密码不一样");
						$("#resetPwd_confirm").focus();
						return false;
					}
				}
			});
		});
	</script>
</body>
</html>
