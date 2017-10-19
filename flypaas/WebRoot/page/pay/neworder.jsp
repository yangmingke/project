<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="u" uri="/flypaas-tags"%>
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
       <li><a href="<%=path %>/bill/chargeList">财务管理</a></li>
       <li class="active"><a href="javascript:void(0)">我的充值</a></li>
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
        <li><i class="num3">&nbsp;</i>确认订单</li>
        <li><i class="num4">&nbsp;</i>进行付款</li>
        <li><i class="num5">&nbsp;</i>充值完成</li>
      </ul>
    </div>

    <div class="recharge_form">
      <form action="/pay/newOrderSmt" method="post" id="newOrderFrm" name="newOrderFrm">
        <dl>
          <dt>当前余额：</dt>
          <dd>
	          <span class="green1">
	          		<span class="cur_money">￥
						<s:if test="acctBalance!=null">
							<s:property value="acctBalance.balance" />
						</s:if>
						<s:else>
				             0
				        </s:else>
					</span>
	          </span>元
          </dd>
        </dl>
        <dl>
          <dt>充值金额：</dt>
          <dd><input type="text"  placeholder="请输入" id="money" value="${chargeStr }" name="chargeStr"/>元&nbsp;&nbsp;<span class="error" id="money_error" style="display: none"></span></dd>
          <dd><span class="tips">* 请确保充值的金额足够应用使用，防止因余额不足导致应用被迫下线</span></dd>
        </dl>
        <dl>
          <dt>支付方式：</dt>
          <input type="hidden" id="vpayType" value="<u:des3 value='1' />"  name="chargeType"/>
          <input type="hidden" value="${accountType}"  name="accountType"/>
          <dd class="type"><a href="#" class="active"><em>&nbsp;</em><img src="<%=path %>/page/images/alipay.png" /></a></dd>
          <dd class="btn"><input type="button" value="充值" id="subt" /></dd>
        </dl>
      </form>
    </div>
    
    
  </div>  
  <!--右侧main bof-->   
</div>

<!--主体content eof--> 

<!--底部footer bof-->
<%@include file="/page/foot.jsp" %>
<!--底部footer eof-->


<script type="text/javascript">
		$(function() {
			$("#subt").click(function() {
				if (validate()) {
					$("#newOrderFrm").submit();
				}
			});
			
			$("#money").focus(function(){
				$("#radioMoney").parent().addClass("checked");
				$("#radioMoney").attr("checked","checked");
				$("#radioType").val(1);
			});

		});
		function validate() {
				var money = $("#money").val();
				if (!verfiyMoney(money)) {
					$("#money_error").text("请输入合法的金额");
					$("#money_error").fadeIn();
					return false;
				} else if (money > 1000000) {
					$("#money_error").text("请输入合法的金额，大于零小于一百万");
					$("#money_error").fadeIn();
					return false;
				} else {
					$("#money_error").hide();
				}
				return true;
		}
	</script>
</body>
</html>