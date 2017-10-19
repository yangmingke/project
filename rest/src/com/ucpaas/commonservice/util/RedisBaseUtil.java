package com.ucpaas.commonservice.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Repository
public class RedisBaseUtil<K, V> {

	private static final Logger log = LoggerFactory.getLogger(RedisBaseUtil.class);

    @Autowired
	protected RedisTemplate<K, V> redisTemplate;

	/**
	 * 将k，v存入缓存
	 * 永久有效期
	 * 建议使用带有效期的方法
	 * @param key    key
	 * @param value  键值
	 */
	public void setToCache(K key, V value) {
		long startTime = System.currentTimeMillis();
		try {
            //System.out.println("redisTemplate对象："+redisTemplate);
			this.redisTemplate.opsForValue().set(key, value);
			log.debug("【存入缓存】,key={},value={},redisTimeCount={}," , key, value,(System.currentTimeMillis()-startTime));
		} catch (Exception e) {
			log.error("【存入缓存】出错,key=" + key + ",value=" + value+",redisTimeCount=" + (System.currentTimeMillis()-startTime), e);
		}
	}

	/**
	 * 将k，v存入缓存
	 * 永久有效期
	 * 建议使用带有效期的方法
	 * @param key    key
	 * @param value  键值
	 */
	public void setToHashCache(K name,K key, V value) {
		long startTime = System.currentTimeMillis();
		try {
            //System.out.println("redisTemplate对象："+redisTemplate);
			this.redisTemplate.opsForHash().put(name, key, value);
			log.debug("【存入Hash缓存】,name={},key={},value={},redisTimeCount={}," ,name, key, value,(System.currentTimeMillis()-startTime));
		} catch (Exception e) {
			log.error("【存入Hash缓存】出错,name=" + name + ",key=" + key + ",value=" + value+",redisTimeCount=" + (System.currentTimeMillis()-startTime), e);
		}
		
	}
	
	public V getFromCache(K key) {
		long startTime = System.currentTimeMillis();
		V v = null;
		try {
            //System.out.println("redisTemplate对象："+redisTemplate);
            v = this.redisTemplate.opsForValue().get(key);
			log.debug("【查询缓存】,key={},redisTimeCount={}," , key, (System.currentTimeMillis()-startTime));
			return v;
		} catch (Exception e) {
			log.error("【查询缓存】出错,key=" + key +",redisTimeCount=" + (System.currentTimeMillis()-startTime), e);
		}
		return v;
		
	}

	/**
	 * 从redis的Hash上获取
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public V getFromHashCache(K name,K key) {
		long startTime = System.currentTimeMillis();
		V v = null;
		try {
            //System.out.println("redisTemplate对象："+redisTemplate);
            v = (V) this.redisTemplate.opsForHash().get(name, key);
			log.debug("【查询HASH缓存】,name={},key={},redisTimeCount={}," ,name, key, (System.currentTimeMillis()-startTime));
			return v;
		} catch (Exception e) {
			log.error("【查询HASH缓存】出错,name=" + name + "key=" + key +",redisTimeCount=" + (System.currentTimeMillis()-startTime), e);
		}
		return v;
		
	}
	
	public void deleteFromCache(K key) {
		long startTime = System.currentTimeMillis();
		try {
            //System.out.println("redisTemplate对象："+redisTemplate);
			this.redisTemplate.delete(key);
			log.debug("【删除缓存】,key={},redisTimeCount={}," , key, (System.currentTimeMillis()-startTime));
		} catch (Exception e) {
			log.error("【删除缓存】出错,key=" + key +",redisTimeCount=" + (System.currentTimeMillis()-startTime), e);
		}
	}
	
	/**
	 * 删除HASH
	 * @param key
	 */
	public void deleteFromHashCache(K name,K key) {
		long startTime = System.currentTimeMillis();
		try {
            //System.out.println("redisTemplate对象："+redisTemplate);
			this.redisTemplate.opsForHash().delete(name, key);
			log.debug("【删除HASH缓存】,name={},key={},redisTimeCount={}," , name,key, (System.currentTimeMillis()-startTime));
		} catch (Exception e) {
			log.error("【删除HASH缓存】出错,name=" + name + ",key=" + key +",redisTimeCount=" + (System.currentTimeMillis()-startTime), e);
		}
	}
	
	/**
	 * 将数据存入缓存，设置有效秒数
	 * @param key           key
	 * @param value         键值
	 * @param expireTime	有效秒数
	 */
	public void setToCacheSeconds(K key, V value, long expireTime) {
		long startTime = System.currentTimeMillis();
		try {
            //System.out.println("redisTemplate对象："+redisTemplate);
			this.redisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
			log.debug("【存入缓存Seconds】,key={},value={},seconds={},redisTimeCount={}," , key,value,expireTime,(System.currentTimeMillis()-startTime));
		} catch (Exception e) {
			log.error("【存入缓存Seconds】出错,key=" + key + ",value=" + value+",seconds="+expireTime+",redisTimeCount=" + (System.currentTimeMillis()-startTime), e);
		}
		
	}
	
	/**
	 * 将数据存入缓存，设置有效天数
	 * 有效天数为redis_day_min、redis_day_max之间的随机整数
	 * @param key   key
	 * @param value 键值
	 * @param days	有效天数
	 */
	public void setToCacheDays(K key, V value, int days) {
		long startTime = System.currentTimeMillis();
		try {
            //System.out.println("redisTemplate对象："+redisTemplate);
			this.redisTemplate.opsForValue().set(key, value, days, TimeUnit.DAYS);
			log.info("【存入缓存Days】,key={},value={},days={},redisTimeCount={}," , key,value,days,(System.currentTimeMillis()-startTime));
		} catch (Exception e) {
			log.error("【存入缓存Days】出错,key=" + key + ",value=" + value+",days="+days+",redisTimeCount=" + (System.currentTimeMillis()-startTime), e);
		}
	}
	
	/**
	 * 发布订阅消息
	 * @param channel
	 * @param message
	 */
	public void sendMessage(String channel, V message){
		long startTime = System.currentTimeMillis();
		try {
			this.redisTemplate.convertAndSend(channel, message);
			log.debug("【发布订阅消息】,channel={},message={},days={},redisTimeCount={}," , channel,message,(System.currentTimeMillis()-startTime));
		} catch (Exception e) {
			log.error("【发布订阅消息】出错,channel=" + channel + ",message=" + message + ",redisTimeCount=" + (System.currentTimeMillis()-startTime), e);
		}
	}
	
	/**
	 * 设置有效期
	 * @param key
	 * @param expireTime
	 */
	public void setExpireTime(K key,Date expireTime) {
		long startTime = System.currentTimeMillis();
		try {
			this.redisTemplate.expireAt(key, expireTime);
			log.debug("【设置redis有效期】,key={},expireTime={},redisTimeCount={}",key,expireTime,(System.currentTimeMillis() - startTime));
		} catch (Exception e) {
			log.error("【设置redis有效期】出错,key=" + key + ",expireTime=" + expireTime + ",redisTimeCount="
							+ (System.currentTimeMillis() - startTime), e);
		}
	}
	
	/**
	 * 获取所有keys
	 */
	
	public Set<K> getAllKeys(K name){
		Set<K> set=null;
		try {
			 set= this.redisTemplate.keys(name);
		} catch (Exception e) {
			log.error("【查询key缓存】出错,name=" + name, e);
		}
		return set;
	}
}
