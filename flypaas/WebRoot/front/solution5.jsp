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
<title>解决方案 - 安全通讯</title>
<%@include file="/front/resource.jsp" %>
</head>

<body>
<!--公共头部header bof-->
<%@include file="/front/head.jsp" %>
<!--公共头部header eof--> 

<!--中间部分middle bof-->
<div class="middle solution_box solution_box5">
  <div class="banner solution_banner solution_banner5">&nbsp;</div>
  <div class="intro_box intro_box1">
    <div class="intro_wrapper">
      <div class="intro_list">
        <p><img src="<%=path%>/front/images/banner_img18.png"></p>
        <p>可怕的大数据时代来临了，你的隐私何在？衣食住行已经全部赤裸裸的沦陷在大数据里，你的手机呢？</p>
        <p>运营商监听，木马病毒非法窃听，话单信息泄露。</p>
      </div>
    </div>
  </div>

  <div class="intro_box intro_box2">
    <div class="intro_wrapper">     
      <div class="intro_img"> <img src="<%=path%>/front/images/banner_img19.png" /> </div> 
      <div class="intro_desc">
        <h2 class="mark">安全通讯解决方案</h2>
        <p><b>防监听</b></p>
        <p>采用点对点语音通话，芯片级加密，保证通话不被监听。</p>
        <p><b>隐藏话单</b></p>
        <p>通过VOIP平台拨打落地电话，隐藏通话记录，无法查询主叫。</p>
        <p><b>实现方式多样化</b></p>
        <p>既可以采用系统内嵌拨号盘的方式，也可以使用独立APP方式，实现安全通话。</p>
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
