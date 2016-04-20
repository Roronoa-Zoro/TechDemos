package com.lp.recon;

import java.util.concurrent.CountDownLatch;

public abstract class DataTask implements Runnable {

	private CountDownLatch done;
	
	public CountDownLatch getDone() {
		return done;
	}

	public void setDone(CountDownLatch done) {
		this.done = done;
	}

	protected abstract void doTask() throws Exception;

	@Override
	public void run() {
		try {
			doTask();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
