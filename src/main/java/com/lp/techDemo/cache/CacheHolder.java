package com.lp.techDemo.cache;

import java.util.concurrent.ConcurrentHashMap;

public class CacheHolder {

	private final ConcurrentHashMap<String, Object> holder = new ConcurrentHashMap<>();
	
	public void reload() {
		//TODO load data from db, and put them into holder
	}
	
	public Object getValue(String key) {
		return holder.get(key);
	}
	
	public boolean set(String key, Object value) {
		//TODO insert into db first
		Object previousV = holder.put(key, value);
		if (previousV == null) {
			return true;
		}
		
		return false;
	}
	
	public int getCacheValueNum() {
		return holder.size();
	}
}
