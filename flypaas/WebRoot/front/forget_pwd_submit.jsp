<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>忘记密码 - 输入邮箱提交</title>
<%@include file="/front/resource1.jsp"%>
</head>

<body>
	<%@include file="/front/head1.jsp"%>


	<!--主体部分content bof-->
	<!--
	<div class="ft_content ft_content_log">
		<div class="ft_content_wp">
			<div class="reg_box pwd_box">
				<div class="float_tit">
					<h3>忘记密码</h3>
					
				</div>
				<div class="float_ctn">
					<form method="post" name="pwdForm" id="forget_pwd">
						<h4>
							您的注册邮箱：<span><s:property value="email" />
							</span>
						</h4>
						<p>* 我们已将重置密码的邮件发送到您的邮箱，请登录并点击邮箱内的重置链接进行下一步操作</p>
						<p>* 如果您在一个小时内未收到密码重置邮件，请检查一下[垃圾邮件](因部分过滤严格的企业邮箱会把部分带链接的邮件统一识别为垃圾邮件)</p>
					</form>
				</div>
			</div>
		</div>
	</div>
	  -->
	 <div class="ft_content ft_content_reg">
    	<div class="ft_content_wp">
            <div class="reg_step">
                <img src="<%=path%>/front/images1/forget_pwd_step2.png" />
            </div>
            <div class="reg_box forget_pwd_box">
                <p>flypaas 已将重置密码邮件发送至您的邮箱，请查收邮件并完成重置密码操作</p>
                <input type="hidden" value="${email }" id="email" />
                <p class="large">您的邮箱<span><s:property value="email" /></span>邮件已发送</p>
                <p><input type="button" value="登录邮箱" class="w140" id="toExtEmail"/></p>
            </div>
        </div>        
	</div>
	<!--主体部分content eof-->


	<!--公共底部footer bof-->
	<%@include file="/front/foot1.jsp"%>
	<!--公共底部footer bof-->
</body>
</html>
