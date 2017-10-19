package com.ucpaas.commonservice.util.db;

import java.util.HashMap;
import java.util.Map;

public class DBShardingUtil {

	public static final String DB_NODE_0 = "0";
	public static final String DB_NODE_1 = "1";
	public static final int[] NODE_0_RANGE = new int[] { 0, 499 }; // 物理节点分表索引区间
	public static final int[] NODE_1_RANGE = new int[] { 500, 999 }; // 物理节点分表索引区间
	public static final int TABLE_MOD = 1000; // 分表数量

	/**
	 * 根据key值计算分表index
	 * 
	 * @param key
	 * @return [0] uin表索引，[1]物理节点索引，对应不同的物理节点
	 */
	public static String[] getDBNodeByKey(String key) {
		char[] chars = key.toCharArray(); // 把字符中转换为字符数组
		int sum = 0;
		String db_node = "0";
		for (int i = 0; i < chars.length; i++) {// 输出结果
			sum += (int) chars[i];
		}
		int uin_mod = sum % 1000;
		String str_uin_mod = uin_mod<10?("0"+uin_mod):(""+uin_mod);
		if (uin_mod >= DBShardingUtil.NODE_0_RANGE[0]
				&& uin_mod <= DBShardingUtil.NODE_0_RANGE[1]) {
			db_node = DBShardingUtil.DB_NODE_0;
		} else if (uin_mod >= DBShardingUtil.NODE_1_RANGE[0]
				&& uin_mod <= DBShardingUtil.NODE_1_RANGE[1]) {
			db_node = DBShardingUtil.DB_NODE_1;
		}
		return new String[]{ str_uin_mod, db_node };
	}
	
	
	/**
	 * 根据key值计算分表index
	 * 
	 * @param key
	 * @return [0] uin表索引，[1]物理节点索引，对应不同的物理节点
	 */
	public static Map<String, String> getMapDBNodeByKey(String key) {
		Map<String,String> map = new HashMap<String,String>();
		char[] chars = key.toCharArray(); // 把字符中转换为字符数组
		int sum = 0;
		String db_node = "0";
		for (int i = 0; i < chars.length; i++) {// 输出结果
			sum += (int) chars[i];
		}
		int uin_mod = sum % 1000;
		String str_uin_mod = uin_mod<10?("0"+uin_mod):(""+uin_mod);
		if (uin_mod >= DBShardingUtil.NODE_0_RANGE[0]
				&& uin_mod <= DBShardingUtil.NODE_0_RANGE[1]) {
			db_node = DBShardingUtil.DB_NODE_0;
		} else if (uin_mod >= DBShardingUtil.NODE_1_RANGE[0]
				&& uin_mod <= DBShardingUtil.NODE_1_RANGE[1]) {
			db_node = DBShardingUtil.DB_NODE_1;
		}
		map.put("uin_mod", str_uin_mod);
		map.put("db_node", db_node);
		
		return map;
//		return new String[]{ str_uin_mod, db_node };
	}
	
	/**
	 * 根据clientnumber返回对应的uin、db_node
	 * @param clientNumber
	 * @return
	 */
	public static String[] getDBNodeByClientNumber(String clientNumber){
		String db_node = "0";
		int uin = Integer.parseInt(clientNumber.substring(5));
		int uin_mod = uin % 1000;
		String str_uin = ""+uin;
		String str_uin_mod = ""+uin_mod;
		if (uin_mod >= DBShardingUtil.NODE_0_RANGE[0]
				&& uin_mod <= DBShardingUtil.NODE_0_RANGE[1]) {
			db_node = DBShardingUtil.DB_NODE_0;
		} else if (uin_mod >= DBShardingUtil.NODE_1_RANGE[0]
				&& uin_mod <= DBShardingUtil.NODE_1_RANGE[1]) {
			db_node = DBShardingUtil.DB_NODE_1;
		}
		
		return new String[]{ str_uin, db_node,str_uin_mod };
	}
	
	/**
	 * 根据clientnumber返回对应的uin、db_node
	 * @param clientNumber
	 * @return
	 */
	public static Map<String, String> getMapDBNodeByClientNumber(String clientNumber){
		Map<String,String> map = new HashMap<String,String>();
		String db_node = "0";
		int uin = Integer.parseInt(clientNumber.substring(5));
		int uin_mod = uin % 1000;
		String str_uin = ""+uin;
		String str_uin_mod = ""+uin_mod;
		if (uin_mod >= DBShardingUtil.NODE_0_RANGE[0]
				&& uin_mod <= DBShardingUtil.NODE_0_RANGE[1]) {
			db_node = DBShardingUtil.DB_NODE_0;
		} else if (uin_mod >= DBShardingUtil.NODE_1_RANGE[0]
				&& uin_mod <= DBShardingUtil.NODE_1_RANGE[1]) {
			db_node = DBShardingUtil.DB_NODE_1;
		}
		
		map.put("uin", str_uin);
		map.put("db_node", db_node);
		map.put("uin_mod", str_uin_mod);
		return map;
//		return new String[]{ str_uin, db_node,str_uin_mod };
	}
	
