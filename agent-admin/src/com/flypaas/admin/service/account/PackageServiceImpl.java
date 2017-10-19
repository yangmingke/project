package com.flypaas.admin.service.account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flypaas.admin.constant.LogConstant.LogType;
import com.flypaas.admin.constant.MsgConstant.MsgType;
import com.flypaas.admin.constant.MsgConstant.TemplateId;
import com.flypaas.admin.constant.PackageConstant;
import com.flypaas.admin.constant.SysConstant;
import com.flypaas.admin.dao.MasterDao;
import com.flypaas.admin.model.PageContainer;
import com.flypaas.admin.service.LogService;
import com.flypaas.admin.service.data.MsgService;
import com.flypaas.admin.util.ConfigUtils;

/**
 * 账务管理-资费套餐
 * 
 * @author xiejiaan
 */
@Service
@Transactional
public class PackageServiceImpl implements PackageService {
	@Autowired
	private MasterDao dao;
	@Autowired
	private LogService logService;
	@Autowired
	private MsgService msgService;

	@Override
	public PageContainer query(Map<String, String> params) {
		params.put("money_rate", SysConstant.money_rate);
		params.put("event_id_1017", String.valueOf(PackageConstant.event_id_1017));
		return dao.getSearchPage("package.query", "package.queryCount", params);
	}

