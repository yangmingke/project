package com.network.monitor.action;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.network.monitor.service.CommonService;
import com.network.monitor.util.web.StrutsUtils;

/**
 * 业务监控：请求url，判断返回内容是否包含字符串：monitor=success，包含则成功，不包含则失败
 * 
 * @author xiejiaan
 */
@Controller
@Scope("prototype")
public class MonitorAction extends BaseAction {
	private static final long serialVersionUID = 2298890095445949187L;
	@Autowired
	private CommonService commonService;

	@Action("/monitor")
	public void monitor() {
		data = commonService.monitor();
		StrutsUtils.renderText(data.toString());
	}
}
