package com.flypaas.admin.tag;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.opensymphony.xwork2.util.ValueStack;
import com.flypaas.admin.service.TagService;

import freemarker.template.Template;

/**
 * 自定义标签的基类
 * 
 * @author xiejiaan
 */
public abstract class BaseTag extends ComponentTagSupport {
	private static final long serialVersionUID = -7466410826954192634L;
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseTag.class);
	@Autowired
	private FreeMarkerConfigurationFactoryBean freeMarkerConfigurationFactoryBean;
	@Autowired
	private TagService tagService;

	@Override
	public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
		BaseComponent cmp = new BaseComponent(stack);
		return cmp;
	}

	@Override
	protected void populateParams() {
		super.populateParams();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);// 手动注入@Autowired修饰的bean

		String templateFile = getTemplateFile(); // 模板文件
		Template template = null;
		if (templateFile != null) {
			try {
				template = freeMarkerConfigurationFactoryBean.getObject().getTemplate(templateFile, "utf-8");
			} catch (IOException e) {
				LOGGER.error("自定义标签获取模板文件异常：templateFile=" + templateFile, e);
			}
		}
		BaseComponent cmp = (BaseComponent) component;
		cmp.setTemplate(template);
		cmp.setTemplateParams(getTemplateParams(tagService));
		cmp.setEvaluated(isEvaluated(tagService));
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
	 * @param tagService
	 * @return
	 */
	public abstract Map<String, Object> getTemplateParams(TagService tagService);

	/**
	 * 内容是否需要执行，默认是true
	 * 
	 * @return
	 */
	public boolean isEvaluated(TagService tagService) {
		return true;
	}

}
