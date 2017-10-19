package com.flypaas.admin.tag;

import java.io.Writer;
import java.util.Map;

import org.apache.struts2.components.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.util.ValueStack;

import freemarker.template.Template;

/**
 * 自定义标签组件基类
 * 
 * @author xiejiaan
 */
public class BaseComponent extends Component {
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseComponent.class);

	/**
	 * 模板文件
	 */
	private Template template;
	/**
	 * 用于模板文件的参数值
	 */
	private Map<String, Object> templateParams;
	/**
	 * 内容是否需要执行，默认是true
	 */
	private boolean evaluated = true;

	public BaseComponent(ValueStack stack) {
		super(stack);
	}

	@Override
	public boolean start(Writer writer) {
		if (template != null) {
			try {
				template.process(templateParams, writer);
			} catch (Throwable ex) {
				LOGGER.error("自定义标签输出异常：templateFile=" + template.getName() + ", templateParams=" + templateParams, ex);
			}
		}
		return evaluated;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	public Map<String, Object> getTemplateParams() {
		return templateParams;
	}

	public void setTemplateParams(Map<String, Object> templateParams) {
		this.templateParams = templateParams;
	}

	public boolean isEvaluated() {
		return evaluated;
	}

	public void setEvaluated(boolean evaluated) {
		this.evaluated = evaluated;
	}

}
