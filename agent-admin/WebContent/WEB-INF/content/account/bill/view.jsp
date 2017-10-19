<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>账单信息/查看</title>
</head>

<body>
	<h1><a href="javascript:;" class="back" onclick="back()">返 回</a>账单信息/查看</h1>
    <div class="main_ctn1 account_detail">
    <ul class="ul_left">
	    <li><label>当前账户</label><span>${data.email}</span></li>
	    <li><label>订单号</label><span>${data.order_id}</span></li>
	    <li><label>业务金额</label><span class="money">￥${data.charge_fee}</span></li>
	    <li><label>支付方式</label><span><u:ucparams key="${data.charge_type}" type="charge_type"/></span></li>
	    <li><label>订单状态</label><span><u:ucparams key="${data.status}" type="payment_order_status"/></span></li>
	    <li><label>发生时间</label><span>${data.create_date}</span></li>
    </ul>
    <div class="clear"></div>
    <hr class="hr" />
    </div>
</body>
</html>