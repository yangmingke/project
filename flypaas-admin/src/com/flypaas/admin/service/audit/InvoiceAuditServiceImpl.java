package com.flypaas.admin.service.audit;

import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.admin.constant.AuditConstant;
import com.flypaas.admin.constant.BillConstant;
import com.flypaas.admin.constant.LogConstant.LogType;
import com.flypaas.admin.constant.MsgConstant.MsgType;
import com.flypaas.admin.constant.MsgConstant.TemplateId;
import com.flypaas.admin.constant.SysConstant;
import com.flypaas.admin.dao.MasterDao;
import com.flypaas.admin.model.PageContainer;
import com.flypaas.admin.service.LogService;
import com.flypaas.admin.service.data.MsgService;
import com.flypaas.admin.util.file.FileUtil;

/**
 * 审核管理-发票审核
 * 
 * @author zenglb
 */
@Service
@Transactional
public class InvoiceAuditServiceImpl implements InvoiceAuditService {
	@Autowired
	private MasterDao dao;
	@Autowired
	private LogService logService;
	@Autowired
	private AuditService auditService;
	@Autowired
	private MsgService msgService;

	@Override
	public PageContainer query(Map<String, String> params) {
		params.put("money_rate", SysConstant.money_rate);
		return dao.getSearchPage("invoiceAudit.query", "invoiceAudit.queryCount", params);
	}

	@Override
	public Map<String, Object> view(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("money_rate", SysConstant.money_rate);
		params.put("id", id);
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> entity = dao.getOneInfo("invoiceAudit.getEntity", params);// 获取资质
		String sid = entity.get("sid").toString();
		long money = dao.getOneInfo("invoiceAudit.getAllPayMoneyLastYear", sid);
		long lastMoney = dao.getOneInfo("invoiceAudit.getInvoiceMoney", sid);
		long enable_invoice_fee = money - lastMoney;
		entity.put("enable_invoice_fee", enable_invoice_fee);
		if (entity != null) {
			data.put("entity", entity);
		}
		return data;
	}

	@Override
	public Map<String, Object> audit(Map<String, String> params) {
		Map<String, Object> data = new HashMap<String, Object>();
		int type = NumberUtils.toInt(params.get("type"), -1);// 审核结果
		if (type != AuditConstant.AUDIT_STATUS_PASS && type != AuditConstant.AUDIT_STATUS_NO_PASS) {
			data.put("result", "fail");
			data.put("msg", "不是审核通过或审核不通过，操作失败");
			return data;
		}
		String sid = params.get("sid");
		String id = params.get("id");
		String msg;
		TemplateId templateId;
		if (type == AuditConstant.AUDIT_STATUS_PASS) {
			msg = "审核通过";
			templateId = TemplateId.bill_id_27;
			params.put("bill_status", String.valueOf(BillConstant.BILL_STATUS_2));
		} else {
			msg = "审核不通过";
			templateId = TemplateId.bill_id_28;
			params.put("bill_status", String.valueOf(BillConstant.BILL_STATUS_4));
		}

		int i = dao.update("invoiceAudit.audit", params);
		if (i > 0) {
			Map<String, Object> auditParams = new HashMap<String, Object>();
			auditParams.put("audit_type", AuditConstant.AUDIT_TYPE_BILL);
			auditParams.put("status", type);
			auditParams.put("audit_desc", params.get("audit_desc"));
			auditParams.put("audited_id", id);
			auditService.saveAudit(auditParams);// 保存审核记录

			Map<String, Object> templateParams = new HashMap<String, Object>();
			templateParams.put("billType", "1".equals(params.get("bill_type")) ? "增值税专票" : "增值税普票");
			templateParams.put("reason", params.get("audit_desc"));
			msgService.sendMsg(sid, MsgType.system_msg, templateId, templateParams);// 发送消息
			data.put("result", "success");
			data.put("msg", msg + "成功");
		} else {
			data.put("result", "fail");
			data.put("msg", "开发者不存在，" + msg + "失败");
		}
		logService.add(LogType.update, "审核管理-发票审核：" + msg, params, data);
		return data;
	}

