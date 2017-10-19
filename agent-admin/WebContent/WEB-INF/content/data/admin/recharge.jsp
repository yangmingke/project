<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>代理商账户 - 生成订单</title>
	<script type="text/javascript" src="${ctx}/js/hex_ha_ha_ha_ha_ha.js"></script>
	<style type="text/css">
		.active{
			border:1px #79b547 solid;
		}
	</style>
</head>
<body>
      <div class="main_ctn">
        <h1>代理商账户 - 生成订单</h1>
        <div class="admin_material material_form">
          <form method="post" id="newOrderFrm" action="/admin/newOrderSmt">
            <p>
              <label>平台账号</label>
              <span>${data.email}</span>
              <input type="text" name="email" id="email" value="${data.email}" hidden="hidden"/>
            </p>
            <p>
              <label>平台余额</label>
              <span>${data.balance}</span>
            </p>
            <p>
              <label>充值额度</label>
              <input type="text" name="charge" id="charge" maxlength="20" onkeyup="this.value=this.value.replace(/[^0-9]/g,'')"/>元
            </p>
            <p>
              <label>支付类型</label>
              <a>
              	<img alt="支付宝"  class="active payType" src="${ctx}/images/alipay.png" id="alipay">
              </a>
              <%-- <a>
              	<img alt="支付宝1" class="payType" src="${ctx}/images/alipay.png">
              </a>	 --%>
            </p>
            <input hidden="hidden" id="chargeType" name="chargeType">
			<p><span id="msg" class="error" style="display:none;"></span></p>
            <hr class="hr" /> 
            <p class="btn">
            	<input type="hidden" name="sid" value="${data.sid}"/>
				<input type="button" value="提交订单" class="org_btn" onclick="save(this)"/>
				<input type="button" value="取 消" class="grey_btn" onclick="window.location.href='/admin/view'"/>
            </p>
          </form>
        </div>
      </div>

<script type="text/javascript">
var validate;

$(function(){
	$('.payType').click(function(){
		$('.payType').removeClass("active");
		$(this).addClass("active");
	});
	
});

//充值
function save(btn){
	$(btn).attr("disabled", true);
	$("label.error, span.error").hide();
	var charge = $('#charge').val();
	if(verfiyMoney(charge)){
		var payType = $(".active:first").attr('id');
		if(payType == "alipay"){
			$('#chargeType').val('1');
		}
		$("#newOrderFrm").submit();
	}else{
		$(btn).attr("disabled", false);
		$("span.error").text("请输入合法的金额").show();
	}
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