package com.flypaas.admin.service.data;

import java.util.Map;

import com.flypaas.admin.constant.MsgConstant.TemplateId;
import com.flypaas.admin.model.PageContainer;

/**
 * 信息管理-管理员中心-邮件管理
 * 
 * @author xiejiaan
 */
public interface EmailService {

	/**
	 * 发送文本格式的Email
	 * 
	 * @param to
	 *            接收者，用,分割
	 * @param subject
	 *            主题
	 * @param body
	 *            内容
	 * @return 是否发送成功
	 */
	boolean sendTextEmail(String to, String subject, String body);

	/**
	 * 发送html格式的Email
	 * 
	 * @param to
	 *            接收者，用,分割
	 * @param subject
	 *            主题
	 * @param body
	 *            内容
	 * @return 是否发送成功
	 */
	boolean sendHtmlEmail(String to, String subject, String body);

	/**
	 * 发送模板格式的Email
	 * 
	 * @param to
	 *            接收者，用,分割
	 * @param templateId
	 *            消息模板id
	 * @param templateParams
	 *            替换的模板参数，可以使用date
	 * @return
	 */
	boolean sendTemplateEmail(String to, TemplateId templateId, Map<String, Object> templateParams);

	/**
	 * 查询邮件
	 * 
	 * @param params
	 * @return
	 */
	PageContainer query(Map<String, String> params);

	/**
	 * 获取邮件模板
	 * 
	 * @return
	 */
	Map<String, Object> getTemplate();

	/**
	 * 保存邮件
	 * 
	 * @param params
	 * @return
	 */
	Map<String, Object> save(Map<String, String> params);

}
