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
<meta content="短信验证码AP接口|语音验证码API接口" name="keywords">
<meta content="快传融合通讯开放平台为广大的开发者免费提供各大主流系统的短信验证码AP接口和语音验证码API接口" name="description">
<title>短信验证接口_语音验证码API_快传</title>
<%@include file="/front/resource.jsp" %>
</head>
</head>
<body>
    <!--公共头部header bof-->
    <%@include file="/front/head.jsp" %>

    <!--公共头部header eof--> 

<!--主体部分 ft_content bof-->
<div class="ft_content">
  <div class="ft_banner1 ft_banner13">
    <div class="ft_banner_wp">
      <div class="desc"> <img src="<%=path%>/front/images/img2.png" alt="短信/语音验证码" />
        <h1>短信/语音验证码</h1>
        <h2>短信验证码和语音验证码可用于用户注册、身份验证、重要资料修改或寻回、网上支付、账户认证及相关通知等场景。</h2>
      </div>
    </div>
  </div>
  <div class="item_box box6 box63">
    <div class="item_box_wp">
      <div class="intro_list">
        <ul>
          <li class="li1">
            <p class="img img1"><img src="<%=path%>/front/images/img3.png" alt="短信验证码" /></p>
            <p><span>短信验证码</span>稳定及时、专业便捷，广泛应用在网站会员手机验证、APP应用手机验证、订单通知、物流提醒等触发类短信应用。</p>
          </li>
          <li class="li2">
            <p class="img"><img src="<%=path%>/front/images/img4.png" alt="短信验证码" /></p>
            <p><span>语音验证码</span>是通过语音电话直接呼叫用户手机或固定电话播报验证码，解决短信验证码到达率及政策性问题。常用于网站、移动客户端、银行金融等用户身份验证，以及支付确认等安全性要求更高的即时服务。</p>
          </li>
        </ul>
        <div class="clear"></div>
        <div class="intro_link"> <a href="<%=path%>/freetrial/VoiceVerificationCode" class="a1">查看功能演示</a> <a href="http://docs.flypaas.com/doku.php?id=短信验证码_模板短信" class="a2">短信验证码开发文档</a> <a href="http://docs.flypaas.com/doku.php?id=语音验证码" class="a3">语音验证码开发文档</a> </div>
      </div>
    </div>
  </div>
  <div class="item_box box7 box73">
    <div class="item_box_wp">
      <ul style="margin-top:170px;">
        <li class="li1">
          <dl>
            <dt>&nbsp;</dt>
            <dd class="tit">99%到达率</dd>
            <dd>行业最高到达率，5秒接收，到达率高达99%</dd>
          </dl>
        </li>
        <li class="li2">
          <dl>
            <dt>&nbsp;</dt>
            <dd class="tit">5秒接收</dd>
            <dd>智能路由，保证无延时，5秒接收</dd>
          </dl>
        </li>
        <li class="li3">
          <dl>
            <dt>&nbsp;</dt>
            <dd class="tit">低拦截</dd>
            <dd>与手机安全软件商紧密合作，短信无拦截</dd>
          </dl>
        </li>
        <li class="li4">
          <dl>
            <dt>&nbsp;</dt>
            <dd class="tit">全网发送</dd>
            <dd>支持移动、联通、电信号码的发送</dd>
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
              <th>验证码服务</th>
              <th>语音验证码显号服务需创建正式应用时设置</th>
              <th>更多</th>
            </tr>
            <tr>
              <td>短信验证码</td>
              <td style="border-left:none;">0.0500 元/条</td>
              <td rowspan="2"><a href="<%=path%>/about/cooperation" target="_blank">联系商务</a></td>
            </tr>
            <tr>
              <td>语音验证码</td>
              <td style="border-left:none;">0.0500 元/条</td>
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
          <p>注册快传平台账号，即可免费获得10元测试费用<a href="<%=path%>/user/toSign" target="_blank">>>立即注册</a></p>
          <p>资质认证后应用即可正式上线使用</p>
          <p>短信验证码需要提交短信模板并通过审核，语音验证码自定义提示语音需上传录音</p>
          <p>接入验证码功能具体可根据开发文档操作</p>
      </div> 
      <div class="intro_link"> <a href="<%=path%>/freetrial/VoiceVerificationCode" class="a1">查看功能演示</a> <a href="http://docs.flypaas.com/doku.php?id=短信验证码_模板短信" class="a2">短信验证码开发文档</a> <a href="http://docs.flypaas.com/doku.php?id=语音验证码" class="a3">语音验证码开发文档</a> </div>    
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