	@Override
	public Map<String, Object> saveReason(Map<String, String> params) {
		Map<String, Object> data = new HashMap<String, Object>();
		int i = auditService.saveReason(AuditConstant.AUDIT_TYPE_BILL, params.get("id"), params.get("audit_desc"));
		if (i > 0) {
			data.put("result", "success");
			data.put("msg", "补充原因成功");
		} else {
			data.put("result", "fail");
			data.put("msg", "开发者不存在，补充原因失败");
		}
		logService.add(LogType.update, "审核管理-发票审核：补充原因", params, data);
		return data;
	}

	@Override
	public Map<String, Object> postBill(Map<String, String> params) {
		Map<String, Object> data = new HashMap<String, Object>();
		int i = dao.update("invoiceAudit.postBill", params);
		if (i > 0) {
			data.put("result", "success");
			data.put("msg", "邮寄操作成功");
			Map<String, Object> templateParams = new HashMap<String, Object>();
			templateParams.put("billType", "1".equals(params.get("bill_type")) ? "增值税专票" : "增值税普票");
			templateParams.put("logistics_company_name",params.get("logistics_company_name"));
			templateParams.put("logistics_no",params.get("logistics_no"));
			msgService.sendMsg(params.get("sid"), MsgType.system_msg, TemplateId.bill_id_29, templateParams);// 发送消息
		} else {
			data.put("result", "fail");
			data.put("msg", "开发者不存在，邮寄操作失败");
		}
		logService.add(LogType.update, "审核管理-发票审核：邮寄", params, data);
		return data;
	}

	@Override
	public Map<String, Object> loadUserInfo(String sid) {
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> entity = dao.getOneInfo("invoiceAudit.loadUserInfo", sid);
		Long order_fee  = dao.getOneInfo("invoiceAudit.getAllPayMoneyLastYear", sid);
		Long bill_fee  = dao.getOneInfo("invoiceAudit.getInvoiceMoney", sid);
		data.put("enable_invoice_fee", (order_fee - bill_fee)/SysConstant.money_rate_int);
		data.put("entity", entity);
		return data;
	}

	@Override
	public Map<String, Object> save(File file,String path,String fileName,Map<String, String> params) {
		Map<String, Object> data = new HashMap<String, Object>();
//		if (dao.getSearchSize("channel.check", params) > 0) {// 查重
//			data.put("result", "fail");
//			data.put("msg", "渠道名称已被使用，请重新输入");
//			return data;
//		}
		if(null != file){
			String identificationimg = FileUtil.upload(file, path, fileName);
			params.put("identificationimg", identificationimg);
		}
		String money = params.get("money");
		Long tmp = new BigDecimal(money).multiply(new BigDecimal(SysConstant.money_rate_int)).longValue();
		params.put("money", String.valueOf(tmp));

		Integer id = NumberUtils.createInteger(params.get("id"));
		if (id == null) {// 添加发票
			int i = dao.insert("invoiceAudit.addInvoiceAddr", params);
			i += dao.insert("invoiceAudit.addInvoice", params);
			if (i > 0) {
				data.put("result", "success");
				data.put("msg", "添加成功");

			} else {
				data.put("result", "fail");
				data.put("msg", "添加失败");
			}
			logService.add(LogType.add, "审核管理-发票审核：开具发票", params, data);

		} else {// 修改发票
			
			int i = dao.update("invoiceAudit.updateInvoiceAddr", params);
			i += dao.update("invoiceAudit.updateInvoice", params);
			if (i > 0) {
				data.put("result", "success");
				data.put("msg", "修改成功");

			} else {
				data.put("result", "fail");
				data.put("msg", "发票不存在，修改失败");
			}
			logService.add(LogType.update, "审核管理-发票审核：修改发票", params, data);
		}
		return data;
	}
}
