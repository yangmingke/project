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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>解决方案 - 隐号匿名</title>
<%@include file="/front/resource.jsp" %>
</head>

<body>
<!--公共头部header bof-->
<%@include file="/front/head.jsp" %>
<!--公共头部header eof--> 

<!--中间部分middle bof-->
<div class="middle solution_box solution_box6">
  <div class="banner solution_banner solution_banner6">&nbsp;</div>
  <div class="intro_box intro_box1">
    <div class="intro_wrapper">
      <div class="intro_img"> <img src="<%=path%>/front/images/banner_img20.png" /> </div> 
      <div class="intro_desc">
        <p>你有没有被房产中介、保险推销、诈骗电话等各类垃圾电话骚扰？</p>
        <p>越来越多的生活场景需要您留下电话号码，</p>
        <p>越来越多的渠道将您的个人电话信息外泄！</p>
        <p>如何防止骚扰？</p>
      </div>
    </div>
  </div>

  <div class="intro_box intro_box2">
    <div class="intro_wrapper">     
      <div class="intro_img"> <img src="<%=path%>/front/images/banner_img21.png" /> </div> 
      <div class="intro_desc">
        <h2 class="mark">隐号匿名解决方案</h2>
        <p><b>隐私通信</b></p>
        <p>开启隐私通信保护，无论短信还是电话，均可隐藏真实号码。</p>
        <p><b>海量号码池</b></p>
        <p>提供海量马甲小号，可以自主根据需要选择不同功能和时效的马甲小号。</p>
        <p><b>人性化控制</b></p>
        <p>提供隐号匿名SDK，用户随时可控隐匿号码功能的开关，不影响原生系统的稳定。</p>
      </div>
    </div>
  </div>
  <div class="clear"></div>
  <div class="intro_box intro_bottom">
    <p>想了解更多具体的解决方案信息，请咨询：400-097-0020</p>
  </div>
  </div>
</div>
<!--中间部分middle eof--> 

 

<!--公共版权copyright bof-->
<%@include file="/front/foot_sk.jsp"%><%@include file="/front/foot.jsp" %>
<!--公共版权copyright eof-->
<script type="text/javascript">
$(function(){
	$("#h_menu_3").css("color","#05c040");
});
</script>
</body>
</html>
