<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>媒体报道_快传融合通讯开放平台</title>
<%@include file="/front/resource.jsp" %>
</head>

<body>
<!--公共头部header bof-->
<%@include file="/front/head.jsp" %>

<!--公共头部header eof--> 

<!--中间部分middle bof-->
<div class="middle mid_box about_box">
  <div class="banner im_banner">&nbsp;</div>  
  <div class="display_wrapper">
    <div class="about_menu">
      <div class="menu_tit">
        <h1><span class="about">关于我们</span></h1>
      </div>
       <ul>
        <li><a href="<%=path%>/about/companyIntro"><i class="company">&nbsp;</i>公司介绍</a></li>
        <li><a href="<%=path%>/about/service"><i class="service">&nbsp;</i>我们的服务</a></li>
        <li><a href="<%=path%>/about/items"><i class="items">&nbsp;</i>服务条款</a></li>
        <li  class="active"><a href="<%=path%>/about/report"><i class="report">&nbsp;</i>媒体报道</a></li>
        <li><a href="<%=path%>/about/news"><i class="news">&nbsp;</i>新闻中心</a></li>
        <li><a href="<%=path%>/about/partners"><i class="news">&nbsp;</i>合作伙伴</a></li>
        <li><a href="<%=path%>/about/cooperation"><i class="cooperation">&nbsp;</i>联系我们</a></li>
      </ul>
    </div>
    <div class="about_txt">
      <div class="display_tit">
        <h1><span class="report">媒体报道</span></h1>
      </div>
      <div class="display_ctn report_list">
        <p><a href="http://news.tom.com/2015-03-09/OKVA/11792819.html" target="_blank">快传助力易孝宝，给父母的礼物让关爱实时在线</a><span class="source">TOM</span></p>
        <p><a href="http://www.sfw.cn/xinwen/464220.html" target="_blank">快传确认成为2015TFC大会支持单位</a><span class="source">上方网</span></p>
        <p><a href="http://www.kejixun.com/article/201412/88892.html" target="_blank">首个企业云服务产业联盟成立 快传为首届会员</a><span class="source">科技讯</span></p>
        <p><a href="http://www.newskj.org/kejixun/2014122330796.html" target="_blank">快传在36氪开放日杭州站完美落幕</a><span class="source">每日科技网</span></p>
        <p><a href="http://tech.hexun.com/2014-12-15/171427336.html" target="_blank">融合高效 快传引领云计算时代新通讯模式</a><span class="source">和讯网</span></p>
        <p><a href="http://www.cctime.com/html/2014-11-14/201411141054213942.htm" target="_blank">OTT势不可挡，微信电话本掀起新一轮OTT大战</a><span class="source">飞象网</span></p>
        <p><a href="http://www.51cto.com/art/201412/459350.htm" target="_blank">快传CTO贾俊杰获“2014最具价值CTO奖”</a><span class="source">51CTO</span></p>
        <p><a href="http://www.cctime.com/html/2014-12-2/2014122172147808.htm" target="_blank">快传亮相第四届全球移动互联网博览会</a><span class="source">飞象网</span></p>
        <p><a href="http://linux.ccidnet.com/art/302/20141113/5666623_1.html" target="_blank">OTT发展背后的融合通讯</a><span class="source">赛迪网</span></p>
        <p><a href="http://tech.sina.com.cn/i/2014-11-07/10079770184.shtml" target="_blank">双十一：你收到了多少商家短信</a><span class="source">新浪网</span></p>
        <p><a href="http://news.csdn.net/article.html?arcid=15820750&preview=1" target="_blank">快传融合通讯即将亮相MDCC 2014大会</a><span class="source">CSDN</span></p>
        <p><a href="http://www.csdn.net/article/a/2014-10-30/15820683" target="_blank">【WISE1.0现场】快传助您的应用获得通讯能力</a><span class="source">CSDN</span></p>
        <p><a href="http://www.donews.com/it/201410/2862256.shtm" target="_blank">快传重磅来袭安卓全球开发者大会</a><span class="source">DoNews</span></p>
        <p><a href="http://www.csdn.net/article/a/2014-10-21/15820519" target="_blank">运营商级服务平台 2亿用户共同见证</a><span class="source">CSDN</span></p>
        <p><a href="http://it.msn.com.cn/602959/347451835394b.shtml" target="_blank">快传融合通讯开放平台助力后通讯聚变时代</a><span class="source">MSN中文网</span></p>
        <p><a href="http://tech.huanqiu.com/cloud/2014-07/5086133.html" target="_blank">快传满足多样化沟通场景 建融合通讯生态圈</a><span class="source">环球网</span></p>
        <p><a href="http://www.36kr.com/p/214108.html" target="_blank">让每个应用插上通讯的翅膀，PaaS 平台快传获方广资本 1000万 A 轮投资</a><span class="source">36氪</span></p>
        <p><a href="http://www.cctime.com/html/2014-7-17/2014717164365229.htm" target="_blank">快传助力腾飞零门槛创业变身通讯精英</a><span class="source">飞象网</span></p>
      </div>
    </div>
  </div>
  <div class="clear"></div>
</div>
<!--中间部分middle eof--> 

 

<!--公共版权copyright bof-->
<%@include file="/front/foot_sk.jsp"%><%@include file="/front/foot.jsp" %>
<!--公共版权copyright eof-->

</body>
</html>
