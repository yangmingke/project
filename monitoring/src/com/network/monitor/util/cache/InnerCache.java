package com.network.monitor.util.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InnerCache<K, V> {
	final Map<K, Long> loadTime = new HashMap<K, Long>();
	final long LOAD_TIME_MS = 3600 * 1000;// 一小时
	final ConcurrentHashMap<K, V> cache = new ConcurrentHashMap<K, V>();

	public final V get(K key) {
		V result = null;
		if (!loadTime.containsKey(key)) {
			loadTime.put(key, 0L);
		}
		long a = loadTime.get(key);
		long now = System.currentTimeMillis();
		if (a < now) {
			if (!cache.containsKey(key)) {
				load(key);
			}
		}
		result = cache.get(key);
		return result;
	}

	private void load(K key) {
		V value = fetch(key);
		if (null != value) {
			cache.put(key, value);
			loadTime.put(key, System.currentTimeMillis() + LOAD_TIME_MS);
		}
	}

	protected V fetch(K key) {
		return null;
	}

	public final void remove(K key) {
		cache.remove(key);
		loadTime.put(key, 0L);
	}

	public final void reload(K type) {
		load(type);
	}
}
