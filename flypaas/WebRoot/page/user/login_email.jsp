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
<title>登录邮箱</title>
<%@include file="/page/resource.jsp"%>
</head>

<body>
<!--公共头部header bof-->
<%@include file="/page/head.jsp"%>
<!--公共头部header eof--> 

<!--主体部分content bof-->
<div class="ft_content ft_content_log">
  <div class="ft_content_wp"> 
    
    <!--用户注册 bof-->
    <div class="reg_box">
      <div class="reg_step"><img src="<%=path %>/images/reg_step_2.png" /></div>
      <div class="reg_info">
        <div class="activate_email">
          <h1 class="mail">激活邮箱</h1>
          <p>感谢注册！确认邮件已发送至你的注册邮箱：${email}。请进入邮箱查看邮件，并激活平台账号。</p>
          <p>
          	<input type="hidden" value="${email}" id="email"/>
          	<input type="hidden" value="${sid}" id="sid" />
            <input type="button" value="登录邮箱" id="toExtEmail"/>
          </p>
          <p class="note">没有收到邮件？<br />
            1、请检查邮箱地址是否正确，你可以返回<a href="<%=path %>/user/toSign">重新注册</a><br />
            2、检查你的邮件垃圾箱<br />
            3、若仍未收到确认，请尝试<a href="javascript:void(0)" id="sendMailSec">重新发送邮箱激活码</a>
         </p>
        </div>
      </div>
    </div>
    <!--用户注册 eof--> 
  </div>
 
</div>
<!--主体部分content eof--> 

<!--公共底部footer bof-->
<%@include file="/page/foot.jsp"%>
<!--公共底部footer bof-->
<script type="text/javascript">
	$(function(){
		$("#toExtEmail").click(function(){
			var mail = $("#email").val();
			var begin = mail.indexOf("@");
			var end = mail.indexOf(".");
			mail = mail.substring(begin+1,end);
			var url = "http://mail."+mail+".com";
			post(url, null, "_blank");
		});
		$("#sendMailSec").click(function(){
			var mail = $("#email").val();
			var sid = $("#sid").val();
			$.ajax({
				url:"<%=path%>/user/sendverifyMailAjax",
				type:"post",
				data:"sid="+sid+"&email="+mail,
				dataType: "text",
				success: function (data) {
					if(data==1){
						popupBox('提示','用户已激活','',2);
					}else{
						popupBox('提示','邮件发送成功','',2);
					}
	            },
	            error: function (msg) {
	            	
	            }
			});
		});
	});
</script>
</body>
</html>
