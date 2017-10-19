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
<title>解决方案 - 国际漫游</title>
<%@include file="/front/resource.jsp" %>
</head>

<body>
<!--公共头部header bof-->
<%@include file="/front/head.jsp" %>
<!--公共头部header eof--> 

<!--中间部分middle bof-->
<div class="middle solution_box solution_box4">
  <div class="banner solution_banner solution_banner4">&nbsp;</div>
  <div class="intro_box intro_box1">
    <div class="intro_wrapper">
      <div class="intro_list">
        <p><img src="<%=path%>/front/images/banner_img17.png"></p>
        <p>出国旅游的团费越来越低，机票越来越便宜，酒店越来越优惠，但话费依然高居不下。</p>
        <p>一出国手机就变成了哑巴，<span class="mark1">不敢接，不敢打！太尴尬！</span></p>
      </div>
    </div>
  </div>

  <div class="intro_box intro_box2">
    <div class="intro_wrapper">     
      <div class="intro_list">
        <h1>国际漫游解决方案</h1>
        <dl class="dl1">
          <dd><b>国际语音漫游SDK</b></dd>
          <dd>将快传提供的国际语音漫游SDK集成到您的应用中，即可用户提供国际语音漫游服务。</dd>
        </dl>
        <dl class="dl2">
          <dd><b>无需换卡换号</b></dd>
          <dd>用户无需换卡换号，即使在国外，资费和体验也跟国内保持一致。</dd>
        </dl>
        <dl class="dl3">
          <dd><b>畅听无阻一键设置</b></dd>
          <dd>使用独立的VOIP APP拨打和接听，一键设置国际漫游时的接听，根据网络情况自适应调整。</dd>
        </dl>
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
