<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<html>
<head>
	<title>保障金账户管理 明细</title>
</head>

<body>
	<h1><a href="javascript:;" class="back" onclick="back2query('${ctx}/securityDeposit/query','_sec_dep_form');">返 回</a>保障金账户管理/明细</h1>
    <div class="main_ctn1 account_detail">
    <ul class="ul_left">
    <li><label>钱包编号</label><span class="v_data" tit="v_entity_id">${data.entity.id}</span></li>
    <li><label>开发者账号</label><span class="v_data" tit="v_email">${data.entity.email}</span></li>
    <li><label>用户类型</label><span>${1==data.entity.is_contract?'合约用户':'一般用户'}</span></li>
    <li><label>钱包余额（元）</label><span class="money " >￥${data.entity.balance_fmt}</span></li>
    <li><label>资金状态</label><span><u:ucparams key="${data.entity.relieve_type}" type="security_balance_relieve_type"/></span></li>
    <li><label>钱包状态</label><span><u:ucparams key="${data.entity.status}" type="security_balance_status"/></span></li>
    <li class="account_link">
		<s:if test="data.entity.status==1"><%--1 正常 --%>
			<u:authority menuId="122">
				<a href="javascript:;" class="org_btn float_lf" id="rechareg_link"  onclick="balanceBox('span.v_data','充值')">充值</a>
			</u:authority>
			<u:authority menuId="123">
				<a href="javascript:;" class="grey_btn float_lf" id="deduction_link" onclick="balanceBox('span.v_data','扣费')">扣费</a>
			</u:authority>
			<u:authority menuId="124">
				<s:if test="1== data.entity.is_contract">
					<a href="javascript:;" class="grey_btn float_lf" id="bill_link" onclick="enableFlagBox('span.v_data','锁定',3)">锁定</a>
				</s:if>
			</u:authority>
		</s:if>
		<s:if test="data.entity.status==3"><%--1 锁定 --%>
			<u:authority menuId="125">
				<a href="javascript:;" class="grey_btn float_lf" id="bill_link" onclick="enableFlagBox('span.v_data','解锁',1)">解锁</a>
			</u:authority>
		</s:if>
		<span class="v_data" tit="v_sid"  style="display: none;">${data.entity.sid}</span>	
		<span class="v_data" tit="v_wallet_status"  style="display: none;">${data.entity.status}</span>
		<span class="v_data" tit="v_balance"  style="display: none;">${data.entity.balance_fmt}</span>
	</li>
    </ul>
    <ul class="ul_right">
    <li class="tit">充值记录</li>
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
			    	<td><u:ucparams key="${charge_type}" type="recharge_type"></u:ucparams></td>
			    </tr>
		    </s:iterator>
	    </tbody>
    </table>
    <form method="post" id="recharge_form" action="${ctx}/securityDeposit/view">
    	<input type="hidden" name="sid" value="${param.sid}"/>
    	<input type="hidden" name="id" value="${param.id}"/>
    	<input type="hidden" name="page_type" value="1"/>
    </form>
    <u:page page="${data.recharge_page}" formId="recharge_form" />
    </li>
    
    <li><hr class="hr" /></li>
	    <li class="tit">提现日志</li>
	    <li>
	    <table class="noEgde">
		    <tbody>
			    <tr><th>序号</th><th>订单号</th><th>操作时间</th><th>提现金额</th><th>提现渠道</th><th>备注</th></tr>
			    <s:iterator value="data.consumption_page.list">
			    	<tr>
				    	<td>${rownum}</td>
				    	<td>${id}</td>
				    	<td>${create_date_fmt}</td>
				    	<td>￥${charge_fmt}</td>
				    	<td>
				    	<s:if test="to_account_type == 0">云平台</s:if>
				    	<s:if test="to_account_type == 3">银行卡</s:if>
				    	<s:if test="to_account_type == 4">管理员扣费</s:if>
				    	</td>
				    	<td>银行信息
				    		收款单位:${company}
				    		银行账号:${banknum}
				    		开户行:${bankaddr}
				    	</td>
			    	</tr>
			    </s:iterator>
		    </tbody>
	    </table>
	    <form method="post" id="consumption_form" action="${ctx}/securityDeposit/view">
	    	<input type="hidden" name="page_type" value="2"/>
	    	<input type="hidden" name="sid" value="${param.sid}"/>
    		<input type="hidden" name="id" value="${param.id}"/>
	    </form>
	    <u:page page="${data.consumption_page}" formId="consumption_form" />
	    
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
      <p><label><span class="mng_type_name">充值</span>金额：</label><span><input id="changeValue" onfocus="inputControl.setNumber(this, 10, 3, false)" type="text" 
      value="<%=com.flypaas.admin.constant.SysConstant.SECURITY_MONEY_NO_RATE%>" class="txt_177" /></span></p>
      <p><label>确认金额：</label><span><input id="changeValueCheck" onfocus="inputControl.setNumber(this, 10, 3, false)" type="text" 
      value="<%=com.flypaas.admin.constant.SysConstant.SECURITY_MONEY_NO_RATE%>" class="txt_177" /></span></p>
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


<!--弹层(冻结,解冻,注销) bof-->
<div class="float_box account_box" id="enable_flag_box" style="display:none;">
  <div class="float_tit">
    <h3 class="enable_flag_mng">锁定</h3>
  </div>
  <div class="float_ctn">
      <p>账户信息</p>
      <p><label>账户编号：</label><span class="v_entity_id"></span></p>
      <p><label>用户名：</label><span class="v_email"></span></p>
      <p><label>账户金额：</label><span class="money">￥</span><span class="money v_balance"></span></p>
      <p>是否确认<span class="enable_flag_mng">锁定</span>账户</p>
      <hr class="hr" />
      <p class="btn">
        <input type="button" value="确 定" onclick="updateEnableFlag('enable_flag_box');" />
        <input type="button" value="取 消" class="cancel_btn grey_btn" onclick="hideBox('enable_flag_box')" />
        <span class="v_enable_flag"  style="display: none;"></span>
        <span class="v_sid"  style="display: none;">${data.entity.sid}</span>
        <span class="v_wallet_status"  style="display: none;"></span>
      </p>
  </div>
</div>
<!--弹层(冻结,解冻,注销) eof-->

<!--弹层(出账单) eof--> 
<script type="text/javascript" src="${ctx}/js/store.min.js"></script>
<script type="text/javascript" src="${ctx}/js/account/securityDeposit/securityDeposit.js"></script>
</body>
</html>