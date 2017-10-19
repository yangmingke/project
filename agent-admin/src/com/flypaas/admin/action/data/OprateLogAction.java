package com.flypaas.admin.action.data;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.admin.action.BaseAction;
import com.flypaas.admin.service.data.OprateLogService;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 信息管理-管理员中心-操作日志
 * 
 * @author xiejiaan
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "query", location = "/WEB-INF/content/data/oprateLog/query.jsp"),
		@Result(name = "view", location = "/WEB-INF/content/data/oprateLog/view.jsp") })
public class OprateLogAction extends BaseAction {
	private static final long serialVersionUID = -1234214414349439397L;
	@Autowired
	private OprateLogService oprateLogService;

	/**
	 * 查询操作日志
	 * 
	 * @return
	 */
	@Action("/oprateLog/query")
	public String query() {
		page = oprateLogService.query(StrutsUtils.getFormData());
		return "query";
	}

	/**
	 * 查看操作日志
	 * 
	 * @return
	 */
	@Action("/oprateLog/view")
	public String view() {
		String logId = StrutsUtils.getParameterTrim("log_id");
		if (NumberUtils.isDigits(logId)) {
			data = oprateLogService.view(Integer.parseInt(logId));
		}
		return "view";
	}

}
