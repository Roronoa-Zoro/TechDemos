package com.lp.techDemo.disruptor.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public enum NumberCalculator {

	Instance;
	private ConcurrentHashMap<String, AtomicLong> methodNameMap;
	private ConcurrentHashMap<String, AtomicLong> methodTimeMap;
	private Object lockOne;
	private Object lockTwo;
	
	NumberCalculator(){
		methodNameMap = new ConcurrentHashMap<String, AtomicLong>();
		methodTimeMap = new ConcurrentHashMap<String, AtomicLong>();
		lockOne = new Object();
		lockTwo = new Object();
	}
	
	public void increaseMethodName(String key){
		AtomicLong al = methodNameMap.get(key);
		if (al == null){
			synchronized(lockOne) {
				if (al == null) {
					al = new AtomicLong();
					methodNameMap.putIfAbsent(key, al);
				}
			}
		}
		
		al.incrementAndGet();
	}
	
	public void increaseMethodTime(String key, Long time){
		AtomicLong al = methodTimeMap.get(key);
		if (al == null){
			synchronized(lockTwo) {
				if (al == null) {
					al = new AtomicLong();
					methodTimeMap.putIfAbsent(key, al);
				}
			}
		}
		
		al.getAndAdd(time);
	}
}
