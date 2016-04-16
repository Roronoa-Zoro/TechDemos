package com.lp.recon;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class ReconThreadFactory implements ThreadFactory {

	private String threadName;
	private AtomicInteger ai = new AtomicInteger(0);
	
	public ReconThreadFactory(String threadName) {
		super();
		this.threadName = threadName;
	}

	@Override
	public Thread newThread(Runnable r) {
		return new Thread(r, threadName + "_" + ai.getAndIncrement());
	}

}
