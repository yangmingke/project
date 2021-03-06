package com.flypaas.service.impl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.flypaas.model.vo.RedisInfo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


public class RedisService {
	private static final Logger logger = Logger.getLogger(RedisService.class);
	
	private static RedisService redisService;
	private static JedisPool jedisPool = null;
	private static JedisPoolConfig config=null;
	private RedisInfo redisInfo = new RedisInfo();
	
	public  static RedisService getInstance(){
		if(redisService == null){
			synchronized (RedisService.class){
				if(redisService == null){
					redisService = new RedisService();
				}
			}
		}
		return redisService;
	}
	
	private RedisService(){
		redisInfo.init();
		synchronized (RedisService.class){
			if (config==null) {
				config = new JedisPoolConfig();
				logger.info("加载Redis配置");
			}
		}
		initPool();
		if (jedisPool==null) {
			//jedisPool=new JedisPool(config, redisInfo.getIp(), Integer.parseInt(redisInfo.getPort()));
			jedisPool=new JedisPool(config, redisInfo.getRedis_servers(), Integer.valueOf(redisInfo.getRedis_port()));
			logger.info("初始化Redis连接池");
		}
	}
	private void initPool(){
		if (config!=null) {
//			config.setMaxActive(Integer.parseInt(redisInfo.getRedis_maxActive()));
			config.setMaxTotal(Integer.parseInt(redisInfo.getRedis_maxActive()));
			config.setMaxIdle(Integer.parseInt(redisInfo.getRedis_maxIdle()));
			config.setMaxWaitMillis(Long.parseLong(redisInfo.getRedis_maxWait()));
//			config.setMinIdle(10);
//			config.setMaxWaitMillis(Long.parseLong(redisInfo.getMaxWait()));
//			config.setTestOnBorrow(Boolean.parseBoolean(redisInfo.getRedis_testOnBorrow));
			
//			config.setMaxIdle(RedisConstant.redis_maxIdle);
//			config.setMaxWaitMillis(RedisConstant.redis_maxWait);
//			config.setTestOnBorrow(true);
			logger.info("初始化Redis配置信息");
		}
	}
	private Jedis getJedis(){
		if (jedisPool==null) {
			initPool();
		}
		Jedis jedis=jedisPool.getResource();
		if (!jedis.isConnected()) {
			logger.error("redis未连接");
			try {
				logger.info("redis尝试连接");
				jedis.connect();
				logger.info("redis连接成功");
			} catch (Exception e) {
				logger.error("redis连接失败");
			}
		}
		return jedis;
	}
	
	//add
	public Long add(String key,String... value){
		Jedis jedis=null;
		try {
			jedis=getJedis();
			long res= jedis.sadd(key, value);
			logger.info("add key "+key);
			return res;
		}catch (Exception e) {  
			logger.error(e);
			jedisPool.returnBrokenResource(jedis);
			return 0l;
        } finally{
			jedisPool.returnResource(jedis);
		}
	}
	//all members
	public Set<String> smembers(String key){
		Jedis jedis=null;
		try {
			jedis=getJedis();
			Set<String> res= jedis.smembers(key);
			return res;
		}catch (Exception e) {  
			jedisPool.returnBrokenResource(jedis);
			return null;
        } finally{
			jedisPool.returnResource(jedis);
		}
	}
	//is exist
	public boolean sismember(String key,String value){
		Jedis jedis=null;
		try {
			jedis=getJedis();
			boolean res= jedis.sismember(key, value);
			return res;
		}catch (Exception e) {  
			jedisPool.returnBrokenResource(jedis);
			return false;
        } finally{
			jedisPool.returnResource(jedis);
		}
	}
	//numbers
	public Long scard(String key){
		Jedis jedis=null;
		try {
			jedis=getJedis();
			Long res= jedis.scard(key);
			return res;
		}catch (Exception e) {  
			jedisPool.returnBrokenResource(jedis);
			return 0l;
        } finally{
			jedisPool.returnResource(jedis);
		}
	}
	//delete
	public Long delKey(final String... key){
		Jedis jedis=null;
		try {
			jedis=getJedis();
			Long res= jedis.del(key);
			logger.info("delete key "+key);
			return res;
		}catch (Exception e) {
			logger.error(e);
			jedisPool.returnBrokenResource(jedis);
			return 0l;
        } finally{
			jedisPool.returnResource(jedis);
		}
	}
	//set a
	public String set(String key,String value){
 		Jedis jedis=null;
		try {
			jedis=getJedis();
			String res= jedis.set(key, value);
			logger.info("add key "+key+",value "+value);
			return res;
		}catch (Exception e) {  
			logger.error(e);
			jedisPool.returnBrokenResource(jedis);
			return null;
        } finally{
			jedisPool.returnResource(jedis);
		}
	}
	public synchronized String getAndSet(String key){
		Jedis jedis=null;
		try {
			jedis=getJedis();
			String cur = jedis.get(key);
			long curNum=0;
			if(StringUtils.isNotEmpty(cur)){
				curNum=Long.parseLong(cur);
			}
			long nownum=curNum+1;
			jedis.set(key, String.valueOf(nownum));
			logger.info("getAndSet key "+key);
			return String.valueOf(nownum);
		}catch (Exception e) { 
			logger.error(e);
			jedisPool.returnBrokenResource(jedis);
			return null;
        } finally{
			jedisPool.returnResource(jedis);
		}
	}
	//get a
	public String get(String key){
		Jedis jedis=null;
		try {
			jedis=getJedis();
			String res= jedis.get(key);
			logger.info("get key "+key);
			return res;
		}catch (Exception e) {  
			logger.error(e);
			jedisPool.returnBrokenResource(jedis);
			return null;
        } finally{
			jedisPool.returnResource(jedis);
		}
	}
	//redis监控
	public String monitorRedis(String key){
		Jedis jedis=getJedis();
		return jedis.get(key);
	}
	public Map<String, String> hgetall(String key){
		Jedis jedis=null;
		try {
			jedis=getJedis();
			Map<String, String> res= jedis.hgetAll(key);
			logger.info("hgetall key "+key);
			return res;
		}catch (Exception e) {  
			logger.error(e);
			jedisPool.returnBrokenResource(jedis);
			return null;
        } finally{
			jedisPool.returnResource(jedis);
		}
	}
	