	/**
	 * 根据uin返回数据节点、分表
	 * @param uin
	 * @return
	 */
	public static Map<String, String> getDBNodeByUin(int uin){
		Map<String,String> map = new HashMap<String,String>();
		String db_node = "0";
		int uin_mod = uin % 1000;
		String str_uin_mod = ""+uin_mod;
		if (uin_mod >= DBShardingUtil.NODE_0_RANGE[0]
				&& uin_mod <= DBShardingUtil.NODE_0_RANGE[1]) {
			db_node = DBShardingUtil.DB_NODE_0;
		} else if (uin_mod >= DBShardingUtil.NODE_1_RANGE[0]
				&& uin_mod <= DBShardingUtil.NODE_1_RANGE[1]) {
			db_node = DBShardingUtil.DB_NODE_1;
		}
		map.put("db_node", db_node);
		map.put("uin_mod", str_uin_mod);
		return map;
//		return new String[]{ str_uin_mod, db_node };
	}
	
	/**
	 * 根据uin返回数据节点、分表
	 * @param uin
	 * @return
	 */
	public static Map<String, String> getMapDBNodeByUin(int uin){
		Map<String,String> map = new HashMap<String,String>();
		String db_node = "0";
		int uin_mod = uin % 1000;
		String str_uin_mod = ""+uin_mod;
		if (uin_mod >= DBShardingUtil.NODE_0_RANGE[0]
				&& uin_mod <= DBShardingUtil.NODE_0_RANGE[1]) {
			db_node = DBShardingUtil.DB_NODE_0;
		} else if (uin_mod >= DBShardingUtil.NODE_1_RANGE[0]
				&& uin_mod <= DBShardingUtil.NODE_1_RANGE[1]) {
			db_node = DBShardingUtil.DB_NODE_1;
		}
		map.put("uin_mod", str_uin_mod);
		map.put("db_node", db_node);
		
//		return new String[]{ str_uin_mod, db_node };
		return map;
	}
	
	
	/**
	 * 根据index返回数据节点
	 * @param uin
	 * @return
	 */
	public static String getDBNodeByIndex(int index){
		String db_node = "0";
		if (index >= DBShardingUtil.NODE_0_RANGE[0]
				&& index <= DBShardingUtil.NODE_0_RANGE[1]) {
			db_node = DBShardingUtil.DB_NODE_0;
		} else if (index >= DBShardingUtil.NODE_1_RANGE[0]
				&& index <= DBShardingUtil.NODE_1_RANGE[1]) {
			db_node = DBShardingUtil.DB_NODE_1;
		}
		return db_node;
//		return new String[]{ str_uin_mod, db_node };
	}
	
	public static void main(String rags[]){
		String clientNumber = "63244003000422";

	
		String[] arr = DBShardingUtil.getDBNodeByClientNumber(clientNumber);
		System.out.println("@@@@@@@@@@@@@@@@@@DBShardingUtil.getDBNodeByClientNumber("+clientNumber+")");
		System.out.println("str_uin="+arr[0]+",db_node="+arr[1]+",str_uin_mod="+arr[2]);
		System.out.println("数据库节点db_node="+arr[1]+",子账号表=tb_ucpaas_client_"+arr[2]+",子账号记录的uin="+arr[0]);
		
		
		String attr = "67063040407889_464e0000c4a64a9da1d9faa4deba454f";
		System.out.println("###################DBShardingUtil.getDBNodeByKey("+attr+")");
		String[] arr2 = DBShardingUtil.getDBNodeByKey(attr);
		System.out.println("str_uin_mod="+arr2[0]+",db_node="+arr2[1]+",数据库节点db_node="+arr2[1]+",反向表=attr2uin_"+arr2[0]);
		
		
		int uin = 21729;
		System.out.println("$$$$$$$$$$$$$$$$$$$DBShardingUtil.getMapDBNodeByUin("+uin+")");
		Map<String, String> uinmap = DBShardingUtil.getMapDBNodeByUin(uin);
		System.out.println("uinmap="+uinmap.toString()+",数据库节点db_node="+uinmap.get("db_node")+",子账号表=tb_ucpaas_client_"+uinmap.get("uin_mod").toString());
		

	}
	
	

}
