package com.lp.recon.dataReader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import com.lp.recon.DataReader;
import com.lp.recon.ReconMessage;
import com.lp.recon.queue.QueueManager;

public class QueueDataReader implements DataReader {

	private String queueName;
	private int batchSize;
	
	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public int getBatchSize() {
		return batchSize;
	}

	public void setBatchSize(int batchSize) {
		this.batchSize = batchSize;
	}

	@Override
	public List<ReconMessage> readData() throws Exception {
		BlockingQueue<ReconMessage> queue = QueueManager.getQueue(queueName);
		int index = 0;
		List<ReconMessage> data = new ArrayList<>();
		while (index < batchSize) {
			if (queue.peek() == null) {
				break;
			}
			data.add(queue.poll());
			index++;
		}
		return data;
	}

}
