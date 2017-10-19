package com.ucp.util;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ucp.bean.MemInfo;
import com.ucp.service.RedisService;
public class CancelAction extends Thread {
	private static Logger logger = Logger.getLogger(CancelAction.class);
	private static int give_up_time=SysConfig.getInstance().getPropertyInt("give_up_time",5);
	private static volatile CancelAction instance;
	private final ConcurrentLinkedQueue<MemInfo> memInfosList = new ConcurrentLinkedQueue<MemInfo>();
	private ThreadPoolExecutor pool;
    private CancelAction(){
    	pool = new ThreadPoolExecutor(12, 20, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(512),
                new ThreadPoolExecutor.CallerRunsPolicy());
    	pool.execute(new MemActionHandler());
    }
    public static CancelAction getInstance(){
    	if (instance == null)
        {
            synchronized (CancelAction.class)
            {
                if (instance == null)
                {
                    instance = new CancelAction();
                }
            }
        }
        return instance;
    }
    public void addQuene(MemInfo info){
    	memInfosList.add(info);
    }
    public ConcurrentLinkedQueue<MemInfo> getForceLists(){
    	return memInfosList;
    }
    public int getActiveCount(){
    	return pool.getActiveCount();
    }
    public int getPoolSize()
    {
        return pool.getPoolSize();
    }
    // 返回核心线程数
    public int getCorePoolSize()
    {
        return pool.getCorePoolSize();
    }

    public int getLargestPoolSize()
    {
        return pool.getLargestPoolSize();
    }

    public long getCompletedTaskCount()
    {
        return pool.getCompletedTaskCount();
    }

    public long getTaskCount()
    {
        return pool.getTaskCount();
    }
    /**
     * @author (段元俊)(duanyj)
     * @date 2013-7-1
     */
    private class MemActionHandler implements Runnable{
        private final int WAITTIME = 100;
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true){
				try {
					MemInfo memInfo=memInfosList.poll();
					if (memInfo==null) {
						try {
							Thread.sleep(WAITTIME);
						} catch (Exception e) {
							// TODO: handle exception
						}
						continue;
					}
					long nowtime=new Date().getTime();
					long time=memInfo.getTime();
					String callId=memInfo.getCallId();
					String ip=RedisService.getInstance().get(Consts.getKey(Consts.KEY_ROUTE,memInfo.getCallId()));
					if (StringUtils.isEmpty(ip)){
						if (nowtime<time+(give_up_time*1000)) {
							addQuene(memInfo);
							//logger.info("路由信息还没返回,继续队列等待callId["+callId+"]");
						}else {
							logger.info("路由信息还没返回,等待超时，放弃本次取消通话操作callId["+callId+"]");
						}
					}else {
						String baseUrl=SysConfig.getInstance().getProperty("sms_server");
			    		StringBuffer sBuffer=new StringBuffer();
						sBuffer.append(baseUrl).append("/autocall?brandid=yzx")
						.append("&cbtype=4").append("&callId=").append(callId)
						.append("&ip=").append(ip);
						logger.info(sBuffer.toString());
						try {
							HttpHelper.httpConnectionGet(sBuffer.toString());
							String key=Consts.getKey(Consts.KEY_ROUTE, callId);
							RedisService.getInstance().delKey(key);
						} catch (Exception e) {
							// TODO: handle exception
							logger.error(e);
							return;
						}
						logger.info("取消缓存通话["+callId+"],ip["+ip+"]");
					}
				} catch (Exception e) {
					// TODO: handle exception
					logger.error("队列入缓存失败");
				}
			}
		}
    }
}
