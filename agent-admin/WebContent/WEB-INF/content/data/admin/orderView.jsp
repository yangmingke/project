<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
	<title>代理商账户 - 充值</title>
	<script type="text/javascript" src="${ctx}/js/hex_ha_ha_ha_ha_ha.js"></script>
	<style type="text/css">
		.payTip{
			color: #a3a3a3;
			padding-left: 6px; 
		}
	
	</style>
</head>
<body>
      <div class="main_ctn">
        <h1>代理商账户 - 充值</h1>
        <div class="admin_material material_form">
            <form method="post" id="toPayForm" action="/admin/toPay" target="_blank">
            <p>
              <label>订单号号</label>
              <span>${data.orderId}</span>
          	  <input hidden="hidden" id="orderId" name="orderId" value="${data.orderId}">
            </p>
            <p>
              <label>平台账号</label>
              <span>${data.email}</span>
            </p>
            <p>
              <label>充值额度</label>
              <span class="money">￥</span><span class="money v_balance">${data.charge}元</span>
            </p>
            <p>
              <label>支付类型</label>
              	<span>
	              <c:if test="${data.chargeType == 1}">
	              	支付宝
	              </c:if>
              	</span>
            </p>
			<p><span id="msg" class="error" style="display:none;"></span></p>
            <hr class="hr" /> 
            <p class="btn">
				<input type="button" value="确认充值" class="org_btn" onclick="toPay()"/>
				<input type="button" value="取 消" class="grey_btn" onclick="back()"/>
            </p>
          </form>
        </div>
      </div>

	<!--弹层(冻结,解冻,注销) bof-->
	<div class="float_box account_box" id="enable_flag_box" style="display: none;">
		<div class="float_tit">
			<h3 class="enable_flag_mng">在线充值</h3>
		</div>
		<div class="float_ctn">
			<p>
				<span style="font-weight:bold;font-size:16px">您在第三方支付平台进行在线转账操作<span>
			</p>
			<p>
				<img src="${ctx}/images/circle.png"><span class="payTip">如转账充值遇到问题，请在充值账单内查询订单状态</span>
			</p>
			<p>
				<img src="${ctx}/images/circle.png"><span class="payTip">如未完成您可以继续完成操作</span>
			</p>
			<hr class="hr" />
			<p class="btn">
				<input type="button" value="遇到支付问题" onclick="updateEnableFlag('enable_flag_box');" /> 
				<input type="button" value="完成充值" class="cancel_btn grey_btn" onclick="hideBox('enable_flag_box')" /> 
			</p>
		</div>
	</div>

<script type="text/javascript">
$(function(){
	$('.payType').click(function(){
		$('.payType').removeClass("active");
		$(this).addClass("active");
	});
	
});

//充值
function toPay(){
	var boxId = "enable_flag_box";
	showBox(boxId);
	$('#toPayForm').submit();
}

function verfiyMoney(money){
//	var pattern = /^(\d|[1-9]\d+)(\.\d+)?$/ ; //包含小数
	var pattern = /^[1-9]\d*$/;
	var isright= pattern.test(money);
	return isright;
}
</script>
</body>
</html>