	public String hget(String key,String field){
		Jedis jedis=null;
		try {
			jedis=getJedis();
			String val= jedis.hget(key,field);
			logger.info("hget key "+key + " field " + field);
			return val;
		}catch (Exception e) {  
			logger.error(e);
			jedisPool.returnBrokenResource(jedis);
			return null;
        } finally{
			jedisPool.returnResource(jedis);
		}
	}
	
	public String hmset(String key,Map<String,String> hash,int seconds){
		Jedis jedis=null;
		try {
			jedis=getJedis();
			String res= jedis.hmset(key, hash);
			if(seconds > 0){ //设置超时时间
				long expireNum = jedis.expire(key, seconds);
				logger.info("超时时间设置：key = " + key + ",seconds = " + seconds + ",expireNum = " + expireNum);
			}
			return res;
		}catch (Throwable e) {
			logger.error("redis的hmset方法错误, key = " + key + ",hash = " + hash + ",异常：", e);
			jedisPool.returnBrokenResource(jedis);
			return null;
        } finally{
			jedisPool.returnResource(jedis);
		}
	}
	public long hset(String key,String field,String value){
		Jedis jedis=null;
		try {
			jedis=getJedis();
			long res= jedis.hset(key, field, value);
			logger.info("hset key "+key);
			return res;
		}catch (Exception e) {  
			logger.error(e);
			jedisPool.returnBrokenResource(jedis);
			return 0;
        } finally{
			jedisPool.returnResource(jedis);
		}
	}
	//random a
	public String srandmember(String key){
		Jedis jedis=null;
		try {
			jedis=getJedis();
			String res= jedis.srandmember(key);
			return res;
		}catch (Exception e) {  
			jedisPool.returnBrokenResource(jedis);
			return null;
        } finally{
			jedisPool.returnResource(jedis);
		}
	}
	//remove a random
	public String spop(String key){
		Jedis jedis=null;
		try {
			jedis=getJedis();
			String res= jedis.spop(key);
			return res;
		}catch (Exception e) {  
			jedisPool.returnBrokenResource(jedis);
			return null;
        } finally{
			jedisPool.returnResource(jedis);
		}
	}
	//remove a or more member
	public Long srem(String key,String...members){
		Jedis jedis=null;
		try {
			jedis=getJedis();
			Long res= jedis.srem(key, members);
			return res;
		}catch (Exception e) {  
			jedisPool.returnBrokenResource(jedis);
			return 0l;
        } finally{
			jedisPool.returnResource(jedis);
		}
	}
	//move a member from one to another
	public Long smove(String srckey,String dstkey,String member){
		Jedis jedis=null;
		try {
			jedis=getJedis();
			Long res= jedis.smove(srckey, dstkey, member);
			return res;
		}catch (Exception e) {  
			jedisPool.returnBrokenResource(jedis);
			return 0l;
        } finally{
			jedisPool.returnResource(jedis);
		}
	}
	//union all
	public Set<String> sunion(String...keys){
		Jedis jedis=null;
		try {
			jedis=getJedis();
			Set<String> res= jedis.sunion(keys);
			return res;
		}catch (Exception e) {  
			jedisPool.returnBrokenResource(jedis);
			return null;
        } finally{
			jedisPool.returnResource(jedis);
		}
	}
	//uoion all keys store to dstkey
	public Long sunionstore(String dstkey,String...keys){
		Jedis jedis=null;
		try {
			jedis=getJedis();
			Long res= jedis.sunionstore(dstkey, keys);
			return res;
		}catch (Exception e) {  
			jedisPool.returnBrokenResource(jedis);
			return 0l;
        } finally{
			jedisPool.returnResource(jedis);
		}
	}
	//join inner all keys 
	public Set<String> sinter(String...keys){
		Jedis jedis=null;
		try {
			jedis=getJedis();
			Set<String> res= jedis.sinter(keys);
			return res;
		}catch (Exception e) {  
			jedisPool.returnBrokenResource(jedis);
			return null;
        } finally{
			jedisPool.returnResource(jedis);
		}
	}
	//inner all keys store to dstkey
	public Long sinter(String dstkey,String...keys){
		Jedis jedis=null;
		try {
			jedis=getJedis();
			Long res= jedis.sinterstore(dstkey, keys);
			return res;
		}catch (Exception e) {  
			jedisPool.returnBrokenResource(jedis);
			return 0l;
        } finally{
			jedisPool.returnResource(jedis);
		}
	}
	
