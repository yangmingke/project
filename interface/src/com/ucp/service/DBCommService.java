/*
 * 描    述:  <描述>
 * 修 改 人:  duanyj
 */
package com.ucp.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.tools.DBUtil;
import com.tools.StreamUtil;
import com.tools.TimeUtil;
import com.ucp.bean.ClientFee;
import com.ucp.util.Consts;
import com.ucp.util.SqlCode;
/**
 * @author duanyj_1076
 */
public class DBCommService{
    private static final Logger logger = Logger.getLogger(DBCommService.class);
    private static DBCommService dbCommService=new DBCommService();
    public static DBCommService getInstance(){
    	return dbCommService;
    }
    private Map<String, String> sqlMap;
    public Map<String, String> getSqlMap(){
    	return sqlMap;
    }
    public void setSqlMap(String sql,List<Object[]> paramList){
    	sqlMap=queryStrMap(sql, paramList);
    }
    private List<Map<String,String>> dspList;
    
    public List<Map<String,String>> getDspList() {
		return dspList;
	}
	public void setDspList() {
		String sql="select * from tb_user_dsp where stype=1 and `status`=1 ";
		this.dspList = queryForListStr(sql, null, false);
	}
	public boolean isDsp(String sid){
		if (dspList!=null&&dspList.size()>0) {
			for (int i = 0; i < dspList.size(); i++) {
				if (dspList.get(i).get("sid").equals(sid)) {
					return true;
				}
			}
		}
		return false;
	}
	public String getSql(String sqlCode){
    	String sql=sqlMap.get(sqlCode);
    	if (StringUtils.isEmpty(sql)) {
			String qSql=SqlCode.QUERY_SQL_CODE+" where sql_code="+sqlCode;
			Map<String, String> map = queryStrMap(qSql, null);
			sql=map.get(sqlCode);
			sqlMap.putAll(map);
		}
		return sql;
	}
    public void refreshSqlMap(){
    	sqlMap.clear();
    	if (this.dspList!=null) {
    		this.dspList.clear();
		}
    	setDspList();
    	setSqlMap(SqlCode.QUERY_SQL_CODE, null);
    }
    public Map<String, String> callProc(String proc,List<Object[]> paramList){
    	Connection conn = null;
    	CallableStatement pstmt = null;
        try {
//        	conn = DBUtil.getInstance().getConnection(Consts.CON_POOL);
        	conn = DBUtil.getInstance().getConnection(Consts.CON_MAIN);
        	logger.debug(proc);
            pstmt = conn.prepareCall(proc);
            if (paramList!=null&&paramList.size()>0) {
            	for (int i = 1; i <=paramList.size(); i++) {
            		Object[] objects=paramList.get(i-1);
            		System.out.print(objects[1]+",");
            		if (objects[0].equals("number")) {
						pstmt.setLong(i, Long.parseLong(objects[1].toString()));
					}else if (objects[0].equals("string")) {
						pstmt.setString(i, objects[1].toString());
					}else if (objects[0].equals("onumber")){
						pstmt.registerOutParameter(i, java.sql.Types.INTEGER);
					}else if (objects[0].equals("ostring")){
						pstmt.registerOutParameter(i, java.sql.Types.VARCHAR);
					}
    			}
			}
            pstmt.execute();
            Map<String, String> hashMap=new HashMap<String, String>();
            if (paramList!=null&&paramList.size()>0) {
            	for (int i = 1; i <=paramList.size(); i++) {
            		Object[] objects=paramList.get(i-1);
            		if (objects[0].equals("onumber")){
            			long code=pstmt.getLong(i);
            			hashMap.put("code", code+"");
            		}else if (objects[0].equals("ostring")){
            			String clientNumber=pstmt.getString(i);
            			hashMap.put("clientNumber",clientNumber);
					}
            	}
            }
            return hashMap;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("执行异常sqlCode="+proc,e);
			return null;
		}finally{
			DBUtil.closePStmt(pstmt);
			DBUtil.closeConn(conn);
		}
    }
    public int deFee3(ClientFee clientFee,String addClientSql){
    	Connection conn = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        int res=0;
        try {
        	List<Object[]> paramList = new ArrayList<Object[]>();
			paramList.add(new Object[]{"string",clientFee.getAcctId()});
        	conn = DBUtil.getInstance().getConnection(Consts.CON_POOL);
        	conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(addClientSql);
            pstmt.setString(1,clientFee.getClientId());
            pstmt.setString(2, clientFee.getAppId());
            pstmt.setString(3,clientFee.getClientName());
            pstmt.setString(4, clientFee.getClientNumber());
            pstmt.setString(5, clientFee.getPwd());
            pstmt.setString(6, clientFee.getToken());
            pstmt.setString(7, clientFee.getStatus());
            pstmt.setString(8, clientFee.getAcctId());
            pstmt.setString(9, "0");
            pstmt.setString(10, clientFee.getClientType());
            pstmt.setString(11, clientFee.getMobile());
            pstmt.setString(12, clientFee.getcType());
            res=pstmt.executeUpdate();
            DBUtil.closePStmt(pstmt);
            //添加子账户余额记录
            String sql4="insert into tb_bill_client_balance(sid,client_number,balance,enable_flag,create_time)values(?,?,?,'1',now())";
            pstmt2=conn.prepareStatement(sql4);
            pstmt2.setString(1, clientFee.getAcctId());
            pstmt2.setString(2, clientFee.getClientNumber());
            pstmt2.setLong(3, Long.parseLong(clientFee.getCharges()));
            int res2=pstmt2.executeUpdate();
            DBUtil.closePStmt(pstmt2);
            res=res+res2;
            conn.commit();
            conn.setAutoCommit(true);
            return res;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("执行异常",e);
			return -1;
		}finally{
			DBUtil.closeConn(conn);
		}
    }
    public int deFee(ClientFee clientFee){
    	Connection conn = null;
//        PreparedStatement pstmt = null;
//        PreparedStatement pstmt2 = null;
        PreparedStatement pstmt3 = null;
        PreparedStatement pstmt4 = null;
        int res=0;
        try {
        	List<Object[]> paramList = new ArrayList<Object[]>();
			paramList.add(new Object[]{"string",clientFee.getAcctId()});
        	conn = DBUtil.getInstance().getConnection(Consts.CON_POOL);
        	conn.setAutoCommit(false);
//        	String sql1="insert into ucpaas_statistics.tb_srv_client_fee(sid,client_number,fee,pre_balance,stype,app_sid,client_count,create_date)values(?,?,?,?,?,?,?,now())";
//        	String sql2="update tb_bill_acct_balance set balance=balance-? where sid=? ";
//            pstmt = conn.prepareStatement(sql1);
//            pstmt.setString(1,clientFee.getAcctId());
//            pstmt.setString(2, clientFee.getClientId());
//            pstmt.setLong(3,clientFee.getFee());
//            pstmt.setLong(4, 0);
//            pstmt.setString(5, clientFee.getStype());
//            pstmt.setString(6, clientFee.getAppId());
//            pstmt.setInt(7, 1);
//            res=pstmt.executeUpdate();
//            DBUtil.closePStmt(pstmt);
            //添加子账户余额记录
            String sql4="insert into tb_bill_client_balance(sid,client_number,balance,enable_flag,create_time)values(?,?,?,'1',now())";
            pstmt4=conn.prepareStatement(sql4);
            pstmt4.setString(1, clientFee.getAcctId());
            pstmt4.setString(2, clientFee.getClientId());
            pstmt4.setLong(3, clientFee.getClientBalance());
            int res4=pstmt4.executeUpdate();
            DBUtil.closePStmt(pstmt4);
            res=res+res4;
            //
//            pstmt2=conn.prepareStatement(sql2);
//            pstmt2.setLong(1,clientFee.getFee());
//            pstmt2.setString(2,clientFee.getAcctId());
//            int res2=pstmt2.executeUpdate();
//            DBUtil.closePStmt(pstmt2);
//            res=res+res2;
            String sql3="update tb_ucpaas_client set status=? where client_number=?";
            pstmt3=conn.prepareStatement(sql3);
            pstmt3.setString(1,"1");
            pstmt3.setString(2,clientFee.getClientId());
            int res3=pstmt3.executeUpdate();
            DBUtil.closePStmt(pstmt3);
            res=res+res3;
            conn.commit();
            conn.setAutoCommit(true);
            return res;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("执行异常",e);
			return 0;
		}finally{
			DBUtil.closeConn(conn);
		}
    }
    public int deFee2(ClientFee clientFee){
    	Connection conn = null;
        PreparedStatement pstmt3 = null;
        PreparedStatement pstmt4 = null;
        int res=0;
        try {
        	List<Object[]> paramList = new ArrayList<Object[]>();
			paramList.add(new Object[]{"string",clientFee.getAcctId()});
        	conn = DBUtil.getInstance().getConnection(Consts.CON_POOL);
        	conn.setAutoCommit(false);
            //添加子账户余额记录
            String sql4="insert into tb_bill_client_balance(sid,client_number,balance,enable_flag,create_time)values(?,?,?,'1',now())";
            pstmt4=conn.prepareStatement(sql4);
            pstmt4.setString(1, clientFee.getAcctId());
            pstmt4.setString(2, clientFee.getClientId());
            pstmt4.setLong(3, clientFee.getClientBalance());
            int res4=pstmt4.executeUpdate();
            DBUtil.closePStmt(pstmt4);
            res=res+res4;
            String sql3="update tb_ucpaas_client set status=? where client_number=?";
            pstmt3=conn.prepareStatement(sql3);
            pstmt3.setString(1,"1");
            pstmt3.setString(2,clientFee.getClientId());
            int res3=pstmt3.executeUpdate();
            DBUtil.closePStmt(pstmt3);
            res=res+res3;
            conn.commit();
            conn.setAutoCommit(true);
            return res;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("执行异常",e);
			return 0;
		}finally{
			DBUtil.closeConn(conn);
		}
    }
    public int deFee(String sid,String clientNumber,long fee,String appSid,String chargeType,long balance){
    	Connection conn = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        PreparedStatement pstmtx = null;
        ResultSet rSet=null;
        int res=0;
        try {
        	conn = DBUtil.getInstance().getConnection(Consts.CON_POOL);
        	conn.setAutoCommit(false);
        	
        	//添加子账户余额记录
            String sqlx="select balance from tb_bill_client_balance where client_number=?";
            pstmtx=conn.prepareStatement(sqlx);
            pstmtx.setString(1,clientNumber);
            rSet=pstmtx.executeQuery();
            long clientBalance=0;
            while (rSet.next()) {
            	clientBalance=rSet.getLong(1);
			}
            if (clientBalance<fee) {
            	fee=clientBalance;
			}
            DBUtil.closeRst(rSet);
			DBUtil.closePStmt(pstmtx);
        	
        	String sql1="insert into ucpaas_statistics.tb_srv_client_fee_[DATE](sid,client_number,fee,pre_balance,stype,app_sid,client_count,create_date)values(?,?,?,?,?,?,?,now())";
        	SimpleDateFormat sdFormat=new SimpleDateFormat("yyyyMM");
        	String now =sdFormat.format(new Date());
        	sql1=sql1.replace("[DATE]", now);
            pstmt = conn.prepareStatement(sql1);
            pstmt.setString(1,sid);
            pstmt.setString(2, clientNumber);
            pstmt.setLong(3,fee);
            pstmt.setLong(4, balance);
            if (chargeType.equals("0")) {
            	 pstmt.setString(5,Consts.CLIENT_CHARGE_IN);
			}else {
				 pstmt.setString(5,Consts.CLIENT_CHARGE_OUT);
			}
            pstmt.setString(6,appSid);
            pstmt.setInt(7, 1);
            res=pstmt.executeUpdate();
            DBUtil.closePStmt(pstmt);
			
            String sql2="update tb_bill_client_balance set balance=balance-? where client_number=?";
            pstmt2=conn.prepareStatement(sql2);
            pstmt2.setLong(1, fee);
            pstmt2.setString(2,clientNumber);
            int res2=pstmt2.executeUpdate();
            DBUtil.closePStmt(pstmt2);
            res=res+res2;
            conn.commit();
            conn.setAutoCommit(true);
            return res;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("执行异常",e);
			return 0;
		}finally{
			DBUtil.closeConn(conn);
		}
    }
    public int update(String sqlCode,List<Object[]> paramList){
    	Connection conn = null;
        PreparedStatement pstmt = null;
        try {
        	conn = DBUtil.getInstance().getConnection(Consts.CON_POOL);
        	String sql=getSql(sqlCode);
        	logger.debug(sql);
            pstmt = conn.prepareStatement(sql);
            if (paramList!=null&&paramList.size()>0) {
            	for (int i = 1; i <=paramList.size(); i++) {
            		Object[] objects=paramList.get(i-1);
            		if (objects[0].equals("number")) {
						pstmt.setLong(i, Long.parseLong(objects[1].toString()));
					}else if (objects[0].equals("string")) {
						pstmt.setString(i, objects[1].toString());
					}
    			}
			}
            int res=pstmt.executeUpdate();
            return res;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("执行异常sqlCode="+sqlCode,e);
			return 0;
		}finally{
			DBUtil.closePStmt(pstmt);
			DBUtil.closeConn(conn);
		}
    }
    public int update(String sqlCode,List<Object[]> paramList,boolean issql){
    	Connection conn = null;
        PreparedStatement pstmt = null;
        try {
        	conn = DBUtil.getInstance().getConnection(Consts.CON_POOL);
        	String sql=sqlCode;
        	if (!issql) {
				sql=getSql(sqlCode);
			}
        	logger.debug(sql);
            pstmt = conn.prepareStatement(sql);
            if (paramList!=null&&paramList.size()>0) {
            	for (int i = 1; i <=paramList.size(); i++) {
            		Object[] objects=paramList.get(i-1);
            		if (objects[0].equals("number")) {
						pstmt.setLong(i, Long.parseLong(objects[1].toString()));
					}else if (objects[0].equals("string")) {
						pstmt.setString(i, objects[1].toString());
					}
    			}
			}
            int res=pstmt.executeUpdate();
            return res;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("执行异常sqlCode="+sqlCode,e);
			return 0;
		}finally{
			DBUtil.closePStmt(pstmt);
			DBUtil.closeConn(conn);
		}
    }
    public long insert(String sqlCode,List<Object[]> paramList,boolean issql){
    	Connection conn = null;
        PreparedStatement pstmt = null;
        try {
        	conn = DBUtil.getInstance().getConnection(Consts.CON_POOL);
        	String sql=sqlCode;
        	if (!issql) {
				sql=getSql(sqlCode);
			}
        	logger.debug(sql);
            pstmt = conn.prepareStatement(sql);
            if (paramList!=null&&paramList.size()>0) {
            	for (int i = 1; i <=paramList.size(); i++) {
            		Object[] objects=paramList.get(i-1);
            		if (objects[0].equals("number")) {
						pstmt.setLong(i, Long.parseLong(objects[1].toString()));
					}else if (objects[0].equals("string")) {
						pstmt.setString(i, objects[1].toString());
					}
    			}
			}
            pstmt.executeUpdate();
            String sequncesql="select @@identity";
            PreparedStatement pstmt2 = conn.prepareStatement(sequncesql);
            ResultSet rs = pstmt2.executeQuery();
            long sequnce=0;
            while (rs.next()){
            	sequnce=rs.getLong(1);
            }
            return sequnce;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("执行异常sqlCode="+sqlCode,e);
			return 0;
		}finally{
			DBUtil.closePStmt(pstmt);
			DBUtil.closeConn(conn);
		}
    }
    /**
     * @autor (段元俊)(duanyj)
     * @param sqlCode
     * @param paramList
     * @return
     */
    public List<Map<String,Object>> queryForList(String sqlCode,List<Object[]> paramList){
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Map<String,Object>> resultList=new ArrayList<Map<String,Object>>();
        try{
        	conn = DBUtil.getInstance().getConnection(Consts.CON_POOL);
        	String sql=getSql(sqlCode);
        	logger.debug(sql);
            pstmt = conn.prepareStatement(sql);
            if (paramList!=null&&paramList.size()>0) {
            	for (int i = 1; i <=paramList.size(); i++) {
            		Object[] objects=paramList.get(i-1);
            		if (objects[0].equals("number")) {
						pstmt.setLong(i, Long.parseLong(objects[1].toString()));
					}else if (objects[0].equals("string")) {
						pstmt.setString(i, objects[1].toString());
					}
    			}
			}
            rs = pstmt.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while (rs.next()){
            	Map<String, Object> map = new Hashtable<String, Object>();
            	for (int j = 0; j < columnCount; j++){
					map.put(md.getColumnName(j+1),rs.getObject(j+1)==null?"":rs.getObject(j+1));
            	}
            	resultList.add(map);
            }
        }catch (SQLException e){
        	logger.error("查询"+sqlCode+"失败:", e);
        }catch (Exception e) {
			// TODO: handle exception
        	logger.error("执行queryForList出现异常:", e);
		}finally{
            DBUtil.closeAllPStmt(conn, pstmt, rs);
        }
        return resultList;
    }
    public List<Map<String,String>> queryForListStr(String sqlCode,List<Object[]> paramList){
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Map<String,String>> resultList=new ArrayList<Map<String,String>>();
        try{
        	conn = DBUtil.getInstance().getConnection(Consts.CON_POOL);
        	String sql=getSql(sqlCode);
        	logger.debug(sql);
            pstmt = conn.prepareStatement(sql);
            if (paramList!=null&&paramList.size()>0) {
            	for (int i = 1; i <=paramList.size(); i++) {
            		Object[] objects=paramList.get(i-1);
            		if (objects[0].equals("number")) {
						pstmt.setLong(i, Long.parseLong(objects[1].toString()));
					}else if (objects[0].equals("string")) {
						pstmt.setString(i, objects[1].toString());
					}
    			}
			}
            rs = pstmt.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while (rs.next()){
            	Map<String, String> map = new Hashtable<String, String>();
            	for (int j = 0; j < columnCount; j++){
					map.put(md.getColumnName(j+1),rs.getString(j+1)==null?"":rs.getString(j+1));
            	}
            	resultList.add(map);
            }
        }catch (SQLException e){
        	logger.error("查询"+sqlCode+"失败:", e);
        }catch (Exception e) {
			// TODO: handle exception
        	logger.error("执行queryForList出现异常:", e);
		}finally{
            DBUtil.closeAllPStmt(conn, pstmt, rs);
        }
        return resultList;
    }
    public List<Map<String,String>> queryForListStr(String sqlCode,List<Object[]> paramList,boolean isCode){
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Map<String,String>> resultList=new ArrayList<Map<String,String>>();
        try{
        	conn = DBUtil.getInstance().getConnection(Consts.CON_POOL);
        	String sql=sqlCode;
        	if (isCode) {
        		sql=getSql(sqlCode);
			}
        	logger.debug(sql);
            pstmt = conn.prepareStatement(sql);
            if (paramList!=null&&paramList.size()>0) {
            	for (int i = 1; i <=paramList.size(); i++) {
            		Object[] objects=paramList.get(i-1);
            		if (objects[0].equals("number")) {
						pstmt.setLong(i, Long.parseLong(objects[1].toString()));
					}else if (objects[0].equals("string")) {
						pstmt.setString(i, objects[1].toString());
					}
    			}
			}
            rs = pstmt.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while (rs.next()){
            	Map<String, String> map = new Hashtable<String, String>();
            	for (int j = 0; j < columnCount; j++){
					map.put(md.getColumnName(j+1),rs.getString(j+1)==null?"":rs.getString(j+1));
            	}
            	resultList.add(map);
            }
        }catch (SQLException e){
        	logger.error("查询"+sqlCode+"失败:", e);
        }catch (Exception e) {
			// TODO: handle exception
        	logger.error("执行queryForList出现异常:", e);
		}finally{
            DBUtil.closeAllPStmt(conn, pstmt, rs);
        }
        return resultList;
    }
    /**
     * 
     * @autor (段元俊)(duanyj)
     * @param sql
     * @return
     */
    public List<Map<String,Object>> queryForList(String sql,List<Object[]> paramList,boolean is){
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Map<String,Object>> resultList=new ArrayList<Map<String,Object>>();
        try{
        	conn = DBUtil.getInstance().getConnection(Consts.CON_POOL);
            pstmt = conn.prepareStatement(sql);
            if (paramList!=null&&paramList.size()>0) {
            	for (int i = 1; i <=paramList.size(); i++) {
            		Object[] objects=paramList.get(i-1);
            		if (objects[0].equals("number")) {
						pstmt.setLong(i, Long.parseLong(objects[1].toString()));
					}else if (objects[0].equals("string")) {
						pstmt.setString(i, objects[1].toString());
					}
    			}
			}
            rs = pstmt.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while (rs.next()){
            	Map<String, Object> map = new Hashtable<String, Object>();
            	for (int j = 0; j < columnCount; j++) {
            		map.put(md.getColumnName(j+1), rs.getObject(j+1)==null?"":rs.getObject(j+1));
            	}
            	resultList.add(map);
            }
        }catch (SQLException e){
        	logger.error("查询"+sql+"失败:", e);
        }catch (Exception e) {
			// TODO: handle exception
        	logger.error("执行queryForList出现异常:", e);
		}finally{
            DBUtil.closeAllPStmt(conn, pstmt, rs);
        }
        return resultList;
    }
    public List<Map<String,Object>> queryForList(String sql){
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Map<String,Object>> resultList=new ArrayList<Map<String,Object>>();
        try{
        	conn = DBUtil.getInstance().getConnection(Consts.CON_POOL);
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while (rs.next()){
            	Map<String, Object> map = new Hashtable<String, Object>();
            	for (int j = 0; j < columnCount; j++) {
            		map.put(md.getColumnName(j+1), rs.getObject(j+1)==null?"":rs.getObject(j+1));
            	}
            	resultList.add(map);
            }
        }catch (SQLException e){
        	logger.error("查询"+sql+"失败:", e);
        }catch (Exception e) {
			// TODO: handle exception
        	logger.error("执行queryForList出现异常:", e);
		}finally{
            DBUtil.closeAllPStmt(conn, pstmt, rs);
        }
        return resultList;
    }
    
