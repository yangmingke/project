package com.flypaas.admin.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.admin.service.CommonService;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 公共服务
 * 
 * @author zenglb
 */
@Controller
@Scope("prototype")
public class CommonAction extends BaseAction {
	private static final long serialVersionUID = -2983359130148766191L;
	private CommonService commonService;

	@Resource
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	/**
	 * 页面顶部1、2级菜单
	 * 
	 * @return
	 */
	@Action("/common/loadCityByProvinceId")
	public void loadCityByProvinceId() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("provinceId", StrutsUtils.getParameter("provinceId"));
		List<Map<String, Object>> lst = commonService.loadCityByProvinceId(params);
		StrutsUtils.renderJson(lst);
	}
}
