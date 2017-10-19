package com.flypaas.admin.action.account;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.admin.action.BaseAction;
import com.flypaas.admin.service.account.SecurityDepositService;
import com.flypaas.admin.util.web.AuthorityUtils;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 账务管理-保障金账户
 * 
 * @author xiejiaan
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "query", location = "/WEB-INF/content/account/securityDeposit/query.jsp"),
		@Result(name = "view", location = "/WEB-INF/content/account/securityDeposit/view.jsp") })
public class SecurityDepositAction extends BaseAction {
	private static final long serialVersionUID = 3293958514356819580L;
	private SecurityDepositService securityDepositService;
	private static final String RECHARGE_PAGE_ROW_COUNT = "2";// 充值记录每页显示行数
	private static final String CONSUMPTION_PAGE_ROW_COUNT = "2";// 消费日志每页显示行数

	@Resource
	public void setSecurityDepositService(SecurityDepositService securityDepositService) {
		this.securityDepositService = securityDepositService;
	}

	/**
	 * 保障金账户列表页面
	 * 
	 * @return
	 */
	@Action("/securityDeposit/query")
	public String query() {
		page = securityDepositService.query(StrutsUtils.getFormData());
		return "query";
	}

	/**
	 * 查看
	 * 
	 * @return
	 */
	@Action("/securityDeposit/view")
	public String view() {
		Map<String, String> form = StrutsUtils.getFormData();
		String sid = form.get("sid");
		String id = form.get("id");
		if (StringUtils.isNotBlank(id)) {
			Map<String, String> rechargeParams = new HashMap<String, String>();
			Map<String, String> consumptionParams = new HashMap<String, String>();
			rechargeParams.put("sid", sid);
			consumptionParams.put("sid", sid);
			rechargeParams.put("id", id);
			consumptionParams.put("id", id);
			rechargeParams.put("pageRowCount", RECHARGE_PAGE_ROW_COUNT);
			consumptionParams.put("pageRowCount", CONSUMPTION_PAGE_ROW_COUNT);

			int pageType = NumberUtils.toInt(form.get("page_type"));
			if (pageType == 1) {
				rechargeParams.put("currentPage", form.get("currentPage"));
			} else if (pageType == 2) {
				consumptionParams.put("currentPage", form.get("currentPage"));
			}
			data = securityDepositService.view(sid, Long.parseLong(id), rechargeParams, consumptionParams);
		}
		return "view";
	}

	@Action("/securityDeposit/updateEnableFlag")
	public void updateEnableFlag() {
		String sid = AuthorityUtils.getLoginSid();
		data = securityDepositService.updateEnableFlag(sid, StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}

	@Action("/securityDeposit/updateBalance")
	public void updateBalance() {
		String sid = AuthorityUtils.getLoginSid();
		data = securityDepositService.updateBalance(sid, StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}
}
