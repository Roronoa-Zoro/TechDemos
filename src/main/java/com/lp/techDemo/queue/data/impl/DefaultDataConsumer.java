package com.lp.techDemo.queue.data.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lp.techDemo.queue.QueueManager;
import com.lp.techDemo.queue.data.DataConsumer;
import com.lp.techDemo.queue.data.DataRouter;

public class DefaultDataConsumer implements DataConsumer, Runnable {

	private String queueName;
	private DataRouter dr;
	private String consumerName;
	
	public String getConsumerName() {
		return consumerName;
	}

	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}

	public DataRouter getDr() {
		return dr;
	}

	public void setDr(DataRouter dr) {
		this.dr = dr;
	}
	
	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	@Override
	public void consumeData() {
		ExecutorService pool = Executors.newFixedThreadPool(2);
		for (int i = 0; i < 2; i++) {
			pool.submit(this);
		}
		System.err.println("consumer started======================");
	}
	
	@Override
	public void run() {
		while(true) {
//			System.err.println("getting data==========================");
			Object msg = QueueManager.getMessage(queueName);
			if (msg != null) {
				System.err.println(consumerName + "--" + Thread.currentThread().getName() + "--" + msg);
				if (dr != null) {
					dr.routeData(msg);
				}
			}
		}
	}

}
