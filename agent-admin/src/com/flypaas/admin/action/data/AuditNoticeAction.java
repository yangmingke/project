package com.flypaas.admin.action.data;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.admin.action.BaseAction;
import com.flypaas.admin.service.data.AuditNoticeService;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 信息管理-管理员中心-审核通知管理
 * 
 * @author xiejiaan
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "query", location = "/WEB-INF/content/data/auditNotice/query.jsp"),
		@Result(name = "view", location = "/WEB-INF/content/data/auditNotice/view.jsp"),
		@Result(name = "edit", location = "/WEB-INF/content/data/auditNotice/edit.jsp") })
public class AuditNoticeAction extends BaseAction {
	private static final long serialVersionUID = -4968542684473314918L;
	@Autowired
	private AuditNoticeService auditNoticeService;

	/**
	 * 查询审核通知时段
	 * 
	 * @return
	 */
	@Action("/auditNotice/query")
	public String query() {
		page = auditNoticeService.query(StrutsUtils.getFormData());
		return "query";
	}

	/**
	 * 查看审核通知时段
	 * 
	 * @return
	 */
	@Action("/auditNotice/view")
	public String view() {
		String noticeId = StrutsUtils.getParameterTrim("notice_id");
		if (NumberUtils.isDigits(noticeId)) {
			data = auditNoticeService.view(Integer.valueOf(noticeId));
		}
		return "view";
	}

	/**
	 * 添加审核通知时段
	 * 
	 * @return
	 */
	@Action("/auditNotice/add")
	public String add() {
		data = auditNoticeService.view(null);
		return "edit";
	}

	/**
	 * 修改审核通知时段
	 * 
	 * @return
	 */
	@Action("/auditNotice/edit")
	public String edit() {
		String noticeId = StrutsUtils.getParameterTrim("notice_id");
		if (NumberUtils.isDigits(noticeId)) {
			data = auditNoticeService.view(Integer.valueOf(noticeId));
		}
		return "edit";
	}

	/**
	 * 保存审核通知时段，包括添加、修改
	 * 
	 * @return
	 */
	@Action("/auditNotice/save")
	public void save() {
		data = auditNoticeService.save(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}

	/**
	 * 修改审核通知时段状态：关闭、启用、删除
	 * 
	 * @return
	 */
	@Action("/auditNotice/updateStatus")
	public void updateStatus() {
		String noticeId = StrutsUtils.getParameterTrim("notice_id");
		String status = StrutsUtils.getParameterTrim("status");
		if (NumberUtils.isDigits(noticeId) && NumberUtils.isDigits(status)) {
			data = auditNoticeService.updateStatus(Integer.parseInt(noticeId), Integer.parseInt(status));
			StrutsUtils.renderJson(data);
		}
	}

}
