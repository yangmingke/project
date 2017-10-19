package com.flypaas.admin.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.admin.constant.BillConstant;
import com.flypaas.admin.dao.MasterDao;

/**
 * 充值，扣费，赠送操作
 * 
 * @author zenglb
 */
@Service
@Transactional
public class BillDtlServiceImpl implements BillDtlService {

	@Autowired
	private MasterDao dao;

	@Override
	public int recharge(Map<String, String> params) {
		int tmp = -1;
		tmp = dao.update("billdtl.rechargeOrGifts", params);
		if (tmp > 0) {
			params.put("charge", params.get("cv"));
			params.put("charge_type", BillConstant.RECHARGE_TYPE_RECHARG);
			params.put("status", BillConstant.PAY_STATUS_3 + "");
			dao.update("billdtl.insertPayOrder", params);

			dao.update("billdtl.updateRemindBySid", params);
		}
		return tmp;
	}

	@Override
	public int gifts(Map<String, String> params) {
		int tmp = -1;
		tmp = dao.update("billdtl.rechargeOrGifts", params);
		if (tmp > 0) {
			params.put("charge", params.get("cv"));
			params.put("charge_type", BillConstant.RECHARGE_TYPE_GIFTS);
			params.put("status", BillConstant.PAY_STATUS_3 + "");
			dao.update("billdtl.insertPayOrder", params);

			dao.update("billdtl.updateRemindBySid", params);
		}
		return tmp;
	}

	@Override
	public int deduction(Map<String, String> params) {
		int tmp = -1;
		tmp = dao.update("billdtl.deduction", params);
		if (tmp > 0) {
			params.put("charge", params.get("cv"));
			params.put("charge_type", BillConstant.RECHARGE_TYPE_DEDUCTION);
			params.put("status", BillConstant.PAY_STATUS_3 + "");
			dao.update("billdtl.insertPayOrder", params);
		}
		return tmp;
	}
}
