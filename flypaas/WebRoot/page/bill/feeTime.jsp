<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%@ taglib prefix="u"  uri="/flypaas-tags" %>
<%
String path = request.getContextPath();
request.setAttribute("path", path);
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="UTF-8"><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>flypaas开放平台管理中心——消费记录</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/js/jquery-ui/css/theme/all.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/js/jquery-ui/css/demos.css" />
<%@include file="/page/resource.jsp"%>
<script type="text/javascript" src="<%=path%>/js/jquery-ui/js/core.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-ui/js/datepicker.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-ui/js/widget.js"></script>
</head>

<body id="03-3">
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
		        <li><a href="#">财务管理</a></li>
		        <li class="active"><a href="#">消费记录</a></li>
		      </ul>
		    </div>
		    <div class="main_tab_tit">
		      <ul>
		        <li class="active">消费记录</li>
		      </ul>
		    </div>

	 <div class="bill_search">
	        <form action="${path}/bill/feeTime" id="appform"  name="appform">
	        <ul>
	          <li class="li1">
	            <label>时间范围：</label>
               		<input type="text" name="datetime" id="datetime" value="${datetime}"/><span>  
               		<input type="submit" value="查 询" class="org_btn"/>
	          <div class="select_box select_app">
	          	<label>选择应用：</label>
		          <div input="appSid" tabindex="2" class="select"  defaultValue="${appSid}">	
					<span>所有应用<i>&nbsp;</i></span>
						<ul>
							<li val="">所有应用</li>
							<s:if test="appList!=null">
		                		<s:iterator value="appList" var="app">
		                		  	<li val="${app.appSid}">${app.appName}</li>
		                		</s:iterator>
	               			</s:if>
	               		</ul>
				</div>
	          </div>
	            <br />
	            <span class="tips"> </span>
	          </li>
	      </ul>
	      </form>
	    </div>
	    
		<div style="font-size: 14px;">日总消费：<font color="green" style="font-size: 18px">${dayTotalFee}</font> 元</div>
	    <!--消费记录 consumption_list bof-->
		<div class="table_box">
			<table cellpadding="0" cellspacing="0" border="0">
				<thead>
					<tr>
						<th width="50px">序号</th>
						<th>应用</th>
		                <th>时长（S）</th>
		                <c:if test="${user.feeType==101}" >
		              		<th>费率（分/G）</th>
		                </c:if>
		                <c:if test="${user.feeType==102}" >
		              		<th>费率（分/min）</th>
		                </c:if>
		                <th>费用（分）</th>
		                <th>入流量（MB）</th>
		                <th>出流量（MB）</th>
		                <th>开始时间</th>
		                <th>结束时间</th>
					</tr>
				</thead>
				<tbody>
					 <s:if test="page!=null&&page.list.size()>0">
			     		 <s:iterator value="page.list" var="fee">
							<tr>
						      	<td>${fee.rownum}</td>
						      	<td>${fee.appName}</td>
	            				<td>${fee.feetime}</td>
				            	<td>${fee.userfeerate}</td>
				            	<td>${fee.userfee}</td>
				            	<td>${fee.total_traffic_in}</td>
				            	<td>${fee.total_traffic_out}</td>
				            	<td>${fee.starttime}</td>
				            	<td>${fee.endtime}</td>
					      	</tr>
						</s:iterator>
					</s:if>
					<s:else>
				      	<tr>
					      	<td>&nbsp;</td>
					      	<td>&nbsp;</td>
					      	<td>&nbsp;</td>
					      	<td colspan="2">暂无数据！</td>
					      	<td>&nbsp;</td>
					      	<td>&nbsp;</td>
					      	<td>&nbsp;</td>
				      	</tr>
			      </s:else>
				</tbody>
			</table>
		</div>
		<u:page page="${page}" formId="appform" ></u:page>
	    <!--消费记录 consumption_list eof-->
	  </div>  
	  <!--右侧main bof-->   
	</div>
	
	<!--主体content eof--> 
	
	<!--底部footer bof-->
	<%@include file="/page/foot.jsp" %>
	<!--底部footer eof-->
	
	<script type="text/javascript">
		$(function(){
			//表格隔行变色
			$(".table_box table tr:even").addClass("even");
		});
		
		function appCsm(){
			$('appform').submit();
		}		
		
		$("#datetime").datepicker({
			 dateFormat:"yy-mm-dd",
			 maxDate:0
		});
		
		if($("#datetime").val() == ''){
			$("#datetime").datepicker( 'setDate' , new Date());
		}
	</script>
</body>
</html>
