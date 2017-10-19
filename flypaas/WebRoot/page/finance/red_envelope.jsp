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
	<title>flypaas开放平台管理中心——红包账户</title>
	<%@include file="/page/resource.jsp"%>
</head>
<body id="03-1">
	<!--头部header bof-->
	<%@include file="/page/head.jsp" %>
	<!--头部header eof-->

	<!--主体content bof-->
	<div class="content">

		<!--侧边side bof-->
		<%@include file="/page/left.jsp" %>
		<!--侧边side bof-->
		<input type="hidden" value="<%=path%>" id="path_fo_js" />
		<!--侧边side bof-->

		<!--右侧main bof-->
		<div class="main">
		    <div class="breadcrumbs">
				<ul>
					<li><a href="javascript:;">财务管理</a></li>
					<li class="active"><a href="javascript:;">财务总览</a></li>
				</ul>
			</div>
		
			<div class="main_tab_tit">
				<ul>
					<li onclick="location.href='<%=path%>/finance/index'">账户信息</li>
					<li onclick="location.href='<%=path%>/user/feeConfig'">资费配置</li>
					<li class="active">红包账户</li>
				</ul>
			</div>
		    
		    <div class="state_box">
		      <p>1、该账户余额只做抵扣相应服务资费，并优先抵扣</p>
		    </div>
		    
		        
		        
            <!-- 当前套餐资费项 -->  
            <div class="table_box">
	            <table cellpadding="0" cellspacing="0" border="0" width="100%">
	              <tbody>
	                <tr>
	                  <th>业务类型</th>
	                  <th>余额</th>
	                  <th>生效时间</th>
	                  <th>失效时间</th>
	                  <th>状态</th>
	                </tr>
		            <s:if test="dataList!=null&&dataList.size()>0">
		          	  	<s:iterator value="dataList" id="data">
		                <tr>
		                      <td>
			                  	${data.event_name }
			                  </td>
			                  <td>
			                  	${data.balance }
			                  </td>
			                  <td>
			                  	<s:date name="#data.eff_date" format="yyyy-MM-dd"/>
			                  </td>
			                  <td>
			                  	<s:date name="#data.exp_date" format="yyyy-MM-dd"/>
			                  </td>
			                  <td>
			                  	<s:if test="#data.status==1">
			                  	正常
			                  	</s:if>
			                  	<s:else>
			                  	失效
			                  	</s:else>
			                  </td>
		                </tr>
		                </s:iterator>
		             </s:if>
		             <s:else>
		             	 <tr>
		                 <td>
		                 	暂无数据！
		                 </td>
		                 <td>
		                 	&nbsp;
		                 </td>
		                 <td>
		                 	&nbsp;
		                 </td>
		                 <td>
		                 	&nbsp;
		                 </td>
		                 <td>
		                 	&nbsp;
		                 </td>
		                 </tr>
		             </s:else>
	              </tbody>
	            </table>
		        </div>
		    </div>
		
		  </div>  
	
	<!--底部footer bof-->
	<%@include file="/page/foot.jsp" %>
	<!--底部footer eof-->
	<script type="text/javascript">
		$(function(){
			$(".table_box table tr:even").addClass("even");//表格变色
		});
	</script>
</body>
</html>