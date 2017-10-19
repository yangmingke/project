package com.flypaas.tag;

import java.io.Writer;
import java.util.Map;

import org.apache.struts2.components.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.util.ValueStack;

import freemarker.template.Template;

/**
 * 公共UI组件
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
	 * 属性值
	 */
	private Map<String, Object> params;

	public BaseComponent(ValueStack stack) {
		super(stack);
	}

	@Override
	public boolean start(Writer writer) {
		boolean result = super.start(writer);
		try {
			template.process(params, writer);
		} catch (Exception ex) {
			LOGGER.error("自定义标签输出异常：templateFile=" + template.getName() + ", params=" + params, ex);
		}
		return result;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

}
