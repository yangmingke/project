package com.flypaas.admin.action.account;

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
import com.flypaas.admin.service.account.PackageService;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 账务管理-资费套餐
 * 
 * @author xiejiaan
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "query", location = "/WEB-INF/content/account/package/query.jsp"),
		@Result(name = "edit", location = "/WEB-INF/content/account/package/edit.jsp") })
public class PackageAction extends BaseAction {
	private static final long serialVersionUID = 3293958514356819580L;
	@Autowired
	private PackageService packageService;

	/**
	 * 查询资费套餐
	 * 
	 * @return
	 */
	@Action("/package/query")
	public String query() {
		page = packageService.query(StrutsUtils.getFormData());
		return "query";
	}

	/**
	 * 查看资费套餐
	 * 
	 * @return
	 */
	@Action("/package/view")
	public String view() {
		String packageId = StrutsUtils.getParameterTrim("package_id");
		if (NumberUtils.isDigits(packageId)) {
			data = packageService.view(Integer.valueOf(packageId));
		}
		data.put("is_view", true);// 是否为查看
		return "edit";
	}

	/**
	 * 添加资费套餐
	 * 
	 * @return
	 */
	@Action("/package/add")
	public String add() {
		data = packageService.view(null);
		return "edit";
	}

	/**
	 * 修改资费套餐
	 * 
	 * @return
	 */
	@Action("/package/edit")
	public String edit() {
		String packageId = StrutsUtils.getParameterTrim("package_id");
		if (NumberUtils.isDigits(packageId)) {
			data = packageService.view(Integer.valueOf(packageId));
		} else {
			data = packageService.view(null);
		}
		return "edit";
	}

	/**
	 * 保存资费套餐，包括添加、修改
	 * 
	 * @return
	 */
	@Action("/package/save")
	public void save() {
		data = packageService.save(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}

	/**
	 * 修改资费套餐状态：关闭、启用、删除
	 * 
	 * @return
	 */
	@Action("/package/updateStatus")
	public void updateStatus() {
		String packageId = StrutsUtils.getParameterTrim("package_id");
		String status = StrutsUtils.getParameterTrim("status");
		if (NumberUtils.isDigits(packageId) && NumberUtils.isDigits(status)) {
			data = packageService.updateStatus(Integer.parseInt(packageId), Integer.parseInt(status));
		}
		StrutsUtils.renderJson(data);
	}

	/**
	 * 开发者转移
	 * 
	 * @return
	 */
	@Action("/package/developerTransfer")
	public void developerTransfer() {
		Map<String, String> form = StrutsUtils.getFormData();
		String sid = form.get("sid");
		if (StringUtils.isBlank(sid)) {
			sid = null;
		}
		String packageId = form.get("package_id");
		String newPackageId = form.get("new_package_id");
		boolean isSendMsg = "1".equals(form.get("is_send_msg"));
		if (NumberUtils.isDigits(packageId) && NumberUtils.isDigits(newPackageId)) {
			data = packageService.developerTransfer(sid, Integer.parseInt(packageId), Integer.parseInt(newPackageId),
					isSendMsg);
		}
		StrutsUtils.renderJson(data);
	}

}
