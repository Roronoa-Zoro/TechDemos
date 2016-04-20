package com.lp.recon.event;

import java.util.Observable;
import java.util.Observer;

import com.lp.recon.lifecycle.Lifecycle;

public class LifecycleEventListener implements Observer {

	@Override
	public void update(Observable lifecycle, Object event) {
		String eventType = (String) event;
		if (LifecycleEvent.STOP.equals(eventType)) {
			Lifecycle lc = (Lifecycle) lifecycle;
			try {
				lc.stop();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
