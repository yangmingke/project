package com.flypaas.admin.action.data;

import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.admin.action.BaseAction;
import com.flypaas.admin.service.data.MsgService;
import com.flypaas.admin.util.web.AuthorityUtils;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 信息管理-管理员中心-消息通知
 * 
 * @author xiejiaan
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "query", location = "/WEB-INF/content/data/msg/query.jsp"),
		@Result(name = "view", location = "/WEB-INF/content/data/msg/view.jsp"),
		@Result(name = "edit", location = "/WEB-INF/content/data/msg/edit.jsp"),
		@Result(name = "queryDeveloper", location = "/WEB-INF/content/data/msg/queryDeveloper.jsp") })
public class MsgAction extends BaseAction {
	private static final long serialVersionUID = 1901409408750775656L;
	@Autowired
	private MsgService msgService;

	/**
	 * 获取未读的消息个数
	 * 
	 * @return
	 */
	@Action("/msg/getUnreadCount")
	public void getUnreadCount() {
		int count = msgService.getUnreadCount();
		StrutsUtils.renderText(String.valueOf(count));
	}

	/**
	 * 查询消息通知
	 * 
	 * @return
	 */
	@Action("/msg/query")
	public String query() {
		Map<String, String> form = StrutsUtils.getFormData();
		if ("1".equals(form.get("myself"))) {
			form.put("text", AuthorityUtils.getLoginSid());
			form.put("hasread", "0");
		}
		page = msgService.query(form);
		StrutsUtils.setAttribute("form", form);
		return "query";
	}

	/**
	 * 查看消息通知
	 * 
	 * @return
	 */
	@Action("/msg/view")
	public String view() {
		String msgId = StrutsUtils.getParameterTrim("msg_id");
		if (NumberUtils.isDigits(msgId)) {
			data = msgService.view(Integer.parseInt(msgId));
		}
		return "view";
	}

	/**
	 * 添加消息
	 * 
	 * @return
	 */
	@Action("/msg/add")
	public String add() {
		return "edit";
	}

	/**
	 * 查询开发者
	 * 
	 * @return
	 */
	@Action("/msg/queryDeveloper")
	public String queryDeveloper() {
		page = msgService.queryDeveloper(StrutsUtils.getFormData());
		return "queryDeveloper";
	}

	/**
	 * 保存消息
	 * 
	 * @return
	 */
	@Action("/msg/save")
	public void save() {
		data = msgService.save(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}

}
