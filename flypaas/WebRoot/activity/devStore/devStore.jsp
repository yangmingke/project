<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>快传音视频开放平台_提供音视频通讯加速,一对一视频通话，视频直播等通讯加速API及SDK</title>
<meta name="keywords" content="一对一音视频通话,音视频通讯,音视频通讯加速,音视频通话,在线教育,视频通话,视频会议,互联网语音,通讯加速,快传,快传技术," >
<meta name="description" content="快传音视频通讯平台让开发者轻松接入一对一视频通话,音视频通讯加速,视频直播等能力,快速搭建音视频通讯,视频通话/会议应用,通讯加速,音视频引擎让视频更流畅。" >
<meta name="baidu-site-verification" content="EtMHBfc7G8" />
<%@include file="/front/resource.jsp" %>

<% Object object = com.flypaas.utils.UcCenterKit.getScript(request);
	if(null != object){
		out.println((String)object);
	}
%>
</head>
<body id="this_is_the_home_page">

<!--主体部分content bof-->
<style type="text/css">
body,div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,h5,h6,pre,code,form,fieldset,legend,input,button,textarea,select,p,blockquote,th,td,hr { margin: 0px; padding: 0px;}
body { font-size: 12px; line-height: 1.5; font-family: 微软雅黑; background: #323232; border-top: none;}
h1,h2,h3,h4,h5,h6 { font-family: 微软雅黑; font-size: 100%; font-weight:normal;}
input,textarea,select,button { font-size: 12px;,font-weight: normal;}
input[type='button'],input[type='submit'],select,button { cursor: pointer;}
table { border-spacing: 0;}
address,caption,cite,code,dfn,em,th,var { font-style: normal; font-weight: normal;}
li { list-style: none;}
caption,th { text-align: left;}
q:before,q:after { content: "";}
abbr,acronym { border: 0 none; font-variant: normal;}
sup { vertical-align: text-top;}
sub { vertical-align: text-bottom;}
fieldset,img,a img,iframe { border-style: none; border-width:0;}
img { vertical-align: middle; vertical-align: middle; outline: none;}
textarea { overflow-y:auto; }
legend { color: #000;}
a { text-decoration: none; outline: none; cursor: pointer;}
a:link,a:visited { text-decoration: none;}
hr {height: 0;}
.clear { clear: both;}
.error { color:#f00; background:url("../images/error.png") 0 0 no-repeat; height:16px; display:inline-block; padding-left:22px;}

.devStore_box { margin: 5% auto 0% auto; width: 1018px;}
.dev_box1 { margin-bottom: 8%;}
.devStore_box dl { overflow: hidden;}
.devStore_box dl dt { float: left; display: inline; width: 381px; text-align: center; margin-right: 92px;}
.devStore_box dl dd { margin-left: 473px; margin-bottom: 40px;}
.devStore_box dl dd.big { font-size: 30px; color: #0085cf;}
.devStore_box dl dd.big span { font-size: 18px; color: #fff; }
.devStore_box dl dd p { font-size: 18px; color: #fff; margin-bottom: 10px;}
.devStore_box dl dd p.p1 { color: #fff;}
.devStore_box dl dd p.p2 { color: #fe9b00; font-size: 24px;}
.devStore_box dl dd.tips p { color: #a3a3a3;}
.devStore_box dl dd a { background: #007BC1; color: #fff; padding: 5px 15px;  font-weight: bold; text-align: center; display: inline-block; margin-left: 20px; font-size: 18px; margin-top: 3px; vertical-align: top;}
.devStore_box dl dd a:hover { background: #fff; color: #007BC1;}
.dev_box1 dl dt img { margin-top: 15%;}
.dev_box2 dl dd.first { margin-top: 2%;}
.dev_box2 dl dd.first p { font-size: 30px;}
.dev_box2 dl dd.tips p { font-size: 16px;}
</style>

<div class="devStore_box">
  <div class="dev_box1">
    <dl>
      <dt>
        <img src="images/1.png" />
      </dt>
      <dd class="big">欢迎DevStore的小伙伴<br/><span>快传 - 几行代码，让您轻松为您的APP接入通讯功能。</span></dd>
      <dd>
        <p class="p1">现在注册快传用户，获得资格认证后，即可免费获取：</p>
        <p class="p2">150元的平台消费券</p>
      </dd>
      <dd class="tips">
        <p>申请方式：</p>
        <p>请小伙伴将自己的注册账号+联系方式 发送至service@flypaas.com</p>
        <p>客服美眉将会及时为您处理！</p>
      </dd>
    </dl>
  </div>

  <div class="dev_box2">
    <dl>
      <dt>
        <img src="images/2.png" />
      </dt>
      <dd class="first">
        <p class="p1">还没有快传账号？<a href="http://www.flypaas.com/user/toSign" target="_blank">立即注册</a></p>
      </dd>
      <dd class="tips">
        <p>官网：www.flypaas.com</p>
        <p>邮箱：service@flypaas.com</p>
        <p>企业QQ：4000970020</p>
        <p>联系电话：400-097-0020</p>
      </dd>
    </dl>
  </div>
</div>
<!--主体部分content eof--> 



</body>
</html>
