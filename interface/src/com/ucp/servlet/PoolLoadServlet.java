package com.ucp.servlet;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.tools.DBUtil;
import com.ucp.bean.RedisInfo;
import com.ucp.service.ConKeyService;
import com.ucp.service.DBCommService;
import com.ucp.service.RedisService;
import com.ucp.util.Consts;
import com.ucp.util.SqlCode;
import com.ucp.util.SysConfig;
/**
 * @author  duanyj
 */
public class PoolLoadServlet extends HttpServlet{
    /**
	 * 
	 */
    private static final long serialVersionUID = 7342447423991935951L;
    private static final Logger log = Logger.getLogger(PoolLoadServlet.class);

    /**
     * 初始化
     * @throws ServletException 
     */
    public void init(javax.servlet.ServletConfig config) throws ServletException{
    	super.init(config);
        log.info("start load proxool.xml:"+getClass().getResource("/proxool.xml").getPath());
        // 初始化相关数据库连接
        DBUtil.getInstance().initweblogic(getClass().getResource("/proxool.xml").getPath());
        log.info("初始化sql");
        DBCommService.getInstance().setSqlMap(SqlCode.QUERY_SQL_CODE, null);
//        DBCommService.getInstance().setDspList();
        log.info("加载子账号扣费用户");
        RedisInfo redisInfo=new RedisInfo();
		String servers=SysConfig.getInstance().getProperty("redis_servers", "172.16.10.36");
		String port=SysConfig.getInstance().getProperty("port", "6379");
		String maxIdle=SysConfig.getInstance().getProperty("redis_maxIdle", "10");
		String maxActive=SysConfig.getInstance().getProperty("redis_maxActive", "500");
		String maxWait=SysConfig.getInstance().getProperty("redis_maxWait", "100000");
		String testOnBorrow=SysConfig.getInstance().getProperty("redis_testOnBorrow", "true");
		redisInfo.setIp(servers);
		redisInfo.setMaxActive(maxActive);
		redisInfo.setMaxIdle(maxIdle);
		redisInfo.setMaxWait(maxWait);
		redisInfo.setPort(port);
		redisInfo.setTestOnBorrow(testOnBorrow);
		RedisService redisService=RedisService.getInstance();
		redisService.setRedisInfo(redisInfo);
		redisService.Init();
		log.info("初始化缓存线程池开始...");
//		String reqMethod=SysConfig.getInstance().getProperty("req_method");
//		int cacheSize=SysConfig.getInstance().getPropertyInt("cache_size");
//		if (reqMethod.equals("local")) {
//			ConKeyService.getInstance().setCacheSize(cacheSize);
//			String refreshTime=SysConfig.getInstance().getProperty("refresh_cur_num");
//			String refreshFile=SysConfig.getInstance().getProperty("write_cur_num_redis");
//			new Timer().schedule(new MemInitThread(0),5000,Integer.parseInt(refreshTime)*60*1000);
//			new Timer().schedule(new MemInitThread(1),15000,Integer.parseInt(refreshFile)*60*1000);
//		}
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/javascript;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String action=request.getParameter("action");
		if (action!=null) {
			String curNbr = RedisService.getInstance().getAndSet(Consts.KEY_CURNBR);
			response.getWriter().write(curNbr);
		}else {
			Map<String, String> nextMap=ConKeyService.getInstance().getNextNbr();
			response.getWriter().write(JSONObject.fromObject(nextMap).toString());
		}
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
