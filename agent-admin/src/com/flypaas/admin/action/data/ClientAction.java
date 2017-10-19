package com.flypaas.admin.action.data;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.flypaas.admin.util.file.BizException;
import com.flypaas.admin.util.web.AuthorityUtils;
import com.flypaas.admin.util.web.StrutsUtils;
import com.flypaas.admin.action.BaseAction;
import com.flypaas.admin.service.data.ClientService;




/**
 * 查询 client  相关 
 * 查询运营统计相关
 * @author chengxu
 */

@Controller
@Scope("prototype")

@Results({ 
@Result(name = "query", location = "/WEB-INF/content/data/clientTestNum/query1.jsp"),
@Result(name = "query2", location = "/WEB-INF/content/data/clientTestNum/query2.jsp"),
@Result(name = "query3", location = "/WEB-INF/content/data/clientTestNum/query3.jsp"),
@Result(name = "query4", location = "/WEB-INF/content/data/clientTestNum/query4.jsp"),
@Result(name = "view", location = "/WEB-INF/content/data/clientTestNum/view.jsp"),
@Result(name = "view2", location = "/WEB-INF/content/data/clientTestNum/view2.jsp"),
@Result(name = "view3", location = "/WEB-INF/content/data/clientTestNum/view3.jsp"),
@Result(name = "view4", location = "/WEB-INF/content/data/clientTestNum/view4.jsp")

})

public class ClientAction extends BaseAction {
	private static final long serialVersionUID = -6398668354512197029L;
	
	@Autowired
	private ClientService clientservice;
	/**
	 * 
	 *  client管理
	 * @author chengxu
	 */
	@Action("/client/query")
	public String query() {
		System.out.println("进入1----------------------------");
		page = clientservice.query(StrutsUtils.getFormData());
		System.out.println(page);
		return "query";	
	}
	
	/**
	 * 
	 *  client管理查看
	 * @author chengxu
	 */
	@Action("/client/view")
	public  String view()
	{
		System.out.println("进入2-----------------------");
		Map<String, String> params = StrutsUtils.getFormData();
		params.put("sid", AuthorityUtils.getLoginSid());
		data = clientservice.getClient(params);
		return "view";
		
	}
	
	/**
	 * 
	 *  client账务
	 * @author chengxu
	 */
	@Action("/clientBill/query")
	public  String clientbill()
	{
		System.out.println("进入3-----------------------");
		page = clientservice.query2(StrutsUtils.getFormData());
		System.out.println(page);
		return "query2";
		
	}
	

	/**
	 * 
	 *  client账务查看
	 * @author chengxu
	 */
	@Action("/clientBill/view")
	public  String clientbillview()
	{
		System.out.println("进入4-----------------------");
		Map<String, String> params = StrutsUtils.getFormData();
		params.put("sid", AuthorityUtils.getLoginSid());
		data = clientservice.getClientbill(params);
		return "view2";
		
	}
	
	/**
	 * 
	 *  开发者注册充值统计
	 * @author chengxu
	 */
	
	@Action("/developerCharge/query")
	public  String developerChargequery()
	{
		System.out.println("进入5-----------------------");
	
		page = clientservice.query3(StrutsUtils.getFormData());
		System.out.println(page);
		return "query3";
		
	}
	/**
	 * 
	 *  注册充值统计查看
	 * @author chengxu
	 */
	@Action("/developerCharge/view")
	public  String chargeLog()
	{
		System.out.println("进入6----------------"
				+ "-------");
	
		Map<String, String> params = StrutsUtils.getFormData();
		params.put("sid", AuthorityUtils.getLoginSid());
		data = clientservice.getChargebill(params);
		return "view3";
		
	}
	
	
	/**
	 * 
	 *  充值日志
	 * @author chengxu
	 */
	@Action("/ChargeLog/query")
	public  String ChargeLog()
	{
		System.out.println("进入7-----------------------");
		page = clientservice.query4(StrutsUtils.getFormData());
		System.out.println(page);
		return "query4";
		
	}
	/**
	 * 
	 *  充值日志查看
	 * @author chengxu
	 */
	@Action("/ChargeLog/view")
	public  String Chargeview()
	{
		System.out.println("进入8-----------------------");
		Map<String, String> params = StrutsUtils.getFormData();
		params.put("sid", AuthorityUtils.getLoginSid());
		data = clientservice.getChargeview(params);
		return "view4";
		
	}
	
	


}
