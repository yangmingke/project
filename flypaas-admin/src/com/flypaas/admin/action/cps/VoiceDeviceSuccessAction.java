package com.flypaas.admin.action.cps;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.admin.action.BaseAction;
import com.flypaas.admin.service.cps.VoiceDeviceSuccessService;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 策略管理-音频驱动适配-音频驱动适配成功
 * 
 * @author xiejiaan
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "query", location = "/WEB-INF/content/cps/voiceDeviceSuccess/query.jsp"),
		@Result(name = "view", location = "/WEB-INF/content/cps/voiceDeviceSuccess/view.jsp") })
public class VoiceDeviceSuccessAction extends BaseAction {
	private static final long serialVersionUID = -4841976542045037350L;
	@Autowired
	private VoiceDeviceSuccessService voiceDeviceSuccessService;

	/**
	 * 查询音频驱动适配成功
	 * 
	 * @return
	 */
	@Action("/voiceDeviceSuccess/query")
	public String query() {
		page = voiceDeviceSuccessService.query(StrutsUtils.getFormData());
		return "query";
	}

	/**
	 * 查看音频驱动适配成功
	 * 
	 * @return
	 */
	@Action("/voiceDeviceSuccess/view")
	public String view() {
		String id = StrutsUtils.getParameterTrim("id");
		if (NumberUtils.isDigits(id)) {
			data = voiceDeviceSuccessService.view(Integer.parseInt(id));
		}
		return "view";
	}

	/**
	 * 删除音频驱动适配成功
	 * 
	 * @return
	 */
	@Action("/voiceDeviceSuccess/delete")
	public void delete() {
		String id = StrutsUtils.getParameterTrim("id");
		if (NumberUtils.isDigits(id)) {
			data = voiceDeviceSuccessService.delete(Integer.parseInt(id));
			StrutsUtils.renderJson(data);
		}
	}
}
