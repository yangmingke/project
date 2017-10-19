package com.flypaas.admin.service.data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.admin.constant.LogConstant.LogType;
import com.flypaas.admin.constant.MsgConstant.MsgType;
import com.flypaas.admin.constant.MsgConstant.TemplateId;
import com.flypaas.admin.dao.MasterDao;
import com.flypaas.admin.model.PageContainer;
import com.flypaas.admin.service.LogService;
import com.flypaas.admin.util.StrUtils;
import com.flypaas.admin.util.web.AuthorityUtils;

/**
 * 消息业务
 * 
 * @author xiejiaan
 */
@Service
@Transactional
public class MsgServiceImpl implements MsgService {
	private static final Logger logger = LoggerFactory.getLogger(MsgServiceImpl.class);
	@Autowired
	private MasterDao dao;
	@Autowired
	private LogService logService;
	/**
	 * 批处理大小
	 */
	private static final int batch = 1000;

	private boolean sendMsg(Map<String, String> params) {
		List<String> sidList = Arrays.asList(params.get("sid").toString().split(","));
		int count = sidList.size();
		int success = 0;

		Map<String, Object> sqlParams = new HashMap<String, Object>();
		sqlParams.putAll(params);
		for (int i = 0; i < count; i += batch) {
			sqlParams.put("sid", sidList.subList(i, Math.min(i + batch, count)));
			success += dao.insert("msg.insertMsg", sqlParams);
		}
		if (success > 0) {
			logger.debug("发送消息成功：success={}, params={}", success, params);
			return true;
		} else {
			logger.debug("发送消息失败：success={}, params={}", success, params);
			return false;
		}
	}

	@Override
	public boolean sendMsg(String sid, MsgType msgType, String msgTitle, String msgDesc) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("sid", sid);
		params.put("msg_type", String.valueOf(msgType.getValue()));
		params.put("msg_title", msgTitle);
		params.put("msg_desc", msgDesc);
		return sendMsg(params);
	}

	@Override
	public boolean sendMsg(String sid, MsgType msgType, TemplateId templateId, Map<String, Object> templateParams) {
		Map<String, String> template = getTemplate(templateId, templateParams);
		return sendMsg(sid, msgType, template.get("title"), template.get("desc"));
	}

	@Override
	public Map<String, String> getTemplate(TemplateId templateId, Map<String, Object> templateParams) {
		Map<String, Object> data = dao.getOneInfo("msg.getMsgTemplate", templateId.getValue());
		if (data == null) {
			logger.error("获取消息模板失败，template_id=" + templateId.getValue());
			return null;
		}
		templateParams.put("date", DateTime.now().toString("yyyy-MM-dd"));
		String title = StrUtils.replacePlaceholder(data.get("template_title").toString(), templateParams);
		String desc = StrUtils.replacePlaceholder(data.get("template_desc").toString(), templateParams);

		Map<String, String> template = new HashMap<String, String>();
		template.put("title", title);
		template.put("desc", desc);
		return template;
	}

	@Override
	public int getUnreadCount() {
		return dao.getOneInfo("msg.getUnreadCount", AuthorityUtils.getLoginSid());
	}

	@Override
	public String getLoginEmail() {
		return dao.getOneInfo("msg.getLoginEmail", AuthorityUtils.getLoginSid());
	}

	@Override
	public PageContainer query(Map<String, String> params) {
		return dao.getSearchPage("msg.query", "msg.queryCount", params);
	}

	@Override
	public Map<String, Object> view(int msgId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sid", AuthorityUtils.getLoginSid());
		params.put("msg_id", msgId);
		dao.update("msg.updateMsg", params);
		return dao.getOneInfo("msg.view", msgId);
	}

	@Override
	public PageContainer queryDeveloper(Map<String, String> params) {
		return dao.getSearchPage("msg.queryDeveloper", "msg.queryDeveloperCount", params);
	}

	@Override
	public Map<String, Object> save(Map<String, String> params) {
		boolean result = false;
		if ("1".equals(params.get("developer"))) {
			result = dao.insert("msg.insertMsgToAll", params) > 0 ? true : false;
		} else {
			result = sendMsg(params);
		}
		Map<String, Object> data = new HashMap<String, Object>();
		if (result) {
			data.put("result", "success");
			data.put("msg", "添加成功");
		} else {
			data.put("result", "fail");
			data.put("msg", "添加失败");
		}
		logService.add(LogType.add, "信息管理-管理员中心-消息通知：添加消息", params, data);
		return data;
	}

}
