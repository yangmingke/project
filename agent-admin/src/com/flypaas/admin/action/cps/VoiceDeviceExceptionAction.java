package com.flypaas.admin.action.cps;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.admin.action.BaseAction;
import com.flypaas.admin.service.cps.VoiceDeviceExceptionService;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 策略管理-音频驱动适配-音频驱动适配异常
 * 
 * @author xiejiaan
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "query", location = "/WEB-INF/content/cps/voiceDeviceException/query.jsp"),
		@Result(name = "view", location = "/WEB-INF/content/cps/voiceDeviceException/view.jsp") })
public class VoiceDeviceExceptionAction extends BaseAction {
	private static final long serialVersionUID = 6985742638568472605L;
	@Autowired
	private VoiceDeviceExceptionService voiceDeviceExceptionService;

	/**
	 * 查询音频驱动适配异常
	 * 
	 * @return
	 */
	@Action("/voiceDeviceException/query")
	public String query() {
		page = voiceDeviceExceptionService.query(StrutsUtils.getFormData());
		return "query";
	}

	/**
	 * 查看音频驱动适配异常
	 * 
	 * @return
	 */
	@Action("/voiceDeviceException/view")
	public String view() {
		String id = StrutsUtils.getParameterTrim("id");
		if (NumberUtils.isDigits(id)) {
			data = voiceDeviceExceptionService.view(Integer.parseInt(id));
		}
		return "view";
	}

	/**
	 * 删除音频驱动适配异常
	 * 
	 * @return
	 */
	@Action("/voiceDeviceException/delete")
	public void delete() {
		String id = StrutsUtils.getParameterTrim("id");
		if (NumberUtils.isDigits(id)) {
			data = voiceDeviceExceptionService.delete(Integer.parseInt(id));
			StrutsUtils.renderJson(data);
		}
	}
}
