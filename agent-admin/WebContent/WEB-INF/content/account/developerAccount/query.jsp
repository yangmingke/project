<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<u:authority menuId="49">
	<s:set var="menuId_49" value="true"/>
</u:authority>
<u:authority menuId="50">
	<s:set var="menuId_50" value="true"/>
</u:authority>
<u:authority menuId="51">
	<s:set var="menuId_51" value="true"/>
</u:authority>
<u:authority menuId="52">
	<s:set var="menuId_52" value="true"/>
</u:authority>
<u:authority menuId="53">
	<s:set var="menuId_53" value="true"/>
</u:authority>
<u:authority menuId="54">
	<s:set var="menuId_54" value="true"/>
</u:authority>

<html>
<head>
	<title>开发者账务</title>
</head>

<body>
	<div class="main_search">
        <form method="post" id="mainForm" action="${ctx}/developerAccount/query"  onsubmit="store.set('_dev_bill_form',$(this).serialize(),0)">
          <ul>
            <li>
              <input type="text" name="text" value="<s:property value="#parameters.text"/>" placeholder="钱包编号/用户名/认证名称" class="txt_177" />
            </li>
            <li class="money">
              <label>钱包金额：</label>
              <input type="text" name="start_balance" value="${param.start_balance}" placeholder="" class="txt_127"  onfocus="inputControl.setNumber(this, 10, 2, false)"/>
              <span>至</span>
              <input type="text"  name="end_balance" value="${param.end_balance}" placeholder="" class="txt_127"  onfocus="inputControl.setNumber(this, 10, 2, false)"/>
            </li>
           <li class="time">
              	<u:date id="start_date" value="${param.start_date}" placeholder="开始时间" maxId="end_date,-1" maxToday="-1" />
				<span>至</span>
            	<u:date id="end_date" value="${param.end_date}" placeholder="结束时间" minId="start_date,1" maxToday=""  />
            </li>
            <li>
            	<u:select id="wallet_status" value="${param.wallet_status}" placeholder="钱包状态" dictionaryType="wallet_status" />
            </li>
            <li>
              <input type="submit" value="查 询" class="search" />
            </li>
          </ul>
        </form>
      </div>
      <div class="clear"></div>
      <div class="table_ctn">
        <table class="noEdge">
          <tbody>
            <tr>
              <th width="50px">序号</th>
              <th>钱包编号</th>
              <th>用户名</th>
              <th>认证名称</th>
              <th>手机号码</th>
              <th>钱包余额（元）</th>
              <th>信用额度（元）</th>
              <th>资费套餐</th>
              <th>钱包状态</th>
              <th>最后一次充值时间</th>
              <th>操作</th>
            </tr>
            <s:iterator value="page.list">
	            <tr id="tr_${rownum}">
	            	<td tit="v_rownum">${rownum}</td>
	            	<td tit="v_acct_id">${acct_id}</td>
	            	<td tit="v_email">${email}</td>
	            	<td tit="v_realname">${realname}</td>
	            	<td tit="v_mobile">${mobile}</td>
	            	<td tit="v_balance">${balance}</td>
	            	<td tit="v_credit_balance">${credit_balance_fmt}</td>
	            	<td tit="v_package_name">${package_name}</td>
	            	<td tit="v_wallet_status_name"><u:ucparams key="${wallet_status}" type="wallet_status"/></td>
	            	<td tit="v_last_recharge_date">${last_recharge_date}</td>
	            	<td>
	            		<s:if test="wallet_status==1"><%--1 正常 --%>
				      		<%-- <s:if test="menuId_49"> --%>
								<a href="javascript:;" onclick="view('${sid}')">查看</a>
							<%-- </s:if>
				      		<s:if test="menuId_50"> --%>
								| <a href="javascript:;" onclick="enableFlagBox('#tr_${rownum} td','冻结',0)">冻结</a>
							<%-- </s:if>
				      		<s:if test="menuId_52"> --%>
								| <a href="javascript:;" onclick="balanceBox('#tr_${rownum} td','充值')">充值</a>
							<%-- </s:if>
				      		<s:if test="menuId_53"> --%>
								| <a href="javascript:;" onclick="balanceBox('#tr_${rownum} td','赠送')">赠送</a>
							<%-- </s:if>
				      		<s:if test="menuId_54"> --%>
								| <a href="javascript:;" onclick="balanceBox('#tr_${rownum} td','扣费')">扣费</a>
							<%-- </s:if>
							<s:if test="menuId_54"> --%>
								| <a href="javascript:;" onclick="createInvoice('${sid}')">开票</a>
							<%-- </s:if> --%>
								<%-- 
								| <a href="javascript:;" onclick="enableFlagBox('#tr_${rownum} td','注销',3)">注销</a>
								 --%>
	            		</s:if>
	            		<s:elseif test="wallet_status==0"><%--0：冻结 --%>
				      		<%-- <s:if test="menuId_49"> --%>
								<a href="javascript:;" onclick="view('${sid}')">查看</a>
							<%-- </s:if>
				      		<s:if test="menuId_51"> --%>
								| <a href="javascript:;" onclick="enableFlagBox('#tr_${rownum} td','解冻',1)">解冻</a>
							<%-- </s:if>
				      		<s:if test="menuId_52"> --%>
								| <a href="javascript:;" onclick="balanceBox('#tr_${rownum} td','充值')">充值</a>
							<%-- </s:if>
				      		<s:if test="menuId_53"> --%>
								| <a href="javascript:;" onclick="balanceBox('#tr_${rownum} td','赠送')">赠送</a>
							<%-- </s:if>
				      		<s:if test="menuId_54"> --%>
								| <a href="javascript:;" onclick="balanceBox('#tr_${rownum} td','扣费')">扣费</a>
							<%-- </s:if> --%>
								<%-- 
								| <a href="javascript:;" onclick="enableFlagBox('#tr_${rownum} td','注销',3)">注销</a>
								 --%>
	            		</s:elseif>
	            		<s:elseif test="wallet_status==3"><%--3已注销 --%>
				      		<%-- <s:if test="menuId_49"> --%>
								<a href="javascript:;" onclick="view('${sid}')">查看</a>
							<%-- </s:if> --%>
	            		</s:elseif>
					</td>
					<td tit="v_sid" style="display:none;">${sid}</td>
					<td tit="v_wallet_status"  style="display:none;">${wallet_status}</td>
	            </tr>
	        </s:iterator>
          </tbody>
        </table>
		
		<u:page page="${page}" formId="mainForm" />
      </div>
