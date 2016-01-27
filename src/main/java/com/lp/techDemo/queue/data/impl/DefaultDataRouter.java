package com.lp.techDemo.queue.data.impl;

import com.lp.techDemo.queue.QueueManager;
import com.lp.techDemo.queue.data.DataRouter;

public class DefaultDataRouter implements DataRouter {

	private String queueName;
	
	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	@Override
	public void routeData(Object msg) {
		QueueManager.putMessage(queueName, msg);
	}

}
