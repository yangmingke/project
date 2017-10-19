<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="UTF-8"><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>flypaas开放平台管理中心——兑换码</title>
<%@include file="/page/resource.jsp"%>
</head>
<body>
<!--头部header bof-->
<%@include file="/page/head.jsp"%>
<!--头部header eof--> 

<!--主体content bof-->
<div class="content"> 
  
  <!--侧边side bof-->
  <%@include file="/page/left.jsp"%>
  <!--侧边side bof--> 
  
  <!--右侧main bof-->
  <div class="main">
    <div class="breadcrumbs">
      <ul>
        <li><a href="#">财务管理</a></li>
        <li class="active"><a href="#">我的充值</a></li>
      </ul>
    </div>
    <div class="main_tab_tit">
      <ul>
        <li onclick="location.href='<%=path %>/bill/chargeList'">充值账单</li>
        <li onclick="location.href='<%=path %>/pay/newOrder'" >在线充值</li>
        <li class="active">兑换码</li>
      </ul>
    </div>
    
    <div class="code_box">
      <p>恭喜您，您已成功兑换!</p>
      <p class="money"><label>充值金额：</label><span class="green1">${couponMoney }</span>元</p>
      <p><input type="button" value="继续兑换" onclick="location.href='<%=path %>/pay/couponPage'"/></p>
    </div>
    
  </div>  
  <!--右侧main bof-->   
</div>

<!--主体content eof--> 

<!--底部footer bof-->
<%@include file="/page/foot.jsp" %>
<!--底部footer eof-->
</body>
</html>