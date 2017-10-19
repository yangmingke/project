package com.flypaas.admin.constant;

/**
 * 消息常量
 * 
 * @author xiejiaan
 */
public class MsgConstant {

	/** 消息类型 */
	public enum MsgType {
		/** 公告 */
		type_1(1),

		/** 资费变更 */
		type_2(2),

		/** 充值确认 */
		type_3(3),

		/** 余额提醒 */
		type_4(4),

		/** 系统消息 */
		system_msg(5),

		/** 管理员指定消息 */
		admin_msg(6);
		private int value;

		MsgType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	/** 消息模板id */
	public enum TemplateId {
		/**
		 * 审核管理-资质审核：通过
		 */
		qualification_pass(1),
		/**
		 * 审核管理-资质审核：不通过
		 */
		qualification_no_pass(2),

		/**
		 * 审核管理-应用审核：通过
		 */
		appAudit_pass(3),
		/**
		 * 审核管理-应用审核：不通过
		 */
		appAudit_no_pass(4),

		/**
		 * 审核管理-短信模板审核：通过
		 */
		smsAudit_pass(5),
		/**
		 * 审核管理-短信模板审核：不通过
		 */
		smsAudit_no_pass(6),

		/**
		 * 审核管理-短信模板审核：短信模板删除
		 */
		sms_template_delete(7),
		/**
		 * 信息管理-开发者管理：开发者的关闭
		 */
		developer_close(8),
		/**
		 * 信息管理-开发者管理：安全校正
		 */
		security_correction(9),
		/**
		 * 信息管理-应用管理：强制下线
		 */
		forced_offline(10),
		/**
		 * 账务管理-资费套餐：开发者转移
		 */
		developer_transfer(11),
		/**
		 * 账务管理-账单信息：账单信息关闭
		 */
		template_id_12(12),
		/**
		 * 账务管理-开发者账务：账户冻结/注销
		 */
		template_id_13(13),
		/**
		 * 账务管理-开发者账务：账户充值/赠送/扣费
		 */
		template_id_14(14),

		/**
		 * 审核管理-协议用户审核:通过需缴保障金
		 */
		contract_id_19(19),
		/**
		 * 审核管理-协议用户审核:通过无需缴保障金
		 */
		contract_id_20(20),
		/**
		 * 审核管理-协议用户审核:不通过
		 */
		contract_id_21(21),

		/**
		 * 信息管理-保障金管理:锁定
		 */
		security_id_22(22),

		/**
		 * 信息管理-保障金管理:解锁
		 */
		security_id_23(23),

		/**
		 * 信息管理-保障金管理:充值
		 */
		security_id_24(24),
		/**
		 * 审核管理-保障金提取:审核通过
		 */
		extraction_id_25(25),

		/**
		 * 审核管理-保障金提取:审核不通过
		 */
		extraction_id_26(26),

		/**
		 * 审核管理-发票审核:审核通过
		 */
		bill_id_27(27),
		/**
		 * 审核管理-发票审核:审核不通过
		 */
		bill_id_28(28),
		/**
		 * 审核管理-发票审核:邮寄
		 */
		bill_id_29(29),
		/**
		 * 审核管理-铃声审核:审核通过
		 */
		ring_pass_30(30),
		/**
		 * 审核管理-铃声审核:审核不通过
		 */
		ring_no_pass_31(31),
		
		/**
		 * 审核管理-语音通知审核:审核通过
		 */
		notify_call_33(33),
		/**
		 * 审核管理-语音通知审核:审核不通过
		 */
		notify_call_34(34),
		
		/**
		 * 审核管理-号码审核:审核通过
		 */
		show_nbr_35(35),
		/**
		 * 审核管理-号码审核:审核不通过
		 */
		show_nbr_36(36);

		private int value;

		TemplateId(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}
}
