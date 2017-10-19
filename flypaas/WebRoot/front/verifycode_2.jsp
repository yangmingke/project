<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>快传音视频开放平台_提供音视频通讯加速,一对一视频通话，视频直播等通讯加速API及SDK</title>
<meta name="keywords" content="一对一音视频通话,音视频通讯,音视频通讯加速,音视频通话,在线教育,视频通话,视频会议,互联网语音,通讯加速,快传,快传技术," >
<meta name="description" content="快传音视频通讯平台让开发者轻松接入一对一视频通话,音视频通讯加速,视频直播等能力,快速搭建音视频通讯,视频通话/会议应用,通讯加速,音视频引擎让视频更流畅。" >
<%@include file="/front/resource.jsp" %>
</head>
</head>
<body>
    <!--公共头部header bof-->
    <%@include file="/front/head.jsp" %>

    <!--公共头部header eof-->  

<!--主体部分 ft_content bof-->
<div class="ft_content">
  <div class="ft_banner1 ft_banner14">
    <div class="ft_banner_wp">
      <div class="desc"> <img src="<%=path%>/front/images/img5.png" alt="智能验证平台" />
        <h1>智能验证平台</h1>
        <h2>智能验证平台是基于云端的用户身份验证平台，平台将短信验证和语音验证集成为一个工具包，从而保证验证码的高到达率；平台集成验证码生成、验证码校验、验证码发送等操作，开发者只需传入用户的手机号码即可轻松完成用户身份验证。
        </h2>
      </div>
    </div>
  </div>
  <div class="item_box box7 box73">
    <div class="item_box_wp">
      <ul>
        <li class="li1">
          <dl>
            <dt>&nbsp;</dt>
            <dd class="tit">专业</dd>
            <dd>专注于提供手机验证服务，专业只发送验证码通道，避免通道拥塞或被投诉</dd>
          </dl>
        </li>
        <li class="li2">
          <dl>
            <dt>&nbsp;</dt>
            <dd class="tit">便捷</dd>
            <dd>提供SDK接口，开发者只需传入用户手机号码即可，验证码生成、校验均由云验证平台完成</dd>
          </dl>
        </li>
        <li class="li3">
          <dl>
            <dt>&nbsp;</dt>
            <dd class="tit">高验证</dd>
            <dd>云验证通过的用户立即验证成功，拥有优质的短信和语音验证通道辅助验证，提供最优的验证码解决方案</dd>
          </dl>
        </li>
        <li class="li4">
          <dl>
            <dt>&nbsp;</dt>
            <dd class="tit">高转化率</dd>
            <dd>高效的验证工具，可提高注册转化率20%以上</dd>
          </dl>
        </li>
        <li class="li5">
          <dl>
            <dt>&nbsp;</dt>
            <dd class="tit">安全</dd>
            <dd>接口增加防刷机制；验证结果增加key加密，保证验证结果的真实性</dd>
          </dl>
        </li>
        <li class="li6">
          <dl>
            <dt>&nbsp;</dt>
            <dd class="tit">实惠</dd>
            <dd>验证费用低至3分/条</dd>
          </dl>
        </li>
      </ul>
    </div>
  </div>
  <div class="item_box box9">
    <div class="item_box_wp">
      <h3>价格</h3>
      <div class="price_wrapper">
        <table cellpadding="0" cellspacing="0" border="0" width="96%">
          <tbody>
            <tr>
              <th style="width:25%;">验证码服务</th>
              <th style="width:35%;">价格</th>
              <th style="width:40%;">联系商务</th>
            </tr>
            <tr>
              <td style="text-align:center;">智能验证</td>
              <td style="text-align:center;">0.0300 元/条</td>
              <td style="line-height:30px; font-size:14px;text-align:center"><span style="display:block;">技术交流群：316069017</span><span style="display:block;padding-left:30px">联系手机：18675548028</span><span style="display:block;padding-left:30px">联系电话：400-097-0020</span><span style="display:block;padding-left:48px">商务合作邮箱：service@flypaas.com</span></td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </div>

  <div class="item_box box10">
      <h3>开通流程</h3>
      <div class="step_txt">
          <p class="img"><img src="<%=path%>/front/images/color_step.png" alt="开通流程" /></p>
          <p>注册快传平台账号，即可免费获得10元测试费用 <a href="<%=path%>/user/toSign" target="_blank">>>立即注册</a></p>
          <p>资质认证后应用即可正式上线使用</p>
          <p>短信验证码需要提交短信模板并通过审核，语音验证码自定义提示语音需上传录音</p>
          <p>接入验证码功能具体可根据开发文档操作</p>
      </div>
  </div>

  <div class="item_box box5">
    <div class="item_box_wp">
      <p>想了解更多？请致电 400-097-0020</p>
    </div>
  </div>
</div>
<!--主体部分 ft_content eof--> 

 

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