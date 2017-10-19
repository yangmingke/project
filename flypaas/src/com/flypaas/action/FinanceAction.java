package com.flypaas.action;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.joda.time.DateTime;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.entity.Application;

/**
 * 财务管理
 * 
 * @author xiejiaan
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "index", location = "/page/finance/index.jsp") })
public class FinanceAction extends BaseAction {
	/**
	 * 返回数据
	 */
	private Map<String, Object> data = new HashMap<String, Object>();

	/**
	 * 财务总览
	 * 
	 * @return
	 */
	@Action("/finance/index")
	public String index() {
		String sid = getSessionUser().getSid();
		data.put("finance", userService.getFinanceInfo(sid));// 获取账务信息

		DateTime now = DateTime.now();
		String ysd = now.minusDays(1).toString("yyyyMMdd");
		String before = now.minusDays(2).toString("yyyyMMdd");
		Map<String, String> map = new HashMap<String, String>();
		map.put("sid", sid);
		map.put("time", ysd);
		Map<String, Object> csm = consumeService.getYstCsm(map);
		BigDecimal ysdConsume = new BigDecimal(csm == null ? "0.0000" : csm.get("fee").toString());// 昨日消费 

		map = new HashMap<String, String>();
		map.put("sid", sid);
		map.put("time", before);
		csm = consumeService.getYstCsm(map);
		BigDecimal beforeConsume = new BigDecimal(csm == null ? "0.0000" : csm.get("fee").toString());// 前日消费 
		
		data.put("ysdConsume", ysdConsume);
		data.put("beforeConsume", beforeConsume);
		data.put("increaseConsume", ysdConsume.subtract(beforeConsume).abs());// 增长消费
		data.put("compareConsume", ysdConsume.compareTo(beforeConsume));

		List<Application> appList = appService.getAllAppBySid(sid);// 月消费曲线图
		data.put("appList", appList);
		data.put("app", appList.get(0));
		data.put("month", now.getMonthOfYear());
		return "index";
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

}
