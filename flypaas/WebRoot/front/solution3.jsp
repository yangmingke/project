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
<title>解决方案 - 企业通讯</title>
<%@include file="/front/resource.jsp" %>
</head>

<body>
<!--公共头部header bof-->
<%@include file="/front/head.jsp" %>
<!--公共头部header eof--> 

<!--中间部分middle bof-->
<div class="middle solution_box solution_box3">
  <div class="banner solution_banner solution_banner3">&nbsp;</div>
  <div class="intro_box intro_box1">
    <div class="intro_wrapper">
      <div class="intro_list">
        <dl class="dl1">
          <dt><img src="<%=path%>/front/images/banner_img14.png"></dt>
          <dd><b>管理者的困惑</b></dd>
          <dd>企业通讯录人员多，更新维护慢</dd>
          <dd>为了保护公司隐私不能随意浏览通讯录，却无法灵活设置权限</dd>
          <dd>客户资源分享少，不能及时方便的群策群力</dd>
          <dd>操作平台限制员工在办公场地，沟通效率低</dd>
        </dl>
        <dl class="dl2">
          <dt><img src="<%=path%>/front/images/banner_img15.png"></dt>
          <dd><b>员工的困惑</b></dd>
          <dd>同事多，通讯录要逐条录入手机，费时费力</dd>
          <dd>通讯方式变更不同步不能及时联系，更新查询效率差</dd>
          <dd>操作平台少，只能通过电脑查询，影响移动办公</dd>
          <dd>除了电话、邮件外沟通方式少，不能满足各种工作中的场景需求</dd>
        </dl>
      </div>
    </div>
  </div>

  <div class="intro_box intro_box2">
    <div class="intro_wrapper">     
      <div class="intro_list">
        <h1>企业通讯解决方案</h1>
        <dl class="dl1">
          <dd><b>高效的通讯录管理</b></dd>
          <dd>提供企业/商务通讯录，</dd>
          <dd>智能分析通信效果，</dd>
          <dd>防止客户流失和信息外泄，</dd>
          <dd>支持设置层级权限，</dd>
          <dd>全权掌控。</dd>
        </dl>
        <dl class="dl2">
          <dd><b>移动协同办公</b></dd>
          <dd>解放员工不再受办公场地的局限，</dd>
          <dd>在移动设备上快速响应工作协同。</dd>
        </dl>
        <dl class="dl3">
          <dd><b>与办公系统无缝对接</b></dd>
          <dd>与CRM、ERP、OA完美结合，</dd>
          <dd>满足各种办公场景的需求。</dd>
        </dl>
        <div class="list_img"><img src="<%=path%>/front/images/banner_img16.png" alt="企业通讯解决方案" /></div>
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
