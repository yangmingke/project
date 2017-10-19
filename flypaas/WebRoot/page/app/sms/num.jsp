<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%@ taglib prefix="u" uri="/flypaas-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="UTF-8"><meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<title>快传开放平台管理中心——短信号码</title>
	<%@include file="/page/resource.jsp"%>
	<script type="text/javascript">
		function del(templateId){
			 location.href = '<%=path%>/app/smsTemplate/delete?templateId=' + templateId;
		}
		<!--表格变色-->
		$(function(){
			$(".table_box table tr:even").addClass("even");
		})
	</script>
</head>
<body id="02-3">
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
					<li><a href="#">应用管理</a></li>
					<li class="active"><a href="#">短信管理</a></li>
				</ul>
			</div>
			<div class="main_tab_tit">
				<ul>
					<li onclick="location.href='<%=path%>/app/smsTemplate/query';">短信模板</li>
					<li class="active" onclick="location.href='<%=path%>/app/smsNum/query';">短信号码</li>
				</ul>
			</div>

			<!--说明state_box bof-->
			<div class="state_box">
				<h1>说明</h1>
				<p>1、短信号码为每创建一个应用系统自动分配一个短信端口号码</p>
				<p>2、因受政策限制，已分配的短信端口号码某些时候不可用</p>
			</div>
			<!--说明state_box eof-->

			<!--搜索search_box bof-->
			<div class="search_box">
				<div class="search app_search">
					<form id="queryForm" method="post" action="/app/smsNum/query">
						<input type="text" name="text" placeholder="应用名称" value="<s:property value="#parameters.text"/>" />
						<input type="submit" value="" />
					</form>
				</div>
			</div>
			<!--搜索search_box eof-->

			<!--表格列表 table_box bof-->
			<div class="table_box">
				<table cellpadding="0" cellspacing="0" border="0">
					<thead>
						<tr>
							<th>短信端口号码</th>
							<th>关联应用</th>
							<th>创建时间</th>
						</tr>
					</thead>
					<tbody>
					    <s:if test="page.list!=null&&page.list.size()>0">
            				<s:iterator value="page.list" var="sms">
			          	   <tr>
				                <td>${sms.sms_msg_nbr}</td>
				                <td>${sms.app_name}</td>
				                <td><s:date name="#sms.create_date" format="yyyy/MM/dd"/></td>
			               </tr>
			          	</s:iterator>
			          </s:if> 
			          <s:else>
		              	<tr>
		              		<td>暂无数据！</td>
		              		<td>&nbsp;</td>
		              		<td>&nbsp;</td>
		              	</tr>
		              </s:else> 
					</tbody>
				</table>
			</div>
			<!--表格列表 table_box eof-->

			<!--分页pagebox bof-->
			 <u:page page="${page}" formId="queryForm"></u:page>
			<!--分页pagebox eof-->
		</div>
		<!--右侧main bof-->
	</div>
	<!--主体content eof-->
	<!--底部footer bof-->
	<%@include file="/page/foot.jsp" %>
	<!--底部footer eof-->
</body>
</html>