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
<meta name="keywords" content="短信验证码|语音验证码"/>
<meta name="description" content="快传融合通讯网络电话，提供多种网络通话能力，支持语音会议，视频会议，VoIP网络电话等，资费最低，即插即用，是国内最好用的免费网络电话" />
<title>短信验证码_语音验证码的SDK与API-快传融合通讯开放平台</title>
<%@include file="/front/resource.jsp" %>
</head>

<body>
<!--公共头部header bof-->
<%@include file="/front/head.jsp" %>

<!--公共头部header eof--> 

<!--中间部分middle bof-->
<div class="middle mid_v_box">
  <div class="banner v_banner">&nbsp;</div>
  <div class="v_box white">
    <div class="v_wrapper v_intro">
      <h1>短信/语音验证码</h1>
      <p class="p1">短信验证码和语音验证码可用于用户注册、身份验证、重要资料修改或寻回、网上支付、账户认证及相关通知等场景。</p>
      <p class="p2">短信验证码稳定及时、专业便捷，广泛应用在网站会员手机验证、APP应用手机验证、订单通知、物流提醒等触发类短信应用。</p>
      <p class="p3">语音验证码是通过语音电话直接呼叫用户手机或固定电话播报验证码，解决短信验证码到达率及政策性问题。常用于网站、移动客户端、银行金融等用户身份验证，以及支付确认等安全性要求更高的即时服务。</p>
      <p class="link"><a href="<%=path%>/freetrial/VoiceVerificationCode" target="_blank">>>查看功能演示</a><a href="<%=path%>/doc/doc_rest4-1.jsp" target="_blank">>>短信验证码开发文档</a><a href="<%=path%>/doc/doc_rest3-2.jsp" target="_blank">>>语音验证码开发文档</a></p>
    </div>
  </div>
  <div class="v_box grey">
    <div class="v_wrapper">
      <h1>优势</h1>
      <ul>
        <li class="li4">
          <dt>&nbsp;</dt>
          <dd><b>99%到达率</b></dd>
          <dd>行业最高到达率，5秒接收，到达率高达99%</dd>
        </li>
        <li class="li2">
          <dt>&nbsp;</dt>
          <dd><b>5秒接收</b></dd>
          <dd>智能路由，保证无延时，5秒接收</dd>
        </li>
        <li class="li1">
          <dt>&nbsp;</dt>
          <dd><b>低拦截</b></dd>
          <dd>与手机安全软件商紧密合作，短信无拦截</dd>
        </li>
        <li class="li3">
          <dt>&nbsp;</dt>
          <dd><b>稳定及时</b></dd>
          <dd>正规的106通道，全国优质全网资源聚合，最优质量，最好服务</dd>
        </li>
        <li class="li5">
          <dt>&nbsp;</dt>
          <dd><b>全网发送</b></dd>
          <dd>支持移动、联通、电信号码的发送</dd>
        </li>
        <li class="li6">
          <dt>&nbsp;</dt>
          <dd><b>无扣量</b></dd>
          <dd>实量发送，诚信为本</dd>
        </li>
      </ul>
    </div>
  </div>
  <div class="v_box white">
    <div class="v_wrapper">
      <h1>价格</h1>
      <table cellpadding="0" cellspacing="0" border="0" width="100%">
        <tbody>
          <tr>
            <th>验证码服务</th>
            <th>语音验证码显号服务需创建正式应用时设置</th>
            <th>更多</th>
          </tr>
          <tr>
            <td>短信验证码</td>
            <td style="border-right: 1px #e0eefe solid;">0.0500 元/条</td>
            <td rowspan="2"><a href="<%=path%>/about/cooperation" target="_blank">联系商务</a></td>
          </tr>
          <tr>
            <td>语音验证码</td>
            <td>0.0500 元/条</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
  <div class="v_box grey">
    <div class="v_wrapper v_step">
      <h1>开通流程</h1>
      <p class="img"><img src="<%=path%>/front/images/v_step.png" /></p>
      <p>注册快传平台账号，即可免费获得10+99元测试费用,相当于免费使用超过2000条短信 <a href="<%=path%>/user/toSign" target="_blank">>>立即注册</a></p>
      <p>免资质认证即可测试使用平台所用功能：短信、语音以及即时消息等；</p>
      <p>快传开放平台坚决不做营销类短信，从而保证短信通道优质，最高99%的到达率；</p>
      <p>接入简单，只需5分钟即可使得你的应用获得“手机短信验证、免费互联网语音以及IM通讯”功能；</p>
      <p>备注：10元直接由系统赠送，99元直接向在线客服免费申请即可！</p>
      <p class="link"><a href="<%=path%>/freetrial/VoiceVerificationCode" target="_blank" style="margin:0px 38px;">查看功能演示</a><a href="<%=path%>/doc/doc_rest4-1.jsp" target="_blank" style="margin:0px 38px;">短信验证码开发文档</a><a href="<%=path%>/doc/doc_rest3-2.jsp" target="_blank" style="margin:0px 38px;">语音验证码开发文档</a><a href="<%=path%>/user/toSign" target="_blank" style="margin:0px 38px;">注册</a></p>
    </div>
  </div>
  <div class="v_box blue">
    <div class="v_wrapper">
      <p>想了解更多信息，请立即注册</p>
    </div>
  </div>
</div>
<!--中间部分middle eof--> 

 

<!--公共版权copyright bof-->
<%@include file="/front/foot_sk.jsp"%><%@include file="/front/foot.jsp" %>
<!--公共版权copyright eof-->
<script type="text/javascript">
$(function(){
	$("#h_menu_2").css("color","#05c040");
})
</script>
</body>
</html>
