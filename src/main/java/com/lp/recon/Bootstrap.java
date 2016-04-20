package com.lp.recon;

import java.util.List;

import com.lp.recon.lifecycle.Lifecycle;

public class Bootstrap {

	private List<Lifecycle> workers;
	
	public List<Lifecycle> getWorkers() {
		return workers;
	}

	public void setWorkers(List<Lifecycle> workers) {
		this.workers = workers;
	}

	public void startProcessor() {
		for (Lifecycle worker : workers) {
			try {
				worker.start();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("failed to start up");
			}
		}
		System.err.println("Bootstrap is started");
	}
}
