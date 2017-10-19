package com.flypaas.action;

//import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.entity.vo.ClientVo;
//import com.flypaas.utils.DateUtil;
//import com.flypaas.utils.JsonUtil;

@Controller
@Scope("prototype")
@Results({
	@Result(name="clientLstSuc",location="/page/app/client.jsp")
})
public class ClientAction extends BaseAction {
	
	private List<ClientVo> clientVoList;
	private ClientVo vo;
	
	private String month ;
	private String year;
	
	private List<String> yearList;
	private String clientNumber;
	
	private List<Map<String,Object>> billList ;
	/*---------------------------------------------client列表--------------------------------*/
//	@Action("/app/client")
//	public String clientList(){
//		if(vo==null){
//			vo = new ClientVo();
//		}
//		String sid = getSessionUser().getSid();
//		vo.setSid(sid);
//		String term = vo.getTerm();
//		if(term!=null&&!term.equals("")){
//			int type = dist(term);
//			if(type==1){
//				vo.setMobile(term);
//			}else if(type==2){
//				vo.setClientNumber(term);
//			}else{
//				vo.setAppName(term);
//			}
//		}
//		clientVoList = clientService.getClientListBySid(vo);
//		yearList  = buildYearList();
//		month = DateUtil.dateToStr(DateUtil.getCurrentDate(), "M") ;
//		year = DateUtil.dateToStr(DateUtil.getCurrentDate(), "yyyy") ;
//		return "clientLstSuc";
//	}
//	@Action("/app/clientMonthBill")
//	public void clientMonthBill(){
//		Map<String,Object> param = new HashMap<String, Object>();
//		yearList  = buildYearList();
//		if(month==null ||month.equals("")){
//			param.put("billing", DateUtil.dateToStr(DateUtil.getCurrentDate(), "yyyyMM"));
//			month = DateUtil.dateToStr(DateUtil.getCurrentDate(), "M") ;
//			year = DateUtil.dateToStr(DateUtil.getCurrentDate(), "yyyy") ;
//		}else{
//			param.put("billing", year+"0"+month);
//		}
//		param.put("clientNumber", clientNumber);
//		billList = consumeService.getClientMonthBill(param);
//		String jsonStr = JsonUtil.ArrayToJsonStr(billList);
//		printMsg(jsonStr+"");
//	}
//	private List<String> buildYearList(){
//		String cYear =  DateUtil.dateToStr(DateUtil.getCurrentDate(), "yyyy");
//		String lastYear = DateUtil.getLastYear();
//		List<String> list = new ArrayList<String>();
//		list.add(cYear);
//		list.add(lastYear);
//		return list;
//	}
//	private int dist(String term){
//		//1：手机 2：账号 3：应用名
//		int type ;
//		try {
//			Long.parseLong(term);
//			if(term.length()==11){
//				type = 1;
//			}else{
//				type = 2;
//			}
//		} catch (Exception e) {
//			type = 3;
//		}
//		return type;
//	}
	/*---------------------------------------------get set--------------------------------*/
	public List<ClientVo> getClientVoList() {
		return clientVoList;
	}
	public void setClientVoList(List<ClientVo> clientVoList) {
		this.clientVoList = clientVoList;
	}

	public ClientVo getVo() {
		return vo;
	}

	public void setVo(ClientVo vo) {
		this.vo = vo;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public List<String> getYearList() {
		return yearList;
	}
	public void setYearList(List<String> yearList) {
		this.yearList = yearList;
	}

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	public List<Map<String, Object>> getBillList() {
		return billList;
	}

	public void setBillList(List<Map<String, Object>> billList) {
		this.billList = billList;
	}
	
	
}
