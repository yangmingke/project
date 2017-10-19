package com.network.monitor.tag;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.network.monitor.service.TagService;

/**
 * 日期标签
 * 
 * @author xiejiaan
 */
public class DateTag extends BaseTag {
	private static final long serialVersionUID = 2352382415882644083L;

	private String id = "";// html的id、name标记
	private String value = "";// 值
	private String className = "txt_177";// class属性，默认是txt_177
	private String placeholder = "";// 占位符
	private boolean readOnly = true;// 是否只读，默认是true
	// 日期显示格式，默认是yyyy-MM-dd HH:mm:ss
	private String dateFmt;
	private String maxId;// 最大等于另一个文本框的值，第二个参数（用,分割，可以为空））表示差值，如end_date、end_date,-1
	private String minId;// 最小等于另一个文本框的值，第二个参数（用,分割，可以为空）表示差值，如start_date、start_date,-1
	private String maxToday;// 最大等于今天，值（可以为空）表示与今天的差值，-1表示最大等于昨天，以此类推
	private String minToday;// 最小等于今天，值（可以为空）表示与今天的差值，-1表示最小等于昨天，以此类推
	private String startDate;// 起始日期
	// 属性配置（请勿与其他参数重复配置），如isShowClear:true, minDate:'%y-%M-01',
	// maxDate:'%y-%M-%d {%H+30}:%m:%s'
	private String params;

	@Override
	public String getTemplateFile() {
		return "date.ftl";
	}

	@Override
	public Map<String, Object> getTemplateParams(TagService tagService) {
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("id", id);
		p.put("value", value);
		p.put("className", className);
		p.put("placeholder", placeholder);
		p.put("readOnly", readOnly ? "readOnly='readOnly'" : "");

		String dateFmtStr = dateFmt;
		StringBuilder sb = new StringBuilder();
		if (dateFmt != null) {
			sb.append(", dateFmt:'");
			sb.append(dateFmt);
			sb.append("'");
		} else {
			dateFmtStr = "yyyy-MM-dd HH:mm:ss";
		}
		maxId = idExpression(dateFmtStr, maxId);
		minId = idExpression(dateFmtStr, minId);
		maxToday = todayExpression(dateFmtStr, maxToday);
		minToday = todayExpression(dateFmtStr, minToday);

		if (maxId == null) {
			if (maxToday != null) {
				sb.append(", maxDate:'");
				sb.append(maxToday);
				sb.append("'");
			}
		} else {
			sb.append(", maxDate:'#F{");
			sb.append(maxId);
			if (maxToday != null) {
				sb.append("||\\'");
				sb.append(maxToday);
				sb.append("\\'");
			}
			sb.append("}'");
		}
		if (minId == null) {
			if (minToday != null) {
				sb.append(", minDate:'");
				sb.append(minToday);
				sb.append("'");
			}
		} else {
			sb.append(", minDate:'#F{");
			sb.append(minId);
			if (minToday != null) {
				sb.append("||\\'");
				sb.append(minToday);
				sb.append("\\'");
			}
			sb.append("}'");
		}

		if (startDate != null) {
			sb.append(", startDate:'");
			sb.append(startDate);
			sb.append("'");
		}
		if (params != null) {
			sb.append(", ");
			sb.append(params);
		}

		if (sb.length() > 0) {
			p.put("params", "{" + sb.substring(1) + "}");
		} else {
			p.put("params", "");
		}
		return p;
	}

	/**
	 * 生成与另一个文本框比较的表达式
	 * 
	 * @param dateFmt
	 * @param thanId
	 * @return
	 */
	private static String idExpression(String dateFmt, String thanId) {
		if (StringUtils.isNotBlank(thanId)) {
			StringBuilder sb = new StringBuilder();

			if (thanId.indexOf(',') == -1) {
				sb.append("$dp.$D(\\'");
				sb.append(thanId);
				sb.append("\\')");
			} else {
				String[] s = thanId.split(",");
				sb.append("$dp.$D(\\'");
				sb.append(s[0]);
				sb.append("\\', {");
				sb.append(dateFmt.charAt(dateFmt.length() - 1));
				sb.append(":");
				sb.append(s[1]);
				sb.append("})");
			}
			return sb.toString();
		}
		return null;
	}

	/**
	 * 生成与今天比较的表达式
	 * 
	 * @param dateFmt
	 * @param thanToday
	 * @return
	 */
	private static String todayExpression(String dateFmt, String thanToday) {
		if (thanToday != null) {
			StringBuilder sb = new StringBuilder();
			Pattern p = Pattern.compile("([yMdHms]+)[yMdHms]");
			Matcher m = p.matcher(dateFmt);

			if (thanToday.length() == 0) {
				while (m.find()) {
					dateFmt = dateFmt.replaceFirst(m.group(1), "%");
				}
				sb.append(dateFmt);
			} else {
				while (m.find()) {
					dateFmt = dateFmt.replaceFirst(m.group(1), m.hitEnd() ? "{%" : "%");
				}
				sb.append(dateFmt);
				if (Integer.parseInt(thanToday) > 0) {
					sb.append("+");
				}
				sb.append(thanToday);
				sb.append("}");
			}
			return sb.toString();
		}
		return null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	public boolean isReadOnly() {
		return readOnly;
	}

	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	public String getDateFmt() {
		return dateFmt;
	}

	public void setDateFmt(String dateFmt) {
		this.dateFmt = dateFmt;
	}

	public String getMaxId() {
		return maxId;
	}

	public void setMaxId(String maxId) {
		this.maxId = maxId;
	}

	public String getMinId() {
		return minId;
	}

	public void setMinId(String minId) {
		this.minId = minId;
	}

	public String getMaxToday() {
		return maxToday;
	}

	public void setMaxToday(String maxToday) {
		this.maxToday = maxToday;
	}

	public String getMinToday() {
		return minToday;
	}

	public void setMinToday(String minToday) {
		this.minToday = minToday;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

}
