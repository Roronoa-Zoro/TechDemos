package com.lp.recon.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import com.lp.recon.ReconMessage;

public class QueueManager {

	private static final ConcurrentHashMap<String, BlockingQueue<ReconMessage>> queueHolder = new ConcurrentHashMap<>();
	
	public static BlockingQueue<ReconMessage> getQueue(String queueName) {
		if (queueHolder.get(queueName) != null) {
			return queueHolder.get(queueName);
		}
		return initQueue(queueName);
	}
	
	private static BlockingQueue<ReconMessage> initQueue(String queueName) {
		if (queueHolder.get(queueName) != null) {
			return queueHolder.get(queueName);
		}
		BlockingQueue<ReconMessage> queue = new LinkedBlockingQueue<>();
		queueHolder.putIfAbsent(queueName, queue);
		return queueHolder.get(queueName);
	}
}
