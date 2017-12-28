package com.flypaas.admin.action.account;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.admin.action.BaseAction;
import com.flypaas.admin.service.account.DeveloperAccountService;
import com.flypaas.admin.util.rest.utils.DateUtil;
import com.flypaas.admin.util.web.AuthorityUtils;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 账务管理-开发者账务
 * 
 * @author xiejiaan
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "query", location = "/WEB-INF/content/account/developerAccount/query.jsp"),
		@Result(name = "trafficView", location = "/WEB-INF/content/account/developerAccount/trafficView.jsp"),
		@Result(name = "feeTimeView", location = "/WEB-INF/content/account/developerAccount/feeTimeView.jsp"),
		@Result(name = "view", location = "/WEB-INF/content/account/developerAccount/view.jsp") })
public class DeveloperAccountAction extends BaseAction {
	private static final long serialVersionUID = -7384429858714304552L;
	@Autowired
	private DeveloperAccountService developerAccountService;
	private static final String RECHARGE_PAGE_ROW_COUNT = "10";// 充值记录每页显示行数
	private static final String CONSUMPTION_PAGE_ROW_COUNT = "10";// 消费日志每页显示行数

	/**
	 * 查询开发者账务
	 * 
	 * @return
	 */
	@Action("/developerAccount/query")
	public String query() {
		page = developerAccountService.query(StrutsUtils.getFormData());
		return "query";
	}

	/**
	 * 查看开发者账务
	 * 
	 * @return
	 */
	@Action("/developerAccount/view")
	public String view() {
		Map<String, String> form = StrutsUtils.getFormData();
		String sid = form.get("sid");
		if (StringUtils.isNotBlank(sid)) {
			Map<String, String> rechargeParams = new HashMap<String, String>();
			Map<String, String> consumptionParams = new HashMap<String, String>();
			rechargeParams.put("sid", sid);
			consumptionParams.put("sid", sid);
			rechargeParams.put("pageRowCount", RECHARGE_PAGE_ROW_COUNT);
			consumptionParams.put("pageRowCount", CONSUMPTION_PAGE_ROW_COUNT);

			int pageType = NumberUtils.toInt(form.get("page_type"));
			if (pageType == 1) {
				rechargeParams.put("currentPage", form.get("currentPage"));
			} else if (pageType == 2) {
				consumptionParams.put("currentPage", form.get("currentPage"));
			}
			data = developerAccountService.view(sid, rechargeParams, consumptionParams);
		}
		return "view";
	}
	
	/**
	 * 时长计费页面
	 * 
	 * @return
	 */
	@Action("/developerAccount/feeTimeView")
	public String feeTimeView() {
		page = developerAccountService.queryFeeTime(StrutsUtils.getFormData());
		return "feeTimeView";
	}
	
	
	/**
	 * 开发者流量页面
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Action("/developerAccount/trafficView")
	public String trafficView() {
		Map form = StrutsUtils.getFormData();
		data = new HashMap<>(form);
		data.put("today", DateUtil.getStrCurrentDate());
		return "trafficView";
	}
	
	/**
	 * 开发者流量页面
	 * 
	 * @return
	 */
	@Action("/developerAccount/queryTraffic")
	public void queryTraffic() {
		data = developerAccountService.queryTraffic(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}

	@Action("/developerAccount/updateEnableFlag")
	public void updateEnableFlag() {
		String sid = AuthorityUtils.getLoginSid();
		data = developerAccountService.updateEnableFlag(sid, StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}

	@Action("/developerAccount/updateBalance")
	public void updateBalance() {
		String sid = AuthorityUtils.getLoginSid();
		data = developerAccountService.updateBalance(sid, StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}
	/**
	 *设置信用额度
	 */
	@Action("/developerAccount/saveCreditBalance")
	public void saveCreditBalance() {
		data = developerAccountService.saveCreditBalance(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}
}
