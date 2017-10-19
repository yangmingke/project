package com.flypaas.admin.action.data;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.admin.action.BaseAction;
import com.flypaas.admin.service.data.DeveloperService;
import com.flypaas.admin.util.web.StrutsUtils;
import com.opensymphony.xwork2.ActionContext;

/**
 * 信息管理-开发者管理
 * 
 * @author xiejiaan
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "query", location = "/WEB-INF/content/data/developer/query.jsp"),
		@Result(name = "view", location = "/WEB-INF/content/data/developer/view.jsp"),
		@Result(name = "edit", location = "/WEB-INF/content/data/developer/edit.jsp"),
		@Result(name = "mainAccount", location = "/WEB-INF/content/data/developer/mainAccount.jsp"),
		@Result(name = "notice", location = "/WEB-INF/content/data/developer/notice.jsp") })
public class DeveloperAction extends BaseAction {
	private static final long serialVersionUID = -6398668354512197029L;
	@Autowired
	private DeveloperService developerService;

	/**
	 * 查询开发者
	 * 
	 * @return
	 */
	@Action("/developer/query")
	public String query() {
		page = developerService.query(StrutsUtils.getFormData());
		return "query";
	}

	/**
	 * 查看开发者资料
	 * 
	 * @return
	 */
	@Action("/developer/view")
	public String view() {
		getDeveloperInfo();
		return "view";
	}

	/**
	 * 修改开发者资料页面
	 * 
	 * @return
	 */
	@Action("/developer/edit")
	public String edit() {
		getDeveloperInfo();
		return "edit";
	}

	/**
	 * 获取开发者资料
	 * 
	 * @return
	 */
	private void getDeveloperInfo() {
		String sid = StrutsUtils.getParameterTrim("sid");
		if (StringUtils.isNotBlank(sid)) {
			data = developerService.getDeveloper(sid);
		}
	}

	/**
	 * 保存开发者资料
	 * 
	 * @return
	 */
	@Action("/developer/save")
	public void save() {
		data = developerService.saveDeveloper(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}

	/**
	 * 查看开发者主账号
	 * 
	 * @return
	 */
	@Action("/developer/mainAccount")
	public String mainAccount() {
		String sid = StrutsUtils.getParameterTrim("sid");
		if (StringUtils.isNotBlank(sid)) {
			data = developerService.getMainAccount(sid);
		}
		return "mainAccount";
	}

	/**
	 * 修改开发者状态：关闭、重新激活
	 * 
	 * @return
	 */
	@Action("/developer/updateStatus")
	public void updateStatus() {
		data = developerService.updateStatus(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}

	/**
	 * 安全校正
	 * 
	 * @return
	 */
	@Action("/developer/securityCorrection")
	public void securityCorrection() {
		String sid = StrutsUtils.getParameterTrim("sid");
		data = developerService.securityCorrection(sid);
		StrutsUtils.renderJson(data);
	}

	/**
	 * 通知
	 * 
	 * @return
	 */
	@Actions({ @Action("/developer/notice"), @Action("/bill/notice") })
	public String notice() {
		return "notice";
	}

	/**
	 * 发送通知
	 * 
	 * @return
	 */
	@Action("/developer/sendNotice")
	public void sendNotice() {
		data = developerService.sendNotice(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}

	/**
	 * 设置或取消为大客户
	 * 
	 * @return
	 */
	@Action("/developer/setHeavybuyer")
	public void setHeavybuyer() {
		data = developerService.setHeavybuyer(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}
	
	/**
	 * 设置或取消为代理商
	 * 
	 * @return
	 */
	@Action("/developer/setProxy")
	public void setProxy() {
		data = developerService.setProxy(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}
	
	
	/**
	 * 保存销售经理信息
	 * 
	 * @return
	 */
	@Action("/developer/saveSale")
	public void saveSale() {
		data = developerService.saveSale(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}
}
