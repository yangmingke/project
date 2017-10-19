package com.yzx.rest.servlet;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.tools.DBUtil;
import com.yzx.rest.bean.RedisInfo;
import com.yzx.rest.service.ConKeyService;
import com.yzx.rest.service.RedisService;
import com.yzx.rest.util.Consts;
import com.yzx.rest.util.SysConfig;
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
    	String springPrefix = System.getProperty("spring.profiles.active");
        // 初始化相关数据库连接
    	log.info("初始化数据库连接:"+getClass().getResource("/proxool_"+springPrefix+".xml").getPath());
        DBUtil.getInstance().initweblogic(getClass().getResource("/proxool_"+springPrefix+".xml").getPath());
        RedisInfo redisInfo=new RedisInfo();
		String servers=SysConfig.getInstance().getProperty("redis_servers", "59.110.10.28");
		String port=SysConfig.getInstance().getProperty("redis_port", "6379");
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
