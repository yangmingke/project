package com.flypaas.admin.action.cps;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.admin.action.BaseAction;
import com.flypaas.admin.service.cps.VoiceDeviceAutoService;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 策略管理-音频驱动适配-音频驱动智能适配
 * 
 * @author xiejiaan
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "query", location = "/WEB-INF/content/cps/voiceDeviceAuto/query.jsp"),
		@Result(name = "view", location = "/WEB-INF/content/cps/voiceDeviceAuto/view.jsp"),
		@Result(name = "edit", location = "/WEB-INF/content/cps/voiceDeviceAuto/edit.jsp") })
public class VoiceDeviceAutoAction extends BaseAction {
	private static final long serialVersionUID = -5375377274808426180L;
	@Autowired
	private VoiceDeviceAutoService voiceDeviceAutoService;

	/**
	 * 查询音频驱动智能适配
	 * 
	 * @return
	 */
	@Action("/voiceDeviceAuto/query")
	public String query() {
		page = voiceDeviceAutoService.query(StrutsUtils.getFormData());
		return "query";
	}

	/**
	 * 查看音频驱动智能适配
	 * 
	 * @return
	 */
	@Action("/voiceDeviceAuto/view")
	public String view() {
		String id = StrutsUtils.getParameterTrim("id");
		if (NumberUtils.isDigits(id)) {
			data = voiceDeviceAutoService.view(Integer.parseInt(id));
		}
		return "view";
	}

	/**
	 * 添加音频驱动智能适配
	 * 
	 * @return
	 */
	@Action("/voiceDeviceAuto/add")
	public String add() {
		return "edit";
	}

	/**
	 * 修改音频驱动智能适配
	 * 
	 * @return
	 */
	@Action("/voiceDeviceAuto/edit")
	public String edit() {
		String id = StrutsUtils.getParameterTrim("id");
		if (NumberUtils.isDigits(id)) {
			data = voiceDeviceAutoService.view(Integer.parseInt(id));
		}
		return "edit";
	}

	/**
	 * 保存音频驱动智能适配，包括添加、修改
	 * 
	 * @return
	 */
	@Action("/voiceDeviceAuto/save")
	public void save() {
		data = voiceDeviceAutoService.save(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}

	/**
	 * 删除音频驱动智能适配
	 * 
	 * @return
	 */
	@Action("/voiceDeviceAuto/delete")
	public void delete() {
		String id = StrutsUtils.getParameterTrim("id");
		if (NumberUtils.isDigits(id)) {
			data = voiceDeviceAutoService.delete(Integer.parseInt(id));
			StrutsUtils.renderJson(data);
		}
	}

}
