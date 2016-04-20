package com.lp.recon.dataTask;

import com.lp.recon.DataTask;
import com.lp.recon.event.LifecycleEvent;

public class MonitorDataTask extends DataTask {

	private LifecycleEvent event;
	
	public LifecycleEvent getEvent() {
		return event;
	}

	public void setEvent(LifecycleEvent event) {
		this.event = event;
	}

	@Override
	public void doTask() throws Exception {
		super.getDone().await();
		System.err.println(Thread.currentThread().getName() + " fire stop event................");
		event.fireLifecycleEvent(LifecycleEvent.STOP);
	}

}
