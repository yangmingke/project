<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%@ taglib prefix="u"  uri="/flypaas-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="UTF-8"><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>flypaas开放平台管理中心——充值账单</title>
<%@include file="/page/resource.jsp"%>
<link rel="stylesheet" type="text/css" href="<%=path%>/js/jquery-ui/css/theme/all.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/js/jquery-ui/css/demos.css" />
<script type="text/javascript" src="<%=path%>/js/jquery-ui/js/core.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-ui/js/datepicker.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery-ui/js/widget.js"></script>
<script type="text/javascript" src="<%=path%>/js/datepicker.js"></script>
</head>
<body id="03-2">
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
        <li><a href="<%=path %>/bill/chargeList">财务管理</a></li>
        <li class="active"><a href="javascript:void(0)">我的充值</a></li>
      </ul>
    </div>
    <div class="main_tab_tit">
      <ul>
        <li class="active">充值账单</li>
        <li onclick="location.href='<%=path %>/pay/newOrder'">在线充值</li>
        <%-- <li onclick="location.href='<%=path %>/pay/couponPage'">兑换码</li> by yangmingke--%>
      </ul>
    </div>
    
    <div class="bill_search">
      <s:form theme="simple" namespace="/bill" action="chargeList" method="post" name="form" id="form">
        <ul>
          <li>
            <label>时间范围：</label>
            <input type="text" value="${paymentOrder.beginDate}" name="paymentOrder.beginDate" id="beginDate"/><span>至</span> &nbsp;<input type="text" value="${paymentOrder.endDate}" name="paymentOrder.endDate" id="endDate" />
            <label>订单号：</label>
            <input type="text" name="orderId" placeholder="订单号" value="${orderId }"/><input type="hidden" id="currentPage" name="page.currentPage"/>
            <input type="submit" value="查询" />
          </li>
     	</ul>
      </s:form>
    </div>

    <div class="table_box">
        <table cellpadding="0" cellspacing="0" border="0">
          <thead>
            <tr>
              <th>订单号</th>
              <th>订单时间</th>
              <th>支付方式</th>
              <th>金额</th>
              <th>状态</th>
              <th>操作</th>
            </tr>
          </thead>
          <tbody>
          	<s:if test="page.list.size()>0">
      		<s:iterator value="page.list" var="pay">
	          	  <tr><td>${pay.order_id }</td>
			      <td><s:date name="#pay.create_date" format="yyyy/MM/dd HH:mm:ss"/> </td>
			      <td>
			      	<s:if test="#pay.charge_type==\"1\"">
			      		支付宝
			      	</s:if>
			      	<s:elseif test="#pay.charge_type==\"6\"">
			      		中国银联
			      	</s:elseif>
			      	<s:elseif test="#pay.charge_type==\"B2\"">
			      		保障金账户转账
			      	</s:elseif>
			      	<s:elseif test="#pay.charge_type==\"B3\"">
			      		代金券充值
			      	</s:elseif>
			      </td>
			      <td>¥${pay.charge }</td>
			      
			      	<s:if test="#pay.status==1">
			      		<td class="red">
			      		未支付
			      		</td>
			      	</s:if>
			      	<s:if test="#pay.status==2">
			      		<td class="orange">
			      		订单已提交
			      		</td>
			      	</s:if>
			      	<s:if test="#pay.status==3">
			      		<td>
			      		支付成功
			      		</td>
			      	</s:if>
			      	<s:if test="#pay.status==4">
			      		<td class="red">
			      		支付失败
			      		</td>
			      	</s:if>
			      	<s:if test="#pay.status==5">
			      		<td class="red">
			      		已关闭
			      		</td>
			      	</s:if>
			      <td class="blue">
			      	<s:if test="#pay.status==1">
			      		<a  href="<%=path %>/pay/updateOrder?orderId=<u:des3 value='${pay.order_id }'/>">重新支付</a>
			      	</s:if>
			      </td>
			      </tr>
            </s:iterator>
            </s:if>
            <s:else>
            <tr>
            <td>暂无数据！</td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            </tr>
            </s:else>
          </tbody>
        </table>
      </div>

      <!--分页pagebox bof-->
        <!--表格变色-->
        <script type="text/javascript">
        $(function(){
          $(".table_box table tr:even").addClass("even");
        })
        </script>
        <!--分页pagebox eof-->
    
   <u:page page="${page}" formId="form" ></u:page>
  </div>  
  <!--右侧main bof-->   
</div>

<!--主体content eof--> 

<!--底部footer bof-->
<%@include file="/page/foot.jsp" %>
<!--底部footer eof-->
</body>
</html>