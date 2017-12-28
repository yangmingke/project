package com.flypaas.constant;
/**
* @author 作者 ZhaoXueFeng:
* @version 创建时间：2017年1月5日 上午11:12:05
* 类说明
*/
public class RouterConstant {
	public static final String	 url="http://ip:port/router/v1/notifyrtpp";
	
	public static final String   ip = "127.0.0.1";
	
	public static final int   post = 8080;
	
	public static final String add = "1";
	
	public static final String del = "2";
	
	public static final String upd = "3";
	
	/**
	 * redis路由域key值列表
	 */
	public static final String ROUTE_KEYS = "RTPP_CONCURR_ZSET*";
	/**
	 * redisRTPP节点key值列表
	 */
	public static final String INFO_KEYS = "RTPP_INFO_*";
	/**
	 * redis路由域默认查询值
	 */
	public static final String ROUTE_DEFAULT_KEY = "RTPP_CONCURR_ZSET_cn";
	/**
	 * redis路由域默认查询值
	 */
	public static final String ROUTE_DEFAULT_KEY_CN = "cn";
	
	/**
	 * redis路由域默认查询值
	 */
	public static final String PRE_ROUTE_KEY = "RTPP_CONCURR_ZSET_";
	/**
	 * redis RTPP key值前缀
	 */
	public static final String PRE_RTPP_INTO = "RTPP_INFO_";
	/**
	 * redisRTPP会话key前缀值列表
	 */
	public static final String PRE_SESSION_KEYS = "SESS_PATH_";
	/**
	 * redisRTPP会话key前缀值列表
	 */
	public static final String PRE_TRAFIC_KEYS = "RTPP_NBR_trafic_";
	/**
	 * redis RTPP 邻居节点优先策略
	 */
	public static final String NBR_QUALITY = "RTPP_NBR_quality_";//质量优先
	public static final String NBR_PERF = "RTPP_NBR_perf_";//性价比优先
	public static final String NBR_PRICE = "RTPP_NBR_price_";//价格优先
	
	public static final String WEIGHT_QUALITY = "RTPP_weight_quality_";//质量权重
	public static final String SPT_QUALITY = "RTPP_SPT_quality_";//最优路径
	
	/**
	 * 排序策略
	 */
	public static final String ASC = "ASC";//升序
	public static final String DESC = "DESC";//降序
	
	public static final double RADII = 0.2d;//节点地图上城市的默认半径

	public static final Integer DEFAULT_DIFFERENTIAL = 100;
	//不可达值
	public static final Integer DISCONNECT = 16777215;//2^24 -1
	
	/**
	 * 路由分析缓存key
	 */
	public static final String routeAnalysisResult = "routeAnalysisResult";
	
	public static final String resultBetter = "1";
	public static final String resultWorse = "2";
	public static final String resultUnsure = "3";
	public static final String resultUnchanged = "4";
	public static final String resultSure = "5";
	
}
