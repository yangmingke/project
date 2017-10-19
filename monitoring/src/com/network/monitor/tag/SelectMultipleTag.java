package com.network.monitor.tag;

/**
 * 多选下拉框标签，三种使用方式： <br/>
 * 1.设置dictionaryType；<br/>
 * 2.设置sqlId<br/>
 * 3.设置data
 * 
 * @author xiejiaan
 */
public class SelectMultipleTag extends SelectTag {
	private static final long serialVersionUID = 124532725535575128L;

	@Override
	public String getTemplateFile() {
		return "selectMultiple.ftl";
	}

}
