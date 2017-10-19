<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="UTF-8"><meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<title>flypaas开放平台管理中心——Demo应用信息</title>
	<%@include file="/page/resource.jsp"%>
</head>
<body id="02-2">
	<!--头部header bof-->
	<%@include file="/page/head.jsp" %>
	<!--头部header eof-->

	<!--主体content bof-->
	<div class="content">

		<!--侧边side bof-->
		<%@include file="/page/left.jsp" %>
		<!--侧边side bof-->

		<!--右侧main bof-->
		<div class="main">
			<div class="breadcrumbs">
				<ul>
					<li><a href="<%=path %>/app/appManager">应用管理</a></li>
					<li class="active"><a href="javascript:void(0)">测试DEMO</a></li>
				</ul>
			</div>

			<div class="main_tab_tit">
				<ul>
					<li class="active">Demo应用信息</li>
					<!--<li onclick="location.href='<%=path %>/app/testMobile'" style="cursor: pointer;">测试号码白名单</li>-->
				</ul>
			</div>

			<!--说明state_box bof-->
			<div class="state_box">
				<h1>提醒</h1>
				<p>1、测试DEMO应用是给客户提供快捷体验快传融合通讯开放平台基本功能的测试应用，不可以创建Client账户，外呼及短信只可以使用已认证号码。</p>
				<p>2、如需涉及到鉴权需要与快传进行交互的业务，需要自建应用进行。</p>
				<p>3、提供的测试帐号可以在下载的Demo 内配置，以供测试使用，严禁将Demo用于生产服务。</p>
				<p>4、在该帐号测试情况下将会产生正常资费。</p>
				<p>5、测试DEMO的使用请查看相关文档。</p>
			</div>
			<!--说明state_box eof-->

			<div class="search_box">
				<div class="download"><a href="<%=path%>/file/downLoad">demo下载</a></div>
			</div>
			<div class="item_box demo_list">
				<!--<div class="item_left">-->
					<div class="table_box">
						<table cellpadding="0" cellspacing="0" border="0">
							<tbody>
							  <s:if test="clientList!=null">
            					 <s:iterator value="clientList" var="c" status="index">
									<tr>
										<td class="green">Client 账户${index.index+1 }</td>
										<td>Client帐号：${c.clientNumber }</td>
										<td>密码： ${c.clientPwd }</td>
										<td>绑定号码： ${c.mobile }</td>
									</tr>
								</s:iterator>
								</s:if>
							</tbody>					
						</table>
					</div>
				<!--</div>-->

<!-- 				<div class="item_right"> -->
<!-- 					<div class="box"> -->
<!-- 						<h2 class="green">开发者账号信息</h2> -->
<!-- 						<p>Account Sid：<br /> -->
<%-- 							${user.sid } --%>
<!-- 						</p> -->
<!-- 						<p>Rest URL: <br /> -->
<%-- 							<s:iterator value="paramsList" var="p"> --%>
<%-- 				                <s:if test="#p.paramKey==1"> --%>
<%-- 				                	${p.paramValue} --%>
<%-- 				                </s:if> --%>
<%-- 			                </s:iterator> --%>
<!-- 						</p> -->
<!-- 					</div> -->
<!-- 				</div> -->
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