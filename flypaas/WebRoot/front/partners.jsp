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
<title>公司介绍_快传融合通讯开放平台</title>
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
        <li><a href="<%=path%>/about/report"><i class="report">&nbsp;</i>媒体报道</a></li>
        <li><a href="<%=path%>/about/news"><i class="news">&nbsp;</i>新闻中心</a></li>
        <li class="active"><a href="<%=path%>/about/partners"><i class="partners">&nbsp;</i>合作伙伴</a></li>
        <li><a href="<%=path%>/about/cooperation"><i class="cooperation">&nbsp;</i>联系我们</a></li>
      </ul>
    </div>
    <div class="about_txt">
      <div class="display_tit">
        <h1><span class="partners">合作伙伴</span></h1>
      </div>
      <div class="display_ctn">
        <ul>
          <li>
            <dl>
              <dt><img src="<%=path%>/front/images/bd_api.png" /></dt>
              <dd>百度 API Store</dd>
              <dd><a href="http://apistore.baidu.com/" target="_blank">apistore.baidu.com</a></dd>
            </dl>
            <dl>
              <dt><img src="<%=path%>/front/images/yzh.png" /></dt>
              <dd>云智慧</dd>
              <dd><a href="http://www.cloudwise.com/" target="_blank">www.cloudwise.com</a></dd>
            </dl>
            <dl>
              <dt><img src="<%=path%>/front/images/one_apm.png"/></dt>
              <dd>OneAPM</dd>
              <dd><a href="http://oneapm.com/" target="_blank">oneapm.com</a></dd>
            </dl>
            <dl>
              <dt><img src="<%=path%>/front/images/sdb.png"/></dt>
              <dd>巨杉数据库</dd>
              <dd><a href="http://www.sequoiadb.com/" target="_blank">www.sequoiadb.com</a></dd>
            </dl>
            <dl>
              <dt><img src="<%=path%>/front/images/pgyer.png"/></dt>
              <dd>蒲公英</dd>
              <dd><a href="http://www.pgyer.com/" target="_blank">www.pgyer.com</a></dd>
            </dl>
            <dl>
              <dt><img src="<%=path%>/front/images/unisound.png"/></dt>
              <dd>云知声</dd>
              <dd><a href="http://dev.hivoice.cn/" target="_blank">dev.hivoice.cn</a></dd>
            </dl>
            <dl>
              <dt><img src="<%=path%>/front/images/teambition.png"/></dt>
              <dd>Teambition</dd>
              <dd><a href="https://www.teambition.com/" target="_blank">www.teambition.com</a></dd>
            </dl>
            <dl>
              <dt><img src="<%=path%>/front/images/mike.png"/></dt>
              <dd>麦客</dd>
              <dd><a href="http://www.mikecrm.com/" target="_blank">www.mikecrm.com</a></dd>
            </dl>
            <dl>
              <dt><img src="<%=path%>/front/images/ijiami.png"/></dt>
              <dd>爱加密</dd>
              <dd><a href="http://www.ijiami.cn/" target="_blank">www.ijiami.cn</a></dd>
            </dl>
            <dl>
              <dt><img src="<%=path%>/front/images/apiCloud.png"/></dt>
              <dd>APICloud</dd>
              <dd><a href="http://www.apicloud.com/ " target="_blank">www.apicloud.com</a></dd>
            </dl>
            <dl>
              <dt><img src="<%=path%>/front/images/qiniu.png"/></dt>
              <dd>七牛云存储</dd>
              <dd><a href="http://www.qiniu.com/" target="_blank">www.qiniu.com</a></dd>
            </dl>
            <dl>
              <dt><img src="<%=path%>/front/images/ping++.svg"/></dt>
              <dd>PING++</dd>
              <dd><a href="https://pingxx.com/" target="_blank">www.pingxx.com</a></dd>
            </dl>
            <dl>
              <dt><img src="<%=path%>/front/images/devStore.png"/></dt>
              <dd>Dev Store</dd>
              <dd><a href="http://www.devstore.cn/" target="_blank">www.devstore.cn</a></dd>
            </dl>
            <dl>
              <dt><img src="<%=path%>/front/images/coding.png"/></dt>
              <dd>CODING</dd>
              <dd><a href="https://coding.net/" target="_blank">www.coding.net</a></dd>
            </dl>
            <dl>
              <dt><img src="<%=path%>/front/images/ycpai.png"/></dt>
              <dd>缘创派</dd>
              <dd><a href="http://www.ycpai.com/" target="_blank">www.ycpai.com</a></dd>
            </dl>
            <dl>
              <dt><img src="<%=path%>/front/images/sobug.png"/></dt>
              <dd>SOBUG</dd>
              <dd><a href="https://sobug.com/" target="_blank">www.sobug.com</a></dd>
            </dl>
          </li>
        </ul>
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
