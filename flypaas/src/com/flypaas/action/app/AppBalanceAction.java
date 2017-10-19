package com.flypaas.action.app;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.action.BaseAction;
import com.flypaas.constant.AppConstant;
import com.flypaas.entity.AcctBalance;
import com.flypaas.entity.AppBalance;
import com.flypaas.utils.DateUtil;
import com.flypaas.utils.StrUtil;

@Controller
@Scope("prototype")
@Results({
	@Result(name="appBalanceSuc",type="redirectAction",location="appManager")
})
public class AppBalanceAction extends BaseAction {
	Logger logger = LoggerFactory.getLogger(AppBalanceAction.class);
	
	private AppBalance appBalance;
	private String sid ;
	@Action("/app/appBalance")
	public String appBalance(){
		if(StrUtil.isEmpty(appBalance)||StrUtil.isEmpty(appBalance.getAppSid())||appBalance.getBalance()<=0){
			logger.info("参数为或金额小于等于0");
			StrUtil.writeMsg(response, "充值失败", null);
			return null ;
		}
		sid = getSessionUser().getSid();
		//用户余额
		AcctBalance acctBalance = acctBalanceService.getAcctBalanceBySid(sid);
		double allAppBalance = appBalanceService.getAllBalance(sid);
		if(appBalance.getBalance()>acctBalance.getBalance()+acctBalance.getCreditBalance()-allAppBalance){
			StrUtil.writeMsg(response, "主账号余额不足", null);
			return null ;
		}
		appBalance.setSid(sid);
		appBalanceService.appBalance(appBalance);
		return "appBalanceSuc";
	}
	/*--------------------------------------------取消应用余额--------------------------------*/
	@Action("/app/unBindAppBalance")
	public String unBindAppBalance(){
		if(StrUtil.isEmpty(appBalance)||StrUtil.isEmpty(appBalance.getAppSid())){
			StrUtil.writeMsg(response, "参数错误", null);
			return null ;
		}
		appBalance.setSid(getSessionUser().getSid());
		appBalance.setBalance(0L);
		appBalance.setStatus(AppConstant.APP_BALANCE_STATUS_2);
		appBalance.setUpdateDate(DateUtil.getCurrentDate());
		appBalanceService.unBindAppBalance(appBalance);
		
		return "appBalanceSuc";
	}
	public AppBalance getAppBalance() {
		return appBalance;
	}

	public void setAppBalance(AppBalance appBalance) {
		this.appBalance = appBalance;
	}
	
}
