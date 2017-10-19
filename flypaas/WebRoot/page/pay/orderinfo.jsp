<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
<%@ taglib prefix="u"  uri="/flypaas-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="UTF-8"><meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>flypaas开放平台管理中心——在线充值</title>
<%@include file="/page/resource.jsp"%>
</head>
<body id="03-2">
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
        <li class="active">在线充值</li>
        <%-- <li onclick="location.href='<%=path %>/pay/couponPage'">兑换码</li> --%>
      </ul>
    </div>
    
    <div class="recharge_step">
      <ul>
        <li class="current"><i class="num1">&nbsp;</i>输入金额</li>
        <li class="current"><i class="num2">&nbsp;</i>选择支付方式</li>
        <li class="current"><i class="num3">&nbsp;</i>确认订单</li>
        <li><i class="num4">&nbsp;</i>进行付款</li>
        <li><i class="num5">&nbsp;</i>充值完成</li>
      </ul>
    </div>

    <div class="table_box recharge_list">
        <table cellpadding="0" cellspacing="0" border="0">
          <thead>
            <tr>
              <th>订单号</th>
              <th>开发者账户</th>
              <th>充值金额</th>
              <th>支付方式</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>${order.orderId }</td>
              <td>${userName }</td>
              <td>￥${order.charge }元</td>
              <td>
              	<s:if test="order.chargeType==1">
              		支付宝
              	</s:if>
              </td>
            </tr>
          </tbody>
        </table>
        
        <div class="btn">
	        <s:if test="order.status==2||order.status==3">
	          <p>恭喜您，您已成功完成充值步骤，如款项未即时到账，请稍后查询</p>
	          <p><input type="button" value="继续充值" class="confirm_btn" id="cr_order"/></p>
	        </s:if>
	        <s:else>
	          <p><input type="button" value="上一步" class="prev_btn" id="back"/><input type="button" value="付款" class="confirm_btn" id="confirm_btn"/></p>
	          <p><span class="tips">* 点击「付款」将在新窗口打开第三方充值页面，完成充值后可在当前窗口查看到帐状态</span></p>
	        </s:else>
        </div>
      </div>
    
    
  </div>  
  <!--右侧main bof-->   
</div>



<!--弹层（在线充值提醒） bof-->
  <div class="background_box" style="display:none">&nbsp;</div>
  <div class="float_box recharge_box" style="display:none">
    <div class="float_tit">
      <h1>在线充值</h1>
    </div>
    <div class="float_ctn">
      <p><b>您在第三方支付平台进行在线转账操作</b></p>
      <p><span class="tips">如转账充值遇到问题，请在充值账单内查询订单状态</span></p>
      <p><span class="tips">如未完成您可以继续完成操作</span></p>
      <div class="float_btn">
        <input type="button" value="遇到支付问题" class="cancel_btn" id="cal_box"/>
        <input type="button" value="完成充值" class="confirm_btn" id="confirm_box"/>
      </div>
    </div>
  </div>
  <!--弹层（在线充值提醒） eof-->
  
  
<!--主体content eof--> 

<!--底部footer bof-->
<%@include file="/page/foot.jsp" %>
<!--底部footer eof-->

<script type="text/javascript">
	$(function(){
		 $("#confirm_btn").click(function(){
			 var orderId = "<u:des3 value='${order.orderId }'/>";
	         ajaxsmt(orderId);
	     });
		 $("#cal_box").click(function(){
			 location.href="<%=path%>/bill/chargeList";
		 });
		 $("#confirm_box").click(function(){
			 location.href=location.href;
		 });
		 $("#cr_order").click(function(){
			 location.href="<%=path%>/pay/newOrder";
		 });
		 $("#back").click(function(){
			 location.href="<%=path%>/pay/newOrder";
		 });
	});
	function ajaxsmt(orderId){
		$(".background_box").show();
		$(".float_box").show();
		post("<%=path%>/pay/toPay",{orderId:orderId},"_blank");
	}
	function toTherPay(){
		 var orderId = "${order.orderId }";
		 post("<%=path%>/pay/updateOrder",{orderId:orderId});
	}
</script>
</body>
</html>