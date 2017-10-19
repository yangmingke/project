package com.flypaas.admin.action.cps;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.admin.action.BaseAction;
import com.flypaas.admin.service.cps.VoiceDeviceService;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 策略管理-音频驱动适配-音频驱动适配清单
 * 
 * @author xiejiaan
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "query", location = "/WEB-INF/content/cps/voiceDevice/query.jsp"),
		@Result(name = "view", location = "/WEB-INF/content/cps/voiceDevice/view.jsp"),
		@Result(name = "edit", location = "/WEB-INF/content/cps/voiceDevice/edit.jsp") })
public class VoiceDeviceAction extends BaseAction {
	private static final long serialVersionUID = -2438802692221587061L;
	@Autowired
	private VoiceDeviceService voiceDeviceService;

	/**
	 * 查询音频驱动适配清单
	 * 
	 * @return
	 */
	@Action("/voiceDevice/query")
	public String query() {
		page = voiceDeviceService.query(StrutsUtils.getFormData());
		return "query";
	}

	/**
	 * 查看音频驱动适配清单
	 * 
	 * @return
	 */
	@Action("/voiceDevice/view")
	public String view() {
		String id = StrutsUtils.getParameterTrim("id");
		if (NumberUtils.isDigits(id)) {
			data = voiceDeviceService.view(Integer.parseInt(id));
		}
		return "view";
	}

	/**
	 * 添加音频驱动适配清单
	 * 
	 * @return
	 */
	@Action("/voiceDevice/add")
	public String add() {
		return "edit";
	}

	/**
	 * 修改音频驱动适配清单
	 * 
	 * @return
	 */
	@Action("/voiceDevice/edit")
	public String edit() {
		String id = StrutsUtils.getParameterTrim("id");
		if (NumberUtils.isDigits(id)) {
			data = voiceDeviceService.view(Integer.parseInt(id));
		}
		return "edit";
	}

	/**
	 * 保存音频驱动适配清单，包括添加、修改
	 * 
	 * @return
	 */
	@Action("/voiceDevice/save")
	public void save() {
		data = voiceDeviceService.save(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}

	/**
	 * 删除音频驱动适配清单
	 * 
	 * @return
	 */
	@Action("/voiceDevice/delete")
	public void delete() {
		String id = StrutsUtils.getParameterTrim("id");
		if (NumberUtils.isDigits(id)) {
			data = voiceDeviceService.delete(Integer.parseInt(id));
			StrutsUtils.renderJson(data);
		}
	}

}