	//inner all keys store to dstkey
		public Set<String> zrange(String key, long start, long end){
			Jedis jedis=null;
			try {
				jedis=getJedis();
				Set<String> res= jedis.zrange(key,start,end);
				return res;
			}catch (Exception e) {  
				jedisPool.returnBrokenResource(jedis);
				return null;
	        } finally{
				jedisPool.returnResource(jedis);
			}
		}
		
	/**
	 * @param regx
	 * @return
	 * Set<String>
	 * getKeys
	 */
	public Set<String> getKeys(String regx){
		Jedis jedis=null;
		try {
			jedis=getJedis();
			Set<String> set=jedis.keys(regx);
			return set;
		}catch (Exception e) {  
			jedisPool.returnBrokenResource(jedis);
			return null;
        } finally{
			jedisPool.returnResource(jedis);
		}
	}
	//set a
	public String setAndExpire(String key,String value,int seconds){
 		Jedis jedis=null;
		try {
			jedis=getJedis();
			String res= jedis.setex(key, seconds, value);
			logger.info("setAndExpire key "+key+",value "+value+",seconds "+seconds);
			return res;
		}catch (Exception e) {
			logger.error(e);
			jedisPool.returnBrokenResource(jedis);
			return null;
        } finally{
			jedisPool.returnResource(jedis);
		}
	}
	
	public static Map<String,String> getMapData(String flag)  
    {  
        Map<String,String> dataMap = null;  
          
        Jedis redisClient = null;  
        try  
        {  
            redisClient = jedisPool.getResource();  
            dataMap = redisClient.hgetAll(flag);  
        }   
        catch (Exception e)  
        {  
            // 销毁对象  
            jedisPool.returnBrokenResource(redisClient);  
        }  
        finally  
        {  
            // 还原到连接池  
            jedisPool.returnResource(redisClient);  
        }  
        return dataMap;  
    }  
	
	 /** 
     * Redis Lrange 返回列表中指定区间内的元素，区间以偏移量 START 和 END 指定。 其中 0 表示列表的第一个元素， 1 表示列表的第二个元素，以此类推。  
     * 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。 
     * @param string 
     * @param start 
     * @param end 
     * @return 
     */  
    public List<String> lrange(String key, int start, int end) {  
        Jedis jedis = jedisPool.getResource();
        logger.info("get key "+key);
        List<String> result = jedis.lrange(key, start, end);  
        jedis.close();  
        return result;  
    }  
    
    /** 
     * Redis Zrevrangebyscore 返回有序集中指定分数区间内的所有的成员。有序集成员按分数值递减(从大到小)的次序排列。 
        具有相同分数值的成员按字典序的逆序(reverse lexicographical order )排列。 
        除了成员按分数值递减的次序排列这一点外， ZREVRANGEBYSCORE 命令的其他方面和 ZRANGEBYSCORE 命令一样。 
     * @param key 
     * @param max 
     * @param min 
     * @param offset 
     * @param count 
     * @return 指定区间内，带有分数值(可选)的有序集成员的列表。 
     */  
    public static LinkedHashSet<String> zrevrangebyscore(String key, String max, String min, int offset, int count){  
        Jedis jedis = jedisPool.getResource();  
        LinkedHashSet<String> result = (LinkedHashSet<String>) jedis.zrevrangeByScore(key, max, min, offset, count);  
        jedis.close();  
        return result;  
    }  
}
