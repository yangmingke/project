<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%@ taglib prefix="u" uri="/flypaas-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>新闻中心_快传融合通讯开放平台</title>
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
        <li class="active"><a href="<%=path%>/about/news"><i class="news">&nbsp;</i>新闻中心</a></li>
        <li><a href="<%=path%>/about/partners"><i class="news">&nbsp;</i>合作伙伴</a></li>
        <li><a href="<%=path%>/about/cooperation"><i class="cooperation">&nbsp;</i>联系我们</a></li>
      </ul>
    </div>
    <div class="about_txt">
      <div class="display_tit">
        <h1><span class="news">新闻中心</span></h1>
      </div>
      <div class="display_ctn news_list">
        <ul>
         <s:if test="page.list!=null">
              	<s:iterator value="page.list" id="r">
              			 <li>
				            <h3><a href="<%=path %>/about/newsInfo?newsId=${r.news_id}"><s:property value="#r.title"/></a></h3>
				            <p><s:property value="#r.summary"/>...</p>
				            <p class="date"><s:date name="#r.create_date" format="yyyy-MM-dd"/></p>
				          </li>
              	</s:iterator>
          </s:if>
          <s:form namespace="/about" action="news" id="newsFrm" theme="simple">
          <input type="hidden" id="currentPage" name="page.currentPage"/>
          </s:form>
           <u:page page="${page}" formId="newsFrm" ></u:page>
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
