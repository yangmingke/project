/**
 * 文本框输入控制，如：<input type="text" value="" onfocus="inputControl.setNumber(this, 10, 2, true)"/>
 */
var inputControl = {

	// 数字控件， 整数部分最大长度，小数部分最大长度，是否允许输入负数（默认不允许）
	setNumber : function(obj, maxIntLength, maxDecimalLength, allowNegative) {
		if (maxIntLength == null || maxIntLength < 1) {
			maxIntLength = 5;
		}
		if (maxDecimalLength == null || maxIntLength < 0) {
			maxDecimalLength = 0;
		}
		if (allowNegative == null) {
			allowNegative = false;
		}

		var maxLength = maxIntLength + maxDecimalLength;
		if (maxDecimalLength > 0) {
			maxLength++;
		}
		if (allowNegative > 0) {
			maxLength++;
		}
		obj.maxLength = maxLength;// 规定最大输入的字符串长度，规则为整数部分长度+小数部分长度+"."+可能的"-"号

		var s = "";
		if (allowNegative)
			s += "\\-?";
		if (maxDecimalLength == 0) {
			s += "\\d{0," + maxIntLength + "}";

		} else {
			s += "(\\d{0," + maxIntLength + "}|\\d{1," + maxIntLength
					+ "}\\.\\d{0," + maxDecimalLength + "})";
		}
		s = "^" + s + "$";
		var regExp = new RegExp(s);

		var control = function() {
			if (regExp.test(obj.value)) {
				obj.o_value = obj.value;
			} else {
				if (obj.o_value == null) {
					obj.o_value = "";
				}
				obj.value = obj.o_value;
			}
		};

		// 格式化数据
		var formatValue = function() {
			var value = obj.value;
			if (value != null && value != "") {
				value = new Number(value).toFixed(maxDecimalLength);
				if (isNaN(value)) {
					obj.value = "";
				} else {
					obj.value = value;
				}
			}
		};

		obj.onblur = function() {
			control();
			if (maxDecimalLength > 0) {
				formatValue();
			}
		};
		obj.onkeypress = function() {
			control();
		};
		obj.onkeyup = function() {
			control();
		};
	},

	/**
	 * 设置文本框只能输入字母和数字
	 * 
	 * @param obj
	 */
	setLetterAndNumber : function(obj) {

		var regExp = new RegExp("^[\\da-zA-Z]*$");

		var control = function() {
			if (regExp.test(obj.value)) {
				obj.o_value = obj.value;
			} else {
				if (obj.o_value == null) {
					obj.o_value = "";
				}
				obj.value = obj.o_value;
			}
		};

		obj.onblur = function() {
			control();
		};
		obj.onkeypress = function() {
			control();
		};
		obj.onkeyup = function() {
			control();
		};
	},

	/**
	 * 自定义输入的数据格式
	 * 
	 * @param obj
	 *            控件
	 * @param regex
	 *            正则表达式字符串
	 */
	setCustom : function(obj, regex) {
		var regExp = new RegExp(regex);

		var control = function() {
			if (regExp.test(obj.value)) {
				obj.o_value = obj.value;
			} else {
				if (obj.o_value == null) {
					obj.o_value = "";
				}
				obj.value = obj.o_value;
			}
		};

		obj.onblur = function() {
			control();
		};
		obj.onkeypress = function() {
			control();
		};
		obj.onkeyup = function() {
			control();
		};
	}

};
