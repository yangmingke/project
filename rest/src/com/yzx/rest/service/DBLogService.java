package com.yzx.rest.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tools.DBUtil;
import com.yzx.rest.util.Consts;
public class DBLogService {
	private static final Logger logger = Logger.getLogger(DBLogService.class);
	private static DBLogService dbLogService=new DBLogService();
    public static DBLogService getInstance(){
    	return dbLogService;
    }
    public int update(String sql,List<Object[]> paramList){
    	Connection conn = null;
        PreparedStatement pstmt = null;
        try {
        	conn = DBUtil.getInstance().getConnection(Consts.CON_LOG);
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
			e.printStackTrace();
			logger.error("执行异常sqlCode="+sql,e);
			return 0;
		}finally{
			DBUtil.closePStmt(pstmt);
			DBUtil.closeConn(conn);
		}
    }

    public int queryForInt(String sql,List<Object[]> paramList){
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count=0;
        try{
        	conn = DBUtil.getInstance().getConnection(Consts.CON_LOG);
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
        	logger.error("查询"+sql+"失败:", e);
        }catch (Exception e) {
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
	public long queryForLong(String sql,List<Object[]> paramList){
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        long numberValue=0;
        try{
        	conn = DBUtil.getInstance().getConnection(Consts.CON_LOG);
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
        	logger.error("查询"+sql+"失败:", e);
        }catch (Exception e) {
        	logger.error("执行queryForLong出现异常:", e);
		}finally{
            DBUtil.closeAllPStmt(conn, pstmt, rs);
        }
        return numberValue;
	}
	public List<Map<String,Object>> queryForList(String sql,List<Object[]> paramList){
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Map<String,Object>> resultList=new ArrayList<Map<String,Object>>();
        try{
        	conn = DBUtil.getInstance().getConnection(Consts.CON_LOG);
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
        	logger.error("查询"+sql+"失败:", e);
        }catch (Exception e) {
        	logger.error("执行queryForList出现异常:", e);
		}finally{
            DBUtil.closeAllPStmt(conn, pstmt, rs);
        }
        return resultList;
    }
	public List<Map<String,String>> queryForListStr(String sql,List<Object[]> paramList){
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Map<String,String>> resultList=new ArrayList<Map<String,String>>();
        try{
        	conn = DBUtil.getInstance().getConnection(Consts.CON_LOG);
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
        	logger.error("查询"+sql+"失败:", e);
        }catch (Exception e) {
        	logger.error("执行queryForList出现异常:", e);
		}finally{
            DBUtil.closeAllPStmt(conn, pstmt, rs);
        }
        return resultList;
    }
}
