package com.flypaas.utils;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {

    private static final int CORE_POOL_SIZE = 20;

    private static final int MAXIMUN_POOL_SIZE = 100;
    
    private static final int KEEP_ALIVE_TIME = 60*60*1000;

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(
            CORE_POOL_SIZE, MAXIMUN_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1000));

    /**
     * 使用线程池执行任务
     * 
     * @param task
     */
    public static void excute(Runnable task) {
        executor.execute(task);
    }

    public static void main(String[] args) {
        Runnable run = new Runnable() {

            public void run() {
                System.out.println(Thread.currentThread());
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        excute(run);
        excute(run);
        excute(run);
    }

}
