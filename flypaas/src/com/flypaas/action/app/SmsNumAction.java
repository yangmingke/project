package com.flypaas.action.app;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.action.BaseAction;
import com.flypaas.entity.vo.PageContainer;

@Controller
@Scope("prototype")
@Results({
	 @Result(name = "list", location = "/page/app/sms/num.jsp") ,
})
public class SmsNumAction extends BaseAction {
	
	private PageContainer page;

	@Action(value = "/app/smsNum/query")
	public String query() {
		if (null == page) {
			page = new PageContainer();
		}
		String sid = getSessionUser().getSid();
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("sid", sid);
		String text=  request.getParameter("text");
		if(StringUtils.isNotBlank(text)){
			param.put("text",text);
		}
		page.setParams(param);
		page = smsNumService.query(page);
		return "list";
	}
	public PageContainer getPage() {
		return page;
	}
	public void setPage(PageContainer page) {
		this.page = page;
	}
}
