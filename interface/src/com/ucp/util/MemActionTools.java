package com.ucp.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import com.ucp.bean.MemInfo;
import com.ucp.service.RedisService;
public class MemActionTools extends Thread{
	private static Logger logger = Logger.getLogger(MemActionTools.class);
	private static volatile MemActionTools instance;
	private final ConcurrentLinkedQueue<MemInfo> memInfosList = new ConcurrentLinkedQueue<MemInfo>();
	private ThreadPoolExecutor pool;
    private MemActionTools(){
    	pool = new ThreadPoolExecutor(12, 20, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(512),
                new ThreadPoolExecutor.CallerRunsPolicy());
    	pool.execute(new MemActionHandler());
    }
    public static MemActionTools getInstance(){
    	if (instance == null)
        {
            synchronized (MemActionTools.class)
            {
                if (instance == null)
                {
                    instance = new MemActionTools();
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
     * 
     * @author (段元俊)(duanyj)
     */
    private class MemActionHandler implements Runnable{
        private final int WAITTIME = 1000;
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
					if (memInfo.getType().equals("query")||memInfo.getType().equals("add")) {
						RedisService.getInstance().set(memInfo.getKey(), memInfo.getValue());
					}else if (memInfo.getType().equals("edit")) {
						RedisService.getInstance().set(memInfo.getKey(), memInfo.getValue());
					}else if (memInfo.getType().equals("delete")) {
						RedisService.getInstance().delKey(memInfo.getKey());
					}else if(memInfo.getType().equals("clear")){
//						RedisService.getInstance().
					}else if (memInfo.getType().equals("hset")) {
						RedisService.getInstance().hset(memInfo.getKey(), memInfo.getField(), memInfo.getValue());
					}else if (memInfo.getType().equals("hmset")) {
						RedisService.getInstance().hmset(memInfo.getKey(), memInfo.getHashMap(), memInfo.getExpries());
					}
					logger.info(memInfo.getKey()+"操作"+memInfo.getType()+"缓存");
				} catch (Exception e) {
					// TODO: handle exception
					logger.error("队列入缓存失败");
				}
			}
		}
    	
    }
}
