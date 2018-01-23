package com.flypaas.admin.action.account;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.admin.action.BaseAction;
import com.flypaas.admin.service.account.FeeTypeService;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 账务管理-开发者账务
 * 
 * @author xiejiaan
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "query", location = "/WEB-INF/content/account/feeType/query.jsp"),
		@Result(name = "view", location = "/WEB-INF/content/account/feeType/view.jsp") })
public class FeeTypeAction extends BaseAction {
	private static final long serialVersionUID = -7384429858714304552L;
	@Autowired
	private FeeTypeService feeTypeService;
	
	@Action("/feeType/feeTypeConfig")
	public String feeTypeConfig(){
		page = feeTypeService.query(StrutsUtils.getFormData());
		return "query";
	}
	
	@Action("/feeType/feeTypeItem")
	public String feeTypeItem(){
		page = feeTypeService.query(StrutsUtils.getFormData());
		return "view";
	}
	
	@Action("/feeType/addFeeTypeItem")
	public void addFeeTypeItem(){
		data = feeTypeService.addFeeTypeItem(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}
	
	@Action("/feeType/updateFeeTypeItem")
	public void updateFeeTypeItem(){
		data = feeTypeService.updateFeeTypeItem(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}
	
	@Action("/feeType/delFeeTypeItem")
	public void delFeeTypeItem(){
		data = feeTypeService.delFeeTypeItem(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}
}
