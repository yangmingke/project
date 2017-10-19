package com.flypaas.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.utils.DateUtil;
import com.flypaas.utils.EncryptUtil;
import com.flypaas.utils.StrUtil;


@Controller
@Scope("prototype")
@Results({
	@Result(name = "frFail",location = "/front/index.jsp")
})
public class ChannelAction extends BaseAction {
	
	private Logger logger = LoggerFactory.getLogger(ChannelAction.class);
	@Action("/fr")
	public String fr(){
		String cid = request.getParameter("cid");
		String fr = request.getParameter("fr");
		try {
			fr = EncryptUtil.base64Decoder(fr);
		} catch (Exception e) {
			fr="/user/toSign";
		}
		if(!StrUtil.isEmpty(cid)){
			session.setAttribute("CHANNEL_CID", cid);
			String ip = StrUtil.getClientIP(request);
			Date date = DateUtil.getCurrentDate();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cid", cid);
			map.put("ip", ip);
			map.put("date", date);
			map.put("url", fr);
			channelService.add(map);
		}
		try {
			response.sendRedirect(fr);
			return null;
		} catch (Exception e1) {
			logger.error(e1.getMessage());
			return "frFail";
		}
	}

}
