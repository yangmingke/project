package com.flypaas.admin.action.account;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.admin.action.BaseAction;
import com.flypaas.admin.service.account.BillService;
import com.flypaas.admin.util.web.AuthorityUtils;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 账务管理-账单信息
 * 
 * @author zenglb
 * 
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "query", location = "/WEB-INF/content/account/bill/query.jsp"),@Result(name = "view", location = "/WEB-INF/content/account/bill/view.jsp") })
public class BillAction extends BaseAction {
	private static final long serialVersionUID = -6433874284291109977L;
	@Autowired
	private BillService billService;

	/**
	 * 账单资料
	 * 
	 * @return
	 */
	@Action("/bill/view")
	public String view() {
		Map<String, String> params = StrutsUtils.getFormData();
		params.put("sid", AuthorityUtils.getLoginSid());
		data = billService.getBill(params);
		return "view";
	}

	/**
	 * 账单列表
	 * 
	 * @return
	 */
	@Action("/bill/query")
	public String query() {
		page = billService.query(StrutsUtils.getFormData());
		return "query";
	}

	@Action("/bill/closeOrder")
	public void closeOrder() {
		Map<String, String> params = StrutsUtils.getFormData();
		data = billService.updateCloseOrder(AuthorityUtils.getLoginSid(), params);
		StrutsUtils.renderJson(data);
	}

}
