<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>

<u:authority menuId="121">
	<s:set var="menuId_121" value="true"/>
</u:authority>
<u:authority menuId="122">
	<s:set var="menuId_122" value="true"/>
</u:authority>
<u:authority menuId="123">
	<s:set var="menuId_123" value="true"/>
</u:authority>
<u:authority menuId="124">
	<s:set var="menuId_124" value="true"/>
</u:authority>
<u:authority menuId="125">
	<s:set var="menuId_125" value="true"/>
</u:authority>

<html>
<head>
	<title>保障金账户管理</title>
</head>

<body>
	<div class="main_search">
        <form method="post" id="mainForm" action="${ctx}/securityDeposit/query"  onsubmit="store.set('_sec_dep_form',$(this).serialize(),0)">
          <ul>
            <li>
              <input type="text" name="text" value="<s:property value="#parameters.text"/>" placeholder="钱包编号/用户名" class="txt_127" />
            </li>
            <li>
            	<u:select id="status" value="${param.status}" placeholder="钱包状态" dictionaryType="security_balance_status" />
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
              <th>用户类型</th>
              <th>钱包余额（元）</th>
              <th>资金状态</th>
              <th>钱包状态</th>
              <th>操作</th>
            </tr>
            <s:iterator value="page.list">
	            <tr id="tr_${rownum}">
	            	<td tit="v_rownum">${rownum}</td>
	            	<td tit="v_acct_id">${id}</td>
	            	<td tit="v_email">${email}</td>
	            	<td tit="v_contract">${1==is_contract?'合约用户':'一般用户'}</td>
	            	<td tit="v_balance">${balance_fmt}</td>
	            	<td tit="v_package_name"><u:ucparams key="${relieve_type}" type="security_balance_relieve_type"/></td>
	            	<td tit="v_wallet_status_name"><u:ucparams key="${status}" type="security_balance_status"/></td>
	            	<td>
	            		<s:if test="status==1"><%--1 正常 --%>
				      		<s:if test="menuId_121">
								<a href="javascript:;" onclick="view('${id}','${sid}')">查看</a>
							</s:if>
				      		<s:if test="menuId_122">
								| <a href="javascript:;" onclick="balanceBox('#tr_${rownum} td','充值')">充值</a>
							</s:if>
				      		<s:if test="menuId_123">
								| <a href="javascript:;" onclick="balanceBox('#tr_${rownum} td','扣费')">扣费</a>
							</s:if>
							<s:if test="menuId_124">
								<s:if test="1==is_contract">
									| <a href="javascript:;" onclick="enableFlagBox('#tr_${rownum} td','锁定',3)">锁定</a>
								</s:if>
							</s:if>
	            		</s:if>
	            		<s:elseif test="status==2"><%--0：关闭 --%>
				      		<s:if test="menuId_121">
								<a href="javascript:;" onclick="view('${id}','${sid}')">查看</a>
							</s:if>
	            		</s:elseif>
	            		<s:elseif test="status==3"><%--3已锁定 --%>
				      		<s:if test="menuId_121">
								<a href="javascript:;" onclick="view('${id}','${sid}')">查看</a>
							</s:if>
							<s:if test="menuId_125">
								| <a href="javascript:;" onclick="enableFlagBox('#tr_${rownum} td','解锁',1)">解锁</a>
							</s:if>
	            		</s:elseif>
					</td>
					<td tit="v_sid" style="display:none;">${sid}</td>
					<td tit="v_wallet_status"  style="display:none;">${status}</td>
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
      <p ><span id="sus_message"></span>，请<a href="#" class="cancel_btn" onclick="window.location.reload(true);" id="back_btn" >返回</a></p>
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

<script type="text/javascript" src="${ctx}/js/store.min.js"></script>
<script type="text/javascript" src="${ctx}/js/account/securityDeposit/securityDeposit.js"></script>
</body>
</html>