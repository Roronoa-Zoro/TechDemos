package com.lp.recon.dataProcessor;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.lp.recon.DataTask;
import com.lp.recon.ReconThreadFactory;
import com.lp.recon.event.LifecycleEvent;

public class QueueDataProcessor extends LifecycleEvent { 

	private ThreadPoolExecutor pool;
	private int coreSize;
	private List<DataTask> dataTasks;
	
	public List<DataTask> getDataTasks() {
		return dataTasks;
	}

	public void setDataTasks(List<DataTask> dataTasks) {
		this.dataTasks = dataTasks;
	}

	public int getCoreSize() {
		return coreSize;
	}

	public void setCoreSize(int coreSize) {
		this.coreSize = coreSize;
	}
	

	@Override
	public void init() throws Exception {
		if (pool == null) {
			pool = new ThreadPoolExecutor(coreSize, 20, 1L, TimeUnit.SECONDS, 
					new LinkedBlockingQueue<Runnable>(), new ReconThreadFactory("QueueDataProcessor"));
		}
	}

	@Override
	public void start() throws Exception {
		for (int i = 0; i < pool.getCorePoolSize(); i++) {
			pool.execute(dataTasks.get(0));
		}
		//default
		pool.execute(dataTasks.get(1));
		System.err.println("queue data processor is started");
	}

	@Override
	public void stop() throws Exception {
		pool.shutdown();
		System.err.println("queue thread pool is shutdown");
	}

}
