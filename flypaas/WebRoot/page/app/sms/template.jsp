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
	<title>flypaas开放平台管理中心——短信模板</title>
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
					<li class="active" onclick="href2url('<%=path%>/app/smsTemplate/query');">短信模板</li>
					<li onclick="href2url('<%=path%>/app/smsNum/query');">短信号码</li>
				</ul>
			</div>

			<!--说明state_box bof-->
			<div class="state_box">
				<h1>说明</h1>
				<p>1、每个短信模板只允许关联一个应用</p>
				<p>2、短信模板通过审核后才允许商用下发</p>
				<p>3、新增短信模板都需重新审核</p>
				<p>4、模板短信使用说明请阅读「开发文档」内说明</p>
			</div>
			<!--说明state_box eof-->

			<!--搜索search_box bof-->
			<div class="search_box">
				<div class="search app_search">
					<form id="queryForm" method="post" action="/app/smsTemplate/query">
						<input type="text" name="text" placeholder="应用名称/模板名称" value="<s:property value="#parameters.text"/>" />
						<input type="submit" value="" />
					</form>
				</div>
				<div class="search_link"><a href="<%=path %>/app/smsTemplate/edit" class="template">添加模板</a></div>
			</div>
			<!--搜索search_box eof-->

			<!--表格列表 table_box bof-->
			<div class="table_box">
				<table cellpadding="0" cellspacing="0" border="0">
					<thead>
						<tr>
							<th>应用名称</th>
							<th>模板ID</th>
							<th>标题</th>
							<th>类型</th>
							<th>创建时间</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
					    <s:if test="page.list!=null&&page.list.size()>0">
            				<s:iterator value="page.list" var="sms">
			          	   <tr>
			                <td>${sms.app_name}</td>
			                <td>${sms.template_id}</td>
			                <td>${sms.name}</td>
			                <td>
			                	<s:if test="#sms.type==1">
			                		云验证模板
			                	</s:if>
			                	<s:elseif test="#sms.type==4">
			                		验证码模板
			                	</s:elseif>
			                	<s:else>
			                		普通模板
			                	</s:else>
			                </td>
			                <td><s:date name="#sms.create_date" format="yyyy/MM/dd"/></td>
			                	<s:if test="#sms.status==1">
			                		<td class="orange">待审核</td>
			                	</s:if>
			                	<s:elseif test="#sms.status==2">
			                	<td class="green">已通过</td>
			                	</s:elseif>
			                	<s:elseif test="#sms.status==3">
			                		<td class="red">审核不通过</td>
			                	</s:elseif>
			                <td><a href="<%=path %>/app/smsTemplate/edit?templateId=<u:des3 value="${sms.template_id}"/>">编辑</a> 
			                | <a href="javascript:void(0);" onclick="popupBox('删除短信模板','是否删除短信模板','del(\'<u:des3 value="${sms.template_id}"/>\')',1)">删除</a></td>
			               </tr>
			          	</s:iterator>
			          </s:if> 
			          <s:else>
		              	<tr>
		              		<td>暂无数据！</td>
		              		<td>&nbsp;</td>
		              		<td>&nbsp;</td>
		              		<td>&nbsp;</td>
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