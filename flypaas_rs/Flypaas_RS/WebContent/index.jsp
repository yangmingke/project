<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="overflow-y:hidden;">
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<LINK rel="Bookmark" href="favicon.ico" >
<LINK rel="Shortcut Icon" href="favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="http://libs.useso.com/js/html5shiv/3.7/html5shiv.min.js"></script>
<script type="text/javascript" src="http://libs.useso.com/js/respond.js/1.4.2/respond.min.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/css3pie/2.0beta1/PIE_IE678.js"></script>
<![endif]-->
<link href="${ctx}/css/H-ui.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet" href="${ctx}/font/font-awesome.min.css"/>
<link type="text/css" rel="stylesheet" href="${ctx}/css/menu/menu.css"/>
<script type="text/javascript" src="${ctx}/js/login/login.js"></script>
<script type="text/javascript" src="${ctx}/js/menu/menu.js"></script>
<link type="text/css" rel="stylesheet" href="${ctx}/css/1.0.8/iconfont.css"/>
<!--[if IE 7]>
<link href="http://www.bootcss.com/p/font-awesome/assets/css/font-awesome-ie7.min.css" rel="stylesheet" type="text/css" />
<![endif]-->
<!--[if IE 6]>
<script type="text/javascript" src="js/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>快传技术有限公司</title>
<meta name="keywords" content="H-ui.admin v2.0,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
<meta name="description" content="H-ui.admin v2.0，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body style="overflow:hidden">
<header class="Hui-header cl"> 
<a class="Hui-logo l" title="快传技术有限公司资源开放平台" href="${ctx}/menuController/queryMenu.action" style="font-family: cursive; font-size: 18px;">快传技术有限公司资源开放平台V1.0</a> 
<span class="Hui-userbox"><span class="c-white">用户：${userSession.username}</span> 
<a class="btn btn-danger radius ml-10" href="/userController/userExit.action" title="退出" ><i class="icon-off"></i> 退出</a></span> 

<a aria-hidden="false" class="Hui-nav-toggle" id="nav-toggle" href="#"></a> </header>
<div class="cl Hui-main">
  <aside class="Hui-aside" style="">
    <input runat="server" id="divScrollValue" type="hidden" value="" />
    <div class="menu_dropdown bk_2">
    <c:forEach var="menu1" items="${userMenu1}">
    <dl class="">
    	<dt> <h4 onclick="showMenu2(this.id)"  id="${menu1.menuId}" class="menu1 ${menu1.menuClass}">  ${menu1.menuName}</h4><b></b></dt>
    	<div name="${menu1.menuId}" style="display: none;">
	    	<c:forEach var="menu2" items="${userMenu2}">
	    		<c:if test="${menu2.parentId == menu1.menuId}">
	    			<div><a _href="${ctx}/${menu2.menuUrl}" href="javascript:void(0);" class="menu2" id="${menu2.menuId}" >${menu2.menuName}</a> </div>
	    		</c:if>
	    	</c:forEach>
    	</div>
    </dl>
    </c:forEach>	
    </div>
  </aside>
  <div class="dislpayArrow"><a class="pngfix" href="javascript:void(0);"></a></div>
  <section class="Hui-article">
    <div id="Hui-tabNav" class="Hui-tabNav">
      <div class="Hui-tabNav-wp">
        <ul id="min_title_list" class="acrossTab cl">
          <li class="active"><span title="我的消息" data-href="welcome.jsp">我的消息</span><em></em></li>
        </ul>
      </div>
      <div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default btn-small" href="javascript:;"><i class="icon-step-backward"></i></a><a id="js-tabNav-next" class="btn radius btn-default btn-small" href="javascript:;"><i class="icon-step-forward"></i></a></div>
    </div>
    <div id="iframe_box" class="Hui-articlebox">
      <div class="show_iframe">
        <div style="display:none" class="loading"></div>
        <iframe scrolling="yes" frameborder="0" src="${ctx}/welcome.jsp">
        </iframe>
      </div>
    </div>
  </section>
</div>
<script type="text/javascript" src="${ctx}/js/jquery.min.1.8.1.js"></script>
<script type="text/javascript" src="${ctx}/js/H-ui.js"></script> 
<script type="text/javascript" src="${ctx}/js/H-ui.admin.js"></script> 
</body>
</html>