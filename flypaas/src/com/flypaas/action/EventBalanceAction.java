package com.flypaas.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
@Results({
	@Result(name="EventBalance",location="/page/finance/red_envelope.jsp"),
})
public class EventBalanceAction extends BaseAction {
	private List<Map<String, Object>> dataList;
	@Action("/app/EventBalance")
	public String EventBalance(){
		String sid = getSessionUser().getSid();
		dataList = eventBalanceService.getEventBalanceBySid(sid);
		return "EventBalance";
	}
	public List<Map<String, Object>> getDataList() {
		return dataList;
	}
	public void setDataList(List<Map<String, Object>> dataList) {
		this.dataList = dataList;
	}
	
}
