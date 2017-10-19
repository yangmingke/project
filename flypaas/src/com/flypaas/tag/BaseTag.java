package com.flypaas.tag;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.util.ValueStack;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 标签类的基类
 * 
 * @author xiejiaan
 */
public abstract class BaseTag extends ComponentTagSupport {
	private static final long serialVersionUID = -7466410826954192634L;
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseTag.class);

	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
		BaseComponent cmp = new BaseComponent(stack);
		return cmp;
	}

	@Override
	protected void populateParams() {
		super.populateParams();

		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(pageContext
				.getServletContext());
		Configuration freeMarkerConfiguration = (Configuration) context.getBean("freeMarkerConfiguration");

		String templateFile = getTemplateFile(); // 模板文件
		Template template = null;
		try {
			template = freeMarkerConfiguration.getTemplate(templateFile, "utf-8");
		} catch (IOException e) {
			LOGGER.error("自定义标签获取模板文件异常：templateFile=" + templateFile, e);
		}

		BaseComponent cmp = (BaseComponent) component;
		cmp.setTemplate(template);
		cmp.setParams(getTemplateParams());
	}

	/**
	 * 获取模板文件
	 * 
	 * @return
	 */
	public abstract String getTemplateFile();

	/**
	 * 获取用于模板文件的参数值
	 * 
	 * @param commonService
	 * @return
	 */
	public abstract Map<String, Object> getTemplateParams();

}
