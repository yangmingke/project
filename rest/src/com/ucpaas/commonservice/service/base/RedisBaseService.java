package com.ucpaas.commonservice.service.base;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

public abstract class RedisBaseService<K, V> {
	private static final Logger log = LoggerFactory.getLogger(RedisBaseService.class);
	

	@Resource(name = "redisTemplate")
	protected RedisTemplate<K, V> redisTemplate;

	/**
	 * 将k，v存入缓存
	 * 永久有效期
	 * 建议使用带有效期的方法
	 * @param key
	 * @param value
	 */
	@Deprecated
	public void setToCache(K key, V value) {
		long startTime = System.currentTimeMillis();
		try {
			this.redisTemplate.opsForValue().set(key, value);
			log.info("【存入缓存】,key={},redisTimeCount={}," , key, (System.currentTimeMillis()-startTime));
		} catch (Exception e) {
			log.error("【存入缓存】错误,key=" + key + ",value=" + value+",redisTimeCount=" + (System.currentTimeMillis()-startTime), e);
		}
		
	}

	public V getFromCache(K key) {
		long startTime = System.currentTimeMillis();
		V v = null;
		try {
			v = this.redisTemplate.opsForValue().get(key);
			log.info("【查询缓存】,key={},redisTimeCount={}," , key, (System.currentTimeMillis()-startTime));
			return v;
		} catch (Exception e) {
			log.error("【查询缓存】错误,key=" + key +",redisTimeCount=" + (System.currentTimeMillis()-startTime), e);
		}
		return v;
		
	}

	public void deleteFromCache(K key) {
		long startTime = System.currentTimeMillis();
		try {
			this.redisTemplate.delete(key);
			log.info("【删除缓存】,key={},redisTimeCount={}," , key, (System.currentTimeMillis()-startTime));
		} catch (Exception e) {
			log.error("【删除缓存】错误,key=" + key +",redisTimeCount=" + (System.currentTimeMillis()-startTime), e);
		}
	}
	
	/**
	 * 将数据存入缓存，设置有效秒数
	 * @param key
	 * @param value
	 * @param expireTime	有效秒数
	 */
	public void setToCacheSeconds(K key, V value, long expireTime) {
		long startTime = System.currentTimeMillis();
		try {
			this.redisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
			log.info("【存入缓存Seconds】,key={},seconds={},redisTimeCount={}," , key, expireTime,(System.currentTimeMillis()-startTime));
		} catch (Exception e) {
			log.error("【存入缓存Seconds】错误,key=" + key + ",value=" + value+",seconds="+expireTime+",redisTimeCount=" + (System.currentTimeMillis()-startTime), e);
		}
		
	}
	
	/**
	 * 将数据存入缓存，设置有效天数
	 * 有效天数为redis_day_min、redis_day_max之间的随机整数
	 * @param key
	 * @param value
	 * @param days	有效天数
	 */
	public void setToCacheDays(K key, V value, int days) {
		long startTime = System.currentTimeMillis();
		try {
			this.redisTemplate.opsForValue().set(key, value, days, TimeUnit.DAYS);
			log.info("【存入缓存Days】,key={},days={},redisTimeCount={}," , key, days,(System.currentTimeMillis()-startTime));
		} catch (Exception e) {
			log.error("【存入缓存Days】错误,key=" + key + ",value=" + value+",days="+days+",redisTimeCount=" + (System.currentTimeMillis()-startTime), e);
		}
	}
	
	

}
