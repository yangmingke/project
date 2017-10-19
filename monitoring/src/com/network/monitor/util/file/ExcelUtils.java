package com.network.monitor.util.file;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.util.Objects;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.network.monitor.util.rest.vo.Excel;

/**
 * Excel文件工具类
 * 
 * @author xiejiaan
 */
public class ExcelUtils {
	private static final Logger logger = LoggerFactory.getLogger(ExcelUtils.class);

	/**
	 * 导出Excel文件
	 * 
	 * @param excel
	 *            Excel文件信息
	 * @return
	 */
	public static boolean exportExcel(Excel excel,OutputStream out) {
//		String filePath = excel.getFilePath();
		String title = excel.getTitle();
		List<String> remarkList = excel.getRemarkList();
		List<Map<String, String>> headerList = excel.getHeaderList();
		List<Map<String, Object>> dataList = excel.getDataList();
		int pageRowCount = excel.getPageRowCount();
		boolean showPage = excel.isShowPage();
		boolean showRownum = excel.isShowRownum();

		if (dataList == null || dataList.size() < 1) {
			logger.debug("【导出Excel文件】数据为空");
			return true;
		}
		if (showRownum) {
			Map<String, String> header = new HashMap<String, String>();
			header.put("name", "序号");
			header.put("width", "10");
			header.put("key", "rownum");
			headerList.add(0, header);

			int i = 1;
			for (Map<String, Object> data : dataList) {
				data.put("rownum", i++);
			}
		}

//		FileUtils.makeDir(filePath);
		WritableWorkbook workbook = null;
		try {
			// 标题样式
			WritableFont titleFont = new WritableFont(WritableFont.createFont("宋体"), 24, WritableFont.BOLD, false,
					UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
			WritableCellFormat titleFormat = new WritableCellFormat(NumberFormats.TEXT);
			titleFormat.setFont(titleFont);
			titleFormat.setBorder(Border.BOTTOM, BorderLineStyle.THIN);
			titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			titleFormat.setAlignment(Alignment.CENTRE);

			// 备注样式
			WritableFont remarkFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false,
					UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
			WritableCellFormat remarkFormat = new WritableCellFormat(NumberFormats.TEXT);
			remarkFormat.setFont(remarkFont);
			remarkFormat.setVerticalAlignment(VerticalAlignment.CENTRE);

			// 表头样式
			WritableFont headerFont = new WritableFont(WritableFont.createFont("宋体"), 12, WritableFont.BOLD, false,
					UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
			WritableCellFormat headerFormat = new WritableCellFormat(NumberFormats.TEXT);
			headerFormat.setFont(headerFont);
			headerFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			headerFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			headerFormat.setAlignment(Alignment.CENTRE);
			headerFormat.setBackground(Colour.GRAY_25);

			// 正文样式
			WritableFont bodyFont = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false,
					UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
			WritableCellFormat bodyFormat = new WritableCellFormat(NumberFormats.TEXT);
			bodyFormat.setFont(bodyFont);
			bodyFormat.setBorder(Border.ALL, BorderLineStyle.THIN);
			bodyFormat.setVerticalAlignment(VerticalAlignment.CENTRE);

			workbook = Workbook.createWorkbook(out);
			WritableSheet sheet = null;
			int totalCount = dataList.size();// 总行数
			int currentPage = 0; // 当前页号
			int totalPage = totalCount / pageRowCount + (totalCount % pageRowCount == 0 ? 0 : 1);// 总页数

			int pageNode = 0;// 当前分页节点
			int rownum = 0;// 当前行数
			int columnCount = headerList.size();// 总列数
			for (int i = 0; i < totalCount; i++) {
				if (i >= pageNode) {// 分页
					rownum = 0;
					currentPage++;
					pageNode = pageRowCount * currentPage;
					sheet = workbook.createSheet("第" + currentPage + "页", currentPage - 1);
					sheet.getSettings().setShowGridLines(false); // 去掉整个sheet中的网格线
					sheet.setRowView(0, 700);
					sheet.mergeCells(0, 0, columnCount - 1, 0);// 合并单元格
					sheet.addCell(new Label(0, rownum++, title, titleFormat));// 标题

					for (String remark : remarkList) {
						sheet.mergeCells(0, rownum, columnCount - 1, rownum);
						sheet.addCell(new Label(0, rownum++, remark, remarkFormat));// 备注
					}
					if (showPage) {
						sheet.mergeCells(0, rownum, columnCount - 1, rownum);
						sheet.addCell(new Label(0, rownum++, "共" + totalCount + "条纪录，每页显示" + pageRowCount + "条，当前"
								+ currentPage + "/" + totalPage + "页", remarkFormat));
					}

					for (int j = 0; j < columnCount; j++) {
						sheet.setColumnView(j, Integer.parseInt(headerList.get(j).get("width")));
						sheet.addCell(new Label(j, rownum, headerList.get(j).get("name"), headerFormat));// 表头
					}
					rownum++;
				}

				Map<String, Object> data = dataList.get(i);
				for (int j = 0; j < columnCount; j++) {
//					sheet.addCell(new Label(j, rownum, Objects.toString(data.get(headerList.get(j).get("key")), null),
//							bodyFormat));// 正文
				}
				rownum++;
			}
			workbook.write();
			logger.debug("【导出Excel文件】成功");
			return true;
		} catch (Throwable e) {
			logger.error("【导出Excel文件】失败",e);
		} finally {
			try {
				if (workbook != null) {
					workbook.close();
				}
			} catch (Throwable e) {
				logger.error("【导出Excel文件】关闭失败", e);
			}
		}
		return false;
	}

	public static void main(String[] args) {
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("key1", "数据11");
		data.put("key2", "数据12");
		data.put("key3", "数据13");
		dataList.add(data);
		data = new HashMap<String, Object>();
		data.put("key1", "数据21");
		data.put("key2", "数据22");
		data.put("key3", "数据23");
		dataList.add(data);

		Excel excel = new Excel();
		excel.setFilePath("E:/home/导出Excel文件.xls");
		excel.setTitle("标题");
		excel.addRemark("备注1");
		excel.addRemark("备注2");
		excel.addRemark("备注3");
		excel.addHeader(10, "表头1", "key1");
		excel.addHeader(20, "表头2", "key2");
		excel.addHeader(30, "表头3", "key3");
		excel.addHeader(40, "表头4", "key4");
		excel.setDataList(dataList);

		ExcelUtils.exportExcel(excel,null);
	}

}
