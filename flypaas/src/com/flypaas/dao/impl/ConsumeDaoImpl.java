package com.flypaas.dao.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.flypaas.dao.ConsumeDao;
import com.flypaas.daocenter.MyBatisDao;
import com.flypaas.daocenter.StatisticsMyBatisDao;
import com.flypaas.entity.vo.PageContainer;

@Repository
public class ConsumeDaoImpl extends StatisticsMyBatisDao implements ConsumeDao {
	@Autowired
	private MyBatisDao myBatisDao;
	
	private static final String GETTODAYCONSUME = "getTodayCsm";
	private static final String GETTODAYTRAFFICCONSUME = "getTodayTrafficCsm";
	private static final String GETBILLMONTH = "getBillMonth";
	private static final String GETBILLCSMSUM = "getBillCsmSum";
	private static final String GETCLIENTMONTHBILL = "getClientMonthBill";
	private static final String ISEXISTSTABLE = "isExistsTable";
	private static final String GETFASTDFS = "getFastDFS";
	private static final String GETYSTCSM="getYstCsm";
	private static final String APPCSMDATA="appCsmData";
	private static final String LYDETAIL="lyDetail";
	private static final String LYDETAILCOUNT="lyDetailCount";
	
	
	private static final Map<String, String> SQL_KEY_MAP = new HashMap<String, String>();
	static {
		SQL_KEY_MAP.put("voice", "getapp45csmVoice");
		SQL_KEY_MAP.put("sms", "getAppSmsLog");
		SQL_KEY_MAP.put("im", "getAppCsmImLog");
		SQL_KEY_MAP.put("voicecode", "getAppVoiceCodeLog");
		SQL_KEY_MAP.put("client", "getAppClientLog");
		SQL_KEY_MAP.put("mid", "getApp45csmMid");
		SQL_KEY_MAP.put("voicenotify", "getApp45csmVoiceNotify");
		SQL_KEY_MAP.put("gjmy", "getApp45csmGjmy");
	}

	public List<Map<String, Object>> getCurrentCsm(Map<String, Object> map) {
		return sqlSessionTemplate.selectList(GETTODAYCONSUME, map);
	}
	
	public List<Map<String, Object>> getCurrentTrafficCsm(Map<String, Object> map) {
		return sqlSessionTemplate.selectList(GETTODAYTRAFFICCONSUME, map);
	}
	

	public PageContainer getApp45Csm(PageContainer page) {
		PageContainer result = null;
		Map<String, Object> params = page.getParams();
		String type = null == params.get("type") ? "" : params.get("type").toString();
		String sqlMapKey = SQL_KEY_MAP.get(type);
		if ("client".equals(type)) {
			result = myBatisDao.getSearchPage(sqlMapKey, sqlMapKey + "Count", page);
			result.getParams().put("total", "0.0000");
//			result.setParams(myBatisDao.getOneInfo(sqlMapKey + "Total", params));
		} else {
			params.put("pageIndex", page.getCurrentPage());
			params.put("pageCount", page.getPageRowCount());
			params.put("totalCount", 0);
			params.put("total", 0);
			List<Map<String, Object>> rs = sqlSessionTemplate.selectList(sqlMapKey, params);
			result = page;
			result.setList(rs);
			int totalCount = 0;
			if (null != params.get("totalCount")) {
				totalCount = Integer.valueOf(params.get("totalCount") + "");
			}
			result.setTotalCount(totalCount);
			
			BigDecimal total = new BigDecimal(0);
			if (null != params.get("total")) {
				total = new BigDecimal(params.get("total") + "").divide(new BigDecimal(1000000));
			}
			params.put("total",String.format("%.4f",total));
			int totalPage = totalCount / result.getPageRowCount()
					+ (totalCount % result.getPageRowCount() == 0 ? 0 : 1);
			result.setTotalPage(totalPage);
		}
		params = result.getParams();
		if (null == params) {
			params = new HashMap<String, Object>();
			result.setParams(params);
		}
		if (null == params.get("total")) {
			params.put("total", "0.0000");
		}
		return result;
	}

	public List<Map<String, Object>> getBillMonth(Map<String, Object> param) {
		return sqlSessionTemplate.selectList(GETBILLMONTH, param);
	}

	public String getBillCsmSum(Map<String, Object> param) {
		return sqlSessionTemplate.selectOne(GETBILLCSMSUM, param);
	}

	public List<Map<String, Object>> getClientMonthBill(Map<String, Object> param) {
		return sqlSessionTemplate.selectList(GETCLIENTMONTHBILL, param);
	}

	public int isExistsTable(String tableName) {
		return sqlSessionTemplate.selectOne(ISEXISTSTABLE, tableName);
	}

	public List<Map<String, Object>> getFastDFS(String sql) {
		return sqlSessionTemplate.selectList(GETFASTDFS, sql);
	}

	public Map<String, Object> getYstCsm(Map<String, String> map) {
		return sqlSessionTemplate.selectOne(GETYSTCSM, map);
	}

	public List<Map<String, Object>> appCsmDataByMonth(Map<String, String> map) {
		return sqlSessionTemplate.selectList(APPCSMDATA, map);
	}

	public PageContainer lyDetail(PageContainer page) {
		return getSearchPage(LYDETAIL, LYDETAILCOUNT, page);
	}
}
