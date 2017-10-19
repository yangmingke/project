<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>激活失败</title>
<%@include file="/front/resource.jsp" %>
</head>
<body>
<%-- <%@include file="/front/head.jsp" %> --%>

<!--主体部分content bof-->
<div class="ft_content ft_content_log">
  <div class="ft_content_wp"> 
    
    <!--用户注册 bof--->
    <div class="reg_box">
      <div class="reg_info reg_success">
        <p style="font-size:24px; margin-bottom:40px;">激活失败</p>
        <c:choose>
        	<c:when test="${resultCode == '1'}">
        		<p>用户不存在</p>
        	</c:when>
        	<c:when test="${resultCode == '2'}">
        		<p>用户已经激活!</p>
        	</c:when>
        	<c:when test="${resultCode == '3'}">
        		<p>数据验证码不存在！</p>
        	</c:when>
        	<c:when test="${resultCode == '4'}">
        		<p>链接参数错误</p>
        	</c:when>
        	<c:otherwise>
	        	<p>邮箱<s:property value="user.email"/>激活失败,激活链接已过期，请点击<a href="javascript:void(0)" onclick="secSendMail()">重新发送邮箱激活码</a></p>
        	</c:otherwise>
        </c:choose>
        <input type="hidden" value="${sid}" name="sid" id="sid"/>
      </div>
    </div>
    <!--用户注册 eof--> 
  </div>  

</div>
<!--主体部分content eof--> 


<!--公共底部footer bof-->
<%-- <%@include file="/front/foot.jsp" %> --%>
<!--公共底部footer bof--> 

<script type="text/javascript">
function secSendMail(){
	var sid = $("#sid").val();
	$.ajax({
		url:"<%=path%>/user/sendverifyMailAjax",
		type:"post",
		data:"sid="+sid,
		dataType: "text",
		success: function (data) {
         if(data=="0"){
        	 alert("发送成功，请查收邮件。");
         }else{
        	 alert("发送失败，请重试。");
         }
        },
        error: function (msg) {
        	alert("邮件服务器异常。");
        }
	});
}

</script>

</body>
</html>