	@Override
	public Map<String, Object> view(Integer packageId) {
		Map<String, Object> data = null;
		if (packageId != null) {
			data = dao.getOneInfo("package.getPackage", packageId);// 获取资费套餐
		}
		if (data == null) {
			data = new HashMap<String, Object>();
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("package_id", packageId);
		params.put("money_rate", SysConstant.money_rate);
		params.put("event_id_1001", PackageConstant.event_id_1001);
		params.put("event_id_1002", PackageConstant.event_id_1002);
		params.put("event_id_1016", PackageConstant.event_id_1016);
		data.put("fee_list", dao.getSearchList("package.getFeeList", params));// 获取资费列表

		data.put("event_id_1003", PackageConstant.event_id_1003);
		data.put("event_id_1004", PackageConstant.event_id_1004);
		data.put("fee_rate_url", ConfigUtils.flypaas_domain + "/user/feeRate");// 查看费率的url
		return data;
	}

	@Override
	public Map<String, Object> save(Map<String, String> params) {
		Map<String, Object> data = new HashMap<String, Object>();
		if (dao.getSearchList("package.checkPackage", params).size() > 0) {// 查重
			data.put("result", "fail");
			data.put("msg", "套餐名称已被使用，请重新输入");
			return data;
		}
		String packageId = params.get("package_id");
		if (StringUtils.isBlank(packageId)) {// 创建资费套餐
			if (dao.insert("package.insertPackage", params) > 0) {
				packageId = dao.getSearchList("package.checkPackage", params).get(0).get("package_id").toString();
				saveFee(packageId, params);// 保存资费

				data.put("result", "success");
				data.put("msg", "创建成功");
			} else {
				data.put("result", "fail");
				data.put("msg", "创建失败");
			}
			logService.add(LogType.add, "账务管理-资费套餐：创建资费套餐", params, data);
		} else {// 修改资费套餐
			if (dao.update("package.updatePackage", params) > 0) {
				saveFee(packageId, params);

				data.put("result", "success");
				data.put("msg", "修改成功");
			} else {
				data.put("result", "fail");
				data.put("msg", "资费套餐不存在，修改失败");
			}
			logService.add(LogType.update, "账务管理-资费套餐：修改资费套餐", params, data);
		}
		return data;
	}

	/**
	 * 保存资费、套餐资费关系
	 * 
	 * @param packageId
	 * @param params
	 */
	private void saveFee(String packageId, Map<String, String> params) {
		params.remove("package_id");
		params.remove("package_name");
		params.remove("charge_type");
		String[] fee;
		for (Entry<String, String> map : params.entrySet()) {
			if (map.getKey().startsWith("fee_")) {
				fee = map.getKey().split("_");// 格式：fee_${event_id}_${is_show_nbr}_${fee_id}_${fee}

				if (fee.length == 3) {// 添加
					Map<String, Object> feeMap = saveFeeItem(fee[1], fee[2], map.getValue());
					feeMap.put("package_id", packageId);
					dao.insert("package.insertFeeItemRel", feeMap);// 添加套餐资费关系

				} else if (fee.length == 5 && !map.getValue().equals(fee[4])) {// 修改
					Map<String, Object> feeMap = saveFeeItem(fee[1], fee[2], map.getValue());
					feeMap.put("package_id", packageId);
					feeMap.put("old_fee_id", fee[3]);
					dao.insert("package.updateFeeItemRel", feeMap);
				}
			}
		}
	}

	/**
	 * 保存资费
	 * 
	 * @param eventId
	 * @param isShowNbr
	 * @param fee
	 * @return
	 */
	private Map<String, Object> saveFeeItem(String eventId, String isShowNbr, String fee) {
		Map<String, Object> feeMap = new HashMap<String, Object>();
		feeMap.put("event_id", eventId);
		feeMap.put("is_show_nbr", isShowNbr);
		feeMap.put("fee", fee);
		feeMap.put("money_rate", SysConstant.money_rate);
		Map<String, Object> data = dao.getOneInfo("package.checkFeeItem", feeMap);// 是否已经存在该资费类型
		if (data != null) {
			feeMap.put("fee_id", data.get("fee_id"));
		} else {
			feeMap.put("fee_id", dao.getOneInfo("package.generateFeeId", feeMap));
			dao.insert("package.insertFeeItem", feeMap);// 添加资费
		}
		return feeMap;
	}

	@Override
	public Map<String, Object> updateStatus(int packageId, int status) {
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		String msg;
		switch (status) {
		case 0:
			msg = "关闭";
			params.put("exp_date", true);
			break;
		case 1:
			msg = "启用";
			params.put("eff_date", true);
			break;
		case 2:
			msg = "删除";
			break;
		default:
			data.put("result", "fail");
			data.put("msg", "不是关闭或启用或删除，操作失败");
			return data;
		}

		params.put("package_id", packageId);
		params.put("status", status);
		int i = dao.update("package.updateStatus", params);// 修改
		if (i > 0) {
			data.put("result", "success");
			data.put("msg", msg + "成功");
		} else {
			data.put("result", "fail");
			data.put("msg", "资费套餐不存在，" + msg + "失败");
		}

		logService.add(LogType.update, "账务管理-资费套餐：修改资费套餐状态", msg, params, data);
		return data;
	}

	@Override
	public Map<String, Object> developerTransfer(String sid, int packageId, int newPackageId, boolean isSendMsg) {
		Map<String, Object> data = new HashMap<String, Object>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sid", sid);
		params.put("package_id", packageId);
		params.put("new_package_id", newPackageId);
		List<Map<String, Object>> packageUser = dao.getSearchList("package.getPackageUser", params);// 获取套餐使用者
		int i = dao.update("package.developerTransfer", params);
		if (i > 0) {
			if (isSendMsg) {
				String packageName = dao.getOneInfo("package.getPackageName", packageId);
				String newPackageName = dao.getOneInfo("package.getPackageName", newPackageId);
				Map<String, Object> templateParams = new HashMap<String, Object>();
				templateParams.put("package_name", packageName);
				templateParams.put("package_id", packageId);
				templateParams.put("new_package_name", newPackageName);
				templateParams.put("new_package_id", newPackageId);
				for (Map<String, Object> map : packageUser) {
					templateParams.put("developer_email", map.get("email"));
					msgService.sendMsg(map.get("sid").toString(), MsgType.system_msg, TemplateId.developer_transfer,
							templateParams);// 发送消息
				}
			}

			data.put("result", "success");
			data.put("msg", "转移成功");
		} else {
			data.put("result", "fail");
			data.put("msg", "资费套餐不存在，转移失败");
		}
		logService.add(LogType.update, "账务管理-资费套餐：开发者转移", params, data);
		return data;
	}
}
