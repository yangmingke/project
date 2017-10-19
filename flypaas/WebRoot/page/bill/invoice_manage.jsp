<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%@ taglib prefix="u"  uri="/flypaas-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="UTF-8"><meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<title>flypaas开放平台管理中心——发票管理</title>
	<%@include file="/page/resource.jsp"%>
	<script type="text/javascript" src="<%=path%>/js/jquery-1.8.2.js"></script>
	<script type="text/javascript" src="<%=path%>/js/Highcharts-4.0.1/highcharts.js" ></script>
	<script type="text/javascript" src="<%=path%>/js/Highcharts-4.0.1/modules/exporting.js" ></script>
	<script type="text/javascript" src="<%=path%>/js/form.js"></script>
	<script type="text/javascript" src="<%=path%>/js/md5.js"></script>
</head>
<body id="03-4">
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
					<li class="active"><a href="javascript:;">发票管理</a></li>
				</ul>
			</div>
		    <div class="main_tab_tit">
		      <ul>
		        <li class="active">发票管理</li>
		      </ul>
		    </div>
		    
		    <!--说明state_box bof-->
		    <div class="state_box">
		      <p>* 申请开票金额1000元以上可开具增值税专票，开票金额100元以上可开具增值税普票（国税发票）</p>
		      <p>* 官方活动赠送金额不计算在开票金额内</p>
		    </div>
		    <!--说明state_box eof-->
		    
		      <div class="table_box invoice_box">
		        <div class="table_relate relate_invoice">
		          <ul>
		            <li class="li2">
		              <dl>
		                <dt>可开票金额：</dt>
		                <dd><span class="green1">￥${invoiceMoney}</span>元<span class="tips">* 通过活动兑换码充值进账户及已充值的保证金无法开具发票</span></dd>
		              </dl>
		              <dl class="relate_link"><a href="<%=path %>/bill/addInvoicePage">开具发票</a></dl>
		            </li>
		          </ul>
		        </div>
		        <table cellpadding="0" cellspacing="0" border="0">
		          <thead>
		            <tr>
		              <th>开票时间</th>
		              <th>开票金额(元)</th>
		              <th>开票类型</th>
		              <th>开票抬头</th>
		              <th>状态</th>
		              <th>操作</th>
		            </tr>
		          </thead>
		          <tbody>
		          	<s:if test="page.list!=null&&page.list.size()>0">
	              	<s:iterator value="page.list" var="inl">
		              <tr>
		                <td>${inl.create_date}</td>
		                <td>${inl.money}</td>
		                <td>
		                	<s:if test="#inl.type==1">
		                		增值税专票
		                	</s:if>
		                	<s:elseif test="#inl.type==2">
		                		增值税普票
		                	</s:elseif>
		                </td>
		                <td>${inl.title}</td>
		                <td ${inl.status==4 ? "class='red'" : ""}>
		                	<s:if test="#inl.status==0">
		                		已删除
		                	</s:if>
		                	<s:elseif test="#inl.status==1">
		                		待受理
		                	</s:elseif>
		                	<s:elseif test="#inl.status==2">
		                		开票中
		                	</s:elseif>
		                	<s:elseif test="#inl.status==3">
		                		已邮寄
		                	</s:elseif>
		                	<s:elseif  test="#inl.status==4">
		                		审核未通过
		                	</s:elseif>
		                </td>
		                <td class="blue">
			                <s:if test="#inl.status==0 || #inl.status==1 || #inl.status==4">
			                	<a href="<%=path %>/bill/cacelInvoice?invoiceId=<u:des3 value='${inl.id }'/>">撤销删除</a>
			                </s:if>
			                <s:else>
				                <a class="view" href="javascript:;" logistics_company="${inl.logistics_company}" logistics_no="${inl.logistics_no}" >查看</a>
			                </s:else>
		                </td>
		              </tr>
		              </s:iterator>
	              </s:if>
	              <s:else>
	              	 <tr><td colspan="6">暂无数据！</td></tr>
	              </s:else>
		          	
		          </tbody>
		        </table>
		      </div>
		      <form method="post" id="queryForm" action="<%=path %>/bill/invoice"></form>
		      <u:page page="${page}" formId="queryForm" ></u:page>
				
		      <!--查看快递 bof-->
		      <div class="view_box">
		        <em>&nbsp;</em>
		        <p>快递号：</p>
		      </div>
		      <!--查看快递 eof-->
		
		  </div>  
		<!--右侧main bof-->

	</div>
	<!--主体content eof-->
	
	<!--底部footer bof-->
	<%@include file="/page/foot.jsp" %>
	<!--底部footer eof-->

<script type="text/javascript">
$(function(){
	$(".table_box table tr:even").addClass("even");//表格变色
	
	//查看view
	var w = $(window).width();
	var w0 = $(".invoice_box").width();
	var left0 = $(".invoice_box").position().left;
	var w1 = w-w0-left0; //查看快递右边距
	$(".invoice_box .view").hover(function(){
		var logistics_company = $(this).attr("logistics_company");
		var logistics_no = $(this).attr("logistics_no");
		$(".view_box p").text("快递号："+logistics_no+"（"+logistics_company+"）");
		
		var h1 = $(this).position().top-42;
		$(".view_box").show();
		$(".view_box").css({"right":w1,"top":h1});
	},function(){
		$(".view_box").hover(function(){
			$(".view_box").show();
		},function(){
			$(".view_box").hide();
		});
	});
});
</script>
</body>
</html>