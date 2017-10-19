<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>开发者信息 - 修改</title>
<%@include file="/page/resource.jsp" %>
</head>

<body>
<!--公共头部header bof-->
<%@include file="/page/head.jsp" %>
<!--公共头部header eof--> 

<!--公共导航菜单 bof-->
<%@include file="/page/menu.jsp"%>
<!--公共导航菜单 eof--> 

<!--主体部分content bof-->
<div class="content">
  <div class="content_wrapper">
    <div class="content_box">
      <div class="main_tit">
        <h1>修改密码</h1>
      </div>
      <div class="main_ctn">
        <div class="correct_pwd">
          <p class="note"><span>定期更换密码可以让您的账户更加安全；建议密码采用字母和数字混合，并且不少于8位</span></p>
          <s:form namespace="/user" action="correctPwd" theme="simple" method="post" name="pwdForm" id="pwdForm" >
            <p><label for="current_pwd">当前密码</label><input type="password" value="" name="current_pwd" id="current_pwd" /><span id="current_pwd_error" class="error" style="display:none"></span></p>
            <p><label for="new_pwd">新密码</label><input type="password" value="" name="user.password" id="new_pwd" /><span id="new_pwd_error" class="error" style="display:none"></span></p>
            <p><label for="confirm_pwd">再次输入新密码</label><input type="password" value="" name="confirm_pwd" id="confirm_pwd" /><span id="confirm_pwd_error" class="error" style="display:none"></span></p>
            <p class="btn"><input type="button" id="correct_pwd_submit" value="修 改" /></p>
            <input type="hidden" value="${param.fr}" name="fr" />
          </s:form>
        </div>
        <input type="hidden" id="hpwd" />
      </div>
    </div>
  </div>
</div>

<!--主体部分content eof--> 

<!--公共底部footer bof-->
<%@include file="/page/foot.jsp" %>
<!--公共底部footer bof-->

<script type="text/javascript">
 $(function(){
	 $("#menu_1_5").addClass("active");
	 $(".child p").eq(0).show().siblings("p").hide();
	  $("#correct_pwd_submit").click(function(){
		  	$("#pwdForm").submit();
	  });
	  $("#current_pwd").change(function(){
		  var cpwd = $("#current_pwd").val();
		  cpwd = encodeURIComponent(cpwd);
		  $.ajax({
				url:"<%=path%>/user/thePwdIsRight",
				type:"post",
				data:"password="+cpwd,
				dataType: "text",
				success: function (data) {
	            if(data=="0"){
	          	  $("#current_pwd_error").fadeIn();
	              $("#current_pwd_error").text("密码不正确");
	    		  $("#current_pwd").focus();
	    		  $("#hpwd").val(2);
	            }else{
	          	  $("#current_pwd_error").hide();
	          	  $("#hpwd").val(1);
	            }
	          }
			}); 
	  });
	  $("#pwdForm").submit(function(){
	  	var newpwd = $("#new_pwd").val();
	  	var confirmpwd = $("#confirm_pwd").val();
	  	var cpwd = $("#current_pwd").val();
	  	if(cpwd==""){
	  		$("#current_pwd_error").fadeIn();
	        $("#current_pwd_error").text("密码不能为空");
	        $("#current_pwd").focus();
	        return false;
	  	}
	  	var c_pwd = $("#hpwd").val();
		if(c_pwd==2){
			return false;
		}
	  	if(newpwd==""){
	  		$("#new_pwd_error").fadeIn();
	        $("#new_pwd_error").text("新密码不能为空");
	        $("#new_pwd").focus();
	        return false;
	  	}else if(spaceExists(newpwd)){
	  		$("#new_pwd_error").fadeIn();
	        $("#new_pwd_error").text("密码不能为包含空格");
	        $("#new_pwd").focus();
	        return false;
	  	}else if(!vPwd(newpwd)){
	  		$("#new_pwd_error").fadeIn();
	        $("#new_pwd_error").text("密码不符合规范");
	        $("#new_pwd").focus();
	        return false;
		}else{
	  		if(newpwd.length<8 || newpwd.length>16){
	  			$("#new_pwd_error").fadeIn();
		        $("#new_pwd_error").text("新密码长度不符合规范");
		        $("#new_pwd").focus();
		        return false;
	  		}else{
	  			$("#new_pwd_error").hide();
	  		}
	  	}
	  	if(confirmpwd==""){
	  		$("#confirm_pwd_error").fadeIn();
	        $("#confirm_pwd_error").text("确认密码不能为空");
	        $("#confirm_pwd").focus();
	        return false;
	  	}
	  	if(newpwd!=confirmpwd){
	  		$("#confirm_pwd_error").fadeIn();
	        $("#confirm_pwd_error").text("两次密码输入不一样");
	        $("#confirm_pwd").focus();
	        return false;
	  	}
	  });
	 
 });
  
</script>
</body>
</html>