	/**
	 * @autor Administrator(duanyj)
	 * @param sql
	 * @param paramList
	 * @return
	 */
	public int queryForInt(String sqlCode,List<Object[]> paramList){
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count=0;
        try{
        	conn = DBUtil.getInstance().getConnection(Consts.CON_POOL);
        	String sql=getSql(sqlCode);
        	logger.debug(sql);
            pstmt = conn.prepareStatement(sql);
            if (paramList!=null&&paramList.size()>0) {
            	for (int i = 1; i <=paramList.size(); i++) {
            		Object[] objects=paramList.get(i-1);
            		if (objects[0].equals("number")) {
						pstmt.setLong(i, Long.parseLong(objects[1].toString()));
					}else if (objects[0].equals("string")) {
						pstmt.setString(i, objects[1].toString());
					}
    			}
			}
            rs = pstmt.executeQuery();
            while (rs.next()){
            	count=rs.getInt(1);
            }
        }catch (SQLException e){
        	logger.error("查询"+sqlCode+"失败:", e);
        }catch (Exception e) {
			// TODO: handle exception
        	logger.error("执行queryForInt出现异常:", e);
		}finally{
            DBUtil.closeAllPStmt(conn, pstmt, rs);
        }
        return count;
	}
	public int queryForInt(String sqlCode,List<Object[]> paramList,boolean isCode){
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count=0;
        try{
        	conn = DBUtil.getInstance().getConnection(Consts.CON_POOL);
        	String sql=sqlCode;
        	if (isCode) {
        		sql=getSql(sqlCode);;
			}
        	logger.debug(sql);
            pstmt = conn.prepareStatement(sql);
            if (paramList!=null&&paramList.size()>0) {
            	for (int i = 1; i <=paramList.size(); i++) {
            		Object[] objects=paramList.get(i-1);
            		if (objects[0].equals("number")) {
						pstmt.setLong(i, Long.parseLong(objects[1].toString()));
					}else if (objects[0].equals("string")) {
						pstmt.setString(i, objects[1].toString());
					}
    			}
			}
            rs = pstmt.executeQuery();
            while (rs.next()){
            	count=rs.getInt(1);
            }
        }catch (SQLException e){
        	logger.error("查询"+sqlCode+"失败:", e);
        }catch (Exception e) {
			// TODO: handle exception
        	logger.error("执行queryForInt出现异常:", e);
		}finally{
            DBUtil.closeAllPStmt(conn, pstmt, rs);
        }
        return count;
	}
	/**
	 * @autor Administrator(duanyj)
	 * @param sql
	 * @param paramList
	 * @return
	 */
	public long queryForLong(String sqlCode,List<Object[]> paramList,boolean isCode){
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        long numberValue=0;
        try{
        	conn = DBUtil.getInstance().getConnection(Consts.CON_POOL);
        	String sql="";
        	if (isCode) {
        		sql=getSql(sqlCode);
			}else {
				sql=sqlCode;
			}
        	logger.debug(sql);
            pstmt = conn.prepareStatement(sql);
            if (paramList!=null&&paramList.size()>0) {
            	for (int i = 1; i <=paramList.size(); i++) {
            		Object[] objects=paramList.get(i-1);
            		if (objects[0].equals("number")) {
						pstmt.setLong(i, Long.parseLong(objects[1].toString()));
					}else if (objects[0].equals("string")) {
						pstmt.setString(i, objects[1].toString());
					}
    			}
			}
            rs = pstmt.executeQuery();
            while (rs.next()){
            	numberValue=rs.getLong(1);
            }
        }catch (SQLException e){
        	logger.error("查询"+sqlCode+"失败:", e);
        }catch (Exception e) {
			// TODO: handle exception
        	logger.error("执行queryForLong出现异常:", e);
		}finally{
            DBUtil.closeAllPStmt(conn, pstmt, rs);
        }
        return numberValue;
	}
	/**
	 * @autor Administrator(duanyj)
	 * @param sql
	 * @param paramList
	 * @return
	 */
	public long queryForLong2(String sqlCode,List<Object[]> paramList,boolean isCode){
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        long numberValue=-100;
        try{
        	conn = DBUtil.getInstance().getConnection(Consts.CON_POOL);
        	String sql="";
        	if (isCode) {
        		sql=getSql(sqlCode);
			}else {
				sql=sqlCode;
			}
        	logger.debug(sql);
            pstmt = conn.prepareStatement(sql);
            if (paramList!=null&&paramList.size()>0) {
            	for (int i = 1; i <=paramList.size(); i++) {
            		Object[] objects=paramList.get(i-1);
            		if (objects[0].equals("number")) {
						pstmt.setLong(i, Long.parseLong(objects[1].toString()));
					}else if (objects[0].equals("string")) {
						pstmt.setString(i, objects[1].toString());
					}
    			}
			}
            rs = pstmt.executeQuery();
            while (rs.next()){
            	numberValue=rs.getLong(1);
            }
        }catch (SQLException e){
        	logger.error("查询"+sqlCode+"失败:", e);
        }catch (Exception e) {
			// TODO: handle exception
        	logger.error("执行queryForLong出现异常:", e);
		}finally{
            DBUtil.closeAllPStmt(conn, pstmt, rs);
        }
        return numberValue;
	}
	/**
	 * @autor Administrator(duanyj)
	 * @param sql
	 * @param paramList
	 * @return
	 */
	public String queryForString(String sqlCode,List<Object[]> paramList){
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String strVal=null;
        try{
        	conn = DBUtil.getInstance().getConnection(Consts.CON_POOL);
        	String sql=getSql(sqlCode);
        	logger.debug(sql);
            pstmt = conn.prepareStatement(sql);
            if (paramList!=null&&paramList.size()>0) {
            	for (int i = 1; i <=paramList.size(); i++) {
            		Object[] objects=paramList.get(i-1);
            		if (objects[0].equals("number")) {
						pstmt.setLong(i, Long.parseLong(objects[1].toString()));
					}else if (objects[0].equals("string")) {
						pstmt.setString(i, objects[1].toString());
					}
    			}
			}
            rs = pstmt.executeQuery();
            while (rs.next()){
            	strVal=rs.getString(1);
            }
        }catch (SQLException e){
        	logger.error("查询"+sqlCode+"失败:", e);
        }catch (Exception e) {
			// TODO: handle exception
        	logger.error("执行queryForString出现异常:", e);
		}finally{
            DBUtil.closeAllPStmt(conn, pstmt, rs);
        }
        return strVal;
	}
	/**
	 * key-value
	 * @autor Administrator(duanyj)
	 * @param sql
	 * @param paramList
	 * @return
	 */
	public Map<String,String> queryStrMap(String sql,List<Object[]> paramList){
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Map<String, String> map = new Hashtable<String, String>();
        try{
        	conn = DBUtil.getInstance().getConnection(Consts.CON_POOL);
            pstmt = conn.prepareStatement(sql);
            if (paramList!=null&&paramList.size()>0) {
            	for (int i = 1; i <=paramList.size(); i++) {
            		Object[] objects=paramList.get(i-1);
            		if (objects[0].equals("number")) {
						pstmt.setLong(i, Long.parseLong(objects[1].toString()));
					}else if (objects[0].equals("string")) {
						pstmt.setString(i, objects[1].toString());
					}
    			}
			}
            rs = pstmt.executeQuery();
            while (rs.next()){
            	map.put(rs.getString(1), rs.getString(2)==null?"":rs.getString(2));
            }
        }catch (SQLException e){
        	logger.error("查询"+sql+"失败:", e);
        }catch (Exception e) {
			// TODO: handle exception
        	logger.error("执行queryForList出现异常:", e);
		}finally{
            DBUtil.closeAllPStmt(conn, pstmt, rs);
        }
        return map;
    }
    /**
     * @param content
     * @return
     */
    protected boolean createLogFile(String savePath, String content){
        boolean flag = false;
        OutputStreamWriter osw = null;
        FileOutputStream fos = null;
        savePath = savePath.replaceAll("\\\\", "/");
        File parentFile = new File(savePath);
        if (!parentFile.exists())
        {
            parentFile.mkdirs();
        }
        Date d = Calendar.getInstance().getTime();
        // 当前日期
        String curDate = TimeUtil.formatDateTime(d, "yyyyMMdd");
        // 当前分钟
        String curMinute = TimeUtil.formatDateTime(d, "yyyyMMddHHmm");
        String todayDoc = savePath + curDate;
        File todayDocFile = new File(todayDoc);
        // 创建每天的日志目录
        if (!todayDocFile.exists())
        {
            todayDocFile.mkdir();
        }
        // 当前需要操作的文件
        String fileFullPath = todayDocFile + File.separator + curMinute;
        File curFile = new File(fileFullPath);
        // 写入日志内容
        try
        {
            // 如果有则写入日志否则根据当前分钟数创建新的文件
            if (!curFile.exists())
            {
                curFile.createNewFile();
            }
            fos = new FileOutputStream(curFile, true);
            osw = new OutputStreamWriter(fos, "UTF-8");
            osw.write(content);
            osw.flush();
            if (logger.isDebugEnabled())
            {
            	logger.debug("createLogFile success|savePath="
                    + curFile.getAbsolutePath() + "|content=========="
                    + content);
            }
            flag = true;
        }
        catch (IOException e)
        {
        	logger.error("write to file failed:" + curFile.getAbsolutePath(), e);
        }
        finally
        {
            StreamUtil.close(osw);
            StreamUtil.close(fos);
        }
        return flag;
    }
}
