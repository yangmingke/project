<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
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
<%@include file="/front/head.jsp" %>

<!--主体部分content bof-->
<style type="text/css">
  .ucp_box { padding: 62px 30px; width: 915px; background:#fff; overflow: hidden; margin: 20px auto 30px auto;}
  .ucp_box h1 { font-size: 35px; color: #3e3e3e;}
  .ucp_box p { font-size: 18px; color: #333332; line-height: 35px; margin-bottom: 10px;}
  .ucp_box p a { display: inline-block; width: 802px; height: 121px; color: #fff; text-decoration: none; font-size: 72px; background: #ff9519; text-align: center; line-height: 121px;}
  .message_box { border-top: 1px solid #e6eaed; color: #747f8c; margin-top: 40px; padding-left: 55px; padding-top: 20px;}
  .message_box h2 { font-size: 25px; margin-bottom: 10px;}
  .ucp_box .message_box p { margin-bottom: 20px;}
  .message_box textarea { background:#f2f2f2; border:none; height: 120px; padding: 10px; width: 780px;resize:none;}
  .message_box input{ background: #51aded; border: none; color: #fff; height: 34px; line-height: 24px; width: 120px; font-weight: bold;}
</style>
<div class="middle" style="background:#e6e6e6; padding:40px 0px; border-top:6px #d6d6d6 solid;">	  
  <div class="display_box ucp_box">
    <h1>快传：如何在APP中加入类微信电话本功能</h1><br />
    <p><img src="<%=path%>/activity/images/1.png" /></p><br />
    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;双11晚上微信正式推出“微信电话本”应用，微信一键登录之后便可与微信好友直接通话，整体交互界面、流程和体验与手机打电话别无二致，且是免费的。</p>
    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一石激起千层浪，刚攀顶苹果APP Store榜首，伴随而来出现的是大量用户无法正常登陆、信号时断时续等故障（官方回应是用户量过大导致）。同时，一大波观察家从四面八方袭来，火速围观2大主角约架，并纷纷站好队伍表明立场 “ OTT势不可挡，微信电话本掀起新一轮OTT大战！”“挑战运营商？微信电话本还言之过早！”；当然也有比较中立的看官表示，OTT需要建立在与运营商合作的基础上；对运营商而言，则需要在新的产业链环境中，发挥固有优势并开放自身能力，与OTT服务共赢，号召大家携手迈进生命大和谐。</p><br />
    <p><img src="<%=path%>/activity/images/2.png" /></p>
    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;好一场热闹大戏。你被时尚新贵与传统壕的光芒闪瞎眼了吗？暂且不用感叹，今日小编带你体验来自小咔的逆袭：30分钟，让你的APP中加入类微信电话本功能。</p>
    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;其实微信电话本并不是新鲜事。让我们先来了解免费电话APP的本质。这其实就是网络电话，电话通讯录应该算做第三代网络电话。第一代网络电话，是指收费网络电话。国际上以Skype最具代表性，国内较有名的则是微说。由于早年运营商电话资费非常高昂，因此收费网络电话推出时深具价格优势，很快获得大批忠实拥趸。虽然当时网络电话的技术相对还不够成熟，通话质量也较差一些，但为了节省电话费，不少用户也愿意承受较差的通话质量；随着通讯手段越来越多，运营商的资费也越来越低，付费网络电话原有的价格优势不再明显，因此以Viber为代表的第二代网络电话应运而生，开启了免费电话时代。Viber这类公司不向用户收费，而是通过广告等增值服务挣钱。但免费电话通话效果毕竟没有运营商通话效果好，导致第二代网络电话影响力也不是十分大。</p>
    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;最近1-2年，开始有企业把系统拨号盘的界面和网络电话整合，使得网络电话APP直接完全兼容系统拨号盘的所有功能，用户可以只用网络电话APP来实现系统拨号盘呼叫或者网络呼叫，整个体验更具备整体感，比如在使用网络电话呼叫时，如果网络质量不太理想，系统会自动提醒是否切换至普通电话，从而实现一键切换。这是第三代网络电话，典型的代表就是微信电话本。当然微信电话本及上文说的网络电话大都是to C用户的，在企业级to B市场，则有用友E联系这样的应用（见下图）。</p><br />
    <P><img src="<%=path%>/activity/images/3.png" /></P>
    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;那么，这些应用都是像微信电话本一样自己研发了该类功能吗，答案显然是否定的，因为这太耗时耗力。此时，用友等背后的底层通讯能力提供商开始浮出水面，如今年初完成首轮融资的快传flypaas平台，就是专门为企业及各类移动APP提供底层通讯能力API接口服务的平台：“快传通过构建一张开放式的融合通信网络，并把融合通信的能力打包成非常友好的API和SDK的方式提供给开发者，降低开发者建设和使用融合通信能力的技术门槛和投资门槛”。</p>
    <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;就是这么酷炫，只需简单几行代码接入API， 30分钟时间即可拥有微信电话本功能：</p>
    <p><img src="<%=path%>/activity/images/4.png" /></p><br />
    <p style="font-size:28px; text-align:center;">你还在等什么呢？一起来逆袭，主角风采人人来一份!</p><br /><br />
    <p style="text-align:center;"><a href="<%=path%>/user/toSign" target="_blank">点击立即试用</a></p>
    
    <div class="message_box">
      <form action="vistorsMsg" id="msgFrm" method="post">
        <h2>游客留言</h2>
        <p><textarea type="text" name="message" id="message"></textarea></p>
        <p><input type="button" id="subt" value="提交"/></p>       
    </form> 
    </div>
    <p>
	    
    </p>
  </div> 
</div>

<!--主体部分content eof--> 


<!--公共底部footer bof-->
<%@include file="/front/foot.jsp" %>
<!--公共底部footer bof--> 

<script type="text/javascript">
	$(function(){
		$("#subt").click(function(){
			var msg = $("#message").val();
			if(msg!=""){
				if(msg.length>1024){
					popupBox("提示","内容太长了，简化一下吧",null,2);
				}else{
					$("#msgFrm").submit();
				}
			}
		});
	});
</script>
</body>
</html>
