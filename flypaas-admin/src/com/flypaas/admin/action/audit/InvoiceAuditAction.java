package com.flypaas.admin.action.audit;

import java.io.File;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.flypaas.admin.action.BaseAction;
import com.flypaas.admin.service.audit.InvoiceAuditService;
import com.flypaas.admin.util.ConfigUtils;
import com.flypaas.admin.util.web.StrutsUtils;

/**
 * 审核管理-发票审核
 * 
 * @author zenglb
 */
@Controller
@Scope("prototype")
@Results({ @Result(name = "query", location = "/WEB-INF/content/audit/invoiceAudit/query.jsp"),
		@Result(name = "edit", location = "/WEB-INF/content/audit/invoiceAudit/edit.jsp") ,
		@Result(name = "view", location = "/WEB-INF/content/audit/invoiceAudit/view.jsp") })
public class InvoiceAuditAction extends BaseAction {
	private static final long serialVersionUID = -8050303601312051986L;
	private InvoiceAuditService invoiceAuditService;

	@Resource
	public void setInvoiceAuditService(InvoiceAuditService invoiceAuditService) {
		this.invoiceAuditService = invoiceAuditService;
	}

	private File identificationimg;
	private String identificationimgFileName;
	
	public File getIdentificationimg() {
		return identificationimg;
	}

	public void setIdentificationimg(File identificationimg) {
		this.identificationimg = identificationimg;
	}

	public String getIdentificationimgFileName() {
		return identificationimgFileName;
	}

	public void setIdentificationimgFileName(String identificationimgFileName) {
		this.identificationimgFileName = identificationimgFileName;
	}

	/**
	 * 列表
	 * 
	 * @return
	 */
	@Action("/invoiceAudit/query")
	public String query() {
		page = invoiceAuditService.query(StrutsUtils.getFormData());
		return "query";
	}

	/**
	 * 明细页面
	 * 
	 * @return
	 */
	@Action("/invoiceAudit/view")
	public String view() {
		String id = StrutsUtils.getParameterTrim("id");
		if (StringUtils.isNotBlank(id)) {
			data = invoiceAuditService.view(Long.valueOf(id));
		}
		return "view";
	}
	@Action("/invoiceAudit/edit")
	public String edit() {
		String id = StrutsUtils.getParameterTrim("id");
		if (StringUtils.isNotBlank(id)) {
			data = invoiceAuditService.view(Long.valueOf(id));
		}
		return "edit";
	}

	/**
	 * 审核
	 * 
	 * @return
	 */
	@Action("/invoiceAudit/audit")
	public void audit() {
		data = invoiceAuditService.audit(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}

	/**
	 * 邮寄发票
	 * 
	 * @return
	 */
	@Action("/invoiceAudit/postBill")
	public void postBill() {
		data = invoiceAuditService.postBill(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}

	/**
	 * 保存补充原因
	 * 
	 * @return
	 */
	@Action("/invoiceAudit/saveReason")
	public void saveReason() {
		data = invoiceAuditService.saveReason(StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}
	
	/**
	 *开发票
	 * @return
	 */
	@Action("/invoiceAudit/createInvoice")
	public String createInvoice(){
		String sid = StrutsUtils.getParameterTrim("sid");
		data = invoiceAuditService.loadUserInfo(sid);
		return "edit";
	}

	/**
	 * 保存
	 * 
	 * @return
	 */
	@Action("/invoiceAudit/save")
	public void save() {
		String path = null;
		String fileName = null;
		if(null != identificationimg){
			String sid  = StrutsUtils.getParameterTrim("sid");
			path = ConfigUtils.resource_base_path +"/"+ sid + "/";
			fileName  = sid + "_"+System.currentTimeMillis() +
						identificationimgFileName.substring(identificationimgFileName.lastIndexOf("."));	
		}
		
		data = invoiceAuditService.save(identificationimg,path,fileName,StrutsUtils.getFormData());
		StrutsUtils.renderJson(data);
	}

}
