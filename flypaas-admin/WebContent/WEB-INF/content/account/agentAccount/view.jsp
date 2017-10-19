<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>代理商账务明细</title>
</head>

<body>
	<h1><a href="javascript:;" class="back" onclick="back2query('${ctx}/agentAccount/query','_dev_bill_form');">返 回</a>代理商账务信息/代理商账务明细</h1>
    <div class="main_ctn1 account_detail">
    <ul class="ul_left">
    <li><label>钱包编号</label><span>${data.account.acct_id}</span></li>
    <li><label>代理商账号</label><span class="v_data" tit="v_email">${data.account.email}</span></li>
    <li><label>手机号码</label><span>${data.account.mobile}</span></li>
    <li><label>开户时间</label><span>${data.account.create_time}</span></li>
    <li><label>钱包状态</label><span><u:ucparams key="${data.account.wallet_status}" type="wallet_status"/></span></li>
    <li><label>钱包余额（元）</label><span class="money">￥${data.account.balance}</span></li>
     <li><label>信用额度（元）</label><span class="money">￥</span><input type="text" id="credit_balance_text" class="txt_127" value="${data.account.credit_balance_fmt}">
      	<%-- <u:authority menuId="243"> --%>
	      	<input type="button" onclick="saveCreditBalance('${data.account.sid}','${data.account.acct_id}');" value="保 存" class="save_btn"/>
      	<%-- </u:authority> --%>
      </li>
    <li><label>套餐类型</label><span>${data.account.package_name}</span></li>
    <li class="account_link">
		<s:if test="data.account.wallet_status==0 ||data.account.wallet_status==1 ||data.account.wallet_status==2"><%--1 正常 --%>
			<%-- <u:authority menuId="55"> --%>
				<a href="javascript:;" class="org_btn float_lf" id="rechareg_link"  onclick="balanceBox('span.v_data','充值')">充值</a>
			<%-- </u:authority>
			<u:authority menuId="56"> --%>
				<a href="javascript:;" class="grey_btn float_lf" id="gifts_link"  onclick="balanceBox('span.v_data','赠送')">赠送</a>
			<%-- </u:authority>
			<u:authority menuId="57"> --%>
				<a href="javascript:;" class="grey_btn float_lf" id="deduction_link" onclick="balanceBox('span.v_data','扣费')">扣费</a>
			<%-- </u:authority>
			<u:authority menuId="58"> --%>
				<!-- <a href="javascript:;" class="grey_btn float_lf" id="bill_link" onclick="billBox()">出账单</a> -->
			<%-- </u:authority>
			<u:authority menuId="59"> --%>
				<%-- <a href="javascript:;" class="grey_btn float_lf" id="package_link"  onclick="transferBox('span.v_data','配置套餐', '${data.account.package_id}')">配置套餐</a> --%>
			<%-- </u:authority> --%>
		</s:if>
		<span class="v_data" tit="v_sid"  style="display: none;">${data.account.sid}</span>	
		<span class="v_data" tit="v_package_id"  style="display: none;">${data.account.package_id}</span>	
	</li>
    </ul>
    <ul class="ul_right">
    <li class="tit">操作记录</li>
    <li>
    <table class="noEdge">
	    <tbody>
		    <tr><th>序号</th><th>订单号</th><th>操作日期</th><th>金额</th><th>操作类型</th></tr>
		    <s:iterator value="data.recharge_page.list">
			    <tr>
			    	<td>${rownum}</td>
			    	<td>${order_id}</td>
			    	<td>${pay_date}</td>
			    	<td>￥${charge}</td>
			    	<td><u:ucparams key="${charge_type}" type="charge_type"></u:ucparams></td>
			    </tr>
		    </s:iterator>
	    </tbody>
    </table>
    <form method="post" id="recharge_form" action="${ctx}/developerAccount/view">
    	<input type="hidden" name="sid" value="${data.account.sid}"/>
    	<input type="hidden" name="page_type" value="1"/>
    </form>
    <u:page page="${data.recharge_page}" formId="recharge_form" />
    </li>
    </ul>
    <div class="clear"></div>
    <hr class="hr" />
    </div>
    
    
