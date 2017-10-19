package com.flypaas.action.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.action.BaseAction;
import com.flypaas.constant.AppConstant;
import com.flypaas.entity.Application;
import com.flypaas.entity.SmsTemplate;
import com.flypaas.entity.vo.PageContainer;
import com.flypaas.httpclient.impl.RefreshRedis;
import com.flypaas.utils.DateUtil;
import com.flypaas.utils.Des3Utils;
import com.flypaas.utils.StrUtil;

@Controller
@Scope("prototype")
@Results({ @Result(name = "list", location = "/page/app/sms/template.jsp"),
		@Result(name = "query", type = "redirectAction", location = "query"),
		@Result(name = "edit", location = "/page/app/sms/template_edit.jsp") })
public class MessageAction extends BaseAction {

	private List<Application> appSmsNumList;

	private List<Application> appList;

	private SmsTemplate smsTemplate;

	private String templateId;

	private Integer opt;

	private PageContainer page;

	@Action(value = "/app/smsTemplate/query")
	public String query() {
		if (null == page) {
			page = new PageContainer();
		}
		String sid = getSessionUser().getSid();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("sid", sid);
		String text = request.getParameter("text");
		if (StringUtils.isNotBlank(text)) {
			param.put("text", text);
		}
		page.setParams(param);
		page = smsTemplateService.getSmsTemplateList(page);
		appList = appService.getAppBySid(sid);
		return "list";
	}

	@Action(value = "/app/smsTemplate/delete")
	public String delete() {
		if (StringUtils.isNotBlank(templateId)) {
			templateId = Des3Utils.decodeDes3(templateId);
			smsTemplateService.delete(templateId);
			RefreshRedis.updateSmsTempalte(templateId);
		} else {
			StrUtil.writeMsg(response, "模板id不正确", null);
			return null;
		}
		return "query";
	}

	@Action(value = "/app/smsTemplate/edit")
	public String edit() {
		String sid = getSessionUser().getSid();
		if (StringUtils.isNotBlank(templateId)) {
			templateId = Des3Utils.decodeDes3(templateId);
			if (templateId == null) {
				StrUtil.writeMsg(response, "模板id不正确", null);
				return null;
			}
			smsTemplate = smsTemplateService.getSmsTemplateById(templateId);
		}
		appList = appService.getAppBySid(sid);
		return "edit";
	}

	@Action(value="/app/smsTemplate/save")
	public String save() {
		if (smsTemplate != null) {
			if (StringUtils.isNotBlank(templateId)) {
				templateId = Des3Utils.decodeDes3(templateId);
				smsTemplate.setTemplateId(Long.valueOf(templateId));
			}
			smsTemplate.setStatus(com.flypaas.utils.XSSUtils.xssEncode(smsTemplate.getStatus()));
			String title = com.flypaas.utils.XSSUtils.xssEncode(smsTemplate.getName());
			String content = com.flypaas.utils.XSSUtils.xssEncode(smsTemplate.getContent());
			String sign = com.flypaas.utils.XSSUtils.xssEncode(smsTemplate.getSign());;
			String appSid = smsTemplate.getAppSid();
			if (StringUtils.isBlank(title) || title.length() > 20) {
				StrUtil.writeMsg(response, "短信模板标题超过指定长度", null);
				return null;
			}
			if (StrUtil.validationBracket(content)) {
				StrUtil.writeMsg(response, "短信模板内容不能包含中括号", null);
				return null;
			}
			if (StrUtil.validationBracket(sign)) {
				StrUtil.writeMsg(response, "短信模板内容不能包含中括号", null);
				return null;
			}
			if (StringUtils.isBlank(appSid)) {
				StrUtil.writeMsg(response, "请选择您的应用", null);
				return null;
			}
			smsTemplate.setName(title);
			smsTemplate.setContent(content);
			if (!StrUtil.isEmpty(opt) && opt == 0||opt==4) {
				smsTemplate.setType(opt);
				smsTemplate.setRule(0);
				smsTemplate.setLen(0);
			}
			smsTemplate.setUpdateDate(DateUtil.getCurrentDate());
			smsTemplate.setStatus(AppConstant.SMS_TEMPLATE_1);
			smsTemplate.setSign(sign);
			if(StringUtils.isBlank(templateId)){
				smsTemplate.setCreateDate(DateUtil.getCurrentDate());
				smsTemplateService.add(smsTemplate);
			}else{
				smsTemplateService.update(smsTemplate);
				RefreshRedis.updateSmsTempalte(templateId);
			}
		}else{
			StrUtil.writeMsg(response, "参数异常,系统无法为您提交!", null);
			return null;
		}
		return "query";
	}

	public PageContainer getPage() {
		return page;
	}

	public void setPage(PageContainer page) {
		this.page = page;
	}

	public List<Application> getAppSmsNumList() {
		return appSmsNumList;
	}

	public void setAppSmsNumList(List<Application> appSmsNumList) {
		this.appSmsNumList = appSmsNumList;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public List<Application> getAppList() {
		return appList;
	}

	public void setAppList(List<Application> appList) {
		this.appList = appList;
	}

	public SmsTemplate getSmsTemplate() {
		return smsTemplate;
	}

	public void setSmsTemplate(SmsTemplate smsTemplate) {
		this.smsTemplate = smsTemplate;
	}

	public Integer getOpt() {
		return opt;
	}

	public void setOpt(Integer opt) {
		this.opt = opt;
	}
}
