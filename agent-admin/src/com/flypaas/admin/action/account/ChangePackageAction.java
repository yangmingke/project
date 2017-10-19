package com.flypaas.admin.action.account;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.admin.action.BaseAction;
import com.flypaas.admin.service.account.ChangePackageService;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 账务管理-套餐变更日志
 * 
 * @author xiejiaan
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "query", location = "/WEB-INF/content/account/changePackage/query.jsp"),
		@Result(name = "history", location = "/WEB-INF/content/account/changePackage/history.jsp") })
public class ChangePackageAction extends BaseAction {
	private static final long serialVersionUID = -5381412812385023764L;
	@Autowired
	private ChangePackageService changePackageService;

	/**
	 * 查询套餐变更日志
	 * 
	 * @return
	 */
	@Action("/changePackage/query")
	public String query() {
		page = changePackageService.query(StrutsUtils.getFormData());
		return "query";
	}

	/**
	 * 查看变更历史
	 * 
	 * @return
	 */
	@Action("/changePackage/history")
	public String history() {
		String sid = StrutsUtils.getParameterTrim("sid");
		if (StringUtils.isNotBlank(sid)) {
			data = changePackageService.history(sid);
		}
		return "history";
	}

	/**
	 * 还原
	 * 
	 * @return
	 */
	@Action("/changePackage/restore")
	public void restore() {
		String id = StrutsUtils.getParameterTrim("id");
		if (NumberUtils.isDigits(id)) {
			data = changePackageService.restore(Integer.parseInt(id));
			StrutsUtils.renderJson(data);
		}
	}

}
