package com.yzx.rest.service;

import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.yzx.rest.bean.RedisInfo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisService {
	private static final Logger logger = Logger.getLogger(RedisService.class);
	private static RedisService redisService=new RedisService();
	public static RedisService getInstance(){
		return redisService;
	}
	private static JedisPool jedisPool = null;
	private static JedisPoolConfig config=null;
	private RedisInfo redisInfo;
	
	public RedisInfo getRedisInfo() {
		return redisInfo;
	}
	public void setRedisInfo(RedisInfo redisInfo) {
		this.redisInfo = redisInfo;
	}
	public void Init(){
		synchronized (RedisService.class){
			if (config==null) {
				config = new JedisPoolConfig();
				logger.info("加载Redis配置");
			}
		}
		initPool();
		synchronized (RedisService.class){
			if (jedisPool==null) {
				jedisPool=new JedisPool(config, redisInfo.getIp(), Integer.parseInt(redisInfo.getPort()));
				logger.info("初始化Redis连接池");
			}
		}
	}
	private void initPool(){
		if (config!=null) {
//			config.setMaxActive(Integer.parseInt(redisInfo.getMaxActive()));
			config.setMaxTotal(Integer.parseInt(redisInfo.getMaxActive()));
			config.setMaxIdle(Integer.parseInt(redisInfo.getMaxIdle()));
			config.setMaxWaitMillis(Long.parseLong(redisInfo.getMaxWait()));
			config.setMinIdle(10);//by yangmingke
//			config.setMaxWait(Long.parseLong(redisInfo.getMaxWait()));
			config.setTestOnBorrow(Boolean.parseBoolean(redisInfo.getTestOnBorrow()));
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
				// TODO: handle exception
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
}