<!--弹层(成功) bof-->
<div class="float_box account_box success_box" id="sus_box" style="display:none;">
  <div class="float_tit">
    <h3 id="sus_title">成功</h3>
  </div>
  <div class="float_ctn">
      <p ><span id="sus_message"></span>，请<a href="#" class="cancel_btn" id="back_btn" >返回</a></p>
  </div>
</div>
<!--弹层(冻结成功) eof--> 


<!--弹层(充值,赠送,扣费) bof-->
<div class="float_box account_box" id="balance_box" style="display:none;">
  <div class="float_tit">
    <h3 class="mng_type_name">充值</h3>
  </div>
  <div class="float_ctn">
    <form method="post" name="rechargeForm">
      <p><label><span class="mng_type_name">充值</span>金额：</label><span><input id="changeValue" onfocus="inputControl.setNumber(this, 10, 3, false)" type="text" value="0" class="txt_177" /></span></p>
      <p><label>确认金额：</label><span><input id="changeValueCheck" onfocus="inputControl.setNumber(this, 10, 3, false)" type="text" value="0" class="txt_177" /></span></p>
      <p><label><span class="mng_type_name">充值</span>账户：</label><span class="v_email"></span></p>
      <hr class="hr" />
      <p class="btn">
        <input type="button" value="确 认" onclick="updateBalance('balance_box');" />
        <input type="button" value="取 消" class="cancel_btn grey_btn"  onclick="hideBox('balance_box')" />
        <span class="v_sid"  style="display: none;"></span>
      </p>
    </form>
  </div>
</div>
<!--弹层(充值,赠送,扣费) eof-->   

<!--弹层(代理商转移) bof-->
<div class="float_box package_box" id="transfer_box" style="display:none;">
  <div class="float_tit">
    <h3 class="mng_type_name"></h3>
  </div>
  <div class="float_ctn">
	<form method="post" id="developerTransferForm">
 	 <p><label>当前账户：</label><span class="v_email"></span></p>
      <div class="select_box">
        <label>套 餐 档：</label>
        <u:select id="select_package" sqlId="package" callback="selectPackage"/>
      </div>
      <div class="clear"></div>
      <p><b>确认信息：</b></p>
      <p><label>套餐编号：</label><span id="box_package_id"></span></p>
      <p><label>套餐名称：</label><span id="box_package_name"></span></p>
      <p><label>保底消费：</label><span id="box_min_consumption" class="money"></span></p>
      <div class="select_box">
        <label>是否推送消息：</label>
        <u:radio name="is_send_msg" dictionaryType="is_send_msg" />
      </div>
      <div class="clear"></div>
      <span id="msg" class="error" style="display:none;"></span>
      <hr class="hr" />
      <p class="btn">
      	<input type="hidden" name="sid" value="${data.account.sid}" />
      	<input type="hidden" id="package_id" name="package_id" value="${data.account.package_id}" />
      	<input type="hidden" id="new_package_id" name="new_package_id"/>
        <input type="button" value="保 存" onclick="developerTransfer(this)" />
        <input type="button" value="取 消" onclick="hideBox('transfer_box')"/>
        <span class="v_sid"  style="display: none;"></span>
      </p>
     </form>
  </div>
</div>
<!--弹层(代理商转移) eof-->

<!--弹层(出账单) bof-->
<div class="float_box account_box" id="bill_box"  style="display:none;">
	<input type="hidden" id="billDownFunction" value="developerBillDownload"/>
	<input type="hidden" id="sid" value="${param.sid}"/>
  <div class="float_tit">
    <h3>出账单</h3>
  </div>
  <div class="float_ctn">
      <table class="noEdge">
        <tbody id="download">
        </tbody>
      </table>
      <p class="btn">
        <input type="button" value="取 消" class="cancel_btn grey_btn" onclick="hideBox('bill_box')"  />
      </p>
  </div>
</div>
<!--弹层(出账单) eof--> 
<script type="text/javascript" src="${ctx}/js/store.min.js"></script>
<script type="text/javascript" src="${ctx}/js/account/agentAccount/agentQuery.js"></script>
</body>
</html>