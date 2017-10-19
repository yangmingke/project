package com.flypaas.admin.action.audit;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.admin.action.BaseAction;
import com.flypaas.admin.service.audit.ContractService;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 审核管理-协议用户审核
 * 
 * @author zenglb
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "query", location = "/WEB-INF/content/audit/contract/query.jsp"),
		@Result(name = "view", location = "/WEB-INF/content/audit/contract/view.jsp") })
public class ContractAuditAction extends BaseAction {
	private static final long serialVersionUID = -8050303601312051986L;
	private ContractService contractService;

	@Resource
	public void setContractService(ContractService contractService) {
		this.contractService = contractService;
	}

	/**
	 * 查询资质
	 * 
	 * @return
	 */
	@Action("/contract/query")
	public String query() {
		page = contractService.query(StrutsUtils.getFormData());
		return "query";
	}

	/**
	 * 查看资质
	 * 
	 * @return
	 */
	@Action("/contract/view")
	public String view() {
		String sid = StrutsUtils.getParameterTrim("sid");
		if (StringUtils.isNotBlank(sid)) {
			data = contractService.view(sid);
		}
		return "view";
	}

	/**
	 * 审核
	 * 
	 * @return
	 */
	@Action("/contract/audit")
	public void audit() {
		data = contractService.audit(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}
	/**
	 * 设置为协议用户
	 * 
	 * @return
	 */
	@Action("/contract/setContract")
	public void setContract() {
		data = contractService.setContract(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}
	
	

	/**
	 * 保存补充原因
	 * 
	 * @return
	 */
	@Action("/contract/saveReason")
	public void saveReason() {
		data = contractService.saveReason(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}

}
