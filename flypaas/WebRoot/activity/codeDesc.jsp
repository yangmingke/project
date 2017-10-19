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
  .codeDesc { background: #fff9f9}
  .act_banner { background: url("images/act_banner1.png") center center no-repeat; width: 100%; height: 718px;}
  .banner_wrapper { width: 980px; margin: 0 auto; position: relative;}
  .banner_wrapper a.link1 { display: inline-block;width: 260px; height: 60px; background: url("images/act_bg.png") 0 0 no-repeat; position: absolute; top: 527px; left: 360px;}
  .banner_wrapper a.link1:hover { background-position: 0 -187px;}
  .banner_wrapper a.link2 { display: block; position: absolute; top: 606px; left: 334px; font-size: 20px; color: #fff; letter-spacing: 0.1em;}
  .codeDesc_box { margin: 0 auto 64px auto; border: none; width: 980px; background: #fff9f9;}
  .act_list { margin: 65px auto 50px auto; overflow: hidden;}
  .act_list dl { float: left; display: inline; width: 223px; text-align: center; margin-right: 29px;}
  .act_list dl.last { margin-right: 0px;}
  .act_list dl dt { display: inline-block; width: 223px; text-align: center; margin-bottom: 20px; height: 152px;}
  .act_list dl dd { font-size: 20px; color: #4a4a4a;}
  .act_link { text-align: center; margin-bottom: 56px;}
  .act_link a { display: inline-block; background: url("images/act_bg.png") 0 -70px no-repeat; width: 260px; height: 60px;}
  .act_link a:hover { background-position: 0 -260px;}
  .act_info h2 { font-size: 20px; color: #3c3c3c; background: url("images/act_bg.png") 0 -142px no-repeat; height: 36px; line-height: 36px; margin-bottom: 24px; padding-left: 48px;}
  .act_info p { color: #595959; font-size: 14px; margin-bottom: 14px; background: url("images/dot.png") 0 6px no-repeat; margin-left: 48px; padding-left: 15px;}
</style>
<div class="middle codeDesc">	
  <div class="act_banner">
    <div class="banner_wrapper"><a href="<%=path%>/user/toSign" class="link1"></a><a href="#act_info" class="link2">如何领取？请阅读下方↓活动说明</a></div>
  </div>
  <div class="display_box codeDesc_box">    
    <div class="act_list">
      <dl>
        <dt><img src="<%=path%>/activity/images/act_img1.png" alt="短信+语音验证码 双保险" /></dt>
        <dd>短信+语音验证码 双保险</dd>
      </dl>
      <dl>
        <dt><img src="<%=path%>/activity/images/act_img2.png" alt="100%到达率" /></dt>
        <dd>100%到达率</dd>
      </dl>
      <dl>
        <dt><img src="<%=path%>/activity/images/act_img3.png" alt="提升注册转化率20%" /></dt>
        <dd>提升注册转化率20%</dd>
      </dl>
      <dl class="last">
        <dt><img src="<%=path%>/activity/images/act_img4.png" alt="增“防刷/Key加密”" /></dt>
        <dd>增“防刷/Key加密”</dd>
      </dl>
    </div>
    <div class="act_link"><a href="<%=path%>/doc/codeDesc"></a></div>
    <div class="act_info" id="act_info">
      <h2>活动说明</h2>
      <p>从即日起凡成功注册使用快传智能验证平台的APP运营企业或个人开发者，每月赠送2万次验证机会(验证码)，自接入日起持续赠送一年，共计赠送24万次验证机会(验证码)。</p>
      <p>如每月使用量超过2万次验证(验证码)的赠送额度，超出部分按3分钱/次验证正常收费。如每月使用量不足2万次验证，赠送余额可累计叠加。</p>
      <p>本活动承诺的2万次验证机会将通过账户额度的方式充值到客户账户，当日下午4点以前提交的需求当日到账，当日下午4点以后提交的需求次日到账，休息日顺延。</p>
      <p>本次活动赠送的免费验证机会只限于以下场景使用，如有其它问题请咨询本活动说明提供的QQ群。</p>
      <p>移动端的APP用户注册验证。</p>
      <p>仅限注册使用。</p>      
      <p>注册成功后，在qq群：316069017，寻找管理员“智能验证赠送”，申请240000条免费验证机会（验证码）的资格，先到先得。</p>
      <p>注册成功起满1年后仍然还有赠送余额的则一律清零。</p>
      <p>本活动有效期为2个月，截止日期2015年2月14日。</p>
      <p>本赠送仅限“智能验证平台”使用，经系统自动检查用于快传其他产品消费的，需正常计费；</p>
      <p>活动最终解释权归深圳市快传技术有限公司所有。</p>
    </div>
  </div>  
</div>
<!--主体部分content eof--> 


<!--公共底部footer bof-->
<%@include file="/front/foot.jsp" %>
<!--公共底部footer bof--> 

</body>
</html>
