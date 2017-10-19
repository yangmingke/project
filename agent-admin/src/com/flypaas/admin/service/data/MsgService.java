package com.flypaas.admin.service.data;

import java.util.Map;

import com.flypaas.admin.constant.MsgConstant.MsgType;
import com.flypaas.admin.constant.MsgConstant.TemplateId;
import com.flypaas.admin.model.PageContainer;

/**
 * 消息业务
 * 
 * @author xiejiaan
 */
public interface MsgService {

	/**
	 * 直接发送消息
	 * 
	 * @param sid
	 *            开发者sid，用,分割
	 * @param msgType
	 *            消息类型
	 * @param msgTitle
	 *            消息标题
	 * @param msgDesc
	 *            消息内容
	 * @return 是否发送成功
	 */
	boolean sendMsg(String sid, MsgType msgType, String msgTitle, String msgDesc);

	/**
	 * 根据消息模板发送消息
	 * 
	 * @param sid
	 *            开发者sid，用,分割
	 * @param msgType
	 *            消息类型
	 * @param templateId
	 *            消息模板id
	 * @param templateParams
	 *            替换的模板参数，可以使用date
	 * @return
	 */
	boolean sendMsg(String sid, MsgType msgType, TemplateId templateId, Map<String, Object> templateParams);

	/**
	 * 获取消息模板
	 * 
	 * @param templateId
	 *            消息模板id
	 * @param templateParams
	 *            替换的模板参数，可以使用date
	 * @return
	 */
	Map<String, String> getTemplate(TemplateId templateId, Map<String, Object> templateParams);

	/**
	 * 获取未读的消息个数
	 * 
	 * @return
	 */
	int getUnreadCount();

	/**
	 * 获取登录用户的email
	 * 
	 * @return
	 */
	String getLoginEmail();

	/**
	 * 查询消息通知
	 * 
	 * @param params
	 * @return
	 */
	PageContainer query(Map<String, String> params);

	/**
	 * 查看消息通知
	 * 
	 * @param msgId
	 * @return
	 */
	Map<String, Object> view(int msgId);

	/**
	 * 查询开发者
	 * 
	 * @param params
	 * @return
	 */
	PageContainer queryDeveloper(Map<String, String> params);

	/**
	 * 保存消息
	 * 
	 * @param params
	 * @return
	 */
	Map<String, Object> save(Map<String, String> params);

}
