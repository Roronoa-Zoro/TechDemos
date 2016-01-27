package com.lp.techDemo.queue;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class QueueManager {

	private static final Map<String, BlockingQueue<?>> queueHolder = new ConcurrentHashMap<>();
	
	private QueueManager() {
	}
	
	public static Object getMessage(String queue) {
		BlockingQueue<Object> q = getQueue(queue);
		return q.poll();
	}
	
	public static void putMessage(String queue, Object msg){
		BlockingQueue<Object> q = getQueue(queue);
		q.offer(msg);
	}
	
	private static BlockingQueue<Object> getQueue(String queue) {
		if(queueHolder.get(queue) != null){
			return (BlockingQueue<Object>) queueHolder.get(queue);
		}
		
		synchronized(queueHolder){
			if (queueHolder.get(queue) != null) {
				return (BlockingQueue<Object>) queueHolder.get(queue);
			}
			BlockingQueue<Object> q = new LinkedBlockingQueue<>();
			queueHolder.put(queue, q);
			return q;
		}
			
	}

}
