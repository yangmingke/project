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
<title>解决方案 - IP客服</title>
<%@include file="/front/resource.jsp" %>
</head>

<body>
<!--公共头部header bof-->
<%@include file="/front/head.jsp" %>
<!--公共头部header eof--> 

<!--中间部分middle bof-->
<div class="middle solution_box solution_box2">
  <div class="banner solution_banner solution_banner2">&nbsp;</div>
  <div class="intro_box intro_box1">
    <div class="intro_wrapper">
      <div class="intro_list">
        <h1>传统客服的困惑</h1>
        <dl class="dl1">
          <dt><img src="<%=path%>/front/images/banner_img13.png"></dt>
          <dd><b>拨打400客服电话的用户</b></dd>
          <dd>即使拨打免费电话也需要支付通讯费</dd>
          <dd>传统的IVR流程冗长，无心等待</dd>
          <dd>只能在手机上拨打</dd>
        </dl>
        <dl class="dl2">
          <dt><img src="<%=path%>/front/images/banner_img12.png"></dt>
          <dd><b>400客服电话座席</b></dd>
          <dd>企业需要支付高昂的通讯费</dd>
          <dd>客服座席不支持移动办公</dd>
          <dd>用户在app中拨打客户电话造成的用户流失</dd>
          <dd>在pc侧、pad侧无法为用户提供客服电话直拨服务</dd>
        </dl>
      </div>
    </div>
  </div>

  <div class="intro_box intro_box2">
    <div class="intro_wrapper">     
      <div class="intro_list">
        <h1>IP客服解决方案</h1>
        <dl class="dl1">
          <dt><img src="<%=path%>/front/images/solution_icon1.png"></dt>
          <dd><b>VOIP通话降低企业成本</b></dd>
          <dd>让用户真正的免费拨打客服电话，让企业将客服成本一降再降。</dd>
        </dl>
        <dl class="dl2">
          <dt><img src="<%=path%>/front/images/solution_icon2.png"></dt>
          <dd><b>移动座席灵活高效</b></dd>
          <dd>客服座席不再受办公场地局限，移动座席颠覆传统模式，实现灵活办公，高效工作。</dd>
        </dl>
        <dl class="dl3">
          <dt><img src="<%=path%>/front/images/solution_icon3.png"></dt>
          <dd><b>拨号盘IVR引导</b></dd>
          <dd>让传统的IVR流程不再成为用户恶评的导火索。</dd>
        </dl>
        <dl class="dl4">
          <dt><img src="<%=path%>/front/images/solution_icon4.png"></dt>
          <dd><b>网页回拨</b></dd>
          <dd>无论是pc网页还是pad终端，用户随时可以与企业发起一键通话，让客服服务更细致。</dd>
        </dl>
      </div>
    </div>
  </div>

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
