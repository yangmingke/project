package com.network.monitor.util;

import java.util.HashMap;
import java.util.Map;

public final class MapKit {
	private MapKit() {
	}
	public static final Map<String, Object> toMap(Object... objects) {
		Map<String, Object> result = new HashMap<String, Object>();
		int len = objects.length;
		len = len - len % 2;
		for (int i = 0; i < len; i++) {
			result.put((String) objects[i], (Object) objects[++i]);
		}
		return result;
	}
}
