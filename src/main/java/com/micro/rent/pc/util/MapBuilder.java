package com.micro.rent.pc.util;

import java.util.HashMap;
import java.util.Map;

public final class MapBuilder {

	private Map<String, Object> parameter;

	private MapBuilder(String key, Object value) {
		parameter = new HashMap<String, Object>();
		parameter.put(key, value);
	}

	public static MapBuilder with(String key, Object value) {
		return new MapBuilder(key, value);
	}

	public MapBuilder add(String key, Object value) {
		parameter.put(key, value);
		return this;
	}

	/**
	 * 返回map
	 * 
	 * @return
	 */
	public Map<String, Object> build() {
		return parameter;
	}

}
