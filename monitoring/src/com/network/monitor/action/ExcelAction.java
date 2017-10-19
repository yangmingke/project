package com.network.monitor.action;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.network.monitor.service.CommonService;
import com.network.monitor.util.encrypt.Des3Utils;
import com.network.monitor.util.file.ExcelUtils;
import com.network.monitor.util.rest.utils.JsonUtil;
import com.network.monitor.util.rest.vo.Excel;
import com.network.monitor.util.web.StrutsUtils;

@Controller
@Scope("prototype")
public class ExcelAction extends BaseAction{
	private static final long serialVersionUID = 4783786334291109411L;
	private HttpServletResponse response;
	Logger logger = LoggerFactory.getLogger(ExcelAction.class);
	@Autowired
	private CommonService commonService;
	
	@SuppressWarnings("unchecked")
	@Action("/excel")
	public void excel() throws IOException {
		Map<String, Object> map = (Map<String, Object>) StrutsUtils.getSession().getAttribute("excel");
		String head = map.get("head").toString();
		String content = map.get("content").toString();
		String title = map.get("title").toString();
		String fileName = map.get("fileName").toString();
		String sqlId = map.get("sqlId")==null?null:map.get("sqlId").toString();
		String sqlParams = map.get("sqlparams")==null?null:map.get("sqlparams").toString();
		String data = map.get("data")==null?null:map.get("data").toString();
		response = StrutsUtils.getResponse();
		response.setCharacterEncoding("gbk");
		if(head==null||content==null){
			print("表头数据和列数据对应不上！");
		}
		if(data==null && sqlId==null){
			print("没有要查询的数据！");
			return;
		}
		if(data!=null){
			dataList = JsonUtil.jsonStrToArray(data);
		}else{
			Map<String, Object> params = new HashMap<String, Object>();
			Map<String, Object> params1 = JsonUtil.jsonStrToMap(Des3Utils.decodeDes3(sqlParams));
			if(params1!=null){
				params.putAll(params1);
			}
			params.put("sqlId", sqlId);
			params.remove("limit");
			dataList = commonService.getExcelData(params);
		}
		if(dataList.size()==0){
			print("没有数据！");
			return;
		}
		if(dataList.size()>10000){
			print("最多只能导出10000条数据！");
			return;
		}
		Excel excel= new Excel();
		excel.setDataList(dataList);
		String [] headArray = head.split("\\,");
		String [] contentArray = content.split("\\,");
		if(headArray.length==0||contentArray.length==0){
			print("表头数据和列数据对应不上！");
			return;
		}
		for(int i=0;i<headArray.length;i++){
			excel.addHeader(20, headArray[i], contentArray[i]);
		}
		excel.setTitle(title==null?"":title);
		
		OutputStream out;
		try {
			out = response.getOutputStream();
			response.setCharacterEncoding("GBK");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ new String((fileName+".xls").getBytes("GBK"), "ISO-8859-1"));
			response.setContentType("application/msexcel");
			ExcelUtils.exportExcel(excel, out);
		} catch (IOException e) {
			logger.error("导出excel错误."+e);
		}
	}
	
	private void print(String content) throws IOException{
		StringBuffer sb = new StringBuffer();
		sb.append("<h2>");
		sb.append(content);
		sb.append("</h2>");
		PrintWriter wt = response.getWriter();
		wt.print(sb.toString());
		wt.flush();
		wt.close();
		return;
	}
}