<!--弹层(冻结、充值、赠送、扣费、注销) bof-->
<div class="background_box" style="display:none;">&nbsp;</div>

<!--弹层(冻结,解冻,注销) bof-->
<div class="float_box account_box" id="enable_flag_box" style="display:none;">
  <div class="float_tit">
    <h3 class="enable_flag_mng">冻结</h3>
  </div>
  <div class="float_ctn">
      <p>账户信息</p>
      <p><label>账户编号：</label><span class="v_acct_id"></span></p>
      <p><label>用户名：</label><span class="v_email"></span></p>
      <p><label>账户金额：</label><span class="money">￥</span><span class="money v_balance"></span></p>
      <p><label>资费套餐：</label><span class="v_package_name"></span></p>
      <p>是否确认<span class="enable_flag_mng">冻结</span>账户</p>
      <hr class="hr" />
      <p class="btn">
        <input type="button" value="确 定" onclick="updateEnableFlag('enable_flag_box');" />
        <input type="button" value="取 消" class="cancel_btn grey_btn" onclick="hideBox('enable_flag_box')" />
        <span class="v_enable_flag"  style="display: none;"></span>
        <span class="v_sid"  style="display: none;"></span>
        <span class="v_wallet_status"  style="display: none;"></span>
      </p>
  </div>
</div>
<!--弹层(冻结,解冻,注销) eof-->

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
<script type="text/javascript" src="${ctx}/js/store.min.js"></script>
<script type="text/javascript" src="${ctx}/js/account/developerAccount/developerQuery.js"></script>
<script type="text/javascript">
	function createInvoice(sid){
		window.location.href = "${ctx}/invoiceAudit/createInvoice?sid="+sid;
	}
</script>
</body>
</html>