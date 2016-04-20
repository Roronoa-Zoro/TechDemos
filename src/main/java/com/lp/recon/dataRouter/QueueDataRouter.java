package com.lp.recon.dataRouter;

import com.lp.recon.DataRouter;
import com.lp.recon.ReconMessage;
import com.lp.recon.queue.QueueManager;

public class QueueDataRouter implements DataRouter {

	private String queueName;
	
	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	@Override
	public void routeMessage(ReconMessage rm) throws Exception {
		QueueManager.getQueue(queueName).put(rm);
	}

}
