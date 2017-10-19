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
<title>解决方案 - 免费通话</title>
<%@include file="/front/resource.jsp" %>
</head>

<body>
<!--公共头部header bof-->
<%@include file="/front/head.jsp" %>
<!--公共头部header eof--> 

<!--中间部分middle bof-->
<div class="middle solution_box solution_box1">
  <div class="banner solution_banner solution_banner1">&nbsp;</div>
  <div class="intro_box intro_box1">
    <div class="intro_wrapper">
      <div class="intro_desc">
        <p>热恋中的小情侣煲电话粥时因欠费而突然<span class="mark">断线</span>。</p>
        <p>背井离乡的农民工大叔们因为<span class="mark">昂贵的话费</span>，一年就打那么几次电话给家中。</p>
        <p><span class="mark">海外</span>求学的游子给家中打电话需要支付昂贵的费用，靠打工赚生活费的他们真不容易。</p>
        <p>商务人士跨省处出差或是海外出差，<span class="mark">长途+漫游</span>，费用吃不消。</p>
        <p>偏远地区运营商<span class="mark">网络信号差</span>，想要有个好的通话质量还要爬到高处。</p>
      </div>
      <div class="intro_img"> <img src="<%=path%>/front/images/banner_img10.png" /> </div>
    </div>
  </div>

  <div class="intro_box intro_box2">
    <div class="intro_wrapper">     
      <div class="intro_img"> <img src="<%=path%>/front/images/banner_img11.png" /> </div> 
      <div class="intro_desc">
        <h2 class="mark">免费通话解决方案</h2>
        <p><b>价廉</b></p>
        <p>提供业界最低价的资费。</p>
        <p><b>质优</b></p>
        <p>拥有业内最多的线路资源,覆盖全运营商，无盲区，通话质量好。</p>
        <p><b>最快速</b></p>
        <p>提供最好用的SDK和API，快速集成通讯能力。</p>
        <p><b>最先进</b></p>
        <p>支持快传平台上的应用互通，轻松帮您的应用引流用户。</p>
      </div>
    </div>
  </div>

  <div class="intro_box intro_bottom">
    <p>想了解更多具体的解决方案信息，请咨询：400-097-0020</p>
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
