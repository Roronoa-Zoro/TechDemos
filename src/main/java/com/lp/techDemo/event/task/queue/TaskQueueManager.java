package com.lp.techDemo.event.task.queue;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TaskQueueManager {

	private static Map<String, TaskQueue> queueHolder = 
			new ConcurrentHashMap<>();
	private static Object lock = new Object();
	
	public static TaskQueue getTaskQueue(String queueName) {
		TaskQueue queue = queueHolder.get(queueName);
		if (queue != null) {
			return queue;
		}
		synchronized(lock) {
			queue = queueHolder.get(queueName);
			if (queue != null) {
				return queue;
			}
			TaskQueue tq = new TaskQueue(queueName);
			queueHolder.put(queueName, tq);
			return tq;
		}
	}
